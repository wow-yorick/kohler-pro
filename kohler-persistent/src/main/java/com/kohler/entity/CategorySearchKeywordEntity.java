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
public class CategorySearchKeywordEntity extends BaseEntity {

	private static final long serialVersionUID = -6562873699884896098L;
	
	private Integer categorySearchKeywordId;
	private Integer lang;
	private Integer categorySearchKeywordMetadataId;
	private String keyword;
	private String url;

	
	public Integer getCategorySearchKeywordId() {
		return categorySearchKeywordId;
	}
	public void setCategorySearchKeywordId(Integer categorySearchKeywordId) {
		this.categorySearchKeywordId = categorySearchKeywordId;
	}
	public Integer getLang() {
		return lang;
	}
	public void setLang(Integer lang) {
		this.lang = lang;
	}
	
	public Integer getCategorySearchKeywordMetadataId() {
		return categorySearchKeywordMetadataId;
	}
	public void setCategorySearchKeywordMetadataId(
			Integer categorySearchKeywordMetadataId) {
		this.categorySearchKeywordMetadataId = categorySearchKeywordMetadataId;
	}
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
