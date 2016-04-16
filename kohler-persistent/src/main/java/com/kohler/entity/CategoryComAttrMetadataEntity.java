/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.entity;

/**
 * Class Function Description
 * CategoryComAttrMetadata
 * @author zhangtingting
 * @Date 2014年9月25日
 */
public class CategoryComAttrMetadataEntity extends BaseEntity {

	private static final long serialVersionUID = 5861072656640506578L;
	
	private Integer categoryComAttrMetadataId;
	private Integer categoryMetadataId;
	private String keyName;
	private Integer seqNo;
	private Integer inputType;
	private Boolean isFilter;
	private String dropdownTypeCode;
	
    public Integer getCategoryComAttrMetadataId() {
        return categoryComAttrMetadataId;
    }
    public void setCategoryComAttrMetadataId(Integer categoryComAttrMetadataId) {
        this.categoryComAttrMetadataId = categoryComAttrMetadataId;
    }
    public Integer getCategoryMetadataId() {
        return categoryMetadataId;
    }
    public void setCategoryMetadataId(Integer categoryMetadataId) {
        this.categoryMetadataId = categoryMetadataId;
    }
    public Integer getSeqNo() {
        return seqNo;
    }
    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }
    public Integer getInputType() {
        return inputType;
    }
    public void setInputType(Integer inputType) {
        this.inputType = inputType;
    }
    public String getDropdownTypeCode() {
        return dropdownTypeCode;
    }
    public void setDropdownTypeCode(String dropdownTypeCode) {
        this.dropdownTypeCode = dropdownTypeCode;
    }
    public Boolean getIsFilter() {
        return isFilter;
    }
    public void setIsFilter(Boolean isFilter) {
        this.isFilter = isFilter;
    }
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
    
}
