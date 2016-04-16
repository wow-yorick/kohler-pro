/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service;

/**
 * Class Function Description
 *
 * @author sana
 * @Date 2014年12月30日
 */
public interface PDFSearchSolrService {
    
    /**
     * import all data from database to the solr
     * @throws Exception
     * @author sana
     * Date 2014年12月25日
     * @version
     * @param lang 
     */
    public void importAllIndex(Integer lang) throws Exception;
}
