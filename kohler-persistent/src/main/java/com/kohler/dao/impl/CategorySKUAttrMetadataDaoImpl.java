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
import com.kohler.dao.CategorySKUAttrMetadataDao;
import com.kohler.dao.utils.DefaultNameHandler;
import com.kohler.dao.utils.DefaultRowMapper;
import com.kohler.entity.CategorySKUAttrMetadataEntity;
import com.kohler.entity.extend.CategorySKUAttrMetadataPojo;

/**
 * Class Function Description
 * CategorySKUAttrMetadataDaoImpl
 * @author zhangtingting
 * @Date 2014年9月29日
 */
@Repository
public class CategorySKUAttrMetadataDaoImpl extends BaseDaoImpl<CategorySKUAttrMetadataEntity> 
    implements CategorySKUAttrMetadataDao {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CategorySKUAttrMetadataPojo getSkuAttrById(
			Integer categorySKUAttrMetadataId) {
		List<Object> queryParams = new ArrayList<Object>();
        queryParams.add(categorySKUAttrMetadataId);
        String sql = CategorySQL.SELECT_CATE_SKU_BY_ID;
        
		return (CategorySKUAttrMetadataPojo) jdbcTemplate.queryForObject(sql,queryParams.toArray(), new DefaultRowMapper(CategorySKUAttrMetadataPojo.class, new DefaultNameHandler()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List getCateSkuAttrByCId(
			Integer categoryMetadataId) {
		List<Object> queryParams = new ArrayList<Object>();
        queryParams.add(categoryMetadataId);
        
        String sql = CategorySQL.SELECT_CATE_SKU_BY_CID;
        
        List<Object> list =jdbcTemplate.query(sql, queryParams.toArray(),
                new DefaultRowMapper(CategorySKUAttrMetadataPojo.class, new DefaultNameHandler()));
        
        return list;
	}

}
