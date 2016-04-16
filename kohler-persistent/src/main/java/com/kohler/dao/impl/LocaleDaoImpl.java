package com.kohler.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kohler.dao.LocaleDao;
import com.kohler.entity.LocaleEntity;

@Repository
public class LocaleDaoImpl extends BaseDaoImpl<LocaleEntity> implements LocaleDao {

	@Override
	public List<Map<String, Object>> getAllLocale() {
		// TODO Auto-generated method stub
		List<Object> typeLists = new ArrayList<Object>();
		typeLists.add(1);
		StringBuffer listSql = new StringBuffer("select LOCALE_ID as localeId,LOCALE_NAME as localeName," +
							"LOCALE_CODE as localeCode from LOCALE where 1=?");
		List<Map<String, Object>> list = selectByConditionWithMap(listSql.toString(), typeLists);
		return list;
	}
	

}
