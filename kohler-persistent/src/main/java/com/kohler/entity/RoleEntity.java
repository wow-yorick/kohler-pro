package com.kohler.entity;

import java.io.Serializable;


/**
 * 用户角色表
 * @author fujiajun
 * 20140904
 */
public class RoleEntity extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 641250993029264293L;
	private Integer roleId;
	private String roleName;
	private String remark;

    public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "TmRole [roleId=" + roleId + ", roleName=" + roleName
				+ ", remark=" + remark + "]";
	}
   
	
}
