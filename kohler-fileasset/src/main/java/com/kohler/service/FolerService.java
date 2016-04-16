/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.service;

import java.util.List;
import java.util.Map;

import com.kohler.dao.utils.Pager;
import com.kohler.entity.FileAssetEntity;
import com.kohler.entity.FolderEntity;
import com.kohler.pojo.FolderPojo;

/**
 * foler Interface
 *
 * @author ys
 * @Date 2014年10月18日
 */
public interface FolerService {
	//获取树的数据进行填充
	public List<Map<String, Object>> getAllFolder(FolderEntity folder);
	//点击树时获取树下数据详细
	public Pager<Map<String, Object>> getFolderList(Pager<Map<String, Object>> pager,FolderPojo folder);
	//删除folder
	public int deleteFolder(FolderEntity folder);
	//填充下拉框
	public List<Map<String, Object>> getAllTypeByMasterData();
	//添加folder
	public int createFolder(FolderEntity folder);
	//根据id查询数据
	public FolderEntity searchOne(FolderEntity folder);
	//修改folder
	public int updateFolder(FolderEntity folder);
	//添加file
	public int createFile(FileAssetEntity file);
	//修改file
	public int updateFile(FileAssetEntity file);
	//修改file
	public int deleteFile(FileAssetEntity file);
	//id查询fileasset
	public List<Map<String, Object>> searchOneFile(FileAssetEntity file);
	
}
