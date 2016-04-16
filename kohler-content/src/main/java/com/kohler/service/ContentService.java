/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.service;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.kohler.dao.utils.Pager;
import com.kohler.entity.AreaEntity;
import com.kohler.entity.ContentFieldValues;
import com.kohler.entity.ContentMetadataEntity;
import com.kohler.entity.DatatypeEntity;
import com.kohler.entity.MasterdataEntity;

/**
 * Section Interface
 *
 * @author WHH
 * @Date 2014年10月28日
 */
public interface ContentService {

    /**
     * @return
     * @author WHH Date 2014年10月28日
     * @version
     */
    public List<Map<String, Object>> getContentWithMap();
    
    /**
     * 查询content列表
     * @param pager
     * @param sectionId
     * @param fieldlist
     * @return
     * @author WHH Date 2014年10月29日
     * @version
     * @param selStr 
     */
    public Pager<Map<String, Object>> getContentPageById(Pager<Map<String, Object>> pager,
            Integer datatypeId, List<String> fieldlist, String selStr) throws ParseException, UnsupportedEncodingException;

    /**
     * 通过id获取datatype的数据
     * @param datatypeId
     * @return
     * @author WHH Date 2014年10月29日
     * @version
     */
    public DatatypeEntity getDatatypeById(Integer datatypeId);

    
   
    /**
     * 保存content
     * @param contentMetadata
     * @param selStr
     * @param l
     * @return
     * @throws UnsupportedEncodingException
     * @author sana
     * Date 2014年10月31日
     * @version
     */
    public int saveContent(ContentMetadataEntity contentMetadata, String selStr, List<Map<String, Object>> l)throws UnsupportedEncodingException;

    /**
     * 获取content
     * @param contentMetadataId
     * @return
     * @author sana
     * Date 2014年10月31日
     * @version
     */
    public ContentMetadataEntity getContentMetadataById(Integer contentMetadataId);

    /**
     * 根据metadataId获取ContentField
     * @param contentMetadataId
     * @return
     * @author sana
     * Date 2014年10月31日
     * @version
     */
    public List<ContentFieldValues> getContentFieldValuesByMetadataId(Integer contentMetadataId);

    /**
     * 删除
     * @param list
     * @author sana
     * Date 2014年10月31日
     * @version
     */
    public void deleteContent(List<Map<String, Object>> list);

    /**
     * 获取masterdata值
     * @param masterdataTypeName
     * @param lang
     * @return
     * @author sana
     * Date 2014年10月31日
     * @version
     */
    public List<MasterdataEntity> getMasterdataByName(String masterdataTypeName, String lang);

    /**
     * 唯一性检测
     * @param fieldname
     * @param fieldvalue
     * @param lang
     * @param metadataId
     * @param datatypeId
     * @return
     * @author sana
     * Date 2014年11月14日
     * @version
     */
    public List<ContentFieldValues> checkContentValue(String fieldname, String fieldvalue, String lang, String metadataId, String datatypeId);

    /**
     * @return
     * @author sana
     * Date 2014年12月10日
     * @version
     */
    public ContentMetadataEntity getMaxContentMetadata();

    /**
     * @param parentId
     * @return
     * @author sana
     * Date 2014年12月16日
     * @version
     */
    public List<AreaEntity> getAreaListByParentId(String parentId);
    
}
