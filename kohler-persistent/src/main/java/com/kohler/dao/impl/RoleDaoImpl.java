package com.kohler.dao.impl;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kohler.dao.RoleDao;
import com.kohler.entity.RoleEntity;

@Repository
public class RoleDaoImpl extends BaseDaoImpl<RoleEntity> implements RoleDao {

    
    /**
     * 
     * {@inheritDoc}
     */
    
    @Override
      public Integer deleteroledata(String idS,int t) {
          // TODO Auto-generated method stub
          StringBuilder sb=null;
          if(t == 1){
              sb=new StringBuilder("UPDATE ROLE SET IS_ENABLE=0 where ROLE_ID IN ( ");
          }else{
              sb=new StringBuilder("UPDATE ROLE_RIGHT SET IS_ENABLE=0 where ROLE_ID IN ( ");
          }
          String[] s=idS.split(",");
          List<Object> list=new LinkedList<Object>();
          for(int i=0,j=s.length,x=j-1;i<j;i++){
              if(i==x){
                  sb.append("?");
              }else{
                  sb.append("?,");
              }
              int ii=Integer.parseInt(s[i].toString());
              list.add(ii);
          }
          sb.append(")");
          return jdbcTemplate.update(sb.toString(), list.toArray()); 
      }

    @Override
    public List<Map<String, Object>> getRoledata(String S,String rid) {
        // TODO Auto-generated method stub
        
        return jdbcTemplate.queryForList(S, rid);
    }

  
    
    
}
