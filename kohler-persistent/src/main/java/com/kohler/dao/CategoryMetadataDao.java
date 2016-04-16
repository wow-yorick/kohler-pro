/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.dao;

import java.util.List;
import java.util.Map;

import com.kohler.entity.CategoryMetadataEntity;
import com.kohler.entity.extend.CategoryMetadataPojo;

/**
 * CategoryMetadataDao Interface
 *
 * @author XHY
 * @Date 2014年10月9日
 */
public interface CategoryMetadataDao extends BaseDao<CategoryMetadataEntity>{

    public List<Map<String, Object>> selectCategorys();
    
   

	/**
	 * @param categoryMetadataId
	 * @return
	 * @author Dragon
	 * Date 2014年10月11日
	 * @version
	 */
	public CategoryMetadataEntity findCategoryMetadataById(
			Integer categoryMetadataId);



	/**
	 * @param categoryMetadataId
	 * @return
	 * @author XHY
	 * Date 2014年11月13日
	 * @version
	 */
	public List<CategoryMetadataPojo> findByPId(Integer categoryMetadataId);



	/**
	 * @param masterData
	 * @return
	 * @author Dragon
	 * Date 2014年11月15日
	 * @version
	 */
	public List<Map<String, Object>> getFiledName(List<Object> masterData);

}
