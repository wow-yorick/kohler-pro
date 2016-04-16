/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * EditDataType Bean
 *
 * @author kangmin_Qu
 * @Date 2014-10-17
 */
public class EditDataType {
	
	private String name;
	
	private List<Field> fields = new ArrayList<Field>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
	
	public void addField(Field field){
		fields.add(field);
	}
	
}
