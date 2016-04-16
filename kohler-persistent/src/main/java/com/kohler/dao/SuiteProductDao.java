package com.kohler.dao;

import java.util.List;
import java.util.Map;

import com.kohler.entity.SuiteProductEntity;


public interface SuiteProductDao extends BaseDao<SuiteProductEntity>{
    /**
     * 通过suiteMetadate获取套间产品列表
     * @param suiteMetadataId
     * @return
     * @author Administrator
     * Date 2014年10月18日
     * @version
     */
    public List<Map<String,Object>> getSuiteProductList(Integer suiteMetadataId);
    
    /**
     * 通过suiteMetadataId删除套间产品
     * @param suiteMetadataId
     * @return
     * @author Administrator
     * Date 2014年10月18日
     * @version
     */
    public Integer deleteBySuiteMetadataId(Integer suiteMetadataId);
    
    /**
     * 根据主键获取套间产品详细
     * @param suiteProductId
     * @return
     * @author Administrator
     * Date 2014年10月18日
     * @version
     */
    public List<Map<String,Object>> getSuiteProductDetail(Integer suiteProductId);
}
