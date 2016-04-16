/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.entity;

/**
 * Class Function Description
 * Product
 * @author Whh
 * @Date 2014年11月12日
 */
public class ProductComAttrEntity extends BaseEntity {

	/**
     * 
     */
    private static final long serialVersionUID = -7796290947711146455L;
    
    private Integer productComAttrId;
	private Integer lang;
	private Integer productMetadataId;
	private Integer categoryComAttrMetadataId;
	private String value;
    public Integer getProductComAttrId() {
        return productComAttrId;
    }
    public void setProductComAttrId(Integer productComAttrId) {
        this.productComAttrId = productComAttrId;
    }
    public Integer getLang() {
        return lang;
    }
    public void setLang(Integer lang) {
        this.lang = lang;
    }
    public Integer getProductMetadataId() {
        return productMetadataId;
    }
    public void setProductMetadataId(Integer productMetadataId) {
        this.productMetadataId = productMetadataId;
    }
    public Integer getCategoryComAttrMetadataId() {
        return categoryComAttrMetadataId;
    }
    public void setCategoryComAttrMetadataId(Integer categoryComAttrMetadataId) {
        this.categoryComAttrMetadataId = categoryComAttrMetadataId;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

	
	
	
}
