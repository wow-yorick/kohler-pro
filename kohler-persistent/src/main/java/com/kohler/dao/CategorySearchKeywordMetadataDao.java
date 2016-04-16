/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.dao;

import java.util.List;

import com.kohler.entity.CategorySearchKeywordMetadataEntity;
import com.kohler.entity.extend.CategorySearchKeywordMetadataPojo;

/**
 * 
 * Class Function Description
 *
 * @author XHY
 * @Date 2014年11月10日
 */
public interface CategorySearchKeywordMetadataDao extends BaseDao<CategorySearchKeywordMetadataEntity>{

	/**
	 * @param categorySearchKeyMetadataId
	 * @return
	 * @author XHY
	 * Date 2014年11月10日
	 * @version
	 */
	CategorySearchKeywordMetadataPojo getSkuAttrById(
			Integer categorySearchKeyMetadataId);

	/**
	 * @param categoryMetadataId
	 * @return
	 * @author XHY
	 * Date 2014年11月13日
	 * @version
	 */
	List<CategorySearchKeywordMetadataPojo> getCateSearchKeyByCId(
			Integer categoryMetadataId);
    
}
