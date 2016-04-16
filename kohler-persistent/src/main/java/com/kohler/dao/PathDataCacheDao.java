/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.dao;

import com.kohler.entity.PathDataCacheEntity;

/**
 * Class Function Description
 *
 * @author Administrator
 * @Date 2015年1月14日
 */
public interface PathDataCacheDao extends BaseDao<PathDataCacheEntity> {

    /**
     * @param key
     * @author Administrator
     * Date 2015年1月14日
     * @version
     */
    void deleteByKey(String key);

}
