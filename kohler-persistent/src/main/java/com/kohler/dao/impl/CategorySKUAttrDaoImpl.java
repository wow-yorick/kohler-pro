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

import com.kohler.dao.CategorySKUAttrDao;
import com.kohler.dao.utils.DefaultNameHandler;
import com.kohler.dao.utils.DefaultRowMapper;
import com.kohler.entity.CategorySKUAttrEntity;

/**
 * Class Function Description
 * CategorySKUAttrDaoImpl
 * @author zhangtingting
 * @Date 2014年9月29日
 */
@Repository
public class CategorySKUAttrDaoImpl extends BaseDaoImpl<CategorySKUAttrEntity> implements CategorySKUAttrDao {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CategorySKUAttrEntity> getCategorySKUAttrListByCategoryMetadataId(
            Integer categoryMetadataId) {
        List<Object> queryParams = new ArrayList<Object>();
        queryParams.add(categoryMetadataId);
        
        StringBuffer sql=new StringBuffer();
        sql.append("SELECT csa.* from CATEGORY_SKU_ATTR csa left join CATEGORY_SKU_ATTR_METADATA csam on ");
        sql.append("csa.CATEGORY_SKU_ATTR_METADATA_ID=csam.CATEGORY_SKU_ATTR_METADATA_ID left join LOCALE l on csa.LANG=l.LOCALE_ID ");
        sql.append("where csa.IS_ENABLE=1 and csam.IS_ENABLE=1 and l.IS_ENABLE=1 and l.IS_DEFAULT=1 and csam.CATEGORY_METADATA_ID=?");
        
        return (List)jdbcTemplate.query(sql.toString(), queryParams.toArray(), new DefaultRowMapper(CategorySKUAttrEntity.class, new DefaultNameHandler()));
    }


}
