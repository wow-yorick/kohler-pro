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
import com.kohler.service.BatchPublishService;
import com.kohler.service.PublishLogRecordService;
import com.kohler.service.StaticResourceCopyService;

/**
 * 批量发布手动
 *
 * @author Administrator
 * @Date 2014年11月27日
 */
@Service
public class BatchPublishForManualSinglePlatform implements BatchPublishService {
    
    private final static Logger logger = Logger.getLogger(BatchPublishForManualSinglePlatform.class);
    
    @Autowired
    private BatchPublishBaseLoop batchPublishBaseLoop;
    
    @Autowired
    private PublishLogRecordService publishLogService;

    @Autowired
    private StaticResourceCopyService staticResourceCopyService;
    
//    @Autowired
//    private ContentDataService contentDataService;
    
    private int publishStatus = CommonConstants.PUBLISH_STATUS_OPEN;//发布状态


    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Object> start(ConfPrepareData conf) {
        
        Map<String, Object> retMap = new HashMap<String, Object>();
        retMap.put("success", false);
        
        //备份
//        Map<String,Object> backupResult = contentDataService.backupData();
//        if(backupResult.containsKey("isSuccess") && !(Boolean)backupResult.get("isSuccess")){
//            retMap.put("success", success);
//            retMap.put("msg", msg);
//            return retMap;
//        }
        try {
                
            retMap = batchPublishBaseLoop.start(conf);

            publishStatus = CommonConstants.PUBLISH_STATUS_PUBLISHED;//已发布
                
            //静态资源拷贝
            staticResourceCopyService.copy(conf);
            
            //日志记录
            PublishLogEntity pLogEntity = publishLogService.getLastLocked();
            pLogEntity.setPublishType(CommonConstants.AUTO_PUBLISH);//自动发布
            pLogEntity.setPublishStatus(publishStatus);//发布状态
            pLogEntity.setIsBackupSucc(true);
            //pLogEntity.setBackupSuffix(backupResult.get("suffix").toString());
            
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String dfdate = df.format(new Date());
            Date publishtime = df.parse(dfdate);
           
            pLogEntity.setPublishTime(publishtime);
            
            pLogEntity.setRemark(retMap.get("log").toString());
            publishLogService.update(pLogEntity);//更新发布状态
            
            if(publishStatus == CommonConstants.PUBLISH_STATUS_PUBLISHED) {//如果发布成功打开一个新的发布记录 
                PublishLogEntity publishLogEntity = new PublishLogEntity();
                publishLogEntity.setStartTime(publishtime);//发布时间
                publishLogEntity.setVersionId(pLogEntity.getVersionId() + 1);//版本加一
                publishLogEntity.setPublishStatus(CommonConstants.PUBLISH_STATUS_OPEN);//发布开启
                publishLogService.insert(publishLogEntity);
                
            }
            
            
        } catch (Exception e) {
            logger.debug("batch publish fail",e);
        }
        
        return retMap;
        
    }
    
}
