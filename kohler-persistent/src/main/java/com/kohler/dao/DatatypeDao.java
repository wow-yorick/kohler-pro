/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.dao;

import java.util.List;
import java.util.Map;

import com.kohler.entity.DatatypeEntity;

/**
 * SectionDao Interface
 *
 * @author liuzhiyong
 * @Date 2014年9月25日
 */
public interface DatatypeDao extends BaseDao<DatatypeEntity>{

	/**
	 * 从数据库中取得对应的列表数据
     * @param String DataTypeName
     * @author liuzhiyong
     * Date 2014年10月22日
     * @version
     */
    public List<Map<String, Object>> listDataType(String DataTypeName);
    
    
    /**
     * 从数据库中获取列表数据的个数
     * @param String dataTypeName 用于筛选的字符串
     * @author liuzhiyong
     * Date 2014年10月22日
     * @version
     */
    public Integer selectDataTypeCount(String dataTypeName);
 
    
    /**
     * 删除datatypeId中包含的id对应的数据
     * @param String datatypeId  被选中的数据id连接而成的字符串
     * @author liuzhiyong
     * Date 2014年10月22日
     * @version
     */
    public Integer deleteDatatype(String datatypeId);
    
    
    
    /**
     * 根据Type类型获取DataType
     * @param type 1:Section 2:Page
     * @return
     * @author kangmin_Qu
     * Date 2014-10-21
     * @version
     */
    public List<DatatypeEntity> getDataTypeByType(Integer type);
    
}
