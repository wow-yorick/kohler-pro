package com.kohler.entity;

/**
 * 
 * FileAsset  Entity
 *
 * @author sana
 * @Date 2014年11月21日
 */
public class FileAssetEntity extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 5227059802505983773L;

    private Integer fileAssetId;

    private Integer folderId;

    private String fileAssetName;

    private Integer fileType;

    private String physicalFilename;

    private String remark;



    public Integer getFileAssetId() {
        return fileAssetId;
    }

    public void setFileAssetId(Integer fileAssetId) {
        this.fileAssetId = fileAssetId;
    }

    public Integer getFolderId() {
        return folderId;
    }

    public void setFolderId(Integer folderId) {
        this.folderId = folderId;
    }

    public String getFileAssetName() {
        return fileAssetName;
    }

    public void setFileAssetName(String fileAssetName) {
        this.fileAssetName = fileAssetName == null ? null : fileAssetName.trim();
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public String getPhysicalFilename() {
        return physicalFilename;
    }

    public void setPhysicalFilename(String physicalFilename) {
        this.physicalFilename = physicalFilename == null ? null : physicalFilename.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

   
}