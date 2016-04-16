/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service;

import java.util.List;
import java.util.Map;

import com.kohler.bean.ConfPrepareData;
import com.kohler.exception.DataException;

/**
 * 面包屑接口
 *
 * @author Administrator
 * @Date 2014年10月31日
 */
public interface BreadCrumbService {
    
    /**
     * 面包屑接口
     * @param confPrepareData
     * @return
     * @author Administrator
     * Date 2014年11月7日
     * @version
     */
    public List<Map<String,Object>> getBreadCrumb(ConfPrepareData confPrepareData) throws DataException;
}
