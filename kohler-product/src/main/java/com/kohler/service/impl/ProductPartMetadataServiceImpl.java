/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kohler.dao.ProductPartDao;
import com.kohler.dao.ProductPartMetadataDao;
import com.kohler.dao.SysLogDao;
import com.kohler.entity.ProductPartEntity;
import com.kohler.entity.ProductPartMetadataEntity;
import com.kohler.service.ProductPartMetadataService;

/**
 * Class Function Description
 * ProductPartMetadataService
 * @author zhangtingting
 * @Date 2014年9月25日
 */
@Service
public class ProductPartMetadataServiceImpl implements ProductPartMetadataService {
    
    @Autowired
    private ProductPartMetadataDao productPartMetadataDao;
    
    @Autowired
    private ProductPartDao productPartDao;
    
    @Autowired
    private SysLogDao sysLogDao;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ProductPartMetadataEntity addProductPartMetadata() {
        ProductPartMetadataEntity productPartMetadataEntity=new ProductPartMetadataEntity();
       /* productPartMetadataEntity.setIsEnable(false);
        
        int productPartMetadataId=productPartMetadataDao.insert(productPartMetadataEntity);
        productPartMetadataEntity.setProductPartMetadataId(productPartMetadataId);
        
        sysLogDao.insertLogForInsert(productPartMetadataId, "PRODUCT_PART_METADATA");*/
        
        return productPartMetadataEntity;
    }

    @Override
    @Transactional
    public void saveProductPartMetadata(ProductPartMetadataEntity productPartMetadataEntity,
            List<ProductPartEntity> productPartList) {
        if(null != productPartMetadataEntity && !"".equals(productPartList)){
            productPartMetadataEntity.setIsEnable(true);
           /* productPartMetadataDao.update(productPartMetadataEntity);
            sysLogDao.insertLogForUpdate(productPartMetadataEntity.getProductPartMetadataId(), "PRODUCT_PART_METADATA");*/
            
            int productPartMetadataId=productPartMetadataDao.insert(productPartMetadataEntity);
            productPartMetadataEntity.setProductPartMetadataId(productPartMetadataId);
            
            sysLogDao.insertLogForInsert(productPartMetadataId, "PRODUCT_PART_METADATA");
            
            if(productPartList.size()>0){
                for(ProductPartEntity productPart:productPartList){
                    productPart.setProductPartMetadataId(productPartMetadataEntity.getProductPartMetadataId());
                    productPart.setIsEnable(true);
                    int productPartId=productPartDao.insert(productPart);
                    
                    sysLogDao.insertLogForInsert(productPartId, "PRODUCT_PART");
                }
            }
        }
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateProductPartMetadata(ProductPartMetadataEntity productPartMetadataEntity,
            List<ProductPartEntity> productPartList) {
        if(null != productPartMetadataEntity && !"".equals(productPartList)){
            productPartMetadataEntity.setIsEnable(true);
            productPartMetadataDao.update(productPartMetadataEntity);
            
            sysLogDao.insertLogForUpdate(productPartMetadataEntity.getProductPartMetadataId(), "PRODUCT_PART_METADATA");
            
            if(productPartList.size()>0){
                for(ProductPartEntity productPart:productPartList){
                    productPart.setProductPartMetadataId(productPartMetadataEntity.getProductPartMetadataId());
                    productPart.setIsEnable(true);
                    productPartDao.update(productPart);
                    
                    sysLogDao.insertLogForUpdate(productPart.getProductPartMetadataId(), "PRODUCT_PART");
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductPartMetadataEntity getPartMetadataById(Integer productPartMetadatId) {
        return productPartMetadataDao.selectById(productPartMetadatId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductPartMetadataEntity> getPartMetadatalistById(Integer productMetadataId) {
        ProductPartMetadataEntity entity=new ProductPartMetadataEntity();
        entity.setProductMetadataId(productMetadataId);
        entity.setIsEnable(true);
        return productPartMetadataDao.selectByCondition(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteProductPartMetadata(Integer productPartMetadataId) {
        ProductPartMetadataEntity productPartMetadataEntity=productPartMetadataDao.selectById(productPartMetadataId);
        if(null != productPartMetadataEntity){
            productPartMetadataEntity.setIsEnable(false);
            productPartMetadataDao.update(productPartMetadataEntity);
            
            sysLogDao.insertLogForDelete(productPartMetadataEntity.getProductPartMetadataId(), "PRODUCT_Part_METADATA");

            ProductPartEntity productPartEntity=new ProductPartEntity();
            productPartEntity.setProductPartMetadataId(productPartMetadataId);
            List<ProductPartEntity> productPartList=productPartDao.selectByCondition(productPartEntity);
            if(productPartList.size()>0){
                for(ProductPartEntity part:productPartList){
                    part.setIsEnable(false);
                    productPartDao.update(part);
                    
                    sysLogDao.insertLogForDelete(part.getProductPartId(), "PRODUCT_Part");
                }
            }
        }
    }    
}
