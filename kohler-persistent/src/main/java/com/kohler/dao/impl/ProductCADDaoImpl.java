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

import com.kohler.dao.ProductCADDao;
import com.kohler.dao.utils.DefaultNameHandler;
import com.kohler.dao.utils.DefaultRowMapper;
import com.kohler.entity.ProductCADEntity;

/**
 * Class Function Description
 * ProductCADDaoImpl
 * @author zhangtingting
 * @Date 2014年9月25日
 */
@Repository
public class ProductCADDaoImpl extends BaseDaoImpl<ProductCADEntity> implements ProductCADDao {

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductCADEntity getProductCADById(Integer productCADMetadataId) {
        List<Object> queryParams = new ArrayList<Object>();
        queryParams.add(productCADMetadataId);
        
        StringBuilder querySql = new StringBuilder("SELECT * from PRODUCT_CAD cad left join LOCALE lan on cad.LANG=lan.LOCALE_ID ");
        querySql.append("where cad.IS_ENABLE=1 and lan.IS_DEFAULT=1 and lan.IS_ENABLE=1 ");
        querySql.append("and cad.PRODUCT_CAD_METADATA_ID= ?");
        
        List<Object> list=jdbcTemplate.query(querySql.toString(), queryParams.toArray(),
                new DefaultRowMapper(ProductCADEntity.class, new DefaultNameHandler()));
        if(list.size()>0){
            return (ProductCADEntity)list.get(0);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Map<String, Object>> getMapList(String sql, Integer lang) {
        return jdbcTemplate.queryForList(sql,new Object[]{lang});
    }
}
