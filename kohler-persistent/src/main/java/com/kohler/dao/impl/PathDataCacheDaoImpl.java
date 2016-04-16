/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.dao.impl;

import org.springframework.stereotype.Repository;

import com.kohler.dao.PathDataCacheDao;
import com.kohler.entity.PathDataCacheEntity;

/**
 * Class Function Description
 *
 * @author Administrator
 * @Date 2015年1月14日
 */
@Repository
public class PathDataCacheDaoImpl extends BaseDaoImpl<PathDataCacheEntity> implements PathDataCacheDao {

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteByKey(String key) {
        StringBuffer sql = new StringBuffer("DELETE FROM PATH_DATA_CACHE WHERE KEY_NAME = ?");  
        jdbcTemplate.update(sql.toString() , new Object[] { key });
        
    }
    
}
