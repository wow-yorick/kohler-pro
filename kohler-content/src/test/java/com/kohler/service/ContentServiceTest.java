package com.kohler.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.kohler.dao.utils.Pager;
import com.kohler.entity.ContentFieldValues;
import com.kohler.entity.ContentMetadataEntity;
import com.kohler.entity.DatatypeEntity;
import com.kohler.entity.MasterdataEntity;

/**
 * 
 * ContentServiceTest test
 *
 * @author sana
 * @Date 2014年11月15日
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/*.xml" })
public class ContentServiceTest {

    @Autowired
    private ContentService contentService;

    /**
     * Test method for
     * {@link com.kohler.service.impl.ContentServiceImpl#getContentWithMap(java.util.Map,java.util.List)}
     * .
     * 
     * @author sana Date 2014年11月15日
     * @version
     */
    @Test
    public void testGetContentWithMap() {
        List<Map<String, Object>> list = contentService.getContentWithMap();
        for (Map<String, Object> map : list) {
            assertNotNull(map);
        }
    }

    /**
     * Test method for
     * {@link com.kohler.service.impl.ContentServiceImpl#getContentPageById(java.util.Map,com.kohler.dao.utils.Pager)}
     * .
     * 
     * @author sana Date 2014年11月15日
     * @version
     * @throws ParseException
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testGetContentPageById() throws ParseException, UnsupportedEncodingException {
        Pager<Map<String, Object>> pager = new Pager<Map<String, Object>>();
        Integer datatypeId = 93;
        List<String> fieldlist = new ArrayList<String>();
        String selStr = "";
        Pager<Map<String, Object>> p = contentService.getContentPageById(pager, datatypeId,
                fieldlist, selStr);
        for (Map<String, Object> l : p.getDatas()) {
            assertNotNull(l);
        }
    }

    /**
     * Test method for
     * {@link com.kohler.service.impl.ContentServiceImpl#getDatatypeById(com.kohler.entity.DatatypeEntity)}
     * .
     * 
     * @author sana Date 2014年11月15日
     * @version
     * @throws ParseException
     */
    @Test
    public void testGetDatatypeById() {
        Integer datatypeId = 93;
        DatatypeEntity d = contentService.getDatatypeById(datatypeId);
        assertEquals(d.getDatatypeId().toString(), "93");
    }

    /**
     * Test method for {@link
     * com.kohler.service.impl.ContentServiceImpl#saveContent(java.lang.int)}.
     * 
     * @author sana Date 2014年11月15日
     * @version
     * @throws UnsupportedEncodingException
     * @throws ParseException
     */
    @Rollback(true)
    @Test
    public void testSaveContent() throws UnsupportedEncodingException {
        ContentMetadataEntity c = new ContentMetadataEntity();
        String selStr = "";
        List<Map<String, Object>> l = new ArrayList<Map<String, Object>>();
        int i = contentService.saveContent(c, selStr, l);
        assertNotNull(i);
    }

    /**
     * Test method for
     * {@link com.kohler.service.impl.ContentServiceImpl#getContentMetadataById(com.kohler.entity.ContentMetadataEntity)}
     * .
     * 
     * @author sana Date 2014年11月15日
     * @version
     * @throws ParseException
     */
    @Test
    public void testGetContentMetadataById() {
        Integer contentMetadataId = 47;
        ContentMetadataEntity c = contentService.getContentMetadataById(contentMetadataId);
        assertEquals(c.getContentMetadataId().toString(), "47");
    }

    /**
     * Test method for
     * {@link com.kohler.service.impl.ContentServiceImpl#getContentFieldValuesByMetadataId(com.kohler.entity.ContentFieldValues,java.util.List)}
     * .
     * 
     * @author sana Date 2014年11月15日
     * @version
     * @throws ParseException
     */
    @Test
    public void testGetContentFieldValuesByMetadataId() {
        Integer contentMetadataId = 47;
        List<ContentFieldValues> list = contentService
                .getContentFieldValuesByMetadataId(contentMetadataId);
        for (ContentFieldValues c : list) {
            assertEquals(c.getContentMetadataId().toString(), "47");
        }
    }

    /**
     * Test method for
     * {@link com.kohler.service.impl.ContentServiceImpl#deleteContent}.
     * 
     * @author sana Date 2014年11月15日
     * @version
     * @throws UnsupportedEncodingException
     * @throws ParseException
     */
    @Rollback(true)
    @Test
    public void testDeleteContent() throws UnsupportedEncodingException {
        Map<String, Object> m = new HashMap<String, Object>();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        m.put("id", 68);
        list.add(m);
        contentService.deleteContent(list);
    }

    /**
     * Test method for
     * {@link com.kohler.service.impl.ContentServiceImpl#getMasterdataByName(com.kohler.entity.MasterdataEntity,java.util.List)}
     * .
     * 
     * @author sana Date 2014年11月15日
     * @version
     * @throws ParseException
     */
    @Test
    public void testGetMasterdataByName() {
        String masterdataTypeName = "TYPE_WINDOW_TARGET";
        String lang = "1";
        List<MasterdataEntity> list = contentService.getMasterdataByName(masterdataTypeName, lang);
        for (MasterdataEntity m : list) {
            assertEquals(m.getLang().toString(), "1");
        }
    }

    /**
     * Test method for
     * {@link com.kohler.service.impl.ContentServiceImpl#checkContentValue(com.kohler.entity.ContentFieldValues,java.util.List)}
     * .
     * 
     * @author sana Date 2014年11月15日
     * @version
     * @throws ParseException
     */
    @Test
    public void testCheckContentValue() {
        String fieldname = "Title";
        String fieldvalue = "首页Hero Area1";
        String lang = "1";
        String metadataId = "1";
        String datatypeId = "93";
        List<ContentFieldValues> list = contentService.checkContentValue(fieldname, fieldvalue,
                lang, metadataId,datatypeId);
        for (ContentFieldValues c : list) {
            assertEquals(c.getFieldValue(), "首页Hero Area1");
        }
    }
}
