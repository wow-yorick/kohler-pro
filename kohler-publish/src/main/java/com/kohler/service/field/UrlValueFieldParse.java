/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.field;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kohler.bean.ConfPrepareData;
import com.kohler.exception.DataException;
import com.kohler.service.SpecialFieldParseService;
import com.kohler.service.UrlAnalysisService;
import com.kohler.service.impl.XmlStrategyParseServiceImpl;
import com.kohler.service.url.UrlAnalysisFactory;

/**
 * url字段类型解析
 *
 * @author Administrator
 * @Date 2014年11月22日
 */
@Component
public class UrlValueFieldParse implements SpecialFieldParseService {
    
    private final static Logger logger = Logger.getLogger(XmlStrategyParseServiceImpl.class);
    
    @Autowired
    private UrlAnalysisFactory urlAnalysisFactory;

    /**
     * 处理url类别字段数据
     * {@inheritDoc}
     */
    public String parse(Map<String,Object> dataSource,Map<String, Object> _filedMap,ConfPrepareData conf) {
            
            String depandOn = (String)_filedMap.get("dependOn");
            String service = (String)_filedMap.get("service");
            
            String url = "";
            
            if(depandOn != null && service != null) {
                //获取数据源中具体依赖的字段
                Object obj = dataSource.get(depandOn);
                Integer parameter = Integer.valueOf(obj.toString());
                //调用具体service
                UrlAnalysisService urlAnalysis = (UrlAnalysisService) urlAnalysisFactory.createObject(service);
                try {
                    url = urlAnalysis.getUrl(parameter,conf);
                } catch (DataException e) {
                    logger.warn("url parse fail!");
                }
            }
            
        return url;
    }

}
