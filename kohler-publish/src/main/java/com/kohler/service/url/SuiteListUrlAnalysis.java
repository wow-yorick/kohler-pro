/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.url;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kohler.bean.ConfPrepareData;
import com.kohler.dao.PublishFolderDao;
import com.kohler.dao.TemplateDao;
import com.kohler.entity.PublishFolderEntity;
import com.kohler.entity.SiteSettingEntity;
import com.kohler.entity.TemplateEntity;
import com.kohler.exception.DataException;
import com.kohler.service.UrlAnalysisService;
import com.kohler.service.base.BaseCommon;

/**
 * suite list url analysis
 *
 * @author Administrator
 * @Date 2014年12月19日
 */
@Component
public class SuiteListUrlAnalysis implements UrlAnalysisService {
    
    @Autowired
    private TemplateDao templateDao;
    
    @Autowired
    private BaseCommon baseCommon;
    
    @Autowired
    private PublishFolderDao publishFolderDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUrl(Integer templateId, ConfPrepareData conf) throws DataException {
        //发布baseUrl
        SiteSettingEntity siteSettingEntity = baseCommon.getSitePlatformSet(conf);
        String publishBaseUrl = siteSettingEntity.getSiteDomainName();
        
        //获取模板
        TemplateEntity templateEntity = templateDao.selectById(templateId);
        
        //获取发布路径
        Integer publishFolderId = templateEntity.getPublishFolderId();//获取发布ID        
        PublishFolderEntity publishFolderEntity = publishFolderDao.selectById(publishFolderId);
        String publishFolder = publishFolderEntity.getPublishFolderPath();//具体路径
        
        //页面地址
        String url = publishBaseUrl + publishFolder;
        
        return url;
    }

}
