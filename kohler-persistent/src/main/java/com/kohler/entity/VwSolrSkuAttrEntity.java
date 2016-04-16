package com.kohler.entity;


/**
 * 
 * @author zhangqiqi
 * @Date 11/25/2014
 */
@SuppressWarnings("serial")
public class VwSolrSkuAttrEntity implements java.io.Serializable {
    private Integer skuMetadataId;

    private Integer lang;

    private String  keyName;

    private String  categorySkuAttrName;

    private String  attrValue;

    public Integer getSkuMetadataId() {
        return skuMetadataId;
    }

    public void setSkuMetadataId(Integer skuMetadataId) {
        this.skuMetadataId = skuMetadataId;
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

    public String getCategorySkuAttrName() {
        return categorySkuAttrName;
    }

    public void setCategorySkuAttrName(String categorySkuAttrName) {
        this.categorySkuAttrName = categorySkuAttrName;
    }

    public String getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }

}
