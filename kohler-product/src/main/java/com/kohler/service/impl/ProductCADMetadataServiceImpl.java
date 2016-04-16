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

import com.kohler.dao.ProductCADDao;
import com.kohler.dao.ProductCADMetadataDao;
import com.kohler.dao.SysLogDao;
import com.kohler.entity.ProductCADEntity;
import com.kohler.entity.ProductCADMetadataEntity;
import com.kohler.service.ProductCADMetadataService;

/**
 * Class Function Description
 * ProductCADMetadataServiceImpl
 * @author zhangtingting
 * @Date 2014年9月25日
 */
@Service
public class ProductCADMetadataServiceImpl implements ProductCADMetadataService {
    
    @Autowired
    private ProductCADMetadataDao productCADMetadataDao;
    
    @Autowired
    private ProductCADDao productCADDao;
    
    @Autowired
    private SysLogDao sysLogDao;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ProductCADMetadataEntity addProductCADMetadata() {
        ProductCADMetadataEntity productCADMetadataEntity=new ProductCADMetadataEntity();
       /* productCADMetadataEntity.setIsEnable(false);
        
        int productCADMetadataId=productCADMetadataDao.insert(productCADMetadataEntity);       
        productCADMetadataEntity.setProductCADMetadataId(productCADMetadataId);
        
        sysLogDao.insertLogForInsert(productCADMetadataId, "PRODUCT_CAD_METADATA");*/
        return productCADMetadataEntity;
    }

    
    
    @Override
    @Transactional
    public void saveProductCADMetadata(ProductCADMetadataEntity productCADMetadataEntity,
            List<ProductCADEntity> productCADList) {
        if(null != productCADMetadataEntity && !"".equals(productCADList)){
            productCADMetadataEntity.setIsEnable(true);
            /*productCADMetadataDao.update(productCADMetadataEntity);
            sysLogDao.insertLogForUpdate(productCADMetadataEntity.getProductCADMetadataId(), "PRODUCT_CAD_METADATA");*/
            
            int productCADMetadataId=productCADMetadataDao.insert(productCADMetadataEntity);
            productCADMetadataEntity.setProductCADMetadataId(productCADMetadataId);
            
            sysLogDao.insertLogForInsert(productCADMetadataId, "PRODUCT_CAD_METADATA");
            
            if(productCADList.size()>0){
                for(ProductCADEntity cad:productCADList){
                    cad.setProductCADMetadataId(productCADMetadataEntity.getProductCADMetadataId());
                    cad.setIsEnable(true);
                    int productCADId=productCADDao.insert(cad);
                    
                    sysLogDao.insertLogForInsert(productCADId, "PRODUCT_CAD");
                }
            }
        }
    }

    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ProductCADMetadataEntity getCADMetadataById(Integer productCADMetadatId) {
        return productCADMetadataDao.selectById(productCADMetadatId);
    }

    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void updateProductCADMetadata(ProductCADMetadataEntity productCADMetadataEntity,
            List<ProductCADEntity> productCADList) {
        if(null != productCADMetadataEntity && !"".equals(productCADList)){
            productCADMetadataEntity.setIsEnable(true);
            productCADMetadataDao.update(productCADMetadataEntity);
            
            sysLogDao.insertLogForUpdate(productCADMetadataEntity.getProductCADMetadataId(), "PRODUCT_CAD_METADATA");
            
            if(productCADList.size()>0){
                for(ProductCADEntity cad:productCADList){
                    cad.setProductCADMetadataId(productCADMetadataEntity.getProductCADMetadataId());
                    cad.setIsEnable(true);
                    productCADDao.update(cad);
                    
                    sysLogDao.insertLogForUpdate(cad.getProductCADId(), "PRODUCT_CAD");
                }
            }
        }
        
    }



    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductCADMetadataEntity> getCADMetadatalistById(Integer productMetadataId) {
        ProductCADMetadataEntity entity=new ProductCADMetadataEntity();
        entity.setProductMetadataId(productMetadataId);
        entity.setIsEnable(true);
        return productCADMetadataDao.selectByCondition(entity);
    }



    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteProductCADMetadata(Integer productCADMetadataId) {
        ProductCADMetadataEntity productCADMetadataEntity=productCADMetadataDao.selectById(productCADMetadataId);
        if(null != productCADMetadataEntity){
            productCADMetadataEntity.setIsEnable(false);
            productCADMetadataDao.update(productCADMetadataEntity);
            
            sysLogDao.insertLogForDelete(productCADMetadataEntity.getProductCADMetadataId(), "PRODUCT_CAD_METADATA");

            ProductCADEntity productCADEntity=new ProductCADEntity();
            productCADEntity.setProductCADMetadataId(productCADMetadataId);
            List<ProductCADEntity> productCADList=productCADDao.selectByCondition(productCADEntity);
            if(productCADList.size()>0){
                for(ProductCADEntity cad:productCADList){
                    cad.setIsEnable(false);
                    productCADDao.update(cad);
                    
                    sysLogDao.insertLogForDelete(cad.getProductCADId(), "PRODUCT_CAD");
                }
            }
        }
    }

}
