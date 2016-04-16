/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.entity;

/**
 * Class Function Description Product
 * 
 * @author XHY
 * @Date 2014年10月8日
 */
public class CategoryMetadataEntity extends BaseEntity {

	private static final long serialVersionUID = -5897056212056455858L;

	private Integer categoryMetadataId;
	private Integer level;
	private Integer parentId;
	private Integer seqNo;
	
	public Integer getCategoryMetadataId() {
		return categoryMetadataId;
	}
	public void setCategoryMetadataId(Integer categoryMetadataId) {
		this.categoryMetadataId = categoryMetadataId;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}
}