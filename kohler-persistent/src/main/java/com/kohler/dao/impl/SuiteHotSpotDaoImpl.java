/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.dao.impl;

import org.springframework.stereotype.Repository;

import com.kohler.constants.SuiteSQL;
import com.kohler.dao.SuiteHotSpotDao;
import com.kohler.entity.SuiteHotSpotEntity;

/**
 * Class Function Description
 *
 * @author Administrator
 * @Date 2014年11月28日
 */
@Repository
public class SuiteHotSpotDaoImpl extends BaseDaoImpl<SuiteHotSpotEntity> implements SuiteHotSpotDao {

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer deleteBySuiteMetadataId(Integer suiteMetadataId) {
        return jdbcTemplate.update(SuiteSQL.DELETE_SUITE_HOTSPOT_BY_METADATA_ID, suiteMetadataId);
    }


}
