package com.kohler.constants;

public class AccountSQL {
    public static final String SELECT_ACCOUNT_WITH_MAP = " SELECT a.ACCOUNT_ID, "
            + " a.ACCOUNT_NAME,a.REAL_NAME,a.COMPANY_TELEPHONE,a.EMAIL,t.MASTERDATA_NAME as AccountType,s.MASTERDATA_NAME as accountstatus  "
            + " FROM ACCOUNT a "
            + " inner JOIN  MASTERDATA  t on (t.MASTERDATA_METADATA_ID = a.ACCOUNT_TYPE and t.LANG =1)"
            + " inner JOIN  MASTERDATA  s on (s.MASTERDATA_METADATA_ID = a.STATUS and s.LANG =1)"
            + " WHERE a.IS_ENABLE = 1 and t.IS_ENABLE=1 and s.IS_ENABLE=1 ";
            //+ " ORDER BY a.ACCOUNT_ID DESC ";
    
    public static final String SELECT_ACCOUNT_COUNT  = " SELECT count(*) "
            + " FROM ACCOUNT a "
            + " inner JOIN  MASTERDATA  t on (t.MASTERDATA_METADATA_ID = a.ACCOUNT_TYPE and t.LANG =1)"
            + " inner JOIN  MASTERDATA  s on (s.MASTERDATA_METADATA_ID = a.STATUS and s.LANG =1)"
            + " WHERE a.IS_ENABLE = 1 and t.IS_ENABLE=1 and s.IS_ENABLE=1 ";
    
    public static final String SELECT_ACCOUNT_VIEW   ="SELECT a.*,ap.AREA_NAME as PROVINCE,ac.AREA_NAME as CITY,ad.AREA_NAME as DISTRICT,t.MASTERDATA_NAME as accounttype,s.MASTERDATA_NAME as accountstatus"
            + " FROM ACCOUNT a "
            + " inner JOIN  MASTERDATA  t on (t.MASTERDATA_METADATA_ID = a.ACCOUNT_TYPE and t.LANG =1)"
            + " inner JOIN  MASTERDATA  s on (s.MASTERDATA_METADATA_ID = a.STATUS and s.LANG =1)"
            + " LEFT JOIN AREA ap on (ap.AREA_METADATA_ID=a.PROVINCE and ap.LANG =1)"
            + " LEFT JOIN AREA ac on (ac.AREA_METADATA_ID=a.CITY and ac.LANG =1)"
            + " LEFT JOIN AREA ad on (ad.AREA_METADATA_ID=a.DISTRICT and ad.LANG =1)"
            + " WHERE ACCOUNT_ID=? and a.IS_ENABLE = 1 and t.IS_ENABLE=1 and s.IS_ENABLE=1 LIMIT 1 ";
    
    //public static final String SELECT_ACCOUNT_PROVINCE ="SELECT PROVINCE FROM AREA WHERE AREA_METADATA_ID = ? LANG =1 LIMIT 1";
    
    //public static final String SELECT_ACCOUNT_CITY ="SELECT CITY FROM AREA WHERE AREA_METADATA_ID = ? LANG =1 LIMIT 1";
    
    //public static final String SELECT_ACCOUNT_DISTRICT ="SELECT DISTRICT FROM AREA WHERE AREA_METADATA_ID = ? LANG =1 LIMIT 1";
    
    public static final String SELECT_ACCOUNT_STATUS  ="SELECT MASTERDATA_METADATA_ID FROM MASTERDATA WHERE MASTERDATA_METADATA_ID=? and LANG=1 LIMIT 1" ;

    public static final String Update_ACCOUNT="UPDATE  ACCOUNT SET STATUS=?,FREEZE_REASON=? WHERE ACCOUNT_ID=? ";
    
    public static final String Update_ACCOUNT_PASSWORD="UPDATE  ACCOUNT SET PASSWORD=? WHERE ACCOUNT_ID=? ";
}   
