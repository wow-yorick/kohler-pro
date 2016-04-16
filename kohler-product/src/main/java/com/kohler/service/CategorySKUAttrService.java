/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.service;

import java.util.List;

import com.kohler.entity.CategorySKUAttrEntity;

/**
 * Class Function Description CategorySKUAttrService
 * 
 * @author zhangtingting
 * @Date 2014年9月29日
 */
public interface CategorySKUAttrService {

	/**
	 * get skuAttrList by categoryId
	 * 
	 * @param CategoryMetadataId
	 * @return
	 * @author XHY Date 2014年11月14日
	 * @version
	 */
	public List<CategorySKUAttrEntity> getCategorySKUAttrListByCategoryMetadataId(
			Integer CategoryMetadataId);

}
