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
import com.kohler.dao.PageDao;
import com.kohler.dao.TemplateDao;
import com.kohler.entity.PageEntity;
import com.kohler.entity.SiteSettingEntity;
import com.kohler.entity.TemplateEntity;
import com.kohler.service.PublishStrategyService;
import com.kohler.service.base.BaseCommon;
import com.kohler.service.base.BaseForSection;
import com.kohler.util.GenerateHtml;
import com.kohler.util.JSonUtil;

/**
 * page publish strategy
 *
 * @author Administrator
 * @Date 2014年10月27日
 */
@Component
public class PageDataPublishStrategy implements PublishStrategyService {
    
    private final static Logger logger = Logger.getLogger(PageDataPublishStrategy.class);
    
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
    private BaseForSection baseForSection;
    
    @Autowired
    private TemplateDao templateDao;

    @Autowired
    private PageDao pageDao;


    /**
     * {@inheritDoc}
     * @throws Exception 
     */
    public Map<String, Object> publishMethod(ConfPrepareData confPrepareData, Map<String,Object> preData) throws Exception {
        Map<String, Object> retMap = new HashMap<String, Object>();//返回结果集
        
        //try {
            //站点配置信息
            SiteSettingEntity siteSet = baseCommon.getSitePlatformSet(confPrepareData);;
            String publishDir = siteSet.getSitePath();//站点发布目录
            
            //数据准备
            VelocityContext context =  new VelocityContext();
            context.put("data", preData);
            logger.debug("result--"+JSonUtil.toJSonString(preData));
            
            //获取页面信息
            PageEntity pageEntity = pageDao.selectById(confPrepareData.getDataId());
            //获取发布路径
            String publishFolder = baseForSection.getPublishFolderFromSection(pageEntity.getSectionId());
            
            //发布完整路径
            String fullPublishDir = publishDir+publishFolder; 
            baseCommon.makeCascadeDir(fullPublishDir);//创建发布路径
            
            //获取模板信息
            TemplateEntity templateEntity = templateDao.selectById(pageEntity.getTemplateId());
            String velocityTemplateDir = websiteDir + velocityDir;//velocity存放目录
            
            StringBuffer filep = new StringBuffer(fullPublishDir);    
            filep.append(pageEntity.getPhysicalName());
            
            GenerateHtml.generateHtml(velocityTemplateDir, templateEntity.getPhysicalName(),filep.toString(), context);
            
            //结果
            String publishBaseUrl = siteSet.getSiteDomainName();//发布站点url
            String publishUrl = publishBaseUrl + publishFolder + pageEntity.getPhysicalName();//访问url

            retMap.put("success", true);
            retMap.put("publishUrl", publishUrl);
            
//        } catch (Exception e) {
//            String msg = "page publish fail id:"+confPrepareData.getDataId();
//            retMap.put("success", false);
//            retMap.put("publishUrl", "");
//            retMap.put("msg", "product detail publish fail!");
//            logger.warn(msg);
//            throw new DataException(e);
//        }
        
        return retMap;
    }
    

}
