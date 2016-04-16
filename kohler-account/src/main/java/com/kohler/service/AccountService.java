/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.service;

import java.util.List;
import java.util.Map;

import com.kohler.dao.utils.Pager;
import com.kohler.entity.AccountEntity;
import com.kohler.entity.NewArrivalMetadataEntity;

/**
 * Account Interface
 * 
 * @author fujiajun
 * @Date 2014年9月25日
 */
public interface AccountService {
    /**
     * 获取系列项目列表
     * @param pager
     * @param Name
     * @return
     * @author fujiajun
     * Date 2014年10月28日
     * @version
     */
     public Pager<Map<String, Object>> getAccountListPage(Pager<Map<String, Object>> pager, String Name,String Mobile,Integer Type);
     
     /**
      * 通过AccountId获取信息
      * @param AccountId
      * @return
      * @author Administrator
      * Date 2014年10月23日
      * @version
      */
     public List<Map<String, Object>> getAccountById(Integer AccountId);
     /**
      * 通过AccountId获取信息
      * @param AccountId
      * @return
      * @author Administrator
      * Date 2014年10月23日
      * @version
      */
     public List<Map<String, Object>> EditViewAccountById(Integer AccountId);
     /**
      * 通过AccountId获取信息
      * @param AccountId
      * @return
      * @author Administrator
      * Date 2014年10月23日
      * @version
      */
     public Integer updateAccount(AccountEntity AccountEntity,int type);
    
     /**
      * 通过AccountId 改变密码
      * @param AccountId
      * @return
      * @author Administrator
      * Date 2014年10月23日
      * @version
      */
     public Integer restpassword(AccountEntity AccountEntity);
     
     /**
      * 用户搜索条件
      * @param UserfolderIdArr
      * @author Administrator
      * Date 2014年10月23日
      * @version
      */
     public List<Map<String,Object>> getselectlist(int type);
}
