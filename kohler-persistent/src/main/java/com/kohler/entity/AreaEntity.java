package com.kohler.entity;

public class AreaEntity extends BaseEntity {
	/**
	 * 省市区
	 */
	private static final long serialVersionUID = 6314418624406484007L;
	
	private Integer areaId;
	private Integer lang;
	private Integer areaMetadataId;//区域MetadateId
	private String areaName;//名称
	private String areaTelecode;//电话区号
	private String abbreviate;//简称
	private String postCode;//邮编
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public Integer getLang() {
		return lang;
	}
	public void setLang(Integer lang) {
		this.lang = lang;
	}
	public Integer getAreaMetadataId() {
		return areaMetadataId;
	}
	public void setAreaMetadataId(Integer areaMetadataId) {
		this.areaMetadataId = areaMetadataId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getAreaTelecode() {
		return areaTelecode;
	}
	public void setAreaTelecode(String areaTelecode) {
		this.areaTelecode = areaTelecode;
	}
	public String getAbbreviate() {
		return abbreviate;
	}
	public void setAbbreviate(String abbreviate) {
		this.abbreviate = abbreviate;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	
	
}
