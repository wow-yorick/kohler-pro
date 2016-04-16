/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.bean;

/**
 * EditDataType Bean
 * 
 * @author kangmin_Qu
 * @Date 2014-10-17
 */
public class Check {

    /**
     * 是否必填
     */
    private String required;

    /**
     * 最大长度
     */
    private String maxLength;

    /**
     * 正则表达式
     */
    private String pattern;
    
    /**
     * 是否唯一
     */
    private String isUnique;
    
    

    public String getIsUnique() {
        return isUnique;
    }

    public void setIsUnique(String isUnique) {
        this.isUnique = isUnique;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(String maxLength) {
        this.maxLength = maxLength;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}
