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
public class CategoryAccessoryEntity extends BaseEntity {

	private static final long serialVersionUID = 2279423160074892991L;
	
	private Integer categoryAccessoryId;
	private Integer lang;
	private Integer categoryAccessoryMetadataId;
	private Integer categoryAccessoryType;
	private String categoryAccessoryName;

	public Integer getCategoryAccessoryId() {
		return categoryAccessoryId;
	}

	public void setCategoryAccessoryId(Integer categoryAccessoryId) {
		this.categoryAccessoryId = categoryAccessoryId;
	}

	public Integer getLang() {
		return lang;
	}

	public void setLang(Integer lang) {
		this.lang = lang;
	}

	

	public Integer getCategoryAccessoryMetadataId() {
		return categoryAccessoryMetadataId;
	}

	public void setCategoryAccessoryMetadataId(Integer categoryAccessoryMetadataId) {
		this.categoryAccessoryMetadataId = categoryAccessoryMetadataId;
	}

	public Integer getCategoryAccessoryType() {
		return categoryAccessoryType;
	}

	public void setCategoryAccessoryType(Integer categoryAccessoryType) {
		this.categoryAccessoryType = categoryAccessoryType;
	}

	public String getCategoryAccessoryName() {
		return categoryAccessoryName;
	}

	public void setCategoryAccessoryName(String categoryAccessoryName) {
		this.categoryAccessoryName = categoryAccessoryName;
	}

}
