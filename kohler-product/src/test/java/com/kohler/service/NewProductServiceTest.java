/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kohler.dao.utils.Pager;
import com.kohler.entity.NewArrivalEntity;
import com.kohler.entity.NewArrivalMetadataEntity;
/**
 * Class Function Description
 *
 * @author Administrator
 * @Date 2014年10月21日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/*.xml"})
public class NewProductServiceTest {
    @Autowired
    private NewProductService NewProductServiceImpl;
    @Test
    public void testGetNewarrivalsListPage() {
        Pager pager = new Pager();
        pager.setUrl("/");
        Pager<Map<String, Object>> page = NewProductServiceImpl.getNewarrivalsListPage(pager, "123");
        Assert.assertNotNull(page);
    }

    @Test
    public void testGetselectList() {
        Map<String,Object> map=NewProductServiceImpl.getselectList();
        Assert.assertNotNull(map);
    }

    @Test
    public void testAddnewproduct() {
        NewArrivalMetadataEntity NewArrivalMetadataEntity = new NewArrivalMetadataEntity();
        NewArrivalMetadataEntity.setCategoryMetadataId(1);
        Integer id = NewProductServiceImpl.addnewproduct(NewArrivalMetadataEntity, "[]","1","1");
        Assert.assertNotNull(id);
    }

    @Test
    public void testEditnewproduct() {
        NewArrivalMetadataEntity NewArrivalMetadataEntity = new NewArrivalMetadataEntity();
        NewArrivalMetadataEntity.setNewArrivalMetadataId(1);
        Integer[] ids={1};
        int id=NewProductServiceImpl.editnewproduct(NewArrivalMetadataEntity, "[]",ids, ids);
        Assert.assertNotNull(id);
    }

    @Test
    public void testGetNewArrivalByMetadataId() {
        List<NewArrivalEntity> list=NewProductServiceImpl.getNewArrivalByMetadataId(1);
        Assert.assertNotNull(list);
    }

    @Test
    public void testGetNewArrivalMetadataByMetadataId() {
        NewArrivalMetadataEntity NewArrivalMetadataEntity=NewProductServiceImpl.getNewArrivalMetadataByMetadataId(5);
        Assert.assertNotNull(NewArrivalMetadataEntity);
    }

    @Test
    public void testGetNewArrivalHeroAreAByMetadataId() {
        List<Map<String, Object>> list=NewProductServiceImpl.getNewArrivalHeroAreAByMetadataId(1);
        Assert.assertNotNull(list);
    }

    @Test
    public void testGetNewArrivalBannerUnitByMetadataId() {
        List<Map<String, Object>> list=NewProductServiceImpl.getNewArrivalBannerUnitByMetadataId(1);
        Assert.assertNotNull(list);
    }

    @Test
    public void testDeleteNewArrival() {
        Integer[] ids={1};
        NewProductServiceImpl.deleteNewArrival(ids);
        Assert.assertTrue(true); 
    }

}
