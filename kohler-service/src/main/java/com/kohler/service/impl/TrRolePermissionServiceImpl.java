package com.kohler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kohler.dao.TrRolePermissionDao;
import com.kohler.entity.TrRolePermission;
import com.kohler.service.TrRolePermissionService;

@Repository
public class TrRolePermissionServiceImpl implements TrRolePermissionService {
	
	@Autowired
	private TrRolePermissionDao rolePermissionRelDao;	

	@Override
	@Transactional
	public List<TrRolePermission> listRolePermissionRel(int roleId) {
		TrRolePermission rolePer=new TrRolePermission();
		rolePer.setRoleId(roleId);
		List<TrRolePermission> list=rolePermissionRelDao.selectByCondition(rolePer);
		return list;
	}

}
