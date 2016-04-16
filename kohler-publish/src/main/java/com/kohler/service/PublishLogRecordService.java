/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service;

import com.kohler.entity.PublishLogEntity;

/**
 * 发布日志记录
 *
 * @author Administrator
 * @Date 2014年11月15日
 */
public interface PublishLogRecordService {
    /**
     * 更新发布信息
     * @param publishLogEntity
     * @return
     * @author Administrator
     * Date 2014年11月15日
     * @version
     */
    public Integer update(PublishLogEntity publishLogEntity);
    
    /**
     * 插入条锁定记录
     * @param publishLogEntity
     * @return
     * @author Administrator
     * Date 2014年11月15日
     * @version
     */
    public Integer insert(PublishLogEntity publishLogEntity);
    
    /**
     * 获取最新的发布锁定记录
     * @return
     * @author Administrator
     * Date 2014年11月17日
     * @version
     */
    public PublishLogEntity getLastLocked();
    
    /**
     * 获取最新开启状态的记录
     * @return
     * @author Administrator
     * Date 2014年11月27日
     * @version
     */
    public PublishLogEntity getLastOpend();
    
    /**
     * 获取已经发布的版本记录数
     * @return
     * @author Administrator
     * Date 2014年12月30日
     * @version
     */
    public int getHasPublishedLength();
}
