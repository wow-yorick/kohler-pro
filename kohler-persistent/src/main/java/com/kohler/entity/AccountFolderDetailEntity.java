/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.entity;

public class AccountFolderDetailEntity  extends BaseEntity{
        
        /**
         * 
         */
        private static final long serialVersionUID = -6016632819168952178L;
        private Integer accountFolderDetailId;
        private Integer accountFolderId;
        private Integer accountFolderType;
        private Integer FolderDetailId;
        
        
        public Integer getAccountFolderDetailId() {
            return accountFolderDetailId;
        }
        public void setAccountFolderDetailId(Integer accountFolderDetailId) {
            this.accountFolderDetailId = accountFolderDetailId;
        }
        public Integer getAccountFolderId() {
            return accountFolderId;
        }
        public void setAccountFolderId(Integer accountFolderId) {
            this.accountFolderId = accountFolderId;
        }
        public Integer getAccountFolderType() {
            return accountFolderType;
        }
        public void setAccountFolderType(Integer accountFolderType) {
            this.accountFolderType = accountFolderType;
        }
        public Integer getFolderDetailId() {
            return FolderDetailId;
        }
        public void setFolderDetailId(Integer folderDetailId) {
            FolderDetailId = folderDetailId;
        }
        
}
