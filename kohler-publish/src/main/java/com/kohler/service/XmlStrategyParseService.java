/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service;

import java.util.Map;

import com.kohler.bean.ConfPrepareData;
import com.kohler.exception.DataException;

/**
 * Class Function Description
 *
 * @author Administrator
 * @Date 2014年10月28日
 */
public interface XmlStrategyParseService {
    /**
     * 根据xml解析后的算法map解析具体结果
     * 
     * @author Administrator
     * Date 2014年10月28日
     * @version
     */
    public Map<String,Object> resolver(ConfPrepareData confPrepareData) throws DataException;
}
