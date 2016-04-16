package com.kohler.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kohler.dao.CollectionMetadataDao;
import com.kohler.dao.utils.DefaultNameHandler;
import com.kohler.dao.utils.DefaultRowMapper;
import com.kohler.dao.utils.Pager;
import com.kohler.entity.CollectionMetadataEntity;

@Repository
public class CollectionMetadataDaoImpl extends BaseDaoImpl<CollectionMetadataEntity> implements CollectionMetadataDao {
	
    @Autowired
    protected JdbcTemplate jdbcTemplate;
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Pager<CollectionMetadataEntity> queryForList(Pager<CollectionMetadataEntity> pager,
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

        int totalCount = jdbcTemplate.queryForInt(countSql.toString(), queryParams.toArray());
        
        int index = (pager.getCurrentPage() - 1) * pager.getPageSize();
        queryParams.add(index);
        queryParams.add(pager.getPageSize());
        
        List list =jdbcTemplate.query(querySql.toString(), queryParams.toArray(),
                new DefaultRowMapper(CollectionMetadataEntity.class, new DefaultNameHandler()));
        
        int pageCount = 0;
        if(totalCount%pager.getPageSize()==0){
        	pageCount=totalCount/pager.getPageSize();
    	}else{
        	pageCount=totalCount/pager.getPageSize()+1;
    	}
        pager.setStartRow(index);
        pager.setDatas(list);
        pager.setTotalRecord(totalCount);
        pager.setTotalPage(pageCount);
        return pager;
	}

}
