/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.entity;

/**
 * 
 * Class Function Description
 *
 * @author XHY
 * @Date 2014年11月10日
 */
public class CategorySearchKeywordMetadataEntity extends BaseEntity {

	private static final long serialVersionUID = 5861072656640506578L;
	
	private Integer categorySearchKeywordMetadataId;
	private Integer categoryMetadataId;
	private Integer seqNo;
	
	
	public Integer getCategorySearchKeywordMetadataId() {
		return categorySearchKeywordMetadataId;
	}
	public void setCategorySearchKeywordMetadataId(
			Integer categorySearchKeywordMetadataId) {
		this.categorySearchKeywordMetadataId = categorySearchKeywordMetadataId;
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
	
    
}
