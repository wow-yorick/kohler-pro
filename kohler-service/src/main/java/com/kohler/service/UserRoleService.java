package com.kohler.service;

import java.util.List;

import com.kohler.entity.UserRole;

public interface UserRoleService {

	/*
	 * 获取某个用户所有的角色id
	 */
	public List<UserRole> listUserRole(int userId);
}
