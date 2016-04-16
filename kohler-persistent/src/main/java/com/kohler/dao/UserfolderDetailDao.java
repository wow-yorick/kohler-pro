package com.kohler.dao;

import com.kohler.entity.AccountFolderDetailEntity;


public interface UserfolderDetailDao  extends BaseDao<AccountFolderDetailEntity>{
       public Integer delUserfolderDetail(Integer[] ids);
}
