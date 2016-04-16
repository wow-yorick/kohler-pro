package com.kohler.constants;

public class NewProductSQL {
    public static final String SELECT_NEW_ARRIVAL_COUNT   = " SELECT count(*) FROM NEW_ARRIVAL WHERE LANG=1 and  IS_ENABLE=1";
    public static final String SELECT_NEW_ARRIVAL_BY_NAME = " SELECT nam.NEW_ARRIVAL_METADATA_ID,na.NEW_ARRIVAL_ID,na.NEW_ARRIVAL_NAME,c.CATEGORY_NAME,na.CREATE_USER,na.CREATE_TIME FROM NEW_ARRIVAL na "
                                                          + " inner JOIN CATEGORY c on (na.NEW_ARRIVAL_METADATA_ID = c.CATEGORY_METADATA_ID) "
                                                          + " inner JOIN  NEW_ARRIVAL_METADATA  nam USING(NEW_ARRIVAL_METADATA_ID)"
                                                          + " WHERE na.LANG=1 and nam.IS_ENABLE=1 and  na.IS_ENABLE=1 and c.LANG=1 and c.IS_ENABLE=1 ";

    public static final String SELECT_NEWCATEGORYMETADATA_List = " SELECT c.CATEGORY_METADATA_ID,c.CATEGORY_NAME FROM CATEGORY_METADATA AS  cm "
                                                               + " INNER JOIN CATEGORY AS c USING(CATEGORY_METADATA_ID)"
                                                               + " WHERE  cm.LEVEL=? and cm.IS_ENABLE=1 and c.lang=1 and  c.IS_ENABLE=1 ";
    
    public static final String SELECT_TEMPLATE_List  =" SELECT TEMPLATE_ID,TEMPLATE_NAME FROM TEMPLATE WHERE  "
                                                     +" WHERE TEMPLATE_TYPE=? ";
    
    public static final String SELECT_LOCALE_List    =" SELECT LOCALE_ID,LOCALE_NAME FROM LOCALE "
                                                     +" WHERE IS_ENABLE=? ";

    public static final String  SELECT_TEMPLATE_LIST = "SELECT TEMPLATE_ID AS templateId,TEMPLATE_TYPE AS templateType,TEMPLATE_NAME AS templateName "
            + "FROM TEMPLATE WHERE IS_ENABLE = 1 AND TEMPLATE_TYPE = ?";
    
    public static final String SELECT_HeroAreA_LIST  =" SELECT GROUP_CONCAT(nah.HERO_AREA_METADATA_ID) as HERO_AREA_METADATA_ID,GROUP_CONCAT(cfv.FIELD_VALUE) as FIELD_VALUE  FROM NEW_ARRIVAL_HERO_AREA nah "
                                                     + "inner JOIN CONTENT_FIELD_VALUES  cfv on(nah.HERO_AREA_METADATA_ID = cfv.CONTENT_METADATA_ID)"
                                                     + "WHERE nah.NEW_ARRIVAL_METADATA_ID=? and cfv.FIELD_NAME = ? and cfv.LANG = ?  LIMIT 1";
    
    public static final String SELECT_Bannerunit_LIST  =" SELECT GROUP_CONCAT(nah.BANNER_UNIT_METADATA_ID) as BANNER_UNIT_METADATA_ID,GROUP_CONCAT(cfv.FIELD_VALUE) as FIELD_VALUE  FROM NEW_ARRIVAL_BANNER_UNIT nah"
            + " inner JOIN CONTENT_FIELD_VALUES  cfv on(nah.BANNER_UNIT_METADATA_ID = cfv.CONTENT_METADATA_ID)"
            + " WHERE nah.NEW_ARRIVAL_METADATA_ID=? and cfv.FIELD_NAME = ? and cfv.LANG = ?  LIMIT 1";
    
    
}
