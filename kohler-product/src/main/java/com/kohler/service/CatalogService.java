/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.service;

import java.util.List;
import java.util.Map;

import com.kohler.dao.utils.Pager;
import com.kohler.entity.CategoryEntity;
import com.kohler.entity.CategoryMetadataEntity;
import com.kohler.entity.LocaleEntity;
import com.kohler.entity.TemplateEntity;
import com.kohler.entity.extend.CategoryMetadataPojo;
import com.kohler.entity.extend.CategoryPojo;

/**
 * Catalog Interface
 * 
 * @author XHY
 * @Date 2014年9月25日
 */
public interface CatalogService {

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
	 * @param catalog
	 * @return
	 * @author XHY Date 2014年10月9日
	 * @version
	 */
	public Integer addCatalog(CategoryPojo catalog);

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
	 * 
	 * @param categoryMetadataId
	 * @return
	 * @author Dragon Date 2014年11月14日
	 * @version
	 */
	public boolean deleteCategoryChildren(Integer categoryMetadataId);

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
	public CategoryMetadataEntity findById(Integer categoryMetadataId);

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
	 * @param categoryMetadataIds
	 * @return
	 * @author XHY Date 2014年10月16日
	 * @version
	 */
	public boolean deleteCatalog(Integer[] categoryMetadataIds);

	/**
	 * @param productIds
	 * @return
	 * @author XHY Date 2014年10月17日
	 * @version
	 */
	public boolean deleteProduct(Integer[] productMetadataIds);

	/**
	 * @param parentCategory
	 * @param languages
	 * @return
	 * @author XHY Date 2014年10月17日
	 * @version
	 */
	public List<CategoryPojo> getCategoryPojoById(
			CategoryMetadataEntity parentCategory, List<LocaleEntity> languages);

	/**
	 * @param catalogName
	 * @param lang
	 * @return
	 * @author XHY Date 2014年10月22日
	 * @version
	 */
	public int checkCatalogName(String catalogName, Integer lang,
			Integer categoryMetadataId);

	/**
	 * @param catalogName
	 * @param lang
	 * @param categoryMetadataId
	 * @param categoryId
	 * @return
	 * @author Dragon Date 2014年10月23日
	 * @version
	 */
	public int checkCatalogNameOutSelf(String catalogName, Integer lang,
			Integer categoryMetadataId, Integer categoryId);

	/**
	 * @param string
	 * @return
	 * @author XHY Date 2014年10月24日
	 * @version
	 */
	public List<TemplateEntity> getTemplateListByType(Integer templateType);

	/**
	 * 
	 * @param categoryMetadataId
	 * @param languages
	 * @return
	 * @author Dragon Date 2014年11月17日
	 * @version
	 */
	public List<CategoryMetadataPojo> findByPId(Integer categoryMetadataId);

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
	 * 
	 * @param catalogMetadataId
	 * @param categoryMetadataId
	 * @param lang
	 * @return
	 * @author Dragon
	 * Date 2014年12月16日
	 * @version
	 */
	public Map<String,Object> getBannerUnitByCon(Integer catalogMetadataId,
			Integer categoryMetadataId, Integer lang);
}
