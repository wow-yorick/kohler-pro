/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service;

import java.util.Map;

import com.kohler.bean.ConfPrepareData;

/**
 * 特殊字段类型解析
 *
 * @author Administrator
 * @Date 2014年11月22日
 */
public interface SpecialFieldParseService {
    
    /**
     * 特殊字段解析
     * @param dataSource
     * @param _filedMap
     * @return
     * @author Administrator
     * Date 2014年11月22日
     * @version
     */
    public String parse(Map<String, Object> dataSource, Map<String, Object> _filedMap, ConfPrepareData conf);
}
