/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.publish;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohler.bean.ConfPrepareData;
import com.kohler.constants.CommonConstants;
import com.kohler.entity.PublishLogEntity;
import com.kohler.service.BatchPublishService;
import com.kohler.service.PublishLogRecordService;

/**
 * 批量发布自动
 *
 * @author Administrator
 * @Date 2014年11月17日
 */

@Service
public class BatchPublishForAutoSinglePlatform implements BatchPublishService {
    
    @Autowired
    private BatchPublishForManualSinglePlatform batchPublishForManual;
    
    @Autowired
    private PublishLogRecordService publishLogService;


    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Object> start(ConfPrepareData conf) {
        //检查锁定状态的记录 如果没有则自动锁定一条记录
        PublishLogEntity pLogEntity = publishLogService.getLastLocked();
        if(pLogEntity == null) {
            PublishLogEntity pLogEntityOpen = publishLogService.getLastOpend();
            pLogEntityOpen.setPublishStatus(CommonConstants.PUBLISH_STATUS_LOCKED);//自动改为锁定
            publishLogService.update(pLogEntityOpen);
        }
        Map<String, Object> retMap = batchPublishForManual.start(conf);
        return retMap;
    }
    
    
}
