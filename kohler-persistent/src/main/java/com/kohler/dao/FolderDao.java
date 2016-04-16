/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.dao;


import java.util.List;
import java.util.Map;
import com.kohler.dao.utils.Pager;
import com.kohler.entity.FolderEntity;

/**
 * SectionDao Interface
 *
 * @author ys
 * @Date 2014年10月18日
 */
public interface FolderDao extends BaseDao<FolderEntity>{
	//获取树的数据进行填充
	public List<Map<String, Object>> getAllFolder(FolderEntity folder);
	//点击树时获取树下数据详细
	public Pager<Map<String, Object>> getFolderList(Pager<Map<String, Object>> pager,FolderEntity folder);
	//删除
	public int delteFolder(FolderEntity folder);
	//添加
	public int createFolder(FolderEntity folder);
	//根据id查询数据
	public FolderEntity searchOne(FolderEntity folder);
	//修改
	public int updateFolder(FolderEntity folder);


}
