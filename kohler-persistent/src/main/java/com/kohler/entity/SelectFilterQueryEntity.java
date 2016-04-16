/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * select filter query entity
 * 
 * @author zqq
 * @Date 10/9/2014
 */
public class SelectFilterQueryEntity {
    private String                title;

    private List<FacetNameEntity> selected = new ArrayList<FacetNameEntity>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<FacetNameEntity> getSelected() {
        return selected;
    }

    public void setSelected(List<FacetNameEntity> selected) {
        this.selected = selected;
    }

}
