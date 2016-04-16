/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.solr.bean;

import org.apache.solr.client.solrj.beans.Field;

/**
 * Class Function Description
 *
 * @author sana
 * @Date 2014年12月30日
 */
public class PDFSolrBean implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -8036092242520335288L;
    
    @Field("id")
    private String            id;
    
    @Field("productPDFMetadataId")
    private String            productPDFMetadataId;
    
    @Field("pdfType")
    private String            pdfType;
    
    @Field("pdfTypeName")
    private String            pdfTypeName;
    
    @Field("productMetadataId")
    private String            productMetadataId;
    
    @Field("productName")
    private String            productName;
    
    @Field("productCode")
    private String            productCode;
    
    @Field("productPDFName")
    private String            productPDFName;
    
    @Field("productPDFUrl")
    private String            productPDFUrl;
    
    @Field("categoryMetadataId")
    private String            categoryMetadataId;
    
    @Field("subCategoryMetadataId")
    private String            subCategoryMetadataId;
    
    @Field("categoryName")
    private String            categoryName;
    
    @Field("subCategoryName")
    private String            subCategoryName;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the productPDFMetadataId
     */
    public String getProductPDFMetadataId() {
        return productPDFMetadataId;
    }

    /**
     * @param productPDFMetadataId the productPDFMetadataId to set
     */
    public void setProductPDFMetadataId(String productPDFMetadataId) {
        this.productPDFMetadataId = productPDFMetadataId;
    }

    /**
     * @return the pdfType
     */
    public String getPdfType() {
        return pdfType;
    }

    /**
     * @param pdfType the pdfType to set
     */
    public void setPdfType(String pdfType) {
        this.pdfType = pdfType;
    }

    /**
     * @return the pdfTypeName
     */
    public String getPdfTypeName() {
        return pdfTypeName;
    }

    /**
     * @param pdfTypeName the pdfTypeName to set
     */
    public void setPdfTypeName(String pdfTypeName) {
        this.pdfTypeName = pdfTypeName;
    }

    /**
     * @return the productMetadataId
     */
    public String getProductMetadataId() {
        return productMetadataId;
    }

    /**
     * @param productMetadataId the productMetadataId to set
     */
    public void setProductMetadataId(String productMetadataId) {
        this.productMetadataId = productMetadataId;
    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return the productCode
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * @param productCode the productCode to set
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /**
     * @return the productPDFName
     */
    public String getProductPDFName() {
        return productPDFName;
    }

    /**
     * @param productPDFName the productPDFName to set
     */
    public void setProductPDFName(String productPDFName) {
        this.productPDFName = productPDFName;
    }

    /**
     * @return the productPDFUrl
     */
    public String getProductPDFUrl() {
        return productPDFUrl;
    }

    /**
     * @param productPDFUrl the productPDFUrl to set
     */
    public void setProductPDFUrl(String productPDFUrl) {
        this.productPDFUrl = productPDFUrl;
    }

    /**
     * @return the categoryMetadataId
     */
    public String getCategoryMetadataId() {
        return categoryMetadataId;
    }

    /**
     * @param categoryMetadataId the categoryMetadataId to set
     */
    public void setCategoryMetadataId(String categoryMetadataId) {
        this.categoryMetadataId = categoryMetadataId;
    }

    /**
     * @return the subCategoryMetadataId
     */
    public String getSubCategoryMetadataId() {
        return subCategoryMetadataId;
    }

    /**
     * @param subCategoryMetadataId the subCategoryMetadataId to set
     */
    public void setSubCategoryMetadataId(String subCategoryMetadataId) {
        this.subCategoryMetadataId = subCategoryMetadataId;
    }

    /**
     * @return the categoryName
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * @param categoryName the categoryName to set
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * @return the subCategoryName
     */
    public String getSubCategoryName() {
        return subCategoryName;
    }

    /**
     * @param subCategoryName the subCategoryName to set
     */
    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    
}
