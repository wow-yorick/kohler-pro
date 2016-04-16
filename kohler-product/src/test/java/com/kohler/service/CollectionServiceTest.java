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

import com.kohler.entity.CollectionEntity;

/**
 * Class Function Description
 * CollectionServiceTest
 * @author ztt
 * @Date 2014年10月20日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/*.xml"})
public class CollectionServiceTest {

    @Autowired
    private CollectionService collectionService;
    
    /**
     * Test method for {@link com.kohler.service.impl.CollectionServiceImpl#getCollectionById(java.lang.Integer)}.
     */
    @Test
    public void testGetCollectionById() {
        CollectionEntity collectionEntity=collectionService.getCollectionById(1);
        assertEquals(collectionEntity, null);
    }

    /**
     * Test method for {@link com.kohler.service.impl.CollectionServiceImpl#getCollectionByCod(com.kohler.entity.CollectionEntity)}.
     */
    @Test
    public void testGetCollectionByCod() {
        CollectionEntity collectionEntity=new CollectionEntity();
        collectionEntity.setIsEnable(true);
        List<CollectionEntity> list=collectionService.getCollectionByCod(collectionEntity);
        for(CollectionEntity entity:list){
            assertNotEquals(entity, null);
        }
    }

    /**
     * Test method for {@link com.kohler.service.impl.CollectionServiceImpl#getCollectionsByDefault()}.
     */
    @Test
    public void testGetCollectionsByDefault() {
        List<CollectionEntity> list=collectionService.getCollectionsByDefault();
        for(CollectionEntity entity:list){
            assertNotEquals(entity, null);
        }
    }

}
