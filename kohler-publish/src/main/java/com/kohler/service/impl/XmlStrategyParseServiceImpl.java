/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohler.bean.ConfPrepareData;
import com.kohler.dao.PublishDataPrepareDao;
import com.kohler.exception.DataException;
import com.kohler.service.SpecialFieldParseService;
import com.kohler.service.XMLFuncIFactoryService;
import com.kohler.service.XmlStrategyParseService;
import com.kohler.service.base.BaseCommon;
import com.kohler.service.base.XmlElementDefineMath;
import com.kohler.service.field.FieldPaseFactory;

/**
 * XML解析策略
 *
 * @author Administrator
 * @Date 2014年10月28日
 */
@Service
public class XmlStrategyParseServiceImpl implements XmlStrategyParseService {  
    
    private final static Logger logger = Logger.getLogger(XmlStrategyParseServiceImpl.class);
    
    @Autowired
    private PublishDataPrepareDao publishDataPrepareDao;
    
    @Autowired
    private FieldPaseFactory fieldPaseFactory;
    
    @Autowired
    private XMLFuncIFactoryService xmlFuncIFactoryService;
    
    @Autowired
    private XmlElementDefineMath xmlElementDefineMath;
    
    @Autowired
    private BaseCommon baseCommon;
    
    /**
     * {@inheritDoc}
     * @throws DataException 
     */
    @SuppressWarnings("unchecked")
    public Map<String,Object> resolver(ConfPrepareData conf) throws DataException {
        
        Map<String,Object> resultMap = new HashMap<String, Object>();//结果集
        Map<String,Object> ALGmap = (Map<String, Object>) XmlElementDefineMath.xmlALGMap.get(conf.getXMLFileName());//获取对应的xml map
        ALGmap = (Map<String, Object>) ALGmap.get("root");//算法集
        
        XmlElementDefineMath.xmlParamMap = xmlElementDefineMath.parseXmlParams(ALGmap, conf);//xml参数替换
        Map<String,Object> _dataXml = xmlALGMapTraversal(ALGmap, resultMap, conf); //xml算法转换为结果
        resultMap.putAll(_dataXml);
        
        
        Map<String,Object> _funcXml = funcDataTraversal(ALGmap, resultMap, conf); //xml算法--内置方法转换为结果
        resultMap.putAll(_funcXml);
        //结果集中增加BasicUrls
        Map<String, String> urls = xmlElementDefineMath.getBasicUrls(conf);
        resultMap.putAll(urls);
        
        return resultMap;
        
        
    }
    
    @SuppressWarnings("unchecked")
    private Map<String,Object> funcDataTraversal(Map<String,Object> xmlALGMap,Map<String,Object> resultMap,ConfPrepareData conf) throws DataException {
        for (Map.Entry<String, Object> dataEntry : xmlALGMap.entrySet()) {
           
            //跳过属性字段
            String mapKey = dataEntry.getKey();
            if("_privateInfo".equals(mapKey)) {
                continue;
            }
            
            //获取属性
            Map<String, Object> dataMap = (Map<String, Object>) dataEntry.getValue();
            Map<String,Object> _privateInfo = (Map<String, Object>) dataMap.get("_privateInfo");
            
            //跳过内置方法
            String _elemtName = (String) _privateInfo.get("_elemtName");
            if(!_elemtName.equals("funcdata")) {
                continue;
            }
            
            //处理内置方法数据源
            Map<String, Object> dataALGMap = (Map<String, Object>) dataEntry.getValue();
            resultMap = xmlFuncIFactoryService.funcFactory(resultMap, dataALGMap, conf);
        }
        return resultMap;
    }
    
 
    /**
     * 一般数据源递归解析
     * @param xmlALGMap
     * @param resultMap
     * @return
     * @author Administrator
     * Date 2014年11月10日
     * @version
     */
    @SuppressWarnings("unchecked")
    private Map<String,Object> xmlALGMapTraversal(Map<String,Object> xmlALGMap,Map<String,Object> resultMap, ConfPrepareData conf) {
        
        for (Map.Entry<String, Object> dataEntry : xmlALGMap.entrySet()) {
            
            //跳过属性字段
            String mapKey = dataEntry.getKey();
            if(mapKey == "_privateInfo") {
                continue;
            }
            //获取属性
            Map<String, Object> dataMap = (Map<String, Object>) dataEntry.getValue();
            Map<String,Object> _privateInfo = (Map<String, Object>) dataMap.get("_privateInfo");
            Map<String,Object> _attribute = (Map<String, Object>) _privateInfo.get("_attribute");
            
            //跳过内置方法
            String _elemtName = (String) _privateInfo.get("_elemtName");
            if(_elemtName.equals("funcdata")) {
                continue;
            }
            //解析获取资源
            List<Map<String, Object>> dataSource = parseDataSource(dataMap, conf);
            resultMap.put(mapKey, dataSource);
            // 递归获取子资源
            if(dataMap != null && dataMap.size() > 1) {//排除_privateInfo 所以要大于1
                if(dataSource != null && dataSource.size() > 0) {
                    List<Map<String,Object>> _tempMultiData = new ArrayList<Map<String,Object>>();
                    for(int i = 0; i < dataSource.size(); i ++) {
                        
                        //结果保存到参数表
                        if(_attribute.containsKey("keyName")) {
                            String keyName = _attribute.get("keyName").toString();
                            XmlElementDefineMath.xmlParamMap.put("@@"+keyName, dataSource.get(i).get(keyName));
                        }
                        Map<String,Object> _tempData = xmlALGMapTraversal(dataMap, dataSource.get(i), conf);
                        _tempMultiData.add(_tempData);
                    }
                    resultMap.put(mapKey, _tempMultiData);
                }else {//查询结果为空时
                    continue;
//                    Map<String,Object> tempMap = new HashMap<String, Object>();
//                    //结果保存到参数表
//                    if(_attribute.containsKey("keyName")) {
//                        String keyName = _attribute.get("keyName").toString();
//                        XmlElementDefineMath.xmlParamMap.put("@@"+keyName, 0);
//                    }
//                    
//                    Map<String,Object> _tempData = xmlALGMapTraversal(dataMap, tempMap, conf);
//                    resultMap.put(mapKey, _tempData);
                }
            }
            else {//TODO 有待测试
                resultMap.put(mapKey, dataSource);
            }
        }
        
        return resultMap;
    }
    
    
    /**
     * 解析数据源
     * @param dataMap
     * @return
     * @author Administrator
     * Date 2014年10月31日
     * @version
     */
    @SuppressWarnings("unchecked")
    private List<Map<String,Object>> parseDataSource(Map<String, Object> dataMap, ConfPrepareData conf) {
        
        List<Map<String,Object>> dataSource = new ArrayList<Map<String,Object>>();//存放结果
        
        try {
            Map<String,Object> _privateAttrInfo = xmlElementDefineMath._getPrivateAttrInfo(dataMap);//获取属性
            String _elemtName = (String) _privateAttrInfo.get("_elemtName");//获取元素名称
            
            if(_privateAttrInfo != null ) {//属性不为空
                if(_elemtName.toLowerCase().equals("data")) {//TODO 为以后做准备因此分开写  单条数据源和多条数据源等同看待
                    
                    dataSource = parseMultidata(dataMap, conf);
                    
                } else if(_elemtName.toLowerCase().equals("multidata")) {
                    
                    dataSource = parseMultidata(dataMap, conf);
                    
                }
            }
            
            //处理特殊意义FIELD字段
            List<Map<String, Object>> _fieldList = (List<Map<String, Object>>) _privateAttrInfo.get("_field");
            if(dataSource != null && dataSource.size() > 0 && _fieldList != null && _fieldList.size() > 0) {
                for(Map<String, Object> _filedMap : _fieldList) {
                    String fieldType = _filedMap.get("fieldType").toString().toLowerCase();
                    if(!"physicalvalue".equals(fieldType)) {//不为标准字段时调用对应组件
                        for(int ind = 0; ind < dataSource.size(); ind++) {
                            String fieldName = (String)_filedMap.get("name");
                            String fieldVal = "";
                            Map<String, Object> _tempDataSourceSig = dataSource.get(ind);
                            try {
                                SpecialFieldParseService specialFieldParseService = (SpecialFieldParseService) fieldPaseFactory.createObject(fieldType);
                                fieldVal = specialFieldParseService.parse(_tempDataSourceSig, _filedMap, conf);
                                _tempDataSourceSig.put(fieldName, fieldVal);
                            } catch (Exception e) {
                                logger.debug("special field parse fail---["+fieldType+"]");
                                //数据源中插入解析结果
                                _tempDataSourceSig.put(fieldName, fieldVal);
                                continue;
                            }
                        }
                    }
                    
                }
            }
            
        } catch (Exception e) {
            logger.debug("parse data source fail",e);
        }
        
        return dataSource;
    }

    
    
    /**
     * 处理数据源，对需要映射的数据进行映射
     * @return
     * @author Administrator
     * Date 2014年10月31日
     * @version
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> parseMultidata(Map<String,Object> dataMap, ConfPrepareData conf) {
        
        List<Map<String, Object>> dataRet = new ArrayList<Map<String, Object>>();//结果集容器
        
        dataRet = parseSqlTypeMulit(dataMap);//获取数据结果
        
        //有MAP标签 如果有映射关系的先转换再返回
        Map<String,Object> _privateInfo =xmlElementDefineMath._getPrivateAttrInfo(dataMap);
        Map<String,Object> _attribute =  (Map<String, Object>) _privateInfo.get("_attribute");
        if(dataRet != null && dataRet.size() > 0 && _attribute.containsKey("contentType") && _attribute.get("contentType") != null) {
            String contentType = _attribute.get("contentType").toString();
            if(dataMap.containsKey(contentType) && dataMap.get(contentType) != null) {
                Map<String,Object> mapData = (Map<String, Object>) dataMap.get(contentType);
                dataRet = contentTypeIsMapTrans(mapData, dataRet, conf);
            }
        }
        
        return dataRet;

    }
    
    /**
     * 结果类型为map时需要转换
     * @param mapData
     * @param dataSource
     * @return
     * @author Administrator
     * Date 2014年11月12日
     * @version
     */
    @SuppressWarnings("unchecked")
    private List<Map<String,Object>> contentTypeIsMapTrans(Map<String,Object> mapData, List<Map<String, Object>> dataSource, ConfPrepareData conf) {
        List<List<Map<String,Object>>> _transData = new ArrayList<List<Map<String,Object>>>();//转换结果
        List<String> _tmpMarks = new ArrayList<String>();//判断是否是一组数据的标志位
        List<Map<String,Object>> _tmpData = new ArrayList<Map<String,Object>>();
        for(int i = 0; i < dataSource.size(); i++){
            String _tmpElet = dataSource.get(i).get("CONTENT_METADATA_ID").toString();
            if(!_tmpMarks.contains(_tmpElet)) {//不是一组就新建组,从第二组开始把上一组结果put进结果集
                if(i != 0) {//开始临界点
                    _transData.add(_tmpData); 
                }
                _tmpMarks.add(_tmpElet);
                _tmpData = new ArrayList<Map<String,Object>>();
            }
            _tmpData.add(dataSource.get(i));
            if(i == dataSource.size()-1) {//结束临界点
                _transData.add(_tmpData);
            }
        }
        
        List<Map<String,Object>> _tempResultMap = new ArrayList<Map<String,Object>>();
        for(List<Map<String,Object>> _tmpList : _transData) {
            Map<String,Object> _tempData = new HashMap<String, Object>();
            if(_tmpList.size() > 0) {//一级查询加入转换列表
                _tempData.putAll(_tmpList.get(0));
            }
            Map<String,Object> _tempTrans = baseCommon.dbDataListToMap(_tmpList, conf);
            _tempData.putAll(_tempTrans);
            _tempResultMap.add(_tempData);
        }
        
        Map<String,Object> _privateInfo =xmlElementDefineMath._getPrivateAttrInfo(mapData);
        List<Map<String, Object>> _filedList =  (List<Map<String, Object>>) _privateInfo.get("_field");
        
        List<Map<String, Object>> resultMap =  xmlElementDefineMath.putFiledMulti(_filedList,  _tempResultMap);
        
        return resultMap;
    }
    

    
    /**
     * 获取查询结果 
     * @param dataMap
     * @return
     * @author Administrator
     * Date 2014年10月31日
     * @version
     */
//    @SuppressWarnings("unchecked")
//    private List<Map<String,Object>> parseSqlTypeMulit(Map<String,Object> dataMap) {
//        List<Map<String,Object>> retData = new ArrayList<Map<String,Object>>();//结果集
//        //获取属性
//        Map<String,Object> _privateAttrInfo = xmlElementDefineMath._getPrivateAttrInfo(dataMap);
//        Map<String,Object> _attribute = (Map<String, Object>) _privateAttrInfo.get("_attribute");
//        
//        //获取解析结果
//        if(_privateAttrInfo != null && _attribute.containsKey("sqlResource")) {
//            retData = parseXmlsqlResourceMulti(dataMap);
//        } else {
//            retData = parseXmlFiledDataSourceMulti(dataMap);
//        }
//        
//        return retData;
//    }
    
    
    /**
     * sqlResource 多条解析
     * @param dataMap
     * @return
     * @author Administrator
     * Date 2014年10月31日
     * @version
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> parseSqlTypeMulit(Map<String,Object> dataMap) {
        
        List<Map<String, Object>> dataRet = new ArrayList<Map<String,Object>>();//结果集
        
        //获取属性
        Map<String,Object> _privateAttrInfo = xmlElementDefineMath._getPrivateAttrInfo(dataMap);
        Map<String,Object> _attribute = (Map<String, Object>) _privateAttrInfo.get("_attribute");
        //获取sql
        String brokenSql = "";
        if(_privateAttrInfo != null && _attribute.containsKey("sqlResource")) {
            brokenSql = sqlResource(dataMap);// 获取sql
        } else {
            brokenSql = filedDataSource(dataMap);//sql获取
        }
        
        //用List返回
        if(brokenSql != null && !"".equals(brokenSql)) {
            
            List<Object> params = new ArrayList<Object>();
            String retSql = XmlElementDefineMath.patternCompileSqlAndParams(brokenSql,params);
            List<Map<String, Object>> dataRetMulti = publishDataPrepareDao.selectByConditionWithMap(retSql, params); //查询sql
            
            if(dataRetMulti != null && dataRetMulti.size() > 0) {
                //获取字段定义
                List<Map<String, Object>> _fieldList = (List<Map<String, Object>>) _privateAttrInfo.get("_field");
                //别名转换
                for(Map<String,Object> _dataRet : dataRetMulti) {
                    Map<String,Object> singleDataRet = name2PhysicalValue(_dataRet, _fieldList);
                    dataRet.add(singleDataRet);
                }
            }
        }
        
        return dataRet;
    }
    
    /**
     * 自定义查询字段和键值对应
     * @param dataRet
     * @param _fieldList
     * @return
     * @author Administrator
     * Date 2014年10月31日
     * @version
     */
    private Map<String,Object> name2PhysicalValue(Map<String,Object> dataRet, List<Map<String, Object>> _fieldList) {
        
        //Map<String,Object> dataRetTrans = new HashMap<String, Object>();
        
        for(Map<String, Object> _filed : _fieldList) {
            Object mapKey = _filed.get("name");
            Object fieldName = _filed.get("fieldName");
            
            if(mapKey != null && fieldName != null) {
                String fieldNameStr = XmlElementDefineMath.patternCompile(fieldName.toString());
                dataRet.put(mapKey.toString(), dataRet.get(fieldNameStr.toString()));
            }
            
            //文件资源类型
//            String fieldType = (String) _filed.get("fieldType");
//            String sourceType = (String) _filed.get("fileAssetSource");
//            String externalFile = (String) _filed.get("fileAssetExternal");
//            String internalFile = (String) _filed.get("fileAssetInternal");
//            if(fieldType != null && "physicalfileasset".equals(fieldType.toLowerCase())) {
//                dataRet.put(sourceType, dataRet.get(sourceType));
//                dataRet.put(externalFile, dataRet.get(externalFile));
//                dataRet.put(internalFile, dataRet.get(internalFile));
//            }
        }
        return dataRet;
    }
    
    
    /**
     * XML字段定义数据源解析
     * @param dataMap
     * @return
     * @author Administrator
     * Date 2014年10月31日
     * @version
     */
//    private List<Map<String,Object>> parseXmlFiledDataSourceMulti(Map<String,Object> dataMap) {
//        List<Map<String,Object>> dataRet = new ArrayList<Map<String,Object>>();//结果集
//        
//        String sqlStr = filedDataSource(dataMap);//sql获取
//        
//        if(sqlStr != null && !"".equals(sqlStr)) {
//            dataRet = publishDataPrepareDao.selectByConditionWithMap(sqlStr, new ArrayList<Object>());//执行查询
//        }
//        
//        return dataRet;
//    }
    
    
    /**
     * sqlResource 完整sql
     * @param dataMap
     * @return
     * @author Administrator
     * Date 2014年10月31日
     * @version
     */
    @SuppressWarnings("unchecked")
    private String sqlResource(Map<String,Object> dataMap) {
        
        Map<String,Object> _privateAttrInfo = xmlElementDefineMath._getPrivateAttrInfo(dataMap);
        Map<String,Object> _attribute = (Map<String, Object>) _privateAttrInfo.get("_attribute");
        
        String sourceSql = _attribute.get("sqlResource").toString();
        //sourceSql = XmlElementDefineMath.patternCompile(sourceSql);//变量替换
        return sourceSql;
    }
    
    
    /**
     *组合式sql
     * @param dataMap
     * @return
     * @author Administrator
     * Date 2014年10月31日
     * @version
     */
    @SuppressWarnings("unchecked")
    private String filedDataSource(Map<String,Object> dataMap) {
        
        Map<String,Object> _privateAttrInfo = xmlElementDefineMath._getPrivateAttrInfo(dataMap);
        
        StringBuilder sqlFiled = new StringBuilder();
        List<Map<String, Object>> _fieldList = (List<Map<String, Object>>) _privateAttrInfo.get("_field");
        Map<String,Object> _attribute = (Map<String, Object>) _privateAttrInfo.get("_attribute");
        
        for(Map<String, Object> _filedMap : _fieldList) {
            
            if(_filedMap.containsKey("fieldName") && _filedMap.get("fieldName") != null){//由物理值类型限制改为实际物理字段
                String filedName = _filedMap.get("fieldName").toString();
                //查询语句结束后再做替换
                //filedName = XmlElementDefineMath.patternCompile(filedName) + " AS " + _filedMap.get("name").toString();
                sqlFiled.append(",");
                sqlFiled.append(filedName);
            }
            if(_filedMap.containsKey("fileAssetSource") && _filedMap.get("fileAssetSource") != null) {//文件来源
                String extFiledName = _filedMap.get("fileAssetSource").toString();
                sqlFiled.append(",");
                sqlFiled.append(extFiledName);
            }
            if(_filedMap.containsKey("fileAssetExternal") && _filedMap.get("fileAssetExternal") != null) {//外部文件
                String extFiledName = _filedMap.get("fileAssetExternal").toString();
                if(sqlFiled.indexOf(extFiledName) == -1) {//只加入一次
                    sqlFiled.append(",");
                    sqlFiled.append(extFiledName);
                }
            }
            if(_filedMap.containsKey("fileAssetInternal") && _filedMap.get("fileAssetInternal") != null) {//内部文件
                String extFiledName = _filedMap.get("fileAssetInternal").toString();
                sqlFiled.append(",");
                sqlFiled.append(extFiledName);
            }
            
        }
        
        //加入keyName的查询
        Object keyName = _attribute.get("keyName");
        if(keyName != null) {
            sqlFiled.append(",");
            sqlFiled.append(keyName.toString());
        }
        
        String sqlStr = "";//sql语句
        if(sqlFiled.length() > 0) {
            //String filter = XmlElementDefineMath.patternCompile(_attribute.get("filter").toString());//对过滤条件中的变量进行替换
            String filter = _attribute.get("filter").toString();
            sqlStr =  "SELECT " + sqlFiled.substring(1) +" FROM " +_attribute.get("tableName").toString()
                    +" WHERE 1=1 AND " +  filter;
            
        }
        return sqlStr;
    }
    


}
