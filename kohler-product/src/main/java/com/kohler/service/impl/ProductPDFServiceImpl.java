/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohler.dao.FileAssetDao;
import com.kohler.dao.ProductPDFDao;
import com.kohler.entity.FileAssetEntity;
import com.kohler.entity.ProductPDFEntity;
import com.kohler.service.ProductPDFService;

/**
 * Class Function Description
 * ProductPDFServiceImpl
 * @author ztt
 * @Date 2014年10月11日
 */
@Service
public class ProductPDFServiceImpl implements ProductPDFService {
    
    @Autowired
    private ProductPDFDao productPDFDao;

    @Autowired
    private FileAssetDao fileAssetDao;
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductPDFEntity> getProductPDFListByEntity(ProductPDFEntity entity) {
        return productPDFDao.selectByCondition(entity);
    }



    /**
     * {@inheritDoc}
     */
    @Override
    public ProductPDFEntity getProductPDFById(Integer productPDFMetadataId) {
        return productPDFDao.getProductPDFById(productPDFMetadataId);
    }



    /**
     * {@inheritDoc}
     */
    @Override
    public FileAssetEntity getFileAssetById(Integer fileId) {
        // TODO Auto-generated method stub
        return fileAssetDao.selectById(fileId);
    }
}
