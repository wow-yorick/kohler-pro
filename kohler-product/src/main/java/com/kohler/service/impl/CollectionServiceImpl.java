package com.kohler.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohler.dao.CollectionDao;
import com.kohler.entity.CollectionEntity;
import com.kohler.service.CollectionService;

@Service
public class CollectionServiceImpl implements CollectionService {

	@Autowired 
	public CollectionDao collectionDao;

	@Override
	public CollectionEntity getCollectionById(Integer id) {
		return collectionDao.getCollectionById(id);
	}

	@Override
	public List<CollectionEntity> getCollectionByCod(CollectionEntity collection) {
		return collectionDao.selectByCondition(collection);
	}

    @Override
    public List<CollectionEntity> getCollectionsByDefault() {
        return collectionDao.getCollectionsByDefault();
    }
	
    @Override
    public Integer existCollectionName(String collectionName,String localeId,String collectionId) {
    	List<Object> params = new ArrayList<Object>(); 
    	params.add(collectionName);
    	params.add(localeId);
    	if (collectionName == "")
    	{
    		return 0;
    	}
    	if (collectionId == "")
    	{
    		return collectionDao.selectCountByCondition(" select count(*) from COLLECTION WHERE COLLECTION_NAME = ? AND LANG = ? AND IS_ENABLE = 1", params);
    	}else
    	{
    		params.add(collectionId);
    		return collectionDao.selectCountByCondition(" select count(*) from COLLECTION WHERE COLLECTION_NAME = ? AND LANG = ? AND IS_ENABLE = 1 AND COLLECTION_METADATA_ID != ? ", params);
    	}
    }
}
