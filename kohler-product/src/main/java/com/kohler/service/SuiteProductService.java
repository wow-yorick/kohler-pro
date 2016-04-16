/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.service;

import java.util.List;
import java.util.Map;

import com.kohler.entity.SuiteProductEntity;
import com.kohler.entity.extend.SuiteProductPojo;

public interface SuiteProductService {

    /**
     * 获取套间产品列表
     * @param suiteMetadataId
     * @return
     * @author Administrator
     * Date 2014年10月23日
     * @version
     */
    public List<Map<String,Object>> getSuiteProductList(Integer suiteMetadataId);

    /**
     * 新增套间产品
     * @param suiteProductEntity
     * @return
     * @author Administrator
     * Date 2014年10月23日
     * @version
     */
    public Integer addSuiteProduct(SuiteProductEntity suiteProductEntity);

    /**
     * 编辑套间产品
     * @param suiteProductEntity
     * @return
     * @author Administrator
     * Date 2014年10月23日
     * @version
     */
    public Integer editSuiteProduct(SuiteProductEntity suiteProductEntity);

    /**
     * 获取套间产品详情
     * @param suiteProductId
     * @return
     * @author Administrator
     * Date 2014年10月23日
     * @version
     */
    public SuiteProductPojo getSuiteProductDetail(Integer suiteProductId); 

    /**
     * 删除套间产品
     * @param suiteProductId
     * @return
     * @author Administrator
     * Date 2014年10月23日
     * @version
     */
    public Integer deleteSuiteProduct(Integer suiteProductId);
    
}
