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
 * @Date 2014年10月20日
 */
public interface DataPrepareService {
    /**
     * 数据准备统一接口
     * @param confDefault
     * @return
     * @author Administrator
     * Date 2014年10月22日
     * @version
     */
    public Map<String, Object> getGeneralData(ConfPrepareData confPrepareData);
     
}
