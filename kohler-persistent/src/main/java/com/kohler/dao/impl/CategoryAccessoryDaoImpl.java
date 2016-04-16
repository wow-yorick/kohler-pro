/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.kohler.dao.CategoryAccessoryDao;
import com.kohler.dao.utils.DefaultNameHandler;
import com.kohler.dao.utils.DefaultRowMapper;
import com.kohler.entity.CategoryAccessoryEntity;
import com.kohler.entity.CategorySKUAttrEntity;

/**
 * 
 * Class Function Description
 *
 * @author XHY
 * @Date 2014年11月10日
 */
@Repository
public class CategoryAccessoryDaoImpl extends BaseDaoImpl<CategoryAccessoryEntity> implements CategoryAccessoryDao {

    @Override
    public List<CategoryAccessoryEntity> getCategoryAccessoryListByCategoryMetadataId(
            String categoryMetadataId, String lang) {
        List<Object> queryParams = new ArrayList<Object>();
        queryParams.add(categoryMetadataId);
        queryParams.add(lang);
        System.out.println("=========="+lang+":::"+categoryMetadataId);
        StringBuffer sql=new StringBuffer();
        sql.append("SELECT ca.* from CATEGORY_ACCESSORY ca left join CATEGORY_ACCESSORY_METADATA cam on ");
        sql.append("ca.CATEGORY_ACCESSORY_METADATA_ID=cam.CATEGORY_ACCESSORY_METADATA_ID ");
        sql.append("where ca.IS_ENABLE=1 and cam.IS_ENABLE=1 and cam.CATEGORY_METADATA_ID=? and ca.LANG = ? ");
        
        return (List)jdbcTemplate.query(sql.toString(), queryParams.toArray(), new DefaultRowMapper(CategoryAccessoryEntity.class, new DefaultNameHandler()));

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CategoryAccessoryEntity> getCategoryAccessoryListByAccessoryMetadataId(
            String categoryAccessoryMetadataId, String lang) {
        List<Object> queryParams = new ArrayList<Object>();
        queryParams.add(categoryAccessoryMetadataId);
        queryParams.add(lang);
        StringBuffer sql=new StringBuffer();
        sql.append("SELECT * from CATEGORY_ACCESSORY ");
        sql.append("where IS_ENABLE=1 and CATEGORY_ACCESSORY_METADATA_ID=? and LANG = ? ");
        
        return (List)jdbcTemplate.query(sql.toString(), queryParams.toArray(), new DefaultRowMapper(CategoryAccessoryEntity.class, new DefaultNameHandler()));

    }

	


}
