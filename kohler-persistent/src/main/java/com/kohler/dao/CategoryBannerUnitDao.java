/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.dao;

import com.kohler.entity.CategoryBannerUnitEntity;
import com.kohler.entity.extend.CategoryBannerUnitPojo;

/**
 * 
 * Class Function Description
 *
 * @author XHY
 * @Date 2014年11月10日
 */
public interface CategoryBannerUnitDao extends BaseDao<CategoryBannerUnitEntity>{

	/**
	 * @param bannerUnitId
	 * @return
	 * @author XHY
	 * Date 2014年11月12日
	 * @version
	 */
	CategoryBannerUnitPojo getCateBannerById(Integer bannerUnitId);
    
}
