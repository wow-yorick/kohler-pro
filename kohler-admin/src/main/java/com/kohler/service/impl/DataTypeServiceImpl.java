/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kohler.constants.CommonConstants;
import com.kohler.constants.DataTypeSQL;
import com.kohler.dao.DatatypeDao;
import com.kohler.dao.MasterDataDao;
import com.kohler.dao.SysLogDao;
import com.kohler.dao.utils.Pager;
import com.kohler.entity.DatatypeEntity;
import com.kohler.service.DataTypeService;
import com.kohler.entity.MasterdataEntity;
import com.kohler.framework.util.ParseXml;


/**
 * Section Interface Impl
 *
 * @author liuzhiyong
 * @Date 2014年9月25日
 */
@Service
public class DataTypeServiceImpl implements DataTypeService {

	//DatatypeDao类的
    @Autowired 
    private DatatypeDao dataTypeDao;
    
    @Autowired 
    private MasterDataDao masterDataDao;
    
    @Autowired 
    private SysLogDao sysLogDao;

    /**
     * 用于插入数据的方法
     * @param DatatypeEntity dataType	封装为DatatypeEntity类型的数据
     * @return
     * @author liuzhiyong Date 2014年10月13日
     * @version
     */
    @Transactional
    public int addDataType(DatatypeEntity dataType)
    {
    	//插入DataType
    	Integer dataTypeId = dataTypeDao.insert(dataType);
    	DatatypeEntity d = dataTypeDao.selectById(dataTypeId);
    	List<Object> list = new ArrayList<Object>();
        list.add(ParseXml.parseListXml(d.getListDefinationXML()));
        list.add(ParseXml.parseEditXml(d.getEditDefinationXML()));
        CommonConstants.DATATYPE_DEFINATION_XML_HASHMAP.put(d.getDatatypeId().toString(), list);
    	
    	sysLogDao.insertLogForInsert(dataTypeId, "DATATYPE");
        return dataTypeId;
    }
    
    /**
     * 用于修改数据的方法
     * @param DatatypeEntity dataType	封装为DatatypeEntity类型的数据
     * @return
     * @author liuzhiyong Date 2014年10月13日
     * @version
     */
    @Transactional
    public int editDataType(DatatypeEntity dataType)
    {
    	Integer affectRow = dataTypeDao.update(dataType);
    	DatatypeEntity d = dataTypeDao.selectById(dataType.getDatatypeId());
    	List<Object> list = new ArrayList<Object>();
    	list.add(ParseXml.parseListXml(d.getListDefinationXML()));
    	list.add(ParseXml.parseEditXml(d.getEditDefinationXML()));
    	CommonConstants.DATATYPE_DEFINATION_XML_HASHMAP.put(d.getDatatypeId().toString(), list);
    	sysLogDao.insertLogForUpdate(dataType.getDatatypeId(), "DATATYPE");
    	return affectRow;
    }
    
    /**
     * 获取对应的列表数据
     * @param String dataTypeName  模糊查询的name名称
     * @param Pager<DatatypeEntity> pager
     * @return
     * @author liuzhiyong Date 2014年10月13日
     * @version
     */
    public Pager<DatatypeEntity> listDataType(Pager<DatatypeEntity> pager, String dataTypeName)
    {
    	List<Object> params = new ArrayList<Object>();
        int pageCount = 0;
        int startRow = (pager.getCurrentPage() - 1) * pager.getPageSize();
        int totalCount = dataTypeDao.selectDataTypeCount(dataTypeName);
        //通过判断总数与每页数据最大个数的余数是否为0算出总页数
        if (totalCount % pager.getPageSize() == 0) {
            pageCount = totalCount / pager.getPageSize();
        } else {
            pageCount = totalCount / pager.getPageSize() + 1;
        }
        params.add(dataTypeName);
        params.add(startRow);
        params.add(pager.getPageSize());   
        List<DatatypeEntity> list = dataTypeDao.selectByCondition(DataTypeSQL.SELECT_DATATYPE_ALL_DATATYPE_WITH_MAP,params);
        
        pager.setStartRow(startRow);
        pager.setDatas(list);
        pager.setTotalRecord(totalCount);
        pager.setTotalPage(pageCount);
        
    	return pager;
    }
    
    
    /**
     * 获取对应Id的DatatypeEntity数据
     * @param Integer dataTypeId  列表中选中的列表数据
     * @return
     * @author liuzhiyong
     * Date 2014年10月13日
     * @version
     */
    public DatatypeEntity getDataTypeById(Integer dataTypeId)
    {	
    	return dataTypeDao.selectById(dataTypeId);
    }
    
    /**
     * 删除选中的数据
     * @param String datatypeId 选中的列表数据id连接而成的字符串
     * @author liuzhiyong
     * Date 2014年10月13日
     * @version
     */
    @Transactional
    public Integer deletedataType(String datatypeId)
    {
    	Integer issuccess=null;
    	issuccess = dataTypeDao.deleteDatatype(datatypeId);
    	CommonConstants.DATATYPE_DEFINATION_XML_HASHMAP.remove(datatypeId);
    	String s[]=datatypeId.split(",");
        for (String x : s) {
        	//判断id是否为空
            if(x != null ){
                int i=Integer.parseInt(x);
                sysLogDao.insertLogForDelete(i, "DATATYPE");
            }
        }
    	
    	return issuccess;
    }
    
    /**
     * 获取datatypeType的下拉框数据
     * @author liuzhiyong
     * Date 2014年10月22日
     * @version
     */
    public Pager<MasterdataEntity> getMasterData()
    {
    	Pager<MasterdataEntity> pager = new Pager<MasterdataEntity>();
    	List<Object> params = new ArrayList<Object>();
    	params.add(1);
    	List<MasterdataEntity> list = masterDataDao.selectByCondition(DataTypeSQL.SELECT_MSTERDATA,params);
    	pager.setDatas(list);
    	return pager;
    }
    
    
    @Override
    public Integer existdatatypeName(String datatypeName) {
    	List<Object> params = new ArrayList<Object>(); 
    	params.add(datatypeName);
    	Integer row= dataTypeDao.selectCountByCondition(" select count(*) from DATATYPE WHERE DATATYPE_NAME = ? and IS_ENABLE =1", params);
    	return row;
    }
    
    public Integer existEditdatatypeName(String datatypeName,String datatypeId)
    {
    	List<Object> params = new ArrayList<Object>(); 
    	params.add(datatypeName);
    	params.add(datatypeId);
    	Integer row= dataTypeDao.selectCountByCondition(" select count(*) from DATATYPE WHERE DATATYPE_NAME = ? AND IS_ENABLE =1 AND DATATYPE_ID != ?", params);
    	return row;
    }
}
