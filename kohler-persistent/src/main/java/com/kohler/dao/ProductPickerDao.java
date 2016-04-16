/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.dao;

import java.util.List;
import java.util.Map;

import com.kohler.entity.AggCategoryEntity;
import com.kohler.entity.CategoryMetadataEntity;

public interface ProductPickerDao extends BaseDao<CategoryMetadataEntity>{
	//查询并显示
	public List<Map<String, Object>> selectCategoryById(AggCategoryEntity category);
	//下拉框填充
	public List<Map<String, Object>> getProductName(List<Object> lsit);
}
