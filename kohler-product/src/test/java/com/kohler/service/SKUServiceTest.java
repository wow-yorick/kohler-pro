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

import com.kohler.entity.SKUEntity;

/**
 * Class Function Description
 * SKUServiceTest
 * @author ztt
 * @Date 2014年10月17日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/*.xml"})
public class SKUServiceTest {
    
    @Autowired
    private SKUService skuService;

    /**
     * Test method for {@link com.kohler.service.impl.SKUServiceImpl#getSKUAttrListBySKUMetadataId(java.lang.Integer)}.
     */
    @Test
    public void testGetSKUAttrListBySKUMetadataId() {
        SKUEntity sku=new SKUEntity();
        sku.setSkuMetadataId(1111);
        sku.setIsEnable(true);
        List<SKUEntity> list=skuService.getSKUListByEntity(sku);
        for(SKUEntity skuEntity:list){
            assertEquals(skuEntity, null);
        }
    }

}
