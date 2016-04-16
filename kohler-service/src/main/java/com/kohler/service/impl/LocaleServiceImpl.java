package com.kohler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohler.dao.LocaleDao;
import com.kohler.entity.Locale;
import com.kohler.service.LocaleService;

@Service
public class LocaleServiceImpl implements LocaleService {

	@Autowired 
	public LocaleDao localeDao;


	@Override
	public List<Locale> getLanguages() {
		Locale locale = new Locale();
		locale.setIsEnable(true);
		return localeDao.selectByCondition(locale);
	}
	

}
