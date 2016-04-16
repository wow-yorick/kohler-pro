/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */

package com.kohler.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kohler.dao.FileAssetDao;
import com.kohler.dao.FolderDao;
import com.kohler.dao.MasterDataCodeDao;
import com.kohler.dao.utils.Pager;
import com.kohler.entity.FileAssetEntity;
import com.kohler.entity.FolderEntity;
import com.kohler.entity.MasterDataCodeConstant;
import com.kohler.pojo.FolderPojo;
import com.kohler.service.FolerService;

/**
 * Section Interface
 *
 * @author ys
 * @Date 2014年10月18日
 */

@Repository
public class FolerServiceImpl implements FolerService{
	@Autowired
	private FolderDao folderDao;
	@Autowired
	private MasterDataCodeDao masterDao;
	@Autowired
	private FileAssetDao fileDao;
	/**
	 * 树填充
	 */
	@Override
	public List<Map<String, Object>> getAllFolder(FolderEntity folder) {
		// TODO Auto-generated method stub
		return folderDao.getAllFolder(folder);
	}
	/**
	 * table填充 
	 */
	@Override
	public Pager<Map<String, Object>> getFolderList(
			Pager<Map<String, Object>> pager, FolderPojo folder) {
		// TODO Auto-generated method stub
		return  folderDao.getFolderList(pager, folder);
	}
	/**
	 * 删除folder
	 */
	@Override
	public int deleteFolder(FolderEntity folder) {
		// TODO Auto-generated method stub
		return folderDao.delteFolder(folder);
	}
	/**
	 * 下拉框填充
	 */
	@Override
	public List<Map<String, Object>> getAllTypeByMasterData() {
		// TODO Auto-generated method stub
		List<Object> MasterData = new ArrayList<Object>();
		MasterData.add(MasterDataCodeConstant.TYPE_FILE_ASSET);//所需数据常量
		MasterData.add(1);//所需数据语言
		return masterDao.getAllTypeByMasterData(MasterData);
	}
	/**
	 * 新增folder
	 */
	@Override
	public int createFolder(FolderEntity folder) {
		// TODO Auto-generated method stub
		return folderDao.createFolder(folder);
	}
	/**
	 * 查询folder
	 */
	@Override
	public FolderEntity searchOne(FolderEntity folder) {
		// TODO Auto-generated method stub
		return folderDao.searchOne(folder);
	}
	/**
	 * 修改folder
	 */
	@Override
	public int updateFolder(FolderEntity folder) {
		// TODO Auto-generated method stub
		return folderDao.updateFolder(folder);
	}
	/**
	 * 新增file asset
	 */
	@Override
	public int createFile(FileAssetEntity file) {
		// TODO Auto-generated method stub
		return fileDao.createFileAsset(file);
	}
	/**
	 * 修改file asset
	 */
	@Override
	public int updateFile(FileAssetEntity file) {
		// TODO Auto-generated method stub
		return fileDao.updateFileAsset(file);
	}
	@Override
	public List<Map<String, Object>> searchOneFile(FileAssetEntity file) {
		// TODO Auto-generated method stub
		return fileDao.searchOneFile(file);
	}
	@Override
	public int deleteFile(FileAssetEntity file) {
		// TODO Auto-generated method stub
		return fileDao.deleteFileAsset(file);
	}
}
