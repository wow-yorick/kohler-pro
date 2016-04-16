/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.breadcrumb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kohler.bean.ConfPrepareData;
import com.kohler.dao.PageDao;
import com.kohler.entity.PageEntity;
import com.kohler.entity.SectionEntity;
import com.kohler.exception.DataException;
import com.kohler.service.BreadCrumbService;
import com.kohler.service.base.BaseForCategory;
import com.kohler.service.base.BaseForSection;
import com.kohler.service.url.PageUrlAnalysis;

/**
 * section breadcrumb
 *
 * @author Administrator
 * @Date 2014年12月25日
 */
@Component
public class PageDataBreadcrumb implements BreadCrumbService {
    
    @Autowired
    private BaseForSection baseForSection;
    
    @Autowired
    private BaseForCategory baseForCategory;
    
    @Autowired
    private PageDao pageDao;
    
    @Autowired
    private PageUrlAnalysis pageUrlAnalysis;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Map<String, Object>> getBreadCrumb(ConfPrepareData conf)
            throws DataException {
        List<Map<String,Object>> breadCrumbs = new ArrayList<Map<String,Object>>();
        
        //首页面包屑
        Map<String, Object> homeBreadBean =  baseForCategory.getHomeBreadcrumb(conf);
        breadCrumbs.add(homeBreadBean);
        
        List<SectionEntity> sectionTree = new ArrayList<SectionEntity>();
        PageEntity pageEntity = pageDao.selectById(conf.getDataId());
        baseForSection.setSectionTree(pageEntity.getSectionId(), sectionTree);
        
        for(int i= sectionTree.size()-1;i >=0; i--) {
            Map<String,Object> breadBean = new HashMap<String, Object>();//面包屑单元素容器
            SectionEntity sectionEntity = sectionTree.get(i);//获取当前section
            breadBean.put("name", sectionEntity.getSectionName());//获取面包屑名称
            String url = pageUrlAnalysis.getUrl(sectionEntity.getSectionId(), conf);//获取url
            breadBean.put("url", url);
            breadCrumbs.add(breadBean);
        }
        
        return breadCrumbs;
        
    }

}
