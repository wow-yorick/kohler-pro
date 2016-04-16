package com.kohler.jaxb.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Field {
	
	@XmlAttribute(name = "id")
	private String id;
	@XmlAttribute(name = "name")
	private String name;
	@XmlAttribute(name = "type")
	private String type;
	@XmlAttribute(name = "area")
	private String area;
	@XmlAttribute(name = "option")
	private String option;
	@XmlAttribute(name = "complicated")
	private String complicated;
	
	@XmlElement(name = "value")
	private List<String> values;
	@XmlElement(name = "check")
	private Check check;
	
	
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public List<String> getValues() {
		return values;
	}
	public void setValues(List<String> values) {
		this.values = values;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Check getCheck() {
		return check;
	}

	public void setCheck(Check check) {
		this.check = check;
	}

	public String getComplicated() {
		return complicated;
	}

	public void setComplicated(String complicated) {
		this.complicated = complicated;
	}
	

}
