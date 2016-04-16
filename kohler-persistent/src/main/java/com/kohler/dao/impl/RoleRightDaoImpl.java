package com.kohler.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kohler.dao.RoleRightDao;
import com.kohler.entity.RoleRightEntity;
@Repository
public class RoleRightDaoImpl extends BaseDaoImpl<RoleRightEntity> implements RoleRightDao {

    @Override
    public List<Map<String, Object>> getlistdata(String Rolesid) {
        // TODO Auto-generated method stub
        StringBuilder querySql = new StringBuilder("SELECT ROLE_RIGHT_ID FROM ROLE_RIGHT WHERE ROLE_ID IN ("+Rolesid+") ORDER BY NULL"); 
        return jdbcTemplate.queryForList(querySql.toString());
    }
}
