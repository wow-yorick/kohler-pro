/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.entity;

/**
 * Class Function Description CategorySKUAttrMetadataEntity
 * 
 * @author zhangtingting
 * @Date 2014年9月29日
 */
public class CategorySKUAttrMetadataEntity extends BaseEntity {

	private static final long serialVersionUID = 5861072656640506578L;

	private Integer categorySKUAttrMetadataId;
	private Integer CategoryMetadataId;

	private String keyName;
	private Integer seqNo;
	private Boolean isFilter;

	public Integer getCategorySKUAttrMetadataId() {
		return categorySKUAttrMetadataId;
	}

	public void setCategorySKUAttrMetadataId(Integer categorySKUAttrMetadataId) {
		this.categorySKUAttrMetadataId = categorySKUAttrMetadataId;
	}

	public Integer getCategoryMetadataId() {
		return CategoryMetadataId;
	}

	public void setCategoryMetadataId(Integer categoryMetadataId) {
		CategoryMetadataId = categoryMetadataId;
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
	public Boolean getIsFilter() {
		return isFilter;
	}

	public void setIsFilter(Boolean isFilter) {
		this.isFilter = isFilter;
	}
	
	
}
