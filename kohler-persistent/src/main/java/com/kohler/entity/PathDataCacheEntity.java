/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.entity;

/**
 * Class Function Description
 *
 * @author Administrator
 * @Date 2015年1月14日
 */
public class PathDataCacheEntity extends BaseEntity  {
    
    private static final long serialVersionUID = 3503479892151999704L;

    private Integer pathDataCacheId;
    private String keyName;
    private String fieldName;
    private String fieldValue;
    
    public Integer getPathDataCacheId() {
        return pathDataCacheId;
    }
    public void setPathDataCacheId(Integer pathDataCacheId) {
        this.pathDataCacheId = pathDataCacheId;
    }
    public String getKeyName() {
        return keyName;
    }
    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }
    public String getFieldName() {
        return fieldName;
    }
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
    public String getFieldValue() {
        return fieldValue;
    }
    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }
    
    
    
}
