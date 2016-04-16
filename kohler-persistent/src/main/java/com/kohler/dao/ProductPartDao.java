/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.dao;

import com.kohler.entity.ProductPartEntity;

/**
 * Class Function Description
 * ProcdutPartDao
 * @author zhangtingting
 * @Date 2014年9月25日
 */
public interface ProductPartDao extends BaseDao<ProductPartEntity>{

    public ProductPartEntity getProductPartById(Integer productPartMetadataId);
}
