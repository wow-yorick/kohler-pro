/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.constants;

/**
 * Section SQL
 *
 * @author XHY
 * @Date 2014年9月27日
 */
public class CategorySQL {
	
	public static final String SELECT_CATEGORY_BY_MID 			= " select c.* from CATEGORY c left join LOCALE l on c.LANG=l.LOCALE_ID where c.IS_ENABLE=1 "
																	+ " and l.IS_ENABLE=1 and l.IS_DEFAULT=1 and c.CATEGORY_METADATA_ID=? ";

	public static final String SELECT_CATEGORY_BY_MID2 			= " select c.* from CATEGORY c  where c.IS_ENABLE=1  "
																	+ " and  c.CATEGORY_METADATA_ID=? ";

    public static final String SELECT_CATEGORYS_WITH_MAP        = " SELECT p.CATEGORY_METADATA_ID as id, "
                                                                       + " p.PARENT_ID AS pId,c.CATEGORY_NAME as name, "
                                                                       + " CONCAT('catalogList.action?categoryId=',p.CATEGORY_METADATA_ID) AS url,'_self' AS target"
                                                                       + " FROM CATEGORY_METADATA p "
                                                                       + " LEFT JOIN CATEGORY c ON p.CATEGORY_METADATA_ID = c.CATEGORY_METADATA_ID and c.IS_ENABLE = 1 "
                                                                       + " left join LOCALE l on c.LANG = l.LOCALE_ID "
                                                                       + " WHERE p.IS_ENABLE = 1 and l.IS_ENABLE=1 and l.IS_DEFAULT=1 "
                                                                       + " ORDER BY p.SEQ_NO,p.MODIFY_TIME DESC ";

    public static final String SELECT_CATEGORY_PAGE_COUNT_BY_ID = "SELECT COUNT(*) FROM ("
                                                                       + "SELECT p.CATEGORY_METADATA_ID as id,c.CATEGORY_NAME as name,p.SEQ_NO as seqNo,p.LEVEL as cateLevel from CATEGORY_METADATA p "
                                                                       + " LEFT JOIN CATEGORY c ON p.CATEGORY_METADATA_ID = c.CATEGORY_METADATA_ID and c.IS_ENABLE = 1 "
                                                                       + " left join LOCALE l on c.LANG = l.LOCALE_ID "
                                                                       + " where p.IS_ENABLE = 1 and l.IS_ENABLE=1 and l.IS_DEFAULT=1 and p.PARENT_ID = ?) t";

    public static final String SELECT_CATEGORY_PAGE_BY_ID       = "SELECT * FROM ("
                                                                       + " SELECT p.CATEGORY_METADATA_ID as id,c.CATEGORY_NAME as name,p.SEQ_NO as seqNo,p.LEVEL as cateLevel from CATEGORY_METADATA p "
                                                                       + " LEFT JOIN CATEGORY c ON p.CATEGORY_METADATA_ID = c.CATEGORY_METADATA_ID and c.IS_ENABLE = 1 "
                                                                       + " left join LOCALE l on c.LANG = l.LOCALE_ID "
                                                                       + " where p.IS_ENABLE = 1 and l.IS_ENABLE=1 and l.IS_DEFAULT=1 and p.PARENT_ID = ? ORDER BY p.SEQ_NO,p.MODIFY_TIME DESC )  t LIMIT ?,?";
    
    public static final String SELECT_PRODUCT_PAGE_COUNT_BY_CID 	= "SELECT COUNT(*) FROM ("
															    		+ "	SELECT p.PRODUCT_METADATA_ID as id, "
																    	+ "	c.PRODUCT_NAME as name, "
																    	+ "	col.COLLECTION_NAME as collectionName, "
																    	+ "	p.PRODUCT_CODE as productCode " 
																    	+ "	from PRODUCT_METADATA p " 
																    	+ "	LEFT JOIN PRODUCT c on p.PRODUCT_METADATA_ID = c.PRODUCT_METADATA_ID " 
																    	+ "	LEFT JOIN  COLLECTION_METADATA co on p.COLLECTION_METADATA_ID = co.COLLECTION_METADATA_ID "
																    	+ "	LEFT JOIN COLLECTION col on co.COLLECTION_METADATA_ID = col.COLLECTION_METADATA_ID " 
																    	+ "	LEFT JOIN LOCALE cl on col.LANG = cl.LOCALE_ID "
																    	+ "	LEFT JOIN LOCALE l on c.LANG = l.LOCALE_ID "  
																    	+ "	where p.IS_ENABLE = 1 and l.IS_ENABLE=1 and c.IS_ENABLE = 1 and co.IS_ENABLE = 1 and col.IS_ENABLE = 1 and cl.IS_ENABLE = 1 " 
																    	+ "	and l.IS_DEFAULT=1 and cl.IS_DEFAULT = 1 and p.CATEGORY_METADATA_ID = ? "
															    		+ ") t";
    
    public static final String SELECT_PRODUCT_PAGE_BY_CID       = "SELECT * FROM ("
																    	+ "	SELECT p.PRODUCT_METADATA_ID as id, "
																    	+ "	c.PRODUCT_NAME as name, "
																    	+ "	col.COLLECTION_NAME as collectionName, "
																    	+ "	p.PRODUCT_CODE as productCode " 
																    	+ "	from PRODUCT_METADATA p " 
																    	+ "	LEFT JOIN PRODUCT c on p.PRODUCT_METADATA_ID = c.PRODUCT_METADATA_ID " 
																    	+ "	LEFT JOIN  COLLECTION_METADATA co on p.COLLECTION_METADATA_ID = co.COLLECTION_METADATA_ID "
																    	+ "	LEFT JOIN COLLECTION col on co.COLLECTION_METADATA_ID = col.COLLECTION_METADATA_ID " 
																    	+ "	LEFT JOIN LOCALE cl on col.LANG = cl.LOCALE_ID "
																    	+ "	LEFT JOIN LOCALE l on c.LANG = l.LOCALE_ID "  
																    	+ "	where p.IS_ENABLE = 1 and l.IS_ENABLE=1 and c.IS_ENABLE = 1 and co.IS_ENABLE = 1 and col.IS_ENABLE = 1 and cl.IS_ENABLE = 1 " 
																    	+ "	and l.IS_DEFAULT=1 and cl.IS_DEFAULT = 1 and p.CATEGORY_METADATA_ID = ? "
															    		+ ")  t LIMIT ?,? ";
															    
   
    public static final String CHECK_CATEGORY_IS_TOP 			= "SELECT COUNT(*) FROM "
    																	+" CATEGORY_METADATA p "
           
            															+" where p.IS_ENABLE = 1 AND p.PARENT_ID IS NULL AND p.CATEGORY_METADATA_ID = ? ";
   
    public static final String CHECK_CATEGORY_IS_BOTTOM			= 	" SELECT COUNT(*) FROM "
    																+" CATEGORY_METADATA p "
    		
            														+" where p.IS_ENABLE = 1 AND p.PARENT_ID = ? ";
    
    public static final String SELECT_CATE_BY_ID				= 	" SELECT p.*,c.CATEGORY_NAME FROM CATEGORY_METADATA p "
		 															+ " LEFT JOIN CATEGORY c ON p.CATEGORY_METADATA_ID = c.CATEGORY_METADATA_ID and c.IS_ENABLE = 1 "
		 															+ " LEFT JOIN LOCALE l ON c.LANG = l.LOCALE_ID  "
    																+ " WHERE p.IS_ENABLE = 1 and l.IS_ENABLE=1 and l.IS_DEFAULT=1  AND p.CATEGORY_METADATA_ID = ? ";
 
    public static final String SELECT_CATE_BY_PID				= 	" SELECT p.*,c.CATEGORY_NAME  FROM CATEGORY_METADATA p "
														    		+ " LEFT JOIN CATEGORY c ON p.CATEGORY_METADATA_ID = c.CATEGORY_METADATA_ID and c.IS_ENABLE = 1 "
														    		+ " LEFT JOIN LOCALE l ON c.LANG = l.LOCALE_ID  "
														    		+ " WHERE p.IS_ENABLE = 1 and l.IS_ENABLE=1 and l.IS_DEFAULT=1  AND p.PARENT_ID = ? ";
    
    public static final String CHECK_CATALOG_COUNT				= 	"	SELECT COUNT(*) FROM CATEGORY c "
																  	+"	LEFT JOIN CATEGORY_METADATA p on p.CATEGORY_METADATA_ID = c.CATEGORY_METADATA_ID and p.IS_ENABLE = 1 "
																  	+"	where c.IS_ENABLE = 1 AND "
    																+"	c.CATEGORY_NAME = ? AND c.LANG = ? and p.PARENT_ID = ? " ;
    
    
    public static final String CHECK_CATALOG_COUNT_OUT				= 	"	SELECT COUNT(*) FROM CATEGORY c "
																  	+	"	LEFT JOIN CATEGORY_METADATA p on p.CATEGORY_METADATA_ID = c.CATEGORY_METADATA_ID and p.IS_ENABLE = 1 "
																  	+	"	where c.IS_ENABLE = 1 AND "
																	+	"	c.CATEGORY_NAME = ? AND c.LANG = ? and p.PARENT_ID = ? " 
																  	+	"	AND c.CATEGORY_ID != ? ";																

    
    public static final String CHECK_PRODUCT_COUNT				= 	" SELECT COUNT(*) FROM "
																	+" PRODUCT_METADATA p "
																	+" where p.IS_ENABLE = 1 AND p.CATEGORY_METADATA_ID = ? ";
    
   
    public static final String SELECT_CATE_COM_BY_CID				= 	"SELECT p.*,c.CATEGORY_COM_ATTR_NAME FROM CATEGORY_COM_ATTR_METADATA p " 
    																+ " LEFT JOIN CATEGORY_COM_ATTR c ON p.CATEGORY_COM_ATTR_METADATA_ID = c.CATEGORY_COM_ATTR_METADATA_ID "
																	+ "	LEFT JOIN LOCALE l ON c.LANG = l.LOCALE_ID "  
																	+ " WHERE p.IS_ENABLE = 1 and l.IS_ENABLE=1 and l.IS_DEFAULT=1  and c.IS_ENABLE = 1 " 
																	+ " AND p.CATEGORY_METADATA_ID = ? ";
   
    public static final String SELECT_CATE_SKU_BY_CID				= 	"SELECT p.*,c.CATEGORY_SKU_ATTR_NAME FROM CATEGORY_SKU_ATTR_METADATA p " 
														    		+ " LEFT JOIN CATEGORY_SKU_ATTR c ON p.CATEGORY_SKU_ATTR_METADATA_ID = c.CATEGORY_SKU_ATTR_METADATA_ID "
														    		+ "	LEFT JOIN LOCALE l ON c.LANG = l.LOCALE_ID "  
														    		+ " WHERE p.IS_ENABLE = 1 and l.IS_ENABLE=1 and l.IS_DEFAULT=1  and c.IS_ENABLE = 1 " 
														    		+ " AND p.CATEGORY_METADATA_ID = ? ";
	
    public static final String SELECT_CATE_SEARCH_BY_CID				= 	" SELECT p.*,c.KEYWORD,c.URL FROM CATEGORY_SEARCH_KEYWORD_METADATA p "
																	+ " LEFT JOIN CATEGORY_SEARCH_KEYWORD c ON p.CATEGORY_SEARCH_KEYWORD_METADATA_ID = c.CATEGORY_SEARCH_KEYWORD_METADATA_ID " 
																	+ "	LEFT JOIN LOCALE l ON c.LANG = l.LOCALE_ID "   
																	+ " WHERE p.IS_ENABLE = 1 and l.IS_ENABLE=1 and l.IS_DEFAULT=1  and c.IS_ENABLE = 1 "
																	+ " and p.CATEGORY_METADATA_ID = ? ";
    
    public static final String SELECT_CATE_ACC_BY_CID				= 	" SELECT p.*,c.CATEGORY_ACCESSORY_NAME,m.MASTERDATA_NAME FROM CATEGORY_ACCESSORY_METADATA p "
																	+ " LEFT JOIN CATEGORY_ACCESSORY c ON p.CATEGORY_ACCESSORY_METADATA_ID = c.CATEGORY_ACCESSORY_METADATA_ID "
																	+ " LEFT JOIN LOCALE l ON c.LANG = l.LOCALE_ID "  
																	+ " LEFT JOIN MASTERDATA m ON c.CATEGORY_ACCESSORY_TYPE = m.MASTERDATA_METADATA_ID "
																	+ " WHERE p.IS_ENABLE = 1 and l.IS_ENABLE=1 and l.IS_DEFAULT=1  and c.IS_ENABLE = 1 "
																	+ " and m.LANG = 1 and p.CATEGORY_METADATA_ID = ?  ";
    
    
    public static final String SELECT_CATE_COM_BY_ID				= 	"SELECT p.*,c.CATEGORY_COM_ATTR_NAME FROM CATEGORY_COM_ATTR_METADATA p " 
														    		+ " LEFT JOIN CATEGORY_COM_ATTR c ON p.CATEGORY_COM_ATTR_METADATA_ID = c.CATEGORY_COM_ATTR_METADATA_ID "
														    		+ "	LEFT JOIN LOCALE l ON c.LANG = l.LOCALE_ID "  
														    		+ " WHERE p.IS_ENABLE = 1 and l.IS_ENABLE=1 and l.IS_DEFAULT=1  and c.IS_ENABLE = 1 " 
														    		+ " AND p.CATEGORY_COM_ATTR_METADATA_ID = ? ";

    public static final String SELECT_CATE_SKU_BY_ID				= 	"SELECT p.*,c.CATEGORY_SKU_ATTR_NAME FROM CATEGORY_SKU_ATTR_METADATA p " 
														    		+ " LEFT JOIN CATEGORY_SKU_ATTR c ON p.CATEGORY_SKU_ATTR_METADATA_ID = c.CATEGORY_SKU_ATTR_METADATA_ID "
														    		+ "	LEFT JOIN LOCALE l ON c.LANG = l.LOCALE_ID "  
														    		+ " WHERE p.IS_ENABLE = 1 and l.IS_ENABLE=1 and l.IS_DEFAULT=1  and c.IS_ENABLE = 1 " 
														    		+ " AND p.CATEGORY_SKU_ATTR_METADATA_ID = ? ";

    public static final String SELECT_CATE_SEARCH_BY_ID				= 	" SELECT p.*,c.KEYWORD,c.URL FROM CATEGORY_SEARCH_KEYWORD_METADATA p "
    																+ " LEFT JOIN CATEGORY_SEARCH_KEYWORD c ON p.CATEGORY_SEARCH_KEYWORD_METADATA_ID = c.CATEGORY_SEARCH_KEYWORD_METADATA_ID " 
																	+ "	LEFT JOIN LOCALE l ON c.LANG = l.LOCALE_ID "   
																	+ " WHERE p.IS_ENABLE = 1 and l.IS_ENABLE=1 and l.IS_DEFAULT=1  and c.IS_ENABLE = 1 "
																	+ " and p.CATEGORY_SEARCH_KEYWORD_METADATA_ID = ? ";

    public static final String SELECT_CATE_ACC_BY_ID				= 	" SELECT p.*,c.CATEGORY_ACCESSORY_NAME,m.MASTERDATA_NAME FROM CATEGORY_ACCESSORY_METADATA p "
    																+ " LEFT JOIN CATEGORY_ACCESSORY c ON p.CATEGORY_ACCESSORY_METADATA_ID = c.CATEGORY_ACCESSORY_METADATA_ID "
																	+ " LEFT JOIN LOCALE l ON c.LANG = l.LOCALE_ID "  
    																+ " LEFT JOIN MASTERDATA m ON c.CATEGORY_ACCESSORY_TYPE = m.MASTERDATA_METADATA_ID "
																	+ " WHERE p.IS_ENABLE = 1 and l.IS_ENABLE=1 and l.IS_DEFAULT=1  and c.IS_ENABLE = 1 "
																	+ " and m.LANG = 1 and p.CATEGORY_ACCESSORY_METADATA_ID = ?  ";

	public static final String CHECK_COM_KEYNAME_COUNT 				= " SELECT COUNT(*) FROM CATEGORY_COM_ATTR_METADATA c "
																	+ " where c.IS_ENABLE = 1 AND " 
																	+ " c.KEY_NAME = ? "
																	+ " AND c.CATEGORY_METADATA_ID = ? ";

	public static final String CHECK_COM_KEYNAME_COUNT_OUT 			= " SELECT COUNT(*) FROM CATEGORY_COM_ATTR_METADATA c "
																	+ " where c.IS_ENABLE = 1 AND " 
																	+ " c.KEY_NAME = ? "
																	+ " AND c.CATEGORY_METADATA_ID = ? "
																	+ " AND c.CATEGORY_COM_ATTR_METADATA_ID != ? ";

}
