/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kohler.dao.CategorySKUAttrValuesDao;
import com.kohler.dao.utils.DefaultNameHandler;
import com.kohler.dao.utils.DefaultRowMapper;
import com.kohler.entity.CategorySKUAttrValuesEntity;

/**
 * Class Function Description
 * CategorySKUAttrValuesDaoImpl
 * @author zhangtingting
 * @Date 2014年9月29日
 */
@Repository
public class CategorySKUAttrValuesDaoImpl extends BaseDaoImpl<CategorySKUAttrValuesEntity> implements CategorySKUAttrValuesDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    /**
     * {@inheritDoc}
     */
    @Override
    public List<CategorySKUAttrValuesEntity> getSKUAttrValuesListBySKUAttrMetadataId(
            Integer categorySKUAttrMetadataId) {
        List<Object> queryParams = new ArrayList<Object>();
        queryParams.add(categorySKUAttrMetadataId);
        
        StringBuffer sql=new StringBuffer();
        sql.append("SELECT * FROM CATEGORY_SKU_ATTR_VALUES csav left join CATEGORY_SKU_ATTR_VALUES_METADATA csavm on ");
        sql.append("csav.CATEGORY_SKU_ATTR_VALUES_METADATA_ID=csavm.CATEGORY_SKU_ATTR_VALUES_METADATA_ID left join LOCALE l on ");
        sql.append("csav.LANG=l.LOCALE_ID where csav.IS_ENABLE=1 and csavm.IS_ENABLE=1 and l.IS_DEFAULT=1 and l.IS_ENABLE=1 and ");
        sql.append("csavm.CATEGORY_SKU_ATTR_METADATA_ID=?");
        
        return (List)jdbcTemplate.query(sql.toString(), queryParams.toArray(), new DefaultRowMapper(CategorySKUAttrValuesEntity.class, new DefaultNameHandler()));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<CategorySKUAttrValuesEntity> getSKUAttrValuesListBySKUMetadataId(
            Integer skuMetadataId) {
        List<Object> queryParams = new ArrayList<Object>();
        queryParams.add(skuMetadataId);
        
        StringBuffer sql=new StringBuffer("SELECT * FROM CATEGORY_SKU_ATTR_VALUES csav left join CATEGORY_SKU_ATTR_VALUES_METADATA ");
        sql.append("csavm on csav.CATEGORY_SKU_ATTR_VALUES_METADATA_ID=csavm.CATEGORY_SKU_ATTR_VALUES_METADATA_ID left join LOCALE l ");
        sql.append("on csav.LANG=l.LOCALE_ID left join SKU_ATTR sa on csavm.CATEGORY_SKU_ATTR_VALUES_METADATA_ID = sa.CATEGORY_SKU_ATTR_VALUES_METADATA_ID ");
        sql.append("where csav.IS_ENABLE=1 and csavm.IS_ENABLE=1 and l.IS_DEFAULT=1 and l.IS_ENABLE=1 and sa.IS_ENABLE=1 ");
        sql.append("and sa.SKU_METADATA_ID = ?");
        
        return (List)jdbcTemplate.query(sql.toString(), queryParams.toArray(), new DefaultRowMapper(CategorySKUAttrValuesEntity.class, new DefaultNameHandler()));
    }

    
}
