/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kohler.constants.VwSolrSkuAttrSQL;
import com.kohler.dao.VwSolrSkuAttrDao;
import com.kohler.entity.VwSolrSkuAttrEntity;

/**
 * Class Function Description
 * 
 * @author zhangqiqi
 * @Date 2014-11-17
 */
@Repository
public class VwSolrSkuAttrDaoImpl extends BaseDaoImpl<VwSolrSkuAttrEntity> implements VwSolrSkuAttrDao {
    public List<Map<String,Object>> getAttr(){
        List<Object> queryParams = new ArrayList<Object>();
        return jdbcTemplate.queryForList(VwSolrSkuAttrSQL.GET_ATTR, queryParams.toArray());
    }
    
    public List<Map<String,Object>> search(List<Object> queryParams){
        return jdbcTemplate.queryForList(VwSolrSkuAttrSQL.GET_BY_CONDITION, queryParams.toArray());
    }
}
