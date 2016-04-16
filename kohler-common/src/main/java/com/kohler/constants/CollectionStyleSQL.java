package com.kohler.constants;

public class CollectionStyleSQL {
    public static final String SELECT_CollectionStyle_COUNT   = " SELECT count(*) FROM COLLECTION_STYLE cs WHERE cs.LANG=1 and  cs.IS_ENABLE=1";
    public static final String SELECT_CollectionStyle_BY_NAME = " SELECT cm.COLLECTION_STYLE_METADATA_ID,cs.COLLECTION_STYLE_ID,cs.COLLECTION_STYLE_NAME,cm.SEQ_NO,cm.CREATE_USER,cm.CREATE_TIME "
                                                          + " FROM  COLLECTION_STYLE cs "
                                                          + " inner JOIN COLLECTION_STYLE_METADATA cm USING(COLLECTION_STYLE_METADATA_ID) "
                                                          + " WHERE cs.LANG=1 and cs.IS_ENABLE=1 and cm.IS_ENABLE=1 ";

   
}
