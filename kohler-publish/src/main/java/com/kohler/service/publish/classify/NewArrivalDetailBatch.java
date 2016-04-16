/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.publish.classify;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kohler.bean.ConfPrepareData;
import com.kohler.constants.CommonConstants;
import com.kohler.dao.CategoryDao;
import com.kohler.service.ClassifyPublishService;
import com.kohler.service.needpublishdata.NewArrivalList;
import com.kohler.service.needpublishdata.ProductList;
import com.kohler.service.publish.PublishHtmlForMulti;

/**
 * 产品批量发布
 *
 * @author Administrator
 * @Date 2014年11月28日
 */
@Component
public class NewArrivalDetailBatch implements ClassifyPublishService {
    
    private final static Logger logger = Logger.getLogger(NewArrivalDetailBatch.class);
    
    @Autowired
    private ProductList productList;
    
    @Autowired
    private CategoryDao categoryDao;
    
    @Autowired
    private PublishHtmlForMulti publishHtmlForMulti;
    
    @Autowired
    private NewArrivalList newArrivalList;
    
    

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Object> batchPublish(Integer keyId, ConfPrepareData conf) {
        long startTime = Calendar.getInstance().getTimeInMillis();
        
        Map<String, Object> retMap = new HashMap<String, Object>();//结果集
        
        int publishFail = 0; //发布失败条数
        List<Integer> dataIdList = new ArrayList<Integer>(); //发布页面数 
        
        StringBuffer runLog = new StringBuffer("new arrival publish--");//batch run log
        StringBuffer successLog = new StringBuffer("[info][success:]");
        StringBuffer failLog = new StringBuffer("[warn][fail:]");
        
        dataIdList = newArrivalList.getPrimaryKeyList(keyId, conf);
        if(dataIdList != null && dataIdList.size() > 0) {
            for(Integer _dataId : dataIdList) {
                try {
                    conf.setDataId(_dataId);
                    conf.setXMLFileName(CommonConstants.XML_FOR_NEW_PRODUCT_PC_CN);
                    publishHtmlForMulti.run(conf);
                    successLog.append(_dataId+",");
                } catch (Exception e) {
                    //logger.warn("new arrival publish fail id "+ _dataId);
                    failLog.append(_dataId+",");
                    ++publishFail;
                }
            }
        }

        int total = dataIdList.size() - publishFail;
        String objResult = "@new arrival nums" + total;
        retMap.put("log", runLog.append(successLog).append(failLog));
        retMap.put("msg", "new arrival publish success!");
        retMap.put("success", true);
        retMap.put("result", objResult);
        
        long endTime = Calendar.getInstance().getTimeInMillis();
        logger.info("NewArrivalDetailBatch time is "+ (endTime-startTime));
        
        return retMap;
    }

}
