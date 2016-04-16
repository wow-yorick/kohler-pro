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
import com.kohler.service.needpublishdata.CategoryList;
import com.kohler.service.publish.PublishHtmlForMulti;

/**
 * category 批量
 *
 * @author Administrator
 * @Date 2014年11月28日
 */
@Component
public class CategoryListBatch implements ClassifyPublishService {
    
    private final static Logger logger = Logger.getLogger(CategoryListBatch.class);
    
    @Autowired
    private CategoryList categoryList;
    
    @Autowired
    private CategoryDao categoryDao;
    
    @Autowired
    private PublishHtmlForMulti publishHtmlForMulti;

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Object> batchPublish(Integer parentId, ConfPrepareData conf) {
        //time mark
        long startTime = Calendar.getInstance().getTimeInMillis();
        
        Map<String, Object> retMap = new HashMap<String, Object>();//结果集
        
        int publishFail = 0; //发布失败条数
        List<Integer> dataIdList = new ArrayList<Integer>(); //发布页面数 
        
        StringBuffer runLog = new StringBuffer("category publish--");//batch run log
        StringBuffer successLog = new StringBuffer("[info][success:]");
        StringBuffer failLog = new StringBuffer("[warn][fail:]");
        
        dataIdList = categoryList.getPrimaryKeyList(parentId, conf);
        if(dataIdList != null && dataIdList.size() > 0) {
            for(Integer _categoryId : dataIdList) {
                try {
                    conf.setDataId(_categoryId);
                    conf.setXMLFileName(CommonConstants.XML_FOR_CATEGORY_PC_CN);
                    publishHtmlForMulti.run(conf);
                    successLog.append(_categoryId+",");
                } catch (Exception e) {
                    //logger.warn("category publish fail id "+ _categoryId);
                    failLog.append(_categoryId+",");
                    ++publishFail;
                }
            }
        }
        
        //获取categoty信息
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryMetadataId(parentId);
        categoryEntity.setIsEnable(true);
        categoryEntity.setLang(conf.getLang());
        
        String categoryName = "[null]";
        List<CategoryEntity> caEntities = categoryDao.selectByCondition(categoryEntity);//获取category信息     
        if(caEntities != null && caEntities.size() > 0) {
            categoryName = caEntities.get(0).getCategoryName();            
        }
        
        int total = dataIdList.size() - publishFail;
        String objResult = "@[" + categoryName+"]-category nums" + total;
        retMap.put("log", runLog.append(successLog).append(failLog));
        retMap.put("msg", "category publish success!");
        retMap.put("success", true);
        retMap.put("result", objResult);
        
        long endTime = Calendar.getInstance().getTimeInMillis();
        logger.info("CategoryListBatch ID:"+parentId+" time is "+ (endTime-startTime));
        
        return retMap;
    }

}
