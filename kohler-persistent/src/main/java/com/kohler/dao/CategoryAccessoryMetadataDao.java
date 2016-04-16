/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.dao;

import java.util.List;

import com.kohler.entity.CategoryAccessoryMetadataEntity;
import com.kohler.entity.extend.CategoryAccessoryMetadataPojo;

/**
 * 
 * Class Function Description
 *
 * @author XHY
 * @Date 2014年11月10日
 */
public interface CategoryAccessoryMetadataDao extends BaseDao<CategoryAccessoryMetadataEntity>{

	/**
	 * @param categoryAccessoryMetadataId
	 * @return
	 * @author XHY
	 * Date 2014年11月11日
	 * @version
	 */
	CategoryAccessoryMetadataPojo getAccessoryPojoById(
			Integer categoryAccessoryMetadataId);

	/**
	 * @param categoryMetadataId
	 * @return
	 * @author XHY
	 * Date 2014年11月13日
	 * @version
	 */
	List<CategoryAccessoryMetadataPojo> getCateAccessoryByCId(
			Integer categoryMetadataId);


    
}
