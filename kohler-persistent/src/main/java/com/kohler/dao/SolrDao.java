/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;

/**
 * solr dao
 * 
 * @author zqq
 * @Date 10/9/2014
 */
public interface SolrDao {

    public List<Map<String, Object>> selectAll(String str, List list);

    public Integer update(String str);
}
