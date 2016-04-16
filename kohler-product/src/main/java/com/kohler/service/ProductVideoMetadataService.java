/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service;

import java.util.List;

import com.kohler.entity.ProductVideoEntity;
import com.kohler.entity.ProductVideoMetadataEntity;

/**
 * Class Function Description
 * ProductVideoMetadataService
 * @author zhangtingting
 * @Date 2014年9月25日
 */
public interface ProductVideoMetadataService {
    /**
     * insert into a isEnable=0 ProductVideoMetadataEntity
     */
    public ProductVideoMetadataEntity addProductVideoMetadata();
    
    
    
    /**
     * insert into ProductVideoMetadataEntity ProductVideoEntity 
     */
    public void saveProductVideoMetadata(ProductVideoMetadataEntity videoMetadataEntity,List<ProductVideoEntity> videoList);
    
    
    
    /**
     * update ProductVideoMetadataEntity ProductVideoEntity 
     */
    public void updateProductVideoMetadata(ProductVideoMetadataEntity videoMetadataEntity,List<ProductVideoEntity> videoList);

    
    
    /**
     * delete ProductVideo
     * @param productVideoMetadatId
     * @author ztt
     * Date 2014年10月22日
     * @version
     */
    public void deleteProductVideoMetadata(Integer productVideoMetadatId);
    
    
    
    /**
     * Get ProductVideoMetadataEntity By productVideoMetadatId
     * @param productVideoMetadatId
     * @return
     * @author ztt
     * Date 2014年10月22日
     * @version
     */
    public ProductVideoMetadataEntity getVideoMetadataById(Integer productVideoMetadatId);
    
    
    
    /**
     * Get ProductVideoMetadataEntity List By productMetadataId
     * @param productMetadataId
     * @return
     * @author ztt
     * Date 2014年10月22日
     * @version
     */
    public List<ProductVideoMetadataEntity> getVideoMetadatalistById(Integer productMetadataId);
}
