/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.dao;

import com.kohler.entity.ContactInfoEntity;

/**
 * ContactusDao Interface
 *
 * @author fujiajun
 * @Date 2014年10月9日
 */
public interface ContactusDao  extends BaseDao<ContactInfoEntity>{
       public Integer updateContactus(Integer id,String str);
}
