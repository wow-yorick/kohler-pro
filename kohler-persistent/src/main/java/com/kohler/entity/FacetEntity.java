/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.entity;

import java.io.Serializable;


/**
 * facet entity
 * 
 * @author zqq
 * @Date 10/9/2014
 */
public class FacetEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String            name;

    private long              count;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

}
