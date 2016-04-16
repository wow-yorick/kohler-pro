package com.kohler.dao;

import java.util.List;
import java.util.Map;

import com.kohler.entity.VwSolrSkuEntity;

/**
 * @author zhangqiqi
 */
public interface VwSolrSkuDao extends BaseDao<VwSolrSkuEntity> {
    public List<Map<String,Object>> getAll();
    
    public List<Map<String,Object>> getByTime(String start);
}
