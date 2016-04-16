/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.dao;

import java.util.List;
import java.util.Map;

import com.kohler.entity.CategoryEntity;
import com.kohler.entity.extend.CategoryMetadataPojo;

/**
 * SectionDao Interface
 *
 * @author XHY
 * @Date 2014年10月9日
 */
public interface CategoryDao extends BaseDao<CategoryEntity>{

    public List<Map<String, Object>> selectCategorys();
    
    public CategoryEntity getCategoryByCategoryMetadataId(Integer categoryMetadataId);

	/**
	 * @param categoryMetadataId
	 * @return
	 * @author XHY
	 * Date 2014年10月11日
	 * @version
	 */
	public CategoryMetadataPojo findCategoryMetadataById(
			Integer categoryMetadataId);

	/**
	 * @param categoryMetadataId
	 * @return
	 * @author XHY
	 * Date 2014年10月15日
	 * @version
	 */
	public List<CategoryEntity> getCategorysByCategoryMetadataId(
			Integer categoryMetadataId);

}
