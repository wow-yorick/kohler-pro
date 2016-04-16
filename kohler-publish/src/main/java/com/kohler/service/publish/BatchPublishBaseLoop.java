/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.publish;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kohler.bean.ConfPrepareData;
import com.kohler.service.BatchPublishService;
import com.kohler.service.base.BaseForCategory;
import com.kohler.service.needpublishdata.CategoryList;
import com.kohler.service.publish.classify.CategoryListBatch;
import com.kohler.service.publish.classify.NewArrivalDetailBatch;
import com.kohler.service.publish.classify.PageListBatch;
import com.kohler.service.publish.classify.ProductDetailBatch;
import com.kohler.service.publish.classify.SectionListBatch;
import com.kohler.service.publish.classify.SuiteDetailBatch;
import com.kohler.service.util.DataCacheServiceImplBaseDB;

/**
 * base loop
 *
 * @author Administrator
 * @Date 2014年12月30日
 */
@Component
public class BatchPublishBaseLoop implements BatchPublishService {
    
    @Autowired
    private CategoryList categoryList;
    
    @Autowired
    private PageListBatch pageListBatch;

    @Autowired
    private SectionListBatch sectionListBatch;
    
    @Autowired
    private CategoryListBatch categoryListBatch;
    
    @Autowired
    private ProductDetailBatch productListBatch;
    
    @Autowired
    private NewArrivalDetailBatch newArrivalListBatch;
    
    @Autowired
    private SuiteDetailBatch suiteListBatch;
    
    @Autowired
    private DataCacheServiceImplBaseDB dataCacheServiceImplBaseDB;

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Object> start(ConfPrepareData conf) {
        //clean data cache
        dataCacheServiceImplBaseDB.hdelAll(BaseForCategory.CATEGORY_BREADCRUMB);
        Map<String, Object> retMap = new HashMap<String, Object>();
        boolean success = false; //发布结果状态
        String msg = "publish fail!,please check the data,try again!";//发布信息
        StringBuffer runLog = new StringBuffer();//run log
        StringBuilder publishInfo = new StringBuilder("publish info:");
        
        //普通页面
        Map<String, Object> pageRet = pageListBatch.batchPublish(new Integer(0), conf);
        retMap.put("publishUrl", pageRet.get("publishUrl"));
        publishInfo.append(pageRet.get("result"));
        runLog.append("PAGE{"+pageRet.get("log")+"}");
        
        //section
        Map<String, Object> sectionRet = sectionListBatch.batchPublish(new Integer(0), conf);
        publishInfo.append(sectionRet.get("result"));
        runLog.append("SECTION{"+sectionRet.get("log")+"}");
        
        List<Integer> section = categoryList.getPrimaryKeyList(new Integer(0), conf);//section列表 parentID为0 即为section
        for(Integer categoryMetadataId : section) {
            //产品页面
            Map<String, Object> productRet = productListBatch.batchPublish(categoryMetadataId, conf);
            publishInfo.append(productRet.get("result"));
            runLog.append("PRODUCT{"+productRet.get("log")+"}");
            
            //category页面
            Map<String, Object> categoryRet = categoryListBatch.batchPublish(categoryMetadataId, conf);
            publishInfo.append(categoryRet.get("result"));
            runLog.append("CATEGORY{"+categoryRet.get("log")+"}");
        }
        
        //新品
        Map<String, Object> newArrivalRet = newArrivalListBatch.batchPublish(new Integer(0), conf);
        publishInfo.append(newArrivalRet.get("result"));
        runLog.append("NEW_ARRIVAL{"+newArrivalRet.get("log")+"}");
        
        //套间
        Map<String, Object> suiteRet = suiteListBatch.batchPublish(new Integer(0), conf);
        publishInfo.append(suiteRet.get("result"));
        runLog.append("SUITE{"+suiteRet.get("log")+"}");
        
        success = true;
        msg = publishInfo.toString();//发布信息
        
        retMap.put("success",success);
        retMap.put("msg", msg);
        retMap.put("log", runLog.toString());
        
        return retMap;
    }

}
