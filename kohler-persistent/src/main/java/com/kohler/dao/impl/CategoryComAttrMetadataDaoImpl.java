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
import com.kohler.dao.CategoryComAttrMetadataDao;
import com.kohler.dao.utils.DefaultNameHandler;
import com.kohler.dao.utils.DefaultRowMapper;
import com.kohler.entity.CategoryComAttrMetadataEntity;
import com.kohler.entity.extend.CategoryComAttrMetadataPojo;

/**
 * Class Function Description
 * CategoryComAttrMetadataDaoImpl
 * @author zhangtingting
 * @Date 2014年9月25日
 */
@Repository
public class CategoryComAttrMetadataDaoImpl extends BaseDaoImpl<CategoryComAttrMetadataEntity> 
    implements CategoryComAttrMetadataDao {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List getCateComAttrByCId(
			Integer categoryMetadataId) {
		List<Object> queryParams = new ArrayList<Object>();
        queryParams.add(categoryMetadataId);
        
        String sql = CategorySQL.SELECT_CATE_COM_BY_CID;
//        StringBuffer sql=new StringBuffer("select c.* from CATEGORY c  where c.IS_ENABLE=1 ");
//        sql.append(" and  c.CATEGORY_METADATA_ID=?");
        
        List<Object> list =jdbcTemplate.query(sql, queryParams.toArray(),
                new DefaultRowMapper(CategoryComAttrMetadataPojo.class, new DefaultNameHandler()));
        
        return list;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CategoryComAttrMetadataPojo getCateComAttrById(
			Integer categoryComAttrMetadataId) {
		List<Object> queryParams = new ArrayList<Object>();
        queryParams.add(categoryComAttrMetadataId);
        String sql = CategorySQL.SELECT_CATE_COM_BY_ID;
        
		return (CategoryComAttrMetadataPojo) jdbcTemplate.queryForObject(sql,queryParams.toArray(), new DefaultRowMapper(CategoryComAttrMetadataPojo.class, new DefaultNameHandler()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getDropDownType() {
		
		String sql = "SELECT MASTERDATA_TYPE_NAME FROM MASTERDATA_TYPE WHERE IS_ENABLE = 1";
		
		return jdbcTemplate.queryForList(sql, String.class);
	}

	

}
