/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.dao;

import java.util.List;
import com.kohler.entity.CategorySKUAttrValuesEntity;

/**
 * Class Function Description
 * CategorySKUAttrValuesDao
 * @author zhangtingting
 * @Date 2014年9月29日
 */
public interface CategorySKUAttrValuesDao extends BaseDao<CategorySKUAttrValuesEntity>{

    public List<CategorySKUAttrValuesEntity> getSKUAttrValuesListBySKUAttrMetadataId(Integer categorySKUAttrMetadataId);
    
    public List<CategorySKUAttrValuesEntity> getSKUAttrValuesListBySKUMetadataId(Integer skuMetadataId);
}
