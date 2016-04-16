/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.url;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kohler.bean.ConfPrepareData;
import com.kohler.dao.PublishFolderDao;
import com.kohler.entity.PublishFolderEntity;
import com.kohler.entity.SiteSettingEntity;
import com.kohler.entity.TemplateEntity;
import com.kohler.exception.DataException;
import com.kohler.service.UrlAnalysisService;
import com.kohler.service.base.BaseCommon;
import com.kohler.service.base.BaseForCategory;
import com.kohler.service.base.BaseForProduct;
import com.kohler.service.templatename.ProductDataFilenameParse;

/**
 * Class Function Description
 *
 * @author Administrator
 * @Date 2014年11月18日
 */
@Component
public class ProductUrlAnalysis implements UrlAnalysisService  {

    @Autowired
    private ProductDataFilenameParse productDataFilenameParse;
    
    @Autowired
    private BaseForCategory baseForCategory;
    
    @Autowired
    private PublishFolderDao publishFolderDao;
    
    @Autowired
    private BaseForProduct baseForProduct;
    
    @Autowired
    private BaseCommon baseCommon;
    
    /**
     * {@inheritDoc}
     * @throws DataException 
     */
    @Override
    public String getUrl(Integer dataId, ConfPrepareData conf) throws DataException {
        
        Integer dataIdTmp = conf.getDataId();//缓存原始ID
        
        if(dataId != null && !"".equals(dataId)) {
            conf.setDataId(dataId);
        }
        
        //站点配置
        SiteSettingEntity siteSet = baseCommon.getSitePlatformSet(conf);//站点配置
        String publishBaseUrl = siteSet.getSiteDomainName();//站点domain
        
        //获取产品信息
        Map<String,Object> product =  baseForProduct.getProductInfoWithMap(conf);
        
        //获取发布目录
        Integer categoryMetadataId = (Integer)product.get("CATEGORY_METADATA_ID");
        String publishFolder = baseForCategory.getCategoryPublishFolder(categoryMetadataId, new StringBuilder(), conf);
        
        //获取产品的模板
        TemplateEntity templateEntity = baseCommon.getTemplateByMap(product, conf);
        //产品目录
        Integer productFolderId = templateEntity.getPublishFolderId();
        //获取产品路径
        PublishFolderEntity publishFolderEntity = publishFolderDao.selectById(productFolderId);
        String productFolder = publishFolderEntity.getPublishFolderPath();//具体路径
        
        //命名规则转换
        String generalUrl = templateEntity.getGenerateUrl();//实际url规则
        generalUrl = productDataFilenameParse.getGoalName(generalUrl, conf); 
        
        //发布路径
        String publishPath = publishBaseUrl + publishFolder + productFolder;
        
        //文件url
        StringBuffer url = new StringBuffer(publishPath);
        url.append(generalUrl);
        
        conf.setDataId(dataIdTmp);//ID复位
        return url.toString();
    }

}
