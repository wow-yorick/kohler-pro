/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.service;

import java.util.List;

import com.kohler.entity.CategoryAccessoryEntity;
import com.kohler.entity.ProductEntity;
import com.kohler.entity.SKUAccessoryEntity;

/**
 * CategoryAccessory Service
 * 
 * @author sana
 * @Date 2014年11月24日
 */
public interface CategoryAccessoryService {

	/**
	 * 通过CategoryMetadataId获取Accessory属性
	 * 
	 * @param CategoryMetadataId
	 * @param lang
	 * @return
	 * @author sana Date 2014年11月24日
	 * @version
	 */
	public List<CategoryAccessoryEntity> getCategoryAccessoryListByCategoryMetadataId(
			String CategoryMetadataId, String lang);

	/**
	 * 编辑时获取对应accessory属性的值
	 * 
	 * @param skuMetadataId
	 * @param lang
	 * @param categoryAccessoryMetadataId
	 * @return
	 * @author sana Date 2014年11月24日
	 * @version
	 */
	public List<SKUAccessoryEntity> getSKUAccessoryBySKUId(
			String skuMetadataId, String lang,
			String categoryAccessoryMetadataId);

	/**
	 * @param skuid
	 * @return
	 * @author sana Date 2014年11月24日
	 * @version
	 */
	public List<ProductEntity> getSKUProductName(String skuid);

}
