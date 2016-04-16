/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.kohler.dao.CategorySearchKeywordDao;
import com.kohler.dao.CategorySearchKeywordMetadataDao;
import com.kohler.dao.ContentFieldValuesDao;
import com.kohler.dao.MasterDataCodeDao;
import com.kohler.dao.ProductDao;
import com.kohler.dao.ProductMetadataDao;
import com.kohler.dao.SysLogDao;
import com.kohler.dao.TemplateDao;
import com.kohler.dao.utils.Pager;
import com.kohler.entity.CategoryAccessoryMetadataEntity;
import com.kohler.entity.CategoryBannerUnitEntity;
import com.kohler.entity.CategoryComAttrMetadataEntity;
import com.kohler.entity.CategoryEntity;
import com.kohler.entity.CategoryHeroAreaEntity;
import com.kohler.entity.CategoryMetadataEntity;
import com.kohler.entity.CategorySKUAttrMetadataEntity;
import com.kohler.entity.CategorySearchKeywordMetadataEntity;
import com.kohler.entity.ContentFieldValues;
import com.kohler.entity.LocaleEntity;
import com.kohler.entity.MasterDataCodeConstant;
import com.kohler.entity.ProductEntity;
import com.kohler.entity.ProductMetadataEntity;
import com.kohler.entity.TemplateEntity;
import com.kohler.entity.extend.CategoryMetadataPojo;
import com.kohler.entity.extend.CategoryPojo;
import com.kohler.service.CatalogService;
import com.kohler.service.CategoryService;

/**
 * Catalog Interface Impl
 * 
 * @author XHY
 * @Date 2014年10月9日
 */
@Service
public class CatalogServiceImpl implements CatalogService {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private SysLogDao sysLogDao;

	@Autowired
	private CategoryMetadataDao categoryMetadataDao;

	@Autowired
	private ProductMetadataDao productMetadataDao;

	@Autowired
	private ProductDao productDao;

	@Autowired
	private TemplateDao templateDao;

	@Autowired
	private CategoryComAttrDao categoryComAttrDao;

	@Autowired
	private CategoryComAttrMetadataDao categoryComAttrMetadataDao;

	@Autowired
	private CategorySKUAttrMetadataDao categorySKUAttrMetadataDao;

	@Autowired
	private CategorySKUAttrDao categorySKUAttrDao;

	@Autowired
	private CategorySearchKeywordDao categorySearchKeywordDao;

	@Autowired
	private CategorySearchKeywordMetadataDao categorySearchKeywordMetadataDao;

	@Autowired
	private CategoryAccessoryMetadataDao categoryAccessoryMetadataDao;

	@Autowired
	private CategoryAccessoryDao categoryAccessoryDao;

	@Autowired
	private CategoryHeroAreaDao categoryHeroAreaDao;

	@Autowired
	private MasterDataCodeDao masterDao;

	@Autowired
	private ContentFieldValuesDao contentFieldValuesDao;

	@Autowired
	private CategoryBannerUnitDao categoryBannerUnitDao;

	@Override
	public List<Map<String, Object>> getCategoryWithMap() {
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("id", 0);
		root.put("pId", null);
		root.put("name", "ROOT");
		root.put("url", "catalogList.action?categoryId=0");
		root.put("target", "_self");
		List<Map<String, Object>> categoryTree = categoryDao.selectCategorys();
		categoryTree.add(root);

		return categoryTree;
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
	public Integer addCatalog(CategoryPojo catalog) {

		// get insert data
		CategoryMetadataEntity pareCatalog = new CategoryMetadataEntity();

		pareCatalog.setLevel(catalog.getLevel());
		pareCatalog.setSeqNo(catalog.getSeqNo());
		pareCatalog.setParentId(catalog.getParentId());

		Integer pareCatalogId = categoryMetadataDao.insert(pareCatalog);

		catalog.setCategoryMetadataId(pareCatalogId);

		// insert log

		sysLogDao.insertLogForInsert(pareCatalogId, "CATEGORY_METADATA");

		// insert children data

		CategoryEntity childCategory = new CategoryEntity();
		childCategory.setCategoryMetadataId(pareCatalogId);

		Integer[] childIds = new Integer[catalog.getLangs().length];

		// use name's index as the projo's index
		for (int i = 0; i < catalog.getLangs().length; i++) {
			childCategory.setCategoryName((String) checkArray(
					catalog.getCategoryNames(), i));

			childCategory.setLang((Integer) checkArray(catalog.getLangs(), i));
			childCategory.setPcTemplateId((Integer) checkArray(
					catalog.getPcTemplateIds(), i));
			childCategory.setSeoTitlePc((String) checkArray(
					catalog.getSeoTitlePcs(), i));
			childCategory.setSeoKeywordsPc((String) checkArray(
					catalog.getSeoKeywordsPcs(), i));
			childCategory.setSeoDescriptionPc((String) checkArray(
					catalog.getSeoDescriptionPcs(), i));
			childCategory.setSeoH1tagPc((String) checkArray(
					catalog.getSeoH1tagPcs(), i));

			childCategory.setMobileTemplateId((Integer) checkArray(
					catalog.getMobileTemplateIds(), i));
			childCategory.setMobileTemplateId((Integer) checkArray(
					catalog.getMobileTemplateIds(), i));
			childCategory.setSeoTitleMobile((String) checkArray(
					catalog.getSeoTitleMobiles(), i));
			childCategory.setSeoKeywordsMobile((String) checkArray(
					catalog.getSeoKeywordsMobiles(), i));
			childCategory.setSeoDescriptionMobile((String) checkArray(
					catalog.getSeoDescriptionMobiles(), i));
			childCategory.setSeoH1tagMobile((String) checkArray(
					catalog.getSeoH1tagMobiles(), i));

			childCategory.setIsEnable(true);

			Integer childCategoryId = categoryDao.insert(childCategory);

			childIds[i] = childCategoryId;

			sysLogDao.insertLogForInsert(childCategoryId, "CATEGORY");

			// heroAarea
			String heroAreaMetadataIds = (String) checkArray(
					catalog.getHeroAreaMetadataIds(), i);
			if (!"".equals(heroAreaMetadataIds)) {
				if (heroAreaMetadataIds.contains(",")) {
					String[] heroAreaIds = heroAreaMetadataIds.split(",");
					for (String heroAreaIdStr : heroAreaIds) {
						CategoryHeroAreaEntity heroAreaFor = new CategoryHeroAreaEntity();
						Integer heroAreaId = Integer.parseInt(heroAreaIdStr);
						heroAreaFor.setHeroAreaMetadataId(heroAreaId);
						heroAreaFor.setCategoryMetadataId(pareCatalogId);
						heroAreaFor.setLang((Integer) checkArray(
								catalog.getLangs(), i));
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
								catalog.getLangs(), i));
						heroArea.setHeroAreaMetadataId(heroAreaMetadataId);
						heroArea.setCategoryMetadataId(pareCatalogId);
						int categoryHeroAreaId = categoryHeroAreaDao
								.insert(heroArea);
						sysLogDao.insertLogForInsert(categoryHeroAreaId,
								"CATEGORY_HERO_AREA");

					}
				}

			}

		}

		return pareCatalogId;
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

		int totalProductCount = categoryDao.selectCountByCondition(
				CategorySQL.SELECT_PRODUCT_PAGE_COUNT_BY_CID, params);

		if (totalCount == 0 && totalProductCount != 0) {
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
	public CategoryMetadataEntity findById(Integer categoryMetadataId) {
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
	public boolean editCatalog(CategoryPojo catalog) {

		boolean isSuccess = false;

		// get update parentData
		CategoryMetadataEntity pareCatalog = categoryMetadataDao
				.selectById(catalog.getCategoryMetadataId());

		// pareCatalog.setLevel(category.getLevel()); level can not be update
		pareCatalog.setSeqNo(catalog.getSeqNo());

		Integer row = categoryMetadataDao.update(pareCatalog);

		// insert data
		sysLogDao.insertLogForUpdate(catalog.getCategoryId(),
				"CATEGORY_METADATA");

		Integer[] childIds = new Integer[catalog.getCategoryIds().length];

		// use id's index as the projo's index
		for (int i = 0; i < catalog.getCategoryIds().length; i++) {
			// get update data
			CategoryEntity childCategory = categoryDao.selectById(catalog
					.getCategoryIds()[i]);

			childCategory.setCategoryName((String) checkArray(
					catalog.getCategoryNames(), i));

			// childCategory.setLang(category.getLangs()[i]); update is not use
			// language
			childCategory.setPcTemplateId((Integer) checkArray(
					catalog.getPcTemplateIds(), i));
			childCategory.setSeoTitlePc((String) checkArray(
					catalog.getSeoTitlePcs(), i));
			childCategory.setSeoKeywordsPc((String) checkArray(
					catalog.getSeoKeywordsPcs(), i));
			childCategory.setSeoDescriptionPc((String) checkArray(
					catalog.getSeoDescriptionPcs(), i));
			childCategory.setSeoH1tagPc((String) checkArray(
					catalog.getSeoH1tagPcs(), i));

			childCategory.setMobileTemplateId((Integer) checkArray(
					catalog.getMobileTemplateIds(), i));
			childCategory.setMobileTemplateId((Integer) checkArray(
					catalog.getMobileTemplateIds(), i));
			childCategory.setSeoTitleMobile((String) checkArray(
					catalog.getSeoTitleMobiles(), i));
			childCategory.setSeoKeywordsMobile((String) checkArray(
					catalog.getSeoKeywordsMobiles(), i));
			childCategory.setSeoDescriptionMobile((String) checkArray(
					catalog.getSeoDescriptionMobiles(), i));
			childCategory.setSeoH1tagMobile((String) checkArray(
					catalog.getSeoH1tagMobiles(), i));

			Integer childCategoryId = categoryDao.update(childCategory);

			childIds[i] = childCategoryId;

			sysLogDao.insertLogForUpdate(childCategory.getCategoryId(),
					"CATEGORY");

			// hero area

			String heroAreaMetadataIds = (String) checkArray(
					catalog.getHeroAreaMetadataIds(), i);
			if (!"".equals(heroAreaMetadataIds)) {

				CategoryHeroAreaEntity searchHero = new CategoryHeroAreaEntity();
				searchHero.setCategoryMetadataId(catalog
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
						heroAreaFor.setCategoryMetadataId(catalog
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
						heroArea.setCategoryMetadataId(catalog
								.getCategoryMetadataId());
						int categoryHeroAreaId = categoryHeroAreaDao
								.insert(heroArea);
						sysLogDao.insertLogForInsert(categoryHeroAreaId,
								"CATEGORY_HERO_AREA");

					}
				}
			}

			// banner unit
			for (int j = 0; j < catalog.getChildCategoryIds().length; j++) {
				if (!"".equals(catalog.getBannerUnitMetadataIds()[j])) {

					// first delete detail banner unit
					CategoryBannerUnitEntity searchDelBanner = new CategoryBannerUnitEntity();
					searchDelBanner.setCatalogMetadataId(catalog
							.getCategoryMetadataId());
					searchDelBanner.setCategoryMetadataId(catalog
							.getChildCategoryIds()[j]);
					searchDelBanner.setLang(childCategory.getLang());
					searchDelBanner.setIsEnable(true);
					List<CategoryBannerUnitEntity> delLists = categoryBannerUnitDao
							.selectByCondition(searchDelBanner);
					for (CategoryBannerUnitEntity banner : delLists) {
						categoryBannerUnitDao.delete(banner);
						sysLogDao.insertLogForInsert(
								banner.getCategoryBannerUnitId(),
								"CATEGORY_BANNER_UNIT");
					}

					String[] bannerUnits = catalog.getBannerUnitMetadataIds()[j]
							.split(",");
					for (int k = 0; k < bannerUnits.length; k++) {
						CategoryBannerUnitEntity insertBanner = new CategoryBannerUnitEntity();
						insertBanner.setCatalogMetadataId(catalog
								.getCategoryMetadataId());
						insertBanner.setCategoryMetadataId(catalog
								.getChildCategoryIds()[j]);
						insertBanner.setBannerUnitMetadataId(Integer
								.parseInt(bannerUnits[k]));
						insertBanner.setLang(childCategory.getLang());

						int bannerUnitId = categoryBannerUnitDao
								.insert(insertBanner);
						sysLogDao.insertLogForInsert(bannerUnitId,
								"CATEGORY_BANNER_UNIT");
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
	public boolean deleteCatalog(Integer[] categoryMetadataIds) {

		for (Integer id : categoryMetadataIds) {
			deleteSelfAndChild(id);
			sysLogDao.insertLogForDelete(id, "CATEGORY_METADATA");

		}
		return true;
	}

	private void deleteSelfAndChild(Integer id) {

		// get category
		CategoryMetadataEntity category = categoryMetadataDao.selectById(id);
		if (category != null) {

			// if this have product delete the product;
			ProductMetadataEntity searchProduct = new ProductMetadataEntity();
			searchProduct.setCategoryMetadataId(id);
			List<ProductMetadataEntity> products = productMetadataDao
					.selectByCondition(searchProduct);
			if (products.size() > 0) {
				Integer[] productMetadataIds = new Integer[products.size()];
				for (int i = 0; i < products.size(); i++) {
					productMetadataIds[i] = products.get(i)
							.getCategoryMetadataId();
				}
				deleteProduct(productMetadataIds);
			}
			// then delete category
			CategoryEntity searchCategory = new CategoryEntity();
			searchCategory.setCategoryMetadataId(category
					.getCategoryMetadataId());
			List<CategoryEntity> categorys = categoryDao
					.selectByCondition(searchCategory);
			for (CategoryEntity delCate : categorys) {
				categoryDao.delete(delCate);
			}
			deleteCategoryChildren(category.getCategoryMetadataId());
			// delete self
			categoryMetadataDao.delete(category);

			// insert log
			sysLogDao.insertLogForDelete(category.getCategoryMetadataId(),
					"CATEGORY_METADATA");
			category = new CategoryMetadataEntity();
			category.setParentId(id);
			// 检索自己的子
			List<CategoryMetadataEntity> childrens = categoryMetadataDao
					.selectByCondition(category);
			for (CategoryMetadataEntity children : childrens) {

				// 删除子
				// 递归删除子孙
				deleteSelfAndChild(children.getCategoryMetadataId());
			}
		}
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteCategoryChildren(Integer categoryMetadataId) {

		// delete children ComAttr
		CategoryComAttrMetadataEntity searchComAttr = new CategoryComAttrMetadataEntity();
		searchComAttr.setCategoryMetadataId(categoryMetadataId);
		searchComAttr.setIsEnable(true);
		List<CategoryComAttrMetadataEntity> comAttrMetaList = categoryComAttrMetadataDao
				.selectByCondition(searchComAttr);
		for (CategoryComAttrMetadataEntity comAttrMeta : comAttrMetaList) {
			categoryService.deleteCateComAttr(comAttrMeta
					.getCategoryComAttrMetadataId());
		}
		// delete children SkuAttr
		CategorySKUAttrMetadataEntity searchSkuAttr = new CategorySKUAttrMetadataEntity();
		searchSkuAttr.setCategoryMetadataId(categoryMetadataId);
		searchSkuAttr.setIsEnable(true);
		List<CategorySKUAttrMetadataEntity> skuAttrMetaList = categorySKUAttrMetadataDao
				.selectByCondition(searchSkuAttr);
		for (CategorySKUAttrMetadataEntity skuAttrMeta : skuAttrMetaList) {
			categoryService.deleteCateSkuAttr(skuAttrMeta
					.getCategorySKUAttrMetadataId());
		}
		// delete children searchKey
		CategorySearchKeywordMetadataEntity searchKey = new CategorySearchKeywordMetadataEntity();
		searchKey.setCategoryMetadataId(categoryMetadataId);
		searchKey.setIsEnable(true);
		List<CategorySearchKeywordMetadataEntity> searchKeyMetaList = categorySearchKeywordMetadataDao
				.selectByCondition(searchKey);
		for (CategorySearchKeywordMetadataEntity searchKeyMeta : searchKeyMetaList) {
			categoryService.deleteCateSearchKey(searchKeyMeta
					.getCategorySearchKeywordMetadataId());
		}
		// delete children accessory
		CategoryAccessoryMetadataEntity searchAcc = new CategoryAccessoryMetadataEntity();
		searchAcc.setCategoryMetadataId(categoryMetadataId);
		searchAcc.setIsEnable(true);
		List<CategoryAccessoryMetadataEntity> searchAccList = categoryAccessoryMetadataDao
				.selectByCondition(searchAcc);
		for (CategoryAccessoryMetadataEntity accessory : searchAccList) {
			categoryService.deleteCateAccessory(accessory
					.getCategoryAccessoryMetadataId());
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteProduct(Integer[] productMetadataIds) {
		// Master-Detail delete;
		for (Integer productMetadataId : productMetadataIds) {

			// first delete detail =>then delete master;
			ProductEntity searchProduct = new ProductEntity();
			searchProduct.setProductMetadataId(productMetadataId);
			searchProduct.setIsEnable(true);
			List<ProductEntity> products = productDao
					.selectByCondition(searchProduct);

			for (ProductEntity product : products) {

				productDao.delete(product);
				sysLogDao.insertLogForDelete(product.getProductId(), "PRODUCT");
			}

			ProductMetadataEntity productMetadata = productMetadataDao
					.selectById(productMetadataId);
			productMetadata.setIsEnable(false);
			productMetadataDao.update(productMetadata);
			// insert log
			sysLogDao.insertLogForUpdate(productMetadataId, "PRODUCT_METADATA");

		}
		return true;
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
			String heroAreaFieldId = "";
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
							heroAreaFieldId += fileds.get(0).getContentMetadataId()
									+ ",";
						}
					}
				}

			}
			if (!"".equals(heroAreaFieldValue)) {
				heroAreaFieldValue = heroAreaFieldValue.substring(0,
						heroAreaFieldValue.length() - 1);
				
				heroAreaFieldId = heroAreaFieldId.substring(0,
						heroAreaFieldId.length() - 1);
			}
			pojo.setFieldValue(heroAreaFieldValue);
			pojo.setFieldValueId(heroAreaFieldId);

		}

		return categoryPojos;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int checkCatalogName(String catalogName, Integer lang,
			Integer categoryMetadataId) {
		Integer count = 0;
		List<Object> params = new ArrayList<Object>();
		params.add(catalogName);
		params.add(lang);
		params.add(categoryMetadataId);
		if (!"".equals(catalogName)) {
			count = categoryDao.selectCountByCondition(
					CategorySQL.CHECK_CATALOG_COUNT, params);
		}
		return count;
	}

	private Object checkArray(Object[] o, int i) {
		Object value = new Object();
		if (i < o.length) {
			value = o[i];
		} else {
			value = "";
		}
		return value;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int checkCatalogNameOutSelf(String catalogName, Integer lang,
			Integer categoryMetadataId, Integer categoryId) {
		Integer count = 0;
		List<Object> params = new ArrayList<Object>();
		params.add(catalogName);
		params.add(lang);
		params.add(categoryMetadataId);
		params.add(categoryId);
		if (!"".equals(catalogName)) {
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
	public List<CategoryMetadataPojo> findByPId(Integer categoryMetadataId) {

		return categoryMetadataDao.findByPId(categoryMetadataId);
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
	public Map<String,Object> getBannerUnitByCon(Integer catalogMetadataId,
			Integer categoryMetadataId, Integer lang) {
		Map<String,Object> bannerUnitMap = new HashMap<String,Object>();
		
		List<Map<String, Object>> fieldNames = getFiledName(
				MasterDataCodeConstant.CONTENT_DISPLAY_ATTR_NAME_BANNER_UNIT, 1);
		String filedName = "";
		for (Map<String, Object> field : fieldNames) {
			if (MasterDataCodeConstant.CONTENT_DISPLAY_ATTR_NAME_BANNER_UNIT == Integer
					.parseInt(field.get("masterdataId").toString())) {
				filedName = field.get("masterdataName").toString();
			}
		}

		CategoryBannerUnitEntity searchBanner = new CategoryBannerUnitEntity();
		searchBanner.setCatalogMetadataId(catalogMetadataId);
		searchBanner.setCategoryMetadataId(categoryMetadataId);
		searchBanner.setLang(lang);

		List<CategoryBannerUnitEntity> showLists = categoryBannerUnitDao
				.selectByCondition(searchBanner);

		String filedValues = "";
		String finalValues = "";
		String filedValueIds = "";
		String finalValueIds = "";
		for (CategoryBannerUnitEntity bannerUnit : showLists) {
			if (!"".equals(filedName)) {
				ContentFieldValues searchFiled = new ContentFieldValues();
				searchFiled.setFieldName(filedName);
				searchFiled.setContentMetadataId(bannerUnit
						.getBannerUnitMetadataId());
				List<ContentFieldValues> fileds = contentFieldValuesDao
						.selectByCondition(searchFiled);
				if (fileds != null && fileds.size() > 0) {
					filedValues += fileds.get(0).getFieldValue() + ",";
					filedValueIds += fileds.get(0).getContentMetadataId() + ",";
				}
			}
		}
		if (!"".equals(filedValues)) {
			finalValues = filedValues.substring(0, filedValues.length() - 1);
			finalValueIds = filedValueIds.substring(0, filedValueIds.length() - 1);
		}
		bannerUnitMap.put("fieldValues", finalValues);
		bannerUnitMap.put("fieldValueIds", finalValueIds);
		return bannerUnitMap;
	}

}
