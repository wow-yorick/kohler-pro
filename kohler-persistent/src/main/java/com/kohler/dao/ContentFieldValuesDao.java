/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.dao;

import com.kohler.entity.ContentFieldValues;

/**
 * Content Metadata Dao 
 *
 * @author kangmin_Qu
 * @Date 2014-10-15
 */
public interface ContentFieldValuesDao extends BaseDao<ContentFieldValues>{

    void deleteByMetadataId(Integer contentMetadataId);

}
