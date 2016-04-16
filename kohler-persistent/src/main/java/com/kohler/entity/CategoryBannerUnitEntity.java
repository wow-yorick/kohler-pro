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
public class CategoryBannerUnitEntity extends BaseEntity {

	private static final long serialVersionUID = 688488868041319959L;
	
	private Integer categoryBannerUnitId;
	private Integer lang;
	private Integer catalogMetadataId;
	private Integer categoryMetadataId;
	private Integer bannerUnitMetadataId;
	private Integer bannerUnitRow;
	private Integer bannerUnitColumn;
	private String bannerUnitFiledValue;
	private Integer seqNo;
	public Integer getCategoryBannerUnitId() {
		return categoryBannerUnitId;
	}
	public void setCategoryBannerUnitId(Integer categoryBannerUnitId) {
		this.categoryBannerUnitId = categoryBannerUnitId;
	}
	public Integer getLang() {
		return lang;
	}
	public void setLang(Integer lang) {
		this.lang = lang;
	}
	public Integer getCatalogMetadataId() {
		return catalogMetadataId;
	}
	public void setCatalogMetadataId(Integer catalogMetadataId) {
		this.catalogMetadataId = catalogMetadataId;
	}
	public Integer getCategoryMetadataId() {
		return categoryMetadataId;
	}
	public void setCategoryMetadataId(Integer categoryMetadataId) {
		this.categoryMetadataId = categoryMetadataId;
	}
	public Integer getBannerUnitMetadataId() {
		return bannerUnitMetadataId;
	}
	public void setBannerUnitMetadataId(Integer bannerUnitMetadataId) {
		this.bannerUnitMetadataId = bannerUnitMetadataId;
	}
	public Integer getBannerUnitRow() {
		return bannerUnitRow;
	}
	public void setBannerUnitRow(Integer bannerUnitRow) {
		this.bannerUnitRow = bannerUnitRow;
	}
	public Integer getBannerUnitColumn() {
		return bannerUnitColumn;
	}
	public void setBannerUnitColumn(Integer bannerUnitColumn) {
		this.bannerUnitColumn = bannerUnitColumn;
	}
	public Integer getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}
	public String getBannerUnitFiledValue() {
		return bannerUnitFiledValue;
	}
	public void setBannerUnitFiledValue(String bannerUnitFiledValue) {
		this.bannerUnitFiledValue = bannerUnitFiledValue;
	}
	
	

}
