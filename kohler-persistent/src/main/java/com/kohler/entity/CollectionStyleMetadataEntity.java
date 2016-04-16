package com.kohler.entity;


public class CollectionStyleMetadataEntity extends BaseEntity {

	
	/**
     * 
     */
    private static final long serialVersionUID = 7990940671529958188L;
    private Integer collectionStyleMetadataId;
    private Integer parentId;
	private Integer seqNo;
	
	public Integer getCollectionStyleMetadataId() {
        return collectionStyleMetadataId;
    }
    public void setCollectionStyleMetadataId(Integer collectionStyleMetadataId) {
        this.collectionStyleMetadataId = collectionStyleMetadataId;
    }
    public Integer getParentId() {
        return parentId;
    }
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
	public Integer getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}
	
}
