/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.record.formula.functions.T;
import org.springframework.stereotype.Repository;
import com.kohler.constants.SKUPickerSQL;
import com.kohler.dao.SKUPickerDao;
import com.kohler.entity.AggSKUEntity;

@Repository
public class SKUPickerDaoImpl extends BaseDaoImpl<T> implements SKUPickerDao {
	
	@Override
	public List<Map<String, Object>> selectSKUById(AggSKUEntity sku) {
		StringBuffer listSql = new StringBuffer(SKUPickerSQL.SELECT_SKU_BY_ID);
		List<Object> list = new ArrayList<Object>();
		if (sku.getProductName() != null
				&& !sku.getProductName().equals("")) {
			listSql.append(" and pc.PRODUCT_NAME like concat(concat('%',?),'%')");
			list.add(sku.getProductName());
		}
		if (sku.getCategoryName() != null
				&& !sku.getCategoryName().equals("")) {
			listSql.append(" and ce.CATEGORY_ID = ? ");
			list.add(sku.getCategoryName());
		}
		List<Map<String, Object>> categorylist = selectByConditionWithMap(listSql.toString(), list);
		return categorylist;
	}

	@Override
	public List<Map<String, Object>> getSKUAttribute(List<Object> lsit) {
		// TODO Auto-generated method stub
		lsit = new ArrayList<Object>();
		StringBuffer listSql = new StringBuffer(SKUPickerSQL.SELECT_SKU_ATTRIBUTE);
		List<Map<String, Object>> list = selectByConditionWithMap(
				listSql.toString(), lsit);
		return list;
	}



	

}
