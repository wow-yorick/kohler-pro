/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.dao;

import java.util.List;
import java.util.Map;

import com.kohler.entity.SectionEntity;

/**
 * SectionDao Interface
 *
 * @author shefeng
 * @Date 2014年9月25日
 */
public interface SectionDao extends BaseDao<SectionEntity>{

    public List<Map<String, Object>> selectSections();

    /**
     * @param sectionName
     * @return
     * @author Sweety
     * Date 2014年10月13日
     * @version
     */
    public List<Map<String, Object>> selectSectionByName(String sectionName);

    
    
    /**
     * @param sectionName
     * @param orgSectionName
     * @return
     * @author Sweety
     * Date 2014年10月14日
     * @version
     */
    public List<Map<String, Object>> selectSectionByNameExceptSelf(String sectionName,
            String orgSectionName);

}
