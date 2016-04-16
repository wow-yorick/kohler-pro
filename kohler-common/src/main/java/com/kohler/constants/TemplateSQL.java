package com.kohler.constants;

public class TemplateSQL {

		public static final String DELETE_BY_TEMPLATE_ID = "delete  from code where DATATYPE_ID = ?";
		
		public static final String INSERT_DATATYPE = "insert into code (DATATYPE_ID,RECORD_ID,FIELD_NAME,COMPLICATED,VALUE) values (?,?,?,?,?)";

		public static final String SELECT_DATATYPE_BY_TEMPLATE_ID = 
				" select FIELD_NAME as name,VALUE as value,COMPLICATED as complicated, "
				+ "DATATYPE_ID as dataTypeId,Record_ID as recordId,ID as id "
				+ " from code "
				+ " where DATATYPE_ID = ? ";

}
