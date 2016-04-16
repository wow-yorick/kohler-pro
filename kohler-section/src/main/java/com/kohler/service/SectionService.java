/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.service;

import java.util.List;
import java.util.Map;

import com.kohler.dao.utils.Pager;
import com.kohler.entity.SectionEntity;

/**
 * Section Interface
 *
 * @author shefeng
 * @Date 2014年9月25日
 */
public interface SectionService {

    /**
     * @return
     * @author shefeng Date 2014年9月27日
     * @version
     */
    public List<Map<String, Object>> getSectionWithMap();

    
    
    /**
     * @param pager
     * @param sectionId
     * @return
     * @author shefeng Date 2014年9月28日
     * @version
     */
    public Pager<Map<String, Object>> getSectionPageById(Pager<Map<String, Object>> pager,
            Integer sectionId);
    
    
    
    /**
     * @param section
     * @return
     * @author Sweety Date 2014年9月28日
     * @version
     */
    public SectionEntity addSection(SectionEntity section);
    
    
    
    /**
     * @param section
     * @return
     * @author Sweety
     * Date 2014年9月28日
     * @version
     */
    public Integer editSection(SectionEntity section);

    
    
    /**
     * @param sectionId
     * @return
     * @author Sweety
     * Date 2014年9月28日
     * @version
     */
    public SectionEntity getSectionById(Integer sectionId);

    
    
    /**
     * @param list
     * @author Sweety
     * Date 2014年9月29日
     * @version
     */
    public void deleteSection(List<Map<String, Object>> list);



    /**
     * @param sectionName
     * @return
     * @author Sweety
     * Date 2014年10月13日
     * @version
     */
    public int checkSectionName(String sectionName , String orgSectionName);
}
