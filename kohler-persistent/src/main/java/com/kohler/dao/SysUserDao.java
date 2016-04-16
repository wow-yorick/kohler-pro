package com.kohler.dao;

import java.util.List;
import java.util.Map;

import com.kohler.entity.SysUserEntity;

public interface SysUserDao extends BaseDao<SysUserEntity>{
	
	public List<Map<String, Object>> getAllUsers();

	public int insertUser(String username, String password, String realname,String remark);

	public int updateUser(String username, String password, String realname,String remark,int id);

	public Map<String, Object> selectUserById(int id);
	
	public int deleteSysUser(Integer userId);

}
