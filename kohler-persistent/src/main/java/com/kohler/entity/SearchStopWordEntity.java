/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.entity;

public class SearchStopWordEntity extends BaseEntity{

    /**
     * ys
     */
    private static final long serialVersionUID = 1L;
    
    private Integer searchStopWordId;
    private String settingValue;
    
    
    public Integer getSearchStopWordId() {
        return searchStopWordId;
    }
    public void setSearchStopWordId(Integer searchStopWordId) {
        this.searchStopWordId = searchStopWordId;
    }
    public String getSettingValue() {
        return settingValue;
    }
    public void setSettingValue(String settingValue) {
        this.settingValue = settingValue;
    }
    
	
	
}
