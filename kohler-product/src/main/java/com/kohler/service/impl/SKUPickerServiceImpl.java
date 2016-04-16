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
import com.kohler.dao.SKUPickerDao;
import com.kohler.entity.AggSKUEntity;
import com.kohler.service.SKUPickerService;

/**
 * Page Interface Impl
 *
 * @author ys
 * @Date 2014年10月17日
 */
@Service
public class SKUPickerServiceImpl implements SKUPickerService {
	@Autowired
	private SKUPickerDao skuDao;
	@Autowired
	private ProductPickerDao proDao;
	
	@Override
	public List<Map<String, Object>> selectSKUById(AggSKUEntity category) {
		// TODO Auto-generated method stub
		return skuDao.selectSKUById(category);
	}

	@Override
	public List<Map<String, Object>> getSKUAttribute(List<Object> lsit) {
		// TODO Auto-generated method stub
		return skuDao.getSKUAttribute(lsit);
	}

	@Override
	public List<Map<String, Object>> getProductName(List<Object> lsit) {
		// TODO Auto-generated method stub
		return proDao.getProductName(lsit);
	}

}
