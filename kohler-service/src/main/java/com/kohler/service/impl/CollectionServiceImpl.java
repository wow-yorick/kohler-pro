package com.kohler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohler.dao.CollectionDao;
import com.kohler.entity.Collection;
import com.kohler.service.CollectionService;

@Service
public class CollectionServiceImpl implements CollectionService {

	@Autowired 
	public CollectionDao collectionDao;

	@Override
	public Collection getCollectionById(Integer id) {
		return collectionDao.getCollectionById(id);
	}

	@Override
	public List<Collection> getCollectionByCod(Collection collection) {
		return collectionDao.selectByCondition(collection);
	}
	

}
