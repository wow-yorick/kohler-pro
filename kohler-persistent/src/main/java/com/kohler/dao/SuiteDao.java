package com.kohler.dao;

import com.kohler.entity.SuiteEntity;

public interface SuiteDao extends BaseDao<SuiteEntity>{
    public Integer deleteByMetaDataId(Integer MetadataId);
}
