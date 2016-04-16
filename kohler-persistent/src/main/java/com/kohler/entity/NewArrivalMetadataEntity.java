package com.kohler.entity;

public class NewArrivalMetadataEntity extends BaseEntity{

    /**
     * 
     */
    private static final long serialVersionUID = 4859588447861832429L;
    private Integer  newArrivalMetadataId;
    private Integer  categoryMetadataId;
    private Integer  seqNo;
    
    
    public Integer getNewArrivalMetadataId() {
        return newArrivalMetadataId;
    }

    public void setNewArrivalMetadataId(Integer newArrivalMetadataId) {
        this.newArrivalMetadataId = newArrivalMetadataId;
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
