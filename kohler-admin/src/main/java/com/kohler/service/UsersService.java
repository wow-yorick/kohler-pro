/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service;

import java.util.List;
import java.util.Map;

import com.kohler.dao.utils.Pager;
import com.kohler.entity.RoleEntity;
import com.kohler.entity.SysUserEntity;
import com.kohler.entity.UserRoleEntity;

/**
 * users service
 *
 * @author Administrator
 * @Date 2014年11月18日
 */
public interface UsersService {

    /**
     * 获取分页列表
     * @param pager
     * @param sysUserEntity
     * @return
     * @author Administrator
     * Date 2014年11月18日
     * @version
     */
    public Pager<Map<String, Object>> getUsersPage(Pager<Map<String, Object>> pager, SysUserEntity sysUserEntity);

    /**
     * 新增用户
     * @param sysUserEntity
     * @author Administrator
     * Date 2014年11月18日
     * @version
     */
    public void addUser(SysUserEntity sysUserEntity, String userRole);
    
    /**
     * 获取用户详情
     * @param sysUserId
     * @return
     * @author Administrator
     * Date 2014年11月18日
     * @version
     */
    public SysUserEntity getUserDetail(Integer sysUserId);
    
    /**
     * 获取用户角色列表
     * @param sysUserId
     * @return
     * @author Administrator
     * Date 2014年11月18日
     * @version
     */
    public List<UserRoleEntity> getUserRoleList(Integer sysUserId);

    /**
     * 编辑用户
     * @param sysUserEntity
     * @param userRole
     * @author Administrator
     * Date 2014年11月18日
     * @version
     */
    public void editUser(SysUserEntity sysUserEntity, String userRole);

	/**
	 * @return
	 * @author XHY
	 * Date 2014年11月22日
	 * @version
	 */
	public List<RoleEntity> getRoles();

	/**
	 * @param sysUserIds
	 * @return
	 * @author XHY
	 * Date 2014年11月22日
	 * @version
	 */
	public boolean deleteUserByIds(Integer[] sysUserIds);

	/**
	 * @param userName
	 * @return
	 * @author XHY
	 * Date 2014年12月18日
	 * @version
	 */
	public int checkUserName(String userName);

	/**
	 * @param userName
	 * @param userId
	 * @return
	 * @author XHY
	 * Date 2014年12月18日
	 * @version
	 */
	public int checkUserNameOutSelf(String userName, Integer userId);
}
