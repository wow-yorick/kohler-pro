/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kohler.constants.UserSQL;
import com.kohler.dao.RoleDao;
import com.kohler.dao.SysLogDao;
import com.kohler.dao.SysUserDao;
import com.kohler.dao.UserRoleDao;
import com.kohler.dao.utils.Pager;
import com.kohler.entity.RoleEntity;
import com.kohler.entity.SysUserEntity;
import com.kohler.entity.UserRoleEntity;
import com.kohler.service.UsersService;
import com.kohler.util.MD5Util;

/**
 * Class Function Description
 *
 * @author Administrator
 * @Date 2014年11月18日
 */
@Service
public class UsersServiceImpl implements UsersService {
    
    @Autowired
    private SysUserDao sysUserDao;
    
    @Autowired
    private SysLogDao sysLogDao;
    
    @Autowired
    private UserRoleDao userRoleDao;
    
    @Autowired
    private RoleDao roleDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public Pager<Map<String, Object>> getUsersPage(Pager<Map<String, Object>> pager,
            SysUserEntity sysUserEntity) {
        
        List<Object> params = new ArrayList<Object>();//参数容器
        
        //用户名参数
        String userName = sysUserEntity.getUserName();//用户名
        if (userName != null) {
            userName = '%' + userName + '%';
        } else {
            userName = "%%";
        }
        params.add(userName);
        
        //显示名参数
        String realName = sysUserEntity.getRealName();//用户显示名
        if (realName != null) {
            realName = '%' + realName + '%';
        } else {
            realName = "%%";
        }
        params.add(realName);

        int pageCount = 0;
        int startRow = (pager.getCurrentPage() - 1) * pager.getPageSize();

        int totalCount = sysUserDao.selectCountByCondition(UserSQL.SELECT_ALL_BY_PAGE_COUNT, params);

        if (totalCount % pager.getPageSize() == 0) {
            pageCount = totalCount / pager.getPageSize();
        } else {
            pageCount = totalCount / pager.getPageSize() + 1;
        }
        params.add(startRow);
        params.add(pager.getPageSize());
        
        List<Map<String, Object>> list = sysUserDao.selectByConditionWithMap(
                UserSQL.SELECT_ALL_BY_PAGE_LM, params);

        pager.setStartRow(startRow);
        pager.setDatas(list);
        pager.setTotalRecord(totalCount);
        pager.setTotalPage(pageCount);
        return pager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void addUser(SysUserEntity sysUserEntity, String userRole) {
        String password = sysUserEntity.getPassword();
        password = MD5Util.md5Hex(password);
        sysUserEntity.setPassword(password);
        Integer matedataId = sysUserDao.insert(sysUserEntity);
        
        if(!"".equals(userRole)){
        	 Integer roleId = Integer.valueOf(userRole);              
        	 UserRoleEntity userRoleEntity = new UserRoleEntity(); 
        	 userRoleEntity.setRoleId(roleId);                     
        	 userRoleEntity.setSysUserId(matedataId);  
        	 userRoleEntity.setIsEnable(true);
        	 userRoleDao.insert(userRoleEntity);                   
        }
        
//        String[] userRoleList = userRole.split(",");
//        
//        for(String roleE : userRoleList) {
//            Integer roleId = Integer.valueOf(roleE);
//            UserRoleEntity userRoleEntity = new UserRoleEntity();
//            userRoleEntity.setRoleId(roleId);
//            userRoleEntity.setSysUserId(matedataId);
//            userRoleDao.insert(userRoleEntity);
//        }
        // 插入日志
        sysLogDao.insertLogForInsert(matedataId, "SYS_USER");
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SysUserEntity getUserDetail(Integer sysUserId) {
        return sysUserDao.selectById(sysUserId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserRoleEntity> getUserRoleList(Integer sysUserId) {
        UserRoleEntity userRoleEntity =  new UserRoleEntity();
        userRoleEntity.setSysUserId(sysUserId);
        userRoleEntity.setIsEnable(true);
        return userRoleDao.selectByCondition(userRoleEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void editUser(SysUserEntity sysUserEntity, String userRole) {
        String password = sysUserEntity.getPassword();
        
        if(password != null && !"".equals(password)){
          password = MD5Util.md5Hex(password);
          sysUserEntity.setPassword(password);
        }else{
        	SysUserEntity psUser = sysUserDao.selectById(sysUserEntity.getSysUserId());
        	sysUserEntity.setPassword(psUser.getPassword());
        }
        

        sysUserEntity.setIsEnable(true);
        sysUserDao.update(sysUserEntity);
        
        //删除旧的角色配置
        UserRoleEntity searchUserRole = new UserRoleEntity();
        searchUserRole.setSysUserId(sysUserEntity.getSysUserId());
        searchUserRole.setIsEnable(true);
        List<UserRoleEntity> userRoles = userRoleDao.selectByCondition(searchUserRole);
        for(UserRoleEntity delRole : userRoles){
         	userRoleDao.delete(delRole);
        }
        
        //插入新的角色
        
        if(!"".equals(userRole)){
       	 Integer roleId = Integer.valueOf(userRole);              
       	 UserRoleEntity userRoleEntity = new UserRoleEntity(); 
       	 userRoleEntity.setRoleId(roleId);                     
       	 userRoleEntity.setSysUserId(sysUserEntity.getSysUserId());
       	 userRoleEntity.setIsEnable(true);
       	 userRoleDao.insert(userRoleEntity);                   
       }
        
//        String[] userRoleList = userRole.split(",");
//        for(String roleE : userRoleList) {
//            Integer roleId = Integer.valueOf(roleE);
//            UserRoleEntity userRoleEntity = new UserRoleEntity();
//            userRoleEntity.setRoleId(roleId);
//            userRoleEntity.setSysUserId(sysUserEntity.getSysUserId());
//            userRoleDao.insert(userRoleEntity);
//        }
        // 插入日志
        sysLogDao.insertLogForUpdate(sysUserEntity.getSysUserId(), "SYS_USER");
        
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<RoleEntity> getRoles() {
		RoleEntity searchRole = new RoleEntity();
		searchRole.setIsEnable(true);
		return roleDao.selectByCondition(searchRole);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteUserByIds(Integer[] sysUserIds) {
		for(Integer id : sysUserIds){
			//delete user's role 
			UserRoleEntity searUserRole = new UserRoleEntity();
			searUserRole.setIsEnable(true);
			searUserRole.setSysUserId(id);
			List<UserRoleEntity> roles = userRoleDao.selectByCondition(searUserRole);
			for(UserRoleEntity userRole : roles){
				userRoleDao.delete(userRole);
				sysLogDao.insertLogForDelete(userRole.getUserRoleId(), "USER_ROLE");
			}
			
			sysUserDao.deleteSysUser(id);
			sysLogDao.insertLogForDelete(id, "SYS_USER");
			
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int checkUserName(String userName) {
		Integer count = 0;
		List<Object> params = new ArrayList<Object>();
		if (!"".equals(userName)) {
			params.add(userName);
			count = sysUserDao.selectCountByCondition(
					UserSQL.CHECK_USERNAME_COUNT, params);
		}
		return count;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int checkUserNameOutSelf(String userName, Integer userId) {
			Integer count = 0;
			List<Object> params = new ArrayList<Object>();
			if (!"".equals(userName)) {
				params.add(userName);
				params.add(userId);
				count = sysUserDao.selectCountByCondition(
						UserSQL.CHECK_USERNAME_COUNT_OUT, params);
			}
			return count;
	}

}
