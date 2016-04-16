/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohler.dao.ProductPDFDao;
import com.kohler.dao.ProductPDFMetadataDao;
import com.kohler.dao.SysLogDao;
import com.kohler.entity.ProductPDFEntity;
import com.kohler.entity.ProductPDFMetadataEntity;
import com.kohler.service.ProductPDFMetadataService;

/**
 * Class Function Description
 * ProductPDFMetadataServiceImpl
 * @author zhangtingting
 * @Date 2014年9月25日
 */
@Service
public class ProductPDFMetadataServiceImpl implements ProductPDFMetadataService {
    
    @Autowired
    private ProductPDFMetadataDao productPDFMetadataDao;
    
    @Autowired
    private ProductPDFDao productPDFDao;
    
    @Autowired
    private SysLogDao sysLogDao;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ProductPDFMetadataEntity addProductPDFMetadata() {
        ProductPDFMetadataEntity pdfMetadataEntity=new ProductPDFMetadataEntity();
        /*pdfMetadataEntity.setIsEnable(false);
        
        int productPDFMetadataId=productPDFMetadataDao.insert(pdfMetadataEntity);
        pdfMetadataEntity.setProductPDFMetadataId(productPDFMetadataId);
        
        sysLogDao.insertLogForInsert(productPDFMetadataId, "PRODUCT_PDF_METADATA");
        */
        return pdfMetadataEntity;
    }

    
    
    @Override
    public void saveProductPDFMetadata(ProductPDFMetadataEntity productPDFMetadataEntity,
            List<ProductPDFEntity> productPDFList) {
        if(null != productPDFMetadataEntity && !"".equals(productPDFList)){
            productPDFMetadataEntity.setIsEnable(true);
            /*productPDFMetadataDao.update(productPDFMetadataEntity);
            sysLogDao.insertLogForUpdate(productPDFMetadataEntity.getProductPDFMetadataId(), "PRODUCT_PDF_METADATA");*/
            
            int productPDFMetadataId=productPDFMetadataDao.insert(productPDFMetadataEntity);
            productPDFMetadataEntity.setProductPDFMetadataId(productPDFMetadataId);
            
            sysLogDao.insertLogForInsert(productPDFMetadataId, "PRODUCT_PDF_METADATA");
            
            if(productPDFList.size()>0){
                for(ProductPDFEntity pdf:productPDFList){
                    pdf.setProductPDFMetadataId(productPDFMetadataEntity.getProductPDFMetadataId());
                    pdf.setIsEnable(true);
                    int productPDFId=productPDFDao.insert(pdf);
                    
                    sysLogDao.insertLogForInsert(productPDFId, "PRODUCT_PDF");
                }
            }
        }
    }

    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void updateProductPDFMetadata(ProductPDFMetadataEntity productPDFMetadataEntity,
            List<ProductPDFEntity> productPDFList) {
        if(null != productPDFMetadataEntity && !"".equals(productPDFList)){
            productPDFMetadataEntity.setIsEnable(true);
            productPDFMetadataDao.update(productPDFMetadataEntity);
            
            sysLogDao.insertLogForUpdate(productPDFMetadataEntity.getProductPDFMetadataId(), "PRODUCT_PDF_METADATA");
            
            if(productPDFList.size()>0){
                for(ProductPDFEntity pdf:productPDFList){
                    pdf.setProductPDFMetadataId(productPDFMetadataEntity.getProductPDFMetadataId());
                    pdf.setIsEnable(true);
                    productPDFDao.update(pdf);
                    
                    sysLogDao.insertLogForUpdate(pdf.getProductPDFId(), "PRODUCT_PDF");
                }
            }
        }
    }

    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ProductPDFMetadataEntity getPDFMetadataById(Integer productPDFMetadatId) {
        return productPDFMetadataDao.selectById(productPDFMetadatId);
    }



    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductPDFMetadataEntity> getPDFMetadatalistById(Integer productMetadataId) {
        ProductPDFMetadataEntity entity=new ProductPDFMetadataEntity();
        entity.setProductMetadataId(productMetadataId);
        entity.setIsEnable(true);
        return productPDFMetadataDao.selectByCondition(entity);
    }



    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteProductPDFMetadata(Integer productPDFMetadataId) {
        ProductPDFMetadataEntity productPDFMetadataEntity=productPDFMetadataDao.selectById(productPDFMetadataId);
        if(null != productPDFMetadataEntity){
            productPDFMetadataEntity.setIsEnable(false);
            productPDFMetadataDao.update(productPDFMetadataEntity);
            
            sysLogDao.insertLogForDelete(productPDFMetadataEntity.getProductPDFMetadataId(), "PRODUCT_PDF_METADATA");

            ProductPDFEntity productPDFEntity=new ProductPDFEntity();
            productPDFEntity.setProductPDFMetadataId(productPDFMetadataId);
            List<ProductPDFEntity> productPDFList=productPDFDao.selectByCondition(productPDFEntity);
            if(productPDFList.size()>0){
                for(ProductPDFEntity pdf:productPDFList){
                    pdf.setIsEnable(false);
                    productPDFDao.update(pdf);
                    
                    sysLogDao.insertLogForDelete(pdf.getProductPDFId(), "PRODUCT_PDF");
                }
            }
        }
    }
}
