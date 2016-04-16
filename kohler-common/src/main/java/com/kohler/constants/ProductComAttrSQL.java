/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.constants;

/**
 * Section SQL
 *
 * @author WHH
 * @Date 2014年10月28日
 */
public class ProductComAttrSQL {

public static final String SELECT_CATEGORY_COM_ATTR_METADATA     = "select * from "
                                                            + "CATEGORY_COM_ATTR_METADATA where IS_ENABLE = 1 and CATEGORY_METADATA_ID = ?";


public static final String SELECT_CATEGORY_COM_ATTR     = "select * from CATEGORY_COM_ATTR "
                                                    + "where IS_ENABLE = 1 and CATEGORY_COM_ATTR_METADATA_ID = ? and LANG = ?";

public static final String SELECT_Product_COM_ATTR     = "select * from PRODUCT_COM_ATTR "
                                                    + "where IS_ENABLE = 1 and PRODUCT_METADATA_ID = ? and CATEGORY_COM_ATTR_METADATA_ID = ? and LANG = ?";
public static final String SELECT_MASTERDATA_BY_NAME     = "select m.* from MASTERDATA m "
        + " left join MASTERDATA_METADATA mm on m.MASTERDATA_METADATA_ID = mm.MASTERDATA_METADATA_ID "
        + " left join MASTERDATA_TYPE mt on mm.MASTERDATA_TYPE_ID = mt.MASTERDATA_TYPE_ID "
        + " where m.IS_ENABLE = 1 and mm.IS_ENABLE = 1 and mt.IS_ENABLE = 1 and mt.MASTERDATA_TYPE_NAME = ? and LANG = ?";
public static final String SELECT_SKU_Product_NAME     = "select * from PRODUCT p "
        + " left join SKU_METADATA sm on p.PRODUCT_METADATA_ID = sm.PRODUCT_METADATA_ID"
        + " where p.IS_ENABLE = 1 and sm.IS_ENABLE = 1 and p.LANG = 1 and  sm.SKU_METADATA_ID = ?";

}
