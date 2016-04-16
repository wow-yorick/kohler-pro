/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.dao.utils;

import com.kohler.util.CamelCaseUtils;

/**
 * 默认名称处理handler
 *
 * @author shefeng
 * @Date 2014年9月25日
 */
public class DefaultNameHandler implements NameHandler {


    private static final String PRI_SUFFIX = "_ID";//主键后缀

    /**
     * 根据实体名获取表名
     *
     * @param entityName
     * @return
     */
    @Override
    public String getTableName(String entityName) {
        return CamelCaseUtils.toUnderlineName(entityName.replace("Entity", "")).toUpperCase();
    }

    
    
    /**
     * 根据实体名获取主键名
     *
     * @param entityName
     * @return
     */
    @Override
    public String getPrimaryName(String entityName) {
    	String tableName = CamelCaseUtils.toUnderlineName(entityName.replace("Entity", "")).toUpperCase();
    	return tableName + PRI_SUFFIX;
    }

    
    
    /**
     * 根据属性名获取列名
     *
     * @param fieldName
     * @return
     */
    @Override
    public String getColumnName(String fieldName) {
        return CamelCaseUtils.toUnderlineName(fieldName).toUpperCase();
    }
}