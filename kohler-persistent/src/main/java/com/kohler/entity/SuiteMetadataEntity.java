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
public class SuiteMetadataEntity extends BaseEntity{

    private static final long serialVersionUID = -3327506566075222661L;
    
    private Integer suiteMetadataId;
	private Integer seqNo;
	private Integer categoryMetadataId;
	public Integer getSuiteMetadataId() {
		return suiteMetadataId;
	}
	public void setSuiteMetadataId(Integer suiteMetadataId) {
		this.suiteMetadataId = suiteMetadataId;
	}
	public Integer getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}
    public Integer getCategoryMetadataId() {
        return categoryMetadataId;
    }
    public void setCategoryMetadataId(Integer categoryMetadataId) {
        this.categoryMetadataId = categoryMetadataId;
    }
    
}
