/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service;

import java.util.List;

import com.kohler.entity.SKUAttrEntity;

/**
 * Class Function Description
 * SKUAttrService
 * @author ztt
 * @Date 2014年10月11日
 */
public interface SKUAttrService {
    
    public List<SKUAttrEntity> getSKUAttrListBySKUMetadataId(Integer skuMetadataId);
}
