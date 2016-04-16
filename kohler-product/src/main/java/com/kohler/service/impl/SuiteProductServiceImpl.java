/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kohler.dao.SuiteProductDao;
import com.kohler.dao.SysLogDao;
import com.kohler.entity.SuiteProductEntity;
import com.kohler.entity.extend.SuiteProductPojo;
import com.kohler.service.SuiteProductService;
import com.kohler.util.DateUtil;

/**
 * 
 * 套间产品业务service
 *
 * @author Administrator
 * @Date 2014年10月23日
 */
@Service
public class SuiteProductServiceImpl implements SuiteProductService {

    @Autowired
    private SuiteProductDao suiteProductDao;

    @Autowired
    public SysLogDao        sysLogDao;

    /**
     * 
     * {@inheritDoc}
     */
    public List<Map<String, Object>> getSuiteProductList(Integer suiteMetadataId) {
        return suiteProductDao.getSuiteProductList(suiteMetadataId);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Transactional
    public Integer addSuiteProduct(SuiteProductEntity suiteProductEntity) {
        Integer spId = suiteProductDao.insert(suiteProductEntity);
        sysLogDao.insertLogForInsert(suiteProductEntity.getSuiteProductId(), "SUITE_PRODUCT");
        return spId;

    }

    /**
     * 
     * {@inheritDoc}
     */
    @Transactional
    public Integer editSuiteProduct(SuiteProductEntity suiteProductEntity) {
        Integer spId = suiteProductDao.update(suiteProductEntity);
        sysLogDao.insertLogForUpdate(suiteProductEntity.getSuiteProductId(), "SUITE_PRODUCT");
        return spId;

    }

    /**
     * 
     * {@inheritDoc}
     */
    public SuiteProductPojo getSuiteProductDetail(Integer suiteProductId) {
        List<Map<String,Object>> list =  suiteProductDao.getSuiteProductDetail(suiteProductId);
        
        SuiteProductPojo _pojo = new SuiteProductPojo();

        
        for(Map<String,Object> _map : list){
            if(_map.get("SKU_CODE") != null){
                _pojo.setSkuCode(_map.get("SKU_CODE").toString());
            }
            if(_map.get("SUITE_PRODUCT_ID") != null){
                _pojo.setSuiteProductId(Integer.valueOf(_map.get("SUITE_PRODUCT_ID").toString()));
            }
            if(_map.get("SUITE_METADATA_ID") != null){
                _pojo.setSuiteMetadataId(Integer.valueOf(_map.get("SUITE_METADATA_ID").toString()));
            }
            if(_map.get("SKU_METADATA_ID") != null){
                _pojo.setSkuMetadataId(Integer.valueOf(_map.get("SKU_METADATA_ID").toString()));
            }
            
            if(_map.get("SEQ_NO") != null){
                _pojo.setSeqNo(Integer.valueOf(_map.get("SEQ_NO").toString()));
            }
            if(_map.get("IS_ENABLE") != null){
                _pojo.setIsEnable(Boolean.valueOf(_map.get("IS_ENABLE").toString()));
            }
            if(_map.get("CREATE_USER") != null){
                _pojo.setCreateUser(_map.get("CREATE_USER").toString());
            }
            if(_map.get("CREATE_TIME") != null){
                _pojo.setCreateTime(DateUtil.str2DateCommon(_map.get("CREATE_TIME").toString(),DateUtil.YYYY_MM_DD_HH_MM_SS));
            }
            if(_map.get("MODIFY_USER") != null){
                _pojo.setCreateUser(_map.get("MODIFY_USER").toString());
            }
            if(_map.get("MODIFY_TIME") != null){
                _pojo.setCreateTime(DateUtil.str2DateCommon(_map.get("MODIFY_TIME").toString(),DateUtil.YYYY_MM_DD_HH_MM_SS));
            }

        }
        
        return _pojo;
        
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Transactional
    public Integer deleteSuiteProduct(Integer suiteProductId) {
        SuiteProductEntity suiteProductEntity = suiteProductDao.selectById(suiteProductId);
        sysLogDao.insertLogForDelete(suiteProductId, "SUITE_PRODUCT");
        return suiteProductDao.delete(suiteProductEntity);
    }
    
    

}
