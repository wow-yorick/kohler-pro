/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kohler.dao.PathDataCacheDao;
import com.kohler.entity.PathDataCacheEntity;
import com.kohler.service.DataCacheService;

/**
 * DB CACHE
 *
 * @author Administrator
 * @Date 2015年1月9日
 */
@Component
public class DataCacheServiceImplBaseDB implements DataCacheService {
    
    @Autowired
    private PathDataCacheDao pathDataCacheDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public String hget(String key, String field) {
        PathDataCacheEntity pdce = new PathDataCacheEntity();
        pdce.setKeyName(key);
        pdce.setFieldName(field);
        pdce.setIsEnable(true);
        List<PathDataCacheEntity> retMap = pathDataCacheDao.selectByCondition(pdce);
        if(retMap != null && retMap.size() > 0) {
            return (String) retMap.get(0).getFieldValue();
        } else {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hset(String key, String field, String value) {
        PathDataCacheEntity pdce = new PathDataCacheEntity();
        pdce.setKeyName(key);
        pdce.setFieldName(field);
        pdce.setFieldValue(value);
        pathDataCacheDao.insert(pdce);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hdelAll(String key) {
        pathDataCacheDao.deleteByKey(key);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, String> hgetAll(String key) {
        Map<String, String> retMap = new HashMap<String, String>();
        StringBuffer sql = new StringBuffer("SELECT `KEY_NAME`,`FIELD_VALUE` FROM PATH_DATA_CACHE WHERE KEY_NAME LIKE ?");
        List<Object> params = new ArrayList<Object>();
        params.add("%"+key+"%");
        List<Map<String,Object>> retMapList = pathDataCacheDao.selectByConditionWithMap(sql.toString(), params);
        if(retMapList != null && retMapList.size() > 0) {
            for(Map<String,Object> _tmpMap : retMapList) {
                retMap.put((String)_tmpMap.get("KEY_NAME"), (String) _tmpMap.get("FIELD_VALUE"));
            }
        }
        return retMap;
    }

}
