/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.test.service.publish;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kohler.bean.ConfPrepareData;
import com.kohler.constants.CommonConstants;
import com.kohler.service.publish.PublishHtmlForSingle;

/**
 * 测试页面发布
 *
 * @author Administrator
 * @Date 2014年10月22日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/*.xml"})
public class PublishHtmlServiceTest {
    
    @Autowired
    PublishHtmlForSingle publishHtmlServiceImpl;
    
    protected final static Logger logger = Logger.getLogger(PublishHtmlServiceTest.class);
    
    /**
     * 普通页面测试
     * 
     * @author Administrator
     * Date 2014年11月19日
     * @version
     */
    @Test
    public void testRunPageData() {
        ConfPrepareData confPrepareData = new ConfPrepareData();
        confPrepareData.setDataId(2);
        confPrepareData.setLang(CommonConstants.LOCALE_CN);
        confPrepareData.setXMLFileName(CommonConstants.XML_FOR_PAGE);
        confPrepareData.setWebsitePlatform(CommonConstants.PC_PLATFORM);
        confPrepareData.setIsPreview(true);
        confPrepareData.setTestPlatform("TEST-1");
        try {
            publishHtmlServiceImpl.run(confPrepareData);
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug(e.getMessage());
        }
    }
    
    /**
     * section 测试
     * 卫浴产品
     * @author Administrator
     * Date 2014年11月19日
     * @version
     */
    @Test
    @Ignore
    public void testRunSectionData() {
        ConfPrepareData confPrepareData = new ConfPrepareData();
        confPrepareData.setDataId(101000000);
        confPrepareData.setLang(CommonConstants.LOCALE_CN);
        confPrepareData.setXMLFileName(CommonConstants.XML_FOR_SECTION_PC_CN);
        confPrepareData.setWebsitePlatform(CommonConstants.PC_PLATFORM);
        confPrepareData.setIsPreview(true);
        confPrepareData.setTestPlatform("TEST-1");
        try {
            publishHtmlServiceImpl.run(confPrepareData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * category 测试
     * 
     * @author Administrator
     * Date 2014年11月19日
     * @version
     */
    @Test
    @Ignore
    public void testRunCategoryData() {
        ConfPrepareData confPrepareData = new ConfPrepareData();
        confPrepareData.setDataId(101001001);
        confPrepareData.setLang(CommonConstants.LOCALE_CN);
        confPrepareData.setXMLFileName(CommonConstants.XML_FOR_CATEGORY_PC_CN);
        confPrepareData.setWebsitePlatform(CommonConstants.PC_PLATFORM);
        confPrepareData.setIsPreview(true);
        confPrepareData.setTestPlatform("TEST-1");
        try {
            publishHtmlServiceImpl.run(confPrepareData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 产品详情测试
     * 
     * @author Administrator
     * Date 2014年11月19日
     * @version
     */
    @Test
    @Ignore
    public void testproductdetailData() {
        ConfPrepareData confPrepareData = new ConfPrepareData();
        confPrepareData.setDataId(3);
        confPrepareData.setLang(CommonConstants.LOCALE_CN);
        confPrepareData.setXMLFileName(CommonConstants.XML_FOR_PRODUCT);
        confPrepareData.setWebsitePlatform(CommonConstants.PC_PLATFORM);
        confPrepareData.setIsPreview(true);
        confPrepareData.setTestPlatform("TEST-1");
        try {
            publishHtmlServiceImpl.run(confPrepareData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * product js file 
     * @author Administrator
     * Date 2014年11月19日
     * @version
     */
    @Test
    @Ignore
    public void testproductdetailJSFileData() {
        ConfPrepareData confPrepareData = new ConfPrepareData();
        confPrepareData.setDataId(11301002);
        confPrepareData.setLang(CommonConstants.LOCALE_CN);
        confPrepareData.setXMLFileName(CommonConstants.XML_FOR_PRODUCT_SKU_JSFILE);
        confPrepareData.setWebsitePlatform(CommonConstants.PC_PLATFORM);
        confPrepareData.setIsPreview(true);
        confPrepareData.setTestPlatform("TEST-1");
        try {
            publishHtmlServiceImpl.run(confPrepareData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 卫浴产品测试
     * 
     * @author Administrator
     * Date 2014年11月19日
     * @version
     */
    @Test
    @Ignore
    public void testproductdetailDataWY() {
        ConfPrepareData confPrepareData = new ConfPrepareData();
        confPrepareData.setDataId(156);
        confPrepareData.setLang(CommonConstants.LOCALE_CN);
        confPrepareData.setXMLFileName(CommonConstants.XML_FOR_PRODUCT);
        confPrepareData.setWebsitePlatform(CommonConstants.PC_PLATFORM);
        confPrepareData.setIsPreview(true);
        confPrepareData.setTestPlatform("TEST-1");
        try {
            publishHtmlServiceImpl.run(confPrepareData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 新品测试
     * 
     * @author Administrator
     * Date 2014年11月19日
     * @version
     */
    @Test
    @Ignore
    public void testNewProduct() {
        ConfPrepareData confPrepareData = new ConfPrepareData();
        confPrepareData.setDataId(8);
        confPrepareData.setLang(CommonConstants.LOCALE_CN);
        confPrepareData.setXMLFileName(CommonConstants.XML_FOR_NEW_PRODUCT_PC_CN);
        confPrepareData.setWebsitePlatform(CommonConstants.PC_PLATFORM);
        confPrepareData.setIsPreview(true);
        confPrepareData.setTestPlatform("TEST-1");
        try {
            publishHtmlServiceImpl.run(confPrepareData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 套间列表查询
     * @author Administrator
     * Date 2014年11月20日
     * @version
     */
    @Test
    @Ignore
    public void testSuiteList() {
        ConfPrepareData confPrepareData = new ConfPrepareData();
        confPrepareData.setDataId(103);
        confPrepareData.setLang(CommonConstants.LOCALE_CN);
        confPrepareData.setXMLFileName(CommonConstants.XML_FOR_SUITE_LIST);
        confPrepareData.setWebsitePlatform(CommonConstants.PC_PLATFORM);
        confPrepareData.setIsPreview(true);
        confPrepareData.setTestPlatform("TEST-1");
        try {
            publishHtmlServiceImpl.run(confPrepareData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 套间列表详情
     * @author Administrator
     * Date 2014年11月20日
     * @version
     */
    @Test
    @Ignore
    public void testSuiteDetail() {
        ConfPrepareData confPrepareData = new ConfPrepareData();
        confPrepareData.setDataId(103);
        confPrepareData.setLang(CommonConstants.LOCALE_CN);
        confPrepareData.setXMLFileName(CommonConstants.XML_FOR_SUITE);
        confPrepareData.setWebsitePlatform(CommonConstants.PC_PLATFORM);
        confPrepareData.setIsPreview(true);
        confPrepareData.setTestPlatform("TEST-1");
        try {
            publishHtmlServiceImpl.run(confPrepareData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
