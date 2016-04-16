/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.solr.bean;

import org.apache.solr.client.solrj.beans.Field;

/**
 * solr product entity
 * 
 * @author zqq
 * @Date 10/9/2014
 */
@SuppressWarnings("serial")
public class ContentSolrBean implements java.io.Serializable {

    private static final long serialVersionUID = 2L;

    @Field("id")
    private String            id;

    @Field("pageId")
    private String            pageId;

    @Field("contentMetadataId")
    private String            contentMetadataId;

    @Field("imageId")
    private String            imageId;

    @Field("date")
    private String            date;

    @Field("title")
    private String            title;

    @Field("imageUrl")
    private String            imageUrl;

    @Field("imageSource")
    private String            imageSource;

    @Field("content")
    private String            content;

    @Field("contentUrl")
    private String            contentUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getContentMetadataId() {
        return contentMetadataId;
    }

    public void setContentMetadataId(String contentMetadataId) {
        this.contentMetadataId = contentMetadataId;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
}
