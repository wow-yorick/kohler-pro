/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.breadcrumb;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kohler.bean.ConfPrepareData;
import com.kohler.exception.DataException;
import com.kohler.service.BreadCrumbService;
import com.kohler.service.base.BaseForCategory;

/**
 * 获取产品列表
 *
 * @author Administrator
 * @Date 2014年10月27日
 */
@Component
public class ProductListDataBreadcrumb implements BreadCrumbService {
    
    @Autowired
    private BaseForCategory baseForCategory;
    
    /**
     * {@inheritDoc}
     * @throws DataException 
     */
    public List<Map<String, Object>> getBreadCrumb(ConfPrepareData confPrepareData) throws DataException {
        List<Map<String,Object>> breadCrumbs = baseForCategory.categoryBreadCrumb(confPrepareData.getDataId(), confPrepareData);
        return breadCrumbs;
    }

}
