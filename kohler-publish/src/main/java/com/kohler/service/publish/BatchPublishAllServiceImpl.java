/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.publish;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohler.bean.ConfPrepareData;
import com.kohler.constants.CommonConstants;
import com.kohler.entity.PublishLogEntity;
import com.kohler.service.BatchPublishAllService;
import com.kohler.service.PublishLogRecordService;
import com.kohler.service.SolrReindexService;
import com.kohler.service.StaticResourceCopyService;

/**
 * batch publish all
 *TODO 移除注释开放数据库备份 和solr 索引重建
 * @author Administrator
 * @Date 2014年12月17日
 */
@Service
public class BatchPublishAllServiceImpl implements BatchPublishAllService {
    
    private final static Logger logger = Logger.getLogger(BatchPublishAllServiceImpl.class);
    
    @Autowired
    private SolrReindexService solrReindexService;

    @Autowired
    private BatchPublishBaseLoop batchPublishBaseLoop;
    
    @Autowired
    private PublishLogRecordService publishLogService;
    
    @Autowired
    private StaticResourceCopyService staticResourceCopyService;
    
//  @Autowired
//  private ContentDataService contentDataService;
  
    private int publishStatus = CommonConstants.PUBLISH_STATUS_OPEN;//发布状态
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Object> start() {
        Map<String, Object> retMap = new HashMap<String, Object>();
        retMap.put("success", false);
        
        try {
            //solrReindexService.run();
           //备份
//      Map<String,Object> backupResult = contentDataService.backupData();
//      if(backupResult.containsKey("isSuccess") && !(Boolean)backupResult.get("isSuccess")){
//          retMap.put("success", success);
//          retMap.put("msg", msg);
//          return retMap;
//      }
            
            //检查锁定状态的记录 如果没有则自动锁定一条记录
            PublishLogEntity pLogEntity = publishLogService.getLastLocked();
            if(pLogEntity == null) {
                PublishLogEntity pLogEntityOpen = publishLogService.getLastOpend();
                pLogEntityOpen.setPublishStatus(CommonConstants.PUBLISH_STATUS_LOCKED);//自动改为锁定
                publishLogService.update(pLogEntityOpen);
            }
            
            //EN/PC
            ConfPrepareData conf_en_pc = new ConfPrepareData();
            conf_en_pc.setLang(CommonConstants.LOCALE_EN);
            conf_en_pc.setWebsitePlatform(CommonConstants.PC_PLATFORM);
            conf_en_pc.setIsPreview(false);
            Map<String, Object> retMapEn = batchPublishBaseLoop.start(conf_en_pc);
            retMap.put("url_pc_en", retMapEn.get("publishUrl"));
            retMap.put("success_pc_en", retMapEn.get("success"));
            retMap.put("msg_pc_en", retMapEn.get("msg"));
            retMap.put("log_pc_en", retMapEn.get("log"));
            
            //CN/PC
            ConfPrepareData conf_cn_pc = new ConfPrepareData();
            conf_cn_pc.setLang(CommonConstants.LOCALE_CN);
            conf_cn_pc.setWebsitePlatform(CommonConstants.PC_PLATFORM);
            conf_cn_pc.setIsPreview(false);
            Map<String, Object> retMapCn = batchPublishBaseLoop.start(conf_cn_pc);
            retMap.put("url_pc_cn", retMapCn.get("publishUrl"));
            retMap.put("success_pc_cn", retMapCn.get("success"));
            retMap.put("msg_pc_cn", retMapCn.get("msg"));
            retMap.put("log_pc_cn", retMapCn.get("log"));
            
            publishStatus = CommonConstants.PUBLISH_STATUS_PUBLISHED;//已发布
            
            //静态资源拷贝
            ConfPrepareData copyConf = new ConfPrepareData();
            copyConf.setIsPreview(false);
            staticResourceCopyService.copy(copyConf);
            
            //日志记录
            PublishLogEntity pLogEntityLock = publishLogService.getLastLocked();
            pLogEntityLock.setPublishType(CommonConstants.AUTO_PUBLISH);//自动发布
            pLogEntityLock.setPublishStatus(publishStatus);//发布状态
            pLogEntityLock.setIsBackupSucc(true);
            //pLogEntityLock.setBackupSuffix(backupResult.get("suffix").toString());
            
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String dfdate = df.format(new Date());
            Date publishtime = df.parse(dfdate);
            
            pLogEntityLock.setPublishTime(publishtime);
            
            pLogEntityLock.setRemark(retMap.get("log").toString());
            publishLogService.update(pLogEntityLock);//更新发布状态
            
            if(publishStatus == CommonConstants.PUBLISH_STATUS_PUBLISHED) {//如果发布成功打开一个新的发布记录 
                PublishLogEntity publishLogEntity = new PublishLogEntity();
                publishLogEntity.setStartTime(publishtime);//发布时间
                publishLogEntity.setVersionId(pLogEntityLock.getVersionId() + 1);//版本加一
                publishLogEntity.setPublishStatus(CommonConstants.PUBLISH_STATUS_OPEN);//发布开启
                publishLogService.insert(publishLogEntity);
                
            }
            
        } catch (Exception e) {
            logger.warn("batch publish fail",e);
        }
        
        return retMap;
    }

}
