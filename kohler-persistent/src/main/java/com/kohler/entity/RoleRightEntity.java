package com.kohler.entity;

import java.io.Serializable;


public class RoleRightEntity extends BaseEntity implements Serializable{
    private static final long serialVersionUID = 8221469583374463626L;
    private Integer RoleRightId;
    private Integer RoleId;
    private Integer RightId;
    private String remark;
    public Integer getRoleRightId() {
        return RoleRightId;
    }
    public void setRoleRightId(Integer roleRightId) {
        RoleRightId = roleRightId;
    }
    public Integer getRoleId() {
        return RoleId;
    }
    public void setRoleId(Integer roleId) {
        RoleId = roleId;
    }
    public Integer getRightId() {
        return RightId;
    }
    public void setRightId(Integer rightId) {
        RightId = rightId;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    

}
