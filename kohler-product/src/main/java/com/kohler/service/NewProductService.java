/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service;

import java.util.List;
import java.util.Map;

import com.kohler.dao.utils.Pager;
import com.kohler.entity.NewArrivalBannerUnitEntity;
import com.kohler.entity.NewArrivalEntity;
import com.kohler.entity.NewArrivalHeroAreaEntity;
import com.kohler.entity.NewArrivalMetadataEntity;


/**
 * Class Function Description
 * NewProductService
 * @author fujiajun
 * @Date 2014年10月28日
 */
public interface NewProductService {
    /**
     * 获取新品项目列表
     * @param pager
     * @param Name
     * @return
     * @author fujiajun
     * Date 2014年10月28日
     * @version
     */
     public Pager<Map<String, Object>> getNewarrivalsListPage(Pager<Map<String, Object>> pager, String Name);
     /**
      * 获取应前台select类型列表
      * @param pager
      * @param Name
      * @return
      * @author fujiajun
      * Date 2014年10月28日
      * @version
      */
     public Map<String,Object> getselectList();
     
     /**
      * 添加新品
      * @param jsonEmity
      * @param suiteMetadataEntity
      * @param jsonSuiteProduct
      * @return
      * @author Administrator
      * Date 2014年10月23日
      * @version
      */
     public Integer addnewproduct(NewArrivalMetadataEntity NewArrivalMetadataEntity,String products,String bannerUnitMetadataId,String heroAreaMetadataId);

     /**
      * 编辑新品
      * @param jsonEmity
      * @param NewArrivalMetadataEntity
      * @param products
      * @return
      * @author Administrator
      * Date 2014年10月23日
      * @version
      */
     public Integer editnewproduct(NewArrivalMetadataEntity NewArrivalMetadataEntity, String products,Integer[] bannerUnitMetadataId,Integer[] heroAreaMetadataId);

     /**
      * 通过metadataId获取新品
      * @param NewArrivalMetadataId
      * @return
      * @author Administrator
      * Date 2014年10月23日
      * @version
      */
     public List<NewArrivalEntity> getNewArrivalByMetadataId(Integer NewArrivalMetadataId);
     
     /**
      * 通过NewArrivalMetadataId获取NewArrivalMetadata信息
      * @param NewArrivalMetadataId
      * @return
      * @author Administrator
      * Date 2014年10月23日
      * @version
      */
     public NewArrivalMetadataEntity getNewArrivalMetadataByMetadataId(Integer NewArrivalMetadataId);
     /**
      * 通过NewArrivalMetadataId获取新品HeroAre信息
      * @param NewArrivalMetadataId
      * @return
      * @author Administrator
      * Date 2014年10月23日
      * @version
      */
     public List<Map<String,Object>>   getNewArrivalHeroAreAByMetadataId(Integer NewArrivalMetadataId);
     
     /**
      * 通过NewArrivalMetadataId获取新品BannerUnit信息
      * @param NewArrivalMetadataId
      * @return
      * @author Administrator
      * Date 2014年10月23日
      * @version
      */
     public List<Map<String,Object>>  getNewArrivalBannerUnitByMetadataId(Integer NewArrivalMetadataId);
     /**
      * 删除新品
      * @param NewArrivalMetadataIdArr
      * @author Administrator
      * Date 2014年10月23日
      * @version
      */
     public void deleteNewArrival(Integer[] NewArrivalMetadataIdArr);
   
}
