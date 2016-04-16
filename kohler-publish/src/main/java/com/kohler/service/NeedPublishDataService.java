/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service;

import java.util.List;

import com.kohler.bean.ConfPrepareData;

/**
 * 批量发布获取需要发布的数据列表ID
 *
 * @author Administrator
 * @Date 2014年11月17日
 */
public interface NeedPublishDataService {
    /**
     * 获取需要发布的数据列表的ID
     * @param conf
     * @return
     * @author Administrator
     * Date 2014年11月17日
     * @version
     */
    public List<Integer> getPrimaryKeyList(Integer keyId, ConfPrepareData conf);
}
