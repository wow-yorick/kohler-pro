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
import org.springframework.beans.factory.annotation.Autowired;

import com.kohler.dao.utils.Pager;
import com.kohler.entity.AggMasterdataMetadataEntity;
import com.kohler.entity.MasterdataEntity;
import com.kohler.entity.MasterdataMetadataEntity;
import com.kohler.service.MasterDataMetaDataService;

/**
 * Class Function Description
 *
 * @author Admin
 * @Date 2014年11月7日
 */
public class MasterDataMetaDataTest {
    @Autowired
    private MasterDataMetaDataService masterService;

    /**
     * Test method for {@link com.kohler.service.impl.MasterDataMetaDataServiceImpl#getAllMasterdata(com.kohler.dao.utils.Pager, com.kohler.entity.AggMasterdataMetadataEntity)}.
     */
    @Test
    public void testGetAllMasterdata() {
        Pager<Map<String, Object>> pager = new Pager<Map<String,Object>>();
        AggMasterdataMetadataEntity master = new AggMasterdataMetadataEntity();
        Pager<Map<String, Object>> allMasterdata = masterService.getAllMasterdata(pager, master);
        assertNotNull(allMasterdata);
    }

    /**
     * Test method for {@link com.kohler.service.impl.MasterDataMetaDataServiceImpl#getAllType(java.util.List)}.
     */
    @Test
    public void testGetAllType() {
        List<Object> MasterData = new ArrayList<Object>();
        List<Map<String, Object>> allType = masterService.getAllType(MasterData);
        assertNotNull(allType);
    }

    /**
     * Test method for {@link com.kohler.service.impl.MasterDataMetaDataServiceImpl#createMasterdataMateDeta(com.kohler.entity.MasterdataMetadataEntity)}.
     */
    @Test
    public void testCreateMasterdataMateDeta() {
        MasterdataMetadataEntity masterData = new MasterdataMetadataEntity();
        masterData.setMasterdataMetadataId(1);
        MasterdataMetadataEntity createMasterdataMateDeta = masterService.createMasterdataMateDeta(masterData);
        assertNotNull(createMasterdataMateDeta);
    }

    /**
     * Test method for {@link com.kohler.service.impl.MasterDataMetaDataServiceImpl#deleteMasterdataMateDeta(com.kohler.entity.MasterdataMetadataEntity)}.
     */
    @Test
    public void testDeleteMasterdataMateDeta() {
        MasterdataMetadataEntity masterData = new MasterdataMetadataEntity();
        masterData.setMasterdataMetadataId(1);
        int deleteMasterdataMateDeta = masterService.deleteMasterdataMateDeta(masterData);
        assertNotNull(deleteMasterdataMateDeta); 
    }

    /**
     * Test method for {@link com.kohler.service.impl.MasterDataMetaDataServiceImpl#getAllLocale()}.
     */
    @Test
    public void testGetAllLocale() {
        List<Map<String, Object>> allLocale = masterService.getAllLocale();
        assertNotNull(allLocale); 
    }

    /**
     * Test method for {@link com.kohler.service.impl.MasterDataMetaDataServiceImpl#updateMasterdataMateDeta(com.kohler.entity.MasterdataMetadataEntity, java.lang.String[])}.
     */
    @Test
    public void testUpdateMasterdataMateDeta() {
        MasterdataMetadataEntity masterData = new MasterdataMetadataEntity();
        String[] masterName = {"1","2"};
        int updateMasterdataMateDeta = masterService.updateMasterdataMateDeta(masterData, masterName);
        assertNotNull(updateMasterdataMateDeta); 
    }

    /**
     * Test method for {@link com.kohler.service.impl.MasterDataMetaDataServiceImpl#selectOne(java.util.List)}.
     */
    @Test
    public void testSelectOne() {
        List<Object> MasterData = new ArrayList<Object>();
        MasterData.add(1);
        List<Map<String, Object>> selectOne = masterService.selectOne(MasterData);
        assertNotNull(selectOne); 
    }

    /**
     * Test method for {@link com.kohler.service.impl.MasterDataMetaDataServiceImpl#updateMasterdata(com.kohler.entity.MasterdataMetadataEntity, java.util.List)}.
     */
    @Test
    public void testUpdateMasterdata() {
        MasterdataMetadataEntity masterData = new MasterdataMetadataEntity();
        masterData.setMasterdataMetadataId(1);
        List<MasterdataEntity> masterdata = new ArrayList<MasterdataEntity>();
        MasterdataEntity me1 = new MasterdataEntity();
        MasterdataEntity me2 = new MasterdataEntity();
        me1.setMasterdataId(1);
        me2.setMasterdataId(2);
        masterdata.add(me1);
        masterdata.add(me2);
        int updateMasterdata = masterService.updateMasterdata(masterData, masterdata);
        assertNotNull(updateMasterdata);
    }

}
