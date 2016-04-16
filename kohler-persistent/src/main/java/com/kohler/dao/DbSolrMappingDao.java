package com.kohler.dao;

import java.io.Serializable;

import com.kohler.entity.DbSolrMappingEntity;

/**
 * @author zhangqiqi
 */
public interface DbSolrMappingDao extends BaseDao<DbSolrMappingEntity> {
    public int deleteByCondition(Serializable id);


}
