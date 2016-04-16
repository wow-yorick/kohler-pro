/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.dao.utils;

/**
 * 名称处理接口
 *
 * @author shefeng
 * @Date 2014年9月25日
 */
public interface NameHandler {

    /**
     * 根据实体名获取表名
     *
     * @param entityName
     * @return
     * @version
     */
    public String getTableName(String entityName);

    
    
    
    /**
     * 根据表名获取主键名
     * 
     * @param entityName
     * @return
     * @version
     */
    public String getPrimaryName(String entityName);

    
    
    /**
     * 根据属性名获取列名
     *
     * @param fieldName
     * @return
     * @version
     */
    public String getColumnName(String fieldName);
}