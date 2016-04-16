/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kohler.bean.ConfPrepareData;
import com.kohler.dao.ProductDao;
import com.kohler.dao.ProductMetadataDao;
import com.kohler.entity.ProductEntity;
import com.kohler.entity.ProductMetadataEntity;

/**
 * product common func
 *
 * @author Administrator
 * @Date 2014年11月15日
 */
@Component
public class BaseForProduct {
    
    @Autowired
    private ProductDao productDao;
    
    @Autowired
    private ProductMetadataDao productMetadataDao;
    
    
    /**
     * 获取产品信息
     * @return
     * @author Administrator
     * Date 2014年10月28日
     * @version
     */
    public Map<String,Object> getProductInfoWithMap(ConfPrepareData conf) {
        StringBuilder sql = new StringBuilder("SELECT CATEGORY_METADATA_ID,PRODUCT_ID,PC_TEMPLATE_ID,MOBILE_TEMPLATE_ID,PRODUCT_METADATA_ID,PRODUCT_CODE,PRODUCT_NAME,DATA_TEMPLATE_ID FROM VW_PRODUCT WHERE LANG =");
        sql.append(conf.getLang());
        sql.append(" AND PRODUCT_METADATA_ID = ");
        sql.append(conf.getDataId());
        
        List<Map<String,Object>> resultMap = productDao.selectByConditionWithMap(sql.toString(), new ArrayList<Object>());
        if(resultMap != null && resultMap.size() > 0) {
            return resultMap.get(0);
        } else {
            return new HashMap<String, Object>();
        }
        
    }
    
    /**
     * 获取产品主表信息
     * @return
     * @author Administrator
     * Date 2014年10月24日
     * @version
     */
    public ProductMetadataEntity getProductMetadataInfo(ConfPrepareData conf) {
        return productMetadataDao.selectById(conf.getDataId());
    }
    

    /**
     * 获取产品信息
     * @return
     * @author Administrator
     * Date 2014年10月28日
     * @version
     */
    public List<ProductEntity> getProductInfo(ConfPrepareData conf) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductMetadataId(conf.getDataId());
        productEntity.setLang(conf.getLang());
        return productDao.selectByCondition(productEntity);
    }
    
}
