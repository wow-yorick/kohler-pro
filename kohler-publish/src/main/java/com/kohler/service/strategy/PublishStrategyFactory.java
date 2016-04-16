/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kohler.service.IFactoryService;

/**
 * 策略工厂
 *
 * @author Administrator
 * @Date 2014年12月2日
 */
@Component
public class PublishStrategyFactory implements IFactoryService {
    
    @Autowired
    private NewArrivalDataPublishStrategy newArrivalDataPublishStrategy;
    
    @Autowired
    private PageDataPublishStrategy pageDataPublishStrategy;
    
    @Autowired
    private ProductDataPublishStrategy productDataPublishStrategy;
    
    @Autowired
    private ProductListDataPublishStrategy productListDataPublishStrategy;
    
    @Autowired
    private SuiteDataPublishStrategy suiteDataPublishStrategy;
    
    @Autowired
    private ProductSKUjsFileDataPublishStrategy productSKUjsFileDataPublishStrategy;

    /**
     * {@inheritDoc}
     */
    @Override
    public Object createObject(String objName) {
        if("newArrivalData".equals(objName)) {
            return newArrivalDataPublishStrategy;
        }
        if("pageData".equals(objName)) {
            return pageDataPublishStrategy;
        }
        if("productData".equals(objName)) {
            return productDataPublishStrategy;
        }
        if("productListData".equals(objName)) {
            return productListDataPublishStrategy;
        }
        if("suiteData".equals(objName)) {
            return suiteDataPublishStrategy;
        }
        if("productSKUjsFileData".equals(objName)) {
            return productSKUjsFileDataPublishStrategy;
        }
        
        return null;
    }

   

}
