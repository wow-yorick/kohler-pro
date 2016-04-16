/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.dao;

import java.util.List;
import java.util.Map;

import com.kohler.entity.ProductPDFEntity;

/**
 * Class Function Description
 * ProductPDFDao
 * @author zhangtingting
 * @Date 2014年9月25日
 */
public interface ProductPDFDao extends BaseDao<ProductPDFEntity>{

    public ProductPDFEntity getProductPDFById(Integer productPDFMetadataId);

    /**
     * @param sql
     * @param lang
     * @return
     * @author sana
     * Date 2015年1月4日
     * @version
     */
    public List<Map<String, Object>> getMapList(String sql, Integer lang);
}
