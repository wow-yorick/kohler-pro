/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kohler.entity.ProductEntity;
import com.kohler.entity.ProductMetadataEntity;

/**
 * Class Function Description
 * ProductMetadataServiceTest
 * @author ztt
 * @Date 2014年10月17日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/*.xml"})
public class ProductMetadataServiceTest {
    
    @Autowired
    private ProductMetadataService productMetadataService;
    
    /**
     * Test method for {@link com.kohler.service.impl.ProductMetadataServiceImpl#addProductMetadata()}.
     */
    @Test
    public void testAddProductMetadata() {
        ProductMetadataEntity productMetadataEntity=productMetadataService.addProductMetadata();
        assertNotEquals(productMetadataEntity,null);
    }

    /**
     * Test method for {@link com.kohler.service.impl.ProductMetadataServiceImpl#saveProductMetadata(com.kohler.entity.ProductMetadataEntity, java.util.List)}.
     * @throws UnsupportedEncodingException 
     */
    @Test
    public void testSaveProductMetadata() throws UnsupportedEncodingException {
        ProductMetadataEntity productMetadataEntity=new ProductMetadataEntity();
        productMetadataEntity.setProductMetadataId(111);
        productMetadataEntity.setIsEnable(true);
        List<ProductEntity> list=new ArrayList<ProductEntity>();
        List<Map<String,Object>> attrlist=new ArrayList<Map<String,Object>>();
        productMetadataService.saveProductMetadata(productMetadataEntity, list, attrlist);
        assertNotEquals(productMetadataEntity, null);
    }

    /**
     * Test method for {@link com.kohler.service.impl.ProductMetadataServiceImpl#findProductMetadataById(java.lang.Integer)}.
     */
    @Test
    public void testGetProductMetadataById() {
       ProductMetadataEntity productMetadataEntity=productMetadataService.getProductMetadataById(111);
       assertEquals(productMetadataEntity, null);
    }

    /**
     * Test method for {@link com.kohler.service.impl.ProductMetadataServiceImpl#updateProductMetadata(com.kohler.entity.ProductMetadataEntity, java.util.List)}.
     * @throws UnsupportedEncodingException 
     */
    @Test
    public void testUpdateProductMetadata() throws UnsupportedEncodingException {
        ProductMetadataEntity productMetadataEntity=new ProductMetadataEntity();
        productMetadataEntity.setProductMetadataId(111);
        productMetadataEntity.setIsEnable(true);
        List<ProductEntity> list=new ArrayList<ProductEntity>();
        List<Map<String,Object>> attrlist=new ArrayList<Map<String,Object>>();
        productMetadataService.updateProductMetadata(productMetadataEntity, list, attrlist);
        for(ProductEntity productEntity:list){
            assertEquals(productEntity, null);
        }
    }

}
