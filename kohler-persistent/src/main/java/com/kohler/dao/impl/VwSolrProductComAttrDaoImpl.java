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

import com.kohler.constants.VwSolrProductComAttrSQL;
import com.kohler.dao.VwSolrProductComAttrDao;
import com.kohler.entity.VwSolrProductComAttrEntity;

/**
 * Class Function Description
 * 
 * @author zhangqiqi
 * @Date 2014-11-17
 */
@Repository
public class VwSolrProductComAttrDaoImpl extends BaseDaoImpl<VwSolrProductComAttrEntity> implements VwSolrProductComAttrDao {
    public List<Map<String,Object>> getAttr(){
        List<Object> queryParams = new ArrayList<Object>();
        return jdbcTemplate.queryForList(VwSolrProductComAttrSQL.GET_ATTR, queryParams.toArray());
    }
    
    public List<Map<String,Object>> search(List<Object> queryParams){
        return jdbcTemplate.queryForList(VwSolrProductComAttrSQL.GET_BY_CONDITION, queryParams.toArray());
    }

}
