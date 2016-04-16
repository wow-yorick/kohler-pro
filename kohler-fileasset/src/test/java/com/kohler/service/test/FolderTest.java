/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.test;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.kohler.dao.utils.Pager;
import com.kohler.entity.FileAssetEntity;
import com.kohler.entity.FolderEntity;
import com.kohler.pojo.FolderPojo;
import com.kohler.service.FolerService;

/**
 * Class Function Description
 *
 * @author Admin
 * @Date 2014年11月7日
 */
public class FolderTest {
    @Autowired
    private FolerService folderService;
    /**
     * Test method for {@link com.kohler.service.impl.FolerServiceImpl#getAllFolder(com.kohler.entity.FolderEntity)}.
     */
    @Test
    public void testGetAllFolder() {
        FolderEntity folder = new FolderEntity();
        List<Map<String, Object>> allFolder = folderService.getAllFolder(folder);
        assertNotNull(allFolder);
    }

    /**
     * Test method for {@link com.kohler.service.impl.FolerServiceImpl#getFolderList(com.kohler.dao.utils.Pager, com.kohler.pojo.FolderPojo)}.
     */
    @Test
    public void testGetFolderList() {
        Pager<Map<String, Object>> pager = new Pager<Map<String,Object>>();
        FolderPojo folder = new FolderPojo();
        Pager<Map<String, Object>> folderList = folderService.getFolderList(pager, folder);
        assertNotNull(folderList);
    }

    /**
     * Test method for {@link com.kohler.service.impl.FolerServiceImpl#deleteFolder(com.kohler.entity.FolderEntity)}.
     */
    @Test
    public void testDeleteFolder() {
        FolderEntity folder = new FolderEntity();
        folder.setFolderId(1);
        int deleteFolder = folderService.deleteFolder(folder);
        assertNotNull(deleteFolder);
    }

    /**
     * Test method for {@link com.kohler.service.impl.FolerServiceImpl#getAllTypeByMasterData()}.
     */
    @Test
    public void testGetAllTypeByMasterData() {
        List<Map<String, Object>> allTypeByMasterData = folderService.getAllTypeByMasterData();
        assertNotNull(allTypeByMasterData);
    }

    /**
     * Test method for {@link com.kohler.service.impl.FolerServiceImpl#createFolder(com.kohler.entity.FolderEntity)}.
     */
    @Test
    public void testCreateFolder() {
        FolderEntity folder = new FolderEntity();
        folder.setFolderName("123");
        int createFolder = folderService.createFolder(folder);
        assertNotNull(createFolder);
    }

    /**
     * Test method for {@link com.kohler.service.impl.FolerServiceImpl#searchOne(com.kohler.entity.FolderEntity)}.
     */
    @Test
    public void testSearchOne() {
        FolderEntity folder = new FolderEntity();
        folder.setFolderId(1);
        FolderEntity searchOne = folderService.searchOne(folder);
        assertNotNull(searchOne);
    }

    /**
     * Test method for {@link com.kohler.service.impl.FolerServiceImpl#updateFolder(com.kohler.entity.FolderEntity)}.
     */
    @Test
    public void testUpdateFolder() {
        FolderEntity folder = new FolderEntity();
        folder.setFolderId(1);
        int updateFolder = folderService.updateFolder(folder);
        assertNotNull(updateFolder);
    }

    /**
     * Test method for {@link com.kohler.service.impl.FolerServiceImpl#createFile(com.kohler.entity.FileAssetEntity)}.
     */
    @Test
    public void testCreateFile() {
        FileAssetEntity file = new FileAssetEntity();
        file.setFileAssetName("123");
        int createFile = folderService.createFile(file);
        assertNotNull(createFile);
    }

    /**
     * Test method for {@link com.kohler.service.impl.FolerServiceImpl#updateFile(com.kohler.entity.FileAssetEntity)}.
     */
    @Test
    public void testUpdateFile() {
        FileAssetEntity file = new FileAssetEntity();
        file.setFileAssetId(1);
        int updateFile = folderService.updateFile(file);
        assertNotNull(updateFile);
    }

    /**
     * Test method for {@link com.kohler.service.impl.FolerServiceImpl#searchOneFile(com.kohler.entity.FileAssetEntity)}.
     */
    @Test
    public void testSearchOneFile() {
        FileAssetEntity file = new FileAssetEntity();
        file.setFileAssetId(1);
        List<Map<String, Object>> searchOneFile = folderService.searchOneFile(file);
        assertNotNull(searchOneFile);
    }

    /**
     * Test method for {@link com.kohler.service.impl.FolerServiceImpl#deleteFile(com.kohler.entity.FileAssetEntity)}.
     */
    @Test
    public void testDeleteFile() {
        FileAssetEntity file = new FileAssetEntity();
        file.setFileAssetId(1);
        int deleteFile = folderService.deleteFile(file);
        assertNotNull(deleteFile);
    }

}
