/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.entity;

/**
 * Class Function Description
 * ProductPart
 * @author zhangtingting
 * @Date 2014年9月25日
 */
public class ProductPartEntity extends BaseEntity {

	private static final long serialVersionUID = 4346401062535188313L;
	
	private Integer productPartId;
	private Integer lang;
	private Integer productPartMetadataId;
	private String productPartName;
	private Integer imageSource;
	private String imageUrl;
	private Integer imageId;
	
    public Integer getProductPartId() {
        return productPartId;
    }
    public void setProductPartId(Integer productPartId) {
        this.productPartId = productPartId;
    }
    public Integer getLang() {
        return lang;
    }
    public void setLang(Integer lang) {
        this.lang = lang;
    }
    public Integer getProductPartMetadataId() {
        return productPartMetadataId;
    }
    public void setProductPartMetadataId(Integer productPartMetadataId) {
        this.productPartMetadataId = productPartMetadataId;
    }
    public String getProductPartName() {
        return productPartName;
    }
    public void setProductPartName(String productPartName) {
        this.productPartName = productPartName;
    }
    public Integer getImageSource() {
        return imageSource;
    }
    public void setImageSource(Integer imageSource) {
        this.imageSource = imageSource;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public Integer getImageId() {
        return imageId;
    }
    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }
}
