/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kohler.constants.ContentSQL;
import com.kohler.dao.ContentDao;
import com.kohler.entity.ContentMetadataEntity;

/**
 * Class Function Description
 *
 * @author WHH
 * @Date 2014年10月29日
 */
@Repository
public class ContentDaoImpl extends BaseDaoImpl<ContentMetadataEntity> implements ContentDao {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Map<String, Object>> selectContents() {
        return jdbcTemplate.queryForList(ContentSQL.SELECT_DATATYPES_WITH_MAP);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Map<String, Object>> getContentMetadataList(Integer datatypeId,
            Map<String, String> selMap) {
        List<Map<String, Object>> list =  jdbcTemplate.queryForList(ContentSQL.SELECT_CONTENT_VALUE_BY_ID,new Object[]{datatypeId});
        return list;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Map<String, Object>> getSelectContentMetadataList(String selectsql,
            Integer datatypeId) {
        // TODO Auto-generated method stub
        return jdbcTemplate.queryForList(selectsql,new Object[]{datatypeId});
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteByMetadataId(Integer contentMetadataId) {
        jdbcTemplate.update(ContentSQL.DELETE_CONTENTMETADATA_BY_METADATA_ID, contentMetadataId);
    }

    

}
