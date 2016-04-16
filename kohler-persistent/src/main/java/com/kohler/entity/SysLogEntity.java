package com.kohler.entity;

import java.util.Date;


public class SysLogEntity extends BaseEntity {
	
	private static final long serialVersionUID = 1216493671701147439L;
	
	private Integer sysLogId;
	private Integer SysUserId;
	private String operation;
	private Date operationTime = new Date();
	private String operationObject;
	private Integer objectId;
	
	public Integer getSysLogId() {
		return sysLogId;
	}
	public void setSysLogId(Integer sysLogId) {
		this.sysLogId = sysLogId;
	}
	public Integer getSysUserId() {
		return SysUserId;
	}
	public void setSysUserId(Integer sysUserId) {
		SysUserId = sysUserId;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public Date getOperationTime() {
        return operationTime;
    }
    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }
    public String getOperationObject() {
		return operationObject;
	}
	public void setOperationObject(String operationObject) {
		this.operationObject = operationObject;
	}
	public Integer getObjectId() {
		return objectId;
	}
	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}
	
}
