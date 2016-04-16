/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * facet object entity
 * 
 * @author zqq
 * @Date 10/9/2014
 */
public class FacetObjectEntity implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private String            facetName;

    private String            initName;

    private List<FacetEntity> list             = new ArrayList<FacetEntity>();

    public String getFacetName() {
        return facetName;
    }

    public void setFacetName(String facetName) {
        this.facetName = facetName;
    }

    public List<FacetEntity> getList() {
        return list;
    }

    public void setList(List<FacetEntity> list) {
        this.list = list;
    }

    public String getInitName() {
        return initName;
    }

    public void setInitName(String initName) {
        this.initName = initName;
    }

}
