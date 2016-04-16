/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.kohler.constants.ProductPickerSQL;
import com.kohler.dao.ProductPickerDao;
import com.kohler.entity.AggCategoryEntity;
import com.kohler.entity.CategoryMetadataEntity;

@Repository
public class ProductPickerDaoImpl extends BaseDaoImpl<CategoryMetadataEntity> implements ProductPickerDao {

	@Override
	public List<Map<String, Object>> selectCategoryById(AggCategoryEntity category) {
	    List<Map<String, Object>> categorylist = new ArrayList<Map<String,Object>>();
		StringBuffer listSql = new StringBuffer(ProductPickerSQL.SELECT_CATEGORY_BY_ID);
		List<Object> list = new ArrayList<Object>();
		if (category.getProductName() != null
				&& !category.getProductName().equals("")) {
			listSql.append(" and pd.PRODUCT_NAME like concat(concat('%',?),'%')");
			list.add(category.getProductName());
		}
		if (category.getProductId() != null
				&& !category.getProductId().equals("")) {
		    List<Object> list1 = new ArrayList<Object>();
		    list1.add(category.getProductId());
		    list.add(category.getProductId());
		    StringBuffer isFather = new StringBuffer(ProductPickerSQL.SELECT_FATHER);
		    List<CategoryMetadataEntity> fa = selectByCondition(isFather.toString(), list1);
		    List<Object> faList = new ArrayList<Object>();
		    for (int i = 0; i < fa.size(); i++) {
		        faList.add(fa.get(i).getCategoryMetadataId());
            }
		    List<CategoryMetadataEntity> sun = new ArrayList<CategoryMetadataEntity>();
		    int c = 1;
		    if(fa.size() > 0){
		        while (c == 1) {
                    StringBuffer isSon = new StringBuffer("select * from CATEGORY_METADATA ");
                    String s  = "?";
                    for (int i = 0; i < faList.size(); i++) {
                        s += ",?";
                    }
                    isSon.append(" where PARENT_ID in ("+s+") ");
                    faList.add(-1);
                    sun = selectByCondition(isSon.toString(), faList);
                    faList.clear();
                    for (int i = 0; i < sun.size(); i++) {
                        faList.add(sun.get(i).getCategoryMetadataId());
                        list.add(sun.get(i).getCategoryMetadataId());
                    }
                    if(sun.size() <= 0 || sun == null || sun.equals("")){
                        c = 0;
                    }
                }
		    }
		    String Ssize = "?";
		    for (int i = 0; i < list.size(); i++) {
                Ssize += ",?";
            }
		    if(Ssize.length() != 1)
		        list.add(0);
			listSql.append(" and cr.CATEGORY_ID in ("+Ssize+") ");
		}
		categorylist = selectByConditionWithMap(listSql.toString(), list);
		System.out.println(categorylist);
		
		return categorylist;
	}

	@Override
	public List<Map<String, Object>> getProductName(List<Object> nameList) {
		nameList = new ArrayList<Object>();
		StringBuffer listSql = new StringBuffer(ProductPickerSQL.GET_ALL_PRODUCTNAME);
		List<Map<String, Object>> list = selectByConditionWithMap(
				listSql.toString(), nameList);
		return list;
	}

	

}
