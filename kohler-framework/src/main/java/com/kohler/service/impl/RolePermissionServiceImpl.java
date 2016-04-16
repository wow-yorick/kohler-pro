package com.kohler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kohler.dao.RolePermissionDao;
import com.kohler.entity.RolePermissionEntity;
import com.kohler.service.RolePermissionService;

@Repository
public class RolePermissionServiceImpl implements RolePermissionService {
	
	@Autowired
	private RolePermissionDao rolePermissionRelDao;	

	@Override
	@Transactional
	public List<RolePermissionEntity> getListRolePermissionRel(int roleId) {
		RolePermissionEntity rolePer=new RolePermissionEntity();
		rolePer.setRoleId(roleId);
		List<RolePermissionEntity> list=rolePermissionRelDao.selectByCondition(rolePer);
		return list;
	}

}
