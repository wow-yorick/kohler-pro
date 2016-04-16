/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.constants;

/**
 * Section SQL
 *
 * @author shefeng
 * @Date 2014年9月27日
 */
public class SuiteSQL {
    public static final String DELETE_SUITE_BY_METADATA_ID = "UPDATE SUITE SET IS_ENABLE = 0 WHERE SUITE_METADATA_ID = ?";
    
    public static final String SELECT_SUITE_COUNT = "SELECT COUNT(*) FROM ("
            + "SELECT s.SUITE_ID as id,s.SUITE_NAME as suiteName "
            + " FROM SUITE s LEFT JOIN SUITE_METADATA m on s.SUITE_METADATA_ID = m.SUITE_METADATA_ID "
            + "  WHERE m.IS_ENABLE = 1 AND s.IS_ENABLE = 1 AND s.LANG = "+CommonConstants.LOCALE_CN+"  AND s.SUITE_NAME LIKE ?) t  ";
    
    public static final String SELECT_SUITE_BY_NAME = "SELECT * FROM ("
            + "SELECT s.SUITE_ID as id,s.SUITE_NAME as suiteName,s.SUITE_METADATA_ID as suiteMetadataId,s.CREATE_USER as createUser,s.CREATE_TIME as createTime "
            + " FROM SUITE  s LEFT JOIN SUITE_METADATA m on s.SUITE_METADATA_ID = m.SUITE_METADATA_ID "
            + " WHERE m.IS_ENABLE = 1  AND s.IS_ENABLE = 1 AND s.LANG = "+CommonConstants.LOCALE_CN+" AND  s.SUITE_NAME LIKE ?) t LIMIT ?,?";
    
    public static final String SELECT_SUITE_PRODUCT_BY_SUITE_METADATA_ID = "SELECT s.*,ku.SKU_CODE FROM SUITE_PRODUCT s "
            + " LEFT JOIN SKU_METADATA ku ON s.SKU_METADATA_ID = ku.SKU_METADATA_ID WHERE s.IS_ENABLE = 1 AND s.SUITE_METADATA_ID = ?";
    
    public static final String DELETE_SUITE_PRODUCT_BY_METADATA_ID = "UPDATE SUITE_PRODUCT SET IS_ENABLE = 0 WHERE SUITE_METADATA_ID = ?";
    
    public static final String DELETE_SUITE_HOTSPOT_BY_METADATA_ID = "UPDATE SUITE_HOT_SPOT SET IS_ENABLE = 0 WHERE SUITE_METADATA_ID = ?";
    
    public static final String SELECT_LOCALE_LIST = "SELECT LOCALE_ID AS localeId,LOCALE_NAME AS localeName,LOCALE_CODE AS localeCode,IS_DEFAULT AS isDefault "
            + "FROM LOCALE WHERE IS_ENABLE = 1";
    
    public static final String  SELECT_TEMPLATE_LIST = "SELECT TEMPLATE_ID AS templateId,TEMPLATE_TYPE AS templateType,TEMPLATE_NAME AS templateName "
            + "FROM TEMPLATE WHERE IS_ENABLE = 1 AND TEMPLATE_TYPE = ?";
    
    public static final String SELECT_SUITE_PRODUCT_DETAIL = "SELECT s.*,ku.SKU_CODE FROM SUITE_PRODUCT s "
            + " LEFT JOIN SKU_METADATA ku ON s.SKU_METADATA_ID = ku.SKU_METADATA_ID WHERE s.IS_ENABLE = 1 AND s.SUITE_PRODUCT_ID = ?";
    
    public static final String SELECT_SUITE_HOT_SPOT_DETAIL = "SELECT S.SUITE_HOT_SPOT_ID,S.SUITE_METADATA_ID,S.HOT_SPOT_ID,S.SEQ_NO FROM SUITE_HOT_SPOT S WHERE S.IS_ENABLE = 1 AND SUITE_METADATA_ID = ?";
    
}
