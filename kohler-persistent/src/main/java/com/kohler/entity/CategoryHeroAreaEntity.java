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
public class CategoryHeroAreaEntity extends BaseEntity {

	private static final long serialVersionUID = -6689759246736963850L;
	
	private Integer categoryHeroAreaId;
	private Integer lang;
	private Integer categoryMetadataId;
	private Integer heroAreaMetadataId;
	private Integer seqNo;
	
	public Integer getCategoryHeroAreaId() {
		return categoryHeroAreaId;
	}
	public void setCategoryHeroAreaId(Integer categoryHeroAreaId) {
		this.categoryHeroAreaId = categoryHeroAreaId;
	}
	public Integer getLang() {
		return lang;
	}
	public void setLang(Integer lang) {
		this.lang = lang;
	}
	public Integer getCategoryMetadataId() {
		return categoryMetadataId;
	}
	public void setCategoryMetadataId(Integer categoryMetadataId) {
		this.categoryMetadataId = categoryMetadataId;
	}
	public Integer getHeroAreaMetadataId() {
		return heroAreaMetadataId;
	}
	public void setHeroAreaMetadataId(Integer heroAreaMetadataId) {
		this.heroAreaMetadataId = heroAreaMetadataId;
	}
	public Integer getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}
	
	

}