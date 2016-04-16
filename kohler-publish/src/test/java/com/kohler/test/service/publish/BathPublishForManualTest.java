/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.test.service.publish;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kohler.bean.ConfPrepareData;
import com.kohler.constants.CommonConstants;
import com.kohler.service.publish.BatchPublishForManualSinglePlatform;

/**
 * 批量发布测试
 *
 * @author Administrator
 * @Date 2014年11月19日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/*.xml"})
public class BathPublishForManualTest {
    
    @Autowired
    BatchPublishForManualSinglePlatform batchPublishServiceImpl;

    /**
     * Test method for {@link com.kohler.service.publish.BatchPublishForAutoSinglePlatform#start(com.kohler.bean.ConfPrepareData, java.lang.Boolean)}.
     */
    @Test
    @Ignore
    public void testStart() {
        ConfPrepareData conf = new ConfPrepareData();
        conf.setIsPreview(true);
        conf.setLang(CommonConstants.LOCALE_CN);
        conf.setWebsitePlatform(CommonConstants.PC_PLATFORM);
        conf.setIsPreview(false);
        batchPublishServiceImpl.start(conf);
    }

}
