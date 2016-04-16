/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohler.dao.SysLogDao;
import com.kohler.entity.SysLogEntity;
import com.kohler.service.SysLogService;

/**
 * SysLog Interface Impl
 *
 * @author shefeng
 * @Date 2014年9月25日
 */
@Service
public class SysLogServiceImpl implements SysLogService {

	@Autowired 
	public SysLogDao sysLogDao;

	@Override
	public Integer addSysLog(SysLogEntity sysLog) {
		return sysLogDao.insert(sysLog);
	}

}
