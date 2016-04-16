package com.kohler.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kohler.constants.CommonConstants;
import com.kohler.dao.CollectionDao;
import com.kohler.dao.CollectionMetadataDao;
import com.kohler.dao.SysLogDao;
import com.kohler.dao.utils.Pager;
import com.kohler.entity.CollectionEntity;
import com.kohler.entity.CollectionMetadataEntity;
import com.kohler.entity.extend.CollectionMetadataPojo;
import com.kohler.service.CollectionMetadataService;

@Service
public class CollectionMetadataServiceImpl implements CollectionMetadataService {

	@Autowired 
	public CollectionMetadataDao collectionMetadataDao;
	
	@Autowired 
	public CollectionDao collectionDao;
	
	@Autowired 
	public SysLogDao sysLogDao;


	@Override
	public Pager<CollectionMetadataEntity> getCollectionsByMap(Pager<CollectionMetadataEntity> pager,
			Map<String, Object> cod) {
	    List<Object> queryParams = new ArrayList<Object>();
	    StringBuilder querySql = new StringBuilder(" select * from COLLECTION_METADATA collectionMetadata "
                + " inner join COLLECTION collection on collectionMetadata.COLLECTION_METADATA_ID = collection.COLLECTION_METADATA_ID "
                + " inner join LOCALE locale on collection.LANG = locale.LOCALE_ID ");
        StringBuilder countSql = new StringBuilder(" select count(*) from COLLECTION_METADATA collectionMetadata "
                + " inner join COLLECTION collection on collectionMetadata.COLLECTION_METADATA_ID = collection.COLLECTION_METADATA_ID "
                + " inner join LOCALE locale on collection.LANG = locale.LOCALE_ID ");
        
        querySql.append(" where collectionMetadata.IS_ENABLE = 1 and collection.IS_ENABLE = 1 and locale.IS_ENABLE = 1 and locale.IS_ENABLE = 1 ");
        countSql.append(" where collectionMetadata.IS_ENABLE = 1 and collection.IS_ENABLE = 1 and locale.IS_ENABLE = 1 and locale.IS_ENABLE = 1 ");
        
        Object collectionName = cod.get("collectionName");
        if(collectionName !=null){
            querySql.append(" and collection.COLLECTION_NAME like concat(concat('%',?),'%') ");
            countSql.append(" and collection.COLLECTION_NAME like concat(concat('%',?),'%') ");
            queryParams.add(collectionName.toString());
        }
        querySql.append(" limit ?,?");
	    
	    
        Integer totalCount = collectionMetadataDao.selectCountByCondition(countSql.toString(), queryParams);
        
	    int pageCount = 0;
        if (totalCount % pager.getPageSize() == 0) {
            pageCount = totalCount / pager.getPageSize();
        } else {
            pageCount = totalCount / pager.getPageSize() + 1;
        }
        
        int index = (pager.getCurrentPage() - 1) * pager.getPageSize();
        queryParams.add(index);
        queryParams.add(pager.getPageSize());
        
        List<CollectionMetadataEntity> list = collectionMetadataDao.selectByCondition(querySql.toString(), queryParams);
        
        pager.setStartRow(index);
        pager.setDatas(list);
        pager.setTotalRecord(totalCount);
        pager.setTotalPage(pageCount);
	    
		
		return pager;
	}


	@Override
	@Transactional
	public void addCollectionMetadata(Integer seqNo,
			List<CollectionEntity> collectionList) {
		//插入collectionMetadata并返回主键
		CollectionMetadataEntity collectionMetadata = new CollectionMetadataEntity();
		collectionMetadata.setSeqNo(seqNo);
		Integer collectionMetadataId = collectionMetadataDao.insert(collectionMetadata);
		
		sysLogDao.insertLogForInsert(collectionMetadataId, "COLLECTION_METADATA");
		
		//插入collection
		for (CollectionEntity collection : collectionList) {
			collection.setCollectionMetadataId(collectionMetadataId);
			collection.setIsEnable(true);
			Integer collectionId = collectionDao.insert(collection);
			
			sysLogDao.insertLogForInsert(collectionId, "COLLECTION");
		}
		
	}


	@Override
	public CollectionMetadataEntity getCollectionMetadataById(
			CollectionMetadataEntity collectionMetadata) {
		List<CollectionMetadataEntity> collectionMetadatas = collectionMetadataDao.selectByCondition(collectionMetadata);
		if(collectionMetadatas == null || collectionMetadatas.size() == 0){
			return null;
		}
		return collectionMetadatas.get(0);
	}


	@Override
	public void editCollectionMetadata(Integer collectionMetadataId, Integer seqNo,
			List<CollectionEntity> collectionList) {
		//更新collectionMetadata
		CollectionMetadataEntity collectionMetadata = new CollectionMetadataEntity();
		collectionMetadata.setSeqNo(seqNo);
		collectionMetadata.setIsEnable(true);
		collectionMetadata.setCollectionMetadataId(collectionMetadataId);
		collectionMetadataDao.update(collectionMetadata);
		
		sysLogDao.insertLogForUpdate(collectionMetadataId, "COLLECTION_METADATA");
		
		//更新collection
		for (CollectionEntity collection : collectionList) {
			collection.setCollectionMetadataId(collectionMetadataId);
			collection.setIsEnable(true);
			collectionDao.update(collection);
			
			sysLogDao.insertLogForUpdate(collection.getCollectionId(), "COLLECTION");
		}
		
	}


	@Override
	public void deleteCollectionMetadata(String collectionMetadataIds) {
		
		String[] ids = collectionMetadataIds.split(",");
		for (String collectionMetadataId : ids) {
			CollectionMetadataEntity collectionMetadata = collectionMetadataDao.selectById(collectionMetadataId);
			collectionMetadata.setIsEnable(false);
			collectionMetadataDao.update(collectionMetadata);
			
			sysLogDao.insertLogForDelete(Integer.valueOf(collectionMetadataId), "COLLECTION_METADATA");
			
			CollectionEntity collection = new CollectionEntity();
			collection.setCollectionMetadataId(Integer.valueOf(collectionMetadataId));
			List<CollectionEntity> collections = collectionDao.selectByCondition(collection);
			
			for (CollectionEntity temp : collections) {
				temp.setIsEnable(false);
				collectionDao.update(temp);
				sysLogDao.insertLogForDelete(collection.getCollectionId(), "COLLECTION");
			}
		}
	}


    /**
     * {@inheritDoc}
     */
    @Override
    public Pager<Map<String,Object>> getCollectionsByMap(Pager<Map<String,Object>> pager,
            CollectionMetadataPojo pojo) {
        List<Object> queryParams = new ArrayList<Object>();
        StringBuilder querySql = new StringBuilder(" select collectionMetadata.COLLECTION_METADATA_ID as collectionMetadataId,"
        		+ " collectionMetadata.CREATE_TIME as createTime,collectionMetadata.CREATE_USER as createUser,collectionMetadata.SEQ_NO as seqNo,"
        		+ " collection.COLLECTION_NAME as collectionName,collectionMetadata.MODIFY_TIME  from COLLECTION_METADATA collectionMetadata "
                + " inner join COLLECTION collection on collectionMetadata.COLLECTION_METADATA_ID = collection.COLLECTION_METADATA_ID ");
        StringBuilder countSql = new StringBuilder(" select count(*) from COLLECTION_METADATA collectionMetadata "
                + " inner join COLLECTION collection on collectionMetadata.COLLECTION_METADATA_ID = collection.COLLECTION_METADATA_ID ");
        
        querySql.append(" where collectionMetadata.IS_ENABLE = 1 and collection.IS_ENABLE = 1 and collection.LANG="+ CommonConstants.LOCALE_CN +" ");
        countSql.append(" where collectionMetadata.IS_ENABLE = 1 and collection.IS_ENABLE = 1 and collection.LANG="+ CommonConstants.LOCALE_CN +" ");
        
        String collectionName = pojo.getCollectionName();
        if(collectionName !=null){
            querySql.append(" and collection.COLLECTION_NAME like concat(concat('%',?),'%') ");
            countSql.append(" and collection.COLLECTION_NAME like concat(concat('%',?),'%') ");
            queryParams.add(collectionName.toString());
        }
        querySql.append(" ORDER BY collectionMetadata.SEQ_NO, collectionMetadata.MODIFY_TIME DESC limit ?,?");
        
        
        Integer totalCount = collectionMetadataDao.selectCountByCondition(countSql.toString(), queryParams);
        
        int pageCount = 0;
        if (totalCount % pager.getPageSize() == 0) {
            pageCount = totalCount / pager.getPageSize();
        } else {
            pageCount = totalCount / pager.getPageSize() + 1;
        }
        	
        int index = (pager.getCurrentPage() - 1) * pager.getPageSize();
        queryParams.add(index);
        queryParams.add(pager.getPageSize());
        
        List<Map<String, Object>> list = collectionMetadataDao.selectByConditionWithMap(querySql.toString(), queryParams);
        
        pager.setStartRow(index);
        pager.setDatas(list);
        pager.setTotalRecord(totalCount);
        pager.setTotalPage(pageCount);
        
        return pager;
    }
}
