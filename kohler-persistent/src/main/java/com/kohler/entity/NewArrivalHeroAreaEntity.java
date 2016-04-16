package com.kohler.entity;

public class NewArrivalHeroAreaEntity extends BaseEntity{

    /**
     * 
     */
    private static final long serialVersionUID = 2277045030666963545L;
    private Integer  newArrivalProductId;
    private Integer  newArrivalMetadataId;
    private Integer  heroAreaMetadataId;
    private Integer  seqNo;
    
    
    public Integer getNewArrivalProductId() {
        return newArrivalProductId;
    }
    public void setNewArrivalProductId(Integer newArrivalProductId) {
        this.newArrivalProductId = newArrivalProductId;
    }
    public Integer getNewArrivalMetadataId() {
        return newArrivalMetadataId;
    }
    public void setNewArrivalMetadataId(Integer newArrivalMetadataId) {
        this.newArrivalMetadataId = newArrivalMetadataId;
    }
    public Integer getHeroAreaMetadataId() {
        return heroAreaMetadataId;
    }
    public void setHeroAreaMetadataId(Integer heroAreaMetadataId) {
        this.heroAreaMetadataId = heroAreaMetadataId;
    }
    public Integer getSeqNo() {
        return seqNo;
    }
    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }
}
