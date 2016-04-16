package com.kohler.dao;

import java.util.List;
import java.util.Map;

import com.kohler.entity.PermissionEntity;

public interface PermissionDao extends BaseDao<PermissionEntity>{
	
	/**
	 * 根据用户Id，获取用户所具有的权限
	 * @param permissionIds
	 * @return
	 */
	public List<PermissionEntity> listTmPermission(Integer[] permissionIds);
	
	public List<Map<String, Object>> getlistdata();

   

    
}
