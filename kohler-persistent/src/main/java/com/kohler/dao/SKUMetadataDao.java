/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.dao;

import java.util.Map;

import com.kohler.entity.SKUMetadataEntity;

/**
 * Class Function Description
 * SKUMetadataDao
 * @author zhangtingting
 * @Date 2014年9月25日
 */
public interface SKUMetadataDao extends BaseDao<SKUMetadataEntity>{

    /**
     * @param skuId
     * @return
     * @author sana
     * Date 2014年12月2日
     * @version
     */
    Map<String, Object> getFileNamesBySkuId(Integer skuId);

}
