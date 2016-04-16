/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import com.kohler.entity.SKUAttrEntity;
import com.kohler.entity.SKUEntity;
import com.kohler.entity.SKUMetadataEntity;

/**
 * Class Function Description
 * SKUMetadataService
 * @author zhangtingting
 * @Date 2014年9月25日
 */
public interface SKUMetadataService {
    
    /**
     * Add a isEnable=0 SKUMetadata
     */
    public SKUMetadataEntity addSKUMetadata();
    
    

    /**
     * insert into SKUMetadataEntity and SKUEntity and SKUAttrEntity and SKUAccessoryEntity
     * @param accessorylist 
     */
    public void saveSKUMetadata(SKUMetadataEntity skuMetadataEntity,List<SKUEntity> skulist,List<SKUAttrEntity> skuAttrlist, List<Map<String, Object>> accessorylist)throws UnsupportedEncodingException;
    
    
    
    /**
     * update SKUMetadataEntity and SKUEntity and SKUAttrEntity and SKUAccessoryEntity
     * @param accessorylist 
     */
    public void updateSKUMetadata(SKUMetadataEntity skuMetadataEntity,List<SKUEntity> skulist,List<SKUAttrEntity> skuAttrlist, List<Map<String, Object>> accessorylist)throws UnsupportedEncodingException;
    
    
    
    /**
     * @param productMetadataId
     * @return
     * @author ztt
     * Date 2014年10月11日
     * @version
     */
    public List<SKUMetadataEntity> getSKUMetadataEntitylistById(Integer productMetadataId);
    
    
    
    /**
     * 
     * @param skuMetadataId
     * @return
     * @author ztt
     * Date 2014年10月13日
     * @version
     */
    public SKUMetadataEntity getSkuMetadataBySKUMetadataid(Integer skuMetadataId);
    
    
    
    /**
     * delete SKU
     * @param skuMetadatId
     * @author ztt
     * Date 2014年10月22日
     * @version
     */
    public void deleteSKUMetadata(Integer skuMetadatId);



    /**
     * @param skuId
     * @return
     * @author sana
     * Date 2014年12月2日
     * @version
     */
    public Map<String, Object> getFileNamesBySkuId(Integer skuId);



    /**
     * @param skuCode
     * @param skuMetadataId
     * @return
     * @author sana
     * Date 2014年12月5日
     * @version
     */
    public List<SKUMetadataEntity> checkSKUCode(String skuCode, Integer skuMetadataId);
}
