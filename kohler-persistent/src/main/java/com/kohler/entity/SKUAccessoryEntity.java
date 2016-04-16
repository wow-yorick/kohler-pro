/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.entity;

/**
 * Class Function Description
 *
 * @author sana
 * @Date 2014年11月24日
 */
public class SKUAccessoryEntity extends BaseEntity{
    
    /**
     * 
     */
    private static final long serialVersionUID = -3102501708230718184L;

    private Integer skuAccessoryId;
    
    private Integer skuMetadataId;
    
    private Integer lang;
    
    private Integer categoryAccessoryMetadataId;
    
    private Integer skuPickId;
    
    private String accessoryDescription;

    public Integer getSkuAccessoryId() {
        return skuAccessoryId;
    }

    public void setSkuAccessoryId(Integer skuAccessoryId) {
        this.skuAccessoryId = skuAccessoryId;
    }

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

    public Integer getCategoryAccessoryMetadataId() {
        return categoryAccessoryMetadataId;
    }

    public void setCategoryAccessoryMetadataId(Integer categoryAccessoryMetadataId) {
        this.categoryAccessoryMetadataId = categoryAccessoryMetadataId;
    }

    public Integer getSkuPickId() {
        return skuPickId;
    }

    public void setSkuPickId(Integer skuPickId) {
        this.skuPickId = skuPickId;
    }

    public String getAccessoryDescription() {
        return accessoryDescription;
    }

    public void setAccessoryDescription(String accessoryDescription) {
        this.accessoryDescription = accessoryDescription;
    }
    
    
}
