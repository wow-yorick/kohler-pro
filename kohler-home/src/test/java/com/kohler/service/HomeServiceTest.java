package com.kohler.service;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kohler.entity.SysUserEntity;
/**
 * @date 2014-11-10 
 * @author wuyun
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/*.xml"})
public class HomeServiceTest {
	@Autowired
	private HomeService homeServiceImpl;
	
	@Test
	public void getSysUserById() {
		Integer sysUserId = 1;
		SysUserEntity users=homeServiceImpl.getSysUserById(sysUserId);
		assertEquals(users,null);
	}
	
	@Test
    public void testEditSuite() {
		SysUserEntity sysUser = new SysUserEntity();
        Integer id = homeServiceImpl.editSysUser(sysUser);
        Assert.assertNotNull(id);
    }
}
