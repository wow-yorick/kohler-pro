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
/**
 * Class Function Description
 *
 * @author Administrator
 * @Date 2014年10月17日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/*.xml"})
public class UserfolderServiceTest {
    @Autowired
    private UserfolderService UserfolderService;

    @Test
    public void testGetUserfolderList() {
        Pager pager = new Pager();
        pager.setUrl("/");
        Pager<Map<String, Object>> page = UserfolderService.getUserfolderList(pager, 10170005, 10210001);
        Assert.assertNotNull(page);
    }

    @Test
    public void testDeleteUserfolder() {
        Integer[] id={1};
        UserfolderService.deleteUserfolder(id);
        Assert.assertTrue(true); 
    }

}
