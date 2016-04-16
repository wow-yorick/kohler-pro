package com.kohler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohler.dao.CategorySKUAttrDao;
import com.kohler.entity.CategorySKUAttrEntity;
import com.kohler.service.CategorySKUAttrService;

/**
 * Class Function Description CategorySKUAttrServiceImpl
 * 
 * @author zhangtingting
 * @Date 2014年9月29日
 */
@Service
public class CategorySKUAttrServiceImpl implements CategorySKUAttrService {

	@Autowired
	private CategorySKUAttrDao categorySKUAttrDao;

	@Override
	public List<CategorySKUAttrEntity> getCategorySKUAttrListByCategoryMetadataId(
			Integer categoryMetadataId) {

		return categorySKUAttrDao
				.getCategorySKUAttrListByCategoryMetadataId(categoryMetadataId);
	}

}
