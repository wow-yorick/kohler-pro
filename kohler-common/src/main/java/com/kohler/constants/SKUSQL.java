/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.constants;

/**
 * Class Function Description
 *
 * @author sana
 * @Date 2014年12月2日
 */
public class SKUSQL {
    public static final String SELECT_SKU_FIELD_NAMES = "SELECT fa0.FILE_ASSET_NAME as LIST_IMAGE_ID,"
            +" fa1.FILE_ASSET_NAME as DETAIL_IMAGE1_ID,"
            +" fa2.FILE_ASSET_NAME as DETAIL_IMAGE2_ID,"
            +" fa3.FILE_ASSET_NAME as DETAIL_IMAGE3_ID,"
            +" fa4.FILE_ASSET_NAME as DETAIL_IMAGE4_ID,"
            +" fa5.FILE_ASSET_NAME as DETAIL_IMAGE5_ID"
            +" from SKU s "
            +" left join FILE_ASSET fa0 on s.LIST_IMAGE_ID = fa0.FILE_ASSET_ID"
            +" left join FILE_ASSET fa1 on s.DETAIL_IMAGE1_ID = fa1.FILE_ASSET_ID"
            +" left join FILE_ASSET fa2 on s.DETAIL_IMAGE2_ID = fa2.FILE_ASSET_ID"
            +" left join FILE_ASSET fa3 on s.DETAIL_IMAGE3_ID = fa3.FILE_ASSET_ID"
            +" left join FILE_ASSET fa4 on s.DETAIL_IMAGE4_ID = fa4.FILE_ASSET_ID"
            +" left join FILE_ASSET fa5 on s.DETAIL_IMAGE5_ID = fa5.FILE_ASSET_ID"
            +" where SKU_ID = ?";
}
