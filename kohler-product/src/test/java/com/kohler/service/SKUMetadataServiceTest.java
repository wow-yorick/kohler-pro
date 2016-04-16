/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kohler.entity.SKUAttrEntity;
import com.kohler.entity.SKUEntity;
import com.kohler.entity.SKUMetadataEntity;

/**
 * Class Function Description
 * SKUMetadataServiceTest
 * @author ztt
 * @Date 2014年10月17日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/*.xml"})
public class SKUMetadataServiceTest {

    @Autowired
    private SKUMetadataService skuMetadataService;
    
    /**
     * Test method for {@link com.kohler.service.impl.SKUMetadataServiceImpl#addSKUMetadata()}.
     */
    @Test
    public void testAddSKUMetadata() {
        SKUMetadataEntity skuMetadataEntity=skuMetadataService.addSKUMetadata();
        assertNotEquals(skuMetadataEntity, null);
    }

    /**
     * Test method for {@link com.kohler.service.impl.SKUMetadataServiceImpl#saveSKUMetadata(com.kohler.entity.SKUMetadataEntity, java.util.List, java.util.List)}.
     * @throws UnsupportedEncodingException 
     */
    @Test
    public void testSaveSKUMetadata() throws UnsupportedEncodingException {
        SKUMetadataEntity skuMetadataEntity=new SKUMetadataEntity();
        skuMetadataEntity.setSkuMetadataId(111);
        skuMetadataEntity.setIsEnable(true);
        List<SKUEntity> skulist=new ArrayList<SKUEntity>();
        List<SKUAttrEntity> skuAttrlist=new ArrayList<SKUAttrEntity>();
        List<Map<String, Object>> maplist = new ArrayList<Map<String, Object>>();
        skuMetadataService.saveSKUMetadata(skuMetadataEntity, skulist, skuAttrlist, maplist);
        assertNotEquals(skuMetadataEntity, null);
    }

    /**
     * Test method for {@link com.kohler.service.impl.SKUMetadataServiceImpl#getSKUMetadataEntitylistById(java.lang.Integer)}.
     */
    @Test
    public void testGetSKUMetadataEntitylistById() {
        List<SKUMetadataEntity> list=skuMetadataService.getSKUMetadataEntitylistById(1111);
        for(SKUMetadataEntity skuMetadataEntity:list){
            assertEquals(skuMetadataEntity, null);
        }
    }

    /**
     * Test method for {@link com.kohler.service.impl.SKUMetadataServiceImpl#getSkuMetadataBySKUMetadataid(java.lang.Integer)}.
     */
    @Test
    public void testGetSkuMetadataBySKUMetadataid() {
        SKUMetadataEntity skuMetadataEntity=skuMetadataService.getSkuMetadataBySKUMetadataid(1111);
        assertEquals(skuMetadataEntity, null);
    }

    /**
     * Test method for {@link com.kohler.service.impl.SKUMetadataServiceImpl#updateSKUMetadata(com.kohler.entity.SKUMetadataEntity, java.util.List, java.util.List)}.
     * @throws UnsupportedEncodingException 
     */
    @Test
    public void testUpdateSKUMetadata() throws UnsupportedEncodingException {
        SKUMetadataEntity skuMetadataEntity=new SKUMetadataEntity();
        skuMetadataEntity.setSkuMetadataId(111);
        skuMetadataEntity.setIsEnable(true);
        List<SKUEntity> skulist=new ArrayList<SKUEntity>();
        List<SKUAttrEntity> skuAttrlist=new ArrayList<SKUAttrEntity>();
        List<Map<String, Object>> maplist = new ArrayList<Map<String, Object>>();
        skuMetadataService.saveSKUMetadata(skuMetadataEntity, skulist, skuAttrlist,maplist);
        for(SKUEntity skuEntity:skulist){
            assertEquals(skuEntity, null);
        }
    }

}
