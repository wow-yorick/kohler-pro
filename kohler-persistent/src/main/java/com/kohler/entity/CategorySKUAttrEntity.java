/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.entity;

/**
 * Class Function Description CategorySKUAttrEntity
 * 
 * @author zhangtingting
 * @Date 2014年9月29日
 */
public class CategorySKUAttrEntity extends BaseEntity {

	private static final long serialVersionUID = 4346401062535188313L;

	private Integer categorySKUAttrId;
	private Integer lang;
	private Integer categorySKUAttrMetadataId;
	private String categorySkuAttrName;
	private Integer imageSource;
	private String imageUrl;
	private Integer imageId;
	private String imageTooltip;

	public Integer getCategorySKUAttrId() {
		return categorySKUAttrId;
	}

	public void setCategorySKUAttrId(Integer categorySKUAttrId) {
		this.categorySKUAttrId = categorySKUAttrId;
	}

	public Integer getLang() {
		return lang;
	}

	public void setLang(Integer lang) {
		this.lang = lang;
	}

	public Integer getCategorySKUAttrMetadataId() {
		return categorySKUAttrMetadataId;
	}

	public void setCategorySKUAttrMetadataId(Integer categorySKUAttrMetadataId) {
		this.categorySKUAttrMetadataId = categorySKUAttrMetadataId;
	}
	

	public String getCategorySkuAttrName() {
		return categorySkuAttrName;
	}

	public void setCategorySkuAttrName(String categorySkuAttrName) {
		this.categorySkuAttrName = categorySkuAttrName;
	}

	public Integer getImageSource() {
		return imageSource;
	}

	public void setImageSource(Integer imageSource) {
		this.imageSource = imageSource;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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
