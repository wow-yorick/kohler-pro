package com.kohler.entity;

/**
 * 角色权限关联表
 * @author zhangtingting
 * 20140904
 */

public class RolePermissionEntity extends BaseEntity{

	private static final long serialVersionUID = -8310755722640416926L;
	
	private Integer rolePermissionId;
	private Integer roleId;
	private Integer permissionId;
	private String remark;
	
	public Integer getRolePermissionId() {
		return rolePermissionId;
	}
	public void setRolePermissionId(Integer rolePermissionId) {
		this.rolePermissionId = rolePermissionId;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
