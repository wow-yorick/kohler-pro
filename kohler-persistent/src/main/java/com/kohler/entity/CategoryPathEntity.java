package com.kohler.entity;

/**
 * 
 * @author zhangqiqi
 * @Date 11/25/2014
 */
@SuppressWarnings("serial")
public class CategoryPathEntity implements java.io.Serializable {
    private Integer categoryMetadataId;

    private Integer parentId;

    private String  pcPath;

    private String  mobilePath;
    
    private String  pcFolderPath;

    private String  mobileFolderPath;

    public String getPcFolderPath() {
        return pcFolderPath;
    }

    public void setPcFolderPath(String pcFolderPath) {
        this.pcFolderPath = pcFolderPath;
    }

    public String getMobileFolderPath() {
        return mobileFolderPath;
    }

    public void setMobileFolderPath(String mobileFolderPath) {
        this.mobileFolderPath = mobileFolderPath;
    }

    public Integer getCategoryMetadataId() {
        return categoryMetadataId;
    }

    public void setCategoryMetadataId(Integer categoryMetadataId) {
        this.categoryMetadataId = categoryMetadataId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getPcPath() {
        return pcPath;
    }

    public void setPcPath(String pcPath) {
        this.pcPath = pcPath;
    }

    public String getMobilePath() {
        return mobilePath;
    }

    public void setMobilePath(String mobilePath) {
        this.mobilePath = mobilePath;
    }

}
