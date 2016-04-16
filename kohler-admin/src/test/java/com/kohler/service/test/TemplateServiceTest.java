/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kohler.dao.utils.Pager;
import com.kohler.entity.MasterDataCodeConstant;
import com.kohler.entity.TemplateEntity;
import com.kohler.service.TemplateService;

/**
 * Class Function Description
 *
 * @author Admin
 * @Date 2014年11月6日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/*.xml"})
public class TemplateServiceTest {
    @Autowired
    private TemplateService templateService;

    /**
     * Test method for {@link com.kohler.service.impl.TemplateServiceImpl#getAllTemplate(com.kohler.dao.utils.Pager, com.kohler.entity.TemplateEntity)}.
     */
    @Test
    public void testGetAllTemplate() {
        Pager<Map<String, Object>> pager = new Pager<Map<String,Object>>();
        TemplateEntity template = new TemplateEntity();
        Pager<Map<String, Object>> allTemplate = templateService.getAllTemplate(pager, template);
        assertNotNull(allTemplate);
    }

    /**
     * Test method for {@link com.kohler.service.impl.TemplateServiceImpl#createTemplate(com.kohler.entity.TemplateEntity)}.
     */
    @Test
    public void testCreateTemplate() {
        TemplateEntity template = new TemplateEntity();
        template.setTemplateId(1);
        TemplateEntity createTemplate = templateService.createTemplate(template);
        assertNotNull(createTemplate);
    }

    /**
     * Test method for {@link com.kohler.service.impl.TemplateServiceImpl#deleteTemplte(com.kohler.entity.TemplateEntity)}.
     */
    @Test
    public void testDeleteTemplte() {
        TemplateEntity template = new TemplateEntity();
        template.setTemplateId(1);
        int deleteTemplte = templateService.deleteTemplte(template);
        assertNotNull(deleteTemplte);
    }

    /**
     * Test method for {@link com.kohler.service.impl.TemplateServiceImpl#updateTemplte(com.kohler.entity.TemplateEntity)}.
     */
    @Test
    public void testUpdateTemplte() {
        TemplateEntity template = new TemplateEntity();
        template.setTemplateId(1);
        int updateTemplte = templateService.updateTemplte(template);
        assertNotNull(updateTemplte);
    }

    /**
     * Test method for {@link com.kohler.service.impl.TemplateServiceImpl#getAllType(java.util.List)}.
     */
    @Test
    public void testGetAllType() {
        List<Object> MasterData = new ArrayList<Object>();
        MasterData.add(MasterDataCodeConstant.TYPE_TEMPLATE_TYPE);//所需数据常量
        MasterData.add(2);//所需数据语言
        List<Map<String, Object>> allType = templateService.getAllType(MasterData);
        assertNotNull(allType);
    }

    /**
     * Test method for {@link com.kohler.service.impl.TemplateServiceImpl#getAllFolder(java.util.List)}.
     */
    @Test
    public void testGetAllFolder() {
        List<Object> MasterData = new ArrayList<Object>();
        MasterData.add(MasterDataCodeConstant.TYPE_TEMPLATE_TYPE);//所需数据常量
        MasterData.add(2);//所需数据语言
        List<Map<String, Object>> allType = templateService.getAllType(MasterData);
        assertNotNull(allType);
    }

    /**
     * Test method for {@link com.kohler.service.impl.TemplateServiceImpl#selectOne(java.util.List)}.
     */
    @Test
    public void testSelectOne() {
        List<Object> template = new ArrayList<Object>();
        template.add(1);
        List<Map<String, Object>> selectOne = templateService.selectOne(template);
        assertNotNull(selectOne);
    }

}
