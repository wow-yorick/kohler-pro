package com.kohler.service;

import java.util.Map;

import com.kohler.dao.utils.Pager;
import com.kohler.entity.RoleEntity;
import com.kohler.entity.SysLogEntity;

public interface SysLogService {
    /**
     * @param list
     * @author Sweety
     * Date 2014年9月29日
     * @version
     */
    public Pager<Map<String,Object>>  GetbyRoleList(Pager<Map<String,Object>> pager,
            Map<String, Object> map);
    
    public Integer deleteSyslogs(Map<String,Object> map);
}
