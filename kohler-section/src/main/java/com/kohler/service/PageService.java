/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import com.kohler.bean.Field;
import com.kohler.entity.AreaEntity;
import com.kohler.entity.AreaMetadataEntity;
import com.kohler.entity.ContentFieldValues;
import com.kohler.entity.ContentMetadataEntity;
import com.kohler.entity.MasterdataEntity;
import com.kohler.entity.TemplateEntity;
import com.kohler.entity.extend.PagePojo;
import com.kohler.pojo.DataTypePojo;

/**
 * Page Interface
 *
 * @author shefeng
 * @Date 2014年9月27日
 */
public interface PageService {
	
    /**
     * 根据PageId获取Page对象
     * @param pageId pageId
     * @return
     * @author kangmin_Qu
     * Date 2014-10-21
     * @version
     */
    public PagePojo getPageById(Integer pageId);
    
    /**
     * 新增一条Page Entity
     * @param pageEntity pageEntity
     * @return
     * @author kangmin_Qu
     * Date 2014-10-20
     * @version
     * @param selStr 
     */
    public Integer insertPage(PagePojo pagePojo, String selStr) throws UnsupportedEncodingException ;
    
    /**
     * 更新一条PageEntity
     * @param pageEntity
     * @return
     * @author kangmin_Qu
     * Date 2014-10-21
     * @version
     * @param selStr 
     */
    public Integer editPage(PagePojo pagePojo, String selStr)  throws UnsupportedEncodingException ;
    
	/**
	 * 查询ContentMetaData的数据
	 * @param dataTypeId DataTypeID
	 * @param selMap 查询参数Map
	 * @return 
	 */
	public Map<String,List<Map<String,Object>>> getContentMetadataList(Integer dataTypeId,Map<String,String> selMap);
	
	/**
	 * 查询字段数据
	 * @param id 字段ID
	 * @return
	 */
	public Map<String, List<ContentFieldValues>> getFieldValuesById(Integer id);
	
	/**
	 * 插入ContentData数据
	 * @param dataTypeId
	 * @param map
	 */
	public Integer insertContentFieldValues(Integer dataTypeId,Map<String,Field> map);
	
	/**
	 * 获取字段的值
	 * @param contentId 内容ID
	 * @param fieldName 字段名称
	 * @return
	 */
	public String getFieldValue(Integer contentId,String fieldName);
	
	/**
	 * 删除Content数据
	 * @param contentId
	 */
	public void deleteContentById(Integer contentId);
	
	
	/**
	 * 获取类型是Page的DataType
	 * @return
	 * @author kangmin_Qu
	 * Date 2014-10-17
	 * @version
	 */
	public List<DataTypePojo> getDataType();
	
	
	
	/**
	 * 根据ID获取DataTypePojo
	 * @param id
	 * @return
	 * @author kangmin_Qu
	 * Date 2014-10-21
	 * @version
	 */
	public DataTypePojo geteDataTypeById(Integer id);
	
	/**
	 * 
	 * @return
	 * @author kangmin_Qu
	 * Date 2014-10-20
	 * @version
	 */
	public List<TemplateEntity> getTemplateList(); 
	
	
	public ContentMetadataEntity getContentMetadataById(Integer id);
	
	public List<MasterdataEntity> getMasterdataByName(String masterdataTypeName, String lang);

    public String getLangFieldValue(Integer contentId,Integer lang, String fieldName);

    /**
     * @param fieldAssetIds
     * @return
     * @author sana
     * Date 2014年11月25日
     * @version
     */
    public List<Map<String, Object>> getFileAssetName(String fieldAssetIds);

    /**
     * @param parseInt
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
     * @param mastercode 
     */
    public List<Map<String, Object>> getContentFieldValues(String pageIds, Integer mastercode);

    /**
     * @param contentMetadataIds
     * @return
     * @author sana
     * Date 2014年12月12日
     * @version
     */
    public Map<String, List<Map<String, Object>>> getContentByIdsList(String contentMetadataIds);

    /**
     * @param parentId
     * @return
     * @author sana
     * Date 2014年12月15日
     * @version
     */
    public List<AreaEntity> getAreaListByParentId(String parentId);

    /**
     * @param areaMetadataId
     * @return
     * @author sana
     * Date 2014年12月16日
     * @version
     */
    public AreaMetadataEntity getAreaMetadataByMetadataId(String areaMetadataId);

    /**
     * @param masterEntity
     * @return
     * @author sana
     * Date 2014年12月18日
     * @version
     */
    public MasterdataEntity getMasterdataByMetadataId(MasterdataEntity masterEntity);
}
