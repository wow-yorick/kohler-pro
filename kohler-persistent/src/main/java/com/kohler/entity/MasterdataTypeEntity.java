/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.entity;

/**
 * MasterdataTypeEntity
 *
 * @author Administrator
 * @Date 2014年11月20日
 */
public class MasterdataTypeEntity  extends BaseEntity {
    private Integer masterdataTypeId;
    private String masterdataTypeName;
    private String remark;
    

    public Integer getMasterdataTypeId() {
        return masterdataTypeId;
    }

    public void setMasterdataTypeId(Integer masterdataTypeId) {
        this.masterdataTypeId = masterdataTypeId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMasterdataTypeName() {
        return masterdataTypeName;
    }

    public void setMasterdataTypeName(String masterdataTypeName) {
        this.masterdataTypeName = masterdataTypeName;
    }
}
