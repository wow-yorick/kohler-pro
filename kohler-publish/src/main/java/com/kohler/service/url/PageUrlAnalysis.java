/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.url;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kohler.bean.ConfPrepareData;
import com.kohler.dao.PageDao;
import com.kohler.entity.PageEntity;
import com.kohler.entity.SiteSettingEntity;
import com.kohler.exception.DataException;
import com.kohler.service.UrlAnalysisService;
import com.kohler.service.base.BaseCommon;
import com.kohler.service.base.BaseForSection;

/**
 * page section url
 *
 * @author Administrator
 * @Date 2014年12月25日
 */
@Component
public class PageUrlAnalysis implements UrlAnalysisService {
    
    @Autowired
    private PageDao pageDao;
    
    @Autowired
    private BaseCommon baseCommon;
    
    @Autowired
    private BaseForSection baseForSection;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUrl(Integer dataId, ConfPrepareData conf) throws DataException {
        //发布baseUrl
        SiteSettingEntity siteSettingEntity = baseCommon.getSitePlatformSet(conf);
        String publishBaseUrl = siteSettingEntity.getSiteDomainName();
        
        //获取页面信息
        PageEntity pageEntity = pageDao.selectById(conf.getDataId());
        //获取发布路径
        String publishFolder = baseForSection.getPublishFolderFromSection(pageEntity.getSectionId());
        
        String url = publishBaseUrl + publishFolder + pageEntity.getPhysicalName();//访问url
        return url;
    }

}
