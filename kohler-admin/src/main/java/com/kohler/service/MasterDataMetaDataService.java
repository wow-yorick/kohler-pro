package com.kohler.service;

import java.util.List;
import java.util.Map;
import com.kohler.dao.utils.Pager;
import com.kohler.entity.AggMasterdataMetadataEntity;
import com.kohler.entity.MasterdataEntity;
import com.kohler.entity.MasterdataMetadataEntity;
/**
 * 
 * @author ys
 *	
 */
public interface MasterDataMetaDataService {
	public Pager<Map<String, Object>> getAllMasterdata(Pager<Map<String, Object>> pager,AggMasterdataMetadataEntity master);
	public List<Map<String, Object>> getAllType(List<Object> template);
	public MasterdataMetadataEntity createMasterdataMateDeta(MasterdataMetadataEntity masterData);
	public int deleteMasterdataMateDeta(MasterdataMetadataEntity masterData);
	public int updateMasterdataMateDeta(MasterdataMetadataEntity masterData,String[] masterName);
	public List<Map<String, Object>> getAllLocale();
	public List<Map<String, Object>> selectOne(List<Object> MasterData);
	public int updateMasterdata(MasterdataMetadataEntity masterData,List<MasterdataEntity> masterdata);
	
}
