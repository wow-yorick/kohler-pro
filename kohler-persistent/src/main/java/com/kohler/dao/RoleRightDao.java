package com.kohler.dao;


import java.util.List;
import java.util.Map;

import com.kohler.entity.RoleRightEntity;

public interface RoleRightDao extends BaseDao<RoleRightEntity>{  
    public List<Map<String, Object>> getlistdata(String Rolesid);
}
