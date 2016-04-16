/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.dao;

import java.util.List;
import java.util.Map;

import com.kohler.entity.ProductCADEntity;

/**
 * Class Function Description
 * ProcdutPartDao
 * @author zhangtingting
 * @Date 2014年9月25日
 */
public interface ProductCADDao extends BaseDao<ProductCADEntity>{

    public ProductCADEntity getProductCADById(Integer productCADMetadataId);

    /**
     * @param sql
     * @param lang
     * @return
     * @author sana
     * Date 2014年12月30日
     * @version
     */
    public List<Map<String, Object>> getMapList(String sql, Integer lang);
}
