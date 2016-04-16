package com.kohler.constants;

public class DbSolrMappingSQL {
    public static final String DELETE_BY_TYPE = "delete from DB_SOLR_MAPPING where IS_ENABLE = 1 and TYPE != ?";
}
