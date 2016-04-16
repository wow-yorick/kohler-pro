package com.kohler.entity.extend;


import com.kohler.entity.PublishLogEntity;

public class PublishLogPojo extends PublishLogEntity {
	private static final long serialVersionUID = -4778929023202664892L;
	private String statusName;
	private String beginDate;
	private String endDate;
	
	
	
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	
}
