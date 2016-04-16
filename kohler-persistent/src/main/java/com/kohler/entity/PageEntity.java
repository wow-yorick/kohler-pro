/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.entity;

/**
 * Class Function Description
 *
 * @author shefeng
 * @Date 2014年9月27日
 */
public class PageEntity extends BaseEntity{
    
    private static final long serialVersionUID = -8203690348902146186L;
    
    private Integer pageId;
    private String pageName;
    private Integer sectionId;
    private Integer templateId;
    private String physicalName;
    private String seoTitle;
    private String seoKeywords;
    private String seoDescription;
    private String seoH1tag;
    private String remark;
    private Integer datatypeId;
    public Integer getPageId() {
        return pageId;
    }
    public void setPageId(Integer pageId) {
        this.pageId = pageId;
    }
    public String getPageName() {
        return pageName;
    }
    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public Integer getTemplateId() {
        return templateId;
    }
    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }
    public String getPhysicalName() {
        return physicalName;
    }
    public void setPhysicalName(String physicalName) {
        this.physicalName = physicalName;
    }
    /**
     * @return the seoTitle
     */
    public String getSeoTitle() {
        return seoTitle;
    }
    /**
     * @param seoTitle the seoTitle to set
     */
    public void setSeoTitle(String seoTitle) {
        this.seoTitle = seoTitle;
    }
    
    /**
     * @return the seoKeywords
     */
    public String getSeoKeywords() {
        return seoKeywords;
    }
    /**
     * @param seoKeywords the seoKeywords to set
     */
    public void setSeoKeywords(String seoKeywords) {
        this.seoKeywords = seoKeywords;
    }
    /**
     * @return the seoDescription
     */
    public String getSeoDescription() {
        return seoDescription;
    }
    /**
     * @param seoDescription the seoDescription to set
     */
    public void setSeoDescription(String seoDescription) {
        this.seoDescription = seoDescription;
    }
    /**
     * @return the seoH1tag
     */
    public String getSeoH1tag() {
        return seoH1tag;
    }
    /**
     * @param seoH1tag the seoH1tag to set
     */
    public void setSeoH1tag(String seoH1tag) {
        this.seoH1tag = seoH1tag;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    /**
     * @return the datatypeId
     */
    public Integer getDatatypeId() {
        return datatypeId;
    }
    /**
     * @param datatypeId the datatypeId to set
     */
    public void setDatatypeId(Integer datatypeId) {
        this.datatypeId = datatypeId;
    }
	public Integer getSectionId() {
		return sectionId;
	}
	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}
    
}
