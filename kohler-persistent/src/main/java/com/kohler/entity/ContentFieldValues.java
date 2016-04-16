package com.kohler.entity;

public class ContentFieldValues extends BaseEntity{
	/**
     * 
     */
    private static final long serialVersionUID = 6750865210760783584L;
    private Integer contentFieldValuesId;
	private Integer lang;
	private Integer ContentMetadataId;
	private String fieldName;
	private Integer complicate;
	private String fieldValue;
	//private String language;

	public Integer getContentFieldValuesId() {
		return contentFieldValuesId;
	}

	public void setContentFieldValuesId(Integer contentFieldValuesId) {
		this.contentFieldValuesId = contentFieldValuesId;
	}

	public Integer getLang() {
		return lang;
	}

	public void setLang(Integer lang) {
		this.lang = lang;
	}

	public Integer getContentMetadataId() {
		return ContentMetadataId;
	}

	public void setContentMetadataId(Integer contentMetadataId) {
		ContentMetadataId = contentMetadataId;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Integer getComplicate() {
		return complicate;
	}

	public void setComplicate(Integer complicate) {
		this.complicate = complicate;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

//	public String getLanguage() {
//		return language;
//	}
//
//	public void setLanguage(String language) {
//		this.language = language;
//	}

}
