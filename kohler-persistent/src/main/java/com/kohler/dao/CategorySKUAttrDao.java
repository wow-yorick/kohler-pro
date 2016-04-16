/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.dao;

import java.util.List;

import com.kohler.entity.CategorySKUAttrEntity;

/**
 * Class Function Description
 * CategorySKUAttrDao
 * @author zhangtingting
 * @Date 2014年9月29日
 */
public interface CategorySKUAttrDao extends BaseDao<CategorySKUAttrEntity>{
    
    public List<CategorySKUAttrEntity> getCategorySKUAttrListByCategoryMetadataId(Integer categoryMetadataId);

}
