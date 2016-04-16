/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.url;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kohler.bean.ConfPrepareData;
import com.kohler.entity.SiteSettingEntity;
import com.kohler.entity.TemplateEntity;
import com.kohler.exception.DataException;
import com.kohler.service.UrlAnalysisService;
import com.kohler.service.base.BaseCommon;
import com.kohler.service.base.BaseForCategory;
import com.kohler.service.templatename.CategoryDataFilenameParse;
import com.kohler.service.util.DataCacheServiceImplBaseDB;

/**
 * Class Function Description
 *
 * @author Administrator
 * @Date 2014年11月14日
 */
@Component
public class CategoryUrlAnalysis  implements UrlAnalysisService {
    
    @Autowired
    private BaseForCategory baseForCategory;
    
    @Autowired
    private CategoryDataFilenameParse categoryDataFilenameParse;
    
    @Autowired
    private BaseCommon baseCommon;
    
    @Autowired
    private DataCacheServiceImplBaseDB dataCacheServiceImplBaseDB;
    
    public final static String CATEGORY_URL = "CATEGORY_URL";

    /**
     * {@inheritDoc}
     * @throws DataException 
     */
    @Override
    public String getUrl(Integer categoryMetadataId, ConfPrepareData conf) throws DataException {
        //cache data hit
        String categoryUrl = dataCacheServiceImplBaseDB.hget(CategoryUrlAnalysis.CATEGORY_URL, categoryMetadataId.toString()+conf.getLang().toString()+conf.getWebsitePlatform());
        if(null != categoryUrl) {
            return categoryUrl;
        }
        //发布baseUrl
        SiteSettingEntity siteSettingEntity = baseCommon.getSitePlatformSet(conf);
        String publishBaseUrl = siteSettingEntity.getSiteDomainName();
        
        //发布目录
        String publishFolder = baseForCategory.getCategoryBreadcrumbPath(categoryMetadataId, new StringBuilder(), conf);
        
        //获取模板
        TemplateEntity templateEntity = baseForCategory.getTemplateInfoByCategoryMetadataId(Integer.valueOf(categoryMetadataId), conf);
        //命名规则转换
        String generalUrl = templateEntity.getGenerateUrl();//实际url
        generalUrl = categoryDataFilenameParse.getGoalName(generalUrl, conf); 
        //页面地址
        String url = publishBaseUrl + publishFolder + generalUrl;
        //cache db
        dataCacheServiceImplBaseDB.hset(CategoryUrlAnalysis.CATEGORY_URL, categoryMetadataId.toString()+conf.getLang().toString()+conf.getWebsitePlatform(), url);
        
        return url;
    }

}
