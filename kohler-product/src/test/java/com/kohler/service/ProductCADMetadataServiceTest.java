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

import com.kohler.entity.ProductCADEntity;
import com.kohler.entity.ProductCADMetadataEntity;

/**
 * Class Function Description
 * ProductCADMetadataServiceTest
 * @author ztt
 * @Date 2014年10月17日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/*.xml"})
public class ProductCADMetadataServiceTest {

    @Autowired
    private ProductCADMetadataService cadMetadataService;
    
    /**
     * Test method for {@link com.kohler.service.impl.ProductCADMetadataServiceImpl#addProductCADMetadata()}.
     */
    @Test
    public void testAddProductCADMetadata() {
        ProductCADMetadataEntity productCADMetadataEntity=cadMetadataService.addProductCADMetadata();
        assertNotEquals(productCADMetadataEntity, null);
    }

    /**
     * Test method for {@link com.kohler.service.impl.ProductCADMetadataServiceImpl#saveProductCADMetadata(com.kohler.entity.ProductCADMetadataEntity, java.util.List)}.
     */
    @Test
    public void testSaveProductCADMetadata() {
        ProductCADMetadataEntity productCADMetadataEntity=new ProductCADMetadataEntity();
        productCADMetadataEntity.setProductCADMetadataId(111);
        productCADMetadataEntity.setIsEnable(true);
        List<ProductCADEntity> list=new ArrayList<ProductCADEntity>();
        cadMetadataService.saveProductCADMetadata(productCADMetadataEntity, list);
        for(ProductCADEntity productCADEntity:list){
            assertEquals(productCADEntity, null);
        }
    }

    /**
     * Test method for {@link com.kohler.service.impl.ProductCADMetadataServiceImpl#getCADMetadataById(java.lang.Integer)}.
     */
    @Test
    public void testGetCADMetadataById() {
        ProductCADMetadataEntity cadMetadataEntity=cadMetadataService.getCADMetadataById(111);
        assertEquals(cadMetadataEntity, null);
    }

    /**
     * Test method for {@link com.kohler.service.impl.ProductCADMetadataServiceImpl#updateProductCADMetadata(com.kohler.entity.ProductCADMetadataEntity, java.util.List)}.
     */
    @Test
    public void testUpdateProductCADMetadata() {
        ProductCADMetadataEntity productCADMetadataEntity=new ProductCADMetadataEntity();
        productCADMetadataEntity.setProductCADMetadataId(111);
        productCADMetadataEntity.setIsEnable(true);
        List<ProductCADEntity> list=new ArrayList<ProductCADEntity>();
        cadMetadataService.updateProductCADMetadata(productCADMetadataEntity, list);
        assertNotEquals(productCADMetadataEntity, null);
    }

    /**
     * Test method for {@link com.kohler.service.impl.ProductCADMetadataServiceImpl#getCADMetadatalistById(java.lang.Integer)}.
     */
    @Test
    public void testGetCADMetadatalistById() {
        List<ProductCADMetadataEntity> list=cadMetadataService.getCADMetadatalistById(1111);
        for(ProductCADMetadataEntity cadMetadataEntity:list){
            assertEquals(cadMetadataEntity, null);
        }
    }

}
