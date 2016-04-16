/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service;

import com.kohler.bean.ConfPrepareData;
import com.kohler.exception.DataException;

/**
 * Class Function Description
 *
 * @author Administrator
 * @Date 2014年11月14日
 */
public interface UrlAnalysisService {
    
    /**
     * 根据具体策略获取url
     * @return
     * @author Administrator
     * Date 2014年11月14日
     * @version
     */
    public String getUrl(Integer dataId, ConfPrepareData confPrepareData) throws DataException;
}
