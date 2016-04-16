/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.dao;

import java.util.List;
import java.util.Map;

import com.kohler.entity.ContentFieldValues;
import com.kohler.entity.PageEntity;

/**
 * Page Dao 
 *
 * @author kangmin_Qu
 * @Date 2014-10-15
 */
public interface PageDao extends BaseDao<PageEntity>{
    
    /**
     * 前台页面展示列表
     * @param dataTypeId dataType id
     * @param selMap 查询参数
     * @return
     * @author kangmin_Qu
     * Date 2014-10-20
     * @version
     */
    public List<Map<String, Object>> getContentMetadataList(Integer dataTypeId,Map<String,String> selMap);
    
    
    
    /**
     * 根据ID获取到Field的值
     * @param id Id
     * @return
     * @author kangmin_Qu
     * Date 2014-10-20
     * @version
     */
    public Map<String, List<ContentFieldValues>> getFieldValuesById(Integer id);
    
    
    
    /**
     * 插入COntent
     * @param content content
     * @author kangmin_Qu
     * Date 2014-10-20
     * @version
     */
    public void insertContent(ContentFieldValues content);
    
    
    
    /**
     * 插入ContentMetaData
     * @param dataTypeId dataTypeId
     * @return
     * @author kangmin_Qu
     * Date 2014-10-20
     * @version
     */
    public Integer insertContentMetadata(Integer dataTypeId);
    
    
    
    /**
     * 获取字段的值
     * @param contentId contentId
     * @param fieldName 字段名称
     * @return
     * @author kangmin_Qu
     * Date 2014-10-20
     * @version
     */
    public String getFieldValue(Integer contentId,String fieldName);
    
    
    
    /**
     * 删除Content
     * @param contentId
     * @author kangmin_Qu
     * Date 2014-10-20
     * @version
     */
    public void deleteContentById(Integer contentId);


    /**
     * 获取各语言的值
     * @param contentId
     * @param lang
     * @param fieldName
     * @return
     * @author sana
     * Date 2014年12月4日
     * @version
     */
    public String getLangFieldValue(Integer contentId, Integer lang, String fieldName);



    /**
     * @param fieldAssetIds
     * @return
     * @author sana
     * Date 2014年11月25日
     * @version
     */
    public List<Map<String, Object>> getFileAssetName(String fieldAssetIds);



    /**
     * @param pageId
     * @param fieldName
     * @return
     * @author sana
     * Date 2014年11月25日
     * @version
     */
    public String getPageFieldValue(int pageId, String fieldName);



    /**
     * @param pageIds
     * @return
     * @author sana
     * Date 2014年11月25日
     * @version
     * @param mastercIntegerode 
     */
    public List<Map<String, Object>> getContentFieldValues(String pageIds, Integer mastercIntegerode);
    
    

}
