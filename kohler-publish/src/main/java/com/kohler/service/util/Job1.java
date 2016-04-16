package com.kohler.service.util;


import org.springframework.beans.factory.annotation.Autowired;

import com.kohler.service.BatchPublishAllService;


public class Job1 {
	@Autowired
	private BatchPublishAllService batchPublishAll;
	public void doJob2() {  
		System.out.println("定时任务执行 中...");  
		//调用业务方法
		batchPublishAll.start();
	}  

}
