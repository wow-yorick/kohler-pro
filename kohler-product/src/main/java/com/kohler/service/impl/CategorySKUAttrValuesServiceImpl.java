package com.kohler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohler.dao.CategorySKUAttrValuesDao;
import com.kohler.entity.CategorySKUAttrValuesEntity;
import com.kohler.service.CategorySKUAttrValuesService;

/**
 * Class Function Description CategorySKUAttrValuesServiceImpl
 * 
 * @author zhangtingting
 * @Date 2014年9月29日
 */
@Service
public class CategorySKUAttrValuesServiceImpl implements
		CategorySKUAttrValuesService {

	@Autowired
	private CategorySKUAttrValuesDao categorySKUAttrValuesDao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CategorySKUAttrValuesEntity> getSKUAttrValuesListBySKUAttrMetadataId(
			Integer categorySKUAttrMetadataId) {
		return categorySKUAttrValuesDao
				.getSKUAttrValuesListBySKUAttrMetadataId(categorySKUAttrMetadataId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CategorySKUAttrValuesEntity> getSKUAttrValuesListBySKUMetadataId(
			Integer skuMetadataId) {
		return categorySKUAttrValuesDao
				.getSKUAttrValuesListBySKUMetadataId(skuMetadataId);
	}
}
