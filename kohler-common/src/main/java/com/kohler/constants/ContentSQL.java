/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.constants;

/**
 * Section SQL
 *
 * @author WHH
 * @Date 2014年10月28日
 */
public class ContentSQL {

    public static final String SELECT_DATATYPES_WITH_MAP        = "select DATATYPE_ID as id,"
                                                                       + "DATATYPE_NAME as name,"
                                                                       + "CONCAT('contentList.action?datatypeId=',DATATYPE_ID) AS url,'_self' AS target from DATATYPE where IS_ENABLE =1 and DATATYPE_TYPE = 10100001";
    
    public static final String SELECT_CONTENT_COUNT_BY_ID = "SELECT COUNT(*) FROM "
                                                                       + "CONTENT_METADATA WHERE IS_ENABLE = 1 AND DATATYPE_ID = ?";

    public static final String SELECT_CONTENT_BY_ID  = "SELECT * FROM "
                                                                       + "CONTENT_METADATA WHERE IS_ENABLE = 1 AND DATATYPE_ID = ? LIMIT ?,?";
    
    public static final String SELECT_CONTENT_VALUE_BY_ID  = "SELECT v.CONTENT_METADATA_ID,v.FIELD_VALUE FROM CONTENT_FIELD_VALUES v LEFT JOIN CONTENT_METADATA m on m.CONTENT_METADATA_ID = v.CONTENT_METADATA_ID where m.DATATYPE_ID = ? and v.IS_ENABLE = 1 and m.IS_ENABLE = 1 ";

    public static final String DELETE_CONTENTMETADATA_BY_METADATA_ID = "UPDATE CONTENT_METADATA SET IS_ENABLE = 0 WHERE CONTENT_METADATA_ID = ?";

    public static final String DELETE_CONTENTFIELDVALUES_BY_METADATA_ID = "UPDATE CONTENT_FIELD_VALUES SET IS_ENABLE = 0 WHERE CONTENT_METADATA_ID = ?";

    public static final String SELECT_CONTENTFIELDVALUES_BY_METADATA_ID = "select * from CONTENT_FIELD_VALUES where IS_ENABLE = 1 and CONTENT_METADATA_ID = ?";

    public static final String SELECT_CONTENTFIELDVALUES_BY_NAME = "select * from CONTENT_FIELD_VALUES where IS_ENABLE = 1 AND FIELD_NAME = ? AND CONTENT_METADATA_ID = ? AND LANG = 1 ";

    public static final String SELECT_CONTENTFIELDVALUES_BY_NAME_LANG = "select * from CONTENT_FIELD_VALUES where IS_ENABLE = 1 AND FIELD_NAME = ? AND CONTENT_METADATA_ID = ? AND LANG = ?";;

    public static final String SELECT_LOCAL_LANG = "select * from LOCALE where IS_DEFAULT = 1 ORDER BY LOCALE_ID";
    
    public static final String SELECT_MASTERDATA_BY_NAME_LANG = "select m.* from MASTERDATA m"+
                                                                    " left join MASTERDATA_METADATA mm on m.MASTERDATA_METADATA_ID = mm.MASTERDATA_METADATA_ID"+
                                                                    " left join MASTERDATA_TYPE mt on mm.MASTERDATA_TYPE_ID = mt.MASTERDATA_TYPE_ID"+
                                                                    " where mt.MASTERDATA_TYPE_NAME = ? and m.LANG = ?";

    public static final String SELECT_AREA_BY_PARENT_ID = "select * FROM AREA a "+
                                                            " left join AREA_METADATA am on a.AREA_METADATA_ID = am.AREA_METADATA_ID "+
                                                            " left join LOCALE l on a.LANG = l.LOCALE_ID "+
                                                            " where am.PARENT_ID = ? and l.IS_DEFAULT = 1;";
}
