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
public class CategorySKUAttrValuesEntity extends BaseEntity {

	private static final long serialVersionUID = 4346401062535188313L;
	
	private Integer categorySKUAttrValuesId;
	private Integer lang;
	private Integer categorySKUAttrValuesMetadataId;
	private String attrValue;
	
    public Integer getCategorySKUAttrValuesId() {
        return categorySKUAttrValuesId;
    }
    public void setCategorySKUAttrValuesId(Integer categorySKUAttrValuesId) {
        this.categorySKUAttrValuesId = categorySKUAttrValuesId;
    }
    public Integer getLang() {
        return lang;
    }
    public void setLang(Integer lang) {
        this.lang = lang;
    }
    public Integer getCategorySKUAttrValuesMetadataId() {
        return categorySKUAttrValuesMetadataId;
    }
    public void setCategorySKUAttrValuesMetadataId(Integer categorySKUAttrValuesMetadataId) {
        this.categorySKUAttrValuesMetadataId = categorySKUAttrValuesMetadataId;
    }
    public String getAttrValue() {
        return attrValue;
    }
    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }
}
