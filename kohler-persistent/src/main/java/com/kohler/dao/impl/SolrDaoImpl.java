/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import com.kohler.dao.SolrDao;

/**
 * solr dao implement
 * 
 * @author zqq
 * @Date 10/9/2014
 */

@Repository
public class SolrDaoImpl implements SolrDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public List<Map<String, Object>> selectAll(String str, List list) {
        // TODO Auto-generated method stub
        return jdbcTemplate.queryForList(str);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Integer update(String str) {
        return jdbcTemplate.update(str);
    }

}
