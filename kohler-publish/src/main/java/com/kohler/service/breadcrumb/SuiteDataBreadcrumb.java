/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.breadcrumb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kohler.bean.ConfPrepareData;
import com.kohler.constants.CommonConstants;
import com.kohler.dao.SuiteMetadataDao;
import com.kohler.entity.TemplateEntity;
import com.kohler.exception.DataException;
import com.kohler.service.BreadCrumbService;
import com.kohler.service.base.BaseCommon;
import com.kohler.service.base.BaseForCategory;
import com.kohler.service.base.BaseForSuite;
import com.kohler.service.url.SuiteListUrlAnalysis;

/**
 * 套间详情页面包屑
 *
 * @author Administrator
 * @Date 2014年11月24日
 */
@Component
public class SuiteDataBreadcrumb implements BreadCrumbService {
    
    //private final static Logger logger = Logger.getLogger(SuiteDataBreadcrumb.class);
    
    @Autowired
    private SuiteMetadataDao suiteMetadataDao;
    
    @Autowired
    private SuiteListUrlAnalysis suiteListUrlAnalysis;
    
    @Autowired
    private BaseCommon baseCommon;
    
    @Autowired
    private BaseForSuite baseForSuite;
    
    @Autowired
    private BaseForCategory baseForCategory;


    /**
     * {@inheritDoc}
     * @throws DataException 
     */
    @Override
    public List<Map<String, Object>> getBreadCrumb(ConfPrepareData conf) throws DataException {

        List<Map<String,Object>> breadCrumbs = new ArrayList<Map<String,Object>>();
        //try {
            
            Map<String, Object> suiteProduct = baseForSuite.getSuiteInfoWithMap(conf);//获取套间
            
            Integer categoryMetadataId = Integer.valueOf(suiteProduct.get("CATEGORY_METADATA_ID").toString());//获取套间所属分类
            //categoty的面包屑
            breadCrumbs = baseForCategory.categoryBreadCrumb(categoryMetadataId, conf);//获取分类面包屑
            
            //获取模板
            TemplateEntity templateEntity = baseCommon.getTemplateByMap(suiteProduct, conf);
            //套间
            Map<String,Object> breadBean = new HashMap<String, Object>();
            String url = suiteListUrlAnalysis.getUrl(templateEntity.getTemplateId(), conf);//获取套间列表的url
            //手动加入一级面包屑
            String suiteName = "Suites";
            if(conf.getLang() == CommonConstants.LOCALE_CN) {
                suiteName = "套间";
            }
            breadBean.put("name", suiteName);
            breadBean.put("url", url);
            breadCrumbs.add(breadBean);
            
            //具体套间
            Map<String,Object> breadBeanSuiteName = new HashMap<String, Object>();
            breadBeanSuiteName.put("name", suiteProduct.get("SUITE_NAME"));
            breadBeanSuiteName.put("url", "");
            breadCrumbs.add(breadBeanSuiteName);
            
//        } catch (Exception e) {
//            logger.warn("get suite breadcrumb fail!");
//        }
        
        return breadCrumbs;
    }
    

}
