/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kohler.entity.ProductPDFEntity;

/**
 * Class Function Description
 * ProductPDFServiceTest
 * @author ztt
 * @Date 2014年10月17日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/*.xml"})
public class ProductPDFServiceTest {

    @Autowired
    private ProductPDFService pdfService;
    /**
     * Test method for {@link com.kohler.service.impl.ProductPDFServiceImpl#getProductPDFListByEntity(com.kohler.entity.ProductPDFEntity)}.
     */
    @Test
    public void testGetProductPDFListByEntity() {
        ProductPDFEntity productPDFEntity=new ProductPDFEntity();
        productPDFEntity.setProductPDFMetadataId(111);
        productPDFEntity.setIsEnable(true);
        List<ProductPDFEntity> list=pdfService.getProductPDFListByEntity(productPDFEntity);
        for(ProductPDFEntity pdfEntity:list){
            assertEquals(pdfEntity, null);
        }
    }

    /**
     * Test method for {@link com.kohler.service.impl.ProductPDFServiceImpl#getProductPDFById(java.lang.Integer)}.
     */
    @Test
    public void testGetProductPDFById() {
        ProductPDFEntity productPDFEntity=pdfService.getProductPDFById(111);
        assertEquals(productPDFEntity, null);
    }

}
