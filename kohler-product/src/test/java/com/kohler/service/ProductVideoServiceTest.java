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

import com.kohler.entity.ProductVideoEntity;

/**
 * Class Function Description
 * ProductVideoServiceTest
 * @author ztt
 * @Date 2014年10月17日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/*.xml"})
public class ProductVideoServiceTest {

    @Autowired
    private ProductVideoService productVideoService;
    
    /**
     * Test method for {@link com.kohler.service.impl.ProductVideoServiceImpl#getProductVideoListByEntity(com.kohler.entity.ProductVideoEntity)}.
     */
    @Test
    public void testGetProductVideoListByEntity() {
        ProductVideoEntity entity=new ProductVideoEntity();
        entity.setProductVideoMetadataId(1111);
        entity.setIsEnable(true);
        List<ProductVideoEntity> list=productVideoService.getProductVideoListByEntity(entity);
        for(ProductVideoEntity videoEntity:list){
            assertEquals(videoEntity,null);
        }
    }

    /**
     * Test method for {@link com.kohler.service.impl.ProductVideoServiceImpl#getProductVideoById(java.lang.Integer)}.
     */
    @Test
    public void testGetProductVideoById() {
        ProductVideoEntity productVideoEntity=productVideoService.getProductVideoById(1111);
        assertEquals(productVideoEntity, null);
    }

}
