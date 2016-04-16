/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kohler.bean.Field;
import com.kohler.constants.ContentSQL;
import com.kohler.dao.AreaDao;
import com.kohler.dao.AreaMetadataDao;
import com.kohler.dao.ContentDao;
import com.kohler.dao.ContentMetadataDao;
import com.kohler.dao.DatatypeDao;
import com.kohler.dao.LocaleDao;
import com.kohler.dao.MasterDataDao;
import com.kohler.dao.PageDao;
import com.kohler.dao.PageFieldValuesDao;
import com.kohler.dao.SysLogDao;
import com.kohler.dao.TemplateDao;
import com.kohler.dao.utils.SqlUtils;
import com.kohler.entity.AreaEntity;
import com.kohler.entity.AreaMetadataEntity;
import com.kohler.entity.ContentFieldValues;
import com.kohler.entity.ContentMetadataEntity;
import com.kohler.entity.DatatypeEntity;
import com.kohler.entity.LocaleEntity;
import com.kohler.entity.MasterDataCodeConstant;
import com.kohler.entity.MasterdataEntity;
import com.kohler.entity.PageEntity;
import com.kohler.entity.PageFieldValuesEntity;
import com.kohler.entity.TemplateEntity;
import com.kohler.entity.extend.PagePojo;
import com.kohler.pojo.DataTypePojo;
import com.kohler.service.PageService;

/**
 * Page Interface Impl
 * 
 * @author shefeng
 * @Date 2014年9月28日
 */

@Service("pageService")
public class PageServiceImpl implements PageService {

    @Autowired
    private PageDao     pageDao;

    @Autowired
    private DatatypeDao datatypeDao;

    @Autowired
    private TemplateDao templateDao;

    @Autowired
    private SysLogDao   sysLogDao;
    
    @Autowired
    private MasterDataDao masterdataDao;
    
    @Autowired
    private ContentMetadataDao contentMetadataDao;
    
    @Autowired
    private PageFieldValuesDao pageFieldValuesDao;
    
    @Autowired
    private LocaleDao localeDao;
    
    @Autowired 
    private ContentDao contentDao;
    
    @Autowired
    private AreaDao areaDao;
    
    @Autowired
    private AreaMetadataDao areaMetadataDao;

    public Map<String, List<Map<String, Object>>> getContentMetadataList(Integer dataTypeId,
            Map<String, String> selMap) {
      //解析查询
        String linksql = "";
        List<String> klist = new ArrayList<String>();
        
        for (String key : selMap.keySet()) {
            linksql += linksql +"or (FIELD_NAME = '"+key+"'and FIELD_VALUE like CONCAT(CONCAT('%','"+SqlUtils.replaceSearchStr(selMap.get(key))+"') ,'%')) ";
        }
        if(!"".equals(linksql)){
            linksql=" and "+linksql.substring(2);
        }
        //解析
        
        List<Object> params = new ArrayList<Object>();

        params.add(dataTypeId);
        
        List<Object> langparams = new ArrayList<Object>();
        List<LocaleEntity> locallist =  localeDao.selectByCondition(ContentSQL.SELECT_LOCAL_LANG,langparams);
        String sqlparams = "v.LANG is null ";
        if(locallist != null && locallist.size() > 0){
            sqlparams += "or v.LANG = "+locallist.get(0).getLocaleId();
        }
        
        //显示各列,只要中文的
        String selectsql = "SELECT * from CONTENT_FIELD_VALUES where CONTENT_METADATA_ID in (select v.CONTENT_METADATA_ID from CONTENT_FIELD_VALUES v "+
                " left join CONTENT_METADATA m on v.CONTENT_METADATA_ID =m.CONTENT_METADATA_ID "+
                " where m.IS_ENABLE=1 and v.IS_ENABLE =1 and ("+sqlparams+") and m.DATATYPE_ID =? "+linksql+
                " GROUP BY v.CONTENT_METADATA_ID having count(v.CONTENT_METADATA_ID)>"+(klist.size()/2-1)+")";
        //查询并过滤
        List<Map<String, Object>> list = contentDao.getSelectContentMetadataList(selectsql, dataTypeId);
        Map<String, List<Map<String, Object>>> map = new HashMap<String, List<Map<String, Object>>>();

        
        
        
        for (Map<String, Object> _map : list) {
            String obj = _map.get("CONTENT_METADATA_ID").toString();
            if (map.containsKey(obj)) {
                map.get(obj).add(_map);
            } else {
                List<Map<String, Object>> _list = new ArrayList<Map<String, Object>>();
                _list.add(_map);
                map.put(obj, _list);
            }
        }
        
        return map;
        
        /*List<Map<String, Object>> list = pageDao.getContentMetadataList(dataTypeId, selMap);

        Map<String, List<Map<String, Object>>> map = new HashMap<String, List<Map<String, Object>>>();

        for (Map<String, Object> _map : list) {
            String obj = _map.get("CONTENT_METADATA_ID").toString();
            if (map.containsKey(obj)) {
                map.get(obj).add(_map);
            } else {
                List<Map<String, Object>> _list = new ArrayList<Map<String, Object>>();
                _list.add(_map);
                map.put(obj, _list);
            }
        }

        // 过滤搜索条件
        List<String> removeKeys = new ArrayList<String>();
        Set<Entry<String, List<Map<String, Object>>>> _map = map.entrySet();
        for (Entry<String, List<Map<String, Object>>> _entry : _map) {
            String _key = _entry.getKey();
            List<Map<String, Object>> _value = _entry.getValue();

            for (Map<String, Object> __map : _value) {
                String _fieldName = __map.get("FIELD_NAME").toString();
                String _fieldValue = __map.get("FIELD_Value").toString();
                Set<Entry<String, String>> _selSet = selMap.entrySet();
                for (Entry<String, String> _setEntry : _selSet) {
                    String _selKey = _setEntry.getKey();
                    String _selValue = _setEntry.getValue();

                    if (_fieldName.equals(_selKey) && _fieldValue.equals(_selValue)) {
                        removeKeys.add(_key);
                        break;
                    }
                }
            }
        }

        // 删除过滤的数据
        Map<String, List<Map<String, Object>>> resultMap = new HashMap<String, List<Map<String, Object>>>();
        if (selMap != null && selMap.size() > 0) {
            for (String _key : removeKeys) {
                if (map.containsKey(_key)) {
                    resultMap.put(_key, map.get(_key));
                }
            }
        } else {
            resultMap = map;
        }

        return resultMap;*/
    }

    public Map<String, List<ContentFieldValues>> getFieldValuesById(Integer id) {
        return pageDao.getFieldValuesById(id);
    }

    @Transactional
    public Integer insertContentFieldValues(Integer dataTypeId, Map<String, Field> map) {
        Integer contentMetadataId = pageDao.insertContentMetadata(dataTypeId);

        Set<Entry<String, Field>> set = map.entrySet();

        for (Entry<String, Field> entry : set) {
            Field field = entry.getValue();

            ContentFieldValues content = new ContentFieldValues();

            content.setContentMetadataId(contentMetadataId);
            content.setFieldName(field.getName());
            content.setFieldValue(field.getValue());

            String editorType = field.getEditorType();
            if ("DataTypeListPicker".equals(editorType)) {
                content.setComplicate(1);
            } else if ("ProductListPicker".equals(editorType)) {
                content.setComplicate(2);
            } else {
                content.setComplicate(0);
            }
            pageDao.insertContent(content);
        }
        
        return contentMetadataId;
    }

    public String getFieldValue(Integer contentId, String fieldName) {
        return pageDao.getFieldValue(contentId, fieldName);
    }

    public void deleteContentById(Integer contentId) {
        pageDao.deleteContentById(contentId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DataTypePojo> getDataType() {
        /*
         * Type mean : 1 : DataType 10100002 : Page
         */
        List<DatatypeEntity> list = datatypeDao.getDataTypeByType(10100002);

        List<DataTypePojo> pojoList = new ArrayList<DataTypePojo>();

        // Transfer Entity to Pojo
        if (list != null && list.size() > 0) {
            for (DatatypeEntity entity : list) {
                DataTypePojo pojo = new DataTypePojo();
                BeanUtils.copyProperties(entity, pojo);
                pojoList.add(pojo);
            }
        }

        return pojoList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TemplateEntity> getTemplateList() {
        TemplateEntity entity = new TemplateEntity();
        entity.setIsEnable(true);
        entity.setTemplateType(MasterDataCodeConstant.TEMPLATE_TYPE_PAGE);
        return this.templateDao.selectByCondition(entity);
    }

    /**
     * {@inheritDoc}
     * @throws UnsupportedEncodingException 
     */
    @Override
    @Transactional
    public Integer insertPage(PagePojo pagePojo,String selStr) throws UnsupportedEncodingException {
        PageEntity pageEntity = new PageEntity();
        
        if(pagePojo != null){
            BeanUtils.copyProperties(pagePojo, pageEntity);
            pageEntity.setIsEnable(true);
            // insert page
            Integer pageId = pageDao.insert(pageEntity);
            // 插入日志
            sysLogDao.insertLogForInsert(pageId, "PAGE");
            
            if(selStr != null && !"".equals(selStr)){
                String[] selstrs = selStr.split("&");
                
                for (String str : selstrs) {
                    String[] keyval = str.split("=");
                    if(keyval!=null&&keyval.length>1){
                        PageFieldValuesEntity pfve = new PageFieldValuesEntity();
                        pfve.setIsEnable(true);
                        pfve.setPageId(pageId);
                        pfve.setFieldName(keyval[0]);
                        List<PageFieldValuesEntity> pfvlist = pageFieldValuesDao.selectByCondition(pfve);
                        if(pfvlist!=null&&pfvlist.size()>0){
                            pfve = pfvlist.get(0);
                            pfve.setFieldValue(URLDecoder.decode(keyval[1],"utf-8"));
                            pageFieldValuesDao.update(pfve);
                            sysLogDao.insertLogForUpdate(pfve.getPageFieldValuesId(), "PAGE_FIELD_VALUES");
                        }else{
                            pfve.setFieldValue(URLDecoder.decode(keyval[1],"utf-8"));
                            pageFieldValuesDao.insert(pfve);  
                            sysLogDao.insertLogForUpdate(pfve.getPageFieldValuesId(), "PAGE_FIELD_VALUES");
                        }
                    }
                }
                
            }
            
            return pageId;
        }
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PagePojo getPageById(Integer pageId) {
        // Get PageEntity By Page Id
        PageEntity pageEntity = this.pageDao.selectById(pageId);

        // New Instance Page Pojo
        PagePojo pagePojo = new PagePojo();

        // Transfer PageEntity To PagePojo
        if(pageEntity != null){
            BeanUtils.copyProperties(pageEntity, pagePojo);
        }
        return pagePojo;
    }

    /**
     * {@inheritDoc}
     * @throws UnsupportedEncodingException 
     */
    @Override
    @Transactional
    public Integer editPage(PagePojo pagePojo, String selStr) throws UnsupportedEncodingException {
        
        PageEntity pageEntity = new PageEntity();
        
        if(pagePojo != null){
            BeanUtils.copyProperties(pagePojo, pageEntity);
            pageEntity.setIsEnable(true);
        }
        // update page
        Integer isSuccess = pageDao.update(pageEntity);

        if (isSuccess == 1) {
            sysLogDao.insertLogForUpdate(pagePojo.getPageId(), "PAGE");
        }
        
        //解析
        if(selStr != null && !"".equals(selStr)){
            String[] selstrs = selStr.split("&");
            
            for (String str : selstrs) {
                String[] keyval = str.split("=");
                if(keyval!=null&&keyval.length>1){
                    PageFieldValuesEntity pfve = new PageFieldValuesEntity();
                    pfve.setIsEnable(true);
                    pfve.setPageId(pagePojo.getPageId());
                    pfve.setFieldName(keyval[0]);
                    List<PageFieldValuesEntity> pfvlist = pageFieldValuesDao.selectByCondition(pfve);
                    if(pfvlist!=null&&pfvlist.size()>0){
                        pfve = pfvlist.get(0);
                        pfve.setFieldValue(URLDecoder.decode(keyval[1],"utf-8"));
                        pageFieldValuesDao.update(pfve);
                        sysLogDao.insertLogForUpdate(pfve.getPageFieldValuesId(), "PAGE_FIELD_VALUES");
                    }else{
                        pfve.setFieldValue(URLDecoder.decode(keyval[1],"utf-8"));
                        pageFieldValuesDao.insert(pfve);  
                        sysLogDao.insertLogForUpdate(pfve.getPageFieldValuesId(), "PAGE_FIELD_VALUES");
                    }
                }
            }
            
        }
        
        
        return isSuccess;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public DataTypePojo geteDataTypeById(Integer id) {
        DatatypeEntity entity = this.datatypeDao.selectById(id);
        
        DataTypePojo pojo = new DataTypePojo();
        if(entity != null){
        BeanUtils.copyProperties(entity, pojo);
        }
        
        return pojo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ContentMetadataEntity getContentMetadataById(Integer id) {
        return contentMetadataDao.selectById(id);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<MasterdataEntity> getMasterdataByName(String masterdataTypeName, String lang) {
        List<Object> params = new ArrayList<Object>();
        params.add(masterdataTypeName);
        params.add(lang);
        return masterdataDao.selectByCondition(ContentSQL.SELECT_MASTERDATA_BY_NAME_LANG, params);
    }

    @Override
    public String getLangFieldValue(Integer contentId, Integer lang, String fieldName) {
        return pageDao.getLangFieldValue(contentId, lang, fieldName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Map<String, Object>> getFileAssetName(String fieldAssetIds) {
        // TODO Auto-generated method stub
        return pageDao.getFileAssetName(fieldAssetIds);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPageFieldValue(int pageId, String fieldName) {
        // TODO Auto-generated method stub
        return pageDao.getPageFieldValue(pageId, fieldName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Map<String, Object>> getContentFieldValues(String pageIds,Integer mastercode) {
        // TODO Auto-generated method stub
        return pageDao.getContentFieldValues(pageIds,mastercode);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, List<Map<String, Object>>> getContentByIdsList(String contentMetadataIds) {
        if(contentMetadataIds!=null&&!"".equals(contentMetadataIds)){
            List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
            if(contentMetadataIds.indexOf(",")>-1){
                String[] contentIds = contentMetadataIds.split(",");
                for (String id : contentIds) {
                    String selectsql = "SELECT * from CONTENT_FIELD_VALUES where CONTENT_METADATA_ID = ? ";
                    //查询并过滤
                    List<Map<String, Object>> everylist = contentDao.getSelectContentMetadataList(selectsql, Integer.parseInt(id));
                    for (Map<String, Object> map : everylist) {
                        list.add(map);
                    }
                }
            }else{
                String selectsql = "SELECT * from CONTENT_FIELD_VALUES where CONTENT_METADATA_ID = ? ";
                //查询并过滤
                list = contentDao.getSelectContentMetadataList(selectsql, Integer.parseInt(contentMetadataIds));
            }
            Map<String, List<Map<String, Object>>> map = new HashMap<String, List<Map<String, Object>>>();
            for (Map<String, Object> _map : list) {
                String obj = _map.get("CONTENT_METADATA_ID").toString();
                if (map.containsKey(obj)) {
                    map.get(obj).add(_map);
                } else {
                    List<Map<String, Object>> _list = new ArrayList<Map<String, Object>>();
                    _list.add(_map);
                    map.put(obj, _list);
                }
            }
            return map;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AreaEntity> getAreaListByParentId(String parentId) {
        List<Object> params = new ArrayList<Object>();
        params.add(parentId);
        return areaDao.selectByCondition(ContentSQL.SELECT_AREA_BY_PARENT_ID, params);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AreaMetadataEntity getAreaMetadataByMetadataId(String areaMetadataId) {
        return areaMetadataDao.selectById(areaMetadataId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MasterdataEntity getMasterdataByMetadataId(MasterdataEntity masterEntity) {
        List<MasterdataEntity> list = masterdataDao.selectByCondition(masterEntity);
        if(list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;
    }
}
