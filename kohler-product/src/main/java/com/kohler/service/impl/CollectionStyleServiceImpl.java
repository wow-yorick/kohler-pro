    package com.kohler.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.kohler.dao.CollectionStyleDao;
import com.kohler.dao.SysLogDao;
import com.kohler.dao.CollectionStyleMetadataDao;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohler.constants.CollectionStyleSQL;
import com.kohler.constants.NewProductSQL;
import com.kohler.dao.utils.Pager;
import com.kohler.entity.CollectionStyleEntity;
import com.kohler.entity.CollectionStyleMetadataEntity;
import com.kohler.entity.NewArrivalEntity;
import com.kohler.service.CollectionStyleService;
import com.kohler.util.JSonUtil;
@Service
public class CollectionStyleServiceImpl  implements CollectionStyleService{
    
    @Autowired
    private CollectionStyleDao CollectionStyleDao;
    @Autowired
    private SysLogDao sysLogDao;
    @Autowired
    private CollectionStyleMetadataDao CollectionStyleMetadataDao;
    @Override
    public Pager<Map<String, Object>> getCollectionStyleListPage(Pager<Map<String, Object>> pager,
            String Name) {
        // TODO Auto-generated method stub
        List<Object> queryParams = new ArrayList<Object>();
        StringBuilder querySql = new StringBuilder(CollectionStyleSQL.SELECT_CollectionStyle_BY_NAME); 
        StringBuilder countSql = new StringBuilder(CollectionStyleSQL.SELECT_CollectionStyle_COUNT);
        if(Name !=null && !"".equals(Name) ){
                querySql.append("  and cs.COLLECTION_STYLE_NAME like concat(concat('%',?),'%') ");
                countSql.append("  and cs.COLLECTION_STYLE_NAME like concat(concat('%',?),'%') ");
                queryParams.add(Name);
        }
        querySql.append(" limit ?,?");
        int totalCount = CollectionStyleDao.selectCountByCondition(countSql.toString(), queryParams);
        int pageCount = 0;
        if (totalCount % pager.getPageSize() == 0) {
            pageCount = totalCount / pager.getPageSize();
        } else {
            pageCount = totalCount / pager.getPageSize() + 1;
        }
        int index = (pager.getCurrentPage() - 1) * pager.getPageSize();
        queryParams.add(index);
        queryParams.add(pager.getPageSize());
        List<Map<String,Object>> list = CollectionStyleDao.selectByConditionWithMap(querySql.toString(), queryParams);
        pager.setStartRow(index);
        pager.setDatas(list);
        pager.setTotalRecord(totalCount);
        pager.setTotalPage(pageCount);
        return pager;
    }

    

    @Override
    public Integer addCollectionStyle(CollectionStyleMetadataEntity CollectionStyleMetadataEntity,
            String products) {
        // TODO Auto-generated method stub
        Integer CollectionStyleMetadataId = CollectionStyleMetadataDao.insert(CollectionStyleMetadataEntity);
        //插入日志
        sysLogDao.insertLogForInsert(CollectionStyleMetadataId, "CollectionStyleMetadata");
        // 数据表插入
        JSONArray entityArr = JSONArray.fromObject(products);
        if (entityArr.size() > 0) {
            for (int i = 0,size=entityArr.size(); i < size; i++) {
                CollectionStyleEntity CollectionStyleEntity = JSonUtil.toObject(entityArr.getString(i).toString(),
                        CollectionStyleEntity.class);
                CollectionStyleEntity.setCollectionStyleMetadataId(CollectionStyleMetadataId);
                CollectionStyleDao.insert(CollectionStyleEntity);
            }
        }
        return CollectionStyleMetadataId;
    }

    @Override
    public Integer editCollectionStyle(CollectionStyleMetadataEntity CollectionStyleMetadataEntity,
            String products) {
        // TODO Auto-generated method stub
        Integer CollectionStyleMetadataId,Id=0;
        CollectionStyleMetadataEntity.setIsEnable(true);
        Id=CollectionStyleMetadataDao.update(CollectionStyleMetadataEntity);
        CollectionStyleMetadataId=CollectionStyleMetadataEntity.getCollectionStyleMetadataId();
        //插入日志
        sysLogDao.insertLogForUpdate(CollectionStyleMetadataId,"CollectionStyleMetadata");
        // 数据表插入    
        JSONArray entityArr = JSONArray.fromObject(products);
        if (entityArr.size() > 0) {
            for (int i = 0; i < entityArr.size(); i++) {
                CollectionStyleEntity CollectionStyleEntity = JSonUtil.toObject(entityArr.getString(i).toString(),
                        CollectionStyleEntity.class);
                CollectionStyleEntity.setIsEnable(true);
                CollectionStyleEntity.setCollectionStyleMetadataId(CollectionStyleMetadataId);
                CollectionStyleDao.update(CollectionStyleEntity);
            }
        }
        return CollectionStyleMetadataId;
    }

    @Override
    public void deleteCollectionStyle(Integer[] CollectionStyle) {
        // TODO Auto-generated method stub
        String CollectionStyleMetadata="UPDATE COLLECTION_STYLE_METADATA SET IS_ENABLE=0 ";
        CollectionStyleDao.DelectCollectionStyle(CollectionStyleMetadata,CollectionStyle);
        String CollectionStyle1="UPDATE COLLECTION_STYLE SET IS_ENABLE=0 ";
        CollectionStyleDao.DelectCollectionStyle(CollectionStyle1,CollectionStyle);
    }



    @Override
    public List<CollectionStyleEntity> getCollectionStyleByMetadataId(
            Integer CollectionStyleMetadataId) {
        // TODO Auto-generated method stub
        CollectionStyleEntity CollectionStyleEntity = new CollectionStyleEntity();
        CollectionStyleEntity.setIsEnable(true);
        CollectionStyleEntity.setCollectionStyleMetadataId(CollectionStyleMetadataId);
        return CollectionStyleDao.selectByCondition(CollectionStyleEntity);
    }



    @Override
    public CollectionStyleMetadataEntity getCollectionStyleMetadataByMetadataId(
            Integer CollectionStyleMetadataId) {
        // TODO Auto-generated method stub
        return CollectionStyleMetadataDao.selectById(CollectionStyleMetadataId);
    }




}
