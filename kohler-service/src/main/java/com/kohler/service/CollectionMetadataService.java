package com.kohler.service;

import java.util.List;
import java.util.Map;

import com.kohler.dao.utils.Pager;
import com.kohler.entity.Collection;
import com.kohler.entity.CollectionMetadata;
import com.kohler.entity.extend.CollectionMetadataPojo;

public interface CollectionMetadataService {
	

    public Pager<CollectionMetadata> getCollectionsByMap(Pager<CollectionMetadata> pager,
            Map<String, Object> cod);
    
    public Pager<CollectionMetadata> getCollectionsByMap(Pager<CollectionMetadata> pager,
            CollectionMetadataPojo pojo);

	public void addCollectionMetadata(Integer valueOf,
			List<Collection> collectionList);

	public CollectionMetadata getCollectionMetadataById(CollectionMetadata collectionMetadata);

	public void editCollectionMetadata(Integer valueOf, Integer valueOf2,
			List<Collection> collectionList);

	public void deleteCollectionMetadata(String collectionMetadataIds);

}
