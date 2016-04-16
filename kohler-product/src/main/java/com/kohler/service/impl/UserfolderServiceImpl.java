/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohler.constants.UserfolderSQL;
import com.kohler.dao.MasterDataCodeDao;
import com.kohler.dao.UserfolderDao;
import com.kohler.dao.UserfolderDetailDao;
import com.kohler.dao.SysLogDao;
import com.kohler.dao.utils.Pager;
import com.kohler.service.UserfolderService;
/**
 * 
 * 用户数据逻辑服务
 *
 * @author Administrator
 * @Date 2014年10月17日
 */
@Service
public class UserfolderServiceImpl implements UserfolderService {
    
    @Autowired
    private UserfolderDao UserfolderDao;
    @Autowired
    private SysLogDao sysLogDao;
    @Autowired
    private UserfolderDetailDao UserfolderDetailDao; 
    @Autowired
    private MasterDataCodeDao MasterDataCodeDao;
    
    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Pager<Map<String, Object>> getUserfolderList(Pager<Map<String, Object>> pager,
            Integer Type, Integer UserType) {
        // TODO Auto-generated method stub
        List<Object> queryParams = new ArrayList<Object>();
        StringBuilder querySql = new StringBuilder(UserfolderSQL.SELECT_Userfolder_WITH_MAP); 
        StringBuilder countSql = new StringBuilder(UserfolderSQL.SELECT_Userfolder_COUNT);
        if( UserType != null ){
                querySql.append("  and ACCOUNT_TYPE =? ");
                countSql.append("  and ACCOUNT_TYPE =? ");
                queryParams.add(UserType);
        }
        if(Type != null){
            querySql.append("  and ACCOUNT_FOLDER_TYPE =? ");
            countSql.append("  and ACCOUNT_FOLDER_TYPE =? ");
            queryParams.add(Type);
        }
        querySql.append(" limit ?,?");
        int totalCount = UserfolderDao.selectCountByCondition(countSql.toString(), queryParams);
        int pageCount,id,xb= 0;
        if (totalCount % pager.getPageSize() == 0) {
            pageCount = totalCount / pager.getPageSize();
        } else {
            pageCount = totalCount / pager.getPageSize() + 1;
        }
        int index = (pager.getCurrentPage() - 1) * pager.getPageSize();
        queryParams.add(index);
        queryParams.add(pager.getPageSize());
        List<Map<String,Object>> list = UserfolderDao.selectByConditionWithMap(querySql.toString(), queryParams);
        String userfolder,sql,str="";
        List<Object> queryParams_ = new ArrayList<Object>();
        for(Map<String,Object> map:list){
             if(map.get("titleid") != null && map.get("FOLDER_DETAIL_ID") != null){
                   List<Map<String,Object>> mapdata=null;
                   userfolder=map.get("titleid").toString();
                   id=Integer.parseInt(map.get("FOLDER_DETAIL_ID").toString());
                   if(userfolder.equals("产品")){
                       queryParams_.add(id);
                       sql=UserfolderSQL.SELECT_SKU_WITH_MAP;
                   }else if(userfolder.equals("文章")){
                       queryParams_.add(id);
                       sql=UserfolderSQL.SELECT_CONTENT_FIELD_VALUES_WITH_MAP;
                   }else{
                       queryParams_.add(id);
                       sql=UserfolderSQL.SELECT_PRODUCT_VIDEO_WITH_MAP;
                   }
                   mapdata=UserfolderDao.selectByConditionWithMap(sql, queryParams_);
                   System.out.println(mapdata.size());
                   if(mapdata.size() > 0 && mapdata.get(0) != null ){
                       str= mapdata.get(0).get("titlename").toString();
                   }else{
                       str="";
                   }
                   map.put("titlename",str);
                }else{
                   list.remove(xb);
                }
              xb++;
        }
        pager.setStartRow(index);
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
    public void deleteUserfolder(Integer[] UserfolderIdArr) {
        // TODO Auto-generated method stub
        UserfolderDetailDao.delUserfolderDetail(UserfolderIdArr);
    }


    @Override
    public List<Map<String, Object>> getselectlist(int type) {
        // TODO Auto-generated method stub
        List<Object> queryParams_ = new ArrayList<Object>();
        queryParams_.add(type);
        queryParams_.add(1);
        return MasterDataCodeDao.getAllTypeByMasterData(queryParams_);
    }

}
