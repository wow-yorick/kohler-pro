package com.kohler.service;

import java.util.List;

import com.kohler.entity.TrRolePermission;

public interface TrRolePermissionService {

	/*
	 * 获取某个角色所有的权限id
	 */
	public List<TrRolePermission> listRolePermissionRel(int roleId);
}
