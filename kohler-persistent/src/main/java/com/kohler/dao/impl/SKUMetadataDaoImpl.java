/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kohler.constants.SKUSQL;
import com.kohler.dao.SKUMetadataDao;
import com.kohler.entity.SKUMetadataEntity;

/**
 * Class Function Description
 * SKUMetadataDaoImpl
 * @author zhangtingting
 * @Date 2014年9月25日
 */
@Repository
public class SKUMetadataDaoImpl extends BaseDaoImpl<SKUMetadataEntity> implements SKUMetadataDao {


    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Object> getFileNamesBySkuId(Integer skuId) {
        List<Map<String, Object>> maplist = jdbcTemplate.queryForList(SKUSQL.SELECT_SKU_FIELD_NAMES,new Object[]{skuId});
        if(maplist!=null&&maplist.size()>0){
            return maplist.get(0);
        }
        return null;
    }

}
