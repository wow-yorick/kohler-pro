package com.kohler.constants;

public class SynonymSQL {

		public static final String SELECT_SYNONYM_LIST = "select * from SEARCH_SYNONYM where IS_ENABLE = 1 ";
		
		public static final String GET_SYNONYM_COUNT = "select COUNT(*) from SEARCH_SYNONYM where IS_ENABLE = 1 ";
		
		public static final String SELECT_ONE_SYNONYM = "select * from SEARCH_SYNONYM"
		        + " where SEARCH_SYNONYM_ID = ? ";
		
		public static final String SELECT_ONE_SYNONYM_FOR_NAME = "select * from SEARCH_SYNONYM"
                + " where SETTING_VALUE = ? AND SEARCH_SYNONYM_ID <> ?";
}
