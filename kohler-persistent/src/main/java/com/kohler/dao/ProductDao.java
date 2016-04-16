/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.dao;

import com.kohler.entity.ProductEntity;

/**
 * Class Function Description
 * ProductDao
 * @author zhangtingting
 * @Date 2014年9月25日
 */
public interface ProductDao extends BaseDao<ProductEntity>{

    /**
     * @param productMetadataIds
     * @return
     * @author sana
     * Date 2014年12月16日
     * @version
     */
    int deleteProduct(String productMetadataIds);

}
