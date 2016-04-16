package com.kohler.constants;

public class PageFieldValuesSQL {
    public static final String GET_BY_NAME = "select PAGE_FIELD_VALUES_ID,PAGE_ID,FIELD_NAME,FIELD_VALUE,IS_ENABLE,CREATE_USER,CREATE_TIME,MODIFY_USER,now() as MODIFY_TIME from PAGE_FIELD_VALUES where IS_ENABLE = 1 and FIELD_NAME = ?";
    
    public static final String GET_BY_TIME = "select PAGE_FIELD_VALUES_ID,PAGE_ID,FIELD_NAME,FIELD_VALUE,IS_ENABLE,CREATE_USER,CREATE_TIME,MODIFY_USER,now() as MODIFY_TIME from PAGE_FIELD_VALUES where IS_ENABLE = 1 and FIELD_NAME = ? and MODIFY_TIME > ? and MODIFY_TIME < now()";
    
}
