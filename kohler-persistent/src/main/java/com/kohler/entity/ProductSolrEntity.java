package com.kohler.entity;

import java.util.Date;

/**
 * 
 * @author zhangqiqi
 * @Date 11/25/2014
 */
@SuppressWarnings("serial")
public class ProductSolrEntity implements java.io.Serializable {
    private Integer productId;

    private Integer lang;

    private Integer productMetadataId;

    private Integer pcTemplateId;

    private Integer mobileTemplateId;

    private Integer combineProductMetadataId;

    private Integer categoryMetadataId;

    private Integer seqNo;

    private Integer collectionMetadataId;

    private Integer collectionStyleMetadataId;

    private Integer dataTemplateId;

    private String  productName;

    private String  seoTitlePc;

    private String  seoKeywordsPc;

    private String  seoDescriptionPc;

    private String  seoH1tagPc;

    private String  seoTitleMobile;

    private String  seoKeywordsMobile;

    private String  seoDescriptionMobile;

    private String  seoH1tagMobile;

    private String  productCode;

    private Date    publishDate;

    private Date    modityTime;

    public Date getModityTime() {
        return modityTime;
    }

    public void setModityTime(Date modityTime) {
        this.modityTime = modityTime;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getLang() {
        return lang;
    }

    public void setLang(Integer lang) {
        this.lang = lang;
    }

    public Integer getProductMetadataId() {
        return productMetadataId;
    }

    public void setProductMetadataId(Integer productMetadataId) {
        this.productMetadataId = productMetadataId;
    }

    public Integer getPcTemplateId() {
        return pcTemplateId;
    }

    public void setPcTemplateId(Integer pcTemplateId) {
        this.pcTemplateId = pcTemplateId;
    }

    public Integer getMobileTemplateId() {
        return mobileTemplateId;
    }

    public void setMobileTemplateId(Integer mobileTemplateId) {
        this.mobileTemplateId = mobileTemplateId;
    }

    public Integer getCombineProductMetadataId() {
        return combineProductMetadataId;
    }

    public void setCombineProductMetadataId(Integer combineProductMetadataId) {
        this.combineProductMetadataId = combineProductMetadataId;
    }

    public Integer getCategoryMetadataId() {
        return categoryMetadataId;
    }

    public void setCategoryMetadataId(Integer categoryMetadataId) {
        this.categoryMetadataId = categoryMetadataId;
    }

    public Integer getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

    public Integer getCollectionMetadataId() {
        return collectionMetadataId;
    }

    public void setCollectionMetadataId(Integer collectionMetadataId) {
        this.collectionMetadataId = collectionMetadataId;
    }

    public Integer getCollectionStyleMetadataId() {
        return collectionStyleMetadataId;
    }

    public void setCollectionStyleMetadataId(Integer collectionStyleMetadataId) {
        this.collectionStyleMetadataId = collectionStyleMetadataId;
    }

    public Integer getDataTemplateId() {
        return dataTemplateId;
    }

    public void setDataTemplateId(Integer dataTemplateId) {
        this.dataTemplateId = dataTemplateId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSeoTitlePc() {
        return seoTitlePc;
    }

    public void setSeoTitlePc(String seoTitlePc) {
        this.seoTitlePc = seoTitlePc;
    }

    public String getSeoKeywordsPc() {
        return seoKeywordsPc;
    }

    public void setSeoKeywordsPc(String seoKeywordsPc) {
        this.seoKeywordsPc = seoKeywordsPc;
    }

    public String getSeoDescriptionPc() {
        return seoDescriptionPc;
    }

    public void setSeoDescriptionPc(String seoDescriptionPc) {
        this.seoDescriptionPc = seoDescriptionPc;
    }

    public String getSeoH1tagPc() {
        return seoH1tagPc;
    }

    public void setSeoH1tagPc(String seoH1tagPc) {
        this.seoH1tagPc = seoH1tagPc;
    }

    public String getSeoTitleMobile() {
        return seoTitleMobile;
    }

    public void setSeoTitleMobile(String seoTitleMobile) {
        this.seoTitleMobile = seoTitleMobile;
    }

    public String getSeoKeywordsMobile() {
        return seoKeywordsMobile;
    }

    public void setSeoKeywordsMobile(String seoKeywordsMobile) {
        this.seoKeywordsMobile = seoKeywordsMobile;
    }

    public String getSeoDescriptionMobile() {
        return seoDescriptionMobile;
    }

    public void setSeoDescriptionMobile(String seoDescriptionMobile) {
        this.seoDescriptionMobile = seoDescriptionMobile;
    }

    public String getSeoH1tagMobile() {
        return seoH1tagMobile;
    }

    public void setSeoH1tagMobile(String seoH1tagMobile) {
        this.seoH1tagMobile = seoH1tagMobile;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

}
