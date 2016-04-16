/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.entity;

/**
 * Class Function Description
 * CategorySKUAttrValuesEntity
 * @author zhangtingting
 * @Date 2014年9月29日
 */
public class CategorySKUAttrValuesMetadataEntity extends BaseEntity {

	private static final long serialVersionUID = 4346401062535188313L;
	
	private Integer categorySKUAttrValuesMetadataId;
	private Integer categorySkuAttrMetadataId;
	private Integer imageId;
	private String subcode;

    public Integer getCategorySKUAttrValuesMetadataId() {
        return categorySKUAttrValuesMetadataId;
    }
    public void setCategorySKUAttrValuesMetadataId(Integer categorySKUAttrValuesMetadataId) {
        this.categorySKUAttrValuesMetadataId = categorySKUAttrValuesMetadataId;
    }
	public Integer getCategorySkuAttrMetadataId() {
		return categorySkuAttrMetadataId;
	}
	public void setCategorySkuAttrMetadataId(Integer categorySkuAttrMetadataId) {
		this.categorySkuAttrMetadataId = categorySkuAttrMetadataId;
	}
	public Integer getImageId() {
		return imageId;
	}
	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}
	public String getSubcode() {
		return subcode;
	}
	public void setSubcode(String subcode) {
		this.subcode = subcode;
	}
   
    
}
