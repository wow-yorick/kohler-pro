/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kohler.constants.ContentSQL;
import com.kohler.dao.AreaDao;
import com.kohler.dao.ContentDao;
import com.kohler.dao.ContentFieldValuesDao;
import com.kohler.dao.DatatypeDao;
import com.kohler.dao.LocaleDao;
import com.kohler.dao.MasterDataDao;
import com.kohler.dao.PageDao;
import com.kohler.dao.SysLogDao;
import com.kohler.dao.utils.Pager;
import com.kohler.dao.utils.SqlUtils;
import com.kohler.entity.AreaEntity;
import com.kohler.entity.ContentFieldValues;
import com.kohler.entity.ContentMetadataEntity;
import com.kohler.entity.DatatypeEntity;
import com.kohler.entity.LocaleEntity;
import com.kohler.entity.MasterdataEntity;
import com.kohler.service.ContentService;

/**
 * Section Interface Impl
 *
 * @author WHH
 * @Date 2014年10月28日
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired 
    private ContentDao contentDao;
    
    @Autowired
    private PageDao pageDao;
    
    @Autowired
    private DatatypeDao datatypeDao;
    
    @Autowired 
    private SysLogDao sysLogDao;
    
    @Autowired
    private ContentFieldValuesDao contentFieldValuesDao;
    
    @Autowired
    private LocaleDao localeDao;
    
    @Autowired
    private MasterDataDao masterdataDao;
    
    @Autowired
    private AreaDao areaDao;

    @Override
    public List<Map<String, Object>> getContentWithMap() {
        return contentDao.selectContents();
    }


    /**
     * {@inheritDoc}
     * @throws ParseException 
     * @throws UnsupportedEncodingException 
     */
    @Override
    public Pager<Map<String, Object>> getContentPageById(Pager<Map<String, Object>> pager,
            Integer datatypeId, List<String> fieldlist, String selStr) throws ParseException, UnsupportedEncodingException {
        //解析查询
        String linksql = "";
        List<String> klist = new ArrayList<String>();
        //解析
        if(selStr != null && !"".equals(selStr)){
            String[] selstrs = selStr.split("&");
            
            for (String str : selstrs) {
                String[] keyval = str.split("=");
                if(keyval!=null&&keyval.length>1){
                    klist.add(keyval[0]);
                    klist.add(URLDecoder.decode(keyval[1],"utf-8"));
                }
            }
            for (int i = 0; i < klist.size(); i=i+2) {
                linksql += linksql +"or (FIELD_NAME = '"+klist.get(i)+"'and FIELD_VALUE like CONCAT(CONCAT('%','"+SqlUtils.replaceSearchStr(klist.get(i+1))+"') ,'%')) ";
            }
            if(!"".equals(linksql)){
                linksql=" and "+linksql.substring(2);
            }
        }
        List<Object> params = new ArrayList<Object>();
        int pageCount = 0;
        //pager.setPageSize(5);
        int startRow = (pager.getCurrentPage() - 1) * pager.getPageSize();

        params.add(datatypeId);
        
        List<Object> langparams = new ArrayList<Object>();
        List<LocaleEntity> locallist =  localeDao.selectByCondition(ContentSQL.SELECT_LOCAL_LANG,langparams);
        String sqlparams = "v.LANG is null ";
        if(locallist != null && locallist.size() > 0){
            sqlparams += "or v.LANG = "+locallist.get(0).getLocaleId();
        }
        
        //得到需要的id集合
        String selectsql = "SELECT DISTINCT CONTENT_METADATA_ID from CONTENT_FIELD_VALUES where CONTENT_METADATA_ID in (select v.CONTENT_METADATA_ID from CONTENT_FIELD_VALUES v "+
                " left join CONTENT_METADATA m on v.CONTENT_METADATA_ID =m.CONTENT_METADATA_ID "+
                " where m.IS_ENABLE=1 and v.IS_ENABLE =1 and ("+sqlparams+") and m.DATATYPE_ID =? "+linksql+
                " GROUP BY v.CONTENT_METADATA_ID having count(v.CONTENT_METADATA_ID)>"+(klist.size()/2-1)+")";
        //查询并过滤
        List<Map<String, Object>> idlist = contentDao.getSelectContentMetadataList(selectsql, datatypeId);
        StringBuffer sb = new StringBuffer();
        sb.append(",");
        for (Map<String, Object> map : idlist) {
            sb.append(map.get("CONTENT_METADATA_ID"));
            sb.append(",");
        }
        String str = sb.toString();
        String pagesql = "SELECT * from CONTENT_METADATA where ? like  concat(concat('%,',CONTENT_METADATA_ID),',%') order by MODIFY_TIME desc limit ?,? ";
        List<Object> pageparams = new ArrayList<Object>();
        pageparams.add(str);
        pageparams.add(startRow);
        pageparams.add(pager.getPageSize());
        List<ContentMetadataEntity> cmIdlist = contentDao.selectByCondition(pagesql, pageparams);
        StringBuffer cmsb = new StringBuffer();
        cmsb.append(",");
        for (ContentMetadataEntity contentMetadataEntity : cmIdlist) {
            cmsb.append(contentMetadataEntity.getContentMetadataId().toString());
            cmsb.append(",");
        }
        selectsql = "SELECT CONTENT_METADATA_ID,LANG,FIELD_NAME,FIELD_VALUE,MODIFY_TIME from CONTENT_FIELD_VALUES where CONTENT_METADATA_ID in (select v.CONTENT_METADATA_ID from CONTENT_FIELD_VALUES v "+
                " left join CONTENT_METADATA m on v.CONTENT_METADATA_ID =m.CONTENT_METADATA_ID "+
                " where m.IS_ENABLE=1 and v.IS_ENABLE =1  and m.DATATYPE_ID =? )"+
                " and '"+cmsb.toString()+"' like concat(concat('%,',CONTENT_METADATA_ID),',%') ";
        //查询并过滤
        List<Map<String, Object>> list = contentDao.getSelectContentMetadataList(selectsql, datatypeId);
        
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
        
        //按页面要求进行显示分组
        List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
        Set<Entry<String, List<Map<String, Object>>>> set = map.entrySet();
        for (Entry<String, List<Map<String, Object>>> entry : set) {
            String key = entry.getKey();
            List<Map<String, Object>> _list = entry.getValue();
            Map<String, Object> m = new LinkedHashMap<String, Object>();
            m.put("keyId", key);
            
            if(fieldlist != null){
                for (String displayFild : fieldlist) {
        
                    int _i = 0;
                    for (Map<String, Object> _map : _list) {
                        
                        String fieldName = _map.get("FIELD_NAME").toString();
                        
                        if(locallist!=null&&locallist.size()>0){
                            if(_map.get("LANG")==null||"".equals(_map.get("LANG").toString().trim())||locallist.get(0).getLocaleId().toString().trim().equals(_map.get("LANG").toString().trim())){
                                
                                if (fieldName.equals(displayFild)) {
                                    m.put(displayFild, _map.get("FIELD_VALUE"));
                                    if(("Province".equals(fieldName)||"City".equals(fieldName)||"Area".equals(fieldName))&&_map.get("FIELD_VALUE")!=null&&!"".equals(_map.get("FIELD_VALUE"))){
                                        AreaEntity area = new AreaEntity();
                                        area.setAreaMetadataId(Integer.parseInt(_map.get("FIELD_VALUE").toString()));
                                        area.setLang(1);
                                        area.setIsEnable(true);
                                        List<AreaEntity> arealist = areaDao.selectByCondition(area);
                                        if(arealist!=null&&arealist.size()>0){
                                            m.put(displayFild, arealist.get(0).getAreaName());
                                        }
                                    }
                                    _i = 1;
                                    break;
                                }
                            }
                        }
                    }
                    if (_i == 0) {
                        m.put(displayFild, "");
                    }
                }
            }
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (Map<String, Object> _map : _list) {
                Date modifytime = (Date)_map.get("MODIFY_TIME");
                if(modifytime!=null){
                    String mstr = df.format(modifytime);
                    m.put("MODIFY_TIME", mstr);
                    break;
//                    if(m.containsKey("MODIFY_TIME")){
//                        String gettime = m.get("MODIFY_TIME").toString();
//                        Date moDate = df.parse(gettime);
//                        if(modifytime.after(moDate)){
//                            m.put("MODIFY_TIME", mstr);
//                        }
//                    }else{
//                        m.put("MODIFY_TIME", mstr);
//                    }
                }
            }
            
            lists.add(m);
        }
        //按修改时间排序
        DateFormat fmat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int j = 0; j < lists.size(); j++) {
            for(int i = j; i < lists.size(); i++) {
                if(lists.get(j).get("MODIFY_TIME")!=null&&!"".equals(lists.get(j).get("MODIFY_TIME"))){
                    if(lists.get(i).get("MODIFY_TIME")!=null&&!"".equals(lists.get(i).get("MODIFY_TIME"))){
                        if(fmat.parse(lists.get(i).get("MODIFY_TIME").toString()).after(fmat.parse(lists.get(j).get("MODIFY_TIME").toString()))) {
                            Map<String, Object> temp = lists.get(j);
                            lists.set(j, lists.get(i));
                            lists.set(i, temp);
                        }
                    }
                }else{
                    Map<String, Object> temp = lists.get(j);
                    lists.set(j, lists.get(i));
                    lists.set(i, temp);
                }
                
            }
        }
        
        int totalCount = idlist.size();
        
        pageCount = totalCount/pager.getPageSize()+(totalCount%pager.getPageSize()==0?0:1);
        pager.setStartRow(startRow);
        pager.setDatas(lists);
        pager.setTotalRecord(totalCount);
        pager.setTotalPage(pageCount);
        return pager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DatatypeEntity getDatatypeById(Integer datatypeId) {
        return datatypeDao.selectById(datatypeId);
    }

    

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public int saveContent(ContentMetadataEntity contentMetadata, String selStr, List<Map<String, Object>> l) throws UnsupportedEncodingException {
        Integer contentMataId = 0;
        //标记主表是更新还是插入
        int mark = 0;
        if(contentMetadata.getContentMetadataId()==null){
            contentMataId = contentDao.insert(contentMetadata);
            sysLogDao.insertLogForInsert(contentMetadata.getContentMetadataId(), "CONTENT_METADATA");
            mark = 1;
        }else{
            Integer seqno = contentMetadata.getSeqNo();
            contentMetadata = contentDao.selectById(contentMetadata.getContentMetadataId());
            contentMetadata.setSeqNo(seqno);
            contentDao.update(contentMetadata);
            sysLogDao.insertLogForUpdate(contentMetadata.getContentMetadataId(), "CONTENT_METADATA");
            contentMataId = contentMetadata.getContentMetadataId();
        }
        //对有语言的进行插入
        if(l != null && l.size() > 0){
            for (Map<String, Object> temp : l) {
                String strtemp= (String)temp.get("lang");
                String[] langstrs = strtemp.split("&");
                String[] lang = langstrs[0].split("=");
                for (String langstr : langstrs) {
                    String[] keyval = langstr.split("=");
                    ContentFieldValues contentFieldValue = new ContentFieldValues();
                    if(keyval[0]!=null&&!"lang".equals(keyval[0])){
                        contentFieldValue.setFieldName(keyval[0]);
                        contentFieldValue.setContentMetadataId(contentMataId);
                        contentFieldValue.setLang(Integer.parseInt(lang[1]));
                        int fieldmark = 0;
                        //mark为0代表主表是更新
                        if(mark == 0){
                            List<Object> params = new ArrayList<Object>();
                            params.add(contentFieldValue.getFieldName());
                            params.add(contentFieldValue.getContentMetadataId());
                            params.add(lang[1]);
                            List<ContentFieldValues> list = contentFieldValuesDao.selectByCondition(ContentSQL.SELECT_CONTENTFIELDVALUES_BY_NAME_LANG,params);
                            if(list!=null&&list.size()>0){
                                fieldmark = 1;
                                contentFieldValue = list.get(0);
                            }
                        }
                        if(keyval!=null&&keyval.length>1){
                            contentFieldValue.setFieldValue(URLDecoder.decode(keyval[1],"utf-8"));
                        }else{
                            contentFieldValue.setFieldValue("");
                        }
                        //根据标记判断是更新还是插入,0为插入
                        if(fieldmark == 0){
                            contentFieldValuesDao.insert(contentFieldValue);
                            sysLogDao.insertLogForInsert(contentFieldValue.getContentFieldValuesId(), "CONTENT_FIELD_VALUES");
                        }else{
                            contentFieldValuesDao.update(contentFieldValue);
                            sysLogDao.insertLogForUpdate(contentFieldValue.getContentFieldValuesId(), "CONTENT_FIELD_VALUES");
                        }
                        
                    }
                }
            }
        }
        
        //无语言的添加
        if(selStr!=null&&!"".equals(selStr)){
            String[] selstrs = selStr.split("&");
            
            for (String str : selstrs) {
                String[] keyval = str.split("=");
                ContentFieldValues contentFieldValue = new ContentFieldValues();
                contentFieldValue.setFieldName(keyval[0]);
                contentFieldValue.setContentMetadataId(contentMataId);
                contentFieldValue.setLang(1);
                int fieldmark = 0;
                //mark为0代表主表是更新
                if(mark == 0){
                    List<Object> params = new ArrayList<Object>();
                    params.add(contentFieldValue.getFieldName());
                    params.add(contentFieldValue.getContentMetadataId());
                    List<ContentFieldValues> list = contentFieldValuesDao.selectByCondition(ContentSQL.SELECT_CONTENTFIELDVALUES_BY_NAME,params);
                    if(list!=null&&list.size()>0){
                        fieldmark = 1;
                        contentFieldValue = list.get(0);
                    }
                }
                if(keyval!=null&&keyval.length>1){
                    contentFieldValue.setFieldValue(URLDecoder.decode(keyval[1],"utf-8"));
                }else{
                    contentFieldValue.setFieldValue("");
                }
                
                //根据标记判断是更新还是插入,0为插入
                if(fieldmark == 0){
                    contentFieldValuesDao.insert(contentFieldValue);
                    sysLogDao.insertLogForInsert(contentFieldValue.getContentFieldValuesId(), "CONTENT_FIELD_VALUES");
                }else{
                    contentFieldValuesDao.update(contentFieldValue);
                    sysLogDao.insertLogForUpdate(contentFieldValue.getContentFieldValuesId(), "CONTENT_FIELD_VALUES");
                }
            }
        }
        return 1;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public ContentMetadataEntity getContentMetadataById(Integer contentMetadataId) {
        return contentDao.selectById(contentMetadataId);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<ContentFieldValues> getContentFieldValuesByMetadataId(Integer contentMetadataId) {
        List<Object> params = new ArrayList<Object>();
        params.add(contentMetadataId);
        return contentFieldValuesDao.selectByCondition(ContentSQL.SELECT_CONTENTFIELDVALUES_BY_METADATA_ID,params);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void deleteContent(List<Map<String, Object>> list) {
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> temp = list.get(i);
            Integer id = (Integer) temp.get("id");
            
            //获取contentMetadata
            ContentMetadataEntity contentMetadata = contentDao.selectById(id);
            if(contentMetadata != null){
                //删除
                contentFieldValuesDao.deleteByMetadataId(id);
                contentDao.deleteByMetadataId(id);
                //插入日志
                sysLogDao.insertLogForDelete(contentMetadata.getContentMetadataId(), "CONTENT_METADATA");
            }
           
        }
        
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

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ContentFieldValues> checkContentValue(String fieldname, String fieldvalue,
            String lang, String metadataId, String datatypeId) {
        String selectsql = "SELECT * from CONTENT_FIELD_VALUES cfv "
                + " left join CONTENT_METADATA cm on cfv.CONTENT_METADATA_ID = cm.CONTENT_METADATA_ID "+
                " where cfv.IS_ENABLE=1 and cm.IS_ENABLE=1 and cfv.FIELD_NAME = ? and cfv.FIELD_VALUE = ? and cm.DATATYPE_ID = ? ";
        List<Object> params = new ArrayList<Object>();
        params.add(fieldname);
        params.add(fieldvalue);
        params.add(datatypeId);
        if(lang!=null&&!"-1".equals(lang)){
            selectsql += " and cfv.LANG = ?";
            params.add(lang);
        }
        //判断是新增还是编辑
        if(metadataId!=null&&!"".equals(metadataId)){
            selectsql += " and cfv.CONTENT_METADATA_ID != ?";
            params.add(metadataId);
        }
        
        return contentFieldValuesDao.selectByCondition(selectsql, params);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public ContentMetadataEntity getMaxContentMetadata() {
        String selectsql = "SELECT * from CONTENT_METADATA ORDER BY CONTENT_METADATA_ID desc limit 0,1";
        List<Object> params = new ArrayList<Object>();
        List<ContentMetadataEntity> list = contentDao.selectByCondition(selectsql,params);
        if(list!=null&&list.size()>0){
            return list.get(0);
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
    
    
}
