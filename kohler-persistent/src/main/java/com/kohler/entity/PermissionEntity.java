/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.entity;


/**
 * 权限角色表
 * @author fujiajun
 * 20140904
 */
public class PermissionEntity extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 3503479892151999704L;
    /**
     * 
     */
  
    private Integer permissionId;
    private String  permissionName;
    private String  PermissionCode;
    private Integer parentId;
    private Integer permissionType;
    private Integer seqNo;
    private String remark;
    private String actionUrl;
    
    public Integer getPermissionId() {
        return permissionId;
    }
    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }
    public String getPermissionName() {
        return permissionName;
    }
    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }
    public Integer getParentId() {
        return parentId;
    }
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
    public String getPermissionCode() {
        return PermissionCode;
    }
    public void setPermissionCode(String permissionCode) {
        PermissionCode = permissionCode;
    }
    
    public String getActionUrl() {
        return actionUrl;
    }
    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }
    public Integer getSeqNo() {
        return seqNo;
    }
    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Integer getPermissionType() {
        return permissionType;
    }
    public void setPermissionType(Integer permissionType) {
        this.permissionType = permissionType;
    }
}
