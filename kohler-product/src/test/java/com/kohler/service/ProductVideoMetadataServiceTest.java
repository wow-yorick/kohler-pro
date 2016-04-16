/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kohler.entity.ProductVideoEntity;
import com.kohler.entity.ProductVideoMetadataEntity;

/**
 * Class Function Description
 * ProductVideoMetadataServiceTest
 * @author ztt
 * @Date 2014年10月17日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/*.xml"})
public class ProductVideoMetadataServiceTest {
    
    @Autowired
    private ProductVideoMetadataService productVideoMetadataService;

    /**
     * Test method for {@link com.kohler.service.impl.ProductVideoMetadataServiceImpl#addProductVideoMetadata()}.
     */
    @Test
    public void testAddProductVideoMetadata() {
        ProductVideoMetadataEntity videoMetadataEntity=productVideoMetadataService.addProductVideoMetadata();
        assertNotEquals(videoMetadataEntity, null);
    }

    /**
     * Test method for {@link com.kohler.service.impl.ProductVideoMetadataServiceImpl#saveProductVideoMetadata(com.kohler.entity.ProductVideoMetadataEntity, java.util.List)}.
     */
    @Test
    public void testSaveProductVideoMetadata() {
        ProductVideoMetadataEntity productVideoMetadataEntity=new ProductVideoMetadataEntity();
        productVideoMetadataEntity.setProductVideoMetadataId(111);
        productVideoMetadataEntity.setIsEnable(true);
        List<ProductVideoEntity> list=new ArrayList<ProductVideoEntity>();
        productVideoMetadataService.saveProductVideoMetadata(productVideoMetadataEntity, list);
        assertNotEquals(productVideoMetadataEntity, null);
    }

    /**
     * Test method for {@link com.kohler.service.impl.ProductVideoMetadataServiceImpl#getVideoMetadataById(java.lang.Integer)}.
     */
    @Test
    public void testGetVideoMetadataById() {
        ProductVideoMetadataEntity videoMetadataEntity=productVideoMetadataService.getVideoMetadataById(1111);
        assertEquals(videoMetadataEntity, null);
    }

    /**
     * Test method for {@link com.kohler.service.impl.ProductVideoMetadataServiceImpl#updateProductVideoMetadata(com.kohler.entity.ProductVideoMetadataEntity, java.util.List)}.
     */
    @Test
    public void testUpdateProductVideoMetadata() {
        ProductVideoMetadataEntity productVideoMetadataEntity=new ProductVideoMetadataEntity();
        productVideoMetadataEntity.setProductVideoMetadataId(111);
        productVideoMetadataEntity.setIsEnable(true);
        List<ProductVideoEntity> list=new ArrayList<ProductVideoEntity>();
        productVideoMetadataService.updateProductVideoMetadata(productVideoMetadataEntity, list);
        for(ProductVideoEntity productVideoEntity:list){
            assertEquals(productVideoEntity, null);
        }
    }

    /**
     * Test method for {@link com.kohler.service.impl.ProductVideoMetadataServiceImpl#getVideoMetadatalistById(java.lang.Integer)}.
     */
    @Test
    public void testGetVideoMetadatalistById() {
        List<ProductVideoMetadataEntity> list=productVideoMetadataService.getVideoMetadatalistById(1111);
        for(ProductVideoMetadataEntity videoMetadataEntity:list){
            assertEquals(videoMetadataEntity, null);
        }
    }

}
