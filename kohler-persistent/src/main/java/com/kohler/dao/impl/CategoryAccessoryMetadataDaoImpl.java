/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.kohler.constants.CategorySQL;
import com.kohler.dao.CategoryAccessoryMetadataDao;
import com.kohler.dao.utils.DefaultNameHandler;
import com.kohler.dao.utils.DefaultRowMapper;
import com.kohler.entity.CategoryAccessoryMetadataEntity;
import com.kohler.entity.extend.CategoryAccessoryMetadataPojo;
import com.kohler.entity.extend.CategorySearchKeywordMetadataPojo;

/**
 * 
 * Class Function Description
 *
 * @author XHY
 * @Date 2014年11月10日
 */
@Repository
public class CategoryAccessoryMetadataDaoImpl extends BaseDaoImpl<CategoryAccessoryMetadataEntity> implements CategoryAccessoryMetadataDao {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CategoryAccessoryMetadataPojo getAccessoryPojoById(
			Integer categoryAccessoryMetadataId) {
		List<Object> queryParams = new ArrayList<Object>();
        queryParams.add(categoryAccessoryMetadataId);
        String sql = CategorySQL.SELECT_CATE_ACC_BY_ID;
        
		return (CategoryAccessoryMetadataPojo) jdbcTemplate.queryForObject(sql,queryParams.toArray(), new DefaultRowMapper(CategoryAccessoryMetadataPojo.class, new DefaultNameHandler()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List getCateAccessoryByCId(
			Integer categoryMetadataId) {
		List<Object> queryParams = new ArrayList<Object>();
        queryParams.add(categoryMetadataId);
        
        String sql = CategorySQL.SELECT_CATE_ACC_BY_CID;
        
        List<Object> list =jdbcTemplate.query(sql, queryParams.toArray(),
                new DefaultRowMapper(CategoryAccessoryMetadataPojo.class, new DefaultNameHandler()));
        
        return list;
	}



}
