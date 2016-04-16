/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.entity;

/**
 * Class Function Description
 * ProductCAD
 * @author zhangtingting
 * @Date 2014年9月25日
 */
public class ProductCADEntity extends BaseEntity {

	private static final long serialVersionUID = 4346401062535188313L;
	
	private Integer productCADId;
	private Integer lang;
	private Integer productCADMetadataId;
	private String productCADName;
	private Integer fileSource;
	private String fileUrl;
	private Integer fileId;
	private String description;
    
    public Integer getLang() {
        return lang;
    }
    public void setLang(Integer lang) {
        this.lang = lang;
    }
    public Integer getProductCADId() {
        return productCADId;
    }
    public void setProductCADId(Integer productCADId) {
        this.productCADId = productCADId;
    }
    public Integer getProductCADMetadataId() {
        return productCADMetadataId;
    }
    public void setProductCADMetadataId(Integer productCADMetadataId) {
        this.productCADMetadataId = productCADMetadataId;
    }
    public String getProductCADName() {
        return productCADName;
    }
    public void setProductCADName(String productCADName) {
        this.productCADName = productCADName;
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
