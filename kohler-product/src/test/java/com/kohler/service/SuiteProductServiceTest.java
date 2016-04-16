/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kohler.entity.SuiteProductEntity;
import com.kohler.entity.extend.SuiteProductPojo;

/**
 * Class Function Description
 *
 * @author Administrator
 * @Date 2014年10月21日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/*.xml"})
public class SuiteProductServiceTest {
    
    @Autowired
    SuiteProductService suiteProductService;

    /**
     * Test method for {@link com.kohler.service.impl.SuiteProductServiceImpl#getSuiteProductList(java.lang.Integer)}.
     */
    @Test
    public void testGetSuiteProductList() {
        List<Map<String, Object>> result =  suiteProductService.getSuiteProductList(null);
        Assert.assertNotNull(result);
    }

    /**
     * Test method for {@link com.kohler.service.impl.SuiteProductServiceImpl#addSuiteProduct(com.kohler.entity.SuiteProductEntity)}.
     * @param result 
     */
    @Test
    public void testAddSuiteProduct() {
        SuiteProductEntity suiteProductEntity = new SuiteProductEntity();
        Integer result = suiteProductService.addSuiteProduct(suiteProductEntity);
        Assert.assertNotNull(result);
    }

    /**
     * Test method for {@link com.kohler.service.impl.SuiteProductServiceImpl#editSuiteProduct(com.kohler.entity.SuiteProductEntity)}.
     */
    @Test
    public void testEditSuiteProduct() {
        SuiteProductEntity suiteProductEntity = new SuiteProductEntity();
        Integer result = suiteProductService.editSuiteProduct(suiteProductEntity);
        Assert.assertNotNull(result);
    }

    /**
     * Test method for {@link com.kohler.service.impl.SuiteProductServiceImpl#getSuiteProductDetail(java.lang.Integer)}.
     */
    @Test
    public void testGetSuiteProductDetail() {
        SuiteProductPojo  result = suiteProductService.getSuiteProductDetail(null);
        Assert.assertNotNull(result);
    }

    /**
     * Test method for {@link com.kohler.service.impl.SuiteProductServiceImpl#deleteSuiteProduct(java.lang.Integer)}.
     */
    @Test
    @Ignore
    public void testDeleteSuiteProduct() {
        //suiteProductService.deleteSuiteProduct(72);
    }

}
