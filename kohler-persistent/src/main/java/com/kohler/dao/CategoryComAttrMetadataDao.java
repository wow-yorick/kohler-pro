/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.dao;

import java.util.List;

import com.kohler.entity.CategoryComAttrMetadataEntity;
import com.kohler.entity.extend.CategoryComAttrMetadataPojo;

/**
 * Class Function Description
 * CategoryComAttrMetadataDao
 * @author zhangtingting
 * @Date 2014年9月25日
 */
public interface CategoryComAttrMetadataDao extends BaseDao<CategoryComAttrMetadataEntity> {

	/**
	 * @param categoryMetadataId
	 * @return
	 * @author XHY
	 * Date 2014年10月28日
	 * @version
	 */
	List<CategoryComAttrMetadataPojo> getCateComAttrByCId(Integer categoryMetadataId);

	/**
	 * @param categoryComAttrMetadataId
	 * @return
	 * @author XHY
	 * Date 2014年10月30日
	 * @version
	 */
	CategoryComAttrMetadataPojo getCateComAttrById(
			Integer categoryComAttrMetadataId);

	/**
	 * @return
	 * @author Dragon
	 * Date 2014年11月14日
	 * @version
	 */
	List<String> getDropDownType();

}
