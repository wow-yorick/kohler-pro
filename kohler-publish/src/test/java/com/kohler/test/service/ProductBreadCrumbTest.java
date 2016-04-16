/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.test.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kohler.bean.ConfPrepareData;
import com.kohler.constants.CommonConstants;
import com.kohler.exception.DataException;
import com.kohler.service.breadcrumb.ProductDataBreadcrumb;

/**
 * Class Function Description
 *
 * @author Administrator
 * @Date 2014年10月24日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/*.xml"})
public class ProductBreadCrumbTest {
    
    @Autowired
    public ProductDataBreadcrumb productDataBreadcrumb;
    
    protected final static Logger logger = Logger.getLogger(ProductBreadCrumbTest.class);

    /**
     * Test method for {@link com.kohler.service.breadcrumb.ProductDataBreadcrumb#getGeneralData(com.kohler.bean.ConfPrepareData)}.
     */
    @Test
    public void testGetBreadcrumb() {
        ConfPrepareData confPrepareData = new ConfPrepareData();
        confPrepareData.setDataId(155);
        confPrepareData.setLang(CommonConstants.LOCALE_CN);
        confPrepareData.setWebsitePlatform(CommonConstants.PC_PLATFORM);
        List<Map<String, Object>> testB = new ArrayList<Map<String,Object>>();
        try {
            testB = productDataBreadcrumb.getBreadCrumb(confPrepareData);
        } catch (DataException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(testB);
        logger.debug("productDataBreadcrumb"+testB);
    }

}
