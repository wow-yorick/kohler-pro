package com.kohler.constants;

public class PublishLogSQL {
	
	public static final String CHECK_PUBLISH_WITH_MAP   = " SELECT * FROM PUBLISH_LOG WHERE PUBLISH_STATUS = ? and IS_ENABLE = 1  ORDER BY PUBLISH_TIME DESC LIMIT 1 ";

	public static final String GET_OLD_PUBLISH_WITH_MAP   = " SELECT * FROM PUBLISH_LOG WHERE PUBLISH_STATUS = ? and IS_ENABLE = 1  ORDER BY CREATE_TIME DESC LIMIT 1 ";
	
}
