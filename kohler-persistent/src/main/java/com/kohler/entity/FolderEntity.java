package com.kohler.entity;

/**
 * 
 * Folder Entity
 *
 * @author sana
 * @Date 2014年11月21日
 */
public class FolderEntity extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1944758650293847138L;

    private Integer folderId;

    private Integer parentId;

    private String folderName;

    private String folderPath;

    private Integer fileType;

    private String remark;


    public Integer getFolderId() {
        return folderId;
    }

    public void setFolderId(Integer folderId) {
        this.folderId = folderId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName == null ? null : folderName.trim();
    }

    public String getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath == null ? null : folderPath.trim();
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

  
}