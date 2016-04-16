package com.kohler.service;

import java.util.List;

import com.kohler.entity.Collection;

public interface CollectionService {
	
	public Collection getCollectionById(Integer id);
	
	public List<Collection> getCollectionByCod(Collection collection);

}
