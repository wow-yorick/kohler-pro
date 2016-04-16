/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.service;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kohler.dao.utils.Pager;
/**
 * Class Function Description
 *
 * @author Administrator
 * @Date 2014年10月21日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/*.xml"})
public class ContactusServiceImplText {
    @Autowired 
    public ContactusService ContactusService;
    @Test
    public void testGetContactusListPage() {
        Pager pager = new Pager();
        pager.setUrl("/");
        Pager<Map<String, Object>> page = ContactusService.getContactusListPage(pager, 10240001);
        Assert.assertNotNull(page);
    }

    @Test
    @Ignore
    public void testUpdateContactus() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetview() {
        List<Map<String, Object>> list=ContactusService.getview(1);
        Assert.assertNotNull(list);
    }

    @Test 
    @Ignore
    public void testReply() {
        fail("Not yet implemented");
    }

}
