/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import com.kohler.entity.ProductEntity;
import com.kohler.entity.ProductMetadataEntity;

/**
 * Class Function Description
 * ProductMetadataService
 * @author zhangtingting
 * @Date 2014年9月25日
 */
public interface ProductMetadataService {
   
    /**
     * Add a isEnable=0 ProductMetadatEntity
     */
    public ProductMetadataEntity addProductMetadata();
    
    
    
    /**
     * insert into ProductMetadata And Product
     * @param attrlist 
     */
    public void saveProductMetadata(ProductMetadataEntity productMetadata,List<ProductEntity> productList, List<Map<String, Object>> attrlist) throws UnsupportedEncodingException ;
    
    
    /**
     * insert into ProductMetadata And Product
     * @param attrlist 
     */
    public void updateProductMetadata(ProductMetadataEntity productMetadata,List<ProductEntity> productList, List<Map<String, Object>> attrlist)throws UnsupportedEncodingException;
    
    
    
    /**
     * Select ProductMetadataEntity
     * @param productMetadataId 
     * @return
     * @author ztt
     * Date 2014年10月10日
     * @version
     */
    public ProductMetadataEntity getProductMetadataById(Integer productMetadataId);



    /**
     * @param productMetadataIds
     * @return
     * @author sana
     * Date 2014年12月16日
     * @version
     */
    public int deleteProduct(String productMetadataIds);
}
