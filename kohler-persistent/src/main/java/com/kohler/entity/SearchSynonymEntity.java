/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.entity;

public class SearchSynonymEntity extends BaseEntity{

    /**
     * ys
     */
    private static final long serialVersionUID = 1L;
    
    private Integer searchSynonymId;
    private String settingValue;
    
    
    public Integer getSearchSynonymId() {
        return searchSynonymId;
    }
    public void setSearchSynonymId(Integer searchSynonymId) {
        this.searchSynonymId = searchSynonymId;
    }
    public String getSettingValue() {
        return settingValue;
    }
    public void setSettingValue(String settingValue) {
        this.settingValue = settingValue;
    }
    
	
	
}
