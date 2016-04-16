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

import com.kohler.constants.ContactusSQL;
import com.kohler.dao.ContactusDao;
import com.kohler.dao.utils.Pager;
import com.kohler.entity.ContactInfoEntity;
import com.kohler.service.ContactusService;
import com.kohler.dao.MasterDataCodeDao;
import com.kohler.entity.SysUserEntity;
import com.kohler.util.SysContent;

import javax.servlet.http.HttpSession;

/**
 * Contactus Service
 * 
 * @author fujiajun
 * @Date 2014年11月24日
 */
@Service
public class ContactusServiceImpl implements ContactusService{
    @Autowired
    private ContactusDao  ContactusDao;
    @Autowired
    private MasterDataCodeDao MasterDataCodeDao;
    
    @Override
    public Pager<Map<String, Object>> getContactusListPage(Pager<Map<String, Object>> pager,
            Integer Status) {
        // TODO Auto-generated method stub
        List<Object> queryParams = new ArrayList<Object>();
        StringBuilder querySql = new StringBuilder(ContactusSQL.SELECT_Contactus_WITH_MAP); 
        StringBuilder countSql = new StringBuilder(ContactusSQL.SELECT_Contactus_WITH_COUNT);
        if(Status !=null){
            querySql.append("  and ci.CONTACT_STATUS =? ");
            countSql.append("  and ci.CONTACT_STATUS =? ");
            queryParams.add(Status);
        }
        querySql.append(" limit ?,?");
        int totalCount = ContactusDao.selectCountByCondition(countSql.toString(), queryParams);
        int pageCount = 0;
        if (totalCount % pager.getPageSize() == 0) {
            pageCount = totalCount / pager.getPageSize();
        } else {
            pageCount = totalCount / pager.getPageSize() + 1;
        }
        int index = (pager.getCurrentPage() - 1) * pager.getPageSize();
        queryParams.add(index);
        queryParams.add(pager.getPageSize());
        System.out.println("querySql.toString()"+querySql.toString());
        List<Map<String,Object>> list = ContactusDao.selectByConditionWithMap(querySql.toString(), queryParams);
        System.out.println("querySql.toString()"+list.size()); 
        pager.setStartRow(index);
        pager.setDatas(list);
        pager.setTotalRecord(totalCount);
        pager.setTotalPage(pageCount);
        return pager;
    }

    @Override
    public Integer updateContactus(ContactInfoEntity ContactInfoEntity, int ContactusId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Map<String,Object>> getview(int ContactusId) {
        // TODO Auto-generated method stub
        List<Object> queryParams = new ArrayList<Object>();
        queryParams.add(ContactusId);
        return ContactusDao.selectByConditionWithMap(ContactusSQL.SELECT_Contactus_VIEW, queryParams);
    }

    @Override
    public Integer Reply(Integer ContactusId) {
        // TODO Auto-generated method stub
        Integer i=0;
        ContactInfoEntity ci=ContactusDao.selectById(ContactusId);
        HttpSession session=SysContent.getSession();
        SysUserEntity user = null;
        if(session != null){
            user=(SysUserEntity)session.getAttribute("sysUser");
        }else{
            return i;
        }
        if(ci.getContactStatus() != null && (ci.getContactStatus() == 10240001 || ci.getContactStatus() == 10240002) && user.getSysUserId() == ci.getHandleUser()){
                if(ci.getContactStatus() == 10240001){
                    ci.setContactInfoId(ContactusId);
                    ci.setContactStatus(10240002);
                    ci.setIsEnable(true);
                    ci.setHandleUser(user.getSysUserId());
                    i=ContactusDao.update(ci);
                }
        }
        return i;
    }       

    @Override
    public List<Map<String, Object>> getselectlist(int status) {
        // TODO Auto-generated method stub
        List<Object> queryParams_ = new ArrayList<Object>();
        queryParams_.add(status);
        queryParams_.add(1);
        return MasterDataCodeDao.getAllTypeByMasterData(queryParams_);
    }

    @Override
    public Integer ReplySave(Integer ContactusId, String replyContent) {
        // TODO Auto-generated method stub
        ContactInfoEntity ci=ContactusDao.selectById(ContactusId);
        ci.setContactInfoId(ContactusId);
        ci.setIsEnable(true);
        if(replyContent.equals("")){
            ci.setContactStatus(10240004);
        }else{
            ci.setReplyContent(replyContent);
        }
        
        
        return ContactusDao.update(ci);
    }

}
