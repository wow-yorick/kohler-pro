package com.kohler.entity.extend;


import java.util.List;

import com.kohler.entity.PermissionEntity;

public class PermissionPojo  extends PermissionEntity {
    /**
     * 
     */
    private static final long serialVersionUID = 7061738782367775256L;
    private List<PermissionEntity>  children; 
    public List<PermissionEntity> getChildren() {
        return children;
    }
    public void setChildren(List<PermissionEntity> children) {
        this.children = children;
    }
}
