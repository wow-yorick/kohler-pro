/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.dao;

import com.kohler.entity.ProductVideoEntity;

/**
 * Class Function Description
 * ProductVideoDao
 * @author zhangtingting
 * @Date 2014年9月25日
 */
public interface ProductVideoDao extends BaseDao<ProductVideoEntity>{

    public ProductVideoEntity getProductVideoById(Integer productVideoMetadataId);
}
