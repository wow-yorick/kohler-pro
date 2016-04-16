package com.kohler.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kohler.constants.PublishLogSQL;
import com.kohler.dao.PublishLogDao;
import com.kohler.dao.utils.DefaultNameHandler;
import com.kohler.dao.utils.DefaultRowMapper;
import com.kohler.dao.utils.Pager;
import com.kohler.entity.CollectionMetadataEntity;
import com.kohler.entity.PublishLogEntity;

@Repository
public class PublishLogDaoImpl extends BaseDaoImpl<PublishLogEntity> implements PublishLogDao {
	@Autowired
    protected JdbcTemplate jdbcTemplate;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Pager<PublishLogEntity> queryForList(Pager<PublishLogEntity> pager,
			Map<String, Object> cod) {
		List<Object> queryParams = new ArrayList<Object>();
		StringBuilder querySql = new StringBuilder(" select publishLog.*,masterData.MASTERDATA_NAME as statusName from PUBLISH_LOG publishLog "
                + " inner join MASTERDATA masterData on publishLog.PUBLISH_STATUS=masterData.MASTERDATA_METADATA_ID ");
        
		StringBuilder countSql = new StringBuilder(" select count(*) from PUBLISH_LOG publishLog "
                + " inner join MASTERDATA masterData on publishLog.PUBLISH_STATUS=masterData.MASTERDATA_METADATA_ID ");
        
		querySql.append(" where publishLog.IS_ENABLE=1 and masterData.IS_ENABLE=1 and masterData.LANG=1");
		countSql.append(" where publishLog.IS_ENABLE=1 and masterData.IS_ENABLE=1 and masterData.LANG=1");
        
        Object beginDate = cod.get("beginDate");
        if(beginDate !=null){
        	querySql.append(" and publishLog.PUBLISH_TIME >= to_date(?,'YYYY-MM-DD') ");
        	countSql.append(" and publishLog.PUBLISH_TIME >= to_date(?,'YYYY-MM-DD') ");
        	queryParams.add(beginDate.toString());
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
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void executeSql(String sql) {
		jdbcTemplate.execute(sql);	
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public PublishLogEntity getCheckPublish(List<Object> params) {
	        
	        String sql = PublishLogSQL.CHECK_PUBLISH_WITH_MAP;
	        
	        Object object =jdbcTemplate.queryForObject(sql, params.toArray(),
	                new DefaultRowMapper(PublishLogEntity.class, new DefaultNameHandler()));
	        
	        return (PublishLogEntity) object;
		
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public PublishLogEntity getOpenPublish(List<Object> params) {
		  String sql = PublishLogSQL.GET_OLD_PUBLISH_WITH_MAP;
	        
	        Object object =jdbcTemplate.queryForObject(sql, params.toArray(),
	                new DefaultRowMapper(PublishLogEntity.class, new DefaultNameHandler()));
	        
	        return (PublishLogEntity) object;
	}

}
