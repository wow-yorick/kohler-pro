/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.kohler.entity.AccountEntity;


/**
 * 
 * Class Function Description
 *
 * @author fujiajun
 * @Date 2014年11月10日
 */
public interface AccountDao extends BaseDao<AccountEntity>{
   
    public Integer account_edit(AccountEntity AccountEntity);
    
    public Integer account_edit_password(AccountEntity AccountEntity);

}
