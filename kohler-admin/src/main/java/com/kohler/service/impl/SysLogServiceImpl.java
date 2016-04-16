package com.kohler.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohler.dao.SysLogDao;
import com.kohler.dao.utils.Pager;
import com.kohler.entity.SysLogEntity;
import com.kohler.service.SysLogService;
@Service
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    private SysLogDao syslogDao;
    public Pager<Map<String,Object>> GetbyRoleList(Pager<Map<String,Object>> pager, Map<String, Object> map) {
        // TODO Auto-generated method stub
        String str[]=new String[3];
        String s,where="";
        StringBuilder querySql,countSql;
        str[0]="operation";
        str[1]="sdate";
        str[2]="edate";
        int totalCount,pageCount,index=0;
        List<Object> queryParams = new ArrayList<Object>();
        querySql = new StringBuilder("SELECT sl.*,su.USER_NAME FROM SYS_LOG as sl left join SYS_USER su on(sl.SYS_USER_ID = su.SYS_USER_ID and su.IS_ENABLE=1) WHERE  1=1"); 
        countSql = new StringBuilder("SELECT count(*) FROM SYS_LOG as sl  WHERE  1=1");
        for(int i=0;i<str.length;i++){
            if(map.get(str[i]) != null ){
                s= map.get(str[i]).toString();
                if(!"".equals(s)){
                    if(i == 0){
                        where="  and sl.OPERATION = ? ";
                    }else if(i == 1){
                        where="  and sl.OPERATION_TIME >= ? ";
                    }else{
                        where="  and sl.OPERATION_TIME <= ? ";
                    }
                    querySql.append(where);
                    countSql.append(where);
                    queryParams.add(s); 
                }
            }
        }
        
        totalCount = syslogDao.selectCountByCondition(countSql.toString(), queryParams);
        if (totalCount % pager.getPageSize() == 0) {
            pageCount = totalCount / pager.getPageSize();
        } else {
            pageCount = totalCount / pager.getPageSize() + 1;
        }
        index = (pager.getCurrentPage() - 1) * pager.getPageSize();
        querySql.append(" limit ?,?");
        queryParams.add(index);
        queryParams.add(pager.getPageSize());
        List<Map<String,Object>> list = syslogDao.querybyloglist(querySql.toString(), queryParams);
        pager.setStartRow(index);
        pager.setDatas(list);
        pager.setTotalRecord(totalCount);
        pager.setTotalPage(pageCount);
        return pager;
    }
    @Override
    public Integer deleteSyslogs(Map<String,Object> map) {
        // TODO Auto-generated method stub
        return syslogDao.deleteSysLog(map);
    }

}
