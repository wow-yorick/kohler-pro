/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kohler.constants.CategorySQL;
import com.kohler.dao.CategoryAccessoryDao;
import com.kohler.dao.CategoryAccessoryMetadataDao;
import com.kohler.dao.CategoryBannerUnitDao;
import com.kohler.dao.CategoryComAttrDao;
import com.kohler.dao.CategoryComAttrMetadataDao;
import com.kohler.dao.CategoryDao;
import com.kohler.dao.CategoryHeroAreaDao;
import com.kohler.dao.CategoryMetadataDao;
import com.kohler.dao.CategorySKUAttrDao;
import com.kohler.dao.CategorySKUAttrMetadataDao;
import com.kohler.dao.CategorySKUAttrValuesDao;
import com.kohler.dao.CategorySKUAttrValuesMetadataDao;
import com.kohler.dao.CategorySearchKeywordDao;
import com.kohler.dao.CategorySearchKeywordMetadataDao;
import com.kohler.dao.ContentFieldValuesDao;
import com.kohler.dao.MasterDataCodeDao;
import com.kohler.dao.ProductDao;
import com.kohler.dao.ProductMetadataDao;
import com.kohler.dao.SysLogDao;
import com.kohler.dao.TemplateDao;
import com.kohler.dao.utils.Pager;
import com.kohler.entity.CategoryAccessoryEntity;
import com.kohler.entity.CategoryAccessoryMetadataEntity;
import com.kohler.entity.CategoryBannerUnitEntity;
import com.kohler.entity.CategoryComAttrEntity;
import com.kohler.entity.CategoryComAttrMetadataEntity;
import com.kohler.entity.CategoryEntity;
import com.kohler.entity.CategoryHeroAreaEntity;
import com.kohler.entity.CategoryMetadataEntity;
import com.kohler.entity.CategorySKUAttrEntity;
import com.kohler.entity.CategorySKUAttrMetadataEntity;
import com.kohler.entity.CategorySKUAttrValuesEntity;
import com.kohler.entity.CategorySKUAttrValuesMetadataEntity;
import com.kohler.entity.CategorySearchKeywordEntity;
import com.kohler.entity.CategorySearchKeywordMetadataEntity;
import com.kohler.entity.ContentFieldValues;
import com.kohler.entity.LocaleEntity;
import com.kohler.entity.MasterDataCodeConstant;
import com.kohler.entity.TemplateEntity;
import com.kohler.entity.extend.CategoryAccessoryMetadataPojo;
import com.kohler.entity.extend.CategoryComAttrMetadataPojo;
import com.kohler.entity.extend.CategoryComAttrPojo;
import com.kohler.entity.extend.CategoryMetadataPojo;
import com.kohler.entity.extend.CategoryPojo;
import com.kohler.entity.extend.CategorySKUAttrMetadataPojo;
import com.kohler.entity.extend.CategorySKUAttrValuesPojo;
import com.kohler.entity.extend.CategorySearchKeywordMetadataPojo;
import com.kohler.service.CategoryService;

/**
 * 
 * Class Function Description
 * 
 * @author XHY
 * @Date 2014年11月11日
 */
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	public CategoryDao categoryDao;

	@Autowired
	public SysLogDao sysLogDao;

	@Autowired
	public CategoryMetadataDao categoryMetadataDao;

	@Autowired
	public ProductMetadataDao productMetadataDao;

	@Autowired
	public ProductDao productDao;

	@Autowired
	public TemplateDao templateDao;

	@Autowired
	public CategoryComAttrDao categoryComAttrDao;

	@Autowired
	public CategoryComAttrMetadataDao categoryComAttrMetadataDao;

	@Autowired
	public CategorySKUAttrMetadataDao categorySKUAttrMetadataDao;

	@Autowired
	public CategorySKUAttrDao categorySKUAttrDao;

	@Autowired
	public CategorySearchKeywordDao categorySearchKeywordDao;

	@Autowired
	public CategorySearchKeywordMetadataDao categorySearchKeywordMetadataDao;

	@Autowired
	public CategoryAccessoryMetadataDao categoryAccessoryMetadataDao;

	@Autowired
	public CategoryAccessoryDao categoryAccessoryDao;

	@Autowired
	private MasterDataCodeDao masterDao;

	@Autowired
	private CategoryBannerUnitDao categoryBannerUnitDao;

	@Autowired
	public CategoryHeroAreaDao categoryHeroAreaDao;

	@Autowired
	private ContentFieldValuesDao contentFieldValuesDao;

	@Autowired
	private CategorySKUAttrValuesMetadataDao categorySKUAttrValuesMetadataDao;

	@Autowired
	private CategorySKUAttrValuesDao categorySKUAttrValuesDao;

	@Override
	public List<Map<String, Object>> getCategoryWithMap() {
		return categoryDao.selectCategorys();
	}

	@Override
	public Pager<Map<String, Object>> getCategoryPageById(
			Pager<Map<String, Object>> pager, Integer categoryId) {
		List<Object> params = new ArrayList<Object>();
		int pageCount = 0;
		int startRow = (pager.getCurrentPage() - 1) * pager.getPageSize();

		params.add(categoryId);
		int totalCount = categoryDao.selectCountByCondition(
				CategorySQL.SELECT_CATEGORY_PAGE_COUNT_BY_ID, params);

		if (totalCount % pager.getPageSize() == 0) {
			pageCount = totalCount / pager.getPageSize();
		} else {
			pageCount = totalCount / pager.getPageSize() + 1;
		}
		params.add(startRow);
		params.add(pager.getPageSize());
		List<Map<String, Object>> list = categoryDao.selectByConditionWithMap(
				CategorySQL.SELECT_CATEGORY_PAGE_BY_ID, params);

		pager.setStartRow(startRow);
		pager.setDatas(list);
		pager.setTotalRecord(totalCount);
		pager.setTotalPage(pageCount);
		return pager;
	}

	@Override
	@Transactional
	public Integer addCategory(CategoryPojo category) {

		// get update pareCategory
		CategoryMetadataEntity pareCategory = categoryMetadataDao
				.selectById(category.getCategoryMetadataId());

		pareCategory.setLevel(category.getLevel());
		pareCategory.setSeqNo(category.getSeqNo());
		pareCategory.setIsEnable(true);

		Integer row = categoryMetadataDao.update(pareCategory);

		// insert log
		sysLogDao.insertLogForUpdate(pareCategory.getCategoryMetadataId(),
				"CATEGORY_METADATA");

		// insert children data

		CategoryEntity childCategory = new CategoryEntity();
		childCategory.setCategoryMetadataId(pareCategory
				.getCategoryMetadataId());

		Integer[] childIds = new Integer[category.getLangs().length];

		// use name's index as the projo's index
		for (int i = 0; i < category.getLangs().length; i++) {

			childCategory.setCategoryName((String) checkArray(
					category.getCategoryNames(), i));
			childCategory.setBreadcrumbName((String) checkArray(
					category.getBreadcrumbNames(), i));
			childCategory.setLang((Integer) checkArray(category.getLangs(), i));
			childCategory.setPcTemplateId((Integer) checkArray(
					category.getPcTemplateIds(), i));
			childCategory.setSeoTitlePc((String) checkArray(
					category.getSeoTitlePcs(), i));
			childCategory.setSeoKeywordsPc((String) checkArray(
					category.getSeoKeywordsPcs(), i));
			childCategory.setSeoDescriptionPc((String) checkArray(
					category.getSeoDescriptionPcs(), i));
			childCategory.setSeoH1tagPc((String) checkArray(
					category.getSeoH1tagPcs(), i));

			childCategory.setMobileTemplateId((Integer) checkArray(
					category.getMobileTemplateIds(), i));
			childCategory.setMobileTemplateId((Integer) checkArray(
					category.getMobileTemplateIds(), i));
			childCategory.setSeoTitleMobile((String) checkArray(
					category.getSeoTitleMobiles(), i));
			childCategory.setSeoKeywordsMobile((String) checkArray(
					category.getSeoKeywordsMobiles(), i));
			childCategory.setSeoDescriptionMobile((String) checkArray(
					category.getSeoDescriptionMobiles(), i));
			childCategory.setSeoH1tagMobile((String) checkArray(
					category.getSeoH1tagMobiles(), i));

			childCategory.setIsEnable(true);

			Integer childCategoryId = categoryDao.insert(childCategory);

			childIds[i] = childCategoryId;
			// insert log
			sysLogDao.insertLogForInsert(childCategoryId, "CATEGORY");

			// heroAarea
			String heroAreaMetadataIds = (String) checkArray(
					category.getHeroAreaMetadataIds(), i);
			if (!"".equals(heroAreaMetadataIds)) {
				if (heroAreaMetadataIds.contains(",")) {
					String[] heroAreaIds = heroAreaMetadataIds.split(",");
					for (String heroAreaIdStr : heroAreaIds) {
						CategoryHeroAreaEntity heroAreaFor = new CategoryHeroAreaEntity();
						Integer heroAreaId = Integer.parseInt(heroAreaIdStr);
						heroAreaFor.setHeroAreaMetadataId(heroAreaId);
						heroAreaFor.setCategoryMetadataId(pareCategory
								.getCategoryMetadataId());
						heroAreaFor.setLang((Integer) checkArray(
								category.getLangs(), i));
						int categoryHeroAreaIdFor = categoryHeroAreaDao
								.insert(heroAreaFor);
						sysLogDao.insertLogForInsert(categoryHeroAreaIdFor,
								"CATEGORY_HERO_AREA");
					}
				} else {
					Integer heroAreaMetadataId = Integer
							.parseInt(heroAreaMetadataIds);
					CategoryHeroAreaEntity heroArea = new CategoryHeroAreaEntity();
					if (heroAreaMetadataId != null) {

						heroArea.setLang((Integer) checkArray(
								category.getLangs(), i));
						heroArea.setHeroAreaMetadataId(heroAreaMetadataId);
						heroArea.setCategoryMetadataId(pareCategory
								.getCategoryMetadataId());
						int categoryHeroAreaId = categoryHeroAreaDao
								.insert(heroArea);
						sysLogDao.insertLogForInsert(categoryHeroAreaId,
								"CATEGORY_HERO_AREA");

					}
				}

			}

		}

		return row;
	}

	@Override
	public CategoryEntity getCategoryById(Integer categoryId) {
		return categoryDao.selectById(categoryId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CategoryEntity getCategoryByCategoryMetadataId(
			Integer categoryMetadataId) {
		return categoryDao.getCategoryByCategoryMetadataId(categoryMetadataId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean checkCategoryIsTop(Integer categoryId) {

		boolean isTop = false;
		List<Object> params = new ArrayList<Object>();

		params.add(categoryId);
		int totalCount = categoryDao.selectCountByCondition(
				CategorySQL.CHECK_CATEGORY_IS_TOP, params);

		if (totalCount > 0) {
			isTop = true;
		}

		return isTop;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean checkCategoryIsBottom(Integer categoryId) {
		boolean isBottom = false;
		List<Object> params = new ArrayList<Object>();

		params.add(categoryId);
		int totalCount = categoryDao.selectCountByCondition(
				CategorySQL.CHECK_CATEGORY_IS_BOTTOM, params);

		if (totalCount == 0) {
			isBottom = true;
		}

		return isBottom;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Pager<Map<String, Object>> getProductByCateId(
			Pager<Map<String, Object>> pager, Integer categoryId) {

		List<Object> params = new ArrayList<Object>();
		int pageCount = 0;
		int startRow = (pager.getCurrentPage() - 1) * pager.getPageSize();

		params.add(categoryId);
		int totalCount = categoryDao.selectCountByCondition(
				CategorySQL.SELECT_PRODUCT_PAGE_COUNT_BY_CID, params);

		if (totalCount % pager.getPageSize() == 0) {
			pageCount = totalCount / pager.getPageSize();
		} else {
			pageCount = totalCount / pager.getPageSize() + 1;
		}
		params.add(startRow);
		params.add(pager.getPageSize());
		List<Map<String, Object>> list = categoryDao.selectByConditionWithMap(
				CategorySQL.SELECT_PRODUCT_PAGE_BY_CID, params);

		pager.setStartRow(startRow);
		pager.setDatas(list);
		pager.setTotalRecord(totalCount);
		pager.setTotalPage(pageCount);
		return pager;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CategoryMetadataPojo findById(Integer categoryMetadataId) {
		return categoryDao.findCategoryMetadataById(categoryMetadataId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CategoryMetadataEntity getCategoryMetadataById(
			Integer categoryMetadataId) {

		return categoryMetadataDao.selectById(categoryMetadataId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CategoryEntity> getCategorysByCategoryMetadataId(
			Integer categoryMetadataId) {

		return categoryDao.getCategorysByCategoryMetadataId(categoryMetadataId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean editCatalog(CategoryPojo category) {

		boolean isSuccess = false;

		// get update parentData
		CategoryMetadataEntity pareCatalog = categoryMetadataDao
				.selectById(category.getCategoryMetadataId());

		// pareCatalog.setLevel(category.getLevel()); level can not be update
		pareCatalog.setSeqNo(category.getSeqNo());

		Integer row = categoryMetadataDao.update(pareCatalog);

		// insert log
		sysLogDao.insertLogForUpdate(category.getParentId(),
				"CATEGORY_METADATA");

		Integer[] childIds = new Integer[category.getCategoryIds().length];

		// use name's index as the projo's index
		for (int i = 0; i < category.getCategoryIds().length; i++) {
			// get update data
			CategoryEntity childCategory = categoryDao.selectById(category
					.getCategoryIds()[i]);

			childCategory.setCategoryName((String) checkArray(
					category.getCategoryNames(), i));
			childCategory.setBreadcrumbName((String) checkArray(
					category.getBreadcrumbNames(), i));

			// childCategory.setLang(category.getLangs()[i]); update is not use
			// language
			childCategory.setPcTemplateId((Integer) checkArray(
					category.getPcTemplateIds(), i));
			childCategory.setSeoTitlePc((String) checkArray(
					category.getSeoTitlePcs(), i));
			childCategory.setSeoKeywordsPc((String) checkArray(
					category.getSeoKeywordsPcs(), i));
			childCategory.setSeoDescriptionPc((String) checkArray(
					category.getSeoDescriptionPcs(), i));
			childCategory.setSeoH1tagPc((String) checkArray(
					category.getSeoH1tagPcs(), i));

			childCategory.setMobileTemplateId((Integer) checkArray(
					category.getMobileTemplateIds(), i));
			childCategory.setMobileTemplateId((Integer) checkArray(
					category.getMobileTemplateIds(), i));
			childCategory.setSeoTitleMobile((String) checkArray(
					category.getSeoTitleMobiles(), i));
			childCategory.setSeoKeywordsMobile((String) checkArray(
					category.getSeoKeywordsMobiles(), i));
			childCategory.setSeoDescriptionMobile((String) checkArray(
					category.getSeoDescriptionMobiles(), i));
			childCategory.setSeoH1tagMobile((String) checkArray(
					category.getSeoH1tagMobiles(), i));

			Integer childCategoryId = categoryDao.update(childCategory);

			childIds[i] = childCategoryId;
			// insert log
			sysLogDao.insertLogForUpdate(childCategory.getCategoryId(),
					"CATEGORY");

			// hero area
			String heroAreaMetadataIds = (String) checkArray(
					category.getHeroAreaMetadataIds(), i);
			if (!"".equals(heroAreaMetadataIds)) {

				CategoryHeroAreaEntity searchHero = new CategoryHeroAreaEntity();
				searchHero.setCategoryMetadataId(category
						.getCategoryMetadataId());
				searchHero.setIsEnable(true);
				List<CategoryHeroAreaEntity> heroList = categoryHeroAreaDao
						.selectByCondition(searchHero);
				for (CategoryHeroAreaEntity delHero : heroList) {
					categoryHeroAreaDao.delete(delHero);
					sysLogDao.insertLogForDelete(
							delHero.getCategoryHeroAreaId(),
							"CATEGORY_HERO_AREA");
				}
				if (heroAreaMetadataIds.contains(",")) {
					String[] heroAreaIds = heroAreaMetadataIds.split(",");
					for (String heroAreaIdStr : heroAreaIds) {
						CategoryHeroAreaEntity heroAreaFor = new CategoryHeroAreaEntity();
						Integer heroAreaId = Integer.parseInt(heroAreaIdStr);
						heroAreaFor.setHeroAreaMetadataId(heroAreaId);
						heroAreaFor.setCategoryMetadataId(category
								.getCategoryMetadataId());
						heroAreaFor.setLang(childCategory.getLang());
						int categoryHeroAreaIdFor = categoryHeroAreaDao
								.insert(heroAreaFor);
						sysLogDao.insertLogForInsert(categoryHeroAreaIdFor,
								"CATEGORY_HERO_AREA");
					}
				} else {
					Integer heroAreaMetadataId = Integer
							.parseInt(heroAreaMetadataIds);
					CategoryHeroAreaEntity heroArea = new CategoryHeroAreaEntity();
					if (heroAreaMetadataId != null) {

						heroArea.setLang(childCategory.getLang());
						heroArea.setHeroAreaMetadataId(heroAreaMetadataId);
						heroArea.setCategoryMetadataId(category
								.getCategoryMetadataId());
						int categoryHeroAreaId = categoryHeroAreaDao
								.insert(heroArea);
						sysLogDao.insertLogForInsert(categoryHeroAreaId,
								"CATEGORY_HERO_AREA");

					}
				}
			}
		}

		if (row != null) {
			isSuccess = true;
		}
		return isSuccess;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CategoryPojo> getCategoryPojoById(
			CategoryMetadataEntity parentCategory, List<LocaleEntity> languages) {

		List<CategoryEntity> categorys = categoryDao
				.getCategorysByCategoryMetadataId(parentCategory
						.getCategoryMetadataId());

		CategoryHeroAreaEntity searchHero = new CategoryHeroAreaEntity();
		searchHero
				.setCategoryMetadataId(parentCategory.getCategoryMetadataId());
		searchHero.setIsEnable(true);

		List<CategoryHeroAreaEntity> heroAreas = categoryHeroAreaDao
				.selectByCondition(searchHero);

		List<Map<String, Object>> fieldNames = getFiledName(
				MasterDataCodeConstant.CONTENT_DISPLAY_ATTR_NAME_HERO_AREA, 1);
		String filedName = "";
		for (Map<String, Object> field : fieldNames) {
			if (MasterDataCodeConstant.CONTENT_DISPLAY_ATTR_NAME_HERO_AREA == Integer
					.parseInt(field.get("masterdataId").toString())) {
				filedName = field.get("masterdataName").toString();
			}
		}

		List<CategoryPojo> categoryPojos = new ArrayList<CategoryPojo>();

		for (CategoryEntity category : categorys) {

			CategoryPojo catePojo = new CategoryPojo();
			catePojo.setCategory(category);
			categoryPojos.add(catePojo);
		}

		// locale set
		for (CategoryPojo pojo : categoryPojos) {
			for (LocaleEntity language : languages) {
				if (pojo.getCategory().getLang() != null
						&& pojo.getCategory().getLang().intValue() == (language
								.getLocaleId().intValue())) {
					pojo.setLanguage(language);
				}
			}

			// HeroArea
			String heroAreaFieldValue = "";
			String heroAreaFieldValueId = "";
			for (CategoryHeroAreaEntity heroArea : heroAreas) {
				if (pojo.getCategory().getLang() != null
						&& pojo.getCategory().getLang().intValue() == (heroArea
								.getLang().intValue())) {
					pojo.setHeroArea(heroArea);
					if (!"".equals(filedName)) {
						ContentFieldValues searchFiled = new ContentFieldValues();
						searchFiled.setFieldName(filedName);
						searchFiled.setContentMetadataId(heroArea
								.getHeroAreaMetadataId());
						List<ContentFieldValues> fileds = contentFieldValuesDao
								.selectByCondition(searchFiled);
						if (fileds != null && fileds.size() > 0) {
							heroAreaFieldValue += fileds.get(0).getFieldValue()
									+ ",";
							heroAreaFieldValueId += fileds.get(0).getContentMetadataId()
									+ ",";
						}
					}
				}

			}
			if (!"".equals(heroAreaFieldValue)) {
				heroAreaFieldValue = heroAreaFieldValue.substring(0,
						heroAreaFieldValue.length() - 1);
				heroAreaFieldValueId = heroAreaFieldValueId.substring(0,
						heroAreaFieldValueId.length() - 1);
			}
			pojo.setFieldValue(heroAreaFieldValue);
			pojo.setFieldValueId(heroAreaFieldValueId);

		}
		return categoryPojos;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer insertCategoryMetadata(
			CategoryMetadataEntity pareCate) {

		CategoryMetadataEntity categoryMetadata = new CategoryMetadataEntity();
		categoryMetadata.setParentId(pareCate.getCategoryMetadataId());
		categoryMetadata.setIsEnable(false);
		Integer categoryMetadataId = categoryMetadataDao
				.insert(categoryMetadata);
//		CategoryMetadataEntity inseModel = categoryMetadataDao
//				.selectById(categoryMetadataId);
		sysLogDao.insertLogForInsert(categoryMetadataId, "CATEGORY_METADATA");

//		return inseModel;
		return categoryMetadataId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int checkCategoryName(String categoryName, Integer lang,
			Integer categoryMetadataId) {
		Integer count = 0;
		List<Object> params = new ArrayList<Object>();
		params.add(categoryName);
		params.add(lang);
		params.add(categoryMetadataId);
		if (!"".equals(categoryName)) {
			count = categoryDao.selectCountByCondition(
					CategorySQL.CHECK_CATALOG_COUNT, params);
		}
		return count;
	}

	private Object checkArray(Object[] o, int i) {
		Object value = new Object();
		if (o != null && i < o.length) {
			value = o[i];
		} else {
			value = null;
		}
		return value;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int checkCategoryNameOutSelf(String categoryName, Integer lang,
			Integer categoryMetadataId, Integer categoryId) {
		Integer count = 0;
		List<Object> params = new ArrayList<Object>();
		params.add(categoryName);
		params.add(lang);
		params.add(categoryMetadataId);
		params.add(categoryId);
		if (!"".equals(categoryName)) {
			count = categoryDao.selectCountByCondition(
					CategorySQL.CHECK_CATALOG_COUNT_OUT, params);
		}
		return count;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TemplateEntity> getTemplateListByType(Integer templateType) {
		TemplateEntity template = new TemplateEntity();
		template.setIsEnable(true);
		template.setTemplateType(templateType);
		return templateDao.selectByCondition(template);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer addCategoryComAttr(CategoryComAttrPojo categoryComAttr) {
		// get insert pareCategory
		CategoryComAttrMetadataEntity pareCategoryComAttr = new CategoryComAttrMetadataEntity();

		pareCategoryComAttr.setCategoryMetadataId(categoryComAttr
				.getCategoryMetadataId());
		pareCategoryComAttr.setSeqNo(categoryComAttr.getSeqNo());
		pareCategoryComAttr.setKeyName(categoryComAttr.getKeyName());
		pareCategoryComAttr.setInputType(categoryComAttr.getInputType());
		pareCategoryComAttr.setIsFilter(categoryComAttr.getIsFilter());
		pareCategoryComAttr.setDropdownTypeCode(categoryComAttr
				.getDropdownTypeCode());
		Integer categoryComAttrMetadataId = categoryComAttrMetadataDao
				.insert(pareCategoryComAttr);

		// insert log
		sysLogDao.insertLogForInsert(categoryComAttrMetadataId,
				"CATEGORY_COM_ATTR_METADATA");

		// insert children data

		CategoryComAttrEntity childCategory = new CategoryComAttrEntity();
		childCategory.setCategoryComAttrMetadataId(categoryComAttrMetadataId);

		Integer[] childIds = new Integer[categoryComAttr.getLangs().length];

		for (int i = 0; i < categoryComAttr.getLangs().length; i++) {

			childCategory.setLang((Integer) checkArray(
					categoryComAttr.getLangs(), i));
			;
			childCategory.setCategoryComAttrName((String) checkArray(
					categoryComAttr.getCategoryComAttrNames(), i));
			childCategory.setImageUrl((String) checkArray(
					categoryComAttr.getImageUrls(), i));
			;
			childCategory.setImageSource((Integer) checkArray(
					categoryComAttr.getImageSources(), i));
			childCategory.setImageId((Integer) checkArray(
					categoryComAttr.getImageIds(), i));
			childCategory.setImageTooltip((String) checkArray(
					categoryComAttr.getImageTooltips(), i));

			childCategory.setIsEnable(true);

			Integer childCategoryComAttrId = categoryComAttrDao
					.insert(childCategory);

			childIds[i] = childCategoryComAttrId;
			sysLogDao.insertLogForInsert(childCategoryComAttrId,
					"CATEGORY_COM_ATTR");
		}

		return categoryComAttrMetadataId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CategoryComAttrMetadataPojo> getCateComAttrByCId(
			Integer categoryMetadataId) {

		return categoryComAttrMetadataDao
				.getCateComAttrByCId(categoryMetadataId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CategoryComAttrMetadataPojo getCateComAttrById(
			Integer categoryComAttrMetadataId) {

		return categoryComAttrMetadataDao
				.getCateComAttrById(categoryComAttrMetadataId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteCateComAttr(Integer categoryComAttrMetadataId) {

		// first delete detail =>then delete master;
		CategoryComAttrEntity searchEntity = new CategoryComAttrEntity();
		searchEntity.setCategoryComAttrMetadataId(categoryComAttrMetadataId);
		searchEntity.setIsEnable(true);
		List<CategoryComAttrEntity> comAttrs = categoryComAttrDao
				.selectByCondition(searchEntity);

		for (CategoryComAttrEntity comAttr : comAttrs) {

			categoryComAttrDao.delete(comAttr);
			sysLogDao.insertLogForDelete(comAttr.getCategoryComAttrId(),
					"CATEGORY_COM_ATTR");
		}

		CategoryComAttrMetadataEntity comAttrMetadata = categoryComAttrMetadataDao
				.selectById(categoryComAttrMetadataId);
		categoryComAttrMetadataDao.delete(comAttrMetadata);
		// insert log
		sysLogDao.insertLogForUpdate(categoryComAttrMetadataId,
				"CATEGORY_COM_ATTR_METADATA");
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CategoryComAttrPojo> getComAttrByMId(
			Integer categoryComAttrMetadataId, List<LocaleEntity> languages) {

		CategoryComAttrEntity search = new CategoryComAttrEntity();
		search.setCategoryComAttrMetadataId(categoryComAttrMetadataId);
		search.setIsEnable(true);
		List<CategoryComAttrEntity> categoryComAttrs = categoryComAttrDao
				.selectByCondition(search);

		List<CategoryComAttrPojo> categoryComAttrPojos = new ArrayList<CategoryComAttrPojo>();

		for (CategoryComAttrEntity categoryComAttr : categoryComAttrs) {

			CategoryComAttrPojo cateComAttrPojo = new CategoryComAttrPojo();
			cateComAttrPojo.setCategoryComAttr(categoryComAttr);
			categoryComAttrPojos.add(cateComAttrPojo);
		}

		// locale set
		for (CategoryComAttrPojo pojo : categoryComAttrPojos) {
			for (LocaleEntity language : languages) {
				if (pojo.getCategoryComAttr().getLang() != null
						&& pojo.getCategoryComAttr().getLang().intValue() == (language
								.getLocaleId().intValue())) {
					pojo.setLanguage(language);
				}
			}
		}
		return categoryComAttrPojos;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer editCategoryComAttr(CategoryComAttrPojo categoryComAttr) {

		// get update pareCategory
		CategoryComAttrMetadataEntity pareCategoryComAttr = categoryComAttrMetadataDao
				.selectById(categoryComAttr.getCategoryComAttrMetadataId());

		pareCategoryComAttr.setSeqNo(categoryComAttr.getSeqNo());
		pareCategoryComAttr.setKeyName(categoryComAttr.getKeyName());
		pareCategoryComAttr.setInputType(categoryComAttr.getInputType());
		pareCategoryComAttr.setIsFilter(categoryComAttr.getIsFilter());
		pareCategoryComAttr.setDropdownTypeCode(categoryComAttr
				.getDropdownTypeCode());
		Integer updateRow = categoryComAttrMetadataDao
				.update(pareCategoryComAttr);

		// insert log
		sysLogDao.insertLogForUpdate(
				categoryComAttr.getCategoryComAttrMetadataId(),
				"CATEGORY_COM_ATTR_METADATA");

		// update children data

		Integer[] childIds = new Integer[categoryComAttr
				.getCategoryComAttrIds().length];

		for (int i = 0; i < categoryComAttr.getCategoryComAttrIds().length; i++) {
			CategoryComAttrEntity childCategoryComAttr = categoryComAttrDao
					.selectById(categoryComAttr.getCategoryComAttrIds()[i]);

			childCategoryComAttr.setCategoryComAttrName((String) checkArray(
					categoryComAttr.getCategoryComAttrNames(), i));
			childCategoryComAttr.setImageUrl((String) checkArray(
					categoryComAttr.getImageUrls(), i));
			;
			childCategoryComAttr.setImageSource((Integer) checkArray(
					categoryComAttr.getImageSources(), i));
			childCategoryComAttr.setImageId((Integer) checkArray(
					categoryComAttr.getImageIds(), i));
			childCategoryComAttr.setImageTooltip((String) checkArray(
					categoryComAttr.getImageTooltips(), i));

			categoryComAttrDao.update(childCategoryComAttr);

			childIds[i] = categoryComAttr.getCategoryComAttrIds()[i];
			sysLogDao.insertLogForUpdate(
					categoryComAttr.getCategoryComAttrIds()[i],
					"CATEGORY_COM_ATTR");
		}

		return updateRow;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer saveCategorySkuAttrs(
			CategorySKUAttrMetadataEntity categorySKUAttrMetadataEntity,
			List<CategorySKUAttrEntity> skuAttrList,
			List<CategorySKUAttrValuesPojo> attrValuesList, Integer[] imageUrls) {

		int categorySKUAttrMetadataId = 0;
		if (null != categorySKUAttrMetadataEntity && skuAttrList.size()>0) {
			categorySKUAttrMetadataEntity.setIsEnable(true);

			categorySKUAttrMetadataId = categorySKUAttrMetadataDao
					.insert(categorySKUAttrMetadataEntity);
			categorySKUAttrMetadataEntity
					.setCategorySKUAttrMetadataId(categorySKUAttrMetadataId);

			sysLogDao.insertLogForInsert(categorySKUAttrMetadataId,
					"CATEGORY_SKU_ATTR_METADATA");
			if(attrValuesList.size() > 0){
			
				for (int j = 0; j < attrValuesList.get(0).getAttrValues().length; j++) {
					CategorySKUAttrValuesMetadataEntity skuAttrValues = new CategorySKUAttrValuesMetadataEntity();
					skuAttrValues
							.setCategorySkuAttrMetadataId(categorySKUAttrMetadataId);
					skuAttrValues.setImageId((Integer) checkArray(imageUrls, j));
					int metadataId = 0;
					if (skuAttrValues.getImageId() != null) {
						skuAttrValues.setIsEnable(true);
						metadataId = categorySKUAttrValuesMetadataDao
								.insert(skuAttrValues);
	
						sysLogDao.insertLogForInsert(metadataId,
								"CATEGORY_SKU_ATTR_VALUES_METADATA");
					}
					int childId = 0;
					if (metadataId != 0) {
						for (int k = 0; k < attrValuesList.size(); k++) {
							CategorySKUAttrValuesEntity childSkuAttrValues = new CategorySKUAttrValuesEntity();
							childSkuAttrValues
									.setCategorySKUAttrValuesMetadataId(metadataId);
							childSkuAttrValues.setAttrValue(attrValuesList.get(k)
									.getAttrValues()[j]);
							childSkuAttrValues.setLang(attrValuesList.get(k)
									.getLang());
							childSkuAttrValues.setIsEnable(true);
	
							childId = categorySKUAttrValuesDao
									.insert(childSkuAttrValues);
							sysLogDao.insertLogForInsert(childId,
									"CATEGORY_SKU_ATTR_VALUES");
						}
	
					}
	
				}
			
			}

			if (skuAttrList.size() > 0) {
				for (int i = 0; i < skuAttrList.size(); i++) {
					skuAttrList.get(i).setCategorySKUAttrMetadataId(
							categorySKUAttrMetadataEntity
									.getCategorySKUAttrMetadataId());
					skuAttrList.get(i).setIsEnable(true);
					int skuId = categorySKUAttrDao.insert(skuAttrList.get(i));
					sysLogDao.insertLogForInsert(skuId, "CATEGORY_SKU_ATTR");

				}
			}
		}
		return categorySKUAttrMetadataId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer editCategorySkuAttrs(
			CategorySKUAttrMetadataEntity categorySKUAttrMetadataEntity,
			List<CategorySKUAttrEntity> skuAttrList,
			List<CategorySKUAttrValuesPojo> attrValuesList, Integer[] imageUrls) {

		if (null != categorySKUAttrMetadataEntity && !"".equals(skuAttrList)) {
			categorySKUAttrMetadataEntity.setIsEnable(true);
			categorySKUAttrMetadataDao.update(categorySKUAttrMetadataEntity);

			sysLogDao.insertLogForUpdate(categorySKUAttrMetadataEntity
					.getCategorySKUAttrMetadataId(),
					"CATEGORY_SKU_ATTR_METADATA");

			// first delete attrValues
			CategorySKUAttrValuesMetadataEntity delAttrValuesMeta = new CategorySKUAttrValuesMetadataEntity();
			delAttrValuesMeta
					.setCategorySkuAttrMetadataId(categorySKUAttrMetadataEntity
							.getCategorySKUAttrMetadataId());
			delAttrValuesMeta.setIsEnable(true);
			List<CategorySKUAttrValuesMetadataEntity> delAttrValuesMetas = categorySKUAttrValuesMetadataDao
					.selectByCondition(delAttrValuesMeta);

			for (CategorySKUAttrValuesMetadataEntity attrValMeta : delAttrValuesMetas) {
				CategorySKUAttrValuesEntity delAttrValDetail = new CategorySKUAttrValuesEntity();
				delAttrValDetail.setCategorySKUAttrValuesMetadataId(attrValMeta
						.getCategorySKUAttrValuesMetadataId());
				delAttrValDetail.setIsEnable(true);
				List<CategorySKUAttrValuesEntity> delAttrValDetails = categorySKUAttrValuesDao
						.selectByCondition(delAttrValDetail);
				for (CategorySKUAttrValuesEntity attrVal : delAttrValDetails) {
					categorySKUAttrValuesDao.delete(attrVal);
					sysLogDao.insertLogForDelete(
							attrVal.getCategorySKUAttrValuesId(),
							"CATEGORY_SKU_ATTR_VALUES");
				}
				categorySKUAttrValuesMetadataDao.delete(attrValMeta);
				sysLogDao.insertLogForDelete(
						attrValMeta.getCategorySKUAttrValuesMetadataId(),
						"CATEGORY_SKU_ATTR_VALUES_METADATA");
			}

			// save attrValues
			for (int j = 0; j < attrValuesList.get(0).getAttrValues().length; j++) {
				CategorySKUAttrValuesMetadataEntity skuAttrValues = new CategorySKUAttrValuesMetadataEntity();
				skuAttrValues
						.setCategorySkuAttrMetadataId(categorySKUAttrMetadataEntity
								.getCategorySKUAttrMetadataId());
				skuAttrValues.setImageId((Integer) checkArray(imageUrls, j));
				int metadataId = 0;
				if (skuAttrValues.getImageId() != null) {
					skuAttrValues.setIsEnable(true);
					metadataId = categorySKUAttrValuesMetadataDao
							.insert(skuAttrValues);

					sysLogDao.insertLogForInsert(metadataId,
							"CATEGORY_SKU_ATTR_VALUES_METADATA");
				}
				int childId = 0;
				if (metadataId != 0) {
					for (int k = 0; k < attrValuesList.size(); k++) {
						CategorySKUAttrValuesEntity childSkuAttrValues = new CategorySKUAttrValuesEntity();
						childSkuAttrValues
								.setCategorySKUAttrValuesMetadataId(metadataId);
						childSkuAttrValues.setAttrValue(attrValuesList.get(k)
								.getAttrValues()[j]);
						childSkuAttrValues.setLang(attrValuesList.get(k)
								.getLang());
						childSkuAttrValues.setIsEnable(true);

						childId = categorySKUAttrValuesDao
								.insert(childSkuAttrValues);
						sysLogDao.insertLogForInsert(childId,
								"CATEGORY_SKU_ATTR_VALUES");
					}

				}

			}
			// save attrValues ^^

			if (skuAttrList.size() > 0) {
				for (CategorySKUAttrEntity sku : skuAttrList) {
					sku.setIsEnable(true);
					categorySKUAttrDao.update(sku);

					sysLogDao.insertLogForInsert(sku.getCategorySKUAttrId(),
							"CATEGORY_SKU_ATTR");
				}
			}
		}

		return categorySKUAttrMetadataEntity.getCategorySKUAttrMetadataId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CategorySKUAttrMetadataPojo getSkuAttrById(
			Integer categorySKUAttrMetadataId) {

		return categorySKUAttrMetadataDao
				.getSkuAttrById(categorySKUAttrMetadataId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CategorySKUAttrEntity> getSKuAttrList(
			CategorySKUAttrEntity skuAttr) {

		return categorySKUAttrDao.selectByCondition(skuAttr);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer saveCategorySearchKeys(
			CategorySearchKeywordMetadataEntity categorySearchKeywordMetadata,
			List<CategorySearchKeywordEntity> searchKeywords) {

		int categorySearchKeyMetadataId = 0;
		if (null != categorySearchKeywordMetadata && !"".equals(searchKeywords)) {
			categorySearchKeywordMetadata.setIsEnable(true);

			categorySearchKeyMetadataId = categorySearchKeywordMetadataDao
					.insert(categorySearchKeywordMetadata);
			categorySearchKeywordMetadata
					.setCategorySearchKeywordMetadataId(categorySearchKeyMetadataId);

			sysLogDao.insertLogForInsert(categorySearchKeyMetadataId,
					"CATEGORY_SEARCH_KEYWORD_METADATA");

			if (searchKeywords.size() > 0) {
				for (CategorySearchKeywordEntity searchKey : searchKeywords) {
					searchKey
							.setCategorySearchKeywordMetadataId(categorySearchKeywordMetadata
									.getCategorySearchKeywordMetadataId());
					searchKey.setIsEnable(true);
					int searchId = categorySearchKeywordDao.insert(searchKey);

					sysLogDao.insertLogForInsert(searchId,
							"CATEGORY_SEARCH_KEYWORD");
				}
			}
		}
		return categorySearchKeyMetadataId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CategorySearchKeywordMetadataPojo getSearchKeyById(
			Integer categorySearchKeyMetadataId) {

		return categorySearchKeywordMetadataDao
				.getSkuAttrById(categorySearchKeyMetadataId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CategorySearchKeywordEntity> getSearchKeyList(
			CategorySearchKeywordEntity searchKeyword) {

		return categorySearchKeywordDao.selectByCondition(searchKeyword);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer editCategorySearchKey(
			CategorySearchKeywordMetadataEntity categorySearchKeywordMetadata,
			List<CategorySearchKeywordEntity> searchKeywords) {
		if (null != categorySearchKeywordMetadata && !"".equals(searchKeywords)) {

			categorySearchKeywordMetadata.setIsEnable(true);

			categorySearchKeywordMetadataDao
					.update(categorySearchKeywordMetadata);

			sysLogDao.insertLogForUpdate(categorySearchKeywordMetadata
					.getCategorySearchKeywordMetadataId(),
					"CATEGORY_SEARCH_KEYWORD_METADATA");

			if (searchKeywords.size() > 0) {
				for (CategorySearchKeywordEntity searchKey : searchKeywords) {
					searchKey.setIsEnable(true);
					categorySearchKeywordDao.update(searchKey);

					sysLogDao.insertLogForInsert(
							searchKey.getCategorySearchKeywordId(),
							"CATEGORY_SEARCH_KEYWORD");
				}
			}
		}
		return categorySearchKeywordMetadata
				.getCategorySearchKeywordMetadataId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteCateSkuAttr(Integer categorySKUAttrMetadataId) {
		// first delete detail =>then delete master;
		CategorySKUAttrEntity searchEntity = new CategorySKUAttrEntity();
		searchEntity.setCategorySKUAttrMetadataId(categorySKUAttrMetadataId);
		searchEntity.setIsEnable(true);
		List<CategorySKUAttrEntity> skuAttrs = categorySKUAttrDao
				.selectByCondition(searchEntity);

		for (CategorySKUAttrEntity comAttr : skuAttrs) {

			categorySKUAttrDao.delete(comAttr);
			sysLogDao.insertLogForDelete(comAttr.getCategorySKUAttrId(),
					"CATEGORY_SKU_ATTR");
		}

		CategorySKUAttrMetadataEntity skuAttrMetadata = categorySKUAttrMetadataDao
				.selectById(categorySKUAttrMetadataId);
		categorySKUAttrMetadataDao.delete(skuAttrMetadata);
		// insert log
		sysLogDao.insertLogForUpdate(categorySKUAttrMetadataId,
				"CATEGORY_SKU_ATTR_METADATA");
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteCateSearchKey(Integer categorySearchKeywordMetadataId) {
		// first delete detail =>then delete master;
		CategorySearchKeywordEntity searchEntity = new CategorySearchKeywordEntity();
		searchEntity
				.setCategorySearchKeywordMetadataId(categorySearchKeywordMetadataId);
		searchEntity.setIsEnable(true);
		List<CategorySearchKeywordEntity> searchKeys = categorySearchKeywordDao
				.selectByCondition(searchEntity);

		for (CategorySearchKeywordEntity searchKey : searchKeys) {

			categorySearchKeywordDao.delete(searchKey);
			sysLogDao.insertLogForDelete(
					searchKey.getCategorySearchKeywordId(),
					"CATEGORY_SEARCH_KEYWORD");
		}

		CategorySearchKeywordMetadataEntity searchKey = categorySearchKeywordMetadataDao
				.selectById(categorySearchKeywordMetadataId);
		categorySearchKeywordMetadataDao.delete(searchKey);
		// insert log
		sysLogDao.insertLogForUpdate(categorySearchKeywordMetadataId,
				"CATEGORY_SEARCH_KEYWORD_METADATA");
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer saveAccessors(
			CategoryAccessoryMetadataEntity categoryAccessoryMetadata,
			List<CategoryAccessoryEntity> accessories) {
		int categoryAccessoryMetadataId = 0;
		if (null != categoryAccessoryMetadata && !"".equals(accessories)) {
			categoryAccessoryMetadata.setIsEnable(true);

			categoryAccessoryMetadataId = categoryAccessoryMetadataDao
					.insert(categoryAccessoryMetadata);
			categoryAccessoryMetadata
					.setCategoryAccessoryMetadataId(categoryAccessoryMetadataId);

			sysLogDao.insertLogForInsert(categoryAccessoryMetadataId,
					"CATEGORY_ACCESSORY_METADATA");

			if (accessories.size() > 0) {
				for (CategoryAccessoryEntity accessory : accessories) {
					accessory
							.setCategoryAccessoryMetadataId(categoryAccessoryMetadata
									.getCategoryAccessoryMetadataId());
					accessory.setIsEnable(true);
					int searchId = categoryAccessoryDao.insert(accessory);

					sysLogDao
							.insertLogForInsert(searchId, "CATEGORY_ACCESSORY");
				}
			}
		}
		return categoryAccessoryMetadataId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CategoryAccessoryMetadataPojo getAccessoryById(
			Integer categoryAccessoryMetadataId) {

		return categoryAccessoryMetadataDao
				.getAccessoryPojoById(categoryAccessoryMetadataId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Map<String, Object>> getAllType(Integer type, Integer lang) {

		List<Object> MasterData = new ArrayList<Object>();
		MasterData.add(type);// 所需数据常量
		MasterData.add(lang);// 所需数据语言
		return masterDao.getAllTypeByMasterData(MasterData);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CategoryAccessoryEntity> getAccessoryList(
			CategoryAccessoryEntity accessory) {

		return categoryAccessoryDao.selectByCondition(accessory);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer editAccessorys(
			CategoryAccessoryMetadataEntity categoryAccessoryMetadata,
			List<CategoryAccessoryEntity> accessorys) {

		if (null != categoryAccessoryMetadata && !"".equals(accessorys)) {

			categoryAccessoryMetadata.setIsEnable(true);

			categoryAccessoryMetadataDao.update(categoryAccessoryMetadata);

			sysLogDao.insertLogForUpdate(
					categoryAccessoryMetadata.getCategoryAccessoryMetadataId(),
					"CATEGORY_ACCESSORY_METADATA");

			if (accessorys.size() > 0) {
				for (CategoryAccessoryEntity access : accessorys) {
					access.setIsEnable(true);
					categoryAccessoryDao.update(access);

					sysLogDao.insertLogForInsert(
							access.getCategoryAccessoryId(),
							"CATEGORY_ACCESSORY");
				}
			}
		}
		return categoryAccessoryMetadata.getCategoryAccessoryMetadataId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteCateAccessory(Integer categoryAccessoryMetadataId) {
		// first delete detail =>then delete master;
		CategoryAccessoryEntity searchEntity = new CategoryAccessoryEntity();
		searchEntity
				.setCategoryAccessoryMetadataId(categoryAccessoryMetadataId);
		searchEntity.setIsEnable(true);
		List<CategoryAccessoryEntity> searchAccessorys = categoryAccessoryDao
				.selectByCondition(searchEntity);

		for (CategoryAccessoryEntity searchKey : searchAccessorys) {

			categoryAccessoryDao.delete(searchKey);
			sysLogDao.insertLogForDelete(searchKey.getCategoryAccessoryId(),
					"CATEGORY_ACCESSORY");
		}

		CategoryAccessoryMetadataEntity searchAccessoryM = categoryAccessoryMetadataDao
				.selectById(categoryAccessoryMetadataId);
		categoryAccessoryMetadataDao.delete(searchAccessoryM);
		// insert log
		sysLogDao.insertLogForUpdate(categoryAccessoryMetadataId,
				"CATEGORY_ACCESSORY_METADATA");
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer saveBannerUnit(CategoryBannerUnitEntity categoryBannerUnit) {
		int categoryBannerUnitId = 0;
		if (null != categoryBannerUnit) {
			categoryBannerUnit.setIsEnable(true);

			categoryBannerUnitId = categoryBannerUnitDao
					.insert(categoryBannerUnit);
			categoryBannerUnit.setCategoryBannerUnitId(categoryBannerUnitId);

			sysLogDao.insertLogForInsert(categoryBannerUnitId,
					"CATEGORY_BANNER_UNIT");
		}
		return categoryBannerUnitId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CategoryBannerUnitEntity getBannerById(Integer bannerUnitId) {

		return categoryBannerUnitDao.selectById(bannerUnitId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CategoryBannerUnitEntity getCategoryBannerById(
			Integer bannerUnitMetadataId) {
		CategoryBannerUnitEntity bannerUnit = categoryBannerUnitDao
				.selectById(bannerUnitMetadataId);

		List<Map<String, Object>> fieldNames = getFiledName(
				MasterDataCodeConstant.CONTENT_DISPLAY_ATTR_NAME_BANNER_UNIT, 1);
		String filedName = "";
		for (Map<String, Object> field : fieldNames) {
			if (MasterDataCodeConstant.CONTENT_DISPLAY_ATTR_NAME_BANNER_UNIT == Integer
					.parseInt(field.get("masterdataId").toString())) {
				filedName = field.get("masterdataName").toString();
			}
		}
		if (!"".equals(filedName)) {
			ContentFieldValues searchFiled = new ContentFieldValues();
			searchFiled.setFieldName(filedName);
			searchFiled.setContentMetadataId(bannerUnit
					.getBannerUnitMetadataId());
			List<ContentFieldValues> fileds = contentFieldValuesDao
					.selectByCondition(searchFiled);
			if (fileds != null && fileds.size() > 0) {
				bannerUnit.setBannerUnitFiledValue(fileds.get(0)
						.getFieldValue());
			}
		}

		return bannerUnit;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer editBannerUnit(CategoryBannerUnitEntity categoryBannerUnit) {

		categoryBannerUnit.setIsEnable(true);

		categoryBannerUnitDao.update(categoryBannerUnit);

		sysLogDao.insertLogForUpdate(
				categoryBannerUnit.getCategoryBannerUnitId(),
				"CATEGORY_BANNER_UNIT");

		return categoryBannerUnit.getCategoryBannerUnitId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CategorySKUAttrMetadataPojo> getCateSkuAttrByCId(
			Integer categoryMetadataId) {

		return categorySKUAttrMetadataDao
				.getCateSkuAttrByCId(categoryMetadataId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CategorySearchKeywordMetadataPojo> getCateSearchKeyByCId(
			Integer categoryMetadataId) {

		return categorySearchKeywordMetadataDao
				.getCateSearchKeyByCId(categoryMetadataId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CategoryAccessoryMetadataPojo> getCateAccessoryByCId(
			Integer categoryMetadataId) {

		return categoryAccessoryMetadataDao
				.getCateAccessoryByCId(categoryMetadataId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getDropDownType() {

		return categoryComAttrMetadataDao.getDropDownType();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Map<String, Object>> getFiledName(Integer type, Integer lang) {

		List<Object> MasterData = new ArrayList<Object>();
		MasterData.add(type);// 所需数据常量
		MasterData.add(lang);// 所需数据语言
		return categoryMetadataDao.getFiledName(MasterData);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteBannerUnitById(Integer categoryBannerUnitId) {
		CategoryBannerUnitEntity bannerUnit = categoryBannerUnitDao
				.selectById(categoryBannerUnitId);
		int row = categoryBannerUnitDao.delete(bannerUnit);
		boolean result = false;
		if (row > 0) {
			result = true;
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CategoryBannerUnitEntity> getBannerUnitListByCon(
			Integer categoryMetadataId, Integer lang) {
		CategoryBannerUnitEntity searchBannerUnit = new CategoryBannerUnitEntity();
		searchBannerUnit.setCatalogMetadataId(null);
		searchBannerUnit.setCategoryMetadataId(categoryMetadataId);
		searchBannerUnit.setLang(lang);

		List<CategoryBannerUnitEntity> bannerUnitList = categoryBannerUnitDao
				.selectByCondition(searchBannerUnit);

		List<Map<String, Object>> fieldNames = getFiledName(
				MasterDataCodeConstant.CONTENT_DISPLAY_ATTR_NAME_BANNER_UNIT, 1);
		String filedName = "";
		for (Map<String, Object> field : fieldNames) {
			if (MasterDataCodeConstant.CONTENT_DISPLAY_ATTR_NAME_BANNER_UNIT == Integer
					.parseInt(field.get("masterdataId").toString())) {
				filedName = field.get("masterdataName").toString();
			}
		}
		if (!"".equals(filedName)) {
			for (CategoryBannerUnitEntity bannerUnit : bannerUnitList) {
				if (bannerUnit.getBannerUnitMetadataId() != null) {

					ContentFieldValues searchFiled = new ContentFieldValues();
					searchFiled.setFieldName(filedName);
					searchFiled.setContentMetadataId(bannerUnit
							.getBannerUnitMetadataId());
					List<ContentFieldValues> fileds = contentFieldValuesDao
							.selectByCondition(searchFiled);
					if (fileds != null && fileds.size() > 0) {
						bannerUnit.setBannerUnitFiledValue(fileds.get(0)
								.getFieldValue());
					}
				}

			}
		}

		return bannerUnitList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CategorySKUAttrValuesMetadataEntity> getSKuAttrValuesBySKUId(
			Integer categorySKUAttrMetadataId) {
		CategorySKUAttrValuesMetadataEntity searchEntity = new CategorySKUAttrValuesMetadataEntity();
		searchEntity.setCategorySkuAttrMetadataId(categorySKUAttrMetadataId);
		searchEntity.setIsEnable(true);

		return categorySKUAttrValuesMetadataDao.selectByCondition(searchEntity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CategorySKUAttrValuesEntity> getSkuAttrValueByMetaList(
			List<CategorySKUAttrValuesMetadataEntity> skuAttrValuesMetaList) {

		List<CategorySKUAttrValuesEntity> allList = new ArrayList<CategorySKUAttrValuesEntity>();
		List<CategorySKUAttrValuesEntity> forList = new ArrayList<CategorySKUAttrValuesEntity>();

		for (CategorySKUAttrValuesMetadataEntity meta : skuAttrValuesMetaList) {
			CategorySKUAttrValuesEntity searchEntity = new CategorySKUAttrValuesEntity();
			searchEntity.setCategorySKUAttrValuesMetadataId(meta
					.getCategorySKUAttrValuesMetadataId());
			searchEntity.setIsEnable(true);
			forList = categorySKUAttrValuesDao.selectByCondition(searchEntity);
			allList.addAll(forList);
		}
		return allList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int checkComKeyName(String keyName, Integer categoryMetadataId) {
		Integer count = 0;
		List<Object> params = new ArrayList<Object>();
		if (!"".equals(keyName)) {
			params.add(keyName);
			params.add(categoryMetadataId);
			count = categoryComAttrMetadataDao.selectCountByCondition(
					CategorySQL.CHECK_COM_KEYNAME_COUNT, params);
		}
		return count;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int checkComKeyNameOutSelf(String keyName,
			Integer categoryMetadataId, Integer categoryComAttrMetadataId) {
		Integer count = 0;
		List<Object> params = new ArrayList<Object>();
		if (!"".equals(keyName)) {
			params.add(keyName);
			params.add(categoryMetadataId);
			params.add(categoryComAttrMetadataId);
			count = categoryComAttrMetadataDao.selectCountByCondition(
					CategorySQL.CHECK_COM_KEYNAME_COUNT_OUT, params);
		}
		return count;
	}

}
