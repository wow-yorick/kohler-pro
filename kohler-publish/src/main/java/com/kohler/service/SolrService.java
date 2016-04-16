/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service;

/**
 * Class Function Description
 *
 * @author Administrator
 * @Date 2014年12月30日
 */
public interface SolrService {
    /**
     * import all data from database to the solr
     * @return
     * @author zhangqiqi Date 10/17/2014
     * @version
     */
    public void importAllIndex() throws Exception;

    /**
     * import delta data from database to the solr
     * @return
     * @author zhangqiqi Date 10/17/2014
     * @version
     */
    public void importDeltaIndex(String timestamp) throws Exception;
}
