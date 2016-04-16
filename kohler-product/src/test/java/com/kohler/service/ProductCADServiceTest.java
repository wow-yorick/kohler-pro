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

import com.kohler.entity.ProductCADEntity;

/**
 * Class Function Description
 * ProductCADServiceTest
 * @author ztt
 * @Date 2014年10月17日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/*.xml"})
public class ProductCADServiceTest {
    
    @Autowired
    private ProductCADService cadService;

    /**
     * Test method for {@link com.kohler.service.impl.ProductCADServiceImpl#getProductCADListByEntity(com.kohler.entity.ProductCADEntity)}.
     */
    @Test
    public void testGetProductCADListByEntity() {
        ProductCADEntity productCADEntity=new ProductCADEntity();
        productCADEntity.setProductCADMetadataId(1111);
        productCADEntity.setIsEnable(true);
        List<ProductCADEntity> list=cadService.getProductCADListByEntity(productCADEntity);
        for(ProductCADEntity cadEntity:list){
            assertEquals(cadEntity, null);
        }
    }

    /**
     * Test method for {@link com.kohler.service.impl.ProductCADServiceImpl#getProductCADById(java.lang.Integer)}.
     */
    @Test
    public void testGetProductCADById() {
        ProductCADEntity productCADEntity=cadService.getProductCADById(1111);
        assertEquals(productCADEntity, null);
    }

}
