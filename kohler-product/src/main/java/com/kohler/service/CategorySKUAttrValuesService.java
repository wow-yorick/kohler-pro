/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.service;

import java.util.List;

import com.kohler.entity.CategorySKUAttrValuesEntity;

/**
 * Class Function Description CategorySKUAttrValuesService
 * 
 * @author zhangtingting
 * @Date 2014年9月29日
 */
public interface CategorySKUAttrValuesService {

	public List<CategorySKUAttrValuesEntity> getSKUAttrValuesListBySKUAttrMetadataId(
			Integer categorySKUAttrMetadataId);

	public List<CategorySKUAttrValuesEntity> getSKUAttrValuesListBySKUMetadataId(
			Integer skuMetadataId);

}
