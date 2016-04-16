/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.dao.impl;

import org.springframework.stereotype.Repository;

import com.kohler.constants.SuiteSQL;
import com.kohler.dao.SuiteDao;
import com.kohler.entity.SuiteEntity;


@Repository
public class SuiteDaoImpl extends BaseDaoImpl<SuiteEntity> implements SuiteDao {
    public Integer deleteByMetaDataId(Integer MetadataId){
        return jdbcTemplate.update(SuiteSQL.DELETE_SUITE_BY_METADATA_ID, MetadataId);
    }
    
}
