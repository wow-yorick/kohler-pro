/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service;

import java.util.Map;


/**
 * data cache
 *
 * @author Administrator
 * @Date 2015年1月8日
 */
public interface DataCacheService {
    
    /**
     * hash set
     * @param key
     * @param field
     * @return
     * @author Administrator
     * Date 2015年1月8日
     * @version
     */
    public String hget(String key, String field);
    
    /**
     * hash get
     * @param key
     * @param field
     * @param value
     * @author Administrator
     * Date 2015年1月8日
     * @version
     */
    public void hset(String key, String field, String value);
    
    /**
     * hash delete all
     * @param key
     * @return
     * @author Administrator
     * Date 2015年1月8日
     * @version
     */
    public boolean hdelAll(String key);
    
    /**
     * hash get all
     * @param key
     * @return
     * @author Administrator
     * Date 2015年1月8日
     * @version
     */
    public Map<String, String> hgetAll(String key);
    
    
}
