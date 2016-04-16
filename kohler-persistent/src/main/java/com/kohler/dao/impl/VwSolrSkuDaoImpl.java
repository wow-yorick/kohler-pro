/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kohler.constants.VwSolrSkuSQL;
import com.kohler.dao.VwSolrSkuDao;
import com.kohler.entity.VwSolrSkuEntity;

/**
 * Class Function Description
 * 
 * @author zhangqiqi
 * @Date 2014-11-17
 */
@Repository
public class VwSolrSkuDaoImpl extends BaseDaoImpl<VwSolrSkuEntity> implements VwSolrSkuDao {
    public List<Map<String,Object>> getAll(){
        List<Object> queryParams = new ArrayList<Object>();
        return jdbcTemplate.queryForList(VwSolrSkuSQL.GET_ALL, queryParams.toArray());
    }
    
    public List<Map<String,Object>> getByTime(String start){
        List<Object> queryParams = new ArrayList<Object>();
        queryParams.add(start);
        return jdbcTemplate.queryForList(VwSolrSkuSQL.GET_BY_TIME, queryParams.toArray());
    }
}
