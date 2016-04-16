/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohler.dao.ContentFieldValuesDao;
import com.kohler.dao.MasterDataDao;
import com.kohler.dao.utils.PropertyUtil;
import com.kohler.entity.ContentFieldValues;
import com.kohler.entity.MasterDataCodeConstant;
import com.kohler.entity.MasterdataEntity;
import com.kohler.service.StoreLocatorService;
import com.kohler.solr.bean.StoreLocatorEntity;

/**
 * Class Function Description
 *
 * @author sana
 * @Date 2014年12月25日
 */
@Service
public class StoreLocatorServiceImpl implements StoreLocatorService{

    @Autowired
    private ContentFieldValuesDao contentFieldValuesDao;
    
    @Autowired
    private MasterDataDao masterdataDao;
    
    private HttpSolrServer  server = new HttpSolrServer(PropertyUtil
            .getPropertyByKey("solr.server.solr")+"mycontent");
    /**
     * {@inheritDoc}
     */
    @Override
    public void importAllIndex(Integer lang) throws Exception {
        if(lang == 2){
            server = new HttpSolrServer(PropertyUtil.getPropertyByKey("solr.server.solr")+"encontent");
        }
        server.deleteByQuery("id:STORE_LOCATOR*");// clean the solr
        server.commit();
        List<Object> params = new ArrayList<Object>();
        params.add(lang);
        String sql = "select * from CONTENT_FIELD_VALUES where IS_ENABLE = 1 and  CONTENT_METADATA_ID in "+
                " (select CONTENT_METADATA_ID from CONTENT_METADATA where IS_ENABLE = 1 and DATATYPE_ID = 96) and LANG = ?";
        List<ContentFieldValues>  list = contentFieldValuesDao.selectByCondition(sql, params);
        Map<String, List<ContentFieldValues>> map = new HashMap<String, List<ContentFieldValues>>();
        for (ContentFieldValues contentFieldValuesEntity : list) {
            String obj = contentFieldValuesEntity.getContentMetadataId().toString();
            if (map.containsKey(obj)) {
                map.get(obj).add(contentFieldValuesEntity);
            } else {
                List<ContentFieldValues> _list = new ArrayList<ContentFieldValues>();
                _list.add(contentFieldValuesEntity);
                map.put(obj, _list);
            }
        }
        
        List<Object> masterparams = new ArrayList<Object>();
        masterparams.add(1);
        masterparams.add(MasterDataCodeConstant.TYPE_STORE_LOCATOR);
        String mastersql = "select * from MASTERDATA m left join MASTERDATA_METADATA mm on "
                + " m.MASTERDATA_METADATA_ID = mm.MASTERDATA_METADATA_ID where m.IS_ENABLE = 1"
                + " and mm.IS_ENABLE = 1 and m.LANG = ? and mm.MASTERDATA_TYPE_ID = ?";
        List<MasterdataEntity> maslist = masterdataDao.selectByCondition(mastersql,masterparams);
        
        
        Set<Entry<String, List<ContentFieldValues>>> set = map.entrySet();
        for (Entry<String, List<ContentFieldValues>> entry : set) {
            StoreLocatorEntity store = new StoreLocatorEntity();
            String key = entry.getKey();
            store.setId("STORE_LOCATOR"+key);
            store.setContentMetadataId(key);
            List<ContentFieldValues> _list = entry.getValue();
            for (ContentFieldValues contentFieldValuesEntity : _list) {
                if(contentFieldValuesEntity.getFieldName()!=null&&"Province".equals(contentFieldValuesEntity.getFieldName())){
                    store.setProvince(contentFieldValuesEntity.getFieldValue());
                }
                if(contentFieldValuesEntity.getFieldName()!=null&&"City".equals(contentFieldValuesEntity.getFieldName())){
                    store.setCity(contentFieldValuesEntity.getFieldValue());
                }
                if(contentFieldValuesEntity.getFieldName()!=null&&"Area".equals(contentFieldValuesEntity.getFieldName())){
                    store.setArea(contentFieldValuesEntity.getFieldValue());
                }
                if(contentFieldValuesEntity.getFieldName()!=null&&"Type".equals(contentFieldValuesEntity.getFieldName())){
                    store.setStoreType(contentFieldValuesEntity.getFieldValue());
                    for (MasterdataEntity masterdata : maslist) {
                        if(masterdata.getMasterdataMetadataId().toString().equals(contentFieldValuesEntity.getFieldValue())){
                            store.setStoreTypeName(masterdata.getMasterdataName());
                        }
                    }
                }
                if(contentFieldValuesEntity.getFieldName()!=null&&"Product Type".equals(contentFieldValuesEntity.getFieldName())){
                    store.setProductType(contentFieldValuesEntity.getFieldValue());
                }
                if(contentFieldValuesEntity.getFieldName()!=null&&"Name".equals(contentFieldValuesEntity.getFieldName())){
                    store.setStoreName(contentFieldValuesEntity.getFieldValue());
                }
                if(contentFieldValuesEntity.getFieldName()!=null&&"Address".equals(contentFieldValuesEntity.getFieldName())){
                    store.setStoreAddress(contentFieldValuesEntity.getFieldValue());
                }
                
            }
            server.addBean(store);
            server.commit();
        }
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void importDeltaIndex() throws Exception {
        List<Object> params = new ArrayList<Object>();
        params.add(1);
        String sql = "select * from CONTENT_FIELD_VALUES where IS_ENABLE = 1 and  CONTENT_METADATA_ID in "+
                " (select CONTENT_METADATA_ID from CONTENT_METADATA where IS_ENABLE = 1 and DATATYPE_ID = 96) and LANG = ?";
        List<ContentFieldValues>  list = contentFieldValuesDao.selectByCondition(sql, params);
        Map<String, List<ContentFieldValues>> map = new HashMap<String, List<ContentFieldValues>>();
        for (ContentFieldValues contentFieldValuesEntity : list) {
            String obj = contentFieldValuesEntity.getContentMetadataId().toString();
            if (map.containsKey(obj)) {
                map.get(obj).add(contentFieldValuesEntity);
            } else {
                List<ContentFieldValues> _list = new ArrayList<ContentFieldValues>();
                _list.add(contentFieldValuesEntity);
                map.put(obj, _list);
            }
        }
        
        List<Object> masterparams = new ArrayList<Object>();
        masterparams.add(1);
        masterparams.add(MasterDataCodeConstant.TYPE_STORE_LOCATOR);
        String mastersql = "select * from MASTERDATA m left join MASTERDATA_METADATA mm on "
                + " m.MASTERDATA_METADATA_ID = mm.MASTERDATA_METADATA_ID where m.IS_ENABLE = 1"
                + " and mm.IS_ENABLE = 1 and m.LANG = ? and mm.MASTERDATA_TYPE_ID = ?";
        List<MasterdataEntity> maslist = masterdataDao.selectByCondition(mastersql,masterparams);
        
        
        Set<Entry<String, List<ContentFieldValues>>> set = map.entrySet();
        for (Entry<String, List<ContentFieldValues>> entry : set) {
            StoreLocatorEntity store = new StoreLocatorEntity();
            String key = entry.getKey();
            store.setId(key);
            List<ContentFieldValues> _list = entry.getValue();
            for (ContentFieldValues contentFieldValuesEntity : _list) {
                if(contentFieldValuesEntity.getFieldName()!=null&&"Province".equals(contentFieldValuesEntity.getFieldName())){
                    store.setProvince(contentFieldValuesEntity.getFieldValue());
                }
                if(contentFieldValuesEntity.getFieldName()!=null&&"City".equals(contentFieldValuesEntity.getFieldName())){
                    store.setCity(contentFieldValuesEntity.getFieldValue());
                }
                if(contentFieldValuesEntity.getFieldName()!=null&&"Area".equals(contentFieldValuesEntity.getFieldName())){
                    store.setArea(contentFieldValuesEntity.getFieldValue());
                }
                if(contentFieldValuesEntity.getFieldName()!=null&&"Type".equals(contentFieldValuesEntity.getFieldName())){
                    store.setStoreType(contentFieldValuesEntity.getFieldValue());
                    for (MasterdataEntity masterdata : maslist) {
                        if(masterdata.getMasterdataMetadataId().toString().equals(contentFieldValuesEntity.getFieldValue())){
                            store.setStoreTypeName(masterdata.getMasterdataName());
                        }
                    }
                }
                if(contentFieldValuesEntity.getFieldName()!=null&&"Product Type".equals(contentFieldValuesEntity.getFieldName())){
                    store.setProductType(contentFieldValuesEntity.getFieldValue());
                }
                if(contentFieldValuesEntity.getFieldName()!=null&&"Name".equals(contentFieldValuesEntity.getFieldName())){
                    store.setStoreName(contentFieldValuesEntity.getFieldValue());
                }
                if(contentFieldValuesEntity.getFieldName()!=null&&"Address".equals(contentFieldValuesEntity.getFieldName())){
                    store.setStoreAddress(contentFieldValuesEntity.getFieldValue());
                }
                
            }
            server.addBean(store);
            server.commit();
        }
        
    }

}
