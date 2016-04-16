/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service;

import java.io.IOException;
import java.util.Map;

import com.kohler.bean.ConfPrepareData;

/**
 * 发布html
 *
 * @author Administrator
 * @Date 2014年10月23日
 */
public interface PublishHtmlService {
    
    /**
     * 运行html发布
     * @param dataType
     * @param confPrepareData
     * @return
     * @author Administrator
     * Date 2014年10月23日
     * @version
     * @throws IOException 
     */
    public Map<String,Object> run(ConfPrepareData confPrepareData) throws Exception;
}
