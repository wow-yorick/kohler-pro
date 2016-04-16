/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.test.service.publish;

import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kohler.bean.ConfPrepareData;
import com.kohler.constants.CommonConstants;
import com.kohler.service.publish.BatchPublishAllServiceImpl;
import com.kohler.service.publish.BatchPublishForAutoSinglePlatform;
import com.kohler.util.JSonUtil;

/**
 * 批量发布测试
 *
 * @author Administrator
 * @Date 2014年11月19日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/*.xml"})
public class BathPublishForAutoTest {
    
    @Autowired
    private BatchPublishForAutoSinglePlatform batchPublishForAuto;
    
    @Autowired
    private BatchPublishAllServiceImpl batchPublishAllServiceImpl;
    
    protected final static Logger logger = Logger.getLogger(BathPublishForAutoTest.class);

    /**
     * Test method for {@link com.kohler.service.publish.BatchPublishForAutoSinglePlatform#start(com.kohler.bean.ConfPrepareData, java.lang.Boolean)}.
     */
    @Test
    @Ignore
    public void testStartCN() {
        ConfPrepareData conf = new ConfPrepareData();
        conf.setLang(CommonConstants.LOCALE_CN);
        conf.setWebsitePlatform(CommonConstants.PC_PLATFORM);
        conf.setTestPlatform("TEST-1");
        conf.setIsPreview(true);
        Map<String,Object> result = batchPublishForAuto.start(conf);
        logger.info(JSonUtil.toJSonString(result));
        
    }
    
    @Test
    @Ignore
    public void testStartEN() {
        ConfPrepareData conf = new ConfPrepareData();
        conf.setLang(CommonConstants.LOCALE_EN);
        conf.setWebsitePlatform(CommonConstants.PC_PLATFORM);
        conf.setTestPlatform("TEST-1");
        conf.setIsPreview(true);
        batchPublishForAuto.start(conf);
    }
    
    @Test
    public void testBatchPublishAll() {
        Map<String, Object> retMap = batchPublishAllServiceImpl.start();
        logger.info(JSonUtil.toJSonString(retMap));
    }

}
