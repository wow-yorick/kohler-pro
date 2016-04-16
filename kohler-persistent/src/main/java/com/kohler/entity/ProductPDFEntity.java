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
public class ProductPDFEntity extends BaseEntity {

	private static final long serialVersionUID = 4346401062535188313L;
	
	private Integer productPDFId;
	private Integer lang;
	private Integer productPDFMetadataId;
	private String productPDFName;
	private Integer pdfType;
	private Integer fileSource;
	private String fileUrl;
	private Integer fileId;
	private String description;
	
    public Integer getProductPDFId() {
        return productPDFId;
    }
    public void setProductPDFId(Integer productPDFId) {
        this.productPDFId = productPDFId;
    }
    public Integer getProductPDFMetadataId() {
        return productPDFMetadataId;
    }
    public void setProductPDFMetadataId(Integer productPDFMetadataId) {
        this.productPDFMetadataId = productPDFMetadataId;
    }
    public String getProductPDFName() {
        return productPDFName;
    }
    public void setProductPDFName(String productPDFName) {
        this.productPDFName = productPDFName;
    }
    public Integer getLang() {
        return lang;
    }
    public void setLang(Integer lang) {
        this.lang = lang;
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
    public Integer getPdfType() {
        return pdfType;
    }
    public void setPdfType(Integer pdfType) {
        this.pdfType = pdfType;
    }
}
