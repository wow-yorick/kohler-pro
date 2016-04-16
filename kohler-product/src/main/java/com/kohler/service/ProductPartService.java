/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service;

import java.util.List;

import com.kohler.entity.ProductPartEntity;

/**
 * Class Function Description
 * ProductPartService
 * @author ztt
 * @Date 2014年10月11日
 */
public interface ProductPartService {
    
    public ProductPartEntity getProductPartById(Integer productPartMetadataId);
    
    
    public List<ProductPartEntity> getProductPartListByEntity(ProductPartEntity entity);
}
