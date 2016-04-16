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
import com.kohler.dao.utils.CommonFileUrl;
import com.kohler.dao.utils.PropertyUtil;
import com.kohler.entity.ContentFieldValues;
import com.kohler.entity.MasterDataCodeConstant;
import com.kohler.service.FamousProjectService;
import com.kohler.solr.bean.FamousProjectSolrBean;

/**
 * Class Function Description
 *
 * @author sana
 * @Date 2014年12月26日
 */
@Service
public class FamousProjectServiceImpl implements FamousProjectService {
   
    @Autowired
    private ContentFieldValuesDao contentFieldValuesDao;
    
    @Autowired
    private MasterDataDao masterdataDao;
    
    @Autowired
    private CommonFileUrl commonFileUrl;
    
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
        server.deleteByQuery("id:FAMOUS_PROJECT*");// clean the solr
        server.commit();
        List<Object> params = new ArrayList<Object>();
        params.add(lang);
        String sql = "select * from CONTENT_FIELD_VALUES where IS_ENABLE = 1 and  CONTENT_METADATA_ID in "+
                " (select CONTENT_METADATA_ID from CONTENT_METADATA where IS_ENABLE = 1 and DATATYPE_ID = 97) and LANG = ?";
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
        
        Set<Entry<String, List<ContentFieldValues>>> set = map.entrySet();
        for (Entry<String, List<ContentFieldValues>> entry : set) {
            FamousProjectSolrBean project = new FamousProjectSolrBean();
            String key = entry.getKey();
            project.setId("FAMOUS_PROJECT"+key);
            project.setContentMetadataId(key);
            List<ContentFieldValues> _list = entry.getValue();
            for (ContentFieldValues contentFieldValuesEntity : _list) {
                if(contentFieldValuesEntity.getFieldName()!=null&&"Province".equals(contentFieldValuesEntity.getFieldName())){
                    project.setProvince(contentFieldValuesEntity.getFieldValue());
                }
                if(contentFieldValuesEntity.getFieldName()!=null&&"City".equals(contentFieldValuesEntity.getFieldName())){
                    project.setCity(contentFieldValuesEntity.getFieldValue());
                }
                if(contentFieldValuesEntity.getFieldName()!=null&&"Image Source".equals(contentFieldValuesEntity.getFieldName())){
                    if(String.valueOf(MasterDataCodeConstant.FILE_SOURCE_INTERNAL).equals(contentFieldValuesEntity.getFieldValue())){
                        project.setFamousProjectImageUrl(commonFileUrl.combineFileUrlPath(contentFieldValuesEntity.getFieldValue()));
                    }else{
                        for (ContentFieldValues cfv : _list) {
                            if(cfv.getFieldName()!=null&&"Image URL".equals(cfv.getFieldName())){
                                project.setFamousProjectImageUrl(cfv.getFieldValue());
                            }
                        }
                    }
                }
                if(contentFieldValuesEntity.getFieldName()!=null&&"Image Alt".equals(contentFieldValuesEntity.getFieldName())){
                    project.setFamousProjectImageAlt(contentFieldValuesEntity.getFieldValue());
                }
                if(contentFieldValuesEntity.getFieldName()!=null&&"Type".equals(contentFieldValuesEntity.getFieldName())){
                    project.setFamousProjectType(contentFieldValuesEntity.getFieldValue());
                }
                if(contentFieldValuesEntity.getFieldName()!=null&&"Name".equals(contentFieldValuesEntity.getFieldName())){
                    project.setFamousProjectName(contentFieldValuesEntity.getFieldValue());
                }
                if(contentFieldValuesEntity.getFieldName()!=null&&"Content".equals(contentFieldValuesEntity.getFieldName())){
                    project.setFamousProjectContent(contentFieldValuesEntity.getFieldValue());
                }
                
            }
            server.addBean(project);
            server.commit();
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void importDeltaIndex() throws Exception {
        // TODO Auto-generated method stub

    }

}
