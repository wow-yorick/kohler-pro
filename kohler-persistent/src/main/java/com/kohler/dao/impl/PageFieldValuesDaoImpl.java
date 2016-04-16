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

import com.kohler.constants.PageFieldValuesSQL;
import com.kohler.dao.PageFieldValuesDao;
import com.kohler.entity.PageFieldValuesEntity;

/**
 * page属性dao
 *
 * @author Administrator
 * @Date 2014年10月23日
 */
@Repository
public class PageFieldValuesDaoImpl extends BaseDaoImpl<PageFieldValuesEntity> implements PageFieldValuesDao {
    
    public List<Map<String,Object>> searchByFieldname(String fieldname) {
        List<Object> queryParams = new ArrayList<Object>();
        queryParams.add(fieldname);
        return jdbcTemplate.queryForList(PageFieldValuesSQL.GET_BY_NAME, queryParams.toArray());
    }
    
    public List<Map<String,Object>> searchByTime(String fieldname,String time) {
        List<Object> queryParams = new ArrayList<Object>();
        queryParams.add(fieldname);
        queryParams.add(time);
        return jdbcTemplate.queryForList(PageFieldValuesSQL.GET_BY_TIME, queryParams.toArray());
    }
}
