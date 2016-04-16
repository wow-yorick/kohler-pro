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

import com.kohler.constants.AccountSQL;
import com.kohler.dao.AccountDao;
import com.kohler.dao.MasterDataCodeDao;
import com.kohler.dao.utils.Pager;
import com.kohler.entity.AccountEntity;
import com.kohler.service.AccountService;

/**
 * Account Service
 * 
 * @author fujiajun
 * @Date 2014年11月24日
 */
@Service
public class AccountServiceImpl  implements AccountService {
    
    @Autowired 
    public AccountDao accountdao;
    @Autowired
    private MasterDataCodeDao MasterDataCodeDao;
    
    @Override
    public Pager<Map<String, Object>> getAccountListPage(Pager<Map<String, Object>> pager,
            String Name,String Mobile,Integer Type) {
        // TODO Auto-generated method stub
        List<Object> queryParams = new ArrayList<Object>();
        StringBuilder querySql = new StringBuilder(AccountSQL.SELECT_ACCOUNT_WITH_MAP); 
        StringBuilder countSql = new StringBuilder(AccountSQL.SELECT_ACCOUNT_COUNT);
        if(Name !=null && !"".equals(Name)  &&  !"*".equals(Name)){
                querySql.append("  and a.REAL_NAME like concat(concat('%',?),'%') ");
                countSql.append("  and a.REAL_NAME like concat(concat('%',?),'%') ");
                queryParams.add(Name);
        }
        if(Mobile !=null && !"".equals(Mobile) && !"*".equals(Mobile)){
            querySql.append("  and a.ACCOUNT_NAME like concat(concat('%',?),'%')  ");
            countSql.append("  and a.ACCOUNT_NAME like concat(concat('%',?),'%') ");
            queryParams.add(Mobile);
        }
        if(Type !=null){
            System.out.println("Type"+Type);
            querySql.append("  and a.ACCOUNT_TYPE =? ");
            countSql.append("  and a.ACCOUNT_TYPE =? ");
            queryParams.add(Type);
        }
        querySql.append(" limit ?,?");
        int totalCount = accountdao.selectCountByCondition(countSql.toString(), queryParams);
        int pageCount = 0;
        if (totalCount % pager.getPageSize() == 0) {
            pageCount = totalCount / pager.getPageSize();
        } else {
            pageCount = totalCount / pager.getPageSize() + 1;
        }
        int index = (pager.getCurrentPage() - 1) * pager.getPageSize();
        queryParams.add(index);
        queryParams.add(pager.getPageSize());
        List<Map<String,Object>> list = accountdao.selectByConditionWithMap(querySql.toString(), queryParams);
        pager.setStartRow(index);
        pager.setDatas(list);
        pager.setTotalRecord(totalCount);
        pager.setTotalPage(pageCount);
        return pager;
    }

   

    @Override
    public List<Map<String, Object>> getAccountById(Integer AccountId) {
        // TODO Auto-generated method stub
        List<Object> queryParams = new ArrayList<Object>();
        queryParams.add(AccountId);
        return accountdao.selectByConditionWithMap(AccountSQL.SELECT_ACCOUNT_VIEW, queryParams);
         
    }

    @Override
    public List<Map<String, Object>> EditViewAccountById(Integer AccountId) {
        // TODO Auto-generated method stub
        List<Object> queryParams = new ArrayList<Object>();
        queryParams.add(AccountId);
        return accountdao.selectByConditionWithMap(AccountSQL.SELECT_ACCOUNT_VIEW, queryParams);
    }
    
    @Override
    public Integer updateAccount(AccountEntity AccountEntity,int type) {
        // TODO Auto-generated method stub
        //int status=0;
        //List<Object> queryParams = new ArrayList<Object>();
        //queryParams.add();
        //List<Map<String,Object>> list=accountdao.selectByConditionWithMap(AccountSQL.SELECT_ACCOUNT_STATUS,queryParams);
        //status=Integer.parseInt(list.get(0).get("MASTERDATA_METADATA_ID").toString());
        AccountEntity.setStatus((type==1) ? 10180002:10180001);
        Integer id=accountdao.account_edit(AccountEntity);
        return (id>0) ? id : 0 ;
    }

    @Override
    public Integer restpassword(AccountEntity AccountEntity) {
        // TODO Auto-generated method stub
        return  accountdao.account_edit_password(AccountEntity);
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
