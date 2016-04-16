package com.kohler.service.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kohler.dao.utils.Pager;
import com.kohler.entity.RoleEntity;
import com.kohler.entity.RoleRightEntity;
import com.kohler.service.RoleService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/*.xml"})
public class RoleServiceTest {
    /**
     * @throws java.lang.Exception
     */
    @Autowired
    public RoleService RoleService;
    @Test
    public void testGetRoleList() {
        Map<String,Object> map =new HashMap<String,Object>();
        map.put("rolename", "1111");
        Pager pager = new Pager();
        pager.setCurrentPage(1);
        pager.setPageSize(10);
        pager.setUrl("/");
        Pager<RoleEntity> page = RoleService.getRoleList(pager, map);
       
        Assert.assertNotNull(page);
    }

    @Test
    public void testGetUserTree() {
        List<Map<String,Object>> list=RoleService.getUserTree();
        Assert.assertNotNull(list);
    }

    @Test
    public void testAddRole() {
        RoleEntity re =new RoleEntity();
        re.setRoleName("1234");
       
        re=RoleService.addRole(re);
        Assert.assertNotNull(re);
    }

    @Test
    public void testEditRole() {
        RoleEntity re =new RoleEntity();
        re.setRoleId(1234);
       
        Integer i=RoleService.editRole(re);
        Assert.assertNotNull(i);
    }

    @Test
    public void testAddRoleRight() {
        RoleRightEntity rre=new RoleRightEntity();
        rre.setRoleId(1);
        rre.setRightId(2);
        rre=RoleService.addRoleRight(rre);
        Assert.assertNotNull(rre);
    }

   
    @Test
    public void testDeleteRole() {
        Integer i=RoleService.deleteRole("1,2", 1);
        Assert.assertNotNull(i);
    }

    @Test
    public void testGetRoleWithMap() {
        List<Map<String,Object>> list=RoleService.getRoleWithMap("1");
        Assert.assertNotNull(list);
    }

}
