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

import net.sf.json.JSONArray;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kohler.constants.CommonConstants;
import com.kohler.constants.NewProductSQL;
import com.kohler.constants.SuiteSQL;
import com.kohler.dao.PageDao;
import com.kohler.dao.SuiteDao;
import com.kohler.dao.SuiteHotSpotDao;
import com.kohler.dao.SuiteMetadataDao;
import com.kohler.dao.SuiteProductDao;
import com.kohler.dao.SysLogDao;
import com.kohler.dao.utils.Pager;
import com.kohler.entity.MasterDataCodeConstant;
import com.kohler.entity.SuiteEntity;
import com.kohler.entity.SuiteHotSpotEntity;
import com.kohler.entity.SuiteMetadataEntity;
import com.kohler.entity.SuiteProductEntity;
import com.kohler.entity.extend.SuiteHotSpotPojo;
import com.kohler.service.SuiteService;
import com.kohler.util.JSonUtil;

/**
 * 
 * 套间业务逻辑服务
 *
 * @author Administrator
 * @Date 2014年10月17日
 */
@Service
public class SuiteServiceImpl implements SuiteService {

    @Autowired
    private SuiteDao         suiteDao;

    @Autowired
    private SuiteMetadataDao suiteMetadataDao;

    @Autowired
    private SuiteProductDao suiteProductDao;

    @Autowired
    private SysLogDao        sysLogDao;

    @Autowired
    private SuiteHotSpotDao  suiteHotSpotDao;
    
    @Autowired
    private PageDao pageDao;
    

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Pager<Map<String, Object>> getSuitePage(Pager<Map<String, Object>> pager,
            String suiteName) {
        List<Object> params = new ArrayList<Object>();
        if (suiteName != null) {
            suiteName = '%' + suiteName + '%';
        } else {
            suiteName = "%%";
        }
        params.add(suiteName);

        int pageCount = 0;
        int startRow = (pager.getCurrentPage() - 1) * pager.getPageSize();

        int totalCount = suiteDao.selectCountByCondition(SuiteSQL.SELECT_SUITE_COUNT, params);

        if (totalCount % pager.getPageSize() == 0) {
            pageCount = totalCount / pager.getPageSize();
        } else {
            pageCount = totalCount / pager.getPageSize() + 1;
        }
        params.add(startRow);
        params.add(pager.getPageSize());
        List<Map<String, Object>> list = suiteDao.selectByConditionWithMap(
                SuiteSQL.SELECT_SUITE_BY_NAME, params);

        pager.setStartRow(startRow);
        pager.setDatas(list);
        pager.setTotalRecord(totalCount);
        pager.setTotalPage(pageCount);
        return pager;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Integer addSuite(String jsonEmity, SuiteMetadataEntity suiteMetadataEntity,
            String jsonSuiteProduct,  SuiteHotSpotPojo suHotSpotPojo) {
        // metadata表插入
        Integer suiteMetadataId = suiteMetadataDao.insert(suiteMetadataEntity);
        // 数据表插入
        JSONArray entityArr = JSONArray.fromObject(jsonEmity);
        if (entityArr.size() > 0) {
            for (int i = 0; i < entityArr.size(); i++) {
                //suite lang table insert data
                SuiteEntity suite = JSonUtil.toObject(entityArr.getString(i).toString(),
                        SuiteEntity.class);
                suite.setSuiteMetadataId(suiteMetadataId);
                suiteDao.insert(suite);
            }
        }
        

        // 插入suiteProduct
        JSONArray suiteProductArr = JSONArray.fromObject(jsonSuiteProduct);
        if (suiteProductArr.size() > 0) {
            for (int i = 0; i < suiteProductArr.size(); i++) {
                SuiteProductEntity suiteProductEntity = JSonUtil.toObject(suiteProductArr
                        .getString(i).toString(), SuiteProductEntity.class);
                suiteProductEntity.setSuiteMetadataId(suiteMetadataId);
                suiteProductDao.insert(suiteProductEntity);
            }
        }
        
        //suite hot spot insert data
        suHotSpotPojo.setSuiteMetadataId(suiteMetadataId);
        SuiteHotSpotEntity suiteHotSpotEntity = new SuiteHotSpotEntity();
        BeanUtils.copyProperties(suHotSpotPojo,suiteHotSpotEntity);
        suiteHotSpotDao.insert(suiteHotSpotEntity);

        // 插入日志
        sysLogDao.insertLogForInsert(suiteMetadataId, "SUITE_METADATA");
        return suiteMetadataId;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Integer editSuite(String jsonEmity, SuiteMetadataEntity suiteMetadataEntity,
            String jsonSuiteProduct, SuiteHotSpotPojo suHotSpotPojo) {

        // 主表更新
        suiteMetadataEntity.setIsEnable(true);
        suiteMetadataDao.update(suiteMetadataEntity);

        // 数据表更新
        JSONArray entityArr = JSONArray.fromObject(jsonEmity);
        if (entityArr.size() > 0) {
            for (int i = 0; i < entityArr.size(); i++) {
                SuiteEntity suite = JSonUtil.toObject(entityArr.getString(i).toString(),
                        SuiteEntity.class);
                suite.setIsEnable(true);
                suiteDao.update(suite);
            }
        }

        Integer suiteMetadataId = suiteMetadataEntity.getSuiteMetadataId();
        // suiteProduct表更新
        //delete suite product first
        suiteProductDao.deleteBySuiteMetadataId(suiteMetadataId);
        //then insert
        JSONArray suiteProductArr = JSONArray.fromObject(jsonSuiteProduct);
        if (suiteProductArr.size() > 0) {
            for (int i = 0; i < suiteProductArr.size(); i++) {
                SuiteProductEntity suiteProductEntity = JSonUtil.toObject(suiteProductArr
                        .getString(i).toString(), SuiteProductEntity.class);
                suiteProductEntity.setSuiteMetadataId(suiteMetadataId);
                suiteProductEntity.setSuiteProductId(null);
                suiteProductDao.insert(suiteProductEntity);
            }
        }
        
        //suite hot spot insert data
        //delete first
        suiteHotSpotDao.deleteBySuiteMetadataId(suiteMetadataId);
        
        //then insert
        SuiteHotSpotEntity suiteHotSpotEntity = new SuiteHotSpotEntity();
        BeanUtils.copyProperties(suHotSpotPojo,suiteHotSpotEntity);
        suiteHotSpotEntity.setSuiteMetadataId(suiteMetadataId);
        suiteHotSpotDao.insert(suiteHotSpotEntity);

        // 插入日志
        sysLogDao.insertLogForUpdate(suiteMetadataEntity.getSuiteMetadataId(), "SUITE_METADATA");
        return suiteMetadataEntity.getSuiteMetadataId();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public List<SuiteEntity> getSuiteByMetadataId(Integer suiteMetadataId) {
        SuiteEntity suiteEntity = new SuiteEntity();
        suiteEntity.setIsEnable(true);
        suiteEntity.setSuiteMetadataId(suiteMetadataId);
        return suiteDao.selectByCondition(suiteEntity);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public SuiteMetadataEntity getSuiteMetadataByMetadataId(Integer suiteMetadataId) {
        return suiteMetadataDao.selectById(suiteMetadataId);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteSuite(String[] suiteMetadataId) {

        for (String metadataId : suiteMetadataId) {
            Integer suiteMetadataIdT = Integer.valueOf(metadataId).intValue();
            // 删除metadata表中的记录
            SuiteMetadataEntity suiteMetadata = suiteMetadataDao.selectById(suiteMetadataIdT);
            suiteMetadataDao.delete(suiteMetadata);

            // 删除数据表中的数据
            suiteDao.deleteByMetaDataId(suiteMetadataIdT);

            // 删除产品表
            suiteProductDao.deleteBySuiteMetadataId(suiteMetadataIdT);
            
            //delete the suite hot spot data
            suiteHotSpotDao.deleteBySuiteMetadataId(suiteMetadataIdT);

            // 插入日志
            sysLogDao.insertLogForDelete(suiteMetadataIdT, "SUITE_METADATA");
        }

    }

    /**
     * 
     * {@inheritDoc}
     */
    public Map<String, Object> getGlobleMasterData() {
        HashMap<String, Object> hsMap = new HashMap<String, Object>();
        List<Map<String, Object>> locate = getLocateList();
        List<Map<String, Object>> tempList_pc = getTempleteList(CommonConstants.TEMPLATE_TYPE_SUITE); // suite template-PC
        List<Map<String, Object>> tempList_mobile = getTempleteList(CommonConstants.TEMPLATE_TYPE_SUITE); // suite template-MOBILE
        
        List<Object> searchKey = new ArrayList<Object>();
        searchKey.add(1);//category level eq 1
        List<Map<String,Object>> category =suiteMetadataDao.selectByConditionWithMap(NewProductSQL.SELECT_NEWCATEGORYMETADATA_List, searchKey);
        hsMap.put("Catalog", category);
        
        hsMap.put("tempList_pc", tempList_pc);
        hsMap.put("locate", locate);
        hsMap.put("tempList_pc", tempList_pc);
        hsMap.put("tempList_mobile", tempList_mobile);
        return hsMap;
    }

    /**
     * 获取语言列表
     * @return
     * @author Administrator Date 2014年10月23日
     * @version
     */
    private List<Map<String, Object>> getLocateList() {
        return suiteMetadataDao.selectByConditionWithMap(SuiteSQL.SELECT_LOCALE_LIST,
                new ArrayList<Object>());
    }

    /**
     * 获取模板列表
     * @param tempType
     * @return
     * @author Administrator Date 2014年10月23日
     * @version
     */
    private List<Map<String, Object>> getTempleteList(Integer tempType) {
        List<Object> searchKey = new ArrayList<Object>();
        searchKey.add(tempType);
        return suiteMetadataDao.selectByConditionWithMap(SuiteSQL.SELECT_TEMPLATE_LIST, searchKey);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SuiteEntity> selectSuiteBySuiteEntity(SuiteEntity suiteEntity) {
        String sql = "SELECT SUITE_NAME FROM SUITE WHERE 1=1";
        if (suiteEntity.getLang() != null && suiteEntity.getSuiteName() != null) {
            sql += " AND SUITE_NAME = '" + suiteEntity.getSuiteName() + "' AND LANG= "
                    + suiteEntity.getLang();
        }
        if (suiteEntity.getSuiteId() != null) {
            sql += " AND SUITE_ID != " + suiteEntity.getSuiteId();
        }
        List<SuiteEntity> suiteList = suiteDao.selectByCondition(sql, new ArrayList<Object>());
        return suiteList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Map<String,Object>> getSuiteHotSpot(Integer suiteMetadataId) {
        List<Object> params = new ArrayList<Object>();
        params.add(suiteMetadataId);
        List<Map<String,Object>> suiteHotSpotList = suiteHotSpotDao.selectByConditionWithMap(SuiteSQL.SELECT_SUITE_HOT_SPOT_DETAIL, params);
        for(int i=0; i < suiteHotSpotList.size(); i++) {
            String suiteHotSpotId = String.valueOf(suiteHotSpotList.get(i).get("HOT_SPOT_ID"));
            String spotTitle =  getHotSpotContentTitle(suiteHotSpotId);
            suiteHotSpotList.get(i).put("HOT_SPOT_NAME", spotTitle);
        }
        return suiteHotSpotList;
    }
    
    private String getHotSpotContentTitle(String pageIds) {
        String str = "";
        List<Map<String, Object>> contentvaluelist = pageDao.getContentFieldValues(","+pageIds+",",MasterDataCodeConstant.CONTENT_DISPLAY_ATTR_NAME_HOT_SPOT);
        if(contentvaluelist != null && contentvaluelist.size()>0){
            StringBuffer sb = new StringBuffer();
            for (Map<String, Object> map : contentvaluelist) {
                sb.append(map.get("FIELD_VALUE"));
                sb.append(",");
            }
            str = sb.toString();
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

}
