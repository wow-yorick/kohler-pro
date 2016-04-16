/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.entity;

/**
 * Class Function Description
 * ProductPdfMetadata
 * @author zhangtingting
 * @Date 2014年9月25日
 */
public class ProductPDFMetadataEntity extends BaseEntity {

	private static final long serialVersionUID = 5861072656640506578L;
	
	private Integer productPDFMetadataId;
	private Integer productMetadataId;
	
	
    public Integer getProductPDFMetadataId() {
        return productPDFMetadataId;
    }
    public void setProductPDFMetadataId(Integer productPDFMetadataId) {
        this.productPDFMetadataId = productPDFMetadataId;
    }
    public Integer getProductMetadataId() {
        return productMetadataId;
    }
    public void setProductMetadataId(Integer productMetadataId) {
        this.productMetadataId = productMetadataId;
    }
}
