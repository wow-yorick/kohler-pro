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

import com.kohler.entity.ProductPartEntity;
import com.kohler.entity.ProductPartMetadataEntity;

/**
 * Class Function Description
 * ProductPartMetadataServiceTest
 * @author ztt
 * @Date 2014年10月17日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/*.xml"})
public class ProductPartMetadataServiceTest {

    @Autowired
    private ProductPartMetadataService partMetadataService;
    
    /**
     * Test method for {@link com.kohler.service.impl.ProductPartMetadataServiceImpl#addProductPartMetadata()}.
     */
    @Test
    public void testAddProductPartMetadata() {
        ProductPartMetadataEntity productPartMetadataEntity=partMetadataService.addProductPartMetadata();
        assertNotEquals(productPartMetadataEntity, null);
    }

    /**
     * Test method for {@link com.kohler.service.impl.ProductPartMetadataServiceImpl#saveProductPartMetadata(com.kohler.entity.ProductPartMetadataEntity, java.util.List)}.
     */
    @Test
    public void testSaveProductPartMetadata() {
        ProductPartMetadataEntity productPartMetadataEntity=new ProductPartMetadataEntity();
        productPartMetadataEntity.setProductPartMetadataId(111);
        productPartMetadataEntity.setIsEnable(true);
        List<ProductPartEntity> list=new ArrayList<ProductPartEntity>();
        partMetadataService.saveProductPartMetadata(productPartMetadataEntity, list);
        assertNotEquals(productPartMetadataEntity, null);
    }

    /**
     * Test method for {@link com.kohler.service.impl.ProductPartMetadataServiceImpl#updateProductPartMetadata(com.kohler.entity.ProductPartMetadataEntity, java.util.List)}.
     */
    @Test
    public void testUpdateProductPartMetadata() {
        ProductPartMetadataEntity productPartMetadataEntity=new ProductPartMetadataEntity();
        productPartMetadataEntity.setProductPartMetadataId(111);
        productPartMetadataEntity.setIsEnable(true);
        List<ProductPartEntity> list=new ArrayList<ProductPartEntity>();
        partMetadataService.updateProductPartMetadata(productPartMetadataEntity, list);
        for(ProductPartEntity productPartEntity:list){
            assertNotEquals(productPartEntity, null);
        }
    }

    /**
     * Test method for {@link com.kohler.service.impl.ProductPartMetadataServiceImpl#getPartMetadataById(java.lang.Integer)}.
     */
    @Test
    public void testGetPartMetadataById() {
        ProductPartMetadataEntity partMetadataEntity=partMetadataService.getPartMetadataById(111);
        assertEquals(partMetadataEntity, null);
    }

    /**
     * Test method for {@link com.kohler.service.impl.ProductPartMetadataServiceImpl#getPartMetadatalistById(java.lang.Integer)}.
     */
    @Test
    public void testGetPartMetadatalistById() {
        List<ProductPartMetadataEntity> list=partMetadataService.getPartMetadatalistById(1111);
        for(ProductPartMetadataEntity partMetadataEntity:list){
            assertEquals(partMetadataEntity, null);
        }
    }

}
