/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service;

import java.util.Map;

import com.kohler.bean.ConfPrepareData;

/**
 * 批量发布
 *
 * @author Administrator
 * @Date 2014年11月17日
 */
public interface BatchPublishService {
    /**
     * 整站批量发布
     * @param platform
     * @param isPreview
     * @author Administrator
     * Date 2014年11月17日
     * @version
     */
    public Map<String, Object> start(ConfPrepareData conf);
}
