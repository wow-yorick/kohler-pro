package com.kohler.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kohler.dao.PageDao;
import com.kohler.entity.ContentFieldValues;
import com.kohler.entity.PageEntity;

/**
 * 
 * PageDao Impl
 *
 * @author sana
 * @Date 2014年12月4日
 */
@Repository
public class PageDaoImpl extends BaseDaoImpl<PageEntity> implements PageDao{
	
	//private String sql_1 = "select * from content_metadata where datatype_id = ? and is_enable = 1";
	
	private String sql_2 = "select * from CONTENT_FIELD_VALUES v LEFT JOIN CONTENT_METADATA m on m.CONTENT_METADATA_ID = v.CONTENT_METADATA_ID where m.DATATYPE_ID = ? and m.IS_ENABLE = 1 ";
	
	private String sql_3 = "insert into CONTENT_FIELD_VALUES(CONTENT_METADATA_ID,FIELD_NAME,COMPLICATE,FIELD_VALUE,LANG) values(?,?,?,?,?)";

	private String sql_4 = "insert into CONTENT_METADATA(content_metadata_id,DATATYPE_ID,IS_ENABLE) values(?,?,1)";
	
	private String sql_5 = "select max(content_metadata_id) from CONTENT_METADATA";
	
	private String sql_7 = "select * from CONTENT_FIELD_VALUES v where v.CONTENT_METADATA_ID = ? and FIELD_NAME = ?";
	
	private String sql_8 = " update CONTENT_METADATA set IS_ENABLE = 0 where CONTENT_METADATA_ID = ? ";
	
	private String sql_9 = "select * from CONTENT_FIELD_VALUES v where v.CONTENT_METADATA_ID = ? and FIELD_NAME = ? and LANG = ?";
	
	private String sql_10 = "select * from CONTENT_FIELD_VALUES v where v.CONTENT_METADATA_ID = ? and FIELD_NAME = ? and LANG is null";
	
	private String sql_11 = "select * from FILE_ASSET where IS_ENABLE = 1 and ? like concat(concat('%,',FILE_ASSET_Id),',%') ";
	
	private String sql_12 = "SELECT * from PAGE_FIELD_VALUES where IS_ENABLE = 1 and PAGE_ID = ? and FIELD_NAME = ?";
	
	private String sql_13 = "select cfv.* from CONTENT_FIELD_VALUES cfv "
	        + "left join MASTERDATA m on cfv.FIELD_NAME = m.MASTERDATA_NAME "
	        + "where cfv.IS_ENABLE = 1 and m.IS_ENABLE = 1 and cfv.LANG = 1 and ? like concat(concat('%,',cfv.CONTENT_METADATA_ID),',%') and m.MASTERDATA_METADATA_ID = ? and m.LANG = 1";
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Map<String, Object>> getContentMetadataList(Integer dataTypeId ,Map<String,String> selMap) {
		List<Map<String, Object>> list =  jdbcTemplate.queryForList(sql_2,new Object[]{dataTypeId});
		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, List<ContentFieldValues>> getFieldValuesById(Integer id) {
		List<Map<String, Object>> list =  jdbcTemplate.queryForList(sql_2,new Object[]{id});
		
		if(list != null && list.size() > 0){
//			List<ContentFieldValues> contentList = new ArrayList<ContentFieldValues>();
			Map<String, List<ContentFieldValues>> _map = new HashMap<String,List<ContentFieldValues>>();
			for(Map<String,Object> map : list){
				if(_map.containsKey(map.get("CONTENT_METADATA_ID").toString())){
					List<ContentFieldValues> _list = (List<ContentFieldValues>)_map.get(map.get("CONTENT_METADATA_ID").toString());
					_list.add(covertObje(map));
					_map.put(map.get("CONTENT_METADATA_ID").toString(), _list);
				}else{
					List<ContentFieldValues> _list = new ArrayList<ContentFieldValues>();
					_list.add(covertObje(map));
					_map.put(map.get("CONTENT_METADATA_ID").toString(), _list);
				}
			}
			return _map;
		}
		return null;
	}
	
	/**
	 * 
	 * @param map
	 * @return
	 * @author sana
	 * Date 2014年12月4日
	 * @version
	 */
	public ContentFieldValues covertObje(Map<String,Object> map){
	    if(map != null && map.size() <= 0){
	        return null;
	    }
		ContentFieldValues content = new ContentFieldValues();
		content.setContentFieldValuesId(Integer.parseInt(map.get("CONTENT_FIELD_VALUES_ID").toString()));
		//content.setLang(Integer.parseInt(map.get("LANG").toString()));
		content.setContentMetadataId(Integer.parseInt(map.get("CONTENT_METADATA_ID").toString()));
		content.setFieldName(map.get("FIELD_NAME").toString());
		content.setFieldValue(map.get("FIELD_VALUE").toString());
		if(map.get("COMPLICATE") != null){
		    content.setComplicate(Integer.parseInt(map.get("COMPLICATE").toString()));
		}
		//content.setLanguage(map.get("LANGUAGE").toString());
		return content;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void insertContent(ContentFieldValues content) {
		Object[] obj = new Object[]{content.getContentMetadataId(),content.getFieldName(),content.getComplicate(),content.getFieldValue(),null};
		jdbcTemplate.update(sql_3,obj);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer insertContentMetadata(Integer dataTypeId) {
		int i = jdbcTemplate.queryForInt(sql_5);
		Object[] obj = new Object[]{i+1,dataTypeId};
		jdbcTemplate.update(sql_4,obj);
		return i+1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getFieldValue(Integer contentId, String fieldName) {
		List<Map<String, Object>> _list =  jdbcTemplate.queryForList(sql_7,new Object[]{contentId,fieldName});
		if(_list  != null & _list.size() > 0){
			Map<String, Object> map = _list.get(0);
			return map.get("FIELD_VALUE").toString();
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteContentById(Integer contentId) {
		jdbcTemplate.update(sql_8,new Object[]{contentId});
	}

	/**
	 * {@inheritDoc}
	 */
    @Override
    public String getLangFieldValue(Integer contentId, Integer lang, String fieldName) {
        if(lang==-1){
            List<Map<String, Object>> _list =  jdbcTemplate.queryForList(sql_10,new Object[]{contentId,fieldName});
            if(_list  != null & _list.size() > 0){
                Map<String, Object> map = _list.get(0);
                return map.get("FIELD_VALUE").toString();
            }
        }else{
            List<Map<String, Object>> _list =  jdbcTemplate.queryForList(sql_9,new Object[]{contentId,fieldName,lang});
            if(_list  != null & _list.size() > 0){
                Map<String, Object> map = _list.get(0);
                return map.get("FIELD_VALUE").toString();
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Map<String, Object>> getFileAssetName(String fieldAssetIds) {
        return  jdbcTemplate.queryForList(sql_11,new Object[]{fieldAssetIds});
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPageFieldValue(int pageId, String fieldName) {
        List<Map<String, Object>> _list =  jdbcTemplate.queryForList(sql_12,new Object[]{pageId,fieldName});
        if(_list  != null & _list.size() > 0){
            Map<String, Object> map = _list.get(0);
            return map.get("FIELD_VALUE").toString();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Map<String, Object>> getContentFieldValues(String pageIds, Integer mastercIntegerode) {
        // TODO Auto-generated method stub
        return jdbcTemplate.queryForList(sql_13,new Object[]{pageIds,mastercIntegerode});
    }

}