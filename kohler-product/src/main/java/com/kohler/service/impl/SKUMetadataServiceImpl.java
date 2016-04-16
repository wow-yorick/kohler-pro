/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kohler.dao.CategoryAccessoryDao;
import com.kohler.dao.SKUAccessoryDao;
import com.kohler.dao.SKUAttrDao;
import com.kohler.dao.SKUDao;
import com.kohler.dao.SKUMetadataDao;
import com.kohler.dao.SysLogDao;
import com.kohler.entity.CategoryAccessoryEntity;
import com.kohler.entity.MasterDataCodeConstant;
import com.kohler.entity.SKUAccessoryEntity;
import com.kohler.entity.SKUAttrEntity;
import com.kohler.entity.SKUEntity;
import com.kohler.entity.SKUMetadataEntity;
import com.kohler.service.SKUMetadataService;

/**
 * Class Function Description
 * SKUMetadataServiceImpl
 * @author zhangtingting
 * @Date 2014年9月25日
 */
@Service
public class SKUMetadataServiceImpl implements SKUMetadataService {
    
    @Autowired
    private SKUMetadataDao skuMetadataDao;
    
    @Autowired
    private SKUDao skuDao;
    
    @Autowired
    private SysLogDao sysLogDao;
    
    @Autowired
    private SKUAttrDao skuAttrDao;
    
    @Autowired
    private SKUAccessoryDao skuAccessoryDao;
    
    @Autowired
    private CategoryAccessoryDao categoryAccessoryDao;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public SKUMetadataEntity addSKUMetadata() {
        SKUMetadataEntity skuMetadataEntity=new SKUMetadataEntity();
       /* skuMetadataEntity.setIsDefault(false);
        skuMetadataEntity.setIsEnable(false);
        
        int skuMetadataId=skuMetadataDao.insert(skuMetadataEntity);
        skuMetadataEntity.setSkuMetadataId(skuMetadataId);
        
        sysLogDao.insertLogForInsert(skuMetadataId, "SKU_METADATA");*/
        
        return skuMetadataEntity;
    }
    
    

    @Override
    @Transactional
    public void saveSKUMetadata(SKUMetadataEntity skuMetadataEntity, List<SKUEntity> skulist,List<SKUAttrEntity> skuAttrlist, List<Map<String, Object>> accessorylist) throws UnsupportedEncodingException {
        if(null != skuMetadataEntity && !"".equals(skulist)){
            skuMetadataEntity.setIsEnable(true);
           /* skuMetadataDao.update(skuMetadataEntity);
            sysLogDao.insertLogForUpdate(skuMetadataEntity.getSkuMetadataId(), "SKU_METADATA");*/
            if(skuMetadataEntity.getIsDefault()!=null&&skuMetadataEntity.getIsDefault()==true){
                SKUMetadataEntity sme = new SKUMetadataEntity();
                sme.setIsDefault(true);
                sme.setProductMetadataId(skuMetadataEntity.getProductMetadataId());
                sme.setIsEnable(true);
                List<SKUMetadataEntity> smelist = skuMetadataDao.selectByCondition(sme);
                if(smelist!=null&&smelist.size()>0){
                    for (SKUMetadataEntity defaultSkuMetadataEntity : smelist) {
                        defaultSkuMetadataEntity.setIsDefault(false);
                        skuMetadataDao.update(defaultSkuMetadataEntity);
                    }
                }
            }
            
            int skuMetadataId=skuMetadataDao.insert(skuMetadataEntity);
            skuMetadataEntity.setSkuMetadataId(skuMetadataId);
            
            sysLogDao.insertLogForInsert(skuMetadataId, "SKU_METADATA");
            
            if(skulist.size()>0){
                for(SKUEntity sku:skulist){
                    sku.setSkuMetadataId(skuMetadataEntity.getSkuMetadataId());
                    sku.setIsEnable(true);
                    int skuId=skuDao.insert(sku);
                    
                    sysLogDao.insertLogForInsert(skuId, "SKU");
                }
            }
            
            if(accessorylist!=null&&accessorylist.size()>0){
                for (Map<String, Object> temp : accessorylist) {
                    String strtemp= (String)temp.get("lang");
                    String[] langstrs = strtemp.split("&");
                    String[] lang = langstrs[0].split("=");
                    
                    for (String langstr : langstrs) {
                        String[] keyval = langstr.split("=");
                        if(keyval[0]!=null&&!"lang".equals(keyval[0])&&keyval!=null&&keyval.length>1){
                            SKUAccessoryEntity sa = new SKUAccessoryEntity();
                            sa.setIsEnable(true);
                            sa.setLang(Integer.parseInt(lang[1]));
                            sa.setCategoryAccessoryMetadataId(Integer.parseInt(keyval[0]));
                            sa.setSkuMetadataId(skuMetadataId);
                           
                            String skuids =  URLDecoder.decode(keyval[1],"utf-8");
                            
                            
                            List<CategoryAccessoryEntity> calist = categoryAccessoryDao.getCategoryAccessoryListByAccessoryMetadataId(keyval[0],lang[1]);
                            if(calist!=null&&calist.size()>0){
                                if(calist.get(0).getCategoryAccessoryType()==MasterDataCodeConstant.CATEGORY_ACCESSORY_SKU_PICKER){
                                    if(skuids!=null&&!"".equals(skuids)){
                                        if(skuids.indexOf(",")>-1){
                                            String[] skuidtemp = skuids.split(",");
                                            for (String skuid : skuidtemp) {
                                                sa.setSkuPickId(Integer.parseInt(skuid));
                                                int skuAccessoryId = skuAccessoryDao.insert(sa);
                                                sysLogDao.insertLogForInsert(skuAccessoryId, "SKU_ACCESSORY");
                                            }
                                        }else{
                                            sa.setSkuPickId(Integer.parseInt(skuids));
                                            int skuAccessoryId = skuAccessoryDao.insert(sa);
                                            sysLogDao.insertLogForInsert(skuAccessoryId, "SKU_ACCESSORY");
                                        }
                                    }
                                }else{
                                    sa.setAccessoryDescription(skuids);
                                    int skuAccessoryId = skuAccessoryDao.insert(sa);
                                    sysLogDao.insertLogForInsert(skuAccessoryId, "SKU_ACCESSORY");
                                }
                            }
                        }
                    }
                }
            }
            
            if(skuAttrlist.size()>0){
                for(SKUAttrEntity skuAttr:skuAttrlist){
                    skuAttr.setSkuMetadataId(skuMetadataEntity.getSkuMetadataId());
                    skuAttr.setIsEnable(true);
                    int skuAttrId=skuAttrDao.insert(skuAttr);
                    
                    sysLogDao.insertLogForInsert(skuAttrId, "SKU_ATTR");
                }
            }
        }
    }



    /**
     * {@inheritDoc}
     */
    @Override
    public List<SKUMetadataEntity> getSKUMetadataEntitylistById(Integer productMetadataId) {
        SKUMetadataEntity entity=new SKUMetadataEntity();
        entity.setProductMetadataId(productMetadataId);
        entity.setIsEnable(true);
        return skuMetadataDao.selectByCondition(entity);
    }



    /**
     * {@inheritDoc}
     */
    @Override
    public SKUMetadataEntity getSkuMetadataBySKUMetadataid(Integer skuMetadataId) {
        return skuMetadataDao.selectById(skuMetadataId);
    }



    /**
     * {@inheritDoc}
     */
    @Override
    public void updateSKUMetadata(SKUMetadataEntity skuMetadataEntity, List<SKUEntity> skulist,
            List<SKUAttrEntity> skuAttrlist, List<Map<String, Object>> accessorylist)throws UnsupportedEncodingException {
        if(null != skuMetadataEntity && !"".equals(skulist)){
            skuMetadataEntity.setIsEnable(true);
            
            if(skuMetadataEntity.getIsDefault()!=null&&skuMetadataEntity.getIsDefault()==true){
                SKUMetadataEntity sme = new SKUMetadataEntity();
                sme.setIsDefault(true);
                sme.setProductMetadataId(skuMetadataEntity.getProductMetadataId());
                sme.setIsEnable(true);
                List<SKUMetadataEntity> smelist = skuMetadataDao.selectByCondition(sme);
                if(smelist!=null&&smelist.size()>0){
                    for (SKUMetadataEntity defaultSkuMetadataEntity : smelist) {
                        defaultSkuMetadataEntity.setIsDefault(false);
                        skuMetadataDao.update(defaultSkuMetadataEntity);
                    }
                }
            }
            
            skuMetadataDao.update(skuMetadataEntity);
            
            sysLogDao.insertLogForUpdate(skuMetadataEntity.getSkuMetadataId(), "SKU_METADATA");
            
            if(skulist.size()>0){
                for(SKUEntity sku:skulist){
                    sku.setSkuMetadataId(skuMetadataEntity.getSkuMetadataId());
                    sku.setIsEnable(true);
                    skuDao.update(sku);
                    
                    sysLogDao.insertLogForUpdate(sku.getSkuId(), "SKU");
                }
            }
            
            if(skuAttrlist.size()>0){
                for(SKUAttrEntity skuAttr:skuAttrlist){
                    SKUAttrEntity newskuattr = new SKUAttrEntity();
                    newskuattr.setSkuMetadataId(skuMetadataEntity.getSkuMetadataId());
                    newskuattr.setCategorySKUAttrMetadataId(skuAttr.getCategorySKUAttrMetadataId());
                    newskuattr.setIsEnable(true);
                    List<SKUAttrEntity> skuattrlist = skuAttrDao.selectByCondition(newskuattr);
                    if(skuattrlist!=null&&skuattrlist.size()>0){
                        newskuattr = skuattrlist.get(0);
                        newskuattr.setCategorySKUAttrValuesMetadataId(skuAttr.getCategorySKUAttrValuesMetadataId());
                        skuAttrDao.update(newskuattr);
                        sysLogDao.insertLogForUpdate(newskuattr.getSkuAttrId(), "SKU_ATTR");
                    }else{
                        newskuattr.setCategorySKUAttrValuesMetadataId(skuAttr.getCategorySKUAttrValuesMetadataId());
                        int i = skuAttrDao.insert(newskuattr);
                        sysLogDao.insertLogForInsert(i, "SKU_ATTR");
                    }
                    
                }
            }
            
//            List<SKUAccessoryEntity> list = skuAccessoryDao.getAccessoryBySKUMetadataId(String.valueOf(skuMetadataEntity.getSkuMetadataId()));
//            for (SKUAccessoryEntity skuAccessoryEntity : list) {
//                skuAccessoryEntity.setIsEnable(false);
//                skuAccessoryDao.update(skuAccessoryEntity);
//                
//            }
            //将SKU_ACCESSORY表中所有词skuMetadataId的数据删除
            skuAccessoryDao.updateAllAccessoryFalseByskuMetadataId(skuMetadataEntity.getSkuMetadataId());
            //重新插入
            if(accessorylist!=null&&accessorylist.size()>0){
                for (Map<String, Object> temp : accessorylist) {
                    String strtemp= (String)temp.get("lang");
                    String[] langstrs = strtemp.split("&");
                    String[] lang = langstrs[0].split("=");
                    
                    for (String langstr : langstrs) {
                        String[] keyval = langstr.split("=");
                        if(keyval[0]!=null&&!"lang".equals(keyval[0])&&keyval!=null&&keyval.length>1){
                            SKUAccessoryEntity sa = new SKUAccessoryEntity();
                            sa.setIsEnable(true);
                            sa.setLang(Integer.parseInt(lang[1]));
                            sa.setCategoryAccessoryMetadataId(Integer.parseInt(keyval[0]));
                            sa.setSkuMetadataId(skuMetadataEntity.getSkuMetadataId());
                           
                            String skuids =  URLDecoder.decode(keyval[1],"utf-8");
                            
                            
                            List<CategoryAccessoryEntity> calist = categoryAccessoryDao.getCategoryAccessoryListByAccessoryMetadataId(keyval[0],lang[1]);
                            if(calist!=null&&calist.size()>0){
                                if(calist.get(0).getCategoryAccessoryType()==MasterDataCodeConstant.CATEGORY_ACCESSORY_SKU_PICKER){
                                    if(skuids!=null&&!"".equals(skuids)){
                                        if(skuids.indexOf(",")>-1){
                                            String[] skuidtemp = skuids.split(",");
                                            for (String skuid : skuidtemp) {
                                                sa.setSkuPickId(Integer.parseInt(skuid));
                                                int skuAccessoryId = skuAccessoryDao.insert(sa);
                                                sysLogDao.insertLogForInsert(skuAccessoryId, "SKU_ACCESSORY");
                                            }
                                        }else{
                                            sa.setSkuPickId(Integer.parseInt(skuids));
                                            int skuAccessoryId = skuAccessoryDao.insert(sa);
                                            sysLogDao.insertLogForInsert(skuAccessoryId, "SKU_ACCESSORY");
                                        }
                                    }
                                }else{
                                    sa.setAccessoryDescription(skuids);
                                    int skuAccessoryId = skuAccessoryDao.insert(sa);
                                    sysLogDao.insertLogForInsert(skuAccessoryId, "SKU_ACCESSORY");
                                }
                            }
                        }
                    }
                }
            }
        }
    }



    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteSKUMetadata(Integer skuMetadataId) {
        SKUMetadataEntity skuMetadataEntity=skuMetadataDao.selectById(skuMetadataId);
        if(null != skuMetadataEntity){
            skuMetadataEntity.setIsEnable(false);
            skuMetadataDao.update(skuMetadataEntity);
            
            sysLogDao.insertLogForDelete(skuMetadataId, "SKU_METADATA");
            
            SKUEntity skuEntity=new SKUEntity();
            skuEntity.setSkuMetadataId(skuMetadataId);
            List<SKUEntity> skulist=skuDao.selectByCondition(skuEntity);
            if(skulist.size()>0){
                for(SKUEntity sku:skulist){
                    sku.setIsEnable(false);
                    skuDao.update(sku);
                    
                    sysLogDao.insertLogForDelete(sku.getSkuId(), "SKU");
                }
            }
            
            
            SKUAttrEntity skuAttrEntity=new SKUAttrEntity();
            skuAttrEntity.setSkuMetadataId(skuMetadataId);
            List<SKUAttrEntity> skuAttrlist=skuAttrDao.selectByCondition(skuAttrEntity);
            if(skuAttrlist.size()>0){
                for(SKUAttrEntity skuAttr:skuAttrlist){
                    skuAttr.setIsEnable(false);
                    skuAttrDao.update(skuAttr);
                    
                    sysLogDao.insertLogForDelete(skuAttr.getSkuAttrId(), "SKU_ATTR");
                }
            }
        }
    }



    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Object> getFileNamesBySkuId(Integer skuId) {
        
        return skuMetadataDao.getFileNamesBySkuId(skuId);
    }



    /**
     * {@inheritDoc}
     */
    @Override
    public List<SKUMetadataEntity> checkSKUCode(String skuCode, Integer skuMetadataId) {
        String selectsql = "SELECT * from SKU_METADATA "+
                " where IS_ENABLE=1 and SKU_CODE = ? ";
        List<Object> params = new ArrayList<Object>();
        params.add(skuCode);
        //判断是新增还是编辑
        if(skuMetadataId!=null&&!"".equals(skuMetadataId.toString())){
            selectsql += " and SKU_METADATA_ID != ?";
            params.add(skuMetadataId);
        }
        
        return skuMetadataDao.selectByCondition(selectsql, params);
    }

}
