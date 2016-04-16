/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.base;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kohler.dao.PublishFolderDao;
import com.kohler.dao.SectionDao;
import com.kohler.entity.PublishFolderEntity;
import com.kohler.entity.SectionEntity;

/**
 * section common func
 *
 * @author Administrator
 * @Date 2014年12月25日
 */
@Component
public class BaseForSection {
    
    //private final static Logger logger = Logger.getLogger(BaseForSection.class);
    
    @Autowired
    private PublishFolderDao publishFolderDao;
    
    @Autowired
    private SectionDao sectionDao;
    
    /**
     * 获取SECTION的发布folder
     * @param currentSectionId
     * @return
     * @author Administrator
     * Date 2014年10月24日
     * @version
     */
    public  String getPublishFolderFromSection(Integer currentSectionId) {
        List<SectionEntity> tree = new ArrayList<SectionEntity>(); //存放sectionEntity的层级关系
        setSectionTree(currentSectionId,tree);//获取sectionEntity的层级关系并存入tree
        StringBuilder publishFolder = new StringBuilder();
        for(int i= tree.size()-1;i >=0; i--) {
            PublishFolderEntity publishFolderEntity =publishFolderDao.selectById(tree.get(i).getPublishFolderId());
            publishFolder.append(publishFolderEntity.getPublishFolderPath());
            
        }
        return publishFolder.toString();
    }
    
    
    /**
     * 获取section的层级结构
     * @param currentSectionId
     * @param tree
     * @author Administrator
     * Date 2014年10月24日
     * @version
     */
    public void setSectionTree(Integer currentSectionId,List<SectionEntity> tree) {
        //try {
            SectionEntity sectionEntity = sectionDao.selectById(currentSectionId);
            tree.add(sectionEntity);
            if(sectionEntity.getParentId() != 0) {
                setSectionTree(sectionEntity.getParentId(), tree);
            }
//        } catch (Exception e) {
//            logger.debug("get sectionEntity fail currentSectionId:"+currentSectionId);
//        }
    }
    
}
