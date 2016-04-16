package com.kohler.dao;

import com.kohler.entity.MasterdataEntity;

public interface SearchConfigurationDao extends BaseDao<MasterdataEntity>{
    //修改
    public int updateDidYouMean(MasterdataEntity entity);
}
