package com.kohler.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohler.dao.LocaleDao;
import com.kohler.dao.MasterDataDao;
import com.kohler.dao.MasterDataMetaDataDao;
import com.kohler.dao.utils.Pager;
import com.kohler.entity.AggMasterdataMetadataEntity;
import com.kohler.entity.MasterdataEntity;
import com.kohler.entity.MasterdataMetadataEntity;
import com.kohler.service.MasterDataMetaDataService;

/**
 * 
 * @author ys
 * 
 */
@Service
public class MasterDataMetaDataServiceImpl implements MasterDataMetaDataService {
	@Autowired
	private MasterDataMetaDataDao masterDataDao;
	@Autowired
	private MasterDataDao masterDao;

	@Autowired
	private LocaleDao localeDao;

	// 查询数据
	@Override
	public Pager<Map<String, Object>> getAllMasterdata(
			Pager<Map<String, Object>> pager, AggMasterdataMetadataEntity master) {
		// TODO Auto-generated method stub
		return masterDataDao.select(pager, master);
	}

	// 获得所有type
	@Override
	public List<Map<String, Object>> getAllType(List<Object> template) {
		// TODO Auto-generated method stub
		return masterDataDao.getAllType(template);
	}

	// 创建masterdata
	@Override
	public MasterdataMetadataEntity createMasterdataMateDeta(
			MasterdataMetadataEntity masterData) {
		// TODO Auto-generated method stub
		return masterDataDao.createMasterdataMateDeta(masterData);
	}

	// 删除masterdata
	@Override
	public int deleteMasterdataMateDeta(MasterdataMetadataEntity masterData) {
		// TODO Auto-generated method stub
		masterData.setIsEnable(false);
		MasterdataEntity master = new MasterdataEntity();
		master.setIsEnable(false);
		master.setMasterdataMetadataId(masterData.getMasterdataMetadataId());

		List<Object> list = new ArrayList<Object>();
		list.add(masterData.getMasterdataMetadataId());
		List<Map<String, Object>> selectOne = selectOne(list);
		for (int i = 0; i < selectOne.size(); i++) {
			master.setMasterdataId((Integer) selectOne.get(i).get(
					"masterdataId"));
			masterDao.update(master);
		}

		return masterDataDao.deleteMasterdataMateDeta(masterData);
	}

	@Override
	public List<Map<String, Object>> getAllLocale() {
		// TODO Auto-generated method stub
		return localeDao.getAllLocale();
	}

	@Override
	public int updateMasterdataMateDeta(MasterdataMetadataEntity masterData,
			String[] masterName) {
		// TODO Auto-generated method stub
		masterData.setIsEnable(true);
		return masterDataDao.updateMasterdataMateDeta(masterData, masterName);
	}

	@Override
	public List<Map<String, Object>> selectOne(List<Object> MasterData) {
		// TODO Auto-generated method stub
		return masterDataDao.selectOne(MasterData);
	}

	@Override
	public int updateMasterdata(MasterdataMetadataEntity masterData,
			List<MasterdataEntity> masterdata) {
		// TODO Auto-generated method stub
		return masterDataDao.updateMasterdata(masterData, masterdata);
	}

}
