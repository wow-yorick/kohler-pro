package com.kohler.dao.impl;


import org.springframework.stereotype.Repository;
import com.kohler.dao.MasterDataDao;
import com.kohler.entity.MasterdataEntity;

@Repository
public class MasterDataImpl extends BaseDaoImpl<MasterdataEntity> implements MasterDataDao {


	@Override
	public int createMasterdata(MasterdataEntity masterData) {
		// TODO Auto-generated method stub
		return insert(masterData);
	}

	@Override
	public int deleteMasterdata(MasterdataEntity masterData) {
		// TODO Auto-generated method stub
		return delete(masterData);
	}

	@Override
	public int updateMasterdata(MasterdataEntity masterData) {
		// TODO Auto-generated method stub
		return update(masterData);
	}



}
