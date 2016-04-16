/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.entity;

/**
 * 
 * Class Function Description
 * 
 * @author XHY
 * @Date 2014年11月10日
 */
public class CategoryAccessoryMetadataEntity extends BaseEntity {

	private static final long serialVersionUID = -6562873699884896098L;

	private Integer categoryAccessoryMetadataId;
	private Integer categoryMetadataId;
	private String keyName;
	private Integer seqNo;

	
	public Integer getCategoryAccessoryMetadataId() {
		return categoryAccessoryMetadataId;
	}

	public void setCategoryAccessoryMetadataId(Integer categoryAccessoryMetadataId) {
		this.categoryAccessoryMetadataId = categoryAccessoryMetadataId;
	}

	public Integer getCategoryMetadataId() {
		return categoryMetadataId;
	}

	public void setCategoryMetadataId(Integer categoryMetadataId) {
		this.categoryMetadataId = categoryMetadataId;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public Integer getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}

	
}
