/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.entity.extend;

import com.kohler.entity.CategoryAccessoryMetadataEntity;

/**
 * 
 * Class Function Description
 * 
 * @author XHY
 * @Date 2014年11月10日
 */
public class CategoryAccessoryMetadataPojo extends CategoryAccessoryMetadataEntity {

	private static final long serialVersionUID = 5100573511849621438L;

	private String masterdataName;
	private String categoryAccessoryName;
	
	
	
	
	public String getMasterdataName() {
		return masterdataName;
	}
	public void setMasterdataName(String masterdataName) {
		this.masterdataName = masterdataName;
	}
	public String getCategoryAccessoryName() {
		return categoryAccessoryName;
	}
	public void setCategoryAccessoryName(String categoryAccessoryName) {
		this.categoryAccessoryName = categoryAccessoryName;
	}
	
	
}
