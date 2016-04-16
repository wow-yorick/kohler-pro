/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.needpublishdata;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kohler.bean.ConfPrepareData;
import com.kohler.dao.SuiteMetadataDao;
import com.kohler.service.NeedPublishDataService;

/**
 * 套间列表
 *
 * @author Administrator
 * @Date 2014年11月28日
 */
@Component
public class SuiteList implements NeedPublishDataService {
    
    @Autowired
    private SuiteMetadataDao suiteMetadataDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Integer> getPrimaryKeyList(Integer keyId, ConfPrepareData conf) {
        List<Integer> retList = new ArrayList<Integer>();
        
        StringBuilder sql = new StringBuilder("SELECT SUITE_METADATA_ID FROM VW_SUITE WHERE IS_ENABLE = ? AND LANG = ?");
        List<Object> params = new ArrayList<Object>();
        params.add(true);
        params.add(conf.getLang());
        List<Map<String,Object>> suiteList = suiteMetadataDao.selectByConditionWithMap(sql.toString(), params);
        if(suiteList != null && suiteList.size() > 0) {
            for(Map<String,Object> suite : suiteList) { 
                Object suiteId = suite.get("SUITE_METADATA_ID");
                retList.add(Integer.valueOf(suiteId.toString()));
            }            
        }
        return retList;
    }

}
