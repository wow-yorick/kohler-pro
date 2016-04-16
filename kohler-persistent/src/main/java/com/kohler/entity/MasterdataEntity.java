package com.kohler.entity;

public class MasterdataEntity extends BaseEntity{

	/**
	 * ys
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer masterdataId;
	private Integer lang;
	private Integer masterdataMetadataId;
	private String masterdataName;
	
	public Integer getMasterdataId() {
		return masterdataId;
	}
	public void setMasterdataId(Integer masterdataId) {
		this.masterdataId = masterdataId;
	}
	public Integer getLang() {
		return lang;
	}
	public void setLang(Integer lang) {
		this.lang = lang;
	}
	public Integer getMasterdataMetadataId() {
		return masterdataMetadataId;
	}
	public void setMasterdataMetadataId(Integer masterdataMetadataId) {
		this.masterdataMetadataId = masterdataMetadataId;
	}
	public String getMasterdataName() {
		return masterdataName;
	}
	public void setMasterdataName(String masterdataName) {
		this.masterdataName = masterdataName;
	}

	
}
