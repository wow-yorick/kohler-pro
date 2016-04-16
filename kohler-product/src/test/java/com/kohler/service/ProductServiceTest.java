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

import com.kohler.entity.ProductEntity;

/**
 * Class Function Description
 * ProductServiceTest
 * @author Admin
 * @Date 2014年10月17日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/*.xml"})
public class ProductServiceTest {

    @Autowired
    private ProductService productService;
    
    /**
     * Test method for {@link com.kohler.service.impl.ProductServiceImpl#getProductListByEntity(com.kohler.entity.ProductEntity)}.
     */
    @Test
    public void testGetProductListByEntity() {
        ProductEntity productEntity=new ProductEntity();
        productEntity.setProductMetadataId(1111);
        productEntity.setIsEnable(true);
        List<ProductEntity> list=productService.getProductListByEntity(productEntity);
        for(ProductEntity entity:list){
            assertEquals(entity, null);
        }
    }

}
