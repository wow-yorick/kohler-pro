/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kohler.dao.utils.Pager;
import com.kohler.entity.CollectionEntity;
import com.kohler.entity.CollectionMetadataEntity;
import com.kohler.entity.extend.CollectionMetadataPojo;

/**
 * Class Function Description
 * CollectionServiceMetadataTest
 * @author ztt
 * @Date 2014年10月20日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/*.xml"})
public class CollectionServiceMetadataTest {

    @Autowired
    private CollectionMetadataService collectionMatadataService;
    
    /**
     * Test method for {@link com.kohler.service.impl.CollectionMetadataServiceImpl#getCollectionsByMap(com.kohler.dao.utils.Pager, java.util.Map)}.
     */
    @Test
    public void testGetCollectionsByMap() {
        Pager pa = new Pager();
        pa.setUrl("/");
        Map<String, Object> map=new HashMap<String, Object>();
        Pager<CollectionMetadataEntity> pagerCollection=collectionMatadataService.getCollectionsByMap(pa, map);
        assertNotEquals(pagerCollection, null);
    }

    /**
     * Test method for {@link com.kohler.service.impl.CollectionMetadataServiceImpl#addCollectionMetadata(java.lang.Integer, java.util.List)}.
     */
    @Test
    public void testAddCollectionMetadata() {
        Integer seqNo=111;
        List<CollectionEntity> list=new ArrayList<CollectionEntity>();
        collectionMatadataService.addCollectionMetadata(seqNo, list);
        for(CollectionEntity collectionEntity:list){
            assertEquals(collectionEntity, null);
        }
    }

    /**
     * Test method for {@link com.kohler.service.impl.CollectionMetadataServiceImpl#getCollectionMetadataById(com.kohler.entity.CollectionMetadataEntity)}.
     */
    @Test
    public void testGetCollectionMetadataById() {
        CollectionMetadataEntity collectionMetadataEntity=new CollectionMetadataEntity();
        collectionMetadataEntity.setCollectionMetadataId(1111);
        collectionMetadataEntity.setIsEnable(true);
        CollectionMetadataEntity entity=collectionMatadataService.getCollectionMetadataById(collectionMetadataEntity);
        assertEquals(entity, null);
    }

    /**
     * Test method for {@link com.kohler.service.impl.CollectionMetadataServiceImpl#editCollectionMetadata(java.lang.Integer, java.lang.Integer, java.util.List)}.
     */
    @Test
    public void testEditCollectionMetadata() {
        List<CollectionEntity> list=new ArrayList<CollectionEntity>();
        collectionMatadataService.editCollectionMetadata(111,222, list);
        for(CollectionEntity collectionEntity:list){
            assertEquals(collectionEntity, null);
        }
    }

    /**
     * Test method for {@link com.kohler.service.impl.CollectionMetadataServiceImpl#deleteCollectionMetadata(java.lang.String)}.
     */
    @Test
    public void testDeleteCollectionMetadata() {
        String collectionMetadataIds="1,2,3,4";
        collectionMatadataService.deleteCollectionMetadata(collectionMetadataIds);
        assertNotEquals(collectionMetadataIds, null);
    }

    /**
     * Test method for {@link com.kohler.service.impl.CollectionMetadataServiceImpl#getCollectionsByMap(com.kohler.dao.utils.Pager, com.kohler.entity.extend.CollectionMetadataPojo)}.
     */
    @Test
    public void testGetCollectionsByPojo() {
        Pager p = new Pager();
        p.setUrl("/");
        CollectionMetadataPojo collectionMetadataPojo=new CollectionMetadataPojo();
        Pager<Map<String,Object>> pager=collectionMatadataService.getCollectionsByMap(p, collectionMetadataPojo);
        assertNotEquals(pager, null);
    }

}
