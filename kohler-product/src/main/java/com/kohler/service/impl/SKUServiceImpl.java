/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohler.dao.SKUAttrDao;
import com.kohler.entity.SKUAttrEntity;
import com.kohler.service.SKUAttrService;

/**
 * Class Function Description
 *
 * @author ztt
 * @Date 2014年10月11日
 */
@Service
public class SKUServiceImpl implements SKUAttrService {
    
    @Autowired
    private SKUAttrDao skuAttrDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SKUAttrEntity> getSKUAttrListBySKUMetadataId(Integer skuMetadataId) {
        SKUAttrEntity attr=new SKUAttrEntity();
        attr.setSkuMetadataId(skuMetadataId);
        attr.setIsEnable(true);
        return skuAttrDao.selectByCondition(attr);
    }

}
