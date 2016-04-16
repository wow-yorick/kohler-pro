package com.kohler.dao;

import java.util.List;
import java.util.Map;


public interface TemplateXmlDao {

	public void deleteByTemplateId(String tempId);

	public void insertRecord(String dataTypeId, String recordId, String fieldId,
			String complicated, String value);

	public List<Map<String, Object>> selectDataTypes(String templateId);
	
}
