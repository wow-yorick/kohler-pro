/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.entity;

public class AccountFolderEntity  extends BaseEntity{

    /**
     * ACCOUNT_FOLDER_DETAIL
     */
    private static final long serialVersionUID = 1347510422137472489L;
    
    private Integer accountFolderId;
    private Integer accountId;
    private String accountFolderName;
    private String remark;
    
    public Integer getAccountFolderId() {
        return accountFolderId;
    }
    public void setAccountFolderId(Integer accountFolderId) {
        this.accountFolderId = accountFolderId;
    }
    public Integer getAccountId() {
        return accountId;
    }
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
    public String getAccountFolderName() {
        return accountFolderName;
    }
    public void setAccountFolderName(String accountFolderName) {
        this.accountFolderName = accountFolderName;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    

}
