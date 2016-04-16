package com.kohler.entity;

/**
 * 用户角色关联表
 * @author zhangtingting
 * 20140904
 */
public class UserRoleEntity extends BaseEntity{

	private static final long serialVersionUID = 4116890467184681649L;
	private Integer userRoleId;
	private Integer sysUserId;
	private Integer roleId;
	private String remark;
	
	public Integer getUserRoleId() {
        return userRoleId;
    }
    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }
    public Integer getSysUserId() {
		return sysUserId;
	}
	public void setSysUserId(Integer sysUserId) {
		this.sysUserId = sysUserId;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
	
