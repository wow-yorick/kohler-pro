package com.kohler.dao;

import com.kohler.entity.MasterdataEntity;

/**
 * 
 * @author ys
 *
 */
public interface MasterDataDao extends BaseDao<MasterdataEntity>{
	public int createMasterdata(MasterdataEntity masterData);
	public int deleteMasterdata(MasterdataEntity masterData);
	public int updateMasterdata(MasterdataEntity masterData);
}
