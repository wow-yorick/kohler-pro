/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohler.constants.CommonConstants;
import com.kohler.dao.PublishLogDao;
import com.kohler.entity.PublishLogEntity;
import com.kohler.service.PublishLogRecordService;

/**
 * 发布日志记录
 *
 * @author Administrator
 * @Date 2014年11月15日
 */
@Service
public class PublishLogRecordServiceImpl implements PublishLogRecordService {
    
    @Autowired
    private PublishLogDao publishLogDao;

    /**
     * {@inheritDoc}
     */
    public Integer update(PublishLogEntity publishLogEntity) {
        Integer retStu = publishLogDao.update(publishLogEntity);
        return retStu;
    }

    /**
     * {@inheritDoc}
     */
    public Integer insert(PublishLogEntity publishLogEntity) {
        Integer retStu = publishLogDao.insert(publishLogEntity);
        return retStu;
    }

    /**
     * {@inheritDoc}
     */
    public PublishLogEntity getLastLocked() {
        PublishLogEntity pLogEntity = new PublishLogEntity();
        pLogEntity.setIsEnable(true);
        pLogEntity.setPublishStatus(CommonConstants.PUBLISH_STATUS_LOCKED);
        List<PublishLogEntity> pLogEntities = publishLogDao.selectByCondition(pLogEntity);
        if(pLogEntities != null && pLogEntities.size() > 0) {
            return pLogEntities.get(0);            
        } else {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    public PublishLogEntity getLastOpend() {
        PublishLogEntity pLogEntity = new PublishLogEntity();
        pLogEntity.setIsEnable(true);
        pLogEntity.setPublishStatus(CommonConstants.PUBLISH_STATUS_OPEN);
        List<PublishLogEntity> pLogEntities = publishLogDao.selectByCondition(pLogEntity);
        if(pLogEntities != null && pLogEntities.size() > 0) {
            return pLogEntities.get(0);            
        } else {
            return null;
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public int getHasPublishedLength() {
        PublishLogEntity pLogEntity = new PublishLogEntity();
        pLogEntity.setIsEnable(true);
        pLogEntity.setPublishStatus(CommonConstants.PUBLISH_STATUS_PUBLISHED);
        List<PublishLogEntity> pLogEntities = publishLogDao.selectByCondition(pLogEntity);
        if(pLogEntities != null && pLogEntities.size() > 0) {
            return pLogEntities.size();
        } else {
            return 0;
        }
    }

}
