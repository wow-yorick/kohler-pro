/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.test.service.publish;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kohler.bean.PublishConf;
import com.kohler.constants.CommonConstants;
import com.kohler.service.PublishHtmlForInterfaceService;
import com.kohler.service.base.BaseForCategory;
import com.kohler.service.util.DataCacheServiceImplBaseDB;
import com.kohler.util.JSonUtil;

/**
 * 外部发布接口测试
 *
 * @author Administrator
 * @Date 2014年11月21日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/*.xml"})
public class PublishHtmlForInterfaceTest {
    
    @Autowired
    private DataCacheServiceImplBaseDB dataCacheServiceImplBaseDB;
    
    @Autowired
    private PublishHtmlForInterfaceService publishHtmlForInterfaceService;
    
    protected final static Logger logger = Logger.getLogger(PublishHtmlForInterfaceTest.class);
    
    /**
     * 普通页面测试
     * @author Administrator
     * Date 2014年11月19日
     * @version
     */
    @Test
    @Ignore
    public void testRunPageData_PC_CN() {
        PublishConf publishConf = new PublishConf();
        publishConf.setDataId(100008);
        publishConf.setLang(CommonConstants.LOCALE_CN);
        publishConf.setTemplateType(CommonConstants.TEMPLATE_TYPE_PAGE);
        publishConf.setWebsitePlatform(CommonConstants.PC_PLATFORM);
        publishConf.setIsPreview(true);
        publishConf.setTestPlatform("TEST-1");//测试平台1
        Map<String, Object> retMap = new HashMap<String, Object>();
        try {
            retMap = publishHtmlForInterfaceService.run(publishConf);
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug(e.getMessage());
        }
        logger.info(JSonUtil.toJSonString(retMap));
    }
    
    @Test
    @Ignore
    public void testRunPageData_ARTICLE_PC_CN() {
        PublishConf publishConf = new PublishConf();
        publishConf.setDataId(100057);
        publishConf.setLang(CommonConstants.LOCALE_CN);
        publishConf.setTemplateType(CommonConstants.TEMPLATE_TYPE_PAGE);
        publishConf.setWebsitePlatform(CommonConstants.PC_PLATFORM);
        publishConf.setIsPreview(true);
        publishConf.setTestPlatform("TEST-1");//测试平台1
        Map<String, Object> retMap = new HashMap<String, Object>();
        try {
            retMap = publishHtmlForInterfaceService.run(publishConf);
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug(e.getMessage());
        }
        logger.info(JSonUtil.toJSonString(retMap));
    }
    
    @Test
    @Ignore
    public void testRunPageData_PC_EN() {
        PublishConf publishConf = new PublishConf();
        publishConf.setDataId(201);
        publishConf.setLang(CommonConstants.LOCALE_EN);
        publishConf.setTemplateType(CommonConstants.TEMPLATE_TYPE_PAGE);
        publishConf.setWebsitePlatform(CommonConstants.PC_PLATFORM);
        publishConf.setIsPreview(true);
        publishConf.setTestPlatform("TEST-1");//测试平台1
        Map<String, Object> retMap = new HashMap<String, Object>();
        try {
            publishHtmlForInterfaceService.run(publishConf);
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug(e.getMessage());
        }
        logger.info(JSonUtil.toJSonString(retMap));
    }
    
    /**
     * SOLR map JS 页面测试
     * @author Administrator
     * Date 2014年11月19日
     * @version
     */
    @Test
    @Ignore
    public void testRunPageDataForSolrMap() {
        PublishConf publishConf = new PublishConf();
        publishConf.setDataId(501);
        publishConf.setLang(CommonConstants.LOCALE_CN);
        publishConf.setTemplateType(CommonConstants.TEMPLATE_TYPE_PAGE_SOLRMAP);
        publishConf.setWebsitePlatform(CommonConstants.PC_PLATFORM);
        publishConf.setIsPreview(true);
        publishConf.setTestPlatform("TEST-1");//测试平台1
        Map<String, Object> retMap = new HashMap<String, Object>();
        try {
            publishHtmlForInterfaceService.run(publishConf);
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug(e.getMessage());
        }
        logger.info(JSonUtil.toJSonString(retMap));
    }
    

    /**
     * 产品测试
     */
    @Test
    public void testRunProduct_PC_CN() {
        //dataCacheServiceImplBaseDB.hdelAll(BaseForCategory.CATEGORY_BREADCRUMB);
        PublishConf publishConf = new PublishConf();
        publishConf.setDataId(10110002);
        publishConf.setLang(CommonConstants.LOCALE_CN);
        publishConf.setTemplateType(CommonConstants.TEMPLATE_TYPE_PRODUCT);
        publishConf.setWebsitePlatform(CommonConstants.PC_PLATFORM);
        publishConf.setIsPreview(true);
        publishConf.setTestPlatform("TEST-1");//测试平台1
        Map<String, Object> retMap = new HashMap<String, Object>();
        try {
            retMap = publishHtmlForInterfaceService.run(publishConf);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info(JSonUtil.toJSonString(retMap));
    }
    
    @Test
    @Ignore
    public void testRunProduct_PC_EN() {
        PublishConf publishConf = new PublishConf();
        publishConf.setDataId(10110004);
        publishConf.setLang(CommonConstants.LOCALE_EN);
        publishConf.setTemplateType(CommonConstants.TEMPLATE_TYPE_PRODUCT);
        publishConf.setWebsitePlatform(CommonConstants.PC_PLATFORM);
        publishConf.setIsPreview(true);
        publishConf.setTestPlatform("TEST-1");//测试平台1
        Map<String, Object> retMap = new HashMap<String, Object>();
        try {
            retMap = publishHtmlForInterfaceService.run(publishConf);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info(JSonUtil.toJSonString(retMap));
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
        PublishConf publishConf = new PublishConf();
        publishConf.setDataId(10110002);
        publishConf.setLang(CommonConstants.LOCALE_CN);
        publishConf.setTemplateType(CommonConstants.TEMPLATE_TYPE_PRODUCT_SKU_JSFILE);
        publishConf.setWebsitePlatform(CommonConstants.PC_PLATFORM);
        publishConf.setIsPreview(true);
        publishConf.setTestPlatform("TEST-1");//测试平台1
        Map<String, Object> retMap = new HashMap<String, Object>();
        try {
            retMap = publishHtmlForInterfaceService.run(publishConf);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info(JSonUtil.toJSonString(retMap));
    }
    
    /**
     * category 测试
     * 
     * @author Administrator
     * Date 2014年12月1日
     * @version
     */
    @Test
    @Ignore
    public void testRunCategory() {
        PublishConf publishConf = new PublishConf();
        publishConf.setDataId(45);
        publishConf.setLang(CommonConstants.LOCALE_CN);
        publishConf.setTemplateType(CommonConstants.TEMPLATE_TYPE_CATEGORY);
        publishConf.setWebsitePlatform(CommonConstants.PC_PLATFORM);
        publishConf.setIsPreview(true);
        publishConf.setTestPlatform("TEST-1");//测试平台1
        Map<String, Object> retMap = new HashMap<String, Object>();
        try {
            retMap = publishHtmlForInterfaceService.run(publishConf);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info(JSonUtil.toJSonString(retMap));
    }
    
    /**
     * section 测试
     * @author Administrator
     * Date 2014年12月1日
     * @version
     */
    @Test
    @Ignore
    public void testRunSection() {
        PublishConf publishConf = new PublishConf();
        publishConf.setDataId(1);
        publishConf.setLang(CommonConstants.LOCALE_EN);
        publishConf.setTemplateType(CommonConstants.TEMPLATE_TYPE_CATALOG);
        publishConf.setWebsitePlatform(CommonConstants.PC_PLATFORM);
        publishConf.setIsPreview(true);
        publishConf.setTestPlatform("TEST-1");//测试平台1
        Map<String, Object> retMap = new HashMap<String, Object>();
        try {
            retMap = publishHtmlForInterfaceService.run(publishConf);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info(JSonUtil.toJSonString(retMap));
    }
    
    /**
     * 测试套间详情
     * en/pc
     * @author Administrator
     * Date 2014年12月11日
     * @version
     */
    @Test
    @Ignore
    public void testRunSuiteDetail_PC_EN() {
        PublishConf publishConf = new PublishConf();
        publishConf.setDataId(1);
        publishConf.setLang(CommonConstants.LOCALE_EN);
        publishConf.setTemplateType(CommonConstants.TEMPLATE_TYPE_SUITE);
        publishConf.setWebsitePlatform(CommonConstants.PC_PLATFORM);
        publishConf.setIsPreview(true);
        publishConf.setTestPlatform("TEST-1");//测试平台1
        Map<String, Object> retMap = new HashMap<String, Object>();
        try {
            retMap = publishHtmlForInterfaceService.run(publishConf);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info(JSonUtil.toJSonString(retMap));
    }
    
    /**
     * suite detail pc/cn
     * 
     * @author Administrator
     * Date 2014年12月16日
     * @version
     */
    @Test
    @Ignore
    public void testRunSuiteDetail_PC_CN() {
        PublishConf publishConf = new PublishConf();
        publishConf.setDataId(4);
        publishConf.setLang(CommonConstants.LOCALE_CN);
        publishConf.setTemplateType(CommonConstants.TEMPLATE_TYPE_SUITE);
        publishConf.setWebsitePlatform(CommonConstants.PC_PLATFORM);
        publishConf.setIsPreview(true);
        publishConf.setTestPlatform("TEST-1");//测试平台1
        Map<String, Object> retMap = new HashMap<String, Object>();
        try {
            retMap = publishHtmlForInterfaceService.run(publishConf);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info(JSonUtil.toJSonString(retMap));
    }
    
    @Test
    @Ignore
    public void testRunNewArrival_PC_EN() {
        PublishConf publishConf = new PublishConf();
        publishConf.setDataId(1);
        publishConf.setLang(CommonConstants.LOCALE_EN);
        publishConf.setTemplateType(CommonConstants.TEMPLATE_TYPE_NEW_PRODUCT);
        publishConf.setWebsitePlatform(CommonConstants.PC_PLATFORM);
        publishConf.setIsPreview(true);
        publishConf.setTestPlatform("TEST-1");//测试平台1
        Map<String, Object> retMap = new HashMap<String, Object>();
        try {
            retMap = publishHtmlForInterfaceService.run(publishConf);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info(JSonUtil.toJSonString(retMap));
    }
    
    @Test
    @Ignore
    public void testRunPageData_SHZC_PC_CN() {
        PublishConf publishConf = new PublishConf();
        publishConf.setDataId(100057);
        publishConf.setLang(CommonConstants.LOCALE_CN);
        publishConf.setTemplateType(CommonConstants.TEMPLATE_TYPE_PAGE);
        publishConf.setWebsitePlatform(CommonConstants.PC_PLATFORM);
        publishConf.setIsPreview(true);
        publishConf.setTestPlatform("TEST-1");//测试平台1
        Map<String, Object> retMap = new HashMap<String, Object>();
        try {
            retMap = publishHtmlForInterfaceService.run(publishConf);
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug(e.getMessage());
        }
        logger.info(JSonUtil.toJSonString(retMap));
    }

}
