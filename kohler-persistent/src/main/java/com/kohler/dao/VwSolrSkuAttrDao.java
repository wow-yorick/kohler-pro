package com.kohler.dao;

import java.util.List;
import java.util.Map;

import com.kohler.entity.VwSolrSkuAttrEntity;

/**
 * @author zhangqiqi
 */
public interface VwSolrSkuAttrDao extends BaseDao<VwSolrSkuAttrEntity> {
    public List<Map<String,Object>> getAttr();
    
    public List<Map<String,Object>> search(List<Object> queryParams);
}
