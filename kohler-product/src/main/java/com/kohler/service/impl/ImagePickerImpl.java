/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohler.dao.FileAssetDao;
import com.kohler.entity.FileAssetEntity;
import com.kohler.service.ImagePickerService;

/**
 * Page Interface Impl
 *
 * @author ys
 * @Date 2014年9月28日
 */
@Service
public class ImagePickerImpl implements ImagePickerService {
	@Autowired
	private FileAssetDao fileDao;
	
	@Override
	public List<FileAssetEntity> selectFileByName(FileAssetEntity file) {
		// TODO Auto-generated method stub
		return fileDao.selectFileByName(file);
	}
	
	@Override
    public List<FileAssetEntity> selectFileById(String fileAssetId){
	    return fileDao.selectFileById(fileAssetId);
	}
}
