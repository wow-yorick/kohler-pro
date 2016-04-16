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
 * xml函数工厂
 *
 * @author Administrator
 * @Date 2014年12月9日
 */
public interface XMLFuncIFactoryService {
    /**
     * 函数工厂
     * @param resultMap
     * @param dataALGMap
     * @return
     * @author Administrator
     * Date 2014年12月9日
     * @version
     */
    public Map<String,Object> funcFactory(Map<String,Object> resultMap, Map<String, Object> dataALGMap, ConfPrepareData conf) throws DataException;
}
