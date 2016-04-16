/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.url;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kohler.bean.ConfPrepareData;
import com.kohler.dao.TemplateDao;
import com.kohler.entity.TemplateEntity;
import com.kohler.exception.DataException;
import com.kohler.service.UrlAnalysisService;
import com.kohler.service.templatename.SuiteListDataFilenameParse;

/**
 * Class Function Description
 *
 * @author Administrator
 * @Date 2014年11月14日
 */
@Component
public class SuiteUrlAnalysis implements UrlAnalysisService {
    
    @Autowired
    private TemplateDao templateDao;
    
    @Autowired
    private SuiteListUrlAnalysis suiteListUrlAnalysis;
    
    @Autowired
    private SuiteListDataFilenameParse suiteListDataFilenameParse;
    

    /**
     * {@inheritDoc}
     */
    public String getUrl(Integer templateId,ConfPrepareData conf) throws DataException {
        
        // get suite list level
        String suiteListUrl = suiteListUrlAnalysis.getUrl(templateId, conf);

        //获取模板
        TemplateEntity templateEntity = templateDao.selectById(templateId);
        
        //文件名
        String generalUrl = templateEntity.getGenerateUrl();//发布url规则
        generalUrl = suiteListDataFilenameParse.getGoalName(generalUrl, conf);
        
        String url = suiteListUrl + generalUrl;
        return url;
    }

}
