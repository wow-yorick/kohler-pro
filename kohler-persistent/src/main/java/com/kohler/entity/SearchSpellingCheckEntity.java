/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.entity;

public class SearchSpellingCheckEntity extends BaseEntity{

    /**
     * ys
     */
    private static final long serialVersionUID = 1L;
    
    private Integer searchSpellingCheckId;
    private String settingValue;
    public Integer getSearchSpellingCheckId() {
        return searchSpellingCheckId;
    }
    public void setSearchSpellingCheckId(Integer searchSpellingCheckId) {
        this.searchSpellingCheckId = searchSpellingCheckId;
    }
    public String getSettingValue() {
        return settingValue;
    }
    public void setSettingValue(String settingValue) {
        this.settingValue = settingValue;
    }
    
	
	
}
