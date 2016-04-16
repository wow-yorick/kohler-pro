package com.kohler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohler.dao.UserRoleDao;
import com.kohler.entity.UserRoleEntity;
import com.kohler.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {
	
	@Autowired
	private UserRoleDao userRoleRelDao;

	@Override
	public List<UserRoleEntity> getListUserRole(int userId) {
		UserRoleEntity userRole=new UserRoleEntity();
		userRole.setSysUserId(userId);
		List<UserRoleEntity> list=userRoleRelDao.selectByCondition(userRole);
		return list;
	}

}
