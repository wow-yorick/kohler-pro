/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.xmlfunc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohler.bean.ConfPrepareData;
import com.kohler.entity.PageEntity;
import com.kohler.service.DataPrepareService;
import com.kohler.service.base.BasePrepareForPage;

/**
 * page data prepare
 *
 * @author Administrator
 * @Date 2014年10月20日
 */
@Service
public class PageDataPrepareServiceImpl implements DataPrepareService  {
    
    @Autowired
    private BasePrepareForPage basePrepareForPage;
    
    
    /**
     * {@inheritDoc}
     */
    public  Map<String, Object> getGeneralData(ConfPrepareData conf) {

            Map<String, Object> result = new HashMap<String, Object>();
            
            //seo信息
            PageEntity page = basePrepareForPage.getPageRecode(conf.getDataId());
            Map<String,Object> seo = basePrepareForPage.seoInfo(page);
            List<Map<String,Object>> seoL = new ArrayList<Map<String,Object>>();
            seoL.add(seo);
            //动态分类信息
            Map<String,Object> getPageExtAttrData = basePrepareForPage.getPageExtAttrData(conf);
            
            result.put("SEO",seoL);
            result.putAll(getPageExtAttrData);
            return result;
    }


}
