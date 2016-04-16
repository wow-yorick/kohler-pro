package com.kohler.entity;

/**
 * 
 * SiteSetting  Entity
 *
 * @author sana
 * @Date 2014年11月21日
 */
public class SiteSettingEntity extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = -8065987831698163906L;

    private Integer siteSettingId;

    private String siteName;

    private String siteDomainName;

    private String sitePath;


    public Integer getSiteSettingId() {
        return siteSettingId;
    }

    public void setSiteSettingId(Integer siteSettingId) {
        this.siteSettingId = siteSettingId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName == null ? null : siteName.trim();
    }

    public String getSiteDomainName() {
        return siteDomainName;
    }

    public void setSiteDomainName(String siteDomainName) {
        this.siteDomainName = siteDomainName == null ? null : siteDomainName.trim();
    }

    public String getSitePath() {
        return sitePath;
    }

    public void setSitePath(String sitePath) {
        this.sitePath = sitePath == null ? null : sitePath.trim();
    }

}