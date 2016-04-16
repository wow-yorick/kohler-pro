package com.kohler.entity;

/**
 * 
 * @author zhangqiqi
 * @Date 11/25/2014
 */
@SuppressWarnings("serial")
public class DbSolrMappingEntity extends BaseEntity implements java.io.Serializable {
    private Integer dbSolrMappingId;

    private Integer type;

    private String  dbFieldName;

    private String  solrFieldName;

    public Integer getDbSolrMappingId() {
        return dbSolrMappingId;
    }

    public void setDbSolrMappingId(Integer dbSolrMappingId) {
        this.dbSolrMappingId = dbSolrMappingId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDbFieldName() {
        return dbFieldName;
    }

    public void setDbFieldName(String dbFieldName) {
        this.dbFieldName = dbFieldName;
    }

    public String getSolrFieldName() {
        return solrFieldName;
    }

    public void setSolrFieldName(String solrFieldName) {
        this.solrFieldName = solrFieldName;
    }

}
