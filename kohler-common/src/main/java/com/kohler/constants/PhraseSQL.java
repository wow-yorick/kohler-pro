package com.kohler.constants;

public class PhraseSQL {

		public static final String SELECT_PHRASE_LIST = "select * from SEARCH_PHRASE where IS_ENABLE = 1 ";
		
		public static final String GET_PHRASE_COUNT = "select COUNT(*) from SEARCH_PHRASE where IS_ENABLE = 1 ";
		
		public static final String SELECT_ONE_PHRASE = "select * from SEARCH_PHRASE"
		        + " where SEARCH_PHRASE_ID = ? ";
		
		public static final String SELECT_ONE_PHRASE_FRO_NAME = "select * from SEARCH_PHRASE"
                + " where SETTING_VALUE = ? AND SEARCH_PHRASE_ID <> ? ";
		
}
