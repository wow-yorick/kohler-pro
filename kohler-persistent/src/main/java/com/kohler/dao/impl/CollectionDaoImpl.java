package com.kohler.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kohler.dao.CollectionDao;
import com.kohler.dao.utils.DefaultNameHandler;
import com.kohler.dao.utils.DefaultRowMapper;
import com.kohler.entity.CollectionEntity;

@Repository
public class CollectionDaoImpl extends BaseDaoImpl<CollectionEntity> implements CollectionDao {
	
	/** spring jdbcTemplate 对象 */
    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @SuppressWarnings("rawtypes")
	@Override
	public CollectionEntity getCollectionById(Integer id) {
		List<Object> queryParams = new ArrayList<Object>();
		queryParams.add(id);
		
        StringBuilder querySql = new StringBuilder(" select * from COLLECTION collection "
        		+ " inner join LOCALE locale on collection.LANG = locale.LOCALE_ID ");
        
        querySql.append(" where collection.IS_ENABLE = 1 and locale.IS_ENABLE = 1 and locale.IS_DEFAULT = 1 ");
        querySql.append(" and  collection.COLLECTION_METADATA_ID = ?");
        
        List list =jdbcTemplate.query(querySql.toString(), queryParams.toArray(),
                new DefaultRowMapper(CollectionEntity.class, new DefaultNameHandler()));
        if(list.size()>0){
            return (CollectionEntity) list.get(0);
        }
        return null;
	}

	/**
     * {@inheritDoc}
     */
    @Override
    public List<CollectionEntity> getCollectionsByDefault() {
        StringBuffer sql=new StringBuffer("select c.* from COLLECTION c left join LOCALE l on c.LANG=l.LOCALE_ID ");
        sql.append("where c.IS_ENABLE=1 and l.IS_ENABLE=1 and l.IS_DEFAULT=1");
        
        return (List)jdbcTemplate.query(sql.toString(), new DefaultRowMapper(CollectionEntity.class, new DefaultNameHandler()));
    }
}
