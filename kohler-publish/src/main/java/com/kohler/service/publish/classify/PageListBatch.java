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
import com.kohler.dao.PageDao;
import com.kohler.dao.TemplateDao;
import com.kohler.entity.PageEntity;
import com.kohler.entity.TemplateEntity;
import com.kohler.service.ClassifyPublishService;
import com.kohler.service.needpublishdata.PageList;
import com.kohler.service.publish.PublishHtmlForMulti;

/**
 * 普通页面发布
 *
 * @author Administrator
 * @Date 2014年11月28日
 */
@Component
public class PageListBatch implements ClassifyPublishService {
    
    private final static Logger logger = Logger.getLogger(PageListBatch.class);
    
    @Autowired
    private PageList pageList;
    
    @Autowired
    private PageDao pageDao;
    
    @Autowired
    private TemplateDao templateDao;
    
    @Autowired
    private PublishHtmlForMulti publishHtmlForMulti;

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Object> batchPublish(Integer keyId, ConfPrepareData conf) {
        long startTime = Calendar.getInstance().getTimeInMillis();
        
        Map<String, Object> retMap = new HashMap<String, Object>();//结果集
        
        int pagePublishFail = 0; //普遍页面发布失败条数
        List<Integer> pageL = new ArrayList<Integer>(); //发布页面数 -- 普通页面
        
        StringBuffer runLog = new StringBuffer("page publish--");//batch run log
        StringBuffer successLog = new StringBuffer("[info][success:]");
        StringBuffer failLog = new StringBuffer("[warn][fail:]");
        
        //普通页面
        pageL = pageList.getPrimaryKeyList(new Integer(0), conf);
        if(pageList != null && pageL.size() > 0) {
            for(int i= 0; i < pageL.size(); i ++) {
                Integer pageId = pageL.get(i);
                try {
                    conf.setDataId(pageId);
                    conf.setXMLFileName(CommonConstants.XML_FOR_PAGE);
                    
                    //如果数据库中配有xml名称则以数据库中为准
                    String xmlName = getXMLfileName(pageId);
                    if(xmlName != null && !"".equals(xmlName)) {
                        conf.setXMLFileName(xmlName);
                    }
                    
                    Map<String, Object> result = publishHtmlForMulti.run(conf);
                    if(i == 0) {//返回首页的url
                        retMap.put("publishUrl", result.get("publishUrl"));// 关键,返回首页
                    }
                    successLog.append(pageId+",");
                    
                } catch (Exception e) {
                    //logger.warn("page publish fail id "+ pageId);
                    failLog.append(pageId+",");
                    ++pagePublishFail;
                }
            }
        }
        
        int pageLTotal = pageL.size() - pagePublishFail;
        
        String objResult = "@page nums" + pageLTotal;
        retMap.put("log", runLog.append(successLog).append(failLog));
        retMap.put("msg", "page publish success!");
        retMap.put("success", true);
        retMap.put("result", objResult);
        
        long endTime = Calendar.getInstance().getTimeInMillis();
        logger.info("PageListBatch time is "+ (endTime-startTime));
        
        return retMap;
    }
    
    /**
     * 获取xml名称
     * @param pageId
     * @return
     * @author Administrator
     * Date 2014年12月8日
     * @version
     */
    private String getXMLfileName(Integer pageId) {
        PageEntity paEntity =pageDao.selectById(pageId);
        TemplateEntity templateEntity = templateDao.selectById(paEntity.getTemplateId());
        String xmlName = templateEntity.getDataGettingXmlName();
        return xmlName;
    }

}
