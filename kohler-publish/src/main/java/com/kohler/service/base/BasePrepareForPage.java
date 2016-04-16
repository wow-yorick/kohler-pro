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
import com.kohler.constants.CommonConstants;
import com.kohler.dao.PageDao;
import com.kohler.dao.PageFieldValuesDao;
import com.kohler.entity.PageEntity;

/**
 * Class Function Description
 *
 * @author Administrator
 * @Date 2014年10月20日
 */
@Component
public class BasePrepareForPage {

    @Autowired
    private PageDao pageDao;
    
    @Autowired
    private BaseCommon baseCommon;
    
    @Autowired
    private PageFieldValuesDao pageFieldValuesDao;
    
    /**
     * SEO信息封裝
     * @param pageData
     * @return
     * @author Administrator
     * Date 2014年10月21日
     * @version
     */
    public Map<String,Object> seoInfo(PageEntity pageData) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("SEO_TITLE", pageData.getSeoTitle());
        result.put("SEO_KEYWORDS", pageData.getSeoKeywords());
        result.put("SEO_DESCRIPTION", pageData.getSeoDescription());
        
        return result;
    }
    
    /**
     * 获取页面属性数据
     * @return
     * @author Administrator
     * Date 2014年10月22日
     * @version
     */
    public Map<String,Object> getPageExtAttrData(ConfPrepareData conf) {
        Map<String, Object> result = new HashMap<String, Object>();
        
        Map<String,Object> pageFields = getPageFieldValues(conf.getDataId(), conf);
        for(Map.Entry<String, Object> entry:pageFields.entrySet()){
            String attrKey = entry.getKey();
            String attrVal = (String) entry.getValue();
            if(!attrKey.equals(CommonConstants.STATIC_HTML_CONTENT_FIELD)) {
                List<Map<String,Object>> _resultTmp = baseCommon.getContentFieldValuesForList(attrVal, conf); 
                result.put(entry.getKey(), _resultTmp);                
            } else {
                result.put(entry.getKey(), attrVal);
            }
        } 
        
        return result;
    }
    
    /**
     * 获取頁面单记录信息
     * @return
     * @author Administrator
     * Date 2014年10月20日
     * @version
     */
    public  PageEntity getPageRecode(Integer dataId) {
        PageEntity dbData = pageDao.selectById(dataId);
        return dbData;
    }
    
    /**
     * 获取页面参数信息
     * @param pageId
     * @return
     * @author Administrator
     * Date 2014年10月22日
     * @version
     */
    public Map<String,Object> getPageFieldValues(Integer pageId, ConfPrepareData conf) {
        
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT * FROM PAGE_FIELD_VALUES WHERE PAGE_ID = ?");
        List<Object> id = new ArrayList<Object>();
        id.add(pageId);
        List<Map<String, Object>> dbData = pageFieldValuesDao.selectByConditionWithMap(sql.toString(), id);
        Map<String,Object> result = baseCommon.dbDataListToMap(dbData, conf);
        return result;
    }
    

   
}
