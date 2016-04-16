/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.publish;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kohler.bean.ConfPrepareData;
import com.kohler.exception.DataException;
import com.kohler.service.PublishHtmlService;
import com.kohler.service.PublishStrategyService;
import com.kohler.service.base.XmlElementDefineMath;
import com.kohler.service.impl.XmlStrategyParseServiceImpl;
import com.kohler.service.strategy.PublishStrategyFactory;
import com.kohler.service.util.XMLReader2DOM4JFinal;

/**
 * 发布功不带静态文件拷贝
 *
 * @author Administrator
 * @Date 2014年10月22日
 */
@Service
public class PublishHtmlForMulti  implements PublishHtmlService {
    
    protected final static Logger logger = Logger.getLogger(PublishHtmlForMulti.class);

    @Autowired
    private PublishStrategyFactory publishStrategyFactory;
    
    @Autowired
    private XmlStrategyParseServiceImpl xmlStrategyParseServiceImpl;
    
    @Value("#{settings['file.resources.dir']}")
    private String resourcesDir;
    
    @Value("#{settings['file.velocity.dataConfig.dir']}")
    private String preDataConfig;
   
    
    /**
     * 
     * {@inheritDoc}
     * @throws DataException 
     */
    @SuppressWarnings("unchecked")
    public  Map<String,Object> run(ConfPrepareData confPrepareData) throws Exception {    
        Map<String, Object> retMap = new HashMap<String, Object>();//结果
        
        String dataPreConfFile = resourcesDir+preDataConfig+confPrepareData.getXMLFileName();//算法配置文件路径
        File xmlTmp = new File(dataPreConfFile);
        if(!XmlElementDefineMath.xmlALGMap.containsKey(confPrepareData.getXMLFileName())) {
            Map<String,Object> _xmlALGMapTmp = XMLReader2DOM4JFinal.readFileXml(xmlTmp); //算法信息 
            XmlElementDefineMath.xmlALGMap.put(confPrepareData.getXMLFileName(), _xmlALGMapTmp);
        }
        Map<String,Object> xmlALGMap = (Map<String, Object>) XmlElementDefineMath.xmlALGMap.get(confPrepareData.getXMLFileName());

        Map<String,Object> preData = xmlStrategyParseServiceImpl.resolver(confPrepareData);//准备数据
        
        //获取对应的发布策略
        Map<String,Object> rootEltMap = (Map<String, Object>) xmlALGMap.get("root");//获取xml解析的根元素
        Map<String,Object> _privateInfo = (Map<String, Object>) rootEltMap.get("_privateInfo");//数据源的私有信息
        Map<String,Object> _attribute = (Map<String, Object>) _privateInfo.get("_attribute");//属性
        
        PublishStrategyService publishStrategy = (PublishStrategyService) publishStrategyFactory.createObject((String) _attribute.get("name"));
        retMap = publishStrategy.publishMethod(confPrepareData, preData);
        
        return retMap;
       
    }

}
