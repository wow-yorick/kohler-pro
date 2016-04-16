/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kohler.dao.ProductPickerDao;
import com.kohler.entity.AggCategoryEntity;
import com.kohler.service.ProductPickerService;

/**
 * Page Interface Impl
 *
 * @author ys
 * @Date 2014年9月28日
 */
@Service
public class ProductPickerImpl implements ProductPickerService {
	@Autowired
	private ProductPickerDao productDao;
	@Override
	public List<Map<String, Object>> selectCategoryById(AggCategoryEntity category) {
		// TODO Auto-generated method stub
		return productDao.selectCategoryById(category);
	}

	@Override
	public List<Map<String, Object>> getProductName(List<Object> lsit) {
		// TODO Auto-generated method stub
		return productDao.getProductName(lsit);
	}

}
