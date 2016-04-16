/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohler.constants.CommonConstants;
import com.kohler.constants.NewProductSQL;
import com.kohler.dao.MasterDataDao;
import com.kohler.dao.NewProductDao;
import com.kohler.dao.NewProductMetadataDao;
import com.kohler.dao.SysLogDao;
import com.kohler.dao.NewProductHeroAreaDao;
import com.kohler.dao.utils.Pager;
import com.kohler.entity.MasterDataCodeConstant;
import com.kohler.entity.MasterdataEntity;
import com.kohler.entity.NewArrivalBannerUnitEntity;
import com.kohler.entity.NewArrivalEntity;
import com.kohler.entity.NewArrivalHeroAreaEntity;
import com.kohler.entity.NewArrivalMetadataEntity;
import com.kohler.dao.NewProductBannerUnitDao;
import com.kohler.service.NewProductService;
import com.kohler.util.JSonUtil;
@Service
public class NewProductServiceImpl implements NewProductService {

    @Autowired 
    private NewProductDao NewProductDao;
    @Autowired
    public SysLogDao sysLogDao;
    @Autowired
    public NewProductMetadataDao NewProductMetadataDao;
    @Autowired
    public NewProductBannerUnitDao NewProductBannerUnitDao;
    @Autowired
    public NewProductHeroAreaDao NewProductHeroAreaDao;
    @Autowired
    private MasterDataDao masterdataDao;
    
    
    @Override
    public Pager<Map<String, Object>> getNewarrivalsListPage(Pager<Map<String, Object>> pager,
            String Name) {
        // TODO Auto-generated method stub
        List<Object> queryParams = new ArrayList<Object>();
        StringBuilder querySql = new StringBuilder(NewProductSQL.SELECT_NEW_ARRIVAL_BY_NAME); 
        StringBuilder countSql = new StringBuilder(NewProductSQL.SELECT_NEW_ARRIVAL_COUNT);
        if(Name !=null && !"".equals(Name) ){
                querySql.append("  and NEW_ARRIVAL_NAME like concat(concat('%',?),'%') ");
                countSql.append("  and NEW_ARRIVAL_NAME like concat(concat('%',?),'%') ");
                queryParams.add(Name);
        }
        querySql.append(" limit ?,?");
        int totalCount = NewProductDao.selectCountByCondition(countSql.toString(), queryParams);
        int pageCount = 0;
        if (totalCount % pager.getPageSize() == 0) {
            pageCount = totalCount / pager.getPageSize();
        } else {
            pageCount = totalCount / pager.getPageSize() + 1;
        }
        int index = (pager.getCurrentPage() - 1) * pager.getPageSize();
        queryParams.add(index);
        queryParams.add(pager.getPageSize());
        List<Map<String,Object>> list = NewProductDao.selectByConditionWithMap(querySql.toString(), queryParams);
        pager.setStartRow(index);
        pager.setDatas(list);
        pager.setTotalRecord(totalCount);
        pager.setTotalPage(pageCount);
        return pager;
    }

    @Override
    public Map<String,Object> getselectList() {
        // TODO Auto-generated method stub
        HashMap<String, Object> hsMap = new HashMap<String, Object>();
        List<Map<String, Object>> tempList_pc = getTempleteList(CommonConstants.TEMPLATE_TYPE_NEW_PRODUCT); // pc模板
        //List<Map<String, Object>> tempList_mobile = getTempleteList(CommonConstants.TEMPLATE_TYPE_NEW_PRODUCT); // mobile模板
        List<Object> searchKey = new ArrayList<Object>();
        searchKey.add(1);
        List<Map<String,Object>> list =NewProductDao.selectByConditionWithMap(NewProductSQL.SELECT_NEWCATEGORYMETADATA_List, searchKey);
        hsMap.put("tempList_pc", tempList_pc);
        //hsMap.put("tempList_mobile", tempList_mobile);
        hsMap.put("Catalog", list);
        return hsMap;
        
    }
    
    /**
     * 获取模板列表
     * @param tempType
     * @return
     * @author Administrator
     * Date 2014年10月23日
     * @version
     */
    private List<Map<String, Object>> getTempleteList(Integer tempType) {
        List<Object> searchKey = new ArrayList<Object>();
        searchKey.add(tempType);
        return NewProductMetadataDao.selectByConditionWithMap(NewProductSQL.SELECT_TEMPLATE_LIST, searchKey);
    }
    @Override
    public Integer addnewproduct(NewArrivalMetadataEntity NewArrivalMetadataEntity, String products,String bannerUnitMetadataId,String heroAreaMetadataId) {
        // TODO Auto-generated method stub
        int Id=0;
        Integer NewProductMetadataId = NewProductMetadataDao.insert(NewArrivalMetadataEntity);
        //插入日志
        sysLogDao.insertLogForInsert(NewProductMetadataId, "NewArrivalMetadata");
        // 数据表插入
        JSONArray entityArr = JSONArray.fromObject(products);
        if (entityArr.size() > 0) {
            for (int i = 0,size=entityArr.size(); i < size; i++) {
                NewArrivalEntity NewArrivalEntity = JSonUtil.toObject(entityArr.getString(i).toString(),
                        NewArrivalEntity.class);
                NewArrivalEntity.setNewArrivalMetadataId(NewProductMetadataId);
                Id=NewProductDao.insert(NewArrivalEntity);
                //插入日志
                sysLogDao.insertLogForInsert(Id, "NewArrival");
            }
        }
        String[] s={};
        if(bannerUnitMetadataId!=null && !bannerUnitMetadataId.equals("")){
            s=bannerUnitMetadataId.split(",");
            NewArrivalBannerUnitEntity bu=new NewArrivalBannerUnitEntity();
            bu.setNewArrivalMetadataId(NewProductMetadataId);
            for(int i=0;i<s.length;i++){
                bu.setBannerUnitMetadataId(Integer.parseInt(s[i].toString()));
                Id=NewProductBannerUnitDao.insert(bu);
                //插入日志
                sysLogDao.insertLogForInsert(Id, "NewArrivalBannerUnit");
            }
        }
        if(heroAreaMetadataId!=null && !heroAreaMetadataId.equals("")){
            s=heroAreaMetadataId.split(",");
            NewArrivalHeroAreaEntity ha=new NewArrivalHeroAreaEntity();
            ha.setNewArrivalMetadataId(NewProductMetadataId);
            for(int i=0;i<s.length;i++){
                ha.setHeroAreaMetadataId(Integer.parseInt(s[i].toString()));
                Id=NewProductHeroAreaDao.insert(ha);
                //插入日志
                sysLogDao.insertLogForInsert(Id, "NewArrivalHeroArea");
            }
        }
        return NewProductMetadataId;
    }
    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Integer editnewproduct(NewArrivalMetadataEntity NewArrivalMetadataEntity, String products,Integer[] bannerUnitMetadataId,Integer[] heroAreaMetadataId) {
        // TODO Auto-generated method stub
        Integer newproductMetadataId,Id=0;
        NewArrivalMetadataEntity.setIsEnable(true);
        Id=NewProductMetadataDao.update(NewArrivalMetadataEntity);
        newproductMetadataId=NewArrivalMetadataEntity.getNewArrivalMetadataId();
        //插入日志
        sysLogDao.insertLogForUpdate(newproductMetadataId,"NewArrivalMetadata");
        // 数据表插入    
        JSONArray entityArr = JSONArray.fromObject(products);
        if (entityArr.size() > 0) {
            for (int i = 0,size=entityArr.size(); i < size; i++) {
                NewArrivalEntity NewArrivalEntity = JSonUtil.toObject(entityArr.getString(i).toString(),
                        NewArrivalEntity.class);
                NewArrivalEntity.setIsEnable(true);
                NewArrivalEntity.setNewArrivalMetadataId(newproductMetadataId);
                Id=NewProductDao.update(NewArrivalEntity);
                //插入日志
                //sysLogDao.insertLogForInsert(NewArrivalEntity.getNewArrivalId(), "NewArrival");
            }
        }
        if(bannerUnitMetadataId!=null){
            String NewArrivalBannerUnit="UPDATE NEW_ARRIVAL_BANNER_UNIT SET IS_ENABLE=0";
            Integer[] ids={newproductMetadataId};
            NewProductDao.DelectNewProduct(NewArrivalBannerUnit, ids);
            NewArrivalBannerUnitEntity bu=new NewArrivalBannerUnitEntity();
            bu.setNewArrivalMetadataId(newproductMetadataId);
            for(int i=0,j=bannerUnitMetadataId.length;i<j;i++){
                bu.setBannerUnitMetadataId(bannerUnitMetadataId[i].intValue());
                NewProductBannerUnitDao.insert(bu);
            }
        }
        if(heroAreaMetadataId!=null){
            String NewArrivalHeroArea="UPDATE NEW_ARRIVAL_HERO_AREA SET IS_ENABLE=0";
            Integer[] ids={newproductMetadataId};
            NewProductDao.DelectNewProduct(NewArrivalHeroArea, ids);
            NewArrivalHeroAreaEntity ha=new NewArrivalHeroAreaEntity();
            ha.setNewArrivalMetadataId(newproductMetadataId);
            for(int i=0,j=heroAreaMetadataId.length;i<j;i++){
                ha.setHeroAreaMetadataId(heroAreaMetadataId[i].intValue());
                NewProductHeroAreaDao.insert(ha);
            }
        }
        return Id;
    }
    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public List<NewArrivalEntity> getNewArrivalByMetadataId(Integer NewArrivalMetadataId) {
        // TODO Auto-generated method stub
        NewArrivalEntity NewArrivalEntity = new NewArrivalEntity();
        NewArrivalEntity.setIsEnable(true);
        NewArrivalEntity.setNewArrivalMetadataId(NewArrivalMetadataId);
        return NewProductDao.selectByCondition(NewArrivalEntity);
    }
    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public NewArrivalMetadataEntity getNewArrivalMetadataByMetadataId(Integer NewArrivalMetadataId) {
        // TODO Auto-generated method stub
        return NewProductMetadataDao.selectById(NewArrivalMetadataId);
    }
    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public List<Map<String,Object>> getNewArrivalHeroAreAByMetadataId(
            Integer NewArrivalMetadataId) {
        // TODO Auto-generated method stub
        MasterdataEntity masterEntity = new MasterdataEntity();
        masterEntity.setLang(1);
        masterEntity.setIsEnable(true);
        masterEntity.setMasterdataMetadataId(MasterDataCodeConstant.CONTENT_DISPLAY_ATTR_NAME_HERO_AREA);
        List<MasterdataEntity> list = masterdataDao.selectByCondition(masterEntity);
        return NewProductHeroAreaDao.NewProductHeroArea(NewArrivalMetadataId,(list.get(0)!=null)?list.get(0).getMasterdataName().toString():"");
    }
    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public List<Map<String,Object>> getNewArrivalBannerUnitByMetadataId(Integer NewArrivalMetadataId) {
        // TODO Auto-generated method stub
        MasterdataEntity masterEntity = new MasterdataEntity();
        masterEntity.setLang(1);
        masterEntity.setIsEnable(true);
        masterEntity.setMasterdataMetadataId(MasterDataCodeConstant.CONTENT_DISPLAY_ATTR_NAME_HERO_AREA);
        List<MasterdataEntity> list = masterdataDao.selectByCondition(masterEntity);
        return NewProductBannerUnitDao.BannerUnit(NewArrivalMetadataId,(list.get(0)!=null)?list.get(0).getMasterdataName().toString():"");
    }
    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void deleteNewArrival(Integer[] NewArrivalMetadataIdArr) {
        // TODO Auto-generated method stub
        String NewArrivalMETADATA,NewArrival,NewArrivalHeroArea,NewArrivalBannerUnit="";
        NewArrivalMETADATA="UPDATE NEW_ARRIVAL_METADATA SET IS_ENABLE=0 ";
        NewProductDao.DelectNewProduct(NewArrivalMETADATA, NewArrivalMetadataIdArr);
        //插入日志
        //this.delectlog(NewArrivalMetadataIdArr, "NEW_ARRIVAL_METADATA");
        NewArrival="UPDATE NEW_ARRIVAL SET IS_ENABLE=0";
        NewProductDao.DelectNewProduct(NewArrival, NewArrivalMetadataIdArr);
        //插入日志
        //this.delectlog(NewArrivalMetadataIdArr, "NEW_ARRIVAL");
        NewArrivalHeroArea="UPDATE NEW_ARRIVAL_HERO_AREA SET IS_ENABLE=0";
        NewProductDao.DelectNewProduct(NewArrivalHeroArea, NewArrivalMetadataIdArr);
        //插入日志
        //this.delectlog(NewArrivalMetadataIdArr, "NEW_ARRIVAL_HERO_AREA");
        NewArrivalBannerUnit="UPDATE NEW_ARRIVAL_BANNER_UNIT SET IS_ENABLE=0";
        NewProductDao.DelectNewProduct(NewArrivalBannerUnit, NewArrivalMetadataIdArr);
        //插入日志
        //this.delectlog(NewArrivalMetadataIdArr, "NEW_ARRIVAL_BANNER_UNIT");
    }
    private void delectlog(Integer[] newArrivalMetadataIdArr, String table) {
        // TODO Auto-generated method stub
        for(int i=0,j=newArrivalMetadataIdArr.length;i<j;i++){
            sysLogDao.insertLogForDelete(newArrivalMetadataIdArr[i].intValue(), table);
        } 
    }
}
