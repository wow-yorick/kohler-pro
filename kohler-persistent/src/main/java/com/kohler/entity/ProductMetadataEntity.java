/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.entity;

import java.util.Date;

/**
 * Class Function Description
 * ProductMetadata
 * @author zhangtingting
 * @Date 2014年9月25日
 */
public class ProductMetadataEntity extends BaseEntity {

	private static final long serialVersionUID = 5861072656640506578L;
	
	private Integer productMetadataId;
	private Integer categoryMetadataId;
	private Integer seqNo;
	private Integer collectionMetadataId;
	private String productCode;
	private Date publishDate;
	
    public Integer getProductMetadataId() {
        return productMetadataId;
    }
    public void setProductMetadataId(Integer productMetadataId) {
        this.productMetadataId = productMetadataId;
    }
    public Integer getCategoryMetadataId() {
        return categoryMetadataId;
    }
    public void setCategoryMetadataId(Integer categoryMetadataId) {
        this.categoryMetadataId = categoryMetadataId;
    }
    public Integer getSeqNo() {
        return seqNo;
    }
    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }
    public Integer getCollectionMetadataId() {
        return collectionMetadataId;
    }
    public void setCollectionMetadataId(Integer collectionMetadataId) {
        this.collectionMetadataId = collectionMetadataId;
    }
    public String getProductCode() {
        return productCode;
    }
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
    public Date getPublishDate() {
        return publishDate;
    }
    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
}
