/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.entity.extend;

import com.kohler.entity.CategoryComAttrEntity;
import com.kohler.entity.LocaleEntity;

/**
 * Class Function Description
 *
 * @author XHY
 * @Date 2014年10月21日
 */
public class CategoryComAttrPojo extends CategoryComAttrEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2582833697955308665L;
	
	private Integer[] categoryComAttrIds;
	private Integer[] langs;
	private String[] categoryComAttrNames;
	private String[] imageUrls;
	private Integer[] imageSources;
	private Integer[] imageIds;
	private String[] imageTooltips;
	
	private Integer categoryComAttrMetadataId;
	private Integer categoryMetadataId;
	private Integer seqNo;
	private String keyName;
	private Integer inputType;
	private Boolean isFilter;
	private String dropdownTypeCode;
	
	private LocaleEntity language;
	
	private CategoryComAttrEntity categoryComAttr;
	
	public Integer[] getLangs() {
		return langs;
	}
	public void setLangs(Integer[] langs) {
		this.langs = langs;
	}
	public String[] getCategoryComAttrNames() {
		return categoryComAttrNames;
	}
	public void setCategoryComAttrNames(String[] categoryComAttrNames) {
		this.categoryComAttrNames = categoryComAttrNames;
	}
	public String[] getImageUrls() {
		return imageUrls;
	}
	public void setImageUrls(String[] imageUrls) {
		this.imageUrls = imageUrls;
	}
	public Integer[] getImageSources() {
		return imageSources;
	}
	public void setImageSources(Integer[] imageSources) {
		this.imageSources = imageSources;
	}
	public Integer[] getImageIds() {
		return imageIds;
	}
	public void setImageIds(Integer[] imageIds) {
		this.imageIds = imageIds;
	}
	public String[] getImageTooltips() {
		return imageTooltips;
	}
	public void setImageTooltips(String[] imageTooltips) {
		this.imageTooltips = imageTooltips;
	}
	
	public Integer[] getCategoryComAttrIds() {
		return categoryComAttrIds;
	}
	public void setCategoryComAttrIds(Integer[] categoryComAttrIds) {
		this.categoryComAttrIds = categoryComAttrIds;
	}
	public LocaleEntity getLanguage() {
		return language;
	}
	public void setLanguage(LocaleEntity language) {
		this.language = language;
	}
	public CategoryComAttrEntity getCategoryComAttr() {
		return categoryComAttr;
	}
	public void setCategoryComAttr(CategoryComAttrEntity categoryComAttr) {
		this.categoryComAttr = categoryComAttr;
	}
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
	public Boolean getIsFilter() {
		return isFilter;
	}
	public void setIsFilter(Boolean isFilter) {
		this.isFilter = isFilter;
	}
	public String getDropdownTypeCode() {
		return dropdownTypeCode;
	}
	public void setDropdownTypeCode(String dropdownTypeCode) {
		this.dropdownTypeCode = dropdownTypeCode;
	}
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	
	

}
