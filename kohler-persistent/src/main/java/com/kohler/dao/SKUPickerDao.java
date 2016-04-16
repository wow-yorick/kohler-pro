/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.dao;

import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.record.formula.functions.T;
import com.kohler.entity.AggSKUEntity;

public interface SKUPickerDao extends BaseDao<T>{
	//查询并显示
	public List<Map<String, Object>> selectSKUById(AggSKUEntity sku);
	//查询attribute值
	public List<Map<String, Object>> getSKUAttribute(List<Object> lsit);
}
