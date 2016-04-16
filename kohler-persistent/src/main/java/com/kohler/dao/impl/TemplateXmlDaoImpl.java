package com.kohler.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.kohler.constants.TemplateSQL;
import com.kohler.dao.TemplateXmlDao;


public class TemplateXmlDaoImpl implements TemplateXmlDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void deleteByTemplateId(String tempId) {
		jdbcTemplate.update(TemplateSQL.DELETE_BY_TEMPLATE_ID,new Object[]{tempId});
	}

	@Override
	public void insertRecord(String dataTypeId, String recordId, String fieldId,
			String complicated, String value) {
		jdbcTemplate.update(TemplateSQL.INSERT_DATATYPE,new Object[]{dataTypeId,recordId,fieldId,
				 complicated, value});
	}

	@Override
	public List<Map<String, Object>> selectDataTypes(String templateId) {
		return jdbcTemplate.queryForList(TemplateSQL.SELECT_DATATYPE_BY_TEMPLATE_ID,new Object[]{templateId});
	}


}
