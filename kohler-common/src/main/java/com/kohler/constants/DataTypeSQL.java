/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.constants;

/**
 * Section SQL
 *
 * @author liuzhiyong
 * @Date 2014年9月27日
 */
public class DataTypeSQL {
    
    public static final String SELECT_COUNT_DATATYPE_ALL       = "SELECT COUNT(*) FROM DATATYPE WHERE IS_ENABLE = 1";
    
    public static final String SELECT_DATATYPE_ALL_DATATYPE       = "SELECT * FROM DATATYPE where IS_ENABLE = 1 AND DATATYPE_NAME LIKE ?";
    
    public static final String SELECT_DATATYPE_ALL       = "SELECT * FROM DATATYPE WHERE IS_ENABLE = 1";
    
    public static final String SELECT_COUNT_DATATYPE_ALL_DATATYPE       = "SELECT COUNT(*) from DATATYPE WHERE IS_ENABLE = 1 AND DATATYPE_NAME LIKE ?";

    /*
     * 按照类型查询DATATYPE 
     */
    public static final String SELECT_DATATYPE_BY_TYPE = "SELECT DATATYPE_NAME FROM DATATYPE where DATATYPE_TYPE = ? and IS_ENABLE = 1 ";
    
    public static final String SELECT_DATATYPE_ALL_DATATYPE_WITH_MAP       = "SELECT * FROM DATATYPE WHERE IS_ENABLE = 1 and DATATYPE_NAME LIKE ?  ORDER BY MODIFY_TIME DESC limit ?,?";

    public static final String UPDATE_DATATYPE_IS_ENBALE_TO_0 			= "UPDATE DATATYPE SET IS_ENABLE=0 where DATATYPE_ID IN ? ";
    
    public static final String SELECT_MSTERDATA 			= "SELECT md.* FROM MASTERDATA as md,MASTERDATA_METADATA as mdmd,MASTERDATA_TYPE as mdt WHERE mdt.MASTERDATA_TYPE_ID = mdmd.MASTERDATA_TYPE_ID AND mdmd.MASTERDATA_METADATA_ID = md.MASTERDATA_METADATA_ID "
    			+ "AND mdt.REMARK = 'DataType类型' AND md.IS_ENABLE = 1 AND mdmd.IS_ENABLE = 1 AND mdt.IS_ENABLE = 1 AND md.LANG=2 AND 1=? ";
}
