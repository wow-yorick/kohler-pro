/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohler.dao.SKUDao;
import com.kohler.entity.SKUEntity;
import com.kohler.service.SKUService;

/**
 * Class Function Description
 *
 * @author ztt
 * @Date 2014年10月11日
 */
@Service
public class SKUAttrServiceImpl implements SKUService {
    
    @Autowired
    private SKUDao skuDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SKUEntity> getSKUListByEntity(SKUEntity sku) {
        return skuDao.selectByCondition(sku);
    }
}
