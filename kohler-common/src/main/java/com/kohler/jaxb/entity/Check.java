package com.kohler.jaxb.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Check {
	
	@XmlElement(name = "maxsize")
	private String maxsize;
	@XmlElement(name = "require")
	private String require;
	
	public String getMaxsize() {
		return maxsize;
	}
	public void setMaxsize(String maxsize) {
		this.maxsize = maxsize;
	}
	public String getRequire() {
		return require;
	}
	public void setRequire(String require) {
		this.require = require;
	}
	
	
}
