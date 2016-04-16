/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.dao;

import java.util.List;
import java.util.Map;

/**
 * 为产品准备数据DAO接口
 *
 * @author Administrator
 * @Date 2014年10月24日
 */
public interface PrepareDataForProduct {
    public List<Map<String,Object>> getProductListWithCategory();
}
