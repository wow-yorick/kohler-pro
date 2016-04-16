/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.entity;

/**
 * Class Function Description
 * ProductCADMetadata
 * @author zhangtingting
 * @Date 2014年9月25日
 */
public class ProductCADMetadataEntity extends BaseEntity {

	private static final long serialVersionUID = 5861072656640506578L;
	
	private Integer productCADMetadataId;
	private Integer productMetadataId;
	private String suffix;
	private Integer cadType;
	
    public Integer getProductCADMetadataId() {
        return productCADMetadataId;
    }
    public void setProductCADMetadataId(Integer productCADMetadataId) {
        this.productCADMetadataId = productCADMetadataId;
    }
    public String getSuffix() {
        return suffix;
    }
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
    public Integer getCadType() {
        return cadType;
    }
    public void setCadType(Integer cadType) {
        this.cadType = cadType;
    }
    public Integer getProductMetadataId() {
        return productMetadataId;
    }
    public void setProductMetadataId(Integer productMetadataId) {
        this.productMetadataId = productMetadataId;
    }
}
