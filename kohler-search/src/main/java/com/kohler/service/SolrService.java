/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.service;


/**
 * solr service
 * 
 * @author zqq
 * @Date 10/9/2014
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
    public void importDeltaIndex() throws Exception;
    
    /**
     * import all data from database to the solr
     * @return
     * @author zhangqiqi Date 10/17/2014
     * @version
     */
    public void importContentAllIndex() throws Exception;
    
    /**
     * import delta data from database to the solr
     * @return
     * @author zhangqiqi Date 10/17/2014
     * @version
     */
    public void importContentDeltaIndex() throws Exception;
}
