package com.kohler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohler.dao.PermissionDao;
import com.kohler.entity.PermissionEntity;
import com.kohler.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {
    
    @Autowired
    private PermissionDao userPermissionDao;

    @Override
    public List<PermissionEntity> getListTmPermission(Integer[] permissionIds) {
        return userPermissionDao.listTmPermission(permissionIds);
    }

    @Override
    public List<PermissionEntity> getPermissions() {
        return userPermissionDao.selectAll();
    }

    @Override
    public PermissionEntity getPermission(Integer permissionId) {
        return userPermissionDao.selectById(permissionId);
    }

    @Override
    public List<PermissionEntity> listTmPermissionByParentId(Integer parentId) {
        PermissionEntity tmPermission=new PermissionEntity();
        tmPermission.setParentId(parentId);
        return userPermissionDao.selectByCondition(tmPermission);
    }

    @Override
    public PermissionEntity getPermissionBycode(String permissionCode) {
        PermissionEntity tmPermission=new PermissionEntity();
        tmPermission.setPermissionCode(permissionCode);
        List<PermissionEntity> list=userPermissionDao.selectByCondition(tmPermission);
        if(null != list && list.size()>0){
            return list.get(0);
        }
        return null;
    }

}
