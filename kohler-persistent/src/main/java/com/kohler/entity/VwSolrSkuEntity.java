package com.kohler.entity;

import java.math.BigDecimal;

/**
 * 
 * @author zhangqiqi
 * @Date 11/25/2014
 */
@SuppressWarnings("serial")
public class VwSolrSkuEntity implements java.io.Serializable {
    private Integer skuId;

    private Integer lang;

    private Integer skuMetadataId;

    private Integer productMetadataId;
    
    private Integer productId;

    private Integer subCategoryMetadataId;
    
    private Integer subCategoryId;

    private Integer categoryMetadataId;
    
    private Integer categoryId;

    private Integer sectionMetadataId;

    private String  skuPrice;

    private String  skuCode;

    private String  skuName;

    private Boolean isDefault;

    private String  productCode;

    private String  productName;

    private String  subCategoryName;

    private String  categoryName;
    
    private String  collectionName;

    private String  sectionName;

    private String  listImageUrl;

    private String  listImageAlt;

    private Integer imageSource;

    private Integer listImageId;

    private String  detailImage1Url;

    private String  detailImage1Alt;

    private Integer detailImage1Id;

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Integer getLang() {
        return lang;
    }

    public void setLang(Integer lang) {
        this.lang = lang;
    }

    public Integer getSkuMetadataId() {
        return skuMetadataId;
    }

    public void setSkuMetadataId(Integer skuMetadataId) {
        this.skuMetadataId = skuMetadataId;
    }

    public Integer getProductMetadataId() {
        return productMetadataId;
    }

    public void setProductMetadataId(Integer productMetadataId) {
        this.productMetadataId = productMetadataId;
    }

    public Integer getSubCategoryMetadataId() {
        return subCategoryMetadataId;
    }

    public void setSubCategoryMetadataId(Integer subCategoryMetadataId) {
        this.subCategoryMetadataId = subCategoryMetadataId;
    }

    public Integer getCategoryMetadataId() {
        return categoryMetadataId;
    }

    public void setCategoryMetadataId(Integer categoryMetadataId) {
        this.categoryMetadataId = categoryMetadataId;
    }

    public Integer getSectionMetadataId() {
        return sectionMetadataId;
    }

    public void setSectionMetadataId(Integer sectionMetadataId) {
        this.sectionMetadataId = sectionMetadataId;
    }

    public String getSkuPrice() {
        return skuPrice;
    }

    public void setSkuPrice(String skuPrice) {
        this.skuPrice = skuPrice;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getListImageUrl() {
        return listImageUrl;
    }

    public void setListImageUrl(String listImageUrl) {
        this.listImageUrl = listImageUrl;
    }

    public String getListImageAlt() {
        return listImageAlt;
    }

    public void setListImageAlt(String listImageAlt) {
        this.listImageAlt = listImageAlt;
    }

    public Integer getImageSource() {
        return imageSource;
    }

    public void setImageSource(Integer imageSource) {
        this.imageSource = imageSource;
    }

    public Integer getListImageId() {
        return listImageId;
    }

    public void setListImageId(Integer listImageId) {
        this.listImageId = listImageId;
    }

    public String getDetailImage1Url() {
        return detailImage1Url;
    }

    public void setDetailImage1Url(String detailImage1Url) {
        this.detailImage1Url = detailImage1Url;
    }

    public String getDetailImage1Alt() {
        return detailImage1Alt;
    }

    public void setDetailImage1Alt(String detailImage1Alt) {
        this.detailImage1Alt = detailImage1Alt;
    }

    public Integer getDetailImage1Id() {
        return detailImage1Id;
    }

    public void setDetailImage1Id(Integer detailImage1Id) {
        this.detailImage1Id = detailImage1Id;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(Integer subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

}
