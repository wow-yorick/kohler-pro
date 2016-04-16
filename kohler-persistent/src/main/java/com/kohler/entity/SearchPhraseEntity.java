/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.entity;

public class SearchPhraseEntity extends BaseEntity{

    /**
     * ys
     */
    private static final long serialVersionUID = 1L;
    
    private Integer searchPhraseId;
    private String settingValue;
    
    public Integer getSearchPhraseId() {
        return searchPhraseId;
    }
    public void setSearchPhraseId(Integer searchPhraseId) {
        this.searchPhraseId = searchPhraseId;
    }
    public String getSettingValue() {
        return settingValue;
    }
    public void setSettingValue(String settingValue) {
        this.settingValue = settingValue;
    }
    
	
	
}
