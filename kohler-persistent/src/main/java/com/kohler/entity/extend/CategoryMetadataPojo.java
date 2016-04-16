/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.entity.extend;

import java.util.ArrayList;
import java.util.List;

import com.kohler.entity.CategoryBannerUnitEntity;
import com.kohler.entity.CategoryMetadataEntity;

/**
 * Class Function Description
 *
 * @author XHY
 * @Date 2014年10月21日
 */
public class CategoryMetadataPojo extends CategoryMetadataEntity {
	
	private static final long serialVersionUID = -2282406851057853675L;

	private String categoryName;
	
	
	

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
