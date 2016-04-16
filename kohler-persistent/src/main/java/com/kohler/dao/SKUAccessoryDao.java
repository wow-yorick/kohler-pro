/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.dao;

import java.util.List;

import com.kohler.entity.SKUAccessoryEntity;

/**
 * Class Function Description
 *
 * @author sana
 * @Date 2014年11月24日
 */
public interface SKUAccessoryDao extends BaseDao<SKUAccessoryEntity>{

    /**
     * @param skuMetadataId
     * @param lang
     * @return
     * @author sana
     * Date 2014年11月24日
     * @version
     * @param categoryAccessoryMetadataId 
     */
    public List<SKUAccessoryEntity> getSKUAccessoryBySKUId(String skuMetadataId, String lang, String categoryAccessoryMetadataId);
    
    
    /**
     * @param skuMetadataId
     * @return
     * @author sana
     * Date 2014年11月24日
     * @version
     */
    public List<SKUAccessoryEntity> getAccessoryBySKUMetadataId(String skuMetadataId);
    
    public int updateAllAccessoryFalseByskuMetadataId(Integer skuMetadataId);
    

}
