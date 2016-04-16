/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kohler.dao.utils.Pager;
import com.kohler.entity.CollectionStyleMetadataEntity;
import com.kohler.entity.NewArrivalMetadataEntity;
/**
 * Class Function Description
 * CollectionServiceTest
 * @author ztt
 * @Date 2014年10月20日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/*.xml"})
public class CollectionStyleServiceTest {
    @Autowired
    private CollectionStyleService CollectionStyleService;
    
    @Test
    public void testGetCollectionStyleListPage() {
        Pager pager = new Pager();
        pager.setUrl("/");
        Pager<Map<String, Object>> page = CollectionStyleService.getCollectionStyleListPage(pager, "123");
        Assert.assertNotNull(page);
    }

    @Test
    public void testAddCollectionStyle() {
        CollectionStyleMetadataEntity CollectionStyleMetadataEntity = new CollectionStyleMetadataEntity();
        CollectionStyleMetadataEntity.setSeqNo(1);
        Integer id = CollectionStyleService.addCollectionStyle(CollectionStyleMetadataEntity, "[]");
        Assert.assertNotNull(id);
    }

    @Test
    public void testEditCollectionStyle() {
        CollectionStyleMetadataEntity CollectionStyleMetadataEntity = new CollectionStyleMetadataEntity();
        CollectionStyleMetadataEntity.setCollectionStyleMetadataId(1);
        Integer id = CollectionStyleService.editCollectionStyle(CollectionStyleMetadataEntity, "[]");
        Assert.assertNotNull(id);
    }

    @Test
    public void testDeleteCollectionStyle() {
        Integer[] ids={1};
        CollectionStyleService.deleteCollectionStyle(ids);
        Assert.assertTrue(true); 
    }

}
