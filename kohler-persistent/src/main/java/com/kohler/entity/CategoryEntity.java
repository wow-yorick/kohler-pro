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
public class CategoryEntity extends BaseEntity {
	
	private static final long serialVersionUID = 6042065009897319111L;
	
	private Integer categoryId;
	private Integer lang;
	private Integer categoryMetadataId;
	private String categoryName;
	private String breadcrumbName;
	private Integer pcTemplateId;
	private Integer mobileTemplateId;
	private String seoTitlePc;
	private String seoKeywordsPc;
	private String seoDescriptionPc;
	private String seoH1tagPc;
	private String seoTitleMobile;
	private String seoKeywordsMobile;
	private String seoDescriptionMobile;
	private String seoH1tagMobile;
	
	
	
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
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
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Integer getPcTemplateId() {
		return pcTemplateId;
	}
	public void setPcTemplateId(Integer pcTemplateId) {
		this.pcTemplateId = pcTemplateId;
	}
	public Integer getMobileTemplateId() {
		return mobileTemplateId;
	}
	public void setMobileTemplateId(Integer mobileTemplateId) {
		this.mobileTemplateId = mobileTemplateId;
	}
	public String getBreadcrumbName() {
		return breadcrumbName;
	}
	public void setBreadcrumbName(String breadcrumbName) {
		this.breadcrumbName = breadcrumbName;
	}
	public String getSeoTitlePc() {
		return seoTitlePc;
	}
	public void setSeoTitlePc(String seoTitlePc) {
		this.seoTitlePc = seoTitlePc;
	}
	public String getSeoKeywordsPc() {
		return seoKeywordsPc;
	}
	public void setSeoKeywordsPc(String seoKeywordsPc) {
		this.seoKeywordsPc = seoKeywordsPc;
	}
	public String getSeoDescriptionPc() {
		return seoDescriptionPc;
	}
	public void setSeoDescriptionPc(String seoDescriptionPc) {
		this.seoDescriptionPc = seoDescriptionPc;
	}
	public String getSeoH1tagPc() {
		return seoH1tagPc;
	}
	public void setSeoH1tagPc(String seoH1tagPc) {
		this.seoH1tagPc = seoH1tagPc;
	}
	public String getSeoTitleMobile() {
		return seoTitleMobile;
	}
	public void setSeoTitleMobile(String seoTitleMobile) {
		this.seoTitleMobile = seoTitleMobile;
	}
	public String getSeoKeywordsMobile() {
		return seoKeywordsMobile;
	}
	public void setSeoKeywordsMobile(String seoKeywordsMobile) {
		this.seoKeywordsMobile = seoKeywordsMobile;
	}
	public String getSeoDescriptionMobile() {
		return seoDescriptionMobile;
	}
	public void setSeoDescriptionMobile(String seoDescriptionMobile) {
		this.seoDescriptionMobile = seoDescriptionMobile;
	}
	public String getSeoH1tagMobile() {
		return seoH1tagMobile;
	}
	public void setSeoH1tagMobile(String seoH1tagMobile) {
		this.seoH1tagMobile = seoH1tagMobile;
	}
}