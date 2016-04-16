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
public class CADSolrBean implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -8036092242520335288L;
    
    @Field("id")
    private String            id;
    
    @Field("productCADMetadataId")
    private String            productCADMetadataId;
    
    @Field("cadType")
    private String            cadType;
    
    @Field("cadTypeName")
    private String            cadTypeName;
    
    @Field("productMetadataId")
    private String            productMetadataId;
    
    @Field("productCADName")
    private String            productCADName;
    
    @Field("productCADUrl")
    private String            productCADUrl;
    
    @Field("productCADFormat")
    private String            productCADFormat;
    
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
     * @return the productCADMetadataId
     */
    public String getProductCADMetadataId() {
        return productCADMetadataId;
    }

    /**
     * @param productCADMetadataId the productCADMetadataId to set
     */
    public void setProductCADMetadataId(String productCADMetadataId) {
        this.productCADMetadataId = productCADMetadataId;
    }

    /**
     * @return the cadType
     */
    public String getCadType() {
        return cadType;
    }

    /**
     * @param cadType the cadType to set
     */
    public void setCadType(String cadType) {
        this.cadType = cadType;
    }

    /**
     * @return the cadTypeName
     */
    public String getCadTypeName() {
        return cadTypeName;
    }

    /**
     * @param cadTypeName the cadTypeName to set
     */
    public void setCadTypeName(String cadTypeName) {
        this.cadTypeName = cadTypeName;
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
     * @return the productCADName
     */
    public String getProductCADName() {
        return productCADName;
    }

    /**
     * @param productCADName the productCADName to set
     */
    public void setProductCADName(String productCADName) {
        this.productCADName = productCADName;
    }

    /**
     * @return the productCADUrl
     */
    public String getProductCADUrl() {
        return productCADUrl;
    }

    /**
     * @param productCADUrl the productCADUrl to set
     */
    public void setProductCADUrl(String productCADUrl) {
        this.productCADUrl = productCADUrl;
    }

    /**
     * @return the productCADFormat
     */
    public String getProductCADFormat() {
        return productCADFormat;
    }

    /**
     * @param productCADFormat the productCADFormat to set
     */
    public void setProductCADFormat(String productCADFormat) {
        this.productCADFormat = productCADFormat;
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
