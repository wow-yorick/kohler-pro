package com.kohler.service;

import java.util.List;
import java.util.Map;

import com.kohler.dao.utils.Pager;
import com.kohler.entity.PublishLogEntity;
import com.kohler.entity.extend.PublishLogPojo;

public interface PublishLogService {
	public Pager<Map<String,Object>> getPublishLogByMap(Pager<Map<String,Object>> pager,
			PublishLogPojo pojo);
	
	public PublishLogEntity getPublishLogById(PublishLogEntity publishLog);
	
	public boolean editPublishLog(Integer publishLogId,String remark);

	/**
	 * @param type
	 * @param lang
	 * @return
	 * @author XHY
	 * Date 2014年11月27日
	 * @version
	 */
	public List<Map<String, Object>> getAllType(Integer type, Integer lang);

	/**
	 * 
	 * @param publishLogId
	 * @return
	 * @author XHY
	 * Date 2014年11月28日
	 * @version
	 */
	public boolean lockOpenPlishById(Integer publishLogId);

	/**
	 * 
	 * @param publishLogId
	 * @return
	 * @author 	XHY
	 * Date 2014年11月28日
	 * @version
	 */
	public boolean unlockPlishById(Integer publishLogId);
	
	/**
	 * 
	 * @return
	 * @author XHY
	 * Date 2014年11月28日
	 * @version
	 */
	public Map<String,Object> backupData();

	/**
	 * @param publishLogId
	 * @return
	 * @author XHY
	 * Date 2014年12月2日
	 * @version
	 */
	public int checkpublishById(Integer publishLogId);

	/**
	 * @param publishLogId
	 * @return
	 * @author XHY
	 * Date 2014年12月2日
	 * @version
	 */
	public boolean rollBackById(Integer publishLogId);

}
