/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.dao;

import java.util.List;
import java.util.Map;

import com.kohler.entity.ContentMetadataEntity;

/**
 * ContentDao Interface
 *
 * @author WHH
 * @Date 2014年10月28日
 */
public interface ContentDao extends BaseDao<ContentMetadataEntity>{

    public List<Map<String, Object>> selectContents();

    public List<Map<String, Object>> getContentMetadataList(Integer datatypeId,
            Map<String, String> selMap);

    public List<Map<String, Object>> getSelectContentMetadataList(String selectsql,
            Integer datatypeId);

    public void deleteByMetadataId(Integer contentMetadataId);

   
}
