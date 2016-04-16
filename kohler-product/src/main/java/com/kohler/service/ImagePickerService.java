package com.kohler.service;

import java.util.List;

import com.kohler.entity.FileAssetEntity;

/**
 * Page Interface
 *
 * @author ys
 * @Date 2014年10月26日
 */
public interface ImagePickerService {
	//查询并显示
	public List<FileAssetEntity> selectFileByName(FileAssetEntity file);
	
	public List<FileAssetEntity> selectFileById(String fileAssetId);
}
