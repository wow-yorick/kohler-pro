/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kohler.bean.Field;
import com.kohler.entity.ContentFieldValues;
import com.kohler.entity.ContentMetadataEntity;
import com.kohler.entity.TemplateEntity;
import com.kohler.entity.extend.PagePojo;
import com.kohler.pojo.DataTypePojo;

/**
 * Class Function Description
 *
 * @author kangmin_Qu
 * @Date 2014-11-3
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/*.xml"})
public class PageServiceTest {

    /**
     * @throws java.lang.Exception
     * @author kangmin_Qu
     * Date 2014-11-3
     * @version
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @Autowired
    public PageService pageService;
    
    /**
     * Test method for {@link com.kohler.service.impl.PageServiceImpl#getContentMetadataList(java.lang.Integer, java.util.Map)}.
     */
    @Test
    public void testGetContentMetadataList() {
        Integer dataTypeId = 93;
        Map<String,List<Map<String,Object>>> map = pageService.getContentMetadataList(dataTypeId, null);
        assertEquals(map.size() > 0 ,true);
    }

    /**
     * Test method for {@link com.kohler.service.impl.PageServiceImpl#getFieldValuesById(java.lang.Integer)}.
     */
    @Test
    public void testGetFieldValuesById() {
        Integer id = 92;
        Map<String, List<ContentFieldValues>> map = pageService.getFieldValuesById(id);
        assertEquals(map.size() > 0 ,true);
    }

    /**
     * Test method for {@link com.kohler.service.impl.PageServiceImpl#insertContentFieldValues(java.lang.Integer, java.util.Map)}.
     */
    @Test
    public void testInsertContentFieldValues() {
        Integer dataTypeId = 90;
        Map<String, Field> map = new HashMap<String,Field>();
        Field field = new Field();
        field.setEditorType("1");
        field.setName("Test");
        field.setValue("Test");
        map.put("Key", field);
        Integer i = this.pageService.insertContentFieldValues(dataTypeId, map);
        assertNotNull(i);
    }

    /**
     * Test method for {@link com.kohler.service.impl.PageServiceImpl#getFieldValue(java.lang.Integer, java.lang.String)}.
     */
    @Test
    public void testGetFieldValue() {
        Integer contentId = 1;
        String fieldName= "image_alt";
        String value = this.pageService.getFieldValue(contentId, fieldName);
        assertNotNull(value);
    }

    /**
     * Test method for {@link com.kohler.service.impl.PageServiceImpl#deleteContentById(java.lang.Integer)}.
     */
    @Test
    public void testDeleteContentById() {
        this.pageService.deleteContentById(22);
        ContentMetadataEntity c = this.pageService.getContentMetadataById(22);
        assertNull(c);
    }

    /**
     * Test method for {@link com.kohler.service.impl.PageServiceImpl#getDataType()}.
     */
    @Test
    public void testGetDataType() {
        List<DataTypePojo> list = this.pageService.getDataType();
        assertEquals(list.size() > 0 ,true);
        for(DataTypePojo pojo : list){
            assertNotNull(pojo.getDatatypeId());
        }
    }

    /**
     * Test method for {@link com.kohler.service.impl.PageServiceImpl#getTemplateList()}.
     */
    @Test
    public void testGetTemplateList() {
        List<TemplateEntity> list = this.pageService.getTemplateList();
        assertEquals(list.size() > 0 ,true);
        for(TemplateEntity entity : list){
            assertNotNull(entity.getTemplateId());
        }
    }

    /**
     * Test method for {@link com.kohler.service.impl.PageServiceImpl#insertPage(com.kohler.entity.extend.PagePojo)}.
     * @throws UnsupportedEncodingException 
     */
    @Test
    public void testInsertPage() throws UnsupportedEncodingException {
        PagePojo pagePojo = new PagePojo();
        pagePojo.setPageName("This is junit Test");
        pagePojo.setCreateTime(new Date());
        Integer id = this.pageService.insertPage(pagePojo,"");
        PagePojo p = this.pageService.getPageById(id);
        assertNotNull(p.getPageId());
    }

    /**
     * Test method for {@link com.kohler.service.impl.PageServiceImpl#getPageById(java.lang.Integer)}.
     */
    @Test
    public void testGetPageById() {
        Integer pageId = 1;
        PagePojo p = this.pageService.getPageById(pageId);
        assertNotNull(p.getPageId());
    }

    /**
     * Test method for {@link com.kohler.service.impl.PageServiceImpl#editPage(com.kohler.entity.extend.PagePojo)}.
     * @throws UnsupportedEncodingException 
     */
    @Test
    public void testEditPage() throws UnsupportedEncodingException {
        String testName = "This is pageUnit 2";
        Integer pageId = 1;
        PagePojo p = this.pageService.getPageById(pageId);
        assertNotNull(p.getPageId());
        p.setPageName(testName);
        this.pageService.editPage(p,"");
        PagePojo p2 = this.pageService.getPageById(pageId);
        assertEquals(testName, p2.getPageName());
    }

    /**
     * Test method for {@link com.kohler.service.impl.PageServiceImpl#geteDataTypeById(java.lang.Integer)}.
     */
    @Test
    public void testGeteDataTypeById() {
        Integer id = 93;
        DataTypePojo pojo = this.pageService.geteDataTypeById(id);
        assertEquals(id,pojo.getDatatypeId());
        
    }

    /**
     * Test method for {@link com.kohler.service.impl.PageServiceImpl#getContentMetadataById(java.lang.Integer)}.
     */
    @Test
    public void testGetContentMetadataById() {
        Integer id = 1;
        ContentMetadataEntity c = this.pageService.getContentMetadataById(id);
        assertEquals(id,c.getContentMetadataId());
    }

}
