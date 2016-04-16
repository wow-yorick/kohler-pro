/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.test.service.needpublishdata;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kohler.bean.ConfPrepareData;
import com.kohler.constants.CommonConstants;
import com.kohler.service.needpublishdata.ProductList;
import com.kohler.util.JSonUtil;

/**
 * 获取产品发布的列表
 *
 * @author Administrator
 * @Date 2014年11月17日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/*.xml"})
public class ProductListTest {
    protected final static Logger logger = Logger.getLogger(ProductListTest.class);
    
    @Autowired
    ProductList productList;

    /**
     * 卫浴
     */
    @Test
    @Ignore
    public void testGetPrimaryKeyListWY() {
        ConfPrepareData conf = new ConfPrepareData();
        conf.setLang(CommonConstants.LOCALE_CN);
        List<Integer> product = productList.getPrimaryKeyList(101000000, conf);
        logger.debug("productList"+JSonUtil.toJSonString(product));
        
    }
    
    /**
     * 厨房
     * 
     * @author Administrator
     * Date 2014年11月17日
     * @version
     */
    @Test
    @Ignore
    public void testGetPrimaryKeyListCF() {
        ConfPrepareData conf = new ConfPrepareData();
        conf.setLang(CommonConstants.LOCALE_CN);
        List<Integer> product = productList.getPrimaryKeyList(102000000, conf);
        logger.debug("productList"+JSonUtil.toJSonString(product));
        
    }

}
