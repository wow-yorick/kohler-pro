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
import com.kohler.dao.ProductDao;
import com.kohler.entity.ProductEntity;
import com.kohler.entity.ProductMetadataEntity;
import com.kohler.exception.DataException;
import com.kohler.service.BreadCrumbService;
import com.kohler.service.base.BaseForCategory;
import com.kohler.service.base.BaseForProduct;

/**
 * Class Function Description
 *
 * @author Administrator
 * @Date 2014年10月20日
 */
@Component
public class ProductDataBreadcrumb implements BreadCrumbService { 
    
    //private final static Logger logger = Logger.getLogger(ProductDataBreadcrumb.class);
    
    @Autowired
    private ProductDao productDao;
    
    @Autowired
    private BaseForProduct baseForProduct;
    
    @Autowired
    private BaseForCategory baseForCategory;
    
    /**
     * 获取产品面包屑信息
     * @return
     * @author Administrator
     * Date 2014年10月28日
     * @version
     * @throws DataException 
     */
    public List<Map<String,Object>> getBreadCrumb(ConfPrepareData confPrepareData) throws DataException {

        List<Map<String,Object>> breadCrumbs = new ArrayList<Map<String,Object>>();
        //try {
            StringBuffer sql = new StringBuffer("SELECT CATEGORY_METADATA_ID, PRODUCT_NAME FROM VW_PRODUCT WHERE PRODUCT_METADATA_ID = ?");
            List<Object> params = new ArrayList<Object>();
            params.add(confPrepareData.getDataId());
            List<Map<String, Object>> productInfo = productDao.selectByConditionWithMap(sql.toString(), params);
//            ProductMetadataEntity productMeta = baseForProduct.getProductMetadataInfo(confPrepareData);//获取产品主表信息
//            List<ProductEntity> productEntiryList = baseForProduct.getProductInfo(confPrepareData);//获取产品信息
            
            //categoty的面包屑
            breadCrumbs = baseForCategory.categoryBreadCrumb(Integer.valueOf(productInfo.get(0).get("CATEGORY_METADATA_ID").toString()), confPrepareData);
            
            //产品名称也是面包屑一部分
            Map<String,Object> breadBean = new HashMap<String, Object>();
            breadBean.put("name", productInfo.get(0).get("PRODUCT_NAME"));
            breadBean.put("url", "");
            breadCrumbs.add(breadBean);
            
//        } catch (Exception e) {
//            logger.warn("get product breadcrumbs fail!");
//        }

        return breadCrumbs;
    }

   
}
