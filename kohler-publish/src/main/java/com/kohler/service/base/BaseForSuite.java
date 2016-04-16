/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kohler.bean.ConfPrepareData;
import com.kohler.dao.SuiteMetadataDao;

/**
 * suite公共方法
 *
 * @author Administrator
 * @Date 2014年11月20日
 */
@Component
public class BaseForSuite {
    
    @Autowired
    SuiteMetadataDao suiteMetadataDao;
    
    /**
     * 获取产品信息
     * @return
     * @author Administrator
     * Date 2014年10月28日
     * @version
     */
    public Map<String,Object> getSuiteInfoWithMap(ConfPrepareData confPrepareData) {
        StringBuilder sql = new StringBuilder("SELECT CATEGORY_METADATA_ID,SUITE_METADATA_ID,SUITE_NAME,PC_TEMPLATE_ID,MOBILE_TEMPLATE_ID FROM VW_SUITE WHERE ");
        sql.append(" LANG = ?");
        sql.append(" AND SUITE_METADATA_ID = ?");
        
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(confPrepareData.getLang());
        params.add(confPrepareData.getDataId());
        List<Map<String, Object>> retMapList = suiteMetadataDao.selectByConditionWithMap(sql.toString(), params);
        if(retMapList != null && retMapList.size() > 0) {
            return retMapList.get(0);            
        } else {
            return new HashMap<String, Object>();
        }
    }
}
