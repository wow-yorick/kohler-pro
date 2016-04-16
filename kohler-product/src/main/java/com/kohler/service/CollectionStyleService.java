/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service;

import java.util.List;
import java.util.Map;

import com.kohler.dao.utils.Pager;
import com.kohler.entity.CollectionStyleEntity;
import com.kohler.entity.CollectionStyleMetadataEntity;
import com.kohler.entity.NewArrivalEntity;


/**
 * Class Function Description
 * CollectionStyleService
 * @author fujiajun
 * @Date 2014年10月28日
 */
public interface CollectionStyleService {
    /**
     * 获取系列项目列表
     * @param pager
     * @param Name
     * @return
     * @author fujiajun
     * Date 2014年10月28日
     * @version
     */
     public Pager<Map<String, Object>> getCollectionStyleListPage(Pager<Map<String, Object>> pager, String Name);
     
     
     /**
      * 添加新品
      * @param suiteMetadataEntity
      * @param jsonSuiteProduct
      * @return
      * @author Administrator
      * Date 2014年10月23日
      * @version
      */
     public Integer addCollectionStyle(CollectionStyleMetadataEntity CollectionStyleMetadataEntity,String products);

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
     public Integer editCollectionStyle(CollectionStyleMetadataEntity CollectionStyleMetadataEntity, String products);
     /**
      * 通过metadataId获取新品
      * @param CollectionStyleMetadataId
      * @return
      * @author Administrator
      * Date 2014年10月23日
      * @version
      */
     public List<CollectionStyleEntity> getCollectionStyleByMetadataId(Integer CollectionStyleMetadataId);
     public CollectionStyleMetadataEntity getCollectionStyleMetadataByMetadataId(Integer CollectionStyleMetadataId);
     /**
      * 删除新品
      * @param NewArrivalMetadataIdArr
      * @author Administrator
      * Date 2014年10月23日
      * @version
      */
     public void deleteCollectionStyle(Integer[] CollectionStyle);   
}
