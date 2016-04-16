/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.service;

import java.util.List;

import com.kohler.entity.PermissionEntity;

/**
 * Permission Interface
 *
 * @author zhangtingting
 * @Date 2014年9月25日
 */
public interface PermissionService {

	/**
	 * 根据用户Id，获取用户所具有的权限
	 * @param permissionIds
	 * @return
	 * @author zhangtingting Date 2014年9月28日
	 * @version
	 */
	public List<PermissionEntity> getListTmPermission(Integer[] permissionIds);

	
	/**
     * @return
     * @author zhangtingting Date 2014年9月28日
     * @version
     */
	public List<PermissionEntity> getPermissions();
	
	
	
	/**
     * 根据parent_id获取权限
     * @param parentId
     * @return
     * @author zhangtingting Date 2014年9月28日
     * @version
     */
	public List<PermissionEntity> listTmPermissionByParentId(Integer parentId);
	
	

	/**
     * @param permissionId
     * @return
     * @author zhangtingting Date 2014年9月28日
     * @version
     */
	public PermissionEntity getPermission(Integer permissionId);
	
	
	
	/**
     * @param permissionCode
     * @return
     * @author zhangtingting Date 2014年9月28日
     * @version
     */
	public PermissionEntity getPermissionBycode(String permissionCode);
	
}
