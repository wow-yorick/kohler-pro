package com.kohler.entity;

/**
 * 用户角色表
 * @author zhangtingting
 * 20140905
 */

public class SysUserEntity extends BaseEntity{
	private static final long serialVersionUID = -6875574971826703209L;
	
	private Integer sysUserId;
	private String userName;
	private String password;
	private String realName;
	private String remark;
	
	public Integer getSysUserId() {
		return sysUserId;
	}
	public void setSysUserId(Integer sysUserId) {
		this.sysUserId = sysUserId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
