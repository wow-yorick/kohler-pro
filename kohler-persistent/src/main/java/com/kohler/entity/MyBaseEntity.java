package com.kohler.entity;

import java.io.Serializable;
import java.util.Date;

public class MyBaseEntity implements Serializable{
        
        /**
     * 
     */
    private static final long serialVersionUID = -6352531379309975502L;
        private String createUser;
        private Date createTime;
        private String modifyUser;
        private Date modifyTime;
        public String getCreateUser() {
            return createUser;
        }
        public void setCreateUser(String createUser) {
            this.createUser = createUser;
        }
        public Date getCreateTime() {
            return createTime;
        }
        public void setCreateTime(Date createTime) {
            this.createTime = createTime;
        }
        public String getModifyUser() {
            return modifyUser;
        }
        public void setModifyUser(String modifyUser) {
            this.modifyUser = modifyUser;
        }
        public Date getModifyTime() {
            return modifyTime;
        }
        public void setModifyTime(Date modifyTime) {
            this.modifyTime = modifyTime;
        }
}
