package com.kohler.constants;

public class SpellingCheckSQL {

		public static final String SELECT_SPELLING_CHECK_LIST = "select * from SEARCH_SPELLING_CHECK where IS_ENABLE = 1 ";
		
		public static final String GET_SPELLING_CHECK_COUNT = "select COUNT(*) from SEARCH_SPELLING_CHECK where IS_ENABLE = 1 ";
		
		public static final String SELECT_ONE_SPELLING_CHECK = "select * from SEARCH_SPELLING_CHECK"
		        + " where SEARCH_SPELLING_CHECK_ID = ? ";
		
		public static final String SELECT_ONE_SPELLING_CHECKF_FOR_NAME = "select * from SEARCH_SPELLING_CHECK"
                + " where SETTING_VALUE = ? AND SEARCH_SPELLING_CHECK_ID <> ?";

}
