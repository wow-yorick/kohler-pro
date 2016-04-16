/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.entity;

/**
 * Class Function Description
 *
 * @author Sweety
 * @Date 2014年9月26日
 */
public class SuiteProductEntity extends BaseEntity{

    private static final long serialVersionUID = -3327506566075222661L;
    
    private Integer suiteProductId;
	private Integer suiteMetadataId;
	private Integer skuMetadataId;
	private Integer seqNo;
	
	public Integer getSuiteProductId() {
		return suiteProductId;
	}
	public void setSuiteProductId(Integer suiteProductId) {
		this.suiteProductId = suiteProductId;
	}
	public Integer getSuiteMetadataId() {
		return suiteMetadataId;
	}
	public void setSuiteMetadataId(Integer suiteMetadataId) {
		this.suiteMetadataId = suiteMetadataId;
	}
	public Integer getSkuMetadataId() {
		return skuMetadataId;
	}
	public void setSkuMetadataId(Integer skuMetadataId) {
		this.skuMetadataId = skuMetadataId;
	}
	public Integer getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}
    
}
