/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohler.dao.SysUserDao;
import com.kohler.entity.SysUserEntity;
import com.kohler.service.SysUserService;

/**
 * SysUser Service
 * 
 * @author fujiajun
 * @Date 2014年11月24日
 */
@Service
public class SysUserServiceImpl implements SysUserService {

	@Autowired 
	public SysUserDao userDao;
	
	@Override
	public List<Map<String, Object>> getUsers() {
		return userDao.getAllUsers();
	}

	@Override
	public boolean addUser(String username, String password, String realname,String remark) {
		boolean flag = false;
		int result = userDao.insertUser(username,password,realname,remark);
		if(result == 1){
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean editUser(String username, String password, String realname,String remark, int id) {
		boolean flag = false;
		int result = userDao.updateUser(username,password,realname,remark,id);
		if(result == 1){
			flag = true;
		}
		return flag;
	}

	@Override
	public Map<String, Object> getUserById(int id) {
		return userDao.selectUserById(id);
	}

	@Override
	public SysUserEntity getUserByNamePwd(String userName) {
		SysUserEntity user=new SysUserEntity();
		user.setUserName(userName);
		user.setIsEnable(true);
	//	user.setPassword(password);
		List<SysUserEntity> list=userDao.selectByCondition(user);
		if(null !=list && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int deleteSysUser(Integer userId) {
		return userDao.deleteSysUser(userId);
	}

}
