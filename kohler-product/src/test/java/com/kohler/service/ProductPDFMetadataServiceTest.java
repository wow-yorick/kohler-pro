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

import com.kohler.entity.ProductPDFEntity;
import com.kohler.entity.ProductPDFMetadataEntity;

/**
 * Class Function Description
 * ProductPDFMetadataServiceTest
 * @author ztt
 * @Date 2014年10月17日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/*.xml"})
public class ProductPDFMetadataServiceTest {

    @Autowired
    private ProductPDFMetadataService pdfMetadataService;
    
    
    /**
     * Test method for {@link com.kohler.service.impl.ProductPDFMetadataServiceImpl#addProductPDFMetadata()}.
     */
    @Test
    public void testAddProductPDFMetadata() {
        ProductPDFMetadataEntity productPDFMetadataEntity=pdfMetadataService.addProductPDFMetadata();
        assertNotEquals(productPDFMetadataEntity, null);
    }

    /**
     * Test method for {@link com.kohler.service.impl.ProductPDFMetadataServiceImpl#saveProductPDFMetadata(com.kohler.entity.ProductPDFMetadataEntity, java.util.List)}.
     */
    @Test
    public void testSaveProductPDFMetadata() {
        ProductPDFMetadataEntity productPDFMetadataEntity=new ProductPDFMetadataEntity();
        productPDFMetadataEntity.setProductPDFMetadataId(111);
        productPDFMetadataEntity.setIsEnable(true);
        List<ProductPDFEntity> list=new ArrayList<ProductPDFEntity>();
        pdfMetadataService.saveProductPDFMetadata(productPDFMetadataEntity, list);
        assertNotEquals(productPDFMetadataEntity, null);
    }

    /**
     * Test method for {@link com.kohler.service.impl.ProductPDFMetadataServiceImpl#updateProductPDFMetadata(com.kohler.entity.ProductPDFMetadataEntity, java.util.List)}.
     */
    @Test
    public void testUpdateProductPDFMetadata() {
        ProductPDFMetadataEntity productPDFMetadataEntity=new ProductPDFMetadataEntity();
        productPDFMetadataEntity.setProductPDFMetadataId(111);
        productPDFMetadataEntity.setIsEnable(true);
        List<ProductPDFEntity> list=new ArrayList<ProductPDFEntity>();
        pdfMetadataService.updateProductPDFMetadata(productPDFMetadataEntity, list);
        for(ProductPDFEntity productPDFEntity:list){
            assertEquals(productPDFEntity, null);
        }
    }

    /**
     * Test method for {@link com.kohler.service.impl.ProductPDFMetadataServiceImpl#getPDFMetadataById(java.lang.Integer)}.
     */
    @Test
    public void testGetPDFMetadataById() {
        ProductPDFMetadataEntity pdfMetadataEntity=pdfMetadataService.getPDFMetadataById(111);
        assertEquals(pdfMetadataEntity, null);
    }

    /**
     * Test method for {@link com.kohler.service.impl.ProductPDFMetadataServiceImpl#getPDFMetadatalistById(java.lang.Integer)}.
     */
    @Test
    public void testGetPDFMetadatalistById() {
        List<ProductPDFMetadataEntity> list=pdfMetadataService.getPDFMetadatalistById(111);
        for(ProductPDFMetadataEntity pdfMetadataEntity:list){
            assertEquals(pdfMetadataEntity, null);
        }
    }

}
