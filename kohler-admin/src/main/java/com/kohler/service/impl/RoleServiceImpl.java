package com.kohler.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kohler.constants.RoleSQL;
import com.kohler.dao.RoleRightDao;
import com.kohler.dao.RoleDao;
import com.kohler.dao.SysLogDao;
import com.kohler.dao.utils.DefaultNameHandler;
import com.kohler.dao.utils.DefaultRowMapper;
import com.kohler.dao.utils.Pager;
import com.kohler.dao.PermissionDao;
import com.kohler.entity.CollectionMetadataEntity;
import com.kohler.entity.PermissionEntity;
import com.kohler.entity.RoleEntity;
import com.kohler.entity.RoleRightEntity;
import com.kohler.entity.SectionEntity;
import com.kohler.entity.SysLogEntity;
import com.kohler.entity.SysUserEntity;
import com.kohler.entity.extend.PermissionPojo;
import com.kohler.service.RoleService;
import com.kohler.util.SysContent;


@Service
public class RoleServiceImpl implements RoleService {
    @Autowired 
    public RoleDao RoleDao;
    @Autowired 
    public SysLogDao sysLogDao;
    @Autowired 
    public PermissionDao PermissionDao;
    @Autowired 
    public RoleRightDao RoleRightDao;
    @Override
    public Pager<RoleEntity> getRoleList(Pager<RoleEntity> pager,Map<String, Object> map) {
        // TODO Auto-generated method stub
        List<Object> queryParams = new ArrayList<Object>();
        StringBuilder querySql = new StringBuilder("SELECT * FROM ROLE WHERE  IS_ENABLE=1"); 
        StringBuilder countSql = new StringBuilder("SELECT count(*) FROM ROLE WHERE  IS_ENABLE=1");
        if(map.get("rolename") != null ){
            String rolename = map.get("rolename").toString();
            if(!"".equals(rolename)){
                querySql.append("  and ROLE_NAME like concat(concat('%',?),'%') ");
                countSql.append("  and ROLE_NAME like concat(concat('%',?),'%') ");
                queryParams.add(rolename.toString());
            }
        }
        querySql.append(" limit ?,?");
        int totalCount = RoleDao.selectCountByCondition(countSql.toString(), queryParams);
        int pageCount = 0;
        if (totalCount % pager.getPageSize() == 0) {
            pageCount = totalCount / pager.getPageSize();
        } else {
            
            pageCount = totalCount / pager.getPageSize() + 1;
        }
        
        int index = (pager.getCurrentPage() - 1) * pager.getPageSize();
        queryParams.add(index);
        queryParams.add(pager.getPageSize());
        List<RoleEntity> list = RoleDao.selectByCondition(querySql.toString(), queryParams);
        pager.setStartRow(index);
        pager.setDatas(list);
        pager.setTotalRecord(totalCount);
        pager.setTotalPage(pageCount);
        return pager;
    }
    @Override
    public List<Map<String, Object>> getUserTree() {
        // TODO Auto-generated method stub
        List<Map<String, Object>> list=PermissionDao.getlistdata();
        return list;
    }
    @Override
    @Transactional
    public RoleEntity addRole(RoleEntity ROLE) {
        //插入Role
        Integer sessionId = RoleDao.insert(ROLE);
        ROLE.setRoleId(sessionId);
        //插入日志
        this.insertlog(sessionId, "ROLE","insert");
        return ROLE;
    }


    
    @Override
    public Integer editRole(RoleEntity ROLE) {
        Integer row = RoleDao.update(ROLE);
        //插入日志
        this.insertlog(ROLE.getRoleId(), "ROLE","update");
        return row;
    }
    @Override
    public RoleRightEntity addRoleRight(RoleRightEntity ROLERIGHT) {
        // TODO Auto-generated method stub
        Integer sessionId = RoleRightDao.insert(ROLERIGHT);
        ROLERIGHT.setRoleId(sessionId);
        //插入日志
        this.insertlog(sessionId, "ROLERIGHT","insert");
        return ROLERIGHT;
      
    }
    
    
   
    @SuppressWarnings("unused")
    private void insertlog(Integer id,String t,String type){
        SysLogEntity log = new SysLogEntity();
        log.setIsEnable(true);
        log.setObjectId(id);
        log.setOperation(type);
        log.setOperationObject(t);
        HttpSession session=SysContent.getSession();
        if(session != null){
            SysUserEntity user=(SysUserEntity)session.getAttribute("sysUser");
            log.setSysUserId(user.getSysUserId());
        }
        sysLogDao.insert(log);
    }
    @Override
    @Transactional
    public Integer deleteRole(String ids,int type) {
         Integer issuccess=null;
         if(type == 1){
            issuccess = RoleDao.deleteroledata(ids,1);
            String s[]=ids.split(",");
            for (String x : s) {
                if(x != null ){
                    int i=Integer.parseInt(x);
                    this.insertlog(i, "ROLE", "delete");
                }
            }
        }
        issuccess = RoleDao.deleteroledata(ids,2);
        List<Map<String, Object>> list=RoleRightDao.getlistdata(ids);
        if(list.size()>0){
            for(Map<String, Object> item: list){
                  int i=Integer.parseInt(item.get("ROLE_RIGHT_ID").toString());
                  this.insertlog(i,"ROLE_RIGHT","delete");
            }
        }
        return issuccess;
    }
    
    @Override
    public List<Map<String, Object>> getRoleWithMap(String ROLE_ID) {
        // TODO Auto-generated method stub
        StringBuilder sb=new StringBuilder(RoleSQL.SELECT_Role_LIST);
        return RoleDao.getRoledata(sb.toString(),ROLE_ID);
    }
    
    
    
}
