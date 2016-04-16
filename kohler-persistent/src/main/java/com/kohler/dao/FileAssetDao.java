/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.dao;
import java.util.List;
import java.util.Map;

import com.kohler.entity.FileAssetEntity;


/**
 * Page Dao 
 *
 * @author ys
 * @Date 2014-10-22
 */
public interface FileAssetDao extends BaseDao<FileAssetEntity>{
    //新增
	public int createFileAsset(FileAssetEntity file);
	//修改
	public int updateFileAsset(FileAssetEntity file);
	//删除
	public int deleteFileAsset(FileAssetEntity file);
	//id查询
	public List<Map<String, Object>> searchOneFile(FileAssetEntity file);
	//name查询
	public List<FileAssetEntity> selectFileByName(FileAssetEntity file);
	//页面初始化查询
	public List<FileAssetEntity> selectFileById(String fileAssetId);
}
