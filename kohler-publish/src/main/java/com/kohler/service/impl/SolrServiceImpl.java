/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.impl;

import org.springframework.stereotype.Service;

import com.kohler.service.SolrService;

/**
 * MOD
 *
 * @author Administrator
 * @Date 2014年12月30日
 */
@Service("MOD")
public class SolrServiceImpl implements SolrService {

    /**
     * {@inheritDoc}
     */
    @Override
    public void importAllIndex() throws Exception {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void importDeltaIndex(String timestamp) throws Exception {
        // TODO Auto-generated method stub

    }

}
