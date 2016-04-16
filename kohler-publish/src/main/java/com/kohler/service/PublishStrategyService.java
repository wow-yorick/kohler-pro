/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service;

import java.util.Map;

import com.kohler.bean.ConfPrepareData;

/**
 * Class Function Description
 *
 * @author Administrator
 * @Date 2014年10月27日
 */
public interface PublishStrategyService {
    /**
     * 发布具体操作
     * @param confPrepareData
     * @author Administrator
     * Date 2014年10月27日
     * @version
     */
    public Map<String, Object> publishMethod(ConfPrepareData confPrepareData, Map<String,Object> preData)  throws Exception;
}
