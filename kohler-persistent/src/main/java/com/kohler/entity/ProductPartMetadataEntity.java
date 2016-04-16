/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.entity;

/**
 * Class Function Description
 * ProductPartMetadata
 * @author zhangtingting
 * @Date 2014年9月25日
 */
public class ProductPartMetadataEntity extends BaseEntity {

	private static final long serialVersionUID = 5861072656640506578L;
	
	private Integer productPartMetadataId;
	private Integer productMetadataId;
	
    public Integer getProductPartMetadataId() {
        return productPartMetadataId;
    }
    public void setProductPartMetadataId(Integer productPartMetadataId) {
        this.productPartMetadataId = productPartMetadataId;
    }
    public Integer getProductMetadataId() {
        return productMetadataId;
    }
    public void setProductMetadataId(Integer productMetadataId) {
        this.productMetadataId = productMetadataId;
    }
}
