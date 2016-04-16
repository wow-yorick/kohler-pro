/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.entity.extend;

import com.kohler.entity.CategorySearchKeywordMetadataEntity;

/**
 * Class Function Description
 *
 * @author Dragon
 * @Date 2014年11月10日
 */
public class CategorySearchKeywordMetadataPojo extends
		CategorySearchKeywordMetadataEntity {
	
	private static final long serialVersionUID = -3663323745964325306L;

	private String keyword;
	private String url;
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
