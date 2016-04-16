/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.entity;

/**
 * Class Function Description
 * ProductVideo
 * @author zhangtingting
 * @Date 2014年9月25日
 */
public class ProductVideoEntity extends BaseEntity {

	private static final long serialVersionUID = 4346401062535188313L;
	
	private Integer productVideoId;
	private Integer lang;
	private Integer productVideoMetadataId;
	private String productVideoName;
	private Integer fileSource;
	private String fileUrl;
	private Integer fileId;
	private String description;
	
    public Integer getProductVideoId() {
        return productVideoId;
    }
    public void setProductVideoId(Integer productVideoId) {
        this.productVideoId = productVideoId;
    }
    public Integer getLang() {
        return lang;
    }
    public void setLang(Integer lang) {
        this.lang = lang;
    }
    public Integer getProductVideoMetadataId() {
        return productVideoMetadataId;
    }
    public void setProductVideoMetadataId(Integer productVideoMetadataId) {
        this.productVideoMetadataId = productVideoMetadataId;
    }
    public String getProductVideoName() {
        return productVideoName;
    }
    public void setProductVideoName(String productVideoName) {
        this.productVideoName = productVideoName;
    }
    public Integer getFileSource() {
        return fileSource;
    }
    public void setFileSource(Integer fileSource) {
        this.fileSource = fileSource;
    }
    public String getFileUrl() {
        return fileUrl;
    }
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
    public Integer getFileId() {
        return fileId;
    }
    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
