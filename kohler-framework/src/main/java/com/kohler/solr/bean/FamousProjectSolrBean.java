package com.kohler.solr.bean;

import org.apache.solr.client.solrj.beans.Field;

/**
 * Class Function Description
 *
 * @author sana
 * @Date 2014年12月26日
 */
public class FamousProjectSolrBean implements java.io.Serializable{
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
	
	@Field("famousProjectType")
	private String famousProjectType;
	
	@Field("famousProjectName")
	private String famousProjectName;
	
	@Field("famousProjectImageUrl")
	private String famousProjectImageUrl;
	
	@Field("famousProjectImageAlt")
    private String famousProjectImageAlt;
	
	@Field("famousProjectContent")
	private String famousProjectContent;

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
     * @return the famousProjectType
     */
    public String getFamousProjectType() {
        return famousProjectType;
    }

    /**
     * @param famousProjectType the famousProjectType to set
     */
    public void setFamousProjectType(String famousProjectType) {
        this.famousProjectType = famousProjectType;
    }

    /**
     * @return the famousProjectName
     */
    public String getFamousProjectName() {
        return famousProjectName;
    }

    /**
     * @param famousProjectName the famousProjectName to set
     */
    public void setFamousProjectName(String famousProjectName) {
        this.famousProjectName = famousProjectName;
    }

    /**
     * @return the famousProjectImageUrl
     */
    public String getFamousProjectImageUrl() {
        return famousProjectImageUrl;
    }

    /**
     * @param famousProjectImageUrl the famousProjectImageUrl to set
     */
    public void setFamousProjectImageUrl(String famousProjectImageUrl) {
        this.famousProjectImageUrl = famousProjectImageUrl;
    }

    /**
     * @return the famousProjectContent
     */
    public String getFamousProjectContent() {
        return famousProjectContent;
    }

    /**
     * @param famousProjectContent the famousProjectContent to set
     */
    public void setFamousProjectContent(String famousProjectContent) {
        this.famousProjectContent = famousProjectContent;
    }

    /**
     * @return the famousProjectImageAlt
     */
    public String getFamousProjectImageAlt() {
        return famousProjectImageAlt;
    }

    /**
     * @param famousProjectImageAlt the famousProjectImageAlt to set
     */
    public void setFamousProjectImageAlt(String famousProjectImageAlt) {
        this.famousProjectImageAlt = famousProjectImageAlt;
    }

	
	
}
