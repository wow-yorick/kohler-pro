package com.kohler.constants;

public class UserSQL {

		public static final String SELECT_ALL_USER = "select * from sys_user";
		
		public static final String INSERT_USER = "insert into sys_user(username,password,realname,remark)"
				+ "values (?,?,?,?)";

		public static final String UPDATE_USER = "update sys_user set username = ? , password = ?,realname = ?,remark = ? where sys_user_id = ?";

		public static final String SELECT_USER_BY_ID = "select * from sys_user where sys_user_id = ?";
		
		public static final String SELECT_ALL_BY_PAGE = "SELECT SYS_USER_ID, USER_NAME, PASSWORD, REAL_NAME FROM SYS_USER WHERE IS_ENABLE =1 AND USER_NAME = ? AND REAL_NAME = ? ";

		public static final String SELECT_ALL_BY_PAGE_LM = " SELECT * FROM "
															+ " ( SELECT DISTINCT  u.SYS_USER_ID as userId ,r.ROLE_NAME as roleName ,u.USER_NAME as loginName, u.PASSWORD, u.REAL_NAME as realName,u.CREATE_USER as createUser,u.CREATE_TIME as createTime FROM SYS_USER u "
															+ " LEFT JOIN USER_ROLE ur ON u.SYS_USER_ID = ur.SYS_USER_ID"
															+ " LEFT JOIN ROLE r ON r.ROLE_ID = ur.ROLE_ID "
															+ " WHERE u.IS_ENABLE =1 AND ur.IS_ENABLE =1 AND r.IS_ENABLE =1 AND u.USER_NAME LIKE ? AND u.REAL_NAME LIKE ? ) t LIMIT ?,? ";
		
		public static final String SELECT_ALL_BY_PAGE_COUNT = "SELECT COUNT(*) FROM ("+SELECT_ALL_BY_PAGE + ") c";

		public static final String CHECK_USERNAME_COUNT = "	SELECT COUNT(*) FROM SYS_USER u "
															+ " where u.IS_ENABLE = 1 AND " 
															+ " u.USER_NAME = ? ";

		public static final String CHECK_USERNAME_COUNT_OUT = "	SELECT COUNT(*) FROM SYS_USER u "
															+ " where u.IS_ENABLE = 1 AND " 
															+ " u.USER_NAME = ? "
															+ "	AND u.SYS_USER_ID != ? ";
}
