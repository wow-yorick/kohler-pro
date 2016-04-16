/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.entity;

/**
 * Class Function Description
 *
 * @author Sweety
 * @Date 2014年9月28日
 */
public class PublishFolderEntity extends BaseEntity{
    
    private static final long serialVersionUID = -2764032232107938117L;
    
    private Integer publishFolderId;
    private String publishFolderName;
    private String publishFolderPath;
    
    public Integer getPublishFolderId() {
        return publishFolderId;
    }
    public void setPublishFolderId(Integer publishFolderId) {
        this.publishFolderId = publishFolderId;
    }
    public String getPublishFolderName() {
        return publishFolderName;
    }
    public void setPublishFolderName(String publishFolderName) {
        this.publishFolderName = publishFolderName;
    }
    public String getPublishFolderPath() {
        return publishFolderPath;
    }
    public void setPublishFolderPath(String publishFolderPath) {
        this.publishFolderPath = publishFolderPath;
    }
    
}
