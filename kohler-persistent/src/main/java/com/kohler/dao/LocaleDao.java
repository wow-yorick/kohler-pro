package com.kohler.dao;

import java.util.List;
import java.util.Map;

import com.kohler.entity.LocaleEntity;

public interface LocaleDao extends BaseDao<LocaleEntity>{
	public List<Map<String, Object>> getAllLocale();
}
