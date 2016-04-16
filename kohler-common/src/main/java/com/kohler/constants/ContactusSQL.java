package com.kohler.constants;

public class ContactusSQL {
    public static final String SELECT_Contactus_WITH_MAP = "SELECT ci.CONTACT_INFO_ID,a.REAL_NAME,a.EMAIL,ci.CONTACT_DATE,ci.CONTACT_MESSAGE,ur.REAL_NAME as HANDLE_USER,t.MASTERDATA_NAME as CONTACT_STATUS "    
            + " FROM CONTACT_INFO ci "
            + " inner JOIN  ACCOUNT a on (a.ACCOUNT_ID = ci.ACCOUNT_ID) "
            + " inner JOIN  MASTERDATA  t on (t.MASTERDATA_METADATA_ID = ci.CONTACT_STATUS and t.LANG =1)"
            + " inner JOIN  SYS_USER ur on (ur.SYS_USER_ID = ci.HANDLE_USER)"
            + " WHERE ci.IS_ENABLE=1 and t.IS_ENABLE=1 and ur.IS_ENABLE=1 and a.IS_ENABLE=1";
    public static final String SELECT_Contactus_WITH_COUNT= "SELECT count(*) "    
            + " FROM CONTACT_INFO ci "
            + " inner JOIN  ACCOUNT a  on (a.ACCOUNT_ID = ci.ACCOUNT_ID) "
            + " inner JOIN  MASTERDATA  t on (t.MASTERDATA_METADATA_ID = ci.CONTACT_STATUS and t.LANG =1)"
            + " inner JOIN  SYS_USER ur on (ur.SYS_USER_ID = ci.HANDLE_USER)"
            + " WHERE ci.IS_ENABLE=1 and t.IS_ENABLE=1 and ur.IS_ENABLE=1 and a.IS_ENABLE=1";

    public static final String SELECT_Contactus_VIEW="SELECT ci.CONTACT_INFO_ID,ci.REPLY_DATE,ci.REPLY_CONTENT,a.REAL_NAME,a.EMAIL,ci.CONTACT_DATE,ci.CONTACT_MESSAGE,ur.REAL_NAME as HANDLE_USER,t.MASTERDATA_NAME as CONTACT_STATUS "    
            + " FROM CONTACT_INFO ci "
            + " inner JOIN  ACCOUNT a  on (a.ACCOUNT_ID = ci.ACCOUNT_ID) "
            + " inner JOIN  MASTERDATA  t on (t.MASTERDATA_METADATA_ID = ci.CONTACT_STATUS and t.LANG =1)"
            + " inner JOIN  SYS_USER ur on (ur.SYS_USER_ID = ci.HANDLE_USER)"
            + " WHERE ci.CONTACT_INFO_ID=? and  ci.IS_ENABLE=1 and t.IS_ENABLE=1 and ur.IS_ENABLE=1 and a.IS_ENABLE=1  LIMIT 1";
}
