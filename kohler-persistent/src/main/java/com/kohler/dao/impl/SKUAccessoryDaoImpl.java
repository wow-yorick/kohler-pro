/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.kohler.dao.SKUAccessoryDao;
import com.kohler.dao.utils.DefaultNameHandler;
import com.kohler.dao.utils.DefaultRowMapper;
import com.kohler.entity.SKUAccessoryEntity;

/**
 * Class Function Description
 *
 * @author sana
 * @Date 2014年11月24日
 */
@Repository
public class SKUAccessoryDaoImpl extends BaseDaoImpl<SKUAccessoryEntity> implements SKUAccessoryDao {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SKUAccessoryEntity> getSKUAccessoryBySKUId(String skuMetadataId, String lang, String categoryAccessoryMetadataId) {
        List<Object> queryParams = new ArrayList<Object>();
        queryParams.add(skuMetadataId);
        queryParams.add(lang);
        queryParams.add(categoryAccessoryMetadataId);
        StringBuffer sql=new StringBuffer();
        sql.append("SELECT * from SKU_ACCESSORY ");
        sql.append("where IS_ENABLE=1 and SKU_METADATA_ID=? and LANG = ? and CATEGORY_ACCESSORY_METADATA_ID = ?");
        
        return (List)jdbcTemplate.query(sql.toString(), queryParams.toArray(), new DefaultRowMapper(SKUAccessoryEntity.class, new DefaultNameHandler()));

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SKUAccessoryEntity> getAccessoryBySKUMetadataId(String skuMetadataId) {
        List<Object> queryParams = new ArrayList<Object>();
        queryParams.add(skuMetadataId);
        StringBuffer sql=new StringBuffer();
        sql.append("SELECT * from SKU_ACCESSORY ");
        sql.append("where IS_ENABLE=1 and SKU_METADATA_ID=?");
        
        return (List)jdbcTemplate.query(sql.toString(), queryParams.toArray(), new DefaultRowMapper(SKUAccessoryEntity.class, new DefaultNameHandler()));

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int updateAllAccessoryFalseByskuMetadataId(Integer skuMetadataId) {
        StringBuffer sql=new StringBuffer();
        sql.append("update SKU_ACCESSORY set IS_ENABLE = 0 ");
        sql.append("where IS_ENABLE=1 and SKU_METADATA_ID="+skuMetadataId);
        jdbcTemplate.execute(sql.toString());
        return 1;
    }

    
}
