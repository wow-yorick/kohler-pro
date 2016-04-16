/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.entity;

/**
 * Class Function Description
 *
 * @author Sweety
 * @Date 2014年9月26日
 */
public class SuiteEntity extends BaseEntity{

    private static final long serialVersionUID = -3327506566075222661L;
    
    private Integer suiteId;
	private Integer lang;
    private Integer suiteMetadataId;
    private String suiteName;
    private Integer pcTemplateId;
    private Integer mobileTemplateId;
    private String listContent;
    private Integer imageSource;
    private String listImageUrl;
    private Integer listImageId;
    private String listImageAlt;
    private String seoTitlePc;
    private String seoKeywordsPc;
    private String seoDescriptionPc;
    private String seoH1tagPc;
    private String seoTitleMobile;
    private String seoKeywordsMobile;
    private String seoDescriptionMobile;
    private String seoH1tagMobile;
    
    public Integer getSuiteId() {
		return suiteId;
	}
	public void setSuiteId(Integer suiteId) {
		this.suiteId = suiteId;
	}
	public Integer getLang() {
		return lang;
	}
	public void setLang(Integer lang) {
		this.lang = lang;
	}
	public Integer getSuiteMetadataId() {
		return suiteMetadataId;
	}
	public void setSuiteMetadataId(Integer suiteMetadataId) {
		this.suiteMetadataId = suiteMetadataId;
	}
	public String getSuiteName() {
		return suiteName;
	}
	public void setSuiteName(String suiteName) {
		this.suiteName = suiteName;
	}
	public Integer getPcTemplateId() {
		return pcTemplateId;
	}
	public void setPcTemplateId(Integer pcTemplateId) {
		this.pcTemplateId = pcTemplateId;
	}
	public String getListContent() {
		return listContent;
	}
	public void setListContent(String listContent) {
		this.listContent = listContent;
	}

    public Integer getMobileTemplateId() {
        return mobileTemplateId;
    }
    public void setMobileTemplateId(Integer mobileTemplateId) {
        this.mobileTemplateId = mobileTemplateId;
    }
    public String getSeoH1tagMobile() {
        return seoH1tagMobile;
    }
    public void setSeoH1tagMobile(String seoH1tagMobile) {
        this.seoH1tagMobile = seoH1tagMobile;
    }
    public String getSeoDescriptionMobile() {
        return seoDescriptionMobile;
    }
    public void setSeoDescriptionMobile(String seoDescriptionMobile) {
        this.seoDescriptionMobile = seoDescriptionMobile;
    }
    public String getSeoKeywordsMobile() {
        return seoKeywordsMobile;
    }
    public void setSeoKeywordsMobile(String seoKeywordsMobile) {
        this.seoKeywordsMobile = seoKeywordsMobile;
    }
    public String getSeoTitleMobile() {
        return seoTitleMobile;
    }
    public void setSeoTitleMobile(String seoTitleMobile) {
        this.seoTitleMobile = seoTitleMobile;
    }
    public String getSeoH1tagPc() {
        return seoH1tagPc;
    }
    public void setSeoH1tagPc(String seoH1tagPc) {
        this.seoH1tagPc = seoH1tagPc;
    }
    public String getSeoDescriptionPc() {
        return seoDescriptionPc;
    }
    public void setSeoDescriptionPc(String seoDescriptionPc) {
        this.seoDescriptionPc = seoDescriptionPc;
    }
    public String getSeoKeywordsPc() {
        return seoKeywordsPc;
    }
    public void setSeoKeywordsPc(String seoKeywordsPc) {
        this.seoKeywordsPc = seoKeywordsPc;
    }
    public String getSeoTitlePc() {
        return seoTitlePc;
    }
    public void setSeoTitlePc(String seoTitlePc) {
        this.seoTitlePc = seoTitlePc;
    }
    public String getListImageUrl() {
        return listImageUrl;
    }
    public void setListImageUrl(String listImageUrl) {
        this.listImageUrl = listImageUrl;
    }

    public String getListImageAlt() {
        return listImageAlt;
    }
    public void setListImageAlt(String listImageAlt) {
        this.listImageAlt = listImageAlt;
    }
    public Integer getListImageId() {
        return listImageId;
    }
    public void setListImageId(Integer listImageId) {
        this.listImageId = listImageId;
    }
    public Integer getImageSource() {
        return imageSource;
    }
    public void setImageSource(Integer imageSource) {
        this.imageSource = imageSource;
    }
    
}
