/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.kohler.constants.DbSolrMappingSQL;
import com.kohler.dao.DbSolrMappingDao;
import com.kohler.entity.DbSolrMappingEntity;

/**
 * Class Function Description
 * 
 * @author zhangqiqi
 * @Date 2014-11-17
 */
@Repository
public class DbSolrMappingDaoImpl extends BaseDaoImpl<DbSolrMappingEntity> implements
        DbSolrMappingDao {

    @Override
    public int deleteByCondition(Serializable id) {
        // TODO Auto-generated method stub
        List<Object> queryParams = new ArrayList<Object>();
        queryParams.add(id);
        return jdbcTemplate.update(DbSolrMappingSQL.DELETE_BY_TYPE,queryParams.toArray());
    }

}
