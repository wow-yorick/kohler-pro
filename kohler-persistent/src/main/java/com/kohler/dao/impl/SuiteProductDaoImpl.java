/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kohler.constants.SuiteSQL;
import com.kohler.dao.SuiteProductDao;
import com.kohler.entity.SuiteProductEntity;


@Repository
public class SuiteProductDaoImpl extends BaseDaoImpl<SuiteProductEntity> implements SuiteProductDao {
    public List<Map<String,Object>> getSuiteProductList(Integer suiteMetadataId) {
        return jdbcTemplate.queryForList(SuiteSQL.SELECT_SUITE_PRODUCT_BY_SUITE_METADATA_ID,suiteMetadataId);
    }
    
    public Integer deleteBySuiteMetadataId(Integer suiteMetadataId) {
        return jdbcTemplate.update(SuiteSQL.DELETE_SUITE_PRODUCT_BY_METADATA_ID, suiteMetadataId);
    }
    
    public List<Map<String,Object>> getSuiteProductDetail(Integer suiteProductId) {
        return jdbcTemplate.queryForList(SuiteSQL.SELECT_SUITE_PRODUCT_DETAIL,new Object[]{suiteProductId});
    }

}
