/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.dao.impl;

import org.springframework.stereotype.Repository;

import com.kohler.dao.CategoryBannerUnitDao;
import com.kohler.entity.CategoryBannerUnitEntity;
import com.kohler.entity.extend.CategoryBannerUnitPojo;

/**
 * 
 * Class Function Description
 *
 * @author XHY
 * @Date 2014年11月10日
 */
@Repository
public class CategoryBannerUnitDaoImpl extends BaseDaoImpl<CategoryBannerUnitEntity> implements CategoryBannerUnitDao {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CategoryBannerUnitPojo getCateBannerById(Integer bannerUnitId) {
		// TODO Auto-generated method stub
		return null;
	}

	


}
