package com.kohler.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kohler.dao.CollectionDao;
import com.kohler.dao.CollectionMetadataDao;
import com.kohler.dao.SysLogDao;
import com.kohler.dao.utils.Pager;
import com.kohler.entity.Collection;
import com.kohler.entity.CollectionMetadata;
import com.kohler.entity.SysLog;
import com.kohler.entity.SysUser;
import com.kohler.entity.extend.CollectionMetadataPojo;
import com.kohler.service.CollectionMetadataService;
import com.kohler.util.SysContent;

@Service
public class CollectionMetadataServiceImpl implements CollectionMetadataService {

	@Autowired 
	public CollectionMetadataDao collectionMetadataDao;
	
	@Autowired 
	public CollectionDao collectionDao;
	
	@Autowired 
	public SysLogDao sysLogDao;


	@Override
	public Pager<CollectionMetadata> getCollectionsByMap(Pager<CollectionMetadata> pager,
			Map<String, Object> cod) {
	    List<Object> queryParams = new ArrayList<Object>();
	    StringBuilder querySql = new StringBuilder(" select * from collection_metadata collectionMetadata "
                + " inner join collection collection on collectionMetadata.collection_metadata_id = collection.collection_metadata_id "
                + " inner join locale locale on collection.lang = locale.locale_id ");
        StringBuilder countSql = new StringBuilder(" select count(*) from collection_metadata collectionMetadata "
                + " inner join collection collection on collectionMetadata.collection_metadata_id = collection.collection_metadata_id "
                + " inner join locale locale on collection.lang = locale.locale_id ");
        
        querySql.append(" where collectionMetadata.is_enable = 1 and collection.is_enable = 1 and locale.is_enable = 1 and locale.is_default = 1 ");
        countSql.append(" where collectionMetadata.is_enable = 1 and collection.is_enable = 1 and locale.is_enable = 1 and locale.is_default = 1 ");
        
        Object collectionName = cod.get("collectionName");
        if(collectionName !=null){
            querySql.append(" and collection.collection_name like concat(concat('%',?),'%') ");
            countSql.append(" and collection.collection_name like concat(concat('%',?),'%') ");
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
        
        List<CollectionMetadata> list = collectionMetadataDao.selectByCondition(querySql.toString(), queryParams);
        
        pager.setStartRow(index);
        pager.setDatas(list);
        pager.setTotalRecord(totalCount);
        pager.setTotalPage(pageCount);
	    
		
		return pager;
	}


	@Override
	@Transactional
	public void addCollectionMetadata(Integer seqNo,
			List<Collection> collectionList) {
		//插入collectionMetadata并返回主键
		CollectionMetadata collectionMetadata = new CollectionMetadata();
		collectionMetadata.setSeqNo(seqNo);
		Integer collectionMetadataId = collectionMetadataDao.insert(collectionMetadata);
		
		//插入collectionMetadata的日志
		SysLog sysLog = new SysLog();
		sysLog.setIsEnable(true);
		sysLog.setObjectId(collectionMetadataId);
		sysLog.setOperation("新增");
		sysLog.setOperationObject("CollectionMetadata");
		sysLog.setSysUserId(((SysUser) SysContent.getSession().getAttribute("sysUser")).getSysUserId());
		sysLogDao.insert(sysLog);
		//插入collection
		for (Collection collection : collectionList) {
			collection.setCollectionMetadataId(collectionMetadataId);
			collection.setIsEnable(true);
			Integer collectionId = collectionDao.insert(collection);
			//插入collection的日志
			sysLog = new SysLog();
			sysLog.setIsEnable(true);
			sysLog.setObjectId(collectionId);
			sysLog.setOperation("新增");
			sysLog.setOperationObject("Collection");
			sysLog.setSysUserId(((SysUser) SysContent.getSession().getAttribute("sysUser")).getSysUserId());
			sysLogDao.insert(sysLog);
		}
		
	}


	@Override
	public CollectionMetadata getCollectionMetadataById(
			CollectionMetadata collectionMetadata) {
		List<CollectionMetadata> collectionMetadatas = collectionMetadataDao.selectByCondition(collectionMetadata);
		if(collectionMetadatas == null || collectionMetadatas.size() == 0){
			return null;
		}
		return collectionMetadatas.get(0);
	}


	@Override
	public void editCollectionMetadata(Integer collectionMetadataId, Integer seqNo,
			List<Collection> collectionList) {
		//更新collectionMetadata
		CollectionMetadata collectionMetadata = new CollectionMetadata();
		collectionMetadata.setSeqNo(seqNo);
		collectionMetadata.setCollectionMetadataId(collectionMetadataId);
		collectionMetadataDao.update(collectionMetadata);
		
		//插入collectionMetadata的日志
		SysLog sysLog = new SysLog();
		sysLog.setIsEnable(true);
		sysLog.setObjectId(collectionMetadataId);
		sysLog.setOperation("修改");
		sysLog.setOperationObject("CollectionMetadata");
		sysLog.setSysUserId(((SysUser) SysContent.getSession().getAttribute("sysUser")).getSysUserId());
		sysLogDao.insert(sysLog);
		
		//更新collection
		for (Collection collection : collectionList) {
			collection.setCollectionMetadataId(collectionMetadataId);
			collection.setIsEnable(true);
			collectionDao.update(collection);
			//插入collection的日志
			sysLog = new SysLog();
			sysLog.setIsEnable(true);
			sysLog.setObjectId(collection.getCollectionId());
			sysLog.setOperation("修改");
			sysLog.setOperationObject("Collection");
			sysLog.setSysUserId(((SysUser) SysContent.getSession().getAttribute("sysUser")).getSysUserId());
			sysLogDao.insert(sysLog);
		}
		
	}


	@Override
	public void deleteCollectionMetadata(String collectionMetadataIds) {
		String[] ids = collectionMetadataIds.split(",");
		for (String collectionMetadataId : ids) {
			CollectionMetadata collectionMetadata = collectionMetadataDao.selectById(collectionMetadataId);
			collectionMetadata.setIsEnable(false);
			collectionMetadataDao.update(collectionMetadata);
			
			SysLog sysLog = new SysLog();
			sysLog.setIsEnable(true);
			sysLog.setObjectId(Integer.valueOf(collectionMetadataId));
			sysLog.setOperation("删除");
			sysLog.setOperationObject("Collection");
			sysLog.setSysUserId(((SysUser) SysContent.getSession().getAttribute("sysUser")).getSysUserId());
			sysLogDao.insert(sysLog);
			
			Collection collection = new Collection();
			collection.setCollectionMetadataId(Integer.valueOf(collectionMetadataId));
			List<Collection> collections = collectionDao.selectByCondition(collection);
			
			for (Collection temp : collections) {
				temp.setIsEnable(false);
				collectionDao.update(temp);
				sysLog = new SysLog();
				sysLog.setIsEnable(true);
				sysLog.setObjectId(collection.getCollectionId());
				sysLog.setOperation("删除");
				sysLog.setOperationObject("Collection");
				sysLog.setSysUserId(((SysUser) SysContent.getSession().getAttribute("sysUser")).getSysUserId());
				
				sysLogDao.insert(sysLog);
			}
		}
	}


    /**
     * {@inheritDoc}
     */
    @Override
    public Pager<CollectionMetadata> getCollectionsByMap(Pager<CollectionMetadata> pager,
            CollectionMetadataPojo pojo) {
        List<Object> queryParams = new ArrayList<Object>();
        StringBuilder querySql = new StringBuilder(" select * from collection_metadata collectionMetadata "
                + " inner join collection collection on collectionMetadata.collection_metadata_id = collection.collection_metadata_id "
                + " inner join locale locale on collection.lang = locale.locale_id ");
        StringBuilder countSql = new StringBuilder(" select count(*) from collection_metadata collectionMetadata "
                + " inner join collection collection on collectionMetadata.collection_metadata_id = collection.collection_metadata_id "
                + " inner join locale locale on collection.lang = locale.locale_id ");
        
        querySql.append(" where collectionMetadata.is_enable = 1 and collection.is_enable = 1 and locale.is_enable = 1 and locale.is_default = 1 ");
        countSql.append(" where collectionMetadata.is_enable = 1 and collection.is_enable = 1 and locale.is_enable = 1 and locale.is_default = 1 ");
        
        String collectionName = pojo.getCollectionName();
        if(collectionName !=null){
            querySql.append(" and collection.collection_name like concat(concat('%',?),'%') ");
            countSql.append(" and collection.collection_name like concat(concat('%',?),'%') ");
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
        
        List<CollectionMetadata> list = collectionMetadataDao.selectByCondition(querySql.toString(), queryParams);
        
        pager.setStartRow(index);
        pager.setDatas(list);
        pager.setTotalRecord(totalCount);
        pager.setTotalPage(pageCount);
        
        return pager;
    }
}
