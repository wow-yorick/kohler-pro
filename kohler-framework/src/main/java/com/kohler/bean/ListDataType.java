/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * ListDataType Bean
 *
 * @author kangmin_Qu
 * @Date 2014-10-17
 */
public class ListDataType {
	
	private String name;
	
	private List<String> displayFields = new ArrayList<String>();
	
	private List<Field> fields = new ArrayList<Field>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getDisplayFields() {
		return displayFields;
	}
	public void setDisplayFields(List<String> displayFields) {
		this.displayFields = displayFields;
	}

	public List<Field> getFields() {
		return fields;
	}
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
	public void addDisplayField(String pojo) {
		displayFields.add(pojo);
	}
	public void addField(Field field){
		fields.add(field);
	}
	
}
