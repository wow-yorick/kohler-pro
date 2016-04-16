package com.kohler.service;


import java.util.List;
import java.util.Map;

import com.kohler.dao.utils.Pager;
import com.kohler.entity.RoleEntity;
import com.kohler.entity.RoleRightEntity;


public interface RoleService {
    /**
     * @param list
     * @author Sweety
     * Date 2014年9月29日
     * @version
     */
    public Pager<RoleEntity>  getRoleList(Pager<RoleEntity> pager,
            Map<String, Object> map);
    /**
     * @param list
     * @author Sweety
     * Date 2014年9月29日
     * @version
     */
    public List<Map<String, Object>>  getUserTree();
    /**
     * @param list
     * @author Sweety
     * Date 2014年9月29日
     * @version
     */
    public RoleEntity addRole(RoleEntity ROLE);
    /**
     * @param list
     * @author Sweety
     * Date 2014年9月29日
     * @version
     */
    public Integer editRole(RoleEntity ROLE);
    /**
     * @param list
     * @author Sweety
     * Date 2014年9月29日
     * @version
     */
    public RoleRightEntity addRoleRight(RoleRightEntity rre);
   
   
    /**
     * @return
     * @author shefeng Date 2014年9月27日
     * @version
     */
    List<Map<String, Object>> getRoleWithMap(String ROLE_ID);
    Integer deleteRole(String ids, int type);

}
