/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service;

import java.util.List;

import com.kohler.entity.ProductVideoEntity;

/**
 * Class Function Description
 * ProductVideoService
 * @author ztt
 * @Date 2014年10月11日
 */
public interface ProductVideoService {
    
    public ProductVideoEntity getProductVideoById(Integer productVideoMetadataId);
    
    
    
    public List<ProductVideoEntity> getProductVideoListByEntity(ProductVideoEntity entity);
}
