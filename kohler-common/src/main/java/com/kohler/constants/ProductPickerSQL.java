/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.constants;

/**
 * Section SQL
 *
 * @author YS
 * @Date 2014年10月16日
 */
public class ProductPickerSQL {

    public static final String SELECT_CATEGORY_BY_ID = "select distinct cr.CATEGORY_ID as categoryId,cr.CATEGORY_NAME as categoryName," 
													 + " pd.PRODUCT_NAME as productName,pm.PRODUCT_METADATA_ID as productId" 
													 + " from CATEGORY cr" 
													 + " left join PRODUCT_METADATA pm on cr.CATEGORY_METADATA_ID = pm.CATEGORY_METADATA_ID" 
													 + " left join PRODUCT pd on pm.PRODUCT_METADATA_ID = pd.PRODUCT_METADATA_ID " 
													 + " where cr.IS_ENABLE = 1 and pd.IS_ENABLE = 1 and cr.LANG = 1 and pd.LANG = 1 ";
    
    public static final String GET_ALL_PRODUCTNAME = "select CATEGORY_ID as productId,CATEGORY_NAME as productName " 
    												 + "from CATEGORY where IS_ENABLE = 1 and LANG = 1";
    //判断是否是根节点
    public static final String SELECT_FATHER = "select * from CATEGORY_METADATA cm "
                                                + " inner join CATEGORY_METADATA ca "
                                                + " where ca.CATEGORY_METADATA_ID = cm.CATEGORY_METADATA_ID and cm.PARENT_ID = 0 "
                                                + " and cm.CATEGORY_METADATA_ID = ? ";
    //查询所有子节点
    public static final String SELECT_SON = "select * from CATEGORY_METADATA where PARENT_ID in (?)";
    
    
    
}
