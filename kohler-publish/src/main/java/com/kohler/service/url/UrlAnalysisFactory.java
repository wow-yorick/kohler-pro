/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.url;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kohler.service.IFactoryService;

/**
 * 获取url工厂
 *
 * @author Administrator
 * @Date 2014年12月3日
 */
@Component
public class UrlAnalysisFactory implements IFactoryService {
    
    @Autowired
    private CategoryUrlAnalysis categoryUrlAnalysis;
    
    @Autowired
    private ProductUrlAnalysis productUrlAnalysis;
    
    @Autowired
    private SuiteUrlAnalysis suiteUrlAnalysis;
    
    @Autowired
    private SuiteListUrlAnalysis suiteListUrlAnalysis;
    
    @Autowired
    private PageUrlAnalysis pageUrlAnalysis;

    /**
     * {@inheritDoc}
     */
    @Override
    public Object createObject(String objName) {
        if("categoryUrlAnalysis".equals(objName)) {
            return categoryUrlAnalysis;
        }
        if("productUrlAnalysis".equals(objName)) {
            return productUrlAnalysis;
        }
        if("suiteUrlAnalysis".equals(objName)) {
            return suiteUrlAnalysis;
        }
        if("suiteListUrlAnalysis".equals(objName)) {
            return suiteListUrlAnalysis;
        }
        if("pageUrlAnalysis".equals(objName)) {
            return pageUrlAnalysis;
        }
        return null;
    }

}
