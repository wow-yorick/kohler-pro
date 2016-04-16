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
import com.kohler.dao.CategorySearchKeywordMetadataDao;
import com.kohler.dao.utils.DefaultNameHandler;
import com.kohler.dao.utils.DefaultRowMapper;
import com.kohler.entity.CategorySearchKeywordMetadataEntity;
import com.kohler.entity.extend.CategorySearchKeywordMetadataPojo;

/**
 * 
 * Class Function Description
 *
 * @author XHY
 * @Date 2014年11月10日
 */
@Repository
public class CategorySearchKeywordMetadataDaoImpl extends BaseDaoImpl<CategorySearchKeywordMetadataEntity> implements CategorySearchKeywordMetadataDao {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CategorySearchKeywordMetadataPojo getSkuAttrById(
			Integer categorySearchKeyMetadataId) {
		List<Object> queryParams = new ArrayList<Object>();
        queryParams.add(categorySearchKeyMetadataId);
        String sql = CategorySQL.SELECT_CATE_SEARCH_BY_ID;
        
		return (CategorySearchKeywordMetadataPojo) jdbcTemplate.queryForObject(sql,queryParams.toArray(), new DefaultRowMapper(CategorySearchKeywordMetadataPojo.class, new DefaultNameHandler()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List getCateSearchKeyByCId(
			Integer categoryMetadataId) {
		List<Object> queryParams = new ArrayList<Object>();
        queryParams.add(categoryMetadataId);
        
        String sql = CategorySQL.SELECT_CATE_SEARCH_BY_CID;
        
        List<Object> list =jdbcTemplate.query(sql, queryParams.toArray(),
                new DefaultRowMapper(CategorySearchKeywordMetadataPojo.class, new DefaultNameHandler()));
        
        return list;
	}


}
