package com.kohler.dao;

import java.util.List;

import com.kohler.entity.CollectionEntity;

public interface CollectionDao extends BaseDao<CollectionEntity>{

	public CollectionEntity getCollectionById(Integer id);
	
	public List<CollectionEntity> getCollectionsByDefault();
}
