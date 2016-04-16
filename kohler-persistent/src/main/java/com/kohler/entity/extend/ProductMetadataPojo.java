package com.kohler.entity.extend;

import com.kohler.entity.ProductMetadataEntity;


public class ProductMetadataPojo extends ProductMetadataEntity {
    
    private static final long serialVersionUID = -4608929023202664892L;
    
    private String publishTime;
    
    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }
}
