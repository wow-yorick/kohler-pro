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

import com.kohler.constants.CategorySQL;
import com.kohler.dao.CategoryDao;
import com.kohler.dao.utils.DefaultNameHandler;
import com.kohler.dao.utils.DefaultRowMapper;
import com.kohler.entity.CategoryEntity;
import com.kohler.entity.extend.CategoryMetadataPojo;

/**
 * Class Function Description
 *
 * @author XHY
 * @Date 2014年10月9日
 */
@Repository
public class CategoryDaoImpl extends BaseDaoImpl<CategoryEntity> implements CategoryDao {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Map<String, Object>> selectCategorys() {
        return jdbcTemplate.queryForList(CategorySQL.SELECT_CATEGORYS_WITH_MAP);
    }

    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public CategoryEntity getCategoryByCategoryMetadataId(Integer categoryMetadataId) {
        List<Object> queryParams = new ArrayList<Object>();
        queryParams.add(categoryMetadataId);
        
        String sql = CategorySQL.SELECT_CATEGORY_BY_MID;
        
        List<Object> list =jdbcTemplate.query(sql, queryParams.toArray(),
                new DefaultRowMapper(CategoryEntity.class, new DefaultNameHandler()));
        CategoryEntity category = new CategoryEntity();
        if(list!=null&&list.size()>0){
        	category = (CategoryEntity)list.get(0);
        }
        return category;
    }



	/**
	 * {@inheritDoc}
	 */
	@Override
	public CategoryMetadataPojo findCategoryMetadataById(
			Integer categoryMetadataId) {
		
		List<Object> queryParams = new ArrayList<Object>();
        queryParams.add(categoryMetadataId);
        
        String sql = CategorySQL.SELECT_CATE_BY_ID;
        
        Object object =jdbcTemplate.queryForObject(sql, queryParams.toArray(),
                new DefaultRowMapper(CategoryMetadataPojo.class, new DefaultNameHandler()));
        
        return (CategoryMetadataPojo) object;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public List getCategorysByCategoryMetadataId(
			Integer categoryMetadataId) {
		List<Object> queryParams = new ArrayList<Object>();
        queryParams.add(categoryMetadataId);
        
        String sql = CategorySQL.SELECT_CATEGORY_BY_MID2;
        
        List<Object> list =jdbcTemplate.query(sql, queryParams.toArray(),
                new DefaultRowMapper(CategoryEntity.class, new DefaultNameHandler()));
        
        return list;
	}

}
