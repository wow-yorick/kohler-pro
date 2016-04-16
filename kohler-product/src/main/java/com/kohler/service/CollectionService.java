package com.kohler.service;

import java.util.List;

import com.kohler.entity.CollectionEntity;

public interface CollectionService {
	
	public CollectionEntity getCollectionById(Integer id);
	
	public List<CollectionEntity> getCollectionByCod(CollectionEntity collection);
	
	/**
	 * 获取默认语言下的collection集合列表
	 * @return list
	 */
	public List<CollectionEntity> getCollectionsByDefault();

	public Integer existCollectionName(String collectionName,String localeId,String collectionId);
}
