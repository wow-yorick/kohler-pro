/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.entity;

import java.util.Date;
/**
 * Class Function Description ContactInfoEntity
 * 
 * @author fujiajun
 * @Date 2014年9月25日
 */
public class ContactInfoEntity  extends BaseEntity {
    
    /**
     * 
     */
    private static final long serialVersionUID = -3803003713738115885L;
    private Integer contactInfoId;
    private Integer accountId;
    private Date contactDate;
    private String contactMessage;
    private Integer contactStatus;
    private String replyContent;
    private Integer handleUser;
    
    public Integer getContactInfoId() {
        return contactInfoId;
    }
    public void setContactInfoId(Integer contactInfoId) {
        this.contactInfoId = contactInfoId;
    }
    public Integer getAccountId() {
        return accountId;
    }
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
    public Date getContactDate() {
        return contactDate;
    }
    public void setContactDate(Date contactDate) {
        this.contactDate = contactDate;
    }
    public String getContactMessage() {
        return contactMessage;
    }
    public void setContactMessage(String contactMessage) {
        this.contactMessage = contactMessage;
    }
    public Integer getContactStatus() {
        return contactStatus;
    }
    public void setContactStatus(Integer contactStatus) {
        this.contactStatus = contactStatus;
    }
    public String getReplyContent() {
        return replyContent;
    }
    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }
    public Integer getHandleUser() {
        return handleUser;
    }
    public void setHandleUser(Integer handleUser) {
        this.handleUser = handleUser;
    }
}
