/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.constants;

/**
 * Section SQL
 *
 * @author YS
 * @Date 2014年10月20日
 */
public class FolderSQL {

	public static final String GET_ALL_FOLDER = "select f.FOLDER_ID as id,f.PARENT_ID as pId," 
			+ "f.FOLDER_NAME as name,"
	        + "CONCAT('folderList.action?folderId=',FOLDER_ID) AS url,'_self' AS target from FOLDER f WHERE IS_ENABLE = 1";
	
//	public static final String GET_ALL_FOLDER_UNION = "  UNION ALL select '0' as id,ft.FOLDER_ID as pId," 
//			+ " fa.FILE_ASSET_NAME as name,CONCAT('folderList.action?folderId=',fa.FOLDER_ID) AS url," 
//			+ " '_self' AS target from FILE_ASSET fa left join FOLDER ft  on fa.FOLDER_ID = ft.FOLDER_ID" 
//			+ " where fa.IS_ENABLE = 1 and ft.IS_ENABLE = 1";
	
	public static final String GET_FOLDER_LIST = "select f.FOLDER_NAME as folderName,f.FOLDER_ID as folderId," 
			+ "'folder' as masterdataName,f.CREATE_TIME as createTime,f.CREATE_USER as createName "
	        + " from FOLDER f  where  f.IS_ENABLE = 1 ";
	
	public static final String GET_FOLDER_LIST_UNION = " UNION ALL select distinct fa.FILE_ASSET_NAME as folderName,fa.FILE_ASSET_ID as folderId,  " 
			+ "  md.MASTERDATA_NAME as masterdataName,ft.CREATE_TIME as createTime,ft.CREATE_USER as createName " 
			+ "  from FILE_ASSET fa left join MASTERDATA_METADATA mm on mm.SEQ_NO = fa.FILE_TYPE"
			+ "  left join MASTERDATA md on mm.MASTERDATA_METADATA_ID = md.MASTERDATA_METADATA_ID"
			+ "  left join FOLDER ft  on fa.FOLDER_ID = ft.FOLDER_ID " 
			+ "  where fa.IS_ENABLE = 1 and ft.IS_ENABLE = 1 ";
	
	public static final String GET_FOLDER_COUNT = "select COUNT(*)  from FOLDER f" 
	        + "  left join MASTERDATA mr on f.FILE_TYPE = mr.MASTERDATA_METADATA_ID" 
	        + " where mr.LANG = 1 and f.IS_ENABLE = 1";
	
	public static final String SERACH_ONE = "select * from FOLDER where FOLDER_ID = ?";

}
