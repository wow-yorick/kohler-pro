package com.kohler.entity;

public class NewArrivalEntity extends BaseEntity{
     /**
     * 
     */
     private static final long serialVersionUID = -4367104341165749663L;
     private  Integer newArrivalId;
     private  Integer lang;
     private  Integer newArrivalMetadataId;
     private  String  newArrivalName;
    
     private  Integer pcTemplateId;
     private  Integer mobileTemplateId;
     private  String  seoTitlePC;
     private  String  seoKeywordsPC;
     private  String  seoH1tagPC; 
     private  String  seoDescriptionPC;
     private  String  seoTitleMobile;
     private  String  seoKeywordsMobile;
     private  String  seoDescriptionMobile;
     private  String  seoH1tagMobile;
     
     public Integer getNewArrivalId() {
         return newArrivalId;
     }
     public void setNewArrivalId(Integer newArrivalId) {
         this.newArrivalId = newArrivalId;
     }
     public Integer getLang() {
         return lang;
     }
     public void setLang(Integer lang) {
         this.lang = lang;
     }
     public Integer getNewArrivalMetadataId() {
         return newArrivalMetadataId;
     }
     public void setNewArrivalMetadataId(Integer newArrivalMetadataId) {
         this.newArrivalMetadataId = newArrivalMetadataId;
     }
     public String getNewArrivalName() {
         return newArrivalName;
     }  
     public void setNewArrivalName(String newArrivalName) {
         this.newArrivalName = newArrivalName;
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
     public String getSeoKeywordsPC() {
         return seoKeywordsPC;
     }
     public void setSeoKeywordsPC(String seoKeywordsPC) {
         this.seoKeywordsPC = seoKeywordsPC;
     }
     public String getSeoDescriptionPC() {
         return seoDescriptionPC;
     }
     public void setSeoDescriptionPC(String seoDescriptionPC) {
         this.seoDescriptionPC = seoDescriptionPC;
     }
     public String getSeoH1tagPC() {
         return seoH1tagPC;
     }
     public void setSeoH1tagPC(String seoH1tagPC) {
         this.seoH1tagPC = seoH1tagPC;
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
     
     
}
