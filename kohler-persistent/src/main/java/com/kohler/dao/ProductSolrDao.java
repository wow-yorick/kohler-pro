/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.dao;

import com.kohler.entity.ProductSolrEntity;

/**
 * Class Function Description
 * ProductDao
 * @author zhangtingting
 * @Date 2014年9月25日
 */
public interface ProductSolrDao extends BaseDao<ProductSolrEntity>{
    public ProductSolrEntity selectByKeyId(Integer productSolrEntityId);
}
