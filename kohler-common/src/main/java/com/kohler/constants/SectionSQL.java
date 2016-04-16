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
public class SectionSQL {

    public static final String SELECT_SECTIONS_WITH_MAP        = "SELECT SECTION_ID AS id,"
                                                                       + "PARENT_ID AS pId,SECTION_NAME AS name,"
                                                                       + "CONCAT('sectionList.action?sectionId=',SECTION_ID) AS url,'_self' AS target FROM SECTION WHERE IS_ENABLE = 1";

    public static final String SELECT_SECTION_PAGE_COUNT_BY_ID = "SELECT COUNT(*) FROM ("
                                                                       + "SELECT SECTION_ID as id,SECTION_NAME as sectionName,'Section' as type FROM SECTION WHERE IS_ENABLE = 1 AND  PARENT_ID = ? "
                                                                       + "UNION ALL SELECT PAGE_ID AS id,PAGE_NAME as sectionName,'Page' AS type "
                                                                       + "FROM PAGE WHERE IS_ENABLE = 1 AND SECTION_ID = ?) t";

    public static final String SELECT_SECTION_PAGE_BY_ID  = "SELECT * FROM ("
                                                                       + "SELECT SECTION_ID as id,SECTION_NAME as sectionName,'Section' as type FROM SECTION WHERE IS_ENABLE = 1 AND  PARENT_ID = ? "
                                                                       + "UNION ALL SELECT PAGE_ID AS id,PAGE_NAME as sectionName,'Page' AS type "
                                                                       + "FROM PAGE WHERE IS_ENABLE = 1 AND  SECTION_ID = ?) t LIMIT ?,?";
    
    public static final String SELECT_SECTION_BY_NAME = "SELECT * FROM SECTION WHERE SECTION_NAME = ? AND IS_ENABLE = 1"; 
    
    public static final String SELECT_SECTION_BY_NAME_EXCEPT_SELF = "SELECT * FROM SECTION WHERE SECTION_NAME = ? AND IS_ENABLE = 1 AND SECTION_NAME <> ?"; 
}
