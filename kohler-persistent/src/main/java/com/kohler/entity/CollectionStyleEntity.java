package com.kohler.entity;

public class CollectionStyleEntity extends BaseEntity {

    /**
     * 
     */
    private static final long serialVersionUID = -3865665778024460143L;
    private Integer collectionStyleId;
    private Integer collectionStyleMetadataId;
    private Integer lang;
    private String  collectionStyleName;
    
    public Integer getCollectionStyleId() {
        return collectionStyleId;
    }
    public void setCollectionStyleId(Integer collectionStyleId) {
        this.collectionStyleId = collectionStyleId;
    }
    public Integer getCollectionStyleMetadataId() {
        return collectionStyleMetadataId;
    }
    public void setCollectionStyleMetadataId(Integer collectionStyleMetadataId) {
        this.collectionStyleMetadataId = collectionStyleMetadataId;
    }
    public Integer getLang() {
        return lang;
    }
    public void setLang(Integer lang) {
        this.lang = lang;
    }
    public String getCollectionStyleName() {
        return collectionStyleName;
    }
    public void setCollectionStyleName(String collectionStyleName) {
        this.collectionStyleName = collectionStyleName;
    }
    
   

}
