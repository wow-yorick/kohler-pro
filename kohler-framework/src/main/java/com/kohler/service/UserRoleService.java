/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.service;

import java.util.List;

import com.kohler.entity.UserRoleEntity;

/**
 * UserRole Interface
 *
 * @author zhangtingting
 * @Date 2014年9月25日
 */
public interface UserRoleService {

    /**
     * @param userId
     * @return
     * @author zhangtingting Date 2014年9月27日
     * @version
     */
	public List<UserRoleEntity> getListUserRole(int userId);
}
