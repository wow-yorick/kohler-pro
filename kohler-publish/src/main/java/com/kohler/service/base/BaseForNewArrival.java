/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kohler.bean.ConfPrepareData;
import com.kohler.dao.NewProductDao;

/**
 * 新品的公共方法
 *
 * @author Administrator
 * @Date 2014年11月20日
 */
@Component
public class BaseForNewArrival {
    
    @Autowired
    private NewProductDao newProductDao;

    /**
     * 根据配置获取新品
     * @return
     * @author Administrator
     * Date 2014年11月19日
     * @version
     */
    public Map<String, Object> getNewArrival(ConfPrepareData confPrepareData) {
        StringBuilder sql = new StringBuilder("SELECT CATEGORY_METADATA_ID,NEW_ARRIVAL_METADATA_ID,NEW_ARRIVAL_NAME,PC_TEMPLATE_ID,MOBILE_TEMPLATE_ID FROM VW_NEW_ARRIVAL WHERE LANG =");
        
        sql.append(confPrepareData.getLang());
        sql.append(" AND NEW_ARRIVAL_METADATA_ID = ");
        sql.append(confPrepareData.getDataId());
        
        List<Map<String,Object>> resultMap = newProductDao.selectByConditionWithMap(sql.toString(), new ArrayList<Object>());
        return resultMap.get(0);
        
    }
    
}
