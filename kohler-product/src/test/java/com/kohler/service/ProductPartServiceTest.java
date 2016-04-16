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

import com.kohler.entity.ProductPartEntity;

/**
 * Class Function Description
 * ProductPartServiceTest
 * @author ztt
 * @Date 2014年10月20日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/*.xml"})
public class ProductPartServiceTest {

    @Autowired
    private ProductPartService productPartService;
    
    /**
     * Test method for {@link com.kohler.service.impl.ProductPartServiceImpl#getProductPartListByEntity(com.kohler.entity.ProductPartEntity)}.
     */
    @Test
    public void testGetProductPartListByEntity() {
        ProductPartEntity partEntity=new ProductPartEntity();
        partEntity.setProductPartMetadataId(1111);
        partEntity.setIsEnable(true);
        List<ProductPartEntity> list=productPartService.getProductPartListByEntity(partEntity);
        for(ProductPartEntity productPartEntity:list){
            assertEquals(productPartEntity, null);
        }
    }

    /**
     * Test method for {@link com.kohler.service.impl.ProductPartServiceImpl#getProductPartById(java.lang.Integer)}.
     */
    @Test
    public void testGetProductPartById() {
        ProductPartEntity productPartEntity=productPartService.getProductPartById(111);
        assertEquals(productPartEntity, null);
    }

}
