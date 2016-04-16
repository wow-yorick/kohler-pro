/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service;

import java.util.List;

import com.kohler.entity.ProductCADEntity;

/**
 * Class Function Description
 * ProductCADService
 * @author ztt
 * @Date 2014年10月11日
 */
public interface ProductCADService {
    
    public ProductCADEntity getProductCADById(Integer productCADMetadataId);
    
    
    
    public List<ProductCADEntity> getProductCADListByEntity(ProductCADEntity entity);
}
