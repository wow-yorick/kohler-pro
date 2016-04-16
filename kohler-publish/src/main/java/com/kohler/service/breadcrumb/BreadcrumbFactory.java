/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.breadcrumb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kohler.service.IFactoryService;

/**
 * 面包屑工厂
 *
 * @author Administrator
 * @Date 2014年12月2日
 */
@Component
public class BreadcrumbFactory implements IFactoryService {
    
    @Autowired
    private PageDataBreadcrumb pageDataBreadcrumb;
    
    @Autowired
    private NewArrivalDataBreadcrumb newArrivalDataBreadcrumb;
    
    @Autowired
    private ProductDataBreadcrumb productDataBreadcrumb;
    
    @Autowired
    private ProductListDataBreadcrumb productListDataBreadcrumb;
    
    @Autowired
    private SuiteDataBreadcrumb suiteDataBreadcrumb;
    

    /**
     * {@inheritDoc}
     */
    @Override
    public Object createObject(String objName) {
        if("newArrivalData".equals(objName)) {
            return newArrivalDataBreadcrumb;
        }
        if("productData".equals(objName)) {
            return productDataBreadcrumb;
        }
        if("productListData".equals(objName)) {
            return productListDataBreadcrumb;
        }
        if("suiteData".equals(objName)) {
            return suiteDataBreadcrumb;
        }
        if("pageData".equals(objName)) {
            return pageDataBreadcrumb;
        }
        return null;
    }

}
