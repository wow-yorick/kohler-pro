/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.service;
import java.util.List;
import java.util.Map;
import com.kohler.dao.utils.Pager;
import com.kohler.entity.FolderEntity;
import com.kohler.pojo.FolderPojo;

/**
 * foler Interface
 *
 * @author ys
 * @Date 2014年10月22日
 */
public interface FileAssetService {
	//点击树时获取树下数据详细
	public Pager<Map<String, Object>> getFolderList(Pager<Map<String, Object>> pager,FolderPojo folder);
	//获取树的数据进行填充
	public List<Map<String, Object>> getAllFolder(FolderEntity folder);
}
