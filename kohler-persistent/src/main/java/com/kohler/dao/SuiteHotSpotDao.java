/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.dao;

import com.kohler.entity.SuiteHotSpotEntity;

/**
 * Class Function Description
 *
 * @author Administrator
 * @Date 2014年11月28日
 */
public interface SuiteHotSpotDao extends BaseDao<SuiteHotSpotEntity> {

    /**
     * delete by suite metadataid
     * @param suiteMetadataId
     * @return
     * @author Administrator
     * Date 2014年12月23日
     * @version
     */
    public Integer deleteBySuiteMetadataId(Integer suiteMetadataId);
}
