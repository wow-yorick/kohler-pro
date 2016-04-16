package com.kohler.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohler.constants.ProductComAttrSQL;
import com.kohler.dao.CategoryAccessoryDao;
import com.kohler.dao.ProductDao;
import com.kohler.dao.SKUAccessoryDao;
import com.kohler.entity.CategoryAccessoryEntity;
import com.kohler.entity.ProductEntity;
import com.kohler.entity.SKUAccessoryEntity;
import com.kohler.service.CategoryAccessoryService;

/**
 * categoryAccessoryService
 * 
 * @author sana
 * @Date 2014年11月24日
 */
@Service("categoryAccessoryService")
public class CategoryAccessoryServiceImpl implements CategoryAccessoryService {

	@Autowired
	private CategoryAccessoryDao categoryAccessoryDao;

	@Autowired
	private SKUAccessoryDao skuAccessoryDao;

	@Autowired
	private ProductDao productDao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CategoryAccessoryEntity> getCategoryAccessoryListByCategoryMetadataId(
			String CategoryMetadataId, String lang) {
		return categoryAccessoryDao
				.getCategoryAccessoryListByCategoryMetadataId(
						CategoryMetadataId, lang);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<SKUAccessoryEntity> getSKUAccessoryBySKUId(
			String skuMetadataId, String lang,
			String categoryAccessoryMetadataId) {
		return skuAccessoryDao.getSKUAccessoryBySKUId(skuMetadataId, lang,
				categoryAccessoryMetadataId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ProductEntity> getSKUProductName(String skuid) {
		List<Object> param = new ArrayList<Object>();
		param.add(skuid);
		return productDao.selectByCondition(
				ProductComAttrSQL.SELECT_SKU_Product_NAME, param);
	}

}
