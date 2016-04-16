/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.service;

import java.util.List;

import com.kohler.entity.CategoryComAttrMetadataEntity;

/**
 * Class Function Description CategoryComAttrMetadataService
 * 
 * @author zhangtingting
 * @Date 2014年9月25日
 */
public interface CategoryComAttrMetadataService {

	/**
	 * 根据categoryMetadataId获取集合列表
	 * 
	 * @param categoryMetadataId
	 * @return list
	 */
	public List<CategoryComAttrMetadataEntity> getCategoryComAttrMetadataList(
			Integer categoryMetadataId);
}
