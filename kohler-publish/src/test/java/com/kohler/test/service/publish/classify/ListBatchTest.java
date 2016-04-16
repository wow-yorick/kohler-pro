/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.test.service.publish.classify;

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
import com.kohler.service.publish.classify.CategoryListBatch;
import com.kohler.service.publish.classify.NewArrivalDetailBatch;
import com.kohler.service.publish.classify.PageListBatch;
import com.kohler.service.publish.classify.SuiteDetailBatch;

/**
 * page 批量
 *
 * @author Administrator
 * @Date 2014年12月10日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/*.xml"})
public class ListBatchTest {
    
    private final static Logger logger = Logger.getLogger(ListBatchTest.class);
    
    @Autowired
    private PageListBatch pageListBatch;
    
    @Autowired
    private CategoryListBatch categoryListBatch;
    
    @Autowired
    private NewArrivalDetailBatch newArrivalDetailBatch;
    
    @Autowired
    private SuiteDetailBatch suiteDetailBatch;

    /**
     * page list batch test CN/PC
     */
    @Test
    public void testBatchPublishPageList() {
        ConfPrepareData conf = new ConfPrepareData();
        conf.setLang(CommonConstants.LOCALE_CN);
        conf.setWebsitePlatform(CommonConstants.PC_PLATFORM);
        conf.setTestPlatform("TEST-1");
        Map<String, Object> retMap = pageListBatch.batchPublish(new Integer(0), conf);
        logger.info(retMap.toString());
    }
    
    @Test
    @Ignore
    public void testBatchPublishPageList_PC_EN() {
        ConfPrepareData conf = new ConfPrepareData();
        conf.setLang(CommonConstants.LOCALE_EN);
        conf.setWebsitePlatform(CommonConstants.PC_PLATFORM);
        conf.setTestPlatform("TEST-1");
        pageListBatch.batchPublish(new Integer(0), conf);
    }
    
    /**
     * category list test
     * 
     * @author Administrator
     * Date 2014年12月10日
     * @version
     */
    @Test
    @Ignore
    public void testBatchPublishCategoryList_PC_CN() {
        ConfPrepareData conf = new ConfPrepareData();
        conf.setLang(CommonConstants.LOCALE_CN);
        conf.setWebsitePlatform(CommonConstants.PC_PLATFORM);
        conf.setTestPlatform("TEST-1");
        categoryListBatch.batchPublish(new Integer(61), conf);
    }
    
    /**
     * new product list
     * 
     * @author Administrator
     * Date 2014年12月11日
     * @version
     */
    @Test
    @Ignore
    public void testBatchPublishNewArrivalList_PC_CN() {
        ConfPrepareData conf = new ConfPrepareData();
        conf.setLang(CommonConstants.LOCALE_CN);
        conf.setWebsitePlatform(CommonConstants.PC_PLATFORM);
        conf.setTestPlatform("TEST-1");
        newArrivalDetailBatch.batchPublish(new Integer(0), conf);
    }
    
    @Test
    @Ignore
    public void testBatchPublishSuiteDetail_PC_CN() {
        ConfPrepareData conf = new ConfPrepareData();
        conf.setLang(CommonConstants.LOCALE_CN);
        conf.setWebsitePlatform(CommonConstants.PC_PLATFORM);
        conf.setTestPlatform("TEST-1");
        suiteDetailBatch.batchPublish(new Integer(0), conf);
    }

}
