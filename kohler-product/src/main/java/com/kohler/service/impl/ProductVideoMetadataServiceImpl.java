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
import com.kohler.dao.ProductVideoMetadataDao;
import com.kohler.dao.SysLogDao;
import com.kohler.entity.ProductVideoEntity;
import com.kohler.entity.ProductVideoMetadataEntity;
import com.kohler.service.ProductVideoMetadataService;

/**
 * Class Function Description
 * ProductVideoMetadataServiceImpl
 * @author zhangtingting
 * @Date 2014年9月25日
 */
@Service
public class ProductVideoMetadataServiceImpl implements ProductVideoMetadataService {

    @Autowired
    private ProductVideoMetadataDao productVideoMetadataDao;
    
    @Autowired
    private ProductVideoDao productVideoDao;
    
    @Autowired
    private SysLogDao sysLogDao;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ProductVideoMetadataEntity addProductVideoMetadata() {
        ProductVideoMetadataEntity videoMetadataEntity=new ProductVideoMetadataEntity();
        /*videoMetadataEntity.setIsEnable(false);
        
        int productVideoMetadataId=productVideoMetadataDao.insert(videoMetadataEntity);
        videoMetadataEntity.setProductVideoMetadataId(productVideoMetadataId);
        
        sysLogDao.insertLogForInsert(productVideoMetadataId, "PRODUCT_VIDEO_METADATA");*/
        
        return videoMetadataEntity;
    }
    
    @Override
    public void saveProductVideoMetadata(ProductVideoMetadataEntity videoMetadataEntity,
            List<ProductVideoEntity> videoList) {
        if(null != videoMetadataEntity && !"".equals(videoList)){
            videoMetadataEntity.setIsEnable(true);
            /*productVideoMetadataDao.update(videoMetadataEntity);
            sysLogDao.insertLogForUpdate(videoMetadataEntity.getProductVideoMetadataId(), "PRODUCT_VIDEO_METADATA");*/
            
            int productVideoMetadataId=productVideoMetadataDao.insert(videoMetadataEntity);
            videoMetadataEntity.setProductVideoMetadataId(productVideoMetadataId);
            
            sysLogDao.insertLogForInsert(productVideoMetadataId, "PRODUCT_VIDEO_METADATA");
            
            if(videoList.size()>0){
                for(ProductVideoEntity video:videoList){
                    video.setProductVideoMetadataId(videoMetadataEntity.getProductVideoMetadataId());
                    video.setIsEnable(true);
                    int productVideoId=productVideoDao.insert(video);
                    
                    sysLogDao.insertLogForInsert(productVideoId, "PRODUCT_VIDEO");
                }
            }
        }
    }

    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ProductVideoMetadataEntity getVideoMetadataById(Integer productVideoMetadatId) {
        return productVideoMetadataDao.selectById(productVideoMetadatId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateProductVideoMetadata(ProductVideoMetadataEntity videoMetadataEntity,
            List<ProductVideoEntity> videoList) {
        if(null != videoMetadataEntity && !"".equals(videoList)){
            videoMetadataEntity.setIsEnable(true);
            productVideoMetadataDao.update(videoMetadataEntity);
                 
            sysLogDao.insertLogForUpdate(videoMetadataEntity.getProductVideoMetadataId(), "PRODUCT_VIDEO_METADATA");
            
            if(videoList.size()>0){
                for(ProductVideoEntity video:videoList){
                    video.setProductVideoMetadataId(videoMetadataEntity.getProductVideoMetadataId());
                    video.setIsEnable(true);
                    productVideoDao.update(video);
                    
                    sysLogDao.insertLogForUpdate(video.getProductVideoId(), "PRODUCT_VIDEO");
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductVideoMetadataEntity> getVideoMetadatalistById(Integer productMetadataId) {
        ProductVideoMetadataEntity entity=new ProductVideoMetadataEntity();
        entity.setProductMetadataId(productMetadataId);
        entity.setIsEnable(true);
        return productVideoMetadataDao.selectByCondition(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteProductVideoMetadata(Integer productVideoMetadataId) {
        ProductVideoMetadataEntity productVideoMetadataEntity=productVideoMetadataDao.selectById(productVideoMetadataId);
        if(null != productVideoMetadataEntity){
            productVideoMetadataEntity.setIsEnable(false);
            productVideoMetadataDao.update(productVideoMetadataEntity);
            
            sysLogDao.insertLogForDelete(productVideoMetadataEntity.getProductVideoMetadataId(), "PRODUCT_Video_METADATA");

            ProductVideoEntity productVideoEntity=new ProductVideoEntity();
            productVideoEntity.setProductVideoMetadataId(productVideoMetadataId);
            List<ProductVideoEntity> productVideoList=productVideoDao.selectByCondition(productVideoEntity);
            if(productVideoList.size()>0){
                for(ProductVideoEntity video:productVideoList){
                    video.setIsEnable(false);
                    productVideoDao.update(video);
                    
                    sysLogDao.insertLogForDelete(video.getProductVideoId(), "PRODUCT_Video");
                }
            }
        }
    }
}
