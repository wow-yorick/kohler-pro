package com.kohler.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kohler.dao.FileAssetDao;
import com.kohler.entity.FileAssetEntity;
@Repository
public class FileAssetDaoImpl extends BaseDaoImpl<FileAssetEntity> implements FileAssetDao{

	@Override
	public int createFileAsset(FileAssetEntity file) {
		// TODO Auto-generated method stub
		return insert(file);
	}

	@Override
	public int updateFileAsset(FileAssetEntity file) {
		// TODO Auto-generated method stub
	    FileAssetEntity upFile = selectById(file.getFileAssetId());
	    upFile.setFileAssetName(file.getFileAssetName());
	    upFile.setPhysicalFilename(file.getPhysicalFilename());
		return update(upFile);
	}

	@Override
	public List<Map<String, Object>> searchOneFile(FileAssetEntity file) {
		// TODO Auto-generated method stub
		StringBuffer listSql = new StringBuffer("select  fa.FILE_ASSET_ID as fileAssetId,fa.FILE_ASSET_NAME as fileAssetName," 
				+ "fa.CREATE_TIME as createTime,fa.CREATE_USER as createUser,fa.PHYSICAL_FILENAME as phy,fa.FOLDER_ID as folderId," 
				+ "fa.MODIFY_TIME as modifyTime,fa.MODIFY_USER as modifyUser, f.FOLDER_NAME as folderName from FILE_ASSET fa" 
				+ " left join FOLDER f on f.FOLDER_ID = fa.FOLDER_ID where fa.FILE_ASSET_ID = ?");
		List<Object> params = new ArrayList<Object>();
		params.add(file.getFileAssetId());
		List<Map<String, Object>> selectByConditionWithMap = selectByConditionWithMap(listSql.toString(),params);
		return selectByConditionWithMap;
	}

	@Override
	public int deleteFileAsset(FileAssetEntity file) {
		// TODO Auto-generated method stub
		return update(file);
	}

	@Override
	public List<FileAssetEntity> selectFileByName(FileAssetEntity file) {
		// TODO Auto-generated method stub
		StringBuffer listSql = new StringBuffer("select * from FILE_ASSET");
		List<Object> params = new ArrayList<Object>();
		if(file.getFileAssetName() != null && !file.getFileAssetName().equals("")){
			listSql.append(" where FILE_ASSET_NAME like concat(concat('%',?),'%')");
			params.add(file.getFileAssetName());
		}
		if(file.getFileType() != null && !file.getFileType().equals("")){
		    listSql.append(" and FILE_TYPE = ?");
		    params.add(file.getFileType());
		}
		List<FileAssetEntity> selectByConditionWithMap = selectByCondition(listSql.toString(),params);
		return selectByConditionWithMap;
	}
	
	@Override
    public List<FileAssetEntity> selectFileById(String fileAssetId) {
        // TODO Auto-generated method stub
        StringBuffer listSql = new StringBuffer("select * from FILE_ASSET where IS_ENABLE = 1");
        List<Object> params = new ArrayList<Object>();
        if(fileAssetId.indexOf(",")>-1){
//            String[] contentIds = fileAssetId.split(",");
//            for (String id : contentIds) {
            listSql.append(" and FILE_ASSET_ID in (?)");
            //查询并过滤
            
//            }
        }else{
            listSql.append(" and FILE_ASSET_ID = ?");
        }
        params.add(fileAssetId);
        List<FileAssetEntity> selectByConditionWithMap = selectByCondition(listSql.toString(),params);
        return selectByConditionWithMap;
    }
	
}