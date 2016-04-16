/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.dao.impl;

import org.springframework.stereotype.Repository;

import com.kohler.constants.ProductSQL;
import com.kohler.dao.ProductDao;
import com.kohler.entity.ProductEntity;

/**
 * Class Function Description
 * ProductDaoImpl
 * @author zhangtingting
 * @Date 2014年9月25日
 */
@Repository
public class ProductDaoImpl extends BaseDaoImpl<ProductEntity> implements ProductDao {

    /**
     * {@inheritDoc}
     */
    @Override
    public int deleteProduct(String productMetadataIds) {
        return jdbcTemplate.update(ProductSQL.DELETE_PRODUCT_BY_METADATA_ID, productMetadataIds);
    }


}
