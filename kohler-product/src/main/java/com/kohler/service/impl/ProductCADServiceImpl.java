/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohler.dao.ProductCADDao;
import com.kohler.entity.ProductCADEntity;
import com.kohler.service.ProductCADService;

/**
 * Class Function Description
 * ProductCADServiceImpl
 * @author ztt
 * @Date 2014年10月11日
 */
@Service
public class ProductCADServiceImpl implements ProductCADService {
    
    @Autowired
    private ProductCADDao productCADDao;



    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductCADEntity> getProductCADListByEntity(ProductCADEntity entity) {
        return productCADDao.selectByCondition(entity);
    }



    /**
     * {@inheritDoc}
     */
    @Override
    public ProductCADEntity getProductCADById(Integer productCADMetadataId) {
        return productCADDao.getProductCADById(productCADMetadataId);
    }

}
