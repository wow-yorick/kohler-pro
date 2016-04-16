package com.kohler.service;

import java.util.List;
import java.util.Map;

import com.kohler.dao.utils.Pager;
import com.kohler.entity.AccountEntity;
import com.kohler.entity.ContactInfoEntity;

/**
 * Contactus Interface
 * 
 * @author fujiajun
 * @Date 2014年9月25日
 */
public interface ContactusService {
    /**
     * 获取Contact us列表
     * @param pager
     * @param Name
     * @return
     * @author fujiajun
     * Date 2014年10月28日
     * @version
     */
     public Pager<Map<String, Object>> getContactusListPage(Pager<Map<String, Object>> pager,Integer Status);
     /**
      * 通过ContactusId获取信息
      * @param ContactusId
      * @param ContactInfoEntity
      * @return
      * @author Administrator
      * Date 2014年10月23日
      * @version
      */
     public Integer updateContactus(ContactInfoEntity ContactInfoEntity,int ContactusId);
     
     /**
      * 通过ContactusId获取信息
      * @param ContactusId
      * @return
      * @author Administrator
      * Date 2014年10月23日
      * @version
      */
     public List<Map<String,Object>> getview(int ContactusId);
     
     /**
      * 通过ContactusId[] 处理相关请求
      * @param ContactusId[]
      * @return
      * @author Administrator
      * Date 2014年10月23日
      * @version
      */
     public Integer Reply(Integer ContactusId);
     /**
      * 通过ContactusId[] 处理相关请求
      * @param ContactusId[]
      * @return
      * @author Administrator
      * Date 2014年10月23日
      * @version
      */
     public Integer ReplySave(Integer ContactusId,String replyContent);
     /**
      * 用户搜索条件
      * @param status
      * @author Administrator
      * Date 2014年10月23日
      * @version
      */
     public List<Map<String,Object>> getselectlist(int status);


}
