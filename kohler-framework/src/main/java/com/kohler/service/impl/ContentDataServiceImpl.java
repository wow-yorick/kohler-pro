/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
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

import com.kohler.constants.CommonConstants;
import com.kohler.dao.MasterDataCodeDao;
import com.kohler.dao.PublishLogDao;
import com.kohler.entity.MasterDataCodeConstant;
import com.kohler.service.ContentDataService;

/**
 * Class Function Description
 * 
 * @author XHY
 * @Date 2014年12月1日
 */
@Service
public class ContentDataServiceImpl implements ContentDataService {

	@Autowired
	public PublishLogDao publishLogDao;

	@Value("#{settings['backup.data.dir']}")
	private String backupDataDir;

	@Autowired
	private MasterDataCodeDao masterDao;

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
	public Map<String, Object> backupData() {
		Map<String, Object> result = new HashMap<String, Object>();

		boolean isSuccess = true;

		Integer tableNamesType = MasterDataCodeConstant.TYPE_CONTENT_TABLE;
		Integer lang = 1;
		List<Map<String, Object>> tableNames = getAllType(tableNamesType, lang);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String sysDateStr = sdf.format(new Date());
		StringBuffer finalDir = new StringBuffer();
		String suffix = CommonConstants.SUFFIX;
		String tableName = "";

		// use date dir
		backupDataDir += sysDateStr;
		backupDataDir += "/";
		File fileDir = new File(backupDataDir);
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}

		String returnStr = "Suffix_" + sysDateStr;
		for (Map<String, Object> mapDir : tableNames) {
			if (mapDir.containsKey("masterdataName")) {
				tableName = mapDir.get("masterdataName").toString();
				finalDir.append(backupDataDir);
				finalDir.append(tableName);
				finalDir.append(sysDateStr);
				finalDir.append(suffix);

				File finalFile = new File(finalDir.toString());
				if (finalFile.exists()) {
					finalFile.delete();
				}

				String sql = "SELECT * FROM "
						+ tableName
						+ " INTO OUTFILE'"
						+ finalDir.toString()
						+ "' FIELDS  TERMINATED BY ',' OPTIONALLY ENCLOSED BY '\"' LINES TERMINATED BY '\r\n' ";
				try {
					publishLogDao.executeSql(sql);
					finalDir.delete(0, finalDir.length());
				} catch (Exception e) {
					isSuccess = false;
					e.printStackTrace();
					break;
				} finally {
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
	public boolean restoreData(String backupSuffix) {
		boolean isSuccess = true;

		String time = CommonConstants.SUFFIX;
		if (backupSuffix.indexOf("_") > -1) {
			
			time = backupSuffix.substring(backupSuffix.indexOf("_")+1,
					backupSuffix.length());
			Integer tableNamesType = MasterDataCodeConstant.TYPE_CONTENT_TABLE;
			Integer lang = 1;
			
			List<Map<String, Object>> tableNames = getAllType(tableNamesType,
					lang);

			// use date dir
			backupDataDir += time;
			backupDataDir += "/";
			File fileDir = new File(backupDataDir);
			if (!fileDir.exists()) {
				fileDir.mkdir();
			}
			StringBuffer finalDir = new StringBuffer();
			String suffix = ".bat";
			String tableName = "";

			for (Map<String, Object> mapDir : tableNames) {
				if (mapDir.containsKey("masterdataName")) {
					tableName = mapDir.get("masterdataName").toString();
					finalDir.append(backupDataDir);
					finalDir.append(tableName);
					finalDir.append(time);
					finalDir.append(suffix);

					File finalFile = new File(finalDir.toString());
					if (finalFile.exists()) {
						String dumpSql = "TRUNCATE table "+ tableName;
						String restoreSql = " LOAD DATA INFILE '"
								+ finalDir.toString()
								+ "' INTO TABLE "
								+ tableName
								+ " FIELDS  TERMINATED BY ',' OPTIONALLY ENCLOSED BY '\"' LINES TERMINATED BY '\r\n' ";
						try {
							publishLogDao.executeSql(dumpSql);
							publishLogDao.executeSql(restoreSql);
							finalDir.delete(0, finalDir.length());
						} catch (Exception e) {
							isSuccess = false;
							e.printStackTrace();
							break;
						} 
					}
					
					
				}
			}
		}

		return isSuccess;
	}

}
