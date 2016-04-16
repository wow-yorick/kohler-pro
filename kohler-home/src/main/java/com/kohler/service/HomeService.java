package com.kohler.service;

import com.kohler.entity.SysUserEntity;

/**
 * 
 * @author wuyun
 *
 */
public interface HomeService {
	
	/**
	 * @author wuyun
	 * @param sysUserId
	 * @return
	 */
	public SysUserEntity getSysUserById(Integer sysUserId);
	
	/**
	 * @author wuyun
	 * @param sysUser Modify
	 * @return
	 */
	public  Integer editSysUser(SysUserEntity sysUser);

}
