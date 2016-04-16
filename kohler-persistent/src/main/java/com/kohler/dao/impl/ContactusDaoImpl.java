/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.dao.impl;
import org.springframework.stereotype.Repository;

import com.kohler.dao.ContactusDao;
import com.kohler.entity.ContactInfoEntity;
/**
 * Class Function Description
 * 
 * @author fujiajun
 * @Date 2014年9月25日
 */
@Repository
public class ContactusDaoImpl extends BaseDaoImpl<ContactInfoEntity> implements ContactusDao {

    @Override
    public Integer updateContactus(Integer id, String str) {
        // TODO Auto-generated method stub
        return null;
    }

}
