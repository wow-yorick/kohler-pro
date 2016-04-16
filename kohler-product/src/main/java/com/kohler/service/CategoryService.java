/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.service;

import java.util.List;
import java.util.Map;

import com.kohler.dao.utils.Pager;
import com.kohler.entity.CategoryAccessoryEntity;
import com.kohler.entity.CategoryAccessoryMetadataEntity;
import com.kohler.entity.CategoryBannerUnitEntity;
import com.kohler.entity.CategoryEntity;
import com.kohler.entity.CategoryMetadataEntity;
import com.kohler.entity.CategorySKUAttrEntity;
import com.kohler.entity.CategorySKUAttrMetadataEntity;
import com.kohler.entity.CategorySKUAttrValuesEntity;
import com.kohler.entity.CategorySKUAttrValuesMetadataEntity;
import com.kohler.entity.CategorySearchKeywordEntity;
import com.kohler.entity.CategorySearchKeywordMetadataEntity;
import com.kohler.entity.LocaleEntity;
import com.kohler.entity.TemplateEntity;
import com.kohler.entity.extend.CategoryAccessoryMetadataPojo;
import com.kohler.entity.extend.CategoryComAttrMetadataPojo;
import com.kohler.entity.extend.CategoryComAttrPojo;
import com.kohler.entity.extend.CategoryMetadataPojo;
import com.kohler.entity.extend.CategoryPojo;
import com.kohler.entity.extend.CategorySKUAttrMetadataPojo;
import com.kohler.entity.extend.CategorySKUAttrValuesPojo;
import com.kohler.entity.extend.CategorySearchKeywordMetadataPojo;

/**
 * Catalog Interface
 * 
 * @author XHY
 * @Date 2014年9月25日
 */
public interface CategoryService {

	/**
	 * @return
	 * @author XHY Date 2014年9月27日
	 * @version
	 */
	public List<Map<String, Object>> getCategoryWithMap();

	/**
	 * @param pager
	 * @param categoryId
	 * @return
	 * @author XHY Date 2014年10月9日
	 * @version
	 */
	public Pager<Map<String, Object>> getCategoryPageById(
			Pager<Map<String, Object>> pager, Integer categoryId);

	/**
	 * @param category
	 * @return
	 * @author XHY Date 2014年10月9日
	 * @version
	 */
	public Integer addCategory(CategoryPojo category);

	/**
	 * @param categoryId
	 * @return
	 * @author XHY Date 2014年10月9日
	 * @version
	 */
	public CategoryEntity getCategoryById(Integer categoryId);

	/**
	 * 
	 * @param categoryMetadataId
	 *            产品分类ID
	 * @return 产品分类多语言信息
	 * @author ztt Date 2014年10月9日
	 * @version
	 */
	public CategoryEntity getCategoryByCategoryMetadataId(
			Integer categoryMetadataId);

	/**
	 * @param categoryId
	 * @return
	 * @author XHY Date 2014年10月10日
	 * @version
	 */
	public boolean checkCategoryIsTop(Integer categoryId);

	/**
	 * @param categoryId
	 * @return
	 * @author XHY Date 2014年10月10日
	 * @version
	 */
	public boolean checkCategoryIsBottom(Integer categoryId);

	/**
	 * @param pager
	 * @param categoryId
	 * @return
	 * @author XHY Date 2014年10月10日
	 * @version
	 */
	public Pager<Map<String, Object>> getProductByCateId(
			Pager<Map<String, Object>> pager, Integer categoryId);

	/**
	 * @param categoryMetadataId
	 * @return
	 * @author Dragon Date 2014年10月11日
	 * @version
	 */
	public CategoryMetadataPojo findById(Integer categoryMetadataId);

	/**
	 * @param categoryId
	 * @return
	 * @author XHY Date 2014年10月15日
	 * @version
	 */
	public CategoryMetadataEntity getCategoryMetadataById(
			Integer categoryMetadataId);

	/**
	 * @param categoryMetadataId
	 * @return
	 * @author XHY Date 2014年10月15日
	 * @version
	 */
	public List<CategoryEntity> getCategorysByCategoryMetadataId(
			Integer categoryMetadataId);

	/**
	 * @param category
	 * @return
	 * @author XHY Date 2014年10月16日
	 * @version
	 */
	public boolean editCatalog(CategoryPojo category);

	/**
	 * @param parentCategory
	 * @param languages
	 * @return
	 * @author Dragon Date 2014年10月17日
	 * @version
	 */
	public List<CategoryPojo> getCategoryPojoById(
			CategoryMetadataEntity parentCategory, List<LocaleEntity> languages);

	/**
	 * @param pareCate
	 * @return
	 * @author XHY Date 2014年10月20日
	 * @version
	 */
	public Integer insertCategoryMetadata(
			CategoryMetadataEntity pareCate);

	/**
	 * @param categoryName
	 * @param lang
	 * @param categoryMetadataId
	 * @return
	 * @author XHY Date 2014年10月23日
	 * @version
	 */
	public int checkCategoryName(String categoryName, Integer lang,
			Integer categoryMetadataId);

	/**
	 * 
	 * @param type
	 * @param lang
	 * @return
	 * @author XHY Date 2014年11月15日
	 * @version
	 */
	public List<Map<String, Object>> getFiledName(Integer type, Integer lang);

	/**
	 * @param categoryName
	 * @param lang
	 * @param categoryMetadataId
	 * @param categoryId
	 * @return
	 * @author Dragon Date 2014年10月23日
	 * @version
	 */
	public int checkCategoryNameOutSelf(String categoryName, Integer lang,
			Integer categoryMetadataId, Integer categoryId);

	/**
	 * @param templateTypeCatalog
	 * @return
	 * @author Dragon Date 2014年10月24日
	 * @version
	 */
	public List<TemplateEntity> getTemplateListByType(Integer templateType);

	/**
	 * @param categoryComAttr
	 * @return
	 * @author Dragon Date 2014年10月28日
	 * @version
	 */
	public Integer addCategoryComAttr(CategoryComAttrPojo categoryComAttr);

	/**
	 * @param categoryMetadataId
	 * @return
	 * @author XHY Date 2014年10月28日
	 * @version
	 */
	public List<CategoryComAttrMetadataPojo> getCateComAttrByCId(
			Integer categoryMetadataId);

	/**
	 * @param categoryComAttrMetadataId
	 * @return
	 * @author XHY Date 2014年10月30日
	 * @version
	 */
	public CategoryComAttrMetadataPojo getCateComAttrById(
			Integer categoryComAttrMetadataId);

	/**
	 * @param categoryComAttrMetadataId
	 * @return
	 * @author XHY Date 2014年10月30日
	 * @version
	 */
	public boolean deleteCateComAttr(Integer categoryComAttrMetadataId);

	/**
	 * @param categoryComAttrMetadataId
	 * @param languages
	 * @return
	 * @author XHY Date 2014年10月31日
	 * @version
	 */
	public List<CategoryComAttrPojo> getComAttrByMId(
			Integer categoryComAttrMetadataId, List<LocaleEntity> languages);

	/**
	 * @param categoryComAttr
	 * @return
	 * @author XHY Date 2014年10月31日
	 * @version
	 */
	public Integer editCategoryComAttr(CategoryComAttrPojo categoryComAttr);

	/**
	 * 
	 * @param categorySKUAttrMetadataEntity
	 * @param skuAttrList
	 * @param attrValuesList
	 * @param imageUrls
	 * @return
	 * @author Dragon Date 2014年11月19日
	 * @version
	 */
	public Integer saveCategorySkuAttrs(
			CategorySKUAttrMetadataEntity categorySKUAttrMetadataEntity,
			List<CategorySKUAttrEntity> skuAttrList,
			List<CategorySKUAttrValuesPojo> attrValuesList, Integer[] imageUrls);

	/**
	 * @param categorySKUAttrMetadataId
	 * @return
	 * @author XHY Date 2014年11月7日
	 * @version
	 */
	public CategorySKUAttrMetadataPojo getSkuAttrById(
			Integer categorySKUAttrMetadataId);

	/**
	 * @param skuAttr
	 * @return
	 * @author XHY Date 2014年11月8日
	 * @version
	 */
	public List<CategorySKUAttrEntity> getSKuAttrList(
			CategorySKUAttrEntity skuAttr);

	/**
	 * @param categorySKUAttrMetadataEntity
	 * @param skuAttrList
	 * @return
	 * @author XHY Date 2014年11月9日
	 * @version
	 * @param imageUrls
	 * @param attrValuesList
	 */
	public Integer editCategorySkuAttrs(
			CategorySKUAttrMetadataEntity categorySKUAttrMetadataEntity,
			List<CategorySKUAttrEntity> skuAttrList,
			List<CategorySKUAttrValuesPojo> attrValuesList, Integer[] imageUrls);

	/**
	 * @param categorySearchKeywordMetadata
	 * @param searchKeywords
	 * @return
	 * @author XHY Date 2014年11月10日
	 * @version
	 */
	public Integer saveCategorySearchKeys(
			CategorySearchKeywordMetadataEntity categorySearchKeywordMetadata,
			List<CategorySearchKeywordEntity> searchKeywords);

	/**
	 * @param categorySearchKeyMetadataId
	 * @return
	 * @author XHY Date 2014年11月10日
	 * @version
	 */
	public CategorySearchKeywordMetadataPojo getSearchKeyById(
			Integer categorySearchKeyMetadataId);

	/**
	 * @param searchKeyword
	 * @return
	 * @author XHY Date 2014年11月10日
	 * @version
	 */
	public List<CategorySearchKeywordEntity> getSearchKeyList(
			CategorySearchKeywordEntity searchKeyword);

	/**
	 * @param categorySearchKeywordMetadata
	 * @param skuAttrList
	 * @return
	 * @author XHY Date 2014年11月10日
	 * @version
	 */
	public Integer editCategorySearchKey(
			CategorySearchKeywordMetadataEntity categorySearchKeywordMetadata,
			List<CategorySearchKeywordEntity> searchKeywords);

	/**
	 * @param categorySKUAttrMetadataId
	 * @return
	 * @author XHY Date 2014年11月10日
	 * @version
	 */
	public boolean deleteCateSkuAttr(Integer categorySKUAttrMetadataId);

	/**
	 * @param categorySearchKeywordMetadataId
	 * @return
	 * @author XHY Date 2014年11月10日
	 * @version
	 */
	public boolean deleteCateSearchKey(Integer categorySearchKeywordMetadataId);

	/**
	 * @param categoryAccessoryMetadata
	 * @param accessories
	 * @return
	 * @author XHY Date 2014年11月11日
	 * @version
	 */
	public Integer saveAccessors(
			CategoryAccessoryMetadataEntity categoryAccessoryMetadata,
			List<CategoryAccessoryEntity> accessories);

	/**
	 * @param categoryAccessoryMetadataId
	 * @return
	 * @author XHY Date 2014年11月11日
	 * @version
	 */
	public CategoryAccessoryMetadataPojo getAccessoryById(
			Integer categoryAccessoryMetadataId);

	/**
	 * @return
	 * @author XHY Date 2014年11月11日
	 * @version
	 */
	public List<Map<String, Object>> getAllType(Integer type, Integer lang);

	/**
	 * @param accessory
	 * @return
	 * @author XHY Date 2014年11月11日
	 * @version
	 */
	public List<CategoryAccessoryEntity> getAccessoryList(
			CategoryAccessoryEntity accessory);

	/**
	 * @param categoryAccessoryMetadata
	 * @param accessorys
	 * @return
	 * @author XHY Date 2014年11月11日
	 * @version
	 */
	public Integer editAccessorys(
			CategoryAccessoryMetadataEntity categoryAccessoryMetadata,
			List<CategoryAccessoryEntity> accessorys);

	/**
	 * @param categoryAccessoryMetadataId
	 * @return
	 * @author XHY Date 2014年11月11日
	 * @version
	 */
	public boolean deleteCateAccessory(Integer categoryAccessoryMetadataId);

	/**
	 * @param categoryBannerUnit
	 * @return
	 * @author XHY Date 2014年11月12日
	 * @version
	 */
	public Integer saveBannerUnit(CategoryBannerUnitEntity categoryBannerUnit);

	/**
	 * @param bannerUnitId
	 * @return
	 * @author XHY Date 2014年11月12日
	 * @version
	 */
	public CategoryBannerUnitEntity getBannerById(Integer bannerUnitId);

	/**
	 * @param bannerUnitMetadataId
	 * @return
	 * @author XHY Date 2014年11月12日
	 * @version
	 */
	public CategoryBannerUnitEntity getCategoryBannerById(
			Integer bannerUnitMetadataId);

	/**
	 * @param categoryBannerUnit
	 * @return
	 * @author XHY Date 2014年11月12日
	 * @version
	 */
	public Integer editBannerUnit(CategoryBannerUnitEntity categoryBannerUnit);

	/**
	 * @param categoryMetadataId
	 * @return
	 * @author XHY Date 2014年11月13日
	 * @version
	 */
	public List<CategorySKUAttrMetadataPojo> getCateSkuAttrByCId(
			Integer categoryMetadataId);

	/**
	 * @param categoryMetadataId
	 * @return
	 * @author XHY Date 2014年11月13日
	 * @version
	 */
	public List<CategorySearchKeywordMetadataPojo> getCateSearchKeyByCId(
			Integer categoryMetadataId);

	/**
	 * @param categoryMetadataId
	 * @return
	 * @author XHY Date 2014年11月13日
	 * @version
	 */
	public List<CategoryAccessoryMetadataPojo> getCateAccessoryByCId(
			Integer categoryMetadataId);

	/**
	 * @return
	 * @author XHY Date 2014年11月14日
	 * @version
	 */
	public List<String> getDropDownType();

	/**
	 * @param categoryBannerUnitId
	 * @return
	 * @author XHY Date 2014年11月18日
	 * @version
	 */
	public boolean deleteBannerUnitById(Integer categoryBannerUnitId);

	/**
	 * 
	 * @param categoryMetadataId
	 * @param lang
	 * @return
	 * @author XHY Date 2014年11月18日
	 * @version
	 */
	public List<CategoryBannerUnitEntity> getBannerUnitListByCon(
			Integer categoryMetadataId, Integer lang);

	/**
	 * @param categorySKUAttrMetadataId
	 * @return
	 * @author XHY Date 2014年11月20日
	 * @version
	 */
	public List<CategorySKUAttrValuesMetadataEntity> getSKuAttrValuesBySKUId(
			Integer categorySKUAttrMetadataId);

	/**
	 * @param skuAttrValuesMetaList
	 * @return
	 * @author XHY Date 2014年11月20日
	 * @version
	 */
	public List<CategorySKUAttrValuesEntity> getSkuAttrValueByMetaList(
			List<CategorySKUAttrValuesMetadataEntity> skuAttrValuesMetaList);

	/**
	 * @param keyName
	 * @param categoryMetadataId
	 * @return
	 * @author XHY
	 * Date 2014年12月18日
	 * @version
	 */
	public int checkComKeyName(String keyName, Integer categoryMetadataId);

	/**
	 * @param keyName
	 * @param categoryMetadataId
	 * @param categoryComAttrMetadataId
	 * @return
	 * @author XHY
	 * Date 2014年12月18日
	 * @version
	 */
	public int checkComKeyNameOutSelf(String keyName,
			Integer categoryMetadataId, Integer categoryComAttrMetadataId);

}
