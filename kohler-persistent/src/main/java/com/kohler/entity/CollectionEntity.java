package com.kohler.entity;


public class CollectionEntity extends BaseEntity {

	private static final long serialVersionUID = 4346401062535188313L;
	
	private Integer collectionId;
	private Integer lang;
	private Integer collectionMetadataId;
	private String collectionName;
	public Integer getCollectionId() {
		return collectionId;
	}
	public void setCollectionId(Integer collectionId) {
		this.collectionId = collectionId;
	}
	public Integer getLang() {
		return lang;
	}
	public void setLang(Integer lang) {
		this.lang = lang;
	}
	public Integer getCollectionMetadataId() {
		return collectionMetadataId;
	}
	public void setCollectionMetadataId(Integer collectionMetadataId) {
		this.collectionMetadataId = collectionMetadataId;
	}
	public String getCollectionName() {
		return collectionName;
	}
	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}
	
	
}
