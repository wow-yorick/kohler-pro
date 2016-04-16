/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.needpublishdata;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kohler.bean.ConfPrepareData;
import com.kohler.constants.CommonConstants;
import com.kohler.dao.PageDao;
import com.kohler.dao.SectionDao;
import com.kohler.entity.PageEntity;
import com.kohler.entity.SectionEntity;
import com.kohler.service.NeedPublishDataService;

/**
 * 获取普通页面列表
 *
 * @author Administrator
 * @Date 2014年11月17日
 */
@Component
public class PageList implements NeedPublishDataService {
    
    @Autowired
    private PageDao pageDao;
    
    @Autowired
    private SectionDao sectionDao;

    /**
     * 区分平台/语言
     * {@inheritDoc}
     */
    @Override
    public List<Integer> getPrimaryKeyList(Integer keyId, ConfPrepareData conf) {
        List<Integer> retList = new ArrayList<Integer>();
        Integer topSectionId = getTopSectionId(conf);
        List<Object> sessionIdList = getCurrentClassifyIdList(topSectionId, new ArrayList<Object>());
        sessionIdList.add(topSectionId);
        
        StringBuffer sql = new StringBuffer("SELECT * FROM PAGE WHERE SECTION_ID IN( ");
        
        for(int i =0; i< sessionIdList.size(); i++) {
            sql.append("?,");
        }
        sql.deleteCharAt(sql.length()-1);
        sql.append(")");

        List<PageEntity> pageList = pageDao.selectByCondition(sql.toString(),sessionIdList);
        for(PageEntity page : pageList) {
            if(page.getTemplateId() != null) {//没有设置模板的页面跳过
                retList.add(page.getPageId());
            }
        }
        return retList;
    }
    
    /**
     * get top section id
     * @param conf
     * @return
     * @author Administrator
     * Date 2014年12月17日
     * @version
     */
    private Integer getTopSectionId(ConfPrepareData conf) {
        Integer sectionHomeId = CommonConstants.SECTION_HOME_PC_CN;//默认中文pc
        if(conf.getLang() == CommonConstants.LOCALE_CN && conf.getWebsitePlatform() == CommonConstants.PC_PLATFORM) { //PC/CN
            sectionHomeId = CommonConstants.SECTION_HOME_PC_CN;
        } else if(conf.getLang() == CommonConstants.LOCALE_EN && conf.getWebsitePlatform() == CommonConstants.PC_PLATFORM) { //PC/EN
            sectionHomeId = CommonConstants.SECTION_HOME_PC_EN;
        } else if(conf.getLang() == CommonConstants.LOCALE_CN && conf.getWebsitePlatform() == CommonConstants.MOBILE_PLATFORM) { //MOBILE/CN
            sectionHomeId = CommonConstants.SECTION_HOME_MOBILE_CN;
        }else {
            sectionHomeId = CommonConstants.SECTION_HOME_MOBILE_EN;
        }
        return sectionHomeId;
    }
    
    /**
     * get current classify id list
     * @param topSectionId
     * @param sectionTree
     * @return
     * @author Administrator
     * Date 2014年12月17日
     * @version
     */
    private List<Object> getCurrentClassifyIdList(Integer topSectionId, List<Object> sectionTree) {
        StringBuffer sql = new StringBuffer("SELECT * FROM SECTION WHERE PARENT_ID = ? AND IS_ENABLE = 1");
        List<Object> params = new ArrayList<Object>();
        params.add(topSectionId);
        List<SectionEntity> retSection = sectionDao.selectByCondition(sql.toString(), params);
        
        if(retSection != null && retSection.size() > 0) {
            for(SectionEntity _section : retSection) {
                Integer thsSectionId = _section.getSectionId();
                sectionTree.add(thsSectionId);
                getCurrentClassifyIdList(thsSectionId,sectionTree);
            }
        }
        
        return sectionTree;
    }

}
