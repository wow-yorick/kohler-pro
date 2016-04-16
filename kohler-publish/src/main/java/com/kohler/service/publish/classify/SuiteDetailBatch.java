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
import com.kohler.service.ClassifyPublishService;
import com.kohler.service.needpublishdata.SuiteList;
import com.kohler.service.publish.PublishHtmlForMulti;

/**
 * suite
 *
 * @author Administrator
 * @Date 2014年11月28日
 */
@Component
public class SuiteDetailBatch implements ClassifyPublishService {
    
    private final static Logger logger = Logger.getLogger(SuiteDetailBatch.class);
    
    @Autowired
    private SuiteList suiteList;
    
    @Autowired
    private PublishHtmlForMulti publishHtmlForMulti;

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Object> batchPublish(Integer keyId, ConfPrepareData conf) {
        long startTime = Calendar.getInstance().getTimeInMillis();
        
        Map<String, Object> retMap = new HashMap<String, Object>();//结果集
        int suitePublishFail = 0; //suite发布失败条数
        List<Integer> suiteIds = new ArrayList<Integer>(); //suite-list 发布产品数
        
        StringBuffer runLog = new StringBuffer("section publish--");//batch run log
        StringBuffer successLog = new StringBuffer("[info][success:]");
        StringBuffer failLog = new StringBuffer("[warn][fail:]");
        //suite 需要发布的id列表
        suiteIds = suiteList.getPrimaryKeyList(keyId, conf);
        if(suiteIds != null && suiteIds.size() > 0) {
            for(Integer _suiteId : suiteIds) {
                try {
                    conf.setDataId(_suiteId);
                    conf.setXMLFileName(CommonConstants.XML_FOR_SUITE);
                    publishHtmlForMulti.run(conf);
                    successLog.append(_suiteId+",");
                } catch (Exception e) {
                    //logger.warn("suite publish fail id "+ _suiteId);
                    failLog.append(_suiteId+",");
                    ++suitePublishFail;
                }
            }
        }
        
        int total = suiteIds.size() - suitePublishFail;
        String objResult = "@suite num" + total;
        retMap.put("log", runLog.append(successLog).append(failLog));
        retMap.put("msg", "suite publish success! suite id "+keyId);
        retMap.put("success", true);
        retMap.put("result", objResult);
        
        long endTime = Calendar.getInstance().getTimeInMillis();
        logger.info("SuiteDetailBatch time is "+ (endTime-startTime));
        
        return retMap;
    }

}
