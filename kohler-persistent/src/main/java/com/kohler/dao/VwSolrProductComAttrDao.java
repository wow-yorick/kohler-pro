package com.kohler.dao;

import java.util.List;
import java.util.Map;

import com.kohler.entity.VwSolrProductComAttrEntity;

/**
 * @author zhangqiqi
 */
public interface VwSolrProductComAttrDao extends BaseDao<VwSolrProductComAttrEntity> {
    public List<Map<String,Object>> getAttr();
    
    public List<Map<String,Object>> search(List<Object> queryParams);
}
