/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service;

import java.util.List;
import java.util.Map;

import com.kohler.bean.ConfPrepareData;
import com.kohler.exception.DataException;

/**
 * 获取指定公共属性下的同类商品
 *
 * @author Administrator
 * @Date 2014年11月22日
 */
public interface SameCommonAttrProductService {
    /**
     * 根据公共属性获取产品
     * @param commonAttrKeyName
     * @param conf
     * @return
     * @author Administrator
     * Date 2014年11月22日
     * @version
     */
    public List<Map<String, Object>> getProductWith(String commonAttrKeyName, ConfPrepareData conf) throws DataException ;
}
