/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.dao;

import java.util.List;
import java.util.Map;

import com.kohler.entity.PageFieldValuesEntity;


/**
 * Class Function Description
 *
 * @author Administrator
 * @Date 2014年10月23日
 */
public interface PageFieldValuesDao extends BaseDao<PageFieldValuesEntity> {
    public List<Map<String,Object>> searchByFieldname(String fieldname);
    
    public List<Map<String,Object>> searchByTime(String fieldname,String time);
}
