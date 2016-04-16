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
public class SectionEntity extends BaseEntity{

    private static final long serialVersionUID = -3327506566075222661L;
    
    private Integer sectionId;
    private Integer site;
    private Integer parentId;
    private String sectionName;
    private Integer publishFolderId;
    private String remark;
    public Integer getSectionId() {
        return sectionId;
    }
    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }
    public Integer getSite() {
        return site;
    }
    public void setSite(Integer site) {
        this.site = site;
    }
    public Integer getParentId() {
        return parentId;
    }
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
    public String getSectionName() {
        return sectionName;
    }
    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }
    public Integer getPublishFolderId() {
        return publishFolderId;
    }
    public void setPublishFolderId(Integer publishFolderId) {
        this.publishFolderId = publishFolderId;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
}
