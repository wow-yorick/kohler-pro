/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.publish;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohler.bean.ConfPrepareData;
import com.kohler.bean.PublishConf;
import com.kohler.constants.CommonConstants;
import com.kohler.exception.DataException;
import com.kohler.service.PublishHtmlForInterfaceService;
import com.kohler.service.impl.StaticResourceCopyServiceImpl;

/**
 * 发布预览接口
 *
 * @author Administrator
 * @Date 2014年11月21日
 */
@Service
public class PublishHtmlForInterfaceServiceImpl implements PublishHtmlForInterfaceService {
    
    private final static Logger logger = Logger.getLogger(PublishHtmlForInterfaceServiceImpl.class);
    
    @Autowired
    private PublishHtmlForSingle publishHtmlForSingle;
    
    @Autowired
    private StaticResourceCopyServiceImpl staticResourceCopyServiceImpl;
    
    private static Map<Integer, String> templateMap;
    
    static {
        if (templateMap == null) {
            templateMap = new HashMap<Integer, String>();
            templateMap.put(CommonConstants.TEMPLATE_TYPE_CATALOG, CommonConstants.XML_FOR_SECTION_PC_CN);//catelog 模板 section
            templateMap.put(CommonConstants.TEMPLATE_TYPE_CATEGORY, CommonConstants.XML_FOR_CATEGORY_PC_CN);//category 模板
            templateMap.put(CommonConstants.TEMPLATE_TYPE_NEW_PRODUCT, CommonConstants.XML_FOR_NEW_PRODUCT_PC_CN);//新品 模板
            
            templateMap.put(CommonConstants.TEMPLATE_TYPE_PAGE, CommonConstants.XML_FOR_PAGE);//页面 模板
            templateMap.put(CommonConstants.TEMPLATE_TYPE_PAGE_SOLRMAP, CommonConstants.XML_FOR_PAGE_SOLRMAP);//页面 solrmap模板
            
            templateMap.put(CommonConstants.TEMPLATE_TYPE_PRODUCT, CommonConstants.XML_FOR_PRODUCT);//产品 模板
            templateMap.put(CommonConstants.TEMPLATE_TYPE_PRODUCT_SKU_JSFILE, CommonConstants.XML_FOR_PRODUCT_SKU_JSFILE);//产品 SKU js模板
            
            templateMap.put(CommonConstants.TEMPLATE_TYPE_SUITE, CommonConstants.XML_FOR_SUITE);//套间 模板详情
            
            templateMap.put(CommonConstants.TEMPLATE_TYPE_PAGE_SUITE_LIST, CommonConstants.XML_FOR_SUITE_LIST);//套间 列表
        
        //return templateMap.get(templateType);
        }
        
    }
    
    /**
     * 
     * {@inheritDoc}
     * @throws Exception 
     * @throws DataException 
     */
    public  Map<String,Object> run(PublishConf publishConf) throws Exception{
        long starttime = Calendar.getInstance().getTimeInMillis();
        logger.info("start");
        
        Map<String, Object> retMap = new HashMap<String, Object>();
        try {
            ConfPrepareData confPrepareData = new ConfPrepareData();
            BeanUtils.copyProperties(confPrepareData, publishConf);
            String xmlFileName = getTemplateFile(publishConf.getTemplateType());
            confPrepareData.setXMLFileName(xmlFileName);
            
            //支持批量发布的不带静态资源拷贝
            retMap = publishHtmlForSingle.run(confPrepareData);
            long endtime = Calendar.getInstance().getTimeInMillis();
            logger.info("times= " + (endtime -starttime));
        } catch (Exception e) {
            long endtime = Calendar.getInstance().getTimeInMillis();
            logger.error("times= " + (endtime -starttime));
            throw e;
        }
        return retMap;
       
    }
    
    
    /**
     * 根据模板类型获取模板文件
     * @param templateType
     * @return
     * @author Administrator
     * Date 2014年11月21日
     * @version
     */
    private String getTemplateFile(Integer templateType) {
        
//        Map<Integer, String> templateMap = new HashMap<Integer, String>();
//        templateMap.put(CommonConstants.TEMPLATE_TYPE_CATALOG, CommonConstants.XML_FOR_SECTION_PC_CN);//catelog 模板 section
//        templateMap.put(CommonConstants.TEMPLATE_TYPE_CATEGORY, CommonConstants.XML_FOR_CATEGORY_PC_CN);//category 模板
//        templateMap.put(CommonConstants.TEMPLATE_TYPE_NEW_PRODUCT, CommonConstants.XML_FOR_NEW_PRODUCT_PC_CN);//新品 模板
//        
//        templateMap.put(CommonConstants.TEMPLATE_TYPE_PAGE, CommonConstants.XML_FOR_PAGE);//页面 模板
//        templateMap.put(CommonConstants.TEMPLATE_TYPE_PAGE_SOLRMAP, CommonConstants.XML_FOR_PAGE_SOLRMAP);//页面 solrmap模板
//        
//        templateMap.put(CommonConstants.TEMPLATE_TYPE_PRODUCT, CommonConstants.XML_FOR_PRODUCT);//产品 模板
//        templateMap.put(CommonConstants.TEMPLATE_TYPE_PRODUCT_SKU_JSFILE, CommonConstants.XML_FOR_PRODUCT_SKU_JSFILE);//产品 SKU js模板
//        
//        templateMap.put(CommonConstants.TEMPLATE_TYPE_SUITE, CommonConstants.XML_FOR_SUITE);//套间 模板详情
//        
//        templateMap.put(CommonConstants.TEMPLATE_TYPE_PAGE_SUITE_LIST, CommonConstants.XML_FOR_SUITE_LIST);//套间 列表
        
        return templateMap.get(templateType);
    }
    
}
