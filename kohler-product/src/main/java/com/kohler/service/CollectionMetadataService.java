package com.kohler.service;

import java.util.List;
import java.util.Map;

import com.kohler.dao.utils.Pager;
import com.kohler.entity.CollectionEntity;
import com.kohler.entity.CollectionMetadataEntity;
import com.kohler.entity.extend.CollectionMetadataPojo;

public interface CollectionMetadataService {
	

    public Pager<CollectionMetadataEntity> getCollectionsByMap(Pager<CollectionMetadataEntity> pager,
            Map<String, Object> cod);
    
    public Pager<Map<String,Object>> getCollectionsByMap(Pager<Map<String,Object>> pager,
            CollectionMetadataPojo pojo);

	public void addCollectionMetadata(Integer valueOf,
			List<CollectionEntity> collectionList);

	public CollectionMetadataEntity getCollectionMetadataById(CollectionMetadataEntity collectionMetadata);

	public void editCollectionMetadata(Integer valueOf, Integer valueOf2,
			List<CollectionEntity> collectionList);

	public void deleteCollectionMetadata(String collectionMetadataIds);

}
