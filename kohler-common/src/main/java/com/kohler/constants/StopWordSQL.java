package com.kohler.constants;

public class StopWordSQL {

		public static final String SELECT_STOP_WORD_LIST = "select * from SEARCH_STOP_WORD where IS_ENABLE = 1 ";
		
		public static final String GET_STOP_WORD_COUNT = "select COUNT(*) from SEARCH_STOP_WORD where IS_ENABLE = 1 ";
		
		public static final String SELECT_ONE_STOP_WORD = "select * from SEARCH_STOP_WORD"
		        + " where SEARCH_STOP_WORD_ID = ? ";
		
		public static final String SELECT_ONE_STOP_WORD_FOR_NAME = "select * from SEARCH_STOP_WORD"
                + " where SETTING_VALUE = ? AND SEARCH_STOP_WORD_ID <> ?";

}
