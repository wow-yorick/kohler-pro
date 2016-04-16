/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.service;

import com.kohler.entity.SysLogEntity;

/**
 * SysLog Interface
 *
 * @author shefeng
 * @Date 2014年9月25日
 */
public interface SysLogService {

    /**
     * @param sysLog
     * @return
     * @author shefeng Date 2014年9月27日
     * @version
     */
    public Integer addSysLog(SysLogEntity sysLog);

}
