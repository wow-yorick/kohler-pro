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
 * @Date 2014年12月26日
 */
public interface FamousProjectService {
    /**
     * import all data from database to the solr
     * @throws Exception
     * @author sana
     * Date 2014年12月25日
     * @version
     * @param lang 
     */
    public void importAllIndex(Integer lang) throws Exception;

    /**
     * import delta data from database to the solr
     * @throws Exception
     * @author sana
     * Date 2014年12月25日
     * @version
     */
    public void importDeltaIndex() throws Exception;

}
