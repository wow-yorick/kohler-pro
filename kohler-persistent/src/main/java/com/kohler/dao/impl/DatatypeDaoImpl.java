/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kohler.constants.DataTypeSQL;
import com.kohler.dao.DatatypeDao;
import com.kohler.entity.DatatypeEntity;

/**
 * Class Function Description
 * 
 * @author liuzhiyong
 * @Date 2014年9月25日
 */
@Repository
public class DatatypeDaoImpl extends BaseDaoImpl<DatatypeEntity> implements DatatypeDao {


    /**
     * 从数据库中取得对应的列表数据
     * @param String DataTypeName
     * @author liuzhiyong
     * Date 2014年10月22日
     * @version
     */
    @Override
    public List<Map<String, Object>> listDataType(String DataTypeName) {
        List<Object> params = new ArrayList<Object>();
        params.add(DataTypeName);
        return selectByConditionWithMap(DataTypeSQL.SELECT_DATATYPE_ALL_DATATYPE, params);
    }
    
    /**
     * 从数据库中获取列表数据的个数
     * @param String dataTypeName	用于筛选的字符串
     * @author liuzhiyong
     * Date 2014年10月22日
     * @version
     */
    @Override
    public Integer selectDataTypeCount(String dataTypeName) {
        List<Object> params = new ArrayList<Object>();
        params.add(dataTypeName);
        return this.selectCountByCondition(DataTypeSQL.SELECT_COUNT_DATATYPE_ALL_DATATYPE, params);
    }

    /**
     * 删除datatypeId中包含的id对应的数据
     * @param String datatypeId  被选中的数据id连接而成的字符串
     * @author liuzhiyong
     * Date 2014年10月22日
     * @version
     */
    public Integer deleteDatatype(String datatypeId) {
        String ql = "(" + datatypeId + ")";
        return jdbcTemplate.update("UPDATE DATATYPE SET IS_ENABLE=0 where DATATYPE_ID IN " + ql);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<DatatypeEntity> getDataTypeByType(Integer type) {
        DatatypeEntity entity = new DatatypeEntity();
        entity.setDatatypeType(type);
        return this.selectByCondition(entity);
    }

}
