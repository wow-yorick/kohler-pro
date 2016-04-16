package com.kohler.service;

import java.util.List;

import com.kohler.entity.TmPermission;

public interface TmPermissionService {

	/**
	 * 根据用户Id，获取用户所具有的权限
	 * @param permissionIds
	 * @return
	 */
	public List<TmPermission> listTmPermission(Integer[] permissionIds);

	public List<TmPermission> getPermissions();
	
	/**
	 * 根据parent_id获取权限
	 * @param parentId
	 * @return
	 */
	public List<TmPermission> listTmPermissionByParentId(Integer parentId);
	
	public TmPermission getPermission(Integer permissionId);
	
	public TmPermission getPermissionBycode(String permissionCode);
	
}
