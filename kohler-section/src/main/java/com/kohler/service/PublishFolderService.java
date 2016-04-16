/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service;

import java.util.List;

import com.kohler.entity.PublishFolderEntity;

/**
 * PublishFolder Interface
 *
 * @author shefeng
 * @Date 2014年9月28日
 */
public interface PublishFolderService {

    /**
     * @return
     * @author shefeng Date 2014年9月28日
     * @version
     */
    public List<PublishFolderEntity> getPublishFolders();
}
