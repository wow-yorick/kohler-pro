/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.entity;

/**
 * Class Function Description
 * SKUAttrEntity
 * @author zhangtingting
 * @Date 2014年9月29日
 */
public class SKUAttrEntity extends BaseEntity {

	private static final long serialVersionUID = 4346401062535188313L;
	
	private Integer skuAttrId;
	private Integer skuMetadataId;
	private Integer categorySKUAttrMetadataId;
	private Integer categorySKUAttrValuesMetadataId;
	
    public Integer getSkuAttrId() {
        return skuAttrId;
    }
    public void setSkuAttrId(Integer skuAttrId) {
        this.skuAttrId = skuAttrId;
    }
    public Integer getSkuMetadataId() {
        return skuMetadataId;
    }
    public void setSkuMetadataId(Integer skuMetadataId) {
        this.skuMetadataId = skuMetadataId;
    }
    public Integer getCategorySKUAttrMetadataId() {
        return categorySKUAttrMetadataId;
    }
    public void setCategorySKUAttrMetadataId(Integer categorySKUAttrMetadataId) {
        this.categorySKUAttrMetadataId = categorySKUAttrMetadataId;
    }
    public Integer getCategorySKUAttrValuesMetadataId() {
        return categorySKUAttrValuesMetadataId;
    }
    public void setCategorySKUAttrValuesMetadataId(Integer categorySKUAttrValuesMetadataId) {
        this.categorySKUAttrValuesMetadataId = categorySKUAttrValuesMetadataId;
    }
}
