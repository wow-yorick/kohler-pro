/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.kohler.constants.FolderSQL;
import com.kohler.dao.FolderDao;
import com.kohler.dao.utils.Pager;
import com.kohler.entity.FolderEntity;

/**
 * Class Function Description
 *
 * @author ys
 * @Date 2014年10月18日
 */
@Repository
public class FolderDaoImpl extends BaseDaoImpl<FolderEntity> implements FolderDao {

	@Override
	public List<Map<String, Object>> getAllFolder(FolderEntity folder) {
		// TODO Auto-generated method stub
		StringBuffer listSql = new StringBuffer(FolderSQL.GET_ALL_FOLDER);
		List<Object> list = new ArrayList<Object>();
		List<Map<String, Object>> categorylist = selectByConditionWithMap(listSql.toString(), list);
		return categorylist;
	}

	@Override
	public Pager<Map<String, Object>> getFolderList(Pager<Map<String, Object>> pager,FolderEntity folder) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer listSql = new StringBuffer(FolderSQL.GET_FOLDER_LIST);
		StringBuffer countSql = new StringBuffer("select COUNT(*) from ("+FolderSQL.GET_FOLDER_LIST);//(FolderSQL.GET_FOLDER_COUNT);
		if(folder.getFolderId() != null && !folder.getFolderId().equals("")){
			listSql.append(" and  f.PARENT_ID = ?");
			countSql.append(" and  f.PARENT_ID = ?");
			params.add(folder.getFolderId());
		}
		listSql.append(FolderSQL.GET_FOLDER_LIST_UNION);
		countSql.append(FolderSQL.GET_FOLDER_LIST_UNION);
		if(folder.getFolderId() != null && !folder.getFolderId().equals("")){
			listSql.append(" and  fa.FOLDER_ID = ?");
			countSql.append(" and  fa.FOLDER_ID = ?");
			params.add(folder.getFolderId());
		}
		countSql.append(" ) AS C");
//		if(template.getTemplateType() != null && !template.getTemplateType().equals("")){
//			listSql.append(" and TEMPLATE_TYPE like concat(concat('%',?),'%')");
//			countSql.append(" and TEMPLATE_TYPE like concat(concat('%',?),'%')");
//			params.add(template.getTemplateType());
//		}
		
		listSql.append(" LIMIT ?,?");
		int templateCount = selectCountByCondition(countSql.toString(), params);
		int pageCount = 0;
        if (templateCount % pager.getPageSize() == 0) {
            pageCount = templateCount / pager.getPageSize();
        } else {
            pageCount = templateCount / pager.getPageSize() + 1;
        }
        int index = (pager.getCurrentPage() - 1) * pager.getPageSize();
        params.add(index);
        params.add(pager.getPageSize());
		List<Map<String, Object>> list = selectByConditionWithMap(listSql.toString(), params);
		pager.setStartRow(index);
        pager.setDatas(list);
        pager.setTotalRecord(templateCount);
        pager.setTotalPage(pageCount);
		return pager;
	}

	@Override
	public int delteFolder(FolderEntity folder) {
		// TODO Auto-generated method stub
	    folder.setIsEnable(false);
		return update(folder);
	}

	@Override
	public int createFolder(FolderEntity folder) {
		// TODO Auto-generated method stub
		return insert(folder);
	}

	@Override
	public FolderEntity searchOne(FolderEntity folder) {
		// TODO Auto-generated method stub
		List<Object> params = new ArrayList<Object>();
		params.add(folder.getFolderId());
		List<FolderEntity> selectByCondition = selectByCondition(FolderSQL.SERACH_ONE, params);
		return (FolderEntity)selectByCondition.get(0);
	}

	@Override
	public int updateFolder(FolderEntity folder) {
		// TODO Auto-generated method stub
	    FolderEntity upFolder = selectById(folder.getFolderId());
	    upFolder.setFolderName(folder.getFolderName());
	    upFolder.setFileType(folder.getFileType());
	    upFolder.setFolderPath(folder.getFolderPath());
		return update(upFolder);
	}
	
}
