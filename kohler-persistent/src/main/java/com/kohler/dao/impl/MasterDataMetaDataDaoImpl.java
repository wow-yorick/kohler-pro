package com.kohler.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kohler.dao.MasterDataDao;
import com.kohler.dao.MasterDataMetaDataDao;
import com.kohler.dao.utils.Pager;
import com.kohler.entity.AggMasterdataMetadataEntity;
import com.kohler.entity.MasterdataEntity;
import com.kohler.entity.MasterdataMetadataEntity;

@Repository
public class MasterDataMetaDataDaoImpl extends
		BaseDaoImpl<MasterdataMetadataEntity> implements MasterDataMetaDataDao {
	@Autowired
	private MasterDataDao masterDataDao;

	@Override
	public Pager<Map<String, Object>> select(Pager<Map<String, Object>> pager,
			AggMasterdataMetadataEntity master) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer listSql = new StringBuffer(
				"select mm.MASTERDATA_METADATA_ID as masterdataId,md.MASTERDATA_NAME as masterdataName,"
						+ "mm.MASTERDATA_CODE as masterdataCode,mm.CREATE_USER as createUser,"
						+ "mm.CREATE_TIME as createTime,mt.REMARK as masterdataTypeId  "
						+ " from MASTERDATA_METADATA mm left join MASTERDATA md on mm.MASTERDATA_METADATA_ID = md.MASTERDATA_METADATA_ID"
						+ " left join MASTERDATA_TYPE mt on mm.MASTERDATA_TYPE_ID = mt.MASTERDATA_TYPE_ID "
						+ " where mm.is_enable = 1 and md.LANG = 1");
		StringBuffer countSql = new StringBuffer(
				"select count(*) from MASTERDATA_METADATA mm"
						+ " left join MASTERDATA md on mm.MASTERDATA_METADATA_ID = md.MASTERDATA_METADATA_ID"
						+ " left join MASTERDATA_TYPE mt on mm.MASTERDATA_TYPE_ID = mt.MASTERDATA_TYPE_ID"
						+ " where mm.is_enable = 1 and md.LANG = 1 ");
		if (master.getMasterdataName() != null
				&& !master.getMasterdataName().equals("")) {
			listSql.append(" and md.MASTERDATA_NAME like concat(concat('%',?),'%')");
			countSql.append(" and md.MASTERDATA_NAME like concat(concat('%',?),'%')");
			params.add(master.getMasterdataName());
		}
		if (master.getMasterdataTypeId() != null
				&& !master.getMasterdataTypeId().equals("")) {
			listSql.append(" and mm.MASTERDATA_TYPE_ID like concat(concat('%',?),'%')");
			countSql.append(" and mm.MASTERDATA_TYPE_ID like concat(concat('%',?),'%')");
			params.add(master.getMasterdataTypeId());
		}
		listSql.append(" LIMIT ?,?");
		int MasterDataCount = selectCountByCondition(countSql.toString(),
				params);
		int pageCount = 0;
		if (MasterDataCount % pager.getPageSize() == 0) {
			pageCount = MasterDataCount / pager.getPageSize();
		} else {
			pageCount = MasterDataCount / pager.getPageSize() + 1;
		}
		int index = (pager.getCurrentPage() - 1) * pager.getPageSize();
		params.add(index);
		params.add(pager.getPageSize());
		List<Map<String, Object>> list = selectByConditionWithMap(
				listSql.toString(), params);
		pager.setStartRow(index);
		pager.setDatas(list);
		pager.setTotalRecord(MasterDataCount);
		pager.setTotalPage(pageCount);
		return pager;
	}

	@Override
	public List<Map<String, Object>> getAllType(List<Object> MasterData) {
		StringBuffer listSql = new StringBuffer(
				"select MASTERDATA_TYPE_ID as templateId,"
						+ "REMARK as templateName "
						+ "from MASTERDATA_TYPE where 1=?");
		List<Map<String, Object>> list = selectByConditionWithMap(
				listSql.toString(), MasterData);
		return list;
	}

	@Override
	public MasterdataMetadataEntity createMasterdataMateDeta(
			MasterdataMetadataEntity masterData) {
		// TODO Auto-generated method stub
		masterData.setIsEnable(false);
		int id = insert(masterData);
		masterData.setMasterdataMetadataId(id);
		return masterData;
	}

	@Override
	public int deleteMasterdataMateDeta(MasterdataMetadataEntity masterData) {
		// TODO Auto-generated method stub
		return update(masterData);
	}

	@Override
	public int updateMasterdataMateDeta(MasterdataMetadataEntity masterData,
			String[] masterName) {
		// TODO Auto-generated method stub
		MasterdataEntity master = new MasterdataEntity();
		for (int i = 0; i < masterName.length; i++) {
			master.setMasterdataName(masterName[i]);
			master.setLang(i + 1);
			master.setMasterdataMetadataId(masterData.getMasterdataMetadataId());
			masterDataDao.createMasterdata(master);
		}
		return update(masterData);
	}

	@Override
	public List<Map<String, Object>> selectOne(List<Object> MasterData) {
		StringBuffer listSql = new StringBuffer(
				"select mm.MASTERDATA_METADATA_ID as masterdataMetadataId,md.MASTERDATA_NAME as masterdataName,"
						+ "mm.MASTERDATA_CODE as masterdataCode,mm.CREATE_USER as createUser,"
						+ "mm.CREATE_TIME as createTime,mt.MASTERDATA_TYPE_NAME as masterdataTypeId,"
						+ "mm.SEQ_NO as seqNo,md.MASTERDATA_ID as masterdataId,mm.MODIFY_TIME as modifyTime,"
						+ "mt.REMARK as masterTypeName,"
						+ "mm.MODIFY_USER as modifyUser,lc.LOCALE_NAME as localeName,lc.LOCALE_CODE as localeCode  "
						+ " from MASTERDATA_METADATA mm left join MASTERDATA md on mm.MASTERDATA_METADATA_ID = md.MASTERDATA_METADATA_ID"
						+ " left join MASTERDATA_TYPE mt on mm.MASTERDATA_TYPE_ID = mt.MASTERDATA_TYPE_ID "
						+ " left join LOCALE lc on md.LANG = lc.LOCALE_ID"
						+ " where mm.MASTERDATA_METADATA_ID = ? ");
		List<Map<String, Object>> list = selectByConditionWithMap(
				listSql.toString(), MasterData);
		return list;
	}

	@Override
	public int updateMasterdata(MasterdataMetadataEntity masterDataMetadata,
			List<MasterdataEntity> masterData) {
		// TODO Auto-generated method stub
	    masterDataMetadata.setIsEnable(true);
		for (int i = 0; i < masterData.size(); i++) {
		    masterData.get(i).setIsEnable(true);
		    masterData.get(i).setLang(i+1);
		    masterData.get(i).setMasterdataMetadataId(masterDataMetadata.getMasterdataMetadataId());
			masterDataDao.update(masterData.get(i));
		}
		return update(masterDataMetadata);
	}

}
