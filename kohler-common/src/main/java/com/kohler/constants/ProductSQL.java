/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.constants;

/**
 * Product SQL
 *
 * @author WHH
 * @Date 2014年10月28日
 */
public class ProductSQL {

    public static final String DELETE_PRODUCT_METADATA_BY_METADATA_ID = "UPDATE PRODUCT_METADATA SET IS_ENABLE = 0 WHERE ? like concat(concat('%,',PRODUCT_METADATA_ID),',%')";

    
    public static final String DELETE_PRODUCT_BY_METADATA_ID = "UPDATE PRODUCT SET IS_ENABLE = 0 WHERE ? like concat(concat('%,',PRODUCT_METADATA_ID),',%')";

}
