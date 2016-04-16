package com.kohler.constants;

public class RoleSQL {
    public static final String SELECT_Role_LIST  = "SELECT  r.*,group_concat(rr.RIGHT_ID) as rid FROM ROLE  r "
                                                     + " inner join ROLE_RIGHT rr  on (r.ROLE_ID = ? and rr.ROLE_ID = r.ROLE_ID and rr.IS_ENABLE=1)    "
                                                     + " order by null  lIMIT 1";
    
}
