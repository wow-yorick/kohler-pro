package com.kohler.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohler.dao.SysLogDao;
import com.kohler.entity.SysLog;
import com.kohler.service.SysLogService;

@Service
public class SysLogServiceImpl implements SysLogService {

	@Autowired 
	public SysLogDao sysLogDao;

	@Override
	public Integer addSysLog(SysLog sysLog) {
		return sysLogDao.insert(sysLog);
	}


	

}
