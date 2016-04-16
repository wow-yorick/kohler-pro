/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.entity;

import java.math.BigDecimal;

/**
 * Class Function Description
 * SKUMetadata
 * @author zhangtingting
 * @Date 2014年9月25日
 */
public class SKUMetadataEntity extends BaseEntity {

	private static final long serialVersionUID = 5861072656640506578L;
	
	private Integer skuMetadataId;
	private Integer productMetadataId;
	private BigDecimal skuPrice;
	private String skuCode;
	private Boolean isDefault;
	
    public Integer getSkuMetadataId() {
        return skuMetadataId;
    }
    public void setSkuMetadataId(Integer skuMetadataId) {
        this.skuMetadataId = skuMetadataId;
    }
    public Integer getProductMetadataId() {
        return productMetadataId;
    }
    public void setProductMetadataId(Integer productMetadataId) {
        this.productMetadataId = productMetadataId;
    }
    public Boolean getIsDefault() {
        return isDefault;
    }
    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }
    public String getSkuCode() {
        return skuCode;
    }
    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }
    public BigDecimal getSkuPrice() {
        return skuPrice;
    }
    public void setSkuPrice(BigDecimal skuPrice) {
        this.skuPrice = skuPrice;
    }
   
}