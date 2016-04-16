/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.entity;

/**
 * Class Function Description
 * ProductVideoMetadata
 * @author zhangtingting
 * @Date 2014年9月25日
 */
public class ProductVideoMetadataEntity extends BaseEntity {

	private static final long serialVersionUID = 5861072656640506578L;
	
	private Integer productVideoMetadataId;
	private Integer productMetadataId;
	
    public Integer getProductVideoMetadataId() {
        return productVideoMetadataId;
    }
    public void setProductVideoMetadataId(Integer productVideoMetadataId) {
        this.productVideoMetadataId = productVideoMetadataId;
    }
    public Integer getProductMetadataId() {
        return productMetadataId;
    }
    public void setProductMetadataId(Integer productMetadataId) {
        this.productMetadataId = productMetadataId;
    }
}
