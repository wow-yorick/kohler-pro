/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.dao.impl;

import org.springframework.stereotype.Repository;

import com.kohler.constants.ProductSQL;
import com.kohler.dao.ProductMetadataDao;
import com.kohler.entity.ProductMetadataEntity;

/**
 * Class Function Description
 * ProductMetadataDaoImpl
 * @author zhangtingting
 * @Date 2014年9月25日
 */
@Repository
public class ProductMetadataDaoImpl extends BaseDaoImpl<ProductMetadataEntity> implements ProductMetadataDao {

    /**
     * {@inheritDoc}
     */
    @Override
    public int deleteProduct(String productMetadataIds) {
        return jdbcTemplate.update(ProductSQL.DELETE_PRODUCT_METADATA_BY_METADATA_ID, productMetadataIds);
    }


}
