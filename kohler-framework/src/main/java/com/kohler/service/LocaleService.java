/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.service;

import java.util.List;

import com.kohler.entity.LocaleEntity;

/**
 * Locate Interface
 *
 * @author shefeng
 * @Date 2014年9月25日
 */
public interface LocaleService {

    /**
     * @return
     * @author shefeng Date 2014年9月27日
     * @version
     */
    public List<LocaleEntity> getLanguages();

}
