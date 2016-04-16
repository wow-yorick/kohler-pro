package com.kohler.dao.impl;


import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.record.formula.functions.T;
import org.springframework.stereotype.Repository;

import com.kohler.dao.MasterDataCodeDao;

@Repository
public class MasterDataCodeImpl extends BaseDaoImpl<T> implements MasterDataCodeDao {

	@Override
	public List<Map<String, Object>> getAllTypeByMasterData(
			List<Object> MasterData) {
		StringBuffer listSql = new StringBuffer("select md.MASTERDATA_METADATA_ID as masterdataId,md.MASTERDATA_NAME as masterdataName" +
								"  from MASTERDATA md  " +
								"  left join MASTERDATA_METADATA mm on mm.MASTERDATA_METADATA_ID = md.MASTERDATA_METADATA_ID" +
								"  where mm.MASTERDATA_TYPE_ID = ? and md.LANG = ?");
		List<Map<String, Object>> list = selectByConditionWithMap(listSql.toString(), MasterData);
		return list;
	}




}
