package com.kohler.entity;

public class MasterdataMetadataEntity extends BaseEntity{

	/**
	 * ys
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer masterdataMetadataId;
	private Integer masterdataTypeId;
	private String MasterdataCode;
	private Integer seqNo;
	
	public Integer getMasterdataMetadataId() {
		return masterdataMetadataId;
	}
	public void setMasterdataMetadataId(Integer masterdataMetadataId) {
		this.masterdataMetadataId = masterdataMetadataId;
	}
	public Integer getMasterdataTypeId() {
		return masterdataTypeId;
	}
	public void setMasterdataTypeId(Integer masterdataTypeId) {
		this.masterdataTypeId = masterdataTypeId;
	}
	public String getMasterdataCode() {
		return MasterdataCode;
	}
	public void setMasterdataCode(String masterdataCode) {
		MasterdataCode = masterdataCode;
	}
	public Integer getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}
	
	
}
