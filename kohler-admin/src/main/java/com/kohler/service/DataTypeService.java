/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.service;


import com.kohler.dao.utils.Pager;
import com.kohler.entity.DatatypeEntity;
import com.kohler.entity.MasterdataEntity;


/**
 * dataType Interface
 * 
 * @author liuzhiyong
 * @Date 2014年10月13日
 */
public interface DataTypeService {

    /**
     * 用于插入数据的方法
     * @param DatatypeEntity dataType	封装为DatatypeEntity类型的数据
     * @return
     * @author liuzhiyong Date 2014年10月13日
     * @version
     */
    public int addDataType(DatatypeEntity dataType);
    
    /**
     * 用于修改数据的方法
     * @param DatatypeEntity dataType	封装为DatatypeEntity类型的数据
     * @return
     * @author liuzhiyong Date 2014年10月13日
     * @version
     */
    public int editDataType(DatatypeEntity dataType);
    
    /**
     * 获取对应的列表数据
     * @param String dataTypeName  模糊查询的name名称
     * @param Pager<DatatypeEntity> pager
     * @return
     * @author liuzhiyong Date 2014年10月13日
     * @version
     */
    public Pager<DatatypeEntity> listDataType(Pager<DatatypeEntity> pager, String dataTypeName);

    
    /**
     * 获取对应Id的DatatypeEntity数据
     * @param Integer dataTypeId  列表中选中的列表数据
     * @return
     * @author liuzhiyong
     * Date 2014年10月13日
     * @version
     */
    public DatatypeEntity getDataTypeById(Integer dataTypeId);

    
    
    /**
     * 删除选中的数据
     * @param String datatypeId 选中的列表数据id连接而成的字符串
     * @author liuzhiyong
     * Date 2014年10月13日
     * @version
     */
    public Integer deletedataType(String datatypeId);
    
    /**
     * 获取datatypeType的下拉框数据
     * @author liuzhiyong
     * Date 2014年10月22日
     * @version
     */
    public Pager<MasterdataEntity> getMasterData();
    
    
    public Integer existdatatypeName(String datatypeName);
    
    public Integer existEditdatatypeName(String datatypeName,String datatypeId);
}
