/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service;

import java.util.List;

import com.kohler.entity.ProductCADEntity;
import com.kohler.entity.ProductCADMetadataEntity;

/**
 * Class Function Description
 * ProductCADMetadataService
 * @author zhangtingting
 * @Date 2014年9月25日
 */
public interface ProductCADMetadataService {
    /**
     * insert a isEnable=0 ProductCADEntity 
     */
    public ProductCADMetadataEntity addProductCADMetadata();
    
    

    /**
     * insert into ProductCADMetadataEntity and ProductCADEntity 
     */
    public void saveProductCADMetadata(ProductCADMetadataEntity productCADMetadataEntity,List<ProductCADEntity> productCADList);
    
    
    
    /**
     * update ProductCADMetadataEntity and ProductCADEntity 
     */
    public void updateProductCADMetadata(ProductCADMetadataEntity productCADMetadataEntity,List<ProductCADEntity> productCADList);
    
    
    
    /**
     * delete ProductCAD
     * @param productCADMetadatId
     * @author ztt
     * Date 2014年10月22日
     * @version
     */
    public void deleteProductCADMetadata(Integer productCADMetadatId);
    
    
    
    /**
     * Get ProductCADMetadataEntity By productCADMetadatId
     * @param productCADMetadatId
     * @return
     * @author ztt
     * Date 2014年10月22日
     * @version
     */
    public ProductCADMetadataEntity getCADMetadataById(Integer productCADMetadatId);
    
    
    
    /**
     * Get ProductCADMetadataEntity List By productMetadataId
     * @param productMetadataId
     * @return
     * @author ztt
     * Date 2014年10月22日
     * @version
     */
    public List<ProductCADMetadataEntity> getCADMetadatalistById(Integer productMetadataId);
}
