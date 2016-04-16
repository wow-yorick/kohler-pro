package com.kohler.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kohler.bean.ConfPrepareData;
import com.kohler.constants.CommonConstants;
import com.kohler.dao.MasterDataCodeDao;
import com.kohler.dao.PublishLogDao;
import com.kohler.dao.SysLogDao;
import com.kohler.dao.utils.Pager;
import com.kohler.entity.MasterDataCodeConstant;
import com.kohler.entity.PublishLogEntity;
import com.kohler.entity.extend.PublishLogPojo;
import com.kohler.service.ContentDataService;
import com.kohler.service.PublishLogService;
import com.kohler.service.publish.BatchPublishForManualSinglePlatform;

@Service
public class PublishLogServiceImpl implements PublishLogService {
	@Autowired
	public PublishLogDao publishLogDao;

	@Autowired
	private MasterDataCodeDao masterDao;

	@Autowired
	public SysLogDao sysLogDao;
	
	@Autowired
	private ContentDataService contentDataService;
	
	@Autowired
	private BatchPublishForManualSinglePlatform catchPublishForManual;
	
	@Value("#{settings['backup.data.dir']}")
	private String backupDataDir;
	    

	@Override
	public Pager<Map<String, Object>> getPublishLogByMap(
			Pager<Map<String, Object>> pager, PublishLogPojo pojo) {
		List<Object> queryParams = new ArrayList<Object>();
		StringBuilder querySql = new StringBuilder(
				" select publishLog.*,masterData.MASTERDATA_NAME as statusName from PUBLISH_LOG publishLog "
						+ " inner join MASTERDATA masterData on publishLog.PUBLISH_STATUS=masterData.MASTERDATA_METADATA_ID ");

		StringBuilder countSql = new StringBuilder(
				" select count(*) from PUBLISH_LOG publishLog "
						+ " inner join MASTERDATA masterData on publishLog.PUBLISH_STATUS=masterData.MASTERDATA_METADATA_ID ");

		querySql.append(" where publishLog.IS_ENABLE=1 and masterData.IS_ENABLE=1 and masterData.LANG=1");
		countSql.append(" where publishLog.IS_ENABLE=1 and masterData.IS_ENABLE=1 and masterData.LANG=1");

		Object beginDate = pojo.getBeginDate();
		Object endDate = pojo.getEndDate();

		if (beginDate != null && beginDate != "") {
			querySql.append(" and date_format(publishLog.PUBLISH_TIME,'%Y-%m-%d') >= ? ");
			countSql.append(" and date_format(publishLog.PUBLISH_TIME,'%Y-%m-%d') >= ?");
			queryParams.add(beginDate.toString());
		}
		if (endDate != null && endDate != "") {
			querySql.append(" and ? >= date_format(publishLog.PUBLISH_TIME,'%Y-%m-%d') ");
			countSql.append(" and ? >= date_format(publishLog.PUBLISH_TIME,'%Y-%m-%d') ");
			queryParams.add(endDate.toString());
		}
		if (pojo.getPublishStatus() != null) {
			querySql.append(" and publishLog.PUBLISH_STATUS = ? ");
			countSql.append(" and publishLog.PUBLISH_STATUS = ? ");
			queryParams.add(pojo.getPublishStatus());
		}
		querySql.append(" limit ?,?");

		Integer totalCount = publishLogDao.selectCountByCondition(
				countSql.toString(), queryParams);
		int pageCount = 0;
		if (totalCount % pager.getPageSize() == 0) {
			pageCount = totalCount / pager.getPageSize();
		} else {
			pageCount = totalCount / pager.getPageSize() + 1;
		}

		int index = (pager.getCurrentPage() - 1) * pager.getPageSize();
		queryParams.add(index);
		queryParams.add(pager.getPageSize());
		List<Map<String, Object>> list = publishLogDao
				.selectByConditionWithMap(querySql.toString(), queryParams);

		pager.setStartRow(index);
		pager.setDatas(list);
		pager.setTotalRecord(totalCount);
		pager.setTotalPage(pageCount);
		return pager;
	}

	@Override
	public PublishLogEntity getPublishLogById(PublishLogEntity publishLog) {
		Integer publishLogId = publishLog.getPublishLogId();
		PublishLogEntity publishLogs = publishLogDao.selectById(publishLogId);
		if (publishLogs == null) {
			return null;
		}
		return publishLogs;
	}

	@Override
	
	public boolean editPublishLog(Integer publishLogId, String remark) {
		
		boolean isSuccess = false;
		
		ConfPrepareData conf = new ConfPrepareData();
        conf.setLang(CommonConstants.LOCALE_CN);
        conf.setIsPreview(false);
        conf.setWebsitePlatform(CommonConstants.PC_PLATFORM);
		Map<String,Object> result = catchPublishForManual.start(conf);
		
		if(result.containsKey("success")){
			if(result.get("success").equals(true)){
				PublishLogEntity publishLogs = publishLogDao.selectById(publishLogId);
				publishLogs.setRemark(remark);
//				publishLogs.setIsBackupSucc(true);
//				publishLogs.setPublishStatus(MasterDataCodeConstant.PUBLISH_STATUS_PUBLISHED);
//				publishLogs.setPublishTime(new Date());
				publishLogs.setIsEnable(true);
				publishLogDao.update(publishLogs);
				sysLogDao.insertLogForUpdate(publishLogId, "PUBLISH_LOG");
				isSuccess = true; 
			}
		}
		
		return isSuccess;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Map<String, Object>> getAllType(Integer type, Integer lang) {
		List<Object> MasterData = new ArrayList<Object>();
		MasterData.add(type);// 所需数据常量
		MasterData.add(lang);// 所需数据语言
		return masterDao.getAllTypeByMasterData(MasterData);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean lockOpenPlishById(Integer publishLogId) {

		boolean result = false;

		PublishLogEntity publish = publishLogDao.selectById(publishLogId);
		
//		if(!"".equals(publish.getBackupSuffix())){
//			contentDataService.restoreData(publish.getBackupSuffix());
//		}
		
		publish.setPublishStatus(MasterDataCodeConstant.PUBLISH_STATUS_LOCKED);
		int row = publishLogDao.update(publish);
		if (row > 0) {
			result = true;
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean unlockPlishById(Integer publishLogId) {
		
		boolean result = false;
		
		PublishLogEntity publish = publishLogDao.selectById(publishLogId);
		
		publish.setPublishStatus(MasterDataCodeConstant.PUBLISH_STATUS_OPEN);
		int row = publishLogDao.update(publish);
		if (row > 0){
			result =true;
		}
		return result;
	}


	
	@Override
	public Map<String,Object> backupData() {
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		boolean isSuccess = true;
		
		Integer tableNamesType = MasterDataCodeConstant.TYPE_CONTENT_TABLE;
		Integer lang = 1;
		List<Map<String, Object>> tableNames = getAllType(tableNamesType, lang);
		File fileDir = new File(backupDataDir);
		if(!fileDir.exists()){
			fileDir.mkdir();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String sysDateStr = sdf.format(new Date());
		StringBuffer finalDir = new StringBuffer();
		String suffix = ".bat";
		String tableName = "";
		
		String returnStr = "Suffix_"+sysDateStr;
		for(Map<String, Object> mapDir : tableNames){
			if(mapDir.containsKey("masterdataName")){
				tableName = mapDir.get("masterdataName").toString();
				finalDir.append(backupDataDir);
				finalDir.append(tableName);
				finalDir.append(sysDateStr);
				finalDir.append(suffix);
				
				File finalFile = new File(finalDir.toString());
				if(fileDir.exists()){
					finalFile.delete();
				}
				
				String sql = "SELECT * FROM "+tableName+" INTO OUTFILE'"+finalDir.toString()+"' FIELDS  TERMINATED BY ',' OPTIONALLY ENCLOSED BY '\"' LINES TERMINATED BY '\r\n' ";
				try {
					publishLogDao.executeSql(sql);
					finalDir.delete(0,finalDir.length()); 
				} catch (Exception e) {
					isSuccess = false;
					e.printStackTrace();
				}finally{
					result.put("isSuccess", isSuccess);
				}
			}
		}
		
		result.put("suffix", returnStr);
		
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int checkpublishById(Integer publishLogId) {
		
		int check = 0;
		
		List<Object> params = new ArrayList<Object>();
		
		params.add(MasterDataCodeConstant.PUBLISH_STATUS_PUBLISHED);
		
		PublishLogEntity checkPublish = publishLogDao.getCheckPublish(params);
		
		if(checkPublish != null&&checkPublish.getPublishLogId().intValue() == publishLogId.intValue()){
			check = 1;
		}else{
			check = 2;
		}
		
		return check;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean rollBackById(Integer publishLogId) {
		
		boolean result = false;
		PublishLogEntity publishLog = publishLogDao.selectById(publishLogId);
		
		if(!"".equals(publishLog.getBackupSuffix())){
			//rollback data
			contentDataService.restoreData(publishLog.getBackupSuffix());
			
			//rollback publishlog
			List<Object> params = new ArrayList<Object>();
			
			params.add(MasterDataCodeConstant.PUBLISH_STATUS_OPEN);
			PublishLogEntity oldPublishLog = publishLogDao.getOpenPublish(params);
			if(oldPublishLog != null){
				publishLogDao.delete(oldPublishLog);
				publishLog.setPublishStatus(MasterDataCodeConstant.PUBLISH_STATUS_OPEN);
				publishLog.setIsBackupSucc(true);
				publishLog.setIsEnable(true);
				publishLogDao.update(publishLog);
				result = true;
			}
			
		}
		
		return result;
	}

}
