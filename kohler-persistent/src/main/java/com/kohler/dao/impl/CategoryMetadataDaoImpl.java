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
import com.kohler.dao.CategoryMetadataDao;
import com.kohler.dao.utils.DefaultNameHandler;
import com.kohler.dao.utils.DefaultRowMapper;
import com.kohler.entity.CategoryEntity;
import com.kohler.entity.CategoryMetadataEntity;
import com.kohler.entity.extend.CategoryAccessoryMetadataPojo;
import com.kohler.entity.extend.CategoryMetadataPojo;

/**
 * Class Function Description
 *
 * @author XHY
 * @Date 2014年10月9日
 */
@Repository
public class CategoryMetadataDaoImpl extends BaseDaoImpl<CategoryMetadataEntity> implements CategoryMetadataDao {

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
	public CategoryMetadataEntity findCategoryMetadataById(
			Integer categoryMetadataId) {
		
		List<Object> queryParams = new ArrayList<Object>();
        queryParams.add(categoryMetadataId);
        
        String sql = CategorySQL.SELECT_CATE_BY_ID;
        
        Object object =jdbcTemplate.queryForObject(sql, queryParams.toArray(),
                new DefaultRowMapper(CategoryMetadataEntity.class, new DefaultNameHandler()));
        
        return (CategoryMetadataEntity) object;
	}






	/**
	 * {@inheritDoc}
	 */
	@Override
	public List findByPId(Integer categoryMetadataId) {
		List<Object> queryParams = new ArrayList<Object>();
        queryParams.add(categoryMetadataId);
        
        String sql = CategorySQL.SELECT_CATE_BY_PID;
        
        List<Object> list =jdbcTemplate.query(sql, queryParams.toArray(),
                new DefaultRowMapper(CategoryMetadataPojo.class, new DefaultNameHandler()));
        
        return list;
	}






	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Map<String, Object>> getFiledName(List<Object> masterData) {
		StringBuffer listSql = new StringBuffer(" select md.MASTERDATA_METADATA_ID as masterdataId,md.MASTERDATA_NAME as masterdataName "
					+" from MASTERDATA md " 
					+" where md.MASTERDATA_METADATA_ID = ? and md.LANG = ? ");
		List<Map<String, Object>> list = selectByConditionWithMap(listSql.toString(), masterData);
		return list;
	}

}
