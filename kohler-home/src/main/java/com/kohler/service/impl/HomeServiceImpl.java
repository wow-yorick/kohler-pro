package com.kohler.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohler.dao.impl.SysUserDaoImpl;
import com.kohler.entity.SysUserEntity;
import com.kohler.service.HomeService;

@Service
public class HomeServiceImpl implements HomeService {
	@Autowired
	private SysUserDaoImpl sysUserDaoImpl;

	/**
	 * {@inheritDoc}
	 */
	public SysUserEntity getSysUserById(Integer sysUserId) {
		
		return sysUserDaoImpl.selectById(sysUserId);
	}
	
	public Integer editSysUser(SysUserEntity sysUser) {
		return sysUserDaoImpl.update(sysUser);
	}

}
