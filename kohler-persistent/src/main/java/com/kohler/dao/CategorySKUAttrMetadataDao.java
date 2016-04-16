/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.dao;

import java.util.List;

import com.kohler.entity.CategorySKUAttrMetadataEntity;
import com.kohler.entity.extend.CategorySKUAttrMetadataPojo;

/**
 * Class Function Description
 * CategorySKUAttrMetadataDao
 * @author zhangtingting
 * @Date 2014年9月29日
 */
public interface CategorySKUAttrMetadataDao extends BaseDao<CategorySKUAttrMetadataEntity>{
	
	/**
	 * @param categorySKUAttrMetadataId
	 * @return
	 * @author XHY
	 * Date 2014年11月7日
	 * @version
	 */
	CategorySKUAttrMetadataPojo getSkuAttrById(Integer categorySKUAttrMetadataId);

	/**
	 * @param categoryMetadataId
	 * @return
	 * @author XHY
	 * Date 2014年11月13日
	 * @version
	 */
	List<CategorySKUAttrMetadataPojo> getCateSkuAttrByCId(
			Integer categoryMetadataId);
}
