/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.entity;

/**
 * Class Function Description
 * Product
 * @author zhangtingting
 * @Date 2014年9月25日
 */
public class ProductEntity extends BaseEntity {

	private static final long serialVersionUID = 4346401062535188313L;
	
	private Integer productId;
	private Integer lang;
	private Integer productMetadataId;
	private String productName;
	private Integer pcTemplateId;
	private Integer mobileTemplateId;
	private String seoTitlePC;
	private String seoKeywordsPC;
	private String seoDescriptionPC;
	private String seoH1tagPC;	
	private String seoTitleMobile;
	private String seoKeywordsMobile;
	private String seoDescriptionMobile;
	private String seoH1tagMobile;
	private Boolean isCombineProduct;
	private Integer combineProductMetadataId;
	private Boolean isDiscontinue;
	
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
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
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
    public String getSeoTitlePC() {
        return seoTitlePC;
    }
    public void setSeoTitlePC(String seoTitlePC) {
        this.seoTitlePC = seoTitlePC;
    }
    public String getSeoDescriptionPC() {
        return seoDescriptionPC;
    }
    public void setSeoDescriptionPC(String seoDescriptionPC) {
        this.seoDescriptionPC = seoDescriptionPC;
    }
    public String getSeoTitleMobile() {
        return seoTitleMobile;
    }
    public void setSeoTitleMobile(String seoTitleMobile) {
        this.seoTitleMobile = seoTitleMobile;
    }
    public String getSeoDescriptionMobile() {
        return seoDescriptionMobile;
    }
    public void setSeoDescriptionMobile(String seoDescriptionMobile) {
        this.seoDescriptionMobile = seoDescriptionMobile;
    }
    public Integer getCombineProductMetadataId() {
        return combineProductMetadataId;
    }
    public void setCombineProductMetadataId(Integer combineProductMetadataId) {
        this.combineProductMetadataId = combineProductMetadataId;
    }
    public Boolean getIsCombineProduct() {
        return isCombineProduct;
    }
    public void setIsCombineProduct(Boolean isCombineProduct) {
        this.isCombineProduct = isCombineProduct;
    }
    public Boolean getIsDiscontinue() {
        return isDiscontinue;
    }
    public void setIsDiscontinue(Boolean isDiscontinue) {
        this.isDiscontinue = isDiscontinue;
    }
    public String getSeoH1tagPC() {
        return seoH1tagPC;
    }
    public void setSeoH1tagPC(String seoH1tagPC) {
        this.seoH1tagPC = seoH1tagPC;
    }
    public String getSeoH1tagMobile() {
        return seoH1tagMobile;
    }
    public void setSeoH1tagMobile(String seoH1tagMobile) {
        this.seoH1tagMobile = seoH1tagMobile;
    }
    public String getSeoKeywordsPC() {
        return seoKeywordsPC;
    }
    public void setSeoKeywordsPC(String seoKeywordsPC) {
        this.seoKeywordsPC = seoKeywordsPC;
    }
    public String getSeoKeywordsMobile() {
        return seoKeywordsMobile;
    }
    public void setSeoKeywordsMobile(String seoKeywordsMobile) {
        this.seoKeywordsMobile = seoKeywordsMobile;
    }
}
