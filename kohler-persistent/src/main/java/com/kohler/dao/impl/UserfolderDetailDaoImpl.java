package com.kohler.dao.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.kohler.constants.AccountSQL;
import com.kohler.dao.UserfolderDetailDao;
import com.kohler.entity.AccountFolderDetailEntity;

@Repository
public class UserfolderDetailDaoImpl extends BaseDaoImpl<AccountFolderDetailEntity> implements UserfolderDetailDao {

    @Override
    public Integer delUserfolderDetail(Integer[] ids) {
        // TODO Auto-generated method stub
        StringBuilder sb=new StringBuilder("UPDATE ACCOUNT_FOLDER_DETAIL SET IS_ENABLE=0 WHERE ACCOUNT_FOLDER_DETAIL_ID IN ( ");
        List<Object> list=new LinkedList<Object>();
        for(int i=0,j=ids.length;i<j;i++){
            if(i<1){
                sb.append("?");
            }else{
                sb.append(",?");
            }
            list.add(ids[i].intValue());
        }   
        sb.append(")");
        return jdbcTemplate.update(sb.toString(), list.toArray());
    }
}
