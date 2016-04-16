package com.kohler.service;

import java.util.List;
import java.util.Map;

import com.kohler.dao.utils.Pager;

public interface UserfolderService {
    /**
     * 获取用户文件夹列表
     * @param pager
     * @param Type
     * @param UserType
     * @return
     * @author Administrator
     * Date 2014年10月23日
     * @version
     */
    public Pager<Map<String, Object>> getUserfolderList(Pager<Map<String, Object>> pager, Integer Type,Integer UserType);
    
    /**
     * 删除用户文件夹
     * @param UserfolderIdArr
     * @author Administrator
     * Date 2014年10月23日
     * @version
     */
    public void deleteUserfolder(Integer[] UserfolderIdArr);
    
    /**
     * 用户文件夹搜索条件
     * @param UserfolderIdArr
     * @author Administrator
     * Date 2014年10月23日
     * @version
     */
    public List<Map<String,Object>> getselectlist(int type);


}
