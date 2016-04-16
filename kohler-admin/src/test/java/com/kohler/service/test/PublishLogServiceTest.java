package com.kohler.service.test;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.kohler.dao.utils.Pager;
import com.kohler.entity.PublishLogEntity;
import com.kohler.entity.extend.PublishLogPojo;
import com.kohler.service.PublishLogService;

public class PublishLogServiceTest {
	
	@Autowired
	private PublishLogService logService;
	
	 @Test
    public void getPublishLogByMap() {
	 Pager p = new Pager();
	 PublishLogPojo pojo=new PublishLogPojo();
	 pojo.setBeginDate("2014-11-11");
	 Pager<Map<String,Object>> pager = logService.getPublishLogByMap(p, pojo);
	 assertEquals(pager,null);
    }
	 
	 @Test
	public void getPublishLogById() {
		PublishLogEntity publishLog=new PublishLogEntity();
		publishLog.setPublishLogId(2);
		PublishLogEntity publishLogs = logService.getPublishLogById(publishLog);
		assertEquals(publishLogs,null);
	}
	 @Test
	 public void editPublishLog() {
			Integer publishLogId=2;
			String remark="12121212";
			logService.editPublishLog(publishLogId,remark);
		}

}
