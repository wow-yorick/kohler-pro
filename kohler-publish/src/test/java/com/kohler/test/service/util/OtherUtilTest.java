/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.test.service.util;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kohler.dao.PageDao;
import com.kohler.service.DataCacheService;
import com.kohler.service.util.DataCacheServiceImpl;
import com.kohler.service.util.DataCacheServiceImplBaseDB;
import com.kohler.util.JSonUtil;

/**
 * Class Function Description
 *
 * @author Administrator
 * @Date 2014年10月27日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/*.xml"})
public class OtherUtilTest {
    
    @Autowired
    private PageDao pageDao;
    
    @Autowired
    private DataCacheServiceImpl dataCacheService;
    
    @Autowired
    private DataCacheServiceImplBaseDB dataCacheServiceImplBaseDB;
    
    private final static Logger logger = Logger.getLogger(OtherUtilTest.class);
    
//    @Autowired
//    ProductListDataServiceImpl productListDataServiceImpl;

    @Test
    @Ignore
    public  void testApplicationPath() {
         URL test = OtherUtilTest.class.getClassLoader().getResource("");
         System.out.println(JSonUtil.toJSonString(test));
         System.out.println(System.getProperty("user.dir"));
         System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));
    }
    
    @Test
    @Ignore
    public void testSqlParams() {
        String sql = "SELECT IF(? = 'pc', SEO_TITLE_PC, SEO_TITLE_MOBILE) AS TITLE FROM VW_PRODUCT WHERE PRODUCT_METADATA_ID=? AND LANG=?";
        List<Object> params = new ArrayList<Object>();
        params.add("pc");
        params.add(10110004);
        params.add(1);
        List<Map<String, Object>> retMapList = pageDao.selectByConditionWithMap(sql, params);
        logger.info(retMapList.toString());
    }
    
    @Test
    @Ignore
    public void testRedis() {
        dataCacheService.hset("qq", "qq", "123");
    }
    
    @Test
    public void testCacheWithDB_GET_SET() {
        dataCacheServiceImplBaseDB.hset("qq", "qq", "123");
        String val = dataCacheServiceImplBaseDB.hget("qq", "qq");
        org.junit.Assert.assertEquals("123", val);
    }
    
    @Test
    @Ignore
    public void testCacheWithDB_DEL_ALL() {
        dataCacheServiceImplBaseDB.hdelAll("qq");
    }
    
    @Test
    public void testCacheWithDB_GETALL() {
        Map<String, String> ret = dataCacheServiceImplBaseDB.hgetAll("qq");
        System.out.println(JSonUtil.toJSonString(ret));
    }

}
