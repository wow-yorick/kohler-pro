package com.kohler.constants;

public class UserfolderSQL {
    public static final String SELECT_Userfolder_COUNT = " SELECT count(*)"
            + " FROM  ACCOUNT a"
            + " inner JOIN  ACCOUNT_FOLDER af USING(ACCOUNT_ID)"
            + " inner JOIN  ACCOUNT_FOLDER_DETAIL afd  USING(ACCOUNT_FOLDER_ID)"
            + " inner JOIN  MASTERDATA  m  on (m.MASTERDATA_METADATA_ID = afd.ACCOUNT_FOLDER_TYPE and m.LANG =1)"
            + " inner JOIN  MASTERDATA  m1 on (m1.MASTERDATA_METADATA_ID = a.ACCOUNT_TYPE and m1.LANG =1)"
            + " WHERE a.IS_ENABLE=1 and af.IS_ENABLE=1 and afd.IS_ENABLE=1 and m.IS_ENABLE=1 and m1.IS_ENABLE=1 ";
    public static final String SELECT_Userfolder_WITH_MAP = " SELECT afd.ACCOUNT_FOLDER_DETAIL_ID,a.ACCOUNT_NAME,a.REAL_NAME,a.ACCOUNT_TYPE,af.ACCOUNT_FOLDER_NAME,afd.ACCOUNT_FOLDER_TYPE,afd.FOLDER_DETAIL_ID,m.MASTERDATA_NAME as titleid,m1.MASTERDATA_NAME"
                + " FROM  ACCOUNT a"
                + " inner JOIN  ACCOUNT_FOLDER af USING(ACCOUNT_ID)"
                + " inner JOIN  ACCOUNT_FOLDER_DETAIL afd  USING(ACCOUNT_FOLDER_ID)"
                + " inner JOIN  MASTERDATA  m  on (m.MASTERDATA_METADATA_ID = afd.ACCOUNT_FOLDER_TYPE and m.LANG =1)"
                + " inner JOIN  MASTERDATA  m1 on (m1.MASTERDATA_METADATA_ID = a.ACCOUNT_TYPE and m1.LANG =1)"
                + " WHERE a.IS_ENABLE=1 and af.IS_ENABLE=1 and afd.IS_ENABLE=1 and m.IS_ENABLE=1 and m1.IS_ENABLE=1 ";
    public static final String  SELECT_SKU_WITH_MAP = " SELECT SKU_NAME as titlename"
                + " FROM SKU "
                + " WHERE SKU_METADATA_ID = ? and IS_ENABLE=1 and LANG=1 limit 1";
    public static final String  SELECT_PRODUCT_VIDEO_WITH_MAP ="SELECT PRODUCT_VIDEO_NAME as titlename"
                + " FROM PRODUCT_VIDEO "
                + " WHERE PRODUCT_VIDEO_METADATA_ID = ? and IS_ENABLE=1"
                + " and LANG=1 limit 1";
    public static final String  SELECT_CONTENT_FIELD_VALUES_WITH_MAP="SELECT  FIELD_VALUE as titlename"
                + " FROM CONTENT_FIELD_VALUES "
                + " WHERE CONTENT_FIELD_VALUES_ID = ? and LANG=1 and  IS_ENABLE=1 and FIELD_NAME='title' limit 1";
    
    
}                
                  
