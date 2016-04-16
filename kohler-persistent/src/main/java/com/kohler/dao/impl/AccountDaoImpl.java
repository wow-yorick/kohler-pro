package com.kohler.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kohler.constants.AccountSQL;
import com.kohler.dao.AccountDao;
import com.kohler.entity.AccountEntity;

@Repository
public class AccountDaoImpl extends BaseDaoImpl<AccountEntity> implements AccountDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    /**
     * {@inheritDoc}
     */
    @Override
    public Integer account_edit(AccountEntity AccountEntity) {
        // TODO Auto-generated method stub
        List<Object> queryParams = new ArrayList<Object>();
        if(AccountEntity.getFreezeReason() != null){
           
            queryParams.add(AccountEntity.getStatus());
            queryParams.add(AccountEntity.getFreezeReason());
        }
        queryParams.add(AccountEntity.getAccountId());
        return jdbcTemplate.update(AccountSQL.Update_ACCOUNT, queryParams.toArray());
    }
    @Override
    public Integer account_edit_password(AccountEntity AccountEntity) {
        // TODO Auto-generated method stub
        List<Object> queryParams = new ArrayList<Object>();
        if(AccountEntity.getPassWord() != null){
            queryParams.add(AccountEntity.getPassWord());
        }
        queryParams.add(AccountEntity.getAccountId());
        return jdbcTemplate.update(AccountSQL.Update_ACCOUNT_PASSWORD, queryParams.toArray());
    }
        
}
