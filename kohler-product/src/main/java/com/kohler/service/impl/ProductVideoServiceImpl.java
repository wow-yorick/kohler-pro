/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohler.dao.ProductVideoDao;
import com.kohler.entity.ProductVideoEntity;
import com.kohler.service.ProductVideoService;

/**
 * Class Function Description
 * ProductVideoServiceImpl
 * @author ztt
 * @Date 2014年10月11日
 */
@Service
public class ProductVideoServiceImpl implements ProductVideoService {
    
    @Autowired
    private ProductVideoDao productVideoDao;



    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductVideoEntity> getProductVideoListByEntity(ProductVideoEntity entity) {
        return productVideoDao.selectByCondition(entity);
    }



    /**
     * {@inheritDoc}
     */
    @Override
    public ProductVideoEntity getProductVideoById(Integer productVideoMetadataId) {
        return productVideoDao.getProductVideoById(productVideoMetadataId);
    }

}
