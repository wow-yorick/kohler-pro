/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.entity;

public class SearchKeywordRedirectionEntity extends BaseEntity{

    /**
     * ys
     */
    private static final long serialVersionUID = 1L;
    
    private Integer searchKeywordRedirectionId;
    private String keyword;
    private String redirectionUrl;
    
    public Integer getSearchKeywordRedirectionId() {
        return searchKeywordRedirectionId;
    }
    public void setSearchKeywordRedirectionId(Integer searchKeywordRedirectionId) {
        this.searchKeywordRedirectionId = searchKeywordRedirectionId;
    }
    public String getKeyword() {
        return keyword;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    public String getRedirectionUrl() {
        return redirectionUrl;
    }
    public void setRedirectionUrl(String redirectionUrl) {
        this.redirectionUrl = redirectionUrl;
    }
    
    
}
