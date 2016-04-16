package com.kohler.entity;

import java.util.Date;


public class PublishLogEntity extends BaseEntity {
	private static final long serialVersionUID = 5881072656640506578L;
	
	private Integer publishLogId;
	private Integer publishType;
	private Integer userId;
	private Date publishTime;
	private Date startTime;
	private Integer versionId;
	private Integer publishStatus;
	private String remark;
	private Boolean isSucc;
	private Boolean isBackupSucc;
	private String backupSuffix;
	
	public Integer getPublishLogId() {
		return publishLogId;
	}
	public void setPublishLogId(Integer publishLogId) {
		this.publishLogId = publishLogId;
	}
	public Integer getPublishType() {
		return publishType;
	}
	public void setPublishType(Integer publishType) {
		this.publishType = publishType;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Integer getVersionId() {
		return versionId;
	}
	public void setVersionId(Integer versionId) {
		this.versionId = versionId;
	}
	public Integer getPublishStatus() {
		return publishStatus;
	}
	public void setPublishStatus(Integer publishStatus) {
		this.publishStatus = publishStatus;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Boolean getIsSucc() {
		return isSucc;
	}
	public void setIsSucc(Boolean isSucc) {
		this.isSucc = isSucc;
	}
    public String getBackupSuffix() {
        return backupSuffix;
    }
    public void setBackupSuffix(String backupSuffix) {
        this.backupSuffix = backupSuffix;
    }
    public Boolean getIsBackupSucc() {
        return isBackupSucc;
    }
    public void setIsBackupSucc(Boolean isBackupSucc) {
        this.isBackupSucc = isBackupSucc;
    }

	
}
