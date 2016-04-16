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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kohler.dao.utils.Pager;
import com.kohler.entity.AccountEntity;

/**
 * Class Function Description
 *
 * @author Administrator
 * @Date 2014年10月21日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/*.xml"})
public class AccountServiceImplText {
    @Autowired 
    public AccountService AccountService;
    @Test
    public void testGetAccountListPage() {
        Pager pager = new Pager();
        pager.setUrl("/");
        Pager<Map<String, Object>> page = AccountService.getAccountListPage(pager, "name","138",133);
        Assert.assertNotNull(page);
    }
    
    @Test
    public void testGetAccountById() {
        List<Map<String, Object>> list= AccountService.EditViewAccountById(1);
        Assert.assertNotNull(list);
    }

    @Test
    public void testEditViewAccountById() {
        List<Map<String, Object>> list=AccountService.EditViewAccountById(1);
        Assert.assertNotNull(list);
    }

    @Test
    public void testUpdateAccount() {
        AccountEntity AccountEntity=new AccountEntity();
        AccountEntity.setAccountId(1);
        AccountEntity.setIsEnable(true);
        AccountEntity.setFreezeReason("111");
        AccountService.updateAccount(AccountEntity, 1);
        Assert.assertNotNull(AccountEntity);
    }

    @Test
    public void testRestpassword() {
        
        AccountEntity AccountEntity=new AccountEntity();
        AccountEntity.setAccountId(1);
        AccountEntity.setIsEnable(true);
        AccountEntity.setpassword("123");
        AccountService.restpassword(AccountEntity);
    }
    


}
