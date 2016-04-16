package com.kohler.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kohler.constants.UserSQL;
import com.kohler.dao.SysUserDao;
import com.kohler.entity.SysUserEntity;

@Repository
public class SysUserDaoImpl extends BaseDaoImpl<SysUserEntity> implements SysUserDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Map<String, Object>> getAllUsers() {
		return jdbcTemplate.queryForList(UserSQL.SELECT_ALL_USER);
	}

	@Override
	public int insertUser(String username, String password, String realname,String remark) {
		Object[] parms = new Object[]{username,password,realname,remark};
		return jdbcTemplate.update(UserSQL.INSERT_USER,parms);
	}

	@Override
	public int updateUser(String username, String password, String realname,String remark,int id) {
		Object[] parms = new Object[]{username,password,realname,remark,id};
		return jdbcTemplate.update(UserSQL.UPDATE_USER,parms);
	}

	@Override
	public Map<String, Object> selectUserById(int id) {
		List<Map<String, Object>> users = jdbcTemplate.queryForList(UserSQL.SELECT_USER_BY_ID,new Object[]{id});
		if(users == null || users.size() != 1){
			return null;
		}
		return users.get(0);
	}

	@Override
	public int deleteSysUser(Integer userId) {
		String sql="update SYS_USER set IS_ENABLE=false where SYS_USER_ID=?";
		int a= jdbcTemplate.update(sql,userId);
		return a;
	}

}
