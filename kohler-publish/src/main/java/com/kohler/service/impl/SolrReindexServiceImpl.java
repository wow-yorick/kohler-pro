/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kohler.dao.PublishDataPrepareDao;
import com.kohler.entity.PublishLogEntity;
import com.kohler.service.PublishLogRecordService;
import com.kohler.service.SolrReindexService;
import com.kohler.service.SolrService;

/**
 * SOLR REINDEX IMPL
 *
 * @author Administrator
 * @Date 2014年12月30日
 */
@Component
public class SolrReindexServiceImpl implements SolrReindexService {
    
    @Autowired
    private PublishLogRecordService publishLogRecordService;
    
    @Autowired
    private PublishDataPrepareDao publishDataPrepareDao;
    
    
    @Autowired
    private SolrService solrService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() throws Exception {
        //do all reindex
        if(isAllReindex()) {
            solrService.importAllIndex();
        } else {
            PublishLogEntity lockedPLEntity = publishLogRecordService.getLastLocked();
            Date publishStartDate = lockedPLEntity.getStartTime();//the last locked record start time
            solrService.importDeltaIndex(publishStartDate.toString());
        }
        
    }
    
    /**
     * yes or no all reindex
     * @return
     * @author Administrator
     * Date 2014年12月30日
     * @version
     */
    private boolean isAllReindex() {
        //if the first version do all reindex
        if(publishLogRecordService.getHasPublishedLength() <= 0) {
            return true;
        }
        
        PublishLogEntity lockedPLEntity = publishLogRecordService.getLastLocked();
        Date publishStartDate = lockedPLEntity.getStartTime();//the last locked record start time
        
        //if com attr has change
        String modifyTime_category_com_attr =  getLastModifyTime("CATEGORY_COM_ATTR");
        if(publishStartDate != null && modifyTime_category_com_attr != null) {
            int flag = compare_date(modifyTime_category_com_attr, publishStartDate.toString());
            if(flag == 1) {
                return true;
            }
        }
        
        //if CATEGORY_SKU_ATTR has change
        String modifyTime_category_sku_attr =  getLastModifyTime("CATEGORY_SKU_ATTR");
        if(publishStartDate != null && modifyTime_category_com_attr != null) {
            int flag = compare_date(modifyTime_category_sku_attr, publishStartDate.toString());
            if(flag == 1) {
                return true;
            }
        }
        
        //if CATEGORY_SKU_ATTR_VALUES has change
        String modifyTime_category_sku_attr_values =  getLastModifyTime("CATEGORY_SKU_ATTR_VALUES");
        if(publishStartDate != null && modifyTime_category_com_attr != null) {
            int flag = compare_date(modifyTime_category_sku_attr_values, publishStartDate.toString());
            if(flag == 1) {
                return true;
            }
        }
        
        return false;
        
    }
    
    /**
     * 获取指定表的最后更新时间
     * @param tableName
     * @return
     * @author Administrator
     * Date 2014年12月30日
     * @version
     */
    private String getLastModifyTime(String tableName) {
        String modifyTime = null;
        List<Object> params = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer("SELECT MAX(MODIFY_TIME) MODIFY_TIME FROM "+tableName);
        List<Map<String, Object>> retList = publishDataPrepareDao.selectByConditionWithMap(sql.toString(), params);
        if(retList != null && retList.size() > 0){
            modifyTime =  (String) retList.get(0).get("MODIFY_TIME");
        }
        return modifyTime;
    }
    
    /**
     * 时间比较
     * @param DATE1
     * @param DATE2
     * @return
     * @author Administrator
     * Date 2014年12月30日
     * @version
     */
    public static int compare_date(String DATE1, String DATE2) {
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            return 0;
        }
    }

}
