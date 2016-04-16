/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.dao;

import java.util.List;
import java.util.Map;

import com.kohler.entity.SysLogEntity;

/**
 * 
 * System User Operation Log
 *
 * @author kangmin_Qu
 * @Date 2014-10-17
 */
public interface SysLogDao extends BaseDao<SysLogEntity>{
    
    
    /**
     * Write Log For query Operation
     * @return
     * @author kangmin_Qu
     * Date 2014-10-17
     * @version
     */
    public List<Map<String,Object>>  querybyloglist(String str,List<Object> list);
    /**
     * Write Log For delete Operation
     * @return
     * @author kangmin_Qu
     * Date 2014-10-17
     * @version
     */
    public Integer deleteSysLog(Map<String,Object> map);
    /**
     * Write Log For Insert Operation
     * @return
     * @author kangmin_Qu
     * Date 2014-10-17
     * @version
     */
    public Integer insertLogForInsert(Integer objectId,String ObjectName);
    
    
    /**
     * Write Log For Update Operation
     * @return
     * @author kangmin_Qu
     * Date 2014-10-17
     * @version
     */
    public Integer insertLogForUpdate(Integer objectId,String ObjectName);
    
    
    /**
     * Write Log For Delete Operation
     * @return
     * @author kangmin_Qu
     * Date 2014-10-17
     * @version
     */
    public Integer insertLogForDelete(Integer objectId,String ObjectName);

}
