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
 * @Date 2014年10月23日
 */
public class PageFieldValuesEntity extends BaseEntity {
    
    private Integer pageFieldValuesId;
    private Integer pageId;
    private String fieldName;
    private String fieldValue;
    
    public Integer getPageFieldValuesId() {
        return pageFieldValuesId;
    }
    public void setPageFieldValuesId(Integer pageFieldValuesId) {
        this.pageFieldValuesId = pageFieldValuesId;
    }
    public Integer getPageId() {
        return pageId;
    }
    public void setPageId(Integer pageId) {
        this.pageId = pageId;
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
