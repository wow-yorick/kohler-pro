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
import com.kohler.dao.PublishFolderDao;
import com.kohler.dao.TemplateDao;
import com.kohler.entity.PublishFolderEntity;
import com.kohler.entity.SiteSettingEntity;
import com.kohler.entity.TemplateEntity;
import com.kohler.service.PublishStrategyService;
import com.kohler.service.base.BaseCommon;
import com.kohler.service.base.BaseForProduct;
import com.kohler.service.templatename.ProductDataFilenameParse;
import com.kohler.util.GenerateHtml;
import com.kohler.util.JSonUtil;

/**
 * product sku js file 
 *
 * @author Administrator
 * @Date 2014年12月6日
 */
@Component
public class ProductSKUjsFileDataPublishStrategy implements PublishStrategyService {
    
    private final static Logger logger = Logger.getLogger(ProductDataPublishStrategy.class);
    
    @Value("#{settings['file.website.dir']}")
    private String websiteDir;
    
    @Value("#{settings['file.resources.dir']}")
    private String resourcesDir;
    
    @Value("#{settings['file.velocity.dataConfig.dir']}")
    private String preDataConfig;    
    
    @Value("#{settings['file.velocity.template.dir']}")
    private String velocityDir;
    
    @Autowired
    private TemplateDao templateDao;
    
    @Autowired
    private PublishFolderDao publishFolderDao;
    
    @Autowired
    private BaseForProduct baseForProduct;
    
    @Autowired
    private BaseCommon baseCommon;
    
    @Autowired
    private ProductDataFilenameParse productDataFilenameParse;
    

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Object> publishMethod(ConfPrepareData confPrepareData, Map<String, Object> preData) throws Exception {
        
        Map<String, Object> retMap = new HashMap<String, Object>();//返回结果集
        //try {
            //站点配置
            SiteSettingEntity siteSet = baseCommon.getSitePlatformSet(confPrepareData);//站点配置
            String publishBaseDir = siteSet.getSitePath();//发布物理路径
            
            //数据准备
            VelocityContext context =  new VelocityContext();
            String preData_json = JSonUtil.toJSonString(preData);
            context.put("data", preData_json);//准备的数据
            logger.debug("result--"+JSonUtil.toJSonString(preData));
            
            //获取产品信息
            Map<String,Object> product =  baseForProduct.getProductInfoWithMap(confPrepareData);
            
            Integer jsfileTemplateId = (Integer) product.get("DATA_TEMPLATE_ID"); 
            
            //获取skujsFile模板
            TemplateEntity templateEntity = templateDao.selectById(jsfileTemplateId);
            
            //js 文件发布目录id
            Integer skujspublishFolderId = templateEntity.getPublishFolderId();
            //获取js路径
            PublishFolderEntity publishFolderEntity = publishFolderDao.selectById(skujspublishFolderId);
            String jsFolder = publishFolderEntity.getPublishFolderPath();//具体路径
            
            //命名规则转换
            String generalName = templateEntity.getGenerateName();//发布文件名
            generalName = productDataFilenameParse.getGoalName(generalName, confPrepareData);
            
            String pathDir = jsFolder; //中间路径
            
            //发布路径
            String publishDir = publishBaseDir + pathDir;
            
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
            String publishUrl = publishBaseUrl + pathDir + generalName;//访问url
            
            retMap.put("success", true);
            retMap.put("publishUrl", publishUrl);
            
//        } catch (Exception e) {
//            String msg = "product sku js file publish fail! id:"+confPrepareData.getDataId();
//            retMap.put("success", false);
//            retMap.put("publishUrl", "");
//            retMap.put("msg", msg);
//            logger.warn(msg);
//            throw new DataException(e);
//        }
        
        return retMap;
    }

}
