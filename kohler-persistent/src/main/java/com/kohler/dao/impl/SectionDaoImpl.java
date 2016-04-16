/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kohler.constants.SectionSQL;
import com.kohler.dao.SectionDao;
import com.kohler.entity.SectionEntity;

/**
 * Class Function Description
 *
 * @author shefeng
 * @Date 2014年9月25日
 */
@Repository
public class SectionDaoImpl extends BaseDaoImpl<SectionEntity> implements SectionDao {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Map<String, Object>> selectSections() {
        return jdbcTemplate.queryForList(SectionSQL.SELECT_SECTIONS_WITH_MAP);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Map<String, Object>> selectSectionByName(String sectionName) {
        return jdbcTemplate.queryForList(SectionSQL.SELECT_SECTION_BY_NAME , new Object[]{sectionName});
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Map<String, Object>> selectSectionByNameExceptSelf(String sectionName,
            String orgSectionName) {
        return jdbcTemplate.queryForList(SectionSQL.SELECT_SECTION_BY_NAME_EXCEPT_SELF , new Object[]{sectionName , orgSectionName});
    }

}
