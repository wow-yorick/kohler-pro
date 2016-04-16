package com.kohler.dao;

import com.kohler.entity.CollectionStyleEntity;
public interface CollectionStyleDao extends BaseDao<CollectionStyleEntity>{
    /**
     * Write Log For Delect CollectionStyle
     * @return
     * @author fujiajun
     * Date 2014-10-17
     * @version
     */
    public Integer DelectCollectionStyle(String sql,Integer[] ids);

    
}
