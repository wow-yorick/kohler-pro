/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohler.dao.LocaleDao;
import com.kohler.entity.LocaleEntity;
import com.kohler.service.LocaleService;

/**
 * Locale Interface Impl
 *
 * @author shefeng
 * @Date 2014年9月25日
 */
@Service
public class LocaleServiceImpl implements LocaleService {

	@Autowired 
	public LocaleDao localeDao;

	@Override
	public List<LocaleEntity> getLanguages() {
		LocaleEntity locale = new LocaleEntity();
		locale.setIsEnable(true);
		return localeDao.selectByCondition(locale);
	}

}
