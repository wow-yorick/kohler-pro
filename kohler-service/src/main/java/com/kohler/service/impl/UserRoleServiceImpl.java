package com.kohler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohler.dao.UserRoleDao;
import com.kohler.entity.UserRole;
import com.kohler.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {
	
	@Autowired
	private UserRoleDao userRoleRelDao;

	@Override
	public List<UserRole> listUserRole(int userId) {
		UserRole userRole=new UserRole();
		userRole.setSysUserId(userId);
		List<UserRole> list=userRoleRelDao.selectByCondition(userRole);
		return list;
	}

}
