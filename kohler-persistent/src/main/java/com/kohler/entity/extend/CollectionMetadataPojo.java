package com.kohler.entity.extend;

import com.kohler.entity.CollectionMetadataEntity;


public class CollectionMetadataPojo extends CollectionMetadataEntity {
    
    private static final long serialVersionUID = -4608929023202664892L;
    
    private String collectionName;

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

}
