/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohler.dao.ProductPartDao;
import com.kohler.entity.ProductPartEntity;
import com.kohler.service.ProductPartService;

/**
 * Class Function Description
 * ProductPartServiceImpl
 * @author ztt
 * @Date 2014年10月11日
 */
@Service
public class ProductPartServiceImpl implements ProductPartService {
    
    @Autowired
    private ProductPartDao productPartDao;


    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductPartEntity> getProductPartListByEntity(ProductPartEntity entity) {
        return productPartDao.selectByCondition(entity);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public ProductPartEntity getProductPartById(Integer productPartMetadataId) {
        return productPartDao.getProductPartById(productPartMetadataId);
    }

}
