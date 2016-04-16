/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.dao;

import java.util.List;

import com.kohler.entity.CategoryAccessoryEntity;

/**
 * 
 * Class Function Description
 *
 * @author XHY
 * @Date 2014年11月10日
 */
public interface CategoryAccessoryDao extends BaseDao<CategoryAccessoryEntity>{
        
    public List<CategoryAccessoryEntity> getCategoryAccessoryListByCategoryMetadataId(String categoryMetadataId, String lang);
    
    public List<CategoryAccessoryEntity> getCategoryAccessoryListByAccessoryMetadataId(String categoryAccessoryMetadataId, String lang);
    
}
