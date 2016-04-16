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
 * @Date 2014年10月17日
 */
public class SKUPickerSQL {

    public static final String SELECT_SKU_BY_ID = "select ce.CATEGORY_NAME as categoryName,pc.PRODUCT_NAME as productName,sk.SKU_METADATA_ID as skuMetadataId," 
												+ "sm.SKU_CODE as skuCode,sk.SKU_ID as skuId from SKU sk " 
												+ "left join SKU_METADATA sm on sm.SKU_METADATA_ID = sk.SKU_METADATA_ID " 
												+ "left join PRODUCT pc on sm.PRODUCT_METADATA_ID = pc.PRODUCT_METADATA_ID " 
												+ "left join PRODUCT_METADATA pm on pc.PRODUCT_METADATA_ID = pm.PRODUCT_METADATA_ID " 
												+ "left join CATEGORY ce on ce.CATEGORY_METADATA_ID = pm.CATEGORY_METADATA_ID " 
												+ "where ce.LANG = 1 and sk.IS_ENABLE = 1 and sk.LANG = 1 and pc.LANG = 1 ";
    
    public static final String SELECT_SKU_ATTRIBUTE = "select sk.SKU_ID as skuId,ca.ATTR_VALUE as attrValue from SKU sk" 
												    + " left join SKU_ATTR sa on sk.SKU_METADATA_ID = sa.SKU_METADATA_ID" 
												    + " left join CATEGORY_SKU_ATTR_VALUES ca" 
												    + " on sa.CATEGORY_SKU_ATTR_VALUES_METADATA_ID = ca.CATEGORY_SKU_ATTR_VALUES_METADATA_ID" 
												    + " where ca.LANG = 1 and sk.LANG = 1 ";

}
