/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohler.dao.CategoryComAttrMetadataDao;
import com.kohler.entity.CategoryComAttrMetadataEntity;
import com.kohler.service.CategoryComAttrMetadataService;

/**
 * Class Function Description CategoryComAttrMetadataServiceImpl
 * 
 * @author zhangtingting
 * @Date 2014年9月25日
 */
@Service
public class CategoryComAttrMetadataServiceImpl implements
		CategoryComAttrMetadataService {

	@Autowired
	private CategoryComAttrMetadataDao categoryComAttrMetadataDao;

	@Override
	public List<CategoryComAttrMetadataEntity> getCategoryComAttrMetadataList(
			Integer categoryMetadataId) {
		CategoryComAttrMetadataEntity ccam = new CategoryComAttrMetadataEntity();
		ccam.setCategoryMetadataId(categoryMetadataId);
		ccam.setIsEnable(true);
		return categoryComAttrMetadataDao.selectByCondition(ccam);
	}

}
