/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.publish;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohler.bean.ConfPrepareData;
import com.kohler.exception.DataException;
import com.kohler.service.PublishHtmlService;
import com.kohler.service.impl.StaticResourceCopyServiceImpl;

/**
 * 发布功能入口
 *
 * @author Administrator
 * @Date 2014年10月22日
 */
@Service
public class PublishHtmlForSingle implements PublishHtmlService {
    
    protected final static Logger logger = Logger.getLogger(PublishHtmlForSingle.class);
    
    @Autowired
    private PublishHtmlForMulti publishHtmlForMulti;
    
    @Autowired
    private StaticResourceCopyServiceImpl staticResourceCopyServiceImpl;
    
    /**
     * 
     * {@inheritDoc}
     * @throws DataException 
     * @throws IOException 
     */
    public  Map<String,Object> run(ConfPrepareData confPrepareData) throws Exception{        
        //支持批量发布的不带静态资源拷贝
        Map<String, Object> retMap = publishHtmlForMulti.run(confPrepareData);
        try {
            staticResourceCopyServiceImpl.copy(confPrepareData);
        } catch (IOException e) {
            logger.warn("copy static resource fail!",e);
        }
        return retMap;
       
    }
    

}
