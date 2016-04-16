/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.kohler.constants.CategoryPathSQL;
import com.kohler.dao.CategoryPathDao;
import com.kohler.dao.utils.DefaultNameHandler;
import com.kohler.dao.utils.DefaultRowMapper;
import com.kohler.entity.CategoryPathEntity;

/**
 * Class Function Description
 * 
 * @author zhangqiqi
 * @Date 2014-11-17
 */
@Repository
public class CategoryPathDaoImpl extends BaseDaoImpl<CategoryPathEntity> implements CategoryPathDao {

    @Override
    public CategoryPathEntity selectByPrimaryKey(Integer categoryId) {
        // TODO Auto-generated method stub
        List<Object> queryParams = new ArrayList<Object>();
        queryParams.add(categoryId);
        return (CategoryPathEntity)jdbcTemplate.queryForObject(CategoryPathSQL.GET_PATH,queryParams.toArray(),new DefaultRowMapper(CategoryPathEntity.class, new DefaultNameHandler()));
    }

    @Override
    public CategoryPathEntity selectFolder(Integer productId) {
        // TODO Auto-generated method stub
        List<Object> queryParams = new ArrayList<Object>();
        queryParams.add(productId);
        return (CategoryPathEntity)jdbcTemplate.queryForObject(CategoryPathSQL.GET_FOLDER_PATH,queryParams.toArray(),new DefaultRowMapper(CategoryPathEntity.class, new DefaultNameHandler()));
    }
}
