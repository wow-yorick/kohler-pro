package com.kohler.service;

import java.util.List;
import java.util.Map;


public interface TemplateXmlService {

	public void saveChose(String templateId, String data);

	public List<Map<String, Object>> getDataTypes(String templateId);

	
}
