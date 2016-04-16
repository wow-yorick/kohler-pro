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
import com.kohler.dao.NewArrivalMetadataDao;
import com.kohler.entity.NewArrivalMetadataEntity;
import com.kohler.exception.DataException;
import com.kohler.service.BreadCrumbService;
import com.kohler.service.base.BaseForCategory;

/**
 * new product breadcrumb
 *
 * @author Administrator
 * @Date 2014年11月19日
 */
@Component
public class NewArrivalDataBreadcrumb implements BreadCrumbService {
    
    //private final static Logger logger = Logger.getLogger(NewArrivalDataBreadcrumb.class);
    
    @Autowired
    private NewArrivalMetadataDao newArrivalMetadataDao;
    
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
            
            NewArrivalMetadataEntity newArrivalMetadataEntity = newArrivalMetadataDao.selectById(conf.getDataId());
            //categoty的面包屑
            breadCrumbs = baseForCategory.categoryBreadCrumb(newArrivalMetadataEntity.getCategoryMetadataId(), conf);
            
            //卫浴新品也是面包屑一部分
            Map<String,Object> breadBean = new HashMap<String, Object>();
            breadBean.put("name", "卫浴新品");
            breadBean.put("url", "");
            breadCrumbs.add(breadBean);
            
//        } catch (Exception e) {
//            logger.warn("get suite breadcrumb fail!");
//        }

        
        return breadCrumbs;
    }


}
