package com.kohler.dao;


import java.util.List;
import java.util.Map;

import com.kohler.entity.RoleEntity;

public interface RoleDao extends BaseDao<RoleEntity>{ 
     
   
    Integer deleteroledata(String idS, int t);
    
    List<Map<String,Object>> getRoledata(String S,String rid);
}
