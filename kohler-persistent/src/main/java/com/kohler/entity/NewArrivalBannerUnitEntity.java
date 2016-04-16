package com.kohler.entity;

public class NewArrivalBannerUnitEntity extends BaseEntity{

    /**
     * 
     */
    private static final long serialVersionUID = 5713480844024003905L;
    private Integer  newArrivalProductId;
    private Integer  newArrivalMetadataId;
    private Integer  bannerUnitMetadataId;
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
    public Integer getBannerUnitMetadataId() {
        return bannerUnitMetadataId;
    }
    public void setBannerUnitMetadataId(Integer bannerUnitMetadataId) {
        this.bannerUnitMetadataId = bannerUnitMetadataId;
    }
    public Integer getSeqNo() {
        return seqNo;
    }
    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }
    
    
}
