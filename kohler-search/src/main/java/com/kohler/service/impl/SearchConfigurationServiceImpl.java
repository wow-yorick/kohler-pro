/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kohler.dao.SearchConfigurationDao;
import com.kohler.entity.MasterdataEntity;
import com.kohler.service.SearchConfigurationService;

/**
 * Configuration service implement
 * 
 * @author YS
 * @Date 10/17/2014
 */
@Service
public class SearchConfigurationServiceImpl implements SearchConfigurationService {
    @Autowired
    private SearchConfigurationDao conDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public int updateConfiuraction(MasterdataEntity entity) {
        // TODO Auto-generated method stub
        
        return conDao.updateDidYouMean(entity);
    }
}
