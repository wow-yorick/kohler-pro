/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.entity;
/**
 * Class Function Description AccountEntity
 * 
 * @author fujiajun
 * @Date 2014年9月25日
 */
public class AccountEntity  extends BaseEntity{

    /**
     * 
     */
    private static final long serialVersionUID = 5172053302935853006L;
    private Integer accountId;
    private String accountName;
    private String password;
    private Integer accountType;
    private String realName;
    private String email;
    private String companyName;
    private String companyTelephone;
    private String companyDepartment;
    private String companyPosition;
    private Integer province;
    private Integer city;
    private Integer district;
    private String address;
    private String creditPoint;
    private Integer status;
    private String freezeReason;
    
    public String getFreezeReason() {
        return freezeReason;
    }
    public void setFreezeReason(String freezeReason) {
        this.freezeReason = freezeReason;
    }
    public Integer getAccountId() {
        return accountId;
    }
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
    public String getAccountName() {
        return accountName;
    }
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    public String getPassWord() {
        return password;
    }
    public void setpassword(String passWord) {
        this.password = passWord;
    }
    public Integer getAccountType() {
        return accountType;
    }
    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }
    public String getRealName() {
        return realName;
    }
    public void setRealName(String realName) {
        this.realName = realName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public String getCompanyTelephone() {
        return companyTelephone;
    }
    public void setCompanyTelephone(String companyTelephone) {
        this.companyTelephone = companyTelephone;
    }
    public String getCompanyDepartment() {
        return companyDepartment;
    }
    public void setCompanyDepartment(String companyDepartment) {
        this.companyDepartment = companyDepartment;
    }
    public String getCompanyPosition() {
        return companyPosition;
    }
    public void setCompanyPosition(String companyPosition) {
        this.companyPosition = companyPosition;
    }
    public Integer getProvince() {
        return province;
    }
    public void setProvince(Integer province) {
        this.province = province;
    }
    public Integer getCity() {
        return city;
    }
    public void setCity(Integer city) {
        this.city = city;
    }
    public Integer getDistrict() {
        return district;
    }
    public void setDistrict(Integer district) {
        this.district = district;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getCreditPoint() {
        return creditPoint;
    }
    public void setCreditPoint(String creditPoint) {
        this.creditPoint = creditPoint;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    
}
