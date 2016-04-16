package com.kohler.solr.bean;

import org.apache.solr.client.solrj.beans.Field;

public class StoreLocatorEntity implements java.io.Serializable{
	/**
     * 
     */
    private static final long serialVersionUID = -5740710825940370938L;

    @Field("id")
	private String id;
    
    @Field("contentMetadataId")
    private String contentMetadataId;
	
	@Field("province")
	private String province;
	
	@Field("city")
	private String city;
	
	@Field("area")
	private String area;
	
	@Field("storeType")
	private String storeType;
	
	@Field("storeTypeName")
	private String storeTypeName;
	
	@Field("storeName")
	private String storeName;
	
	@Field("storeAddress")
	private String storeAddress;
	
	@Field("productType")
	private String productType;

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
     * @return the contentMetadataId
     */
    public String getContentMetadataId() {
        return contentMetadataId;
    }

    /**
     * @param contentMetadataId the contentMetadataId to set
     */
    public void setContentMetadataId(String contentMetadataId) {
        this.contentMetadataId = contentMetadataId;
    }

    /**
     * @return the province
     */
    public String getProvince() {
        return province;
    }

    /**
     * @param province the province to set
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the area
     */
    public String getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * @return the storeType
     */
    public String getStoreType() {
        return storeType;
    }

    /**
     * @param storeType the storeType to set
     */
    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }

    /**
     * @return the storeTypeName
     */
    public String getStoreTypeName() {
        return storeTypeName;
    }

    /**
     * @param storeTypeName the storeTypeName to set
     */
    public void setStoreTypeName(String storeTypeName) {
        this.storeTypeName = storeTypeName;
    }

    /**
     * @return the storeName
     */
    public String getStoreName() {
        return storeName;
    }

    /**
     * @param storeName the storeName to set
     */
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    /**
     * @return the storeAddress
     */
    public String getStoreAddress() {
        return storeAddress;
    }

    /**
     * @param storeAddress the storeAddress to set
     */
    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    /**
     * @return the productType
     */
    public String getProductType() {
        return productType;
    }

    /**
     * @param productType the productType to set
     */
    public void setProductType(String productType) {
        this.productType = productType;
    }

    
}
