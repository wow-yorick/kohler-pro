/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service;

import java.util.Map;

import com.kohler.bean.ConfPrepareData;

/**
 * 按类别发布
 *
 * @author Administrator
 * @Date 2014年11月28日
 */
public interface ClassifyPublishService {
    
    /**
     * 按列表发布方法
     * @param keyId
     * @param conf
     * @return
     * @author Administrator
     * Date 2014年11月28日
     * @version
     */
    Map<String,Object> batchPublish(Integer keyId, ConfPrepareData conf);
}
