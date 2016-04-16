/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.entity;

/**
 * Class Function Description
 *
 * @author Sweety
 * @Date 2014年9月26日
 */
public class DatatypeEntity extends BaseEntity{

    private static final long serialVersionUID = -3327506566075222661L;
    
    private Integer datatypeId;
    private Integer datatypeType;
    private String datatypeName;
    private String menuName;
    private String listDefinationXML;
    private String editDefinationXML;
    private String remark;
    
    /**
     * @return the datatypeId
     */
    public Integer getDatatypeId() {
        return datatypeId;
    }
    /**
     * @param datatypeId the datatypeId to set
     */
    public void setDatatypeId(Integer datatypeId) {
        this.datatypeId = datatypeId;
    }
    /**
     * @return the datatypeType
     */
    public Integer getDatatypeType() {
        return datatypeType;
    }
    /**
     * @param datatypeType the datatypeType to set
     */
    public void setDatatypeType(Integer datatypeType) {
        this.datatypeType = datatypeType;
    }
    
    /**
     * @return the datatypeName
     */
    public String getDatatypeName() {
        return datatypeName;
    }
    /**
     * @param datatypeName the datatypeName to set
     */
    public void setDatatypeName(String datatypeName) {
        this.datatypeName = datatypeName;
    }
    public String getMenuName() {
        return menuName;
    }
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    public String getListDefinationXML() {
        return listDefinationXML;
    }
    public void setListDefinationXML(String listDefinationXML) {
        this.listDefinationXML = listDefinationXML;
    }
    public String getEditDefinationXML() {
        return editDefinationXML;
    }
    public void setEditDefinationXML(String editDefinationXML) {
        this.editDefinationXML = editDefinationXML;
    }
    
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
}
