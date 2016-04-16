package com.kohler.service;

import java.util.List;
import java.util.Map;

import com.kohler.entity.SysUserEntity;

public interface SysUserService {

	public List<Map<String, Object>> getUsers();

	public boolean addUser(String username, String password,String realname,String remark);

	public boolean editUser(String username, String password, String realname,String remark, int id);

	public Map<String, Object> getUserById(int id);
	/**
	 * 根据用户名密码获取用户对象
	 * @param userName
	 * @param password
	 * @return
	 */
	public SysUserEntity getUserByNamePwd(String userName);
	
	/**
	 * 逻辑删除用户
	 * @param userId
	 * @return
	 */
	public int deleteSysUser(Integer userId);
	
}
