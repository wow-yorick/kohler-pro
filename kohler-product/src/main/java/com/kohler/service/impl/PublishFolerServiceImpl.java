/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohler.dao.PublishFolderDao;
import com.kohler.entity.PublishFolderEntity;
import com.kohler.service.PublishFolderService;

/**
 * PublishFolder Interface Impl
 *
 * @author Sweety
 * @Date 2014年9月28日
 */
@Service
public class PublishFolerServiceImpl implements PublishFolderService {

    @Autowired 
    public PublishFolderDao publishFolderDao;

    @Override
    public List<PublishFolderEntity> getPublishFolders() {
        PublishFolderEntity publishFolder = new PublishFolderEntity();
        publishFolder.setIsEnable(true);
        return publishFolderDao.selectByCondition(publishFolder);
    }


}
