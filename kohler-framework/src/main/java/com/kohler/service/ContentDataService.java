/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service;

import java.util.List;
import java.util.Map;

/**
 * Class Function Description
 *
 * @author XHY
 * @Date 2014年12月1日
 */
public interface ContentDataService {
	
	
	/**
	 * 
	 * @return
	 * @author XHY
	 * Date 2014年11月28日
	 * @version
	 */
	public Map<String,Object> backupData();

	/**
	 * 
	 * @param backupSuffix
	 * @return
	 * @author XHY
	 * Date 2014年12月1日
	 * @version
	 */
	public boolean restoreData(String backupSuffix);

	/**
	 * @param type
	 * @param lang
	 * @return
	 * @author XHY
	 * Date 2014年12月1日
	 * @version
	 */
	List<Map<String, Object>> getAllType(Integer type, Integer lang);
	
}
