package com.kohler.entity;

/**
 * 
 * @author zhangqiqi
 * @Date 11/25/2014
 */
@SuppressWarnings("serial")
public class VwSolrProductComAttrEntity implements java.io.Serializable {
    private Integer productMetadataId;

    private Integer lang;

    private Integer inputType;

    private String  keyName;

    private String  categoryComAttrName;

    private String  value;

    public Integer getProductMetadataId() {
        return productMetadataId;
    }

    public void setProductMetadataId(Integer productMetadataId) {
        this.productMetadataId = productMetadataId;
    }

    public Integer getLang() {
        return lang;
    }

    public void setLang(Integer lang) {
        this.lang = lang;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getCategoryComAttrName() {
        return categoryComAttrName;
    }

    public void setCategoryComAttrName(String categoryComAttrName) {
        this.categoryComAttrName = categoryComAttrName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getInputType() {
        return inputType;
    }

    public void setInputType(Integer inputType) {
        this.inputType = inputType;
    }

}
