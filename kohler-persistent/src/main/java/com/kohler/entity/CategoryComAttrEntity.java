/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.entity;

/**
 * Class Function Description CategoryComAttr
 * 
 * @author zhangtingting
 * @Date 2014年9月25日
 */
public class CategoryComAttrEntity extends BaseEntity {

	private static final long serialVersionUID = 4346401062535188313L;

	private Integer categoryComAttrId;
	private Integer lang;
	private Integer categoryComAttrMetadataId;
	private String categoryComAttrName;
	private String imageUrl;
	private Integer imageSource;
	private Integer imageId;
	private String imageTooltip;

	public Integer getCategoryComAttrId() {
		return categoryComAttrId;
	}

	public void setCategoryComAttrId(Integer categoryComAttrId) {
		this.categoryComAttrId = categoryComAttrId;
	}

	public Integer getLang() {
		return lang;
	}

	public void setLang(Integer lang) {
		this.lang = lang;
	}

	public Integer getCategoryComAttrMetadataId() {
		return categoryComAttrMetadataId;
	}

	public void setCategoryComAttrMetadataId(Integer categoryComAttrMetadataId) {
		this.categoryComAttrMetadataId = categoryComAttrMetadataId;
	}

	public String getCategoryComAttrName() {
		return categoryComAttrName;
	}

	public void setCategoryComAttrName(String categoryComAttrName) {
		this.categoryComAttrName = categoryComAttrName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Integer getImageSource() {
		return imageSource;
	}

	public void setImageSource(Integer imageSource) {
		this.imageSource = imageSource;
	}

	public Integer getImageId() {
		return imageId;
	}

	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

	public String getImageTooltip() {
		return imageTooltip;
	}

	public void setImageTooltip(String imageTooltip) {
		this.imageTooltip = imageTooltip;
	}
	
}
