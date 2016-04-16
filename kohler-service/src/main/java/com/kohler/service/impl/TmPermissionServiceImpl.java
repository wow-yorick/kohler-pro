package com.kohler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohler.dao.TmPermissionDao;
import com.kohler.entity.TmPermission;
import com.kohler.service.TmPermissionService;

@Service
public class TmPermissionServiceImpl implements TmPermissionService {
	
	@Autowired
	private TmPermissionDao userPermissionDao;

	@Override
	public List<TmPermission> listTmPermission(Integer[] permissionIds) {
		return userPermissionDao.listTmPermission(permissionIds);
	}

	@Override
	public List<TmPermission> getPermissions() {
		return userPermissionDao.selectAll();
	}

	@Override
	public TmPermission getPermission(Integer permissionId) {
		return userPermissionDao.selectById(permissionId);
	}

	@Override
	public List<TmPermission> listTmPermissionByParentId(Integer parentId) {
		TmPermission tmPermission=new TmPermission();
		tmPermission.setParentId(parentId);
		return userPermissionDao.selectByCondition(tmPermission);
	}

	@Override
	public TmPermission getPermissionBycode(String permissionCode) {
		TmPermission tmPermission=new TmPermission();
		tmPermission.setPermissionCode(permissionCode);
		List<TmPermission> list=userPermissionDao.selectByCondition(tmPermission);
		if(null != list && list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
