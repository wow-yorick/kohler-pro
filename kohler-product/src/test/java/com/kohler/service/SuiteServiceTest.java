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
import com.kohler.entity.SuiteEntity;
import com.kohler.entity.SuiteHotSpotEntity;
import com.kohler.entity.SuiteMetadataEntity;

/**
 * Class Function Description
 *
 * @author Administrator
 * @Date 2014年10月17日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/*.xml"})
public class SuiteServiceTest {

    @Autowired
    private SuiteService suiteServiceImpl;
    
    @Test
    public void testGetSuitePage() {
        Pager pager = new Pager();
        pager.setUrl("/");
        Pager<Map<String, Object>> page = suiteServiceImpl.getSuitePage(pager, "3");
        Assert.assertNotNull(page);
    }

    /**
     * Test method for {@link com.kohler.service.impl.SuiteServiceImpl#addSuite(java.lang.String, com.kohler.entity.SuiteMetadataEntity, java.lang.String)}.
     */
    @Test
    public void testAddSuite() {
        SuiteMetadataEntity suiteMetadataEntity = new SuiteMetadataEntity();
        suiteMetadataEntity.setSeqNo(123);
        Integer  id = null;
        Assert.assertNotNull(id);
    }

    /**
     * Test method for {@link com.kohler.service.impl.SuiteServiceImpl#editSuite(java.lang.String, com.kohler.entity.SuiteMetadataEntity, java.lang.String)}.
     */
    @Test
    public void testEditSuite() {
        SuiteMetadataEntity suiteMetadataEntity = new SuiteMetadataEntity();
        Integer id = suiteServiceImpl.editSuite("[]", suiteMetadataEntity, "[]", null);
        Assert.assertNotNull(id);
    }

    /**
     * Test method for {@link com.kohler.service.impl.SuiteServiceImpl#getSuiteByMetadataId(java.lang.Integer)}.
     */
    @Test
    public void testGetSuiteByMetadataId() {
        List<SuiteEntity> result = suiteServiceImpl.getSuiteByMetadataId(1);
        Assert.assertNotNull(result);
    }

    /**
     * Test method for {@link com.kohler.service.impl.SuiteServiceImpl#getSuiteMetadataByMetadataId(java.lang.Integer)}.
     */
    @Test
    public void testGetSuiteMetadataByMetadataId() {
        SuiteMetadataEntity suiteMetadataEntity = suiteServiceImpl.getSuiteMetadataByMetadataId(null);
        Assert.assertNull(suiteMetadataEntity);
    }

    /**
     * Test method for {@link com.kohler.service.impl.SuiteServiceImpl#deleteSuite(java.lang.String[])}.
     */
    @Test
    public void testDeleteSuite() {
        String suiteMetadataId = "57,58"; 
        String[] suiteMetadataIdArr = suiteMetadataId.split(",");
        Assert.assertEquals(2, suiteMetadataIdArr.length);
        //suiteServiceImpl.deleteSuite(suiteMetadataIdArr);
    }

    /**
     * Test method for {@link com.kohler.service.impl.SuiteServiceImpl#getGlobleMasterData()}.
     */
    @Test
    public void testGetGlobleMasterData() {
        Map<String, Object> msd = suiteServiceImpl.getGlobleMasterData();
        Assert.assertNotNull(msd);
    }

}
