package com.kohler.dao.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;

import com.kohler.dao.SysLogDao;
import com.kohler.entity.SysLogEntity;
import com.kohler.entity.SysUserEntity;
import com.kohler.util.SysContent;
/**
 * 
 * SysLog Dao Impl
 *
 * @author sana
 * @Date 2014年12月4日
 */
@Repository
public class SysLogDaoImpl extends BaseDaoImpl<SysLogEntity> implements SysLogDao {

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer insertLogForInsert(Integer objectId,String ObjectName) {
        
        //封装LOG日志对象
        SysLogEntity log = new SysLogEntity();
        log.setIsEnable(true);
        log.setObjectId(objectId);
        log.setOperation("INSERT"); //插入操作
        log.setOperationObject(ObjectName);
        HttpSession session=SysContent.getSession();
        if(session != null){
            SysUserEntity user=(SysUserEntity)session.getAttribute("sysUser");
            log.setSysUserId(user.getSysUserId());
        }
        return this.insert(log);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer insertLogForUpdate(Integer objectId,String ObjectName) {
        
        //封装LOG日志对象
        SysLogEntity log = new SysLogEntity();
        log.setIsEnable(true);
        log.setObjectId(objectId);
        log.setOperation("UPDATE"); //插入操作
        log.setOperationObject(ObjectName);
        HttpSession session=SysContent.getSession();
        if(session != null){
            SysUserEntity user=(SysUserEntity)session.getAttribute("sysUser");
            log.setSysUserId(user.getSysUserId());
        }
        
        return this.insert(log);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer insertLogForDelete(Integer objectId,String ObjectName) {
        
        //封装LOG日志对象
        SysLogEntity log = new SysLogEntity();
        log.setIsEnable(true);
        log.setObjectId(objectId);
        log.setOperation("DELETE"); //插入操作
        log.setOperationObject(ObjectName);
        HttpSession session=SysContent.getSession();
        if(session != null){
            SysUserEntity user=(SysUserEntity)session.getAttribute("sysUser");
            log.setSysUserId(user.getSysUserId());
        }
        
        return this.insert(log);
    }

    @Override
    public List<Map<String, Object>> querybyloglist(String str,List<Object> list) {
        // TODO Auto-generated method stub
        return jdbcTemplate.queryForList(str, list.toArray());
    }

    @Override
    public Integer deleteSysLog(Map<String, Object> map) {
        // TODO Auto-generated method stub
        StringBuilder sb=new StringBuilder("DELETE FROM SYS_LOG WHERE 1=1   ");
        List<Object> array=new LinkedList<Object>();
        String[] strp=null;
        String where = "",s="";
        int size=0,c=0,i=0;
        if(map.get("type").toString().equals("1")){
            sb.append(" and SYS_LOG_ID in (");
            strp=map.get("ids").toString().split(",");
            size=strp.length;
            for(i=0;i<size;i++){
                if(i < 1){
                    sb.append("?"); 
                }else{
                    sb.append(",?"); 
                }
                array.add(strp[i].toString());
            }
            sb.append(")");
        }else{
            strp=new String[]{"operation","sdate","edate"};
            c=strp.length;
            for(i=0;i<c;i++){
                if(map.get(strp[i]) != null ){
                    s= map.get(strp[i]).toString();
                    if(!"".equals(s)){
                        switch(i){
                            case 0:
                                where="  and OPERATION = ? ";
                                break;
                            case 1:
                                where="  and OPERATION_TIME >= ? ";
                                break;
                            case 2:
                                where="  and OPERATION_TIME < ? ";
                                break;
                        }
                        array.add(s.toString());
                        sb.append(where);
                        size++;
                    }
                }
            }
        }
        return jdbcTemplate.update(sb.toString(), array.toArray());
    }
	

}
