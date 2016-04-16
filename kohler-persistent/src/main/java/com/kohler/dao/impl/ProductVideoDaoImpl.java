/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.kohler.dao.ProductVideoDao;
import com.kohler.dao.utils.DefaultNameHandler;
import com.kohler.dao.utils.DefaultRowMapper;
import com.kohler.entity.ProductCADEntity;
import com.kohler.entity.ProductVideoEntity;

/**
 * Class Function Description
 * ProductVideoDaoImpl
 * @author zhangtingting
 * @Date 2014年9月25日
 */
@Repository
public class ProductVideoDaoImpl extends BaseDaoImpl<ProductVideoEntity> implements ProductVideoDao {

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductVideoEntity getProductVideoById(Integer productVideoMetadataId) {
        List<Object> queryParams = new ArrayList<Object>();
        queryParams.add(productVideoMetadataId);
        
        StringBuilder querySql = new StringBuilder("SELECT * from PRODUCT_VIDEO video left join LOCALE lan on video.LANG=lan.LOCALE_ID ");
        querySql.append("where video.IS_ENABLE=1 and lan.IS_ENABLE=1 AND lan.IS_DEFAULT=1 ");
        querySql.append("and video.PRODUCT_VIDEO_METADATA_ID = ?");
        
        List list=jdbcTemplate.query(querySql.toString(), queryParams.toArray(),
                new DefaultRowMapper(ProductVideoEntity.class, new DefaultNameHandler()));
        if(list.size()>0){
            return (ProductVideoEntity)list.get(0);
        }
        return null;
    }
}
