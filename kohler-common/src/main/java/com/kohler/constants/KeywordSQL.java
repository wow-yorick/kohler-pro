package com.kohler.constants;

public class KeywordSQL {

        public static final String SELECT_KEYWORD_LIST = "select * from SEARCH_KEYWORD_REDIRECTION where IS_ENABLE = 1 ";
        
        public static final String GET_KEYWORD_COUNT = "select COUNT(*) from SEARCH_KEYWORD_REDIRECTION where IS_ENABLE = 1 ";
        
        public static final String SELECT_ONE_KEYWORD = "select * from SEARCH_KEYWORD_REDIRECTION"
                + " where SEARCH_KEYWORD_REDIRECTION_ID = ? ";

}
