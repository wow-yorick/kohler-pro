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

import com.kohler.bean.ConfPrepareData;
import com.kohler.dao.PublishFolderDao;
import com.kohler.entity.PublishFolderEntity;
import com.kohler.entity.SiteSettingEntity;
import com.kohler.entity.TemplateEntity;
import com.kohler.exception.DataException;
import com.kohler.service.base.BaseCommon;
import com.kohler.util.GenerateHtml;
import com.kohler.util.JSonUtil;

/**
 * 发布用的公共模板基类 抽象类
 *
 * @author Administrator
 * @Date 2014年12月11日
 */
abstract class PublishStrategyAbst {
    
    protected final static Logger logger = Logger.getLogger(SuiteDataPublishStrategy.class);
     
    @Value("#{settings['file.website.dir']}")
    private String websiteDir;
       
    @Value("#{settings['file.velocity.template.dir']}")
    private String velocityDir;
     
    @Autowired
    private BaseCommon baseCommon;
     
    @Autowired
    protected PublishFolderDao publishFolderDao;
 
    /**
     * 获取需要的数据
     * @param conf
     * @return
     * @author Administrator
     * Date 2014年12月11日
     * @version
     */
    protected abstract Map<String, Object> getDataMap(ConfPrepareData conf);
    
    /**
     * 获取 错误信息
     * @return
     * @author Administrator
     * Date 2014年12月11日
     * @version
     */
    protected abstract String getErrorMsg(ConfPrepareData conf);
    
    /**
     * 获取 成功信息
     * @return
     * @author Administrator
     * Date 2014年12月11日
     * @version
     */
    protected abstract String getSuccessMsg(ConfPrepareData conf);
    
    /**
     * 获取目标名称的算法
     * @return
     * @author Administrator
     * Date 2014年12月11日
     * @version
     */
    protected abstract String getGoalNameArith(String generalName, ConfPrepareData conf);
    
    /**
     * 算法主方法
     * @param conf
     * @param preData
     * @return
     * @throws DataException
     * @author Administrator
     * Date 2014年12月25日
     * @version
     */
    public Map<String, Object> publishMethod(ConfPrepareData conf, Map<String, Object> preData) throws Exception {
        
        Map<String, Object> retMap = new HashMap<String, Object>();//返回结果集
        
        //try {
            
            //站点配置
            SiteSettingEntity siteSet = baseCommon.getSitePlatformSet(conf);//站点配置
            String publishBaseDir = siteSet.getSitePath();//发布物理路径
            
            //数据准备
            VelocityContext context =  new VelocityContext();
            context.put("data", preData);//准备的数据
            logger.debug("result--"+JSonUtil.toJSonString(preData));
            
            //get data map
            Map<String,Object> dataMap =  getDataMap(conf);
            
            //获取产品的模板
            TemplateEntity templateEntity = baseCommon.getTemplateByMap(dataMap, conf);
    
            //文件发布目录id
            Integer publishFolderId = templateEntity.getPublishFolderId();
            //获取路径
            PublishFolderEntity publishFolderEntity = publishFolderDao.selectById(publishFolderId);
            String publishFolder = publishFolderEntity.getPublishFolderPath();//具体路径
            
            String generalName = templateEntity.getGenerateName();//发布文件名
            generalName = getGoalNameArith(generalName, conf); //命名规则转换
            
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
            retMap.put("msg", getSuccessMsg(conf));
            retMap.put("publishUrl", publishUrl);
            
//        } catch (Exception e) {
//            String msg = getErrorMsg(conf);
//            retMap.put("success", false);
//            retMap.put("publishUrl", "");
//            retMap.put("msg", msg);
//            logger.warn(msg);
//            throw new DataException(e);
//        }
        
        return retMap;
    }

}
