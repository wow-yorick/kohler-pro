/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.strategy;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.kohler.bean.ConfPrepareData;
import com.kohler.entity.SiteSettingEntity;
import com.kohler.entity.TemplateEntity;
import com.kohler.exception.DataException;
import com.kohler.service.PublishStrategyService;
import com.kohler.service.base.BaseCommon;
import com.kohler.service.base.BaseForCategory;
import com.kohler.util.GenerateHtml;
import com.kohler.util.JSonUtil;

/**
 * product list publish strategy
 *
 * @author Administrator
 * @Date 2014年10月27日
 */
@Component
public class ProductListDataPublishStrategy implements PublishStrategyService {

    private final static Logger logger = Logger.getLogger(ProductListDataPublishStrategy.class);
    
    @Value("#{settings['file.website.dir']}")
    private String websiteDir;
    
    @Value("#{settings['file.resources.dir']}")
    private String resourcesDir;
    
    @Value("#{settings['file.velocity.dataConfig.dir']}")
    private String preDataConfig;    
    
    @Value("#{settings['file.velocity.template.dir']}")
    private String velocityDir;
    
    @Autowired
    private BaseCommon baseCommon;
    
    @Autowired
    private BaseForCategory baseForCategory;
    
    

    /**
     * {@inheritDoc}
     * @throws DataException 
     */
    public Map<String, Object> publishMethod(ConfPrepareData confPrepareData, Map<String,Object> preData) throws Exception {
        
        Map<String, Object> retMap = new HashMap<String, Object>();
        
        //try {
            //站点配置
            SiteSettingEntity siteSet = baseCommon.getSitePlatformSet(confPrepareData);//站点配置
            String publishBaseDir = siteSet.getSitePath();//发布物理路径
            
            //数据准备
            VelocityContext context =  new VelocityContext();
            context.put("data", preData);//准备的数据
            logger.debug("result--"+JSonUtil.toJSonString(preData));
            
            //获取发布目录
            String publishFolder = baseForCategory.getCategoryPublishFolder(confPrepareData.getDataId(), new StringBuilder(), confPrepareData);
            //模板信息
            TemplateEntity templateEntity = baseForCategory.getTemplateInfoByCategoryMetadataId(confPrepareData.getDataId(), confPrepareData);
            String generalName = templateEntity.getGenerateName();//发布文件名
            
            //发布路径
            String publishDir = publishBaseDir + publishFolder;
            
            //创建发布路径
            baseCommon.makeCascadeDir(publishDir);
            
            //html文件生成路径
            StringBuffer filePath = new StringBuffer(publishDir);
            filePath.append(generalName);
            //velocity存放目录
            String velocityTemplateDir = websiteDir + velocityDir;
            
            GenerateHtml.generateHtml(velocityTemplateDir, templateEntity.getPhysicalName(),filePath.toString(), context);
            
            //结果
            String publishBaseUrl = siteSet.getSiteDomainName();//发布站点url
            String publishUrl = publishBaseUrl + publishFolder + generalName;//访问url
            
            retMap.put("success", true);
            retMap.put("publishUrl", publishUrl);
            
//        } catch (Exception e) {
//            String msg = "category publish fail! id: " + confPrepareData.getDataId();
//            logger.warn(msg);
//            retMap.put("success", false);
//            retMap.put("msg", msg);
//            throw new DataException(e);
//        }
        
        return retMap;

    }
    
    
   
}
