package com.kohler.entity;


public class CollectionMetadataEntity extends BaseEntity {

	private static final long serialVersionUID = 5861072656640506578L;
	
	private Integer collectionMetadataId;
	private Integer seqNo;
	
	public Integer getCollectionMetadataId() {
		return collectionMetadataId;
	}
	public void setCollectionMetadataId(Integer collectionMetadataId) {
		this.collectionMetadataId = collectionMetadataId;
	}
	public Integer getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}
	
}
