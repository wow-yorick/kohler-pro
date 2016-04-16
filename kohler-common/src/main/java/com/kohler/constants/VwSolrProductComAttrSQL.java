package com.kohler.constants;

public class VwSolrProductComAttrSQL {
    public static final String GET_ALL        = "select * from VW_SOLR_PRODUCT_COM_ATTR";

    public static final String GET_ATTR = "select distinct KEY_NAME from VW_SOLR_PRODUCT_COM_ATTR where KEY_NAME!=''";
    
    public static final String GET_BY_CONDITION        = "select * from VW_SOLR_PRODUCT_COM_ATTR where PRODUCT_METADATA_ID = ? and LANG = ?";
}
