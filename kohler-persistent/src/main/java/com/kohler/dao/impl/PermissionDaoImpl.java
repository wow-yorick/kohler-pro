package com.kohler.dao.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kohler.dao.PermissionDao;
import com.kohler.dao.utils.DefaultNameHandler;
import com.kohler.dao.utils.DefaultRowMapper;
import com.kohler.entity.PermissionEntity;

@Repository
public class PermissionDaoImpl extends BaseDaoImpl<PermissionEntity> implements PermissionDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<PermissionEntity> listTmPermission(Integer[] permissionIds) {
		StringBuffer sql=new StringBuffer("select * from PERMISSION ");
		if(null!=permissionIds && permissionIds.length>0){
			sql.append("where PERMISSION_ID in (");
			for(int i=0;i<permissionIds.length;i++){
				if(i==permissionIds.length-1){
					sql.append("?");
				}else{
					sql.append("?,");
				}
			}
			sql.append(")");
		}
		return (List)jdbcTemplate.query(sql.toString(), permissionIds, new DefaultRowMapper(PermissionEntity.class,new DefaultNameHandler()));
	}

	@Override
    public List<Map<String, Object>> getlistdata() {
        // TODO Auto-generated method stub、、permission_id,permission_code,permission_name,parent_id,action_url,permission_type
        StringBuilder querySql = new StringBuilder("SELECT permission_id as id,permission_code,permission_name as name,parent_id as pId,action_url,permission_type FROM PERMISSION WHERE is_enable = 1 ORDER BY SEQ_NO DESC"); 
        return jdbcTemplate.queryForList(querySql.toString());
        
    }

}
