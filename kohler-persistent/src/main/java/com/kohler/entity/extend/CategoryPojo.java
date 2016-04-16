/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.entity.extend;

import com.kohler.entity.CategoryEntity;
import com.kohler.entity.CategoryHeroAreaEntity;
import com.kohler.entity.LocaleEntity;

/**
 * Class Function Description Product
 * 
 * @author XHY
 * @Date 2014年10月8日
 */
public class CategoryPojo extends CategoryEntity {
	
	private static final long serialVersionUID = 6042065009897319111L;
	
	private Integer[] categoryIds;
	private Integer[] langs;
	private String[] categoryNames;
	private String[] breadcrumbNames;
	private Integer[] pcTemplateIds;
	private Integer[] mobileTemplateIds;
	private String[] seoTitlePcs;
	private String[] seoKeywordsPcs;
	private String[] seoDescriptionPcs;
	private String[] seoH1tagPcs;
	private String[] seoTitleMobiles;
	private String[] seoKeywordsMobiles;
	private String[] seoDescriptionMobiles;
	private String[] seoH1tagMobiles;
	private String[] heroAreaMetadataIds;
	
	private String[] bannerUnitMetadataIds;
	
	private Integer[] childCategoryIds;
	
	
	
	private Integer categoryMetadataId;
	private Integer level;
	private Integer parentId;
	private Integer seqNo;
	
	private LocaleEntity language;
	
	private CategoryEntity category;
	
	private CategoryHeroAreaEntity heroArea;
	
	private String fieldValue;
	
	private String fieldValueId;
	
	private Integer[] categoryHeroAreaIds;
	
	


	public Integer[] getLangs() {
		return langs;
	}
	public void setLangs(Integer[] langs) {
		this.langs = langs;
	}
	public String[] getCategoryNames() {
		return categoryNames;
	}
	public void setCategoryNames(String[] categoryNames) {
		this.categoryNames = categoryNames;
	}
	public Integer[] getPcTemplateIds() {
		return pcTemplateIds;
	}
	public void setPcTemplateIds(Integer[] pcTemplateIds) {
		this.pcTemplateIds = pcTemplateIds;
	}
	public Integer[] getMobileTemplateIds() {
		return mobileTemplateIds;
	}
	public void setMobileTemplateIds(Integer[] mobileTemplateIds) {
		this.mobileTemplateIds = mobileTemplateIds;
	}
	public String[] getSeoTitlePcs() {
		return seoTitlePcs;
	}
	public void setSeoTitlePcs(String[] seoTitlePcs) {
		this.seoTitlePcs = seoTitlePcs;
	}
	public String[] getSeoKeywordsPcs() {
		return seoKeywordsPcs;
	}
	public void setSeoKeywordsPcs(String[] seoKeywordsPcs) {
		this.seoKeywordsPcs = seoKeywordsPcs;
	}
	public String[] getSeoDescriptionPcs() {
		return seoDescriptionPcs;
	}
	public void setSeoDescriptionPcs(String[] seoDescriptionPcs) {
		this.seoDescriptionPcs = seoDescriptionPcs;
	}
	public String[] getSeoH1tagPcs() {
		return seoH1tagPcs;
	}
	public void setSeoH1tagPcs(String[] seoH1tagPcs) {
		this.seoH1tagPcs = seoH1tagPcs;
	}
	public String[] getSeoTitleMobiles() {
		return seoTitleMobiles;
	}
	public void setSeoTitleMobiles(String[] seoTitleMobiles) {
		this.seoTitleMobiles = seoTitleMobiles;
	}
	public String[] getSeoKeywordsMobiles() {
		return seoKeywordsMobiles;
	}
	public void setSeoKeywordsMobiles(String[] seoKeywordsMobiles) {
		this.seoKeywordsMobiles = seoKeywordsMobiles;
	}
	public String[] getSeoDescriptionMobiles() {
		return seoDescriptionMobiles;
	}
	public void setSeoDescriptionMobiles(String[] seoDescriptionMobiles) {
		this.seoDescriptionMobiles = seoDescriptionMobiles;
	}
	public String[] getSeoH1tagMobiles() {
		return seoH1tagMobiles;
	}
	public void setSeoH1tagMobiles(String[] seoH1tagMobiles) {
		this.seoH1tagMobiles = seoH1tagMobiles;
	}
	public LocaleEntity getLanguage() {
		return language;
	}
	public void setLanguage(LocaleEntity language) {
		this.language = language;
	}
	public Integer getCategoryMetadataId() {
		return categoryMetadataId;
	}
	public void setCategoryMetadataId(Integer categoryMetadataId) {
		this.categoryMetadataId = categoryMetadataId;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}
	public CategoryEntity getCategory() {
		return category;
	}
	public void setCategory(CategoryEntity category) {
		this.category = category;
	}
	public Integer[] getCategoryIds() {
		return categoryIds;
	}
	public void setCategoryIds(Integer[] categoryIds) {
		this.categoryIds = categoryIds;
	}
	public String[] getBreadcrumbNames() {
		return breadcrumbNames;
	}
	public void setBreadcrumbNames(String[] breadcrumbNames) {
		this.breadcrumbNames = breadcrumbNames;
	}
	
	public String[] getHeroAreaMetadataIds() {
		return heroAreaMetadataIds;
	}
	public void setHeroAreaMetadataIds(String[] heroAreaMetadataIds) {
		this.heroAreaMetadataIds = heroAreaMetadataIds;
	}
	public CategoryHeroAreaEntity getHeroArea() {
		return heroArea;
	}
	public void setHeroArea(CategoryHeroAreaEntity heroArea) {
		this.heroArea = heroArea;
	}
	public String getFieldValue() {
		return fieldValue;
	}
	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}
	public Integer[] getCategoryHeroAreaIds() {
		return categoryHeroAreaIds;
	}
	public void setCategoryHeroAreaIds(Integer[] categoryHeroAreaIds) {
		this.categoryHeroAreaIds = categoryHeroAreaIds;
	}
	public String[] getBannerUnitMetadataIds() {
		return bannerUnitMetadataIds;
	}
	public void setBannerUnitMetadataIds(String[] bannerUnitMetadataIds) {
		this.bannerUnitMetadataIds = bannerUnitMetadataIds;
	}
	public Integer[] getChildCategoryIds() {
		return childCategoryIds;
	}
	public void setChildCategoryIds(Integer[] childCategoryIds) {
		this.childCategoryIds = childCategoryIds;
	}
	public String getFieldValueId() {
		return fieldValueId;
	}
	public void setFieldValueId(String fieldValueId) {
		this.fieldValueId = fieldValueId;
	}
	
	
}