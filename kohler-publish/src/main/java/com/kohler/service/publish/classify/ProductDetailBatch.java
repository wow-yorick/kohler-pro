/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.publish.classify;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kohler.bean.ConfPrepareData;
import com.kohler.constants.CommonConstants;
import com.kohler.dao.CategoryDao;
import com.kohler.entity.CategoryEntity;
import com.kohler.service.ClassifyPublishService;
import com.kohler.service.needpublishdata.ProductList;
import com.kohler.service.publish.PublishHtmlForMulti;

/**
 * 产品批量发布
 *
 * @author Administrator
 * @Date 2014年11月28日
 */
@Component
public class ProductDetailBatch implements ClassifyPublishService {
    
    private final static Logger logger = Logger.getLogger(ProductDetailBatch.class);
    
    @Autowired
    private ProductList productList;
    
    @Autowired
    private CategoryDao categoryDao;
    
    @Autowired
    private PublishHtmlForMulti publishHtmlForMulti;
    
    

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Object> batchPublish(Integer categoryMetadataId, ConfPrepareData conf) {
        //time mark
        long startTime = Calendar.getInstance().getTimeInMillis();
        
        Map<String, Object> retMap = new HashMap<String, Object>();//结果集
        
        int publishFail = 0; //发布失败条数
        List<Integer> dataIdList = new ArrayList<Integer>(); //发布页面数 

        StringBuffer runLog = new StringBuffer("product detail publish--");//batch run log
        StringBuffer successLog = new StringBuffer("[info][success:]");
        StringBuffer failLog = new StringBuffer("[warn][fail:]");
        
        dataIdList = productList.getPrimaryKeyList(categoryMetadataId, conf);
        if(dataIdList != null && dataIdList.size() > 0) {
            for(Integer _dataId : dataIdList) {
                try {
                    conf.setDataId(_dataId);
                    conf.setXMLFileName(CommonConstants.XML_FOR_PRODUCT);
                    publishHtmlForMulti.run(conf);  

                    //only run pc cn version
                    if(conf.getLang().equals(CommonConstants.LOCALE_CN) && CommonConstants.PC_PLATFORM.equals(conf.getWebsitePlatform())) {
                        long startTime_s = Calendar.getInstance().getTimeInMillis();
                        
                        conf.setDataId(_dataId);
                        conf.setXMLFileName(CommonConstants.XML_FOR_PRODUCT_SKU_JSFILE);
                        publishHtmlForMulti.run(conf);
                        
                        long endTime_s = Calendar.getInstance().getTimeInMillis();
                        logger.info("ProductDetailBatch js file categoryMetadataId:"+categoryMetadataId+" time is "+ (endTime_s-startTime_s));
                    }
                    
                    successLog.append(_dataId+",");
                    
                } catch (Exception e) {
                    //logger.warn("[warn]product data publish fail id "+ _dataId);
                    failLog.append(_dataId+",");
                    ++publishFail;
                }
            }
        }
        //获取categoty信息
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryMetadataId(categoryMetadataId);
        categoryEntity.setIsEnable(true);
        categoryEntity.setLang(conf.getLang());
        List<CategoryEntity> caEntities = categoryDao.selectByCondition(categoryEntity);//获取category信息
        String categoryName = "[null]";
        if(caEntities != null && caEntities.size() > 0) {
            categoryName = caEntities.get(0).getCategoryName();            
        }
        
        int total = dataIdList.size() - publishFail;
        String objResult = "@["+categoryName+"]product nums" + total;
        retMap.put("log", runLog.append(successLog).append(failLog));
        retMap.put("msg", "product publish success!");
        retMap.put("success", true);
        retMap.put("result", objResult);
        
        long endTime = Calendar.getInstance().getTimeInMillis();
        logger.info("ProductDetailBatch categoryMetadataId:"+categoryMetadataId+" time is "+ (endTime-startTime));
        
        return retMap;
    }

}
