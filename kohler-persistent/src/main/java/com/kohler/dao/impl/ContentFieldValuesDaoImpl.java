package com.kohler.dao.impl;

import org.springframework.stereotype.Repository;

import com.kohler.constants.ContentSQL;
import com.kohler.dao.ContentFieldValuesDao;
import com.kohler.entity.ContentFieldValues;

@Repository
public class ContentFieldValuesDaoImpl extends BaseDaoImpl<ContentFieldValues> implements ContentFieldValuesDao{

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteByMetadataId(Integer contentMetadataId) {
        jdbcTemplate.update(ContentSQL.DELETE_CONTENTFIELDVALUES_BY_METADATA_ID, contentMetadataId);
    }

	
}