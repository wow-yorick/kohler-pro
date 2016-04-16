package com.kohler.entity;

public class AggTemplateEntity extends TemplateEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String typeName;
	private String folderName;
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getFolderName() {
		return folderName;
	}
	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}
}
