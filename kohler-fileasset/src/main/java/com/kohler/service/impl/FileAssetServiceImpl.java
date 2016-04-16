/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */

package com.kohler.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kohler.dao.FolderDao;
import com.kohler.dao.utils.Pager;
import com.kohler.entity.FolderEntity;
import com.kohler.pojo.FolderPojo;
import com.kohler.service.FileAssetService;

/**
 * Section Interface
 *
 * @author ys
 * @Date 2014年10月22日
 */

@Repository
public class FileAssetServiceImpl implements FileAssetService{
	@Autowired
	private FolderDao foldeDao;
	
	@Override
	public Pager<Map<String, Object>> getFolderList(
			Pager<Map<String, Object>> pager, FolderPojo folder) {
		// TODO Auto-generated method stub
		return foldeDao.getFolderList(pager, folder);
	}

	@Override
	public List<Map<String, Object>> getAllFolder(FolderEntity folder) {
		// TODO Auto-generated method stub
		return foldeDao.getAllFolder(folder);
	}
}
