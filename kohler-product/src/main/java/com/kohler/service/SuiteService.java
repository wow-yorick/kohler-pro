/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.service;

import java.util.List;
import java.util.Map;

import com.kohler.dao.utils.Pager;
import com.kohler.entity.SuiteEntity;
import com.kohler.entity.SuiteMetadataEntity;
import com.kohler.entity.extend.SuiteHotSpotPojo;

public interface SuiteService {

    /**
     * 获取套间列表
     * @param pager
     * @param suitName
     * @return
     * @author Administrator
     * Date 2014年10月23日
     * @version
     */
    public Pager<Map<String, Object>> getSuitePage(Pager<Map<String, Object>> pager, String suitName);

    /**
     * 添加套间
     * @param jsonEmity
     * @param suiteMetadataEntity
     * @param jsonSuiteProduct
     * @return
     * @author Administrator
     * Date 2014年10月23日
     * @version
     */
    public Integer addSuite(String jsonEmity, SuiteMetadataEntity suiteMetadataEntity,String jsonSuiteProduct, SuiteHotSpotPojo suHotSpotPojo);

    /**
     * 编辑套间
     * @param jsonEmity
     * @param suiteMetadataEntity
     * @param jsonSuiteProduct
     * @return
     * @author Administrator
     * Date 2014年10月23日
     * @version
     */
    public Integer editSuite(String jsonEmity, SuiteMetadataEntity suiteMetadataEntity,String jsonSuiteProduct, SuiteHotSpotPojo suHotSpotPojo);

    /**
     * 通过metadataId获取套间
     * @param suiteMetadataId
     * @return
     * @author Administrator
     * Date 2014年10月23日
     * @version
     */
    public List<SuiteEntity> getSuiteByMetadataId(Integer suiteMetadataId);
    
    /**
     * 通过suiteMetadataId获取套间主表排序信息
     * @param suiteMetadataId
     * @return
     * @author Administrator
     * Date 2014年10月23日
     * @version
     */
    public SuiteMetadataEntity getSuiteMetadataByMetadataId(Integer suiteMetadataId);

    /**
     * 删除套间
     * @param suiteMetadataIdArr
     * @author Administrator
     * Date 2014年10月23日
     * @version
     */
    public void deleteSuite(String[] suiteMetadataIdArr);
    
    /**
     * 获取公共信息
     * @return
     * @author Administrator
     * Date 2014年10月23日
     * @version
     */
    public Map<String,Object> getGlobleMasterData();
    
    /**
     * 通过过滤条件查询suite
     * @param suiteEntity
     * @return
     * @author Administrator
     * Date 2014年11月6日
     * @version
     */
    public List<SuiteEntity> selectSuiteBySuiteEntity(SuiteEntity suiteEntity);
    
    /**
     * 获取suite hot spot 
     * @param suiteMetadataId
     * @return
     * @author Administrator
     * Date 2014年12月19日
     * @version
     */
    public List<Map<String,Object>> getSuiteHotSpot(Integer suiteMetadataId);


}
