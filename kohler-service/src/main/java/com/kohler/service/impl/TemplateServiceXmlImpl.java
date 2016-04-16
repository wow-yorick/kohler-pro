package com.kohler.service.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kohler.dao.TemplateXmlDao;
import com.kohler.service.TemplateXmlService;

@Service
public class TemplateServiceXmlImpl implements TemplateXmlService {

	@Autowired 
	public TemplateXmlDao templateDao;

	@Override
	@Transactional
	public void saveChose(String templateId, String data) {
		templateDao.deleteByTemplateId(templateId);
		
		String fields[] = data.split(";");
		for (String field : fields) {
			String values[] = field.split(",");
			UUID uuid = UUID.randomUUID();
			templateDao.insertRecord(templateId,uuid.toString(),values[0],values[2],values[1]);
		}
	}

	@Override
	public List<Map<String, Object>> getDataTypes(String templateId) {
		return templateDao.selectDataTypes(templateId);
	}

}
