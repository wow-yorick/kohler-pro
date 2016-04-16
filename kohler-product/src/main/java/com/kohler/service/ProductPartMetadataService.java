/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service;

import java.util.List;

import com.kohler.entity.ProductPartEntity;
import com.kohler.entity.ProductPartMetadataEntity;

/**
 * Class Function Description
 * ProductPartMetadataService
 * @author zhangtingting
 * @Date 2014年9月25日
 */
public interface ProductPartMetadataService {
    /**
     * insert a isEnable=0 ProductPartMetadataEntity
     */
    public ProductPartMetadataEntity addProductPartMetadata();
    
    

    /**
     * insert into ProductPartMetadataEntity and ProductPartEntity
     */
    public void saveProductPartMetadata(ProductPartMetadataEntity productPartMetadataEntity,List<ProductPartEntity> productPartList);
    
    
    
    /**
     * update ProductPartMetadataEntity and ProductPartEntity
     */
    public void updateProductPartMetadata(ProductPartMetadataEntity productPartMetadataEntity,List<ProductPartEntity> productPartList);

    
    
    /**
     * delete ProductPart
     * @param productPartMetadatId
     * @author ztt
     * Date 2014年10月22日
     * @version
     */
    public void deleteProductPartMetadata(Integer productPartMetadatId);
    
    
    
    /**
     * Get ProductPartMetadataEntity By productPartMetadatId
     * @param productPartMetadatId
     * @return
     * @author ztt
     * Date 2014年10月22日
     * @version
     */
    public ProductPartMetadataEntity getPartMetadataById(Integer productPartMetadatId);
    
    
    
    /**
     * Get ProductPartMetadataEntity List By productMetadataId
     * @param productMetadataId
     * @return
     * @author ztt
     * Date 2014年10月22日
     * @version
     */
    public List<ProductPartMetadataEntity> getPartMetadatalistById(Integer productMetadataId);
}
