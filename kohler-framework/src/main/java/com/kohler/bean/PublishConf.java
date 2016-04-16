/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.bean;

import java.io.Serializable;

/**
 * 准备数据配置类
 *
 * @author Administrator
 * @Date 2014年10月21日
 */
public class PublishConf implements Serializable {
    private Integer dataId; //数据ID (主键ID如[产品页为PRODUCT_MATEDATA_ID]页面为PAGE_ID)
    private Integer lang; //语言
    private String websitePlatform;//网站平台
    private Integer templateType;//模板类型
    private Boolean isPreview = false; //是否为预览
    private String testPlatform;//TODO 测试平台 以后删除
    
    public Integer getDataId() {
        return dataId;
    }
    
    public void setDataId(Integer dataId) {
        this.dataId = dataId;
    }   
    
    public Integer getLang() {
        return lang;
    }
    
    public void setLang(Integer lang) {
        this.lang = lang;
    }


    public Boolean getIsPreview() {
        return isPreview;
    }

    public void setIsPreview(Boolean isPreview) {
        this.isPreview = isPreview;
    }

    public String getWebsitePlatform() {
        return websitePlatform;
    }

    public void setWebsitePlatform(String websitePlatform) {
        this.websitePlatform = websitePlatform;
    }

    public Integer getTemplateType() {
        return templateType;
    }

    public void setTemplateType(Integer templateType) {
        this.templateType = templateType;
    }

    public String getTestPlatform() {
        return testPlatform;
    }

    public void setTestPlatform(String testPlatform) {
        this.testPlatform = testPlatform;
    }


   
}