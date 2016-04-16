/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kohler.bean.ConfPrepareData;
import com.kohler.constants.CommonConstants;
import com.kohler.entity.SiteSettingEntity;

/**
 * xml元素基础方法集
 *
 * @author Administrator
 * @Date 2014年10月30日
 */
@Component
public class XmlElementDefineMath {

    public static Map<String,Object> xmlALGMap = new HashMap<String, Object>();//xml算法
    
    public static Map<String, Object> xmlParamMap = new HashMap<String, Object>();//参数对应实际值
    
    private static Map<String, String> urlsMap = new HashMap<String, String>();
    
    @Autowired
    private BaseCommon baseCommon;

    
    /**
     * 私有属性获取
     * @param dataMap
     * @return
     * @author Administrator
     * Date 2014年10月31日
     * @version
     */
    @SuppressWarnings("unchecked")
    public Map<String,Object> _getPrivateAttrInfo(Map<String,Object> dataMap) {
        Map<String,Object> attrInfo = new HashMap<String, Object>();
        if(dataMap.containsKey("_privateInfo")) {
            Map<String,Object> _privateInfo = (Map<String, Object>) dataMap.get("_privateInfo");//数据源的私有信息
            if(_privateInfo.containsKey("_field")) {
                List<Map<String, Object>> filedList = (List<Map<String, Object>>) _privateInfo.get("_field");//字段列表
                attrInfo.put("_field", filedList);
                
            }
            if(_privateInfo.containsKey("_attribute")) {
                Map<String,Object> _attribute = (Map<String, Object>) _privateInfo.get("_attribute");//属性
                attrInfo.put("_attribute", _attribute);
            }
            if(_privateInfo.containsKey("_elemtName")) {
                String _elemtName = (String) _privateInfo.get("_elemtName");//属性
                attrInfo.put("_elemtName", _elemtName);
            }
        }
        
        return attrInfo;
        
    }
    
    /**
     * 匹配一些变量@XX or @@XX
     * @param str
     * @author Administrator
     * Date 2014年10月30日
     * @version
     */
    public static  String patternCompile(String str) {
        Pattern p = Pattern.compile("@+(\\w*)");
        Matcher m = p.matcher(str);
        while (m.find()) {
            Object paramVal = XmlElementDefineMath.xmlParamMap.get(m.group(0));
            if(paramVal != null) {
                str = str.replace(m.group(0),"'"+paramVal.toString()+"'");                
            }
        }
        return str;
    }
    
    /**
     * 匹配变量替换为? 生成参数列表
     * @param brokenSql
     * @param params
     * @author Administrator
     * Date 2014年12月29日
     * @version
     */
    public static String patternCompileSqlAndParams(String brokenSql,List<Object> params) {
        Pattern p = Pattern.compile("@+(\\w*)");
        Matcher m = p.matcher(brokenSql);
        while (m.find()) {
            Object paramVal = XmlElementDefineMath.xmlParamMap.get(m.group(0));
            if(paramVal != null) {
                brokenSql = brokenSql.replace(m.group(0),"?");
                params.add(paramVal);
            }
        }
        
        return brokenSql;
    }
    
    
    /**
     * xml中传入参数值的对应Map
     * @return
     * @author Administrator
     * Date 2014年10月30日
     * @version
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> parseXmlParams(Map<String,Object> ALGmap, ConfPrepareData conf) {
        Map<String, Object> paramsMap = new HashMap<String, Object>();//参数集容器

        Map<String,Object> dataPrivateAttr = (Map<String, Object>) ALGmap.get("_privateInfo");//数据源的私有信息
        if(dataPrivateAttr.get("_elemtName").toString().toLowerCase() == "page") {
            Map<String, Object> _paramsMap = (Map<String, Object>) dataPrivateAttr.get("_attribute");
            String[] paramArr = _paramsMap.get("param").toString().split(",");
            paramsMap.put(paramArr[0], conf.getDataId());
            paramsMap.put(paramArr[1], conf.getWebsitePlatform());
            paramsMap.put(paramArr[2], conf.getLang());
            paramsMap.put("xmlName", _paramsMap.get("name"));
        }

        return paramsMap;
    }
    
    
    /**
     * 多数据别名转换
     * @param _fieldList
     * @param dataMapList
     * @return
     * @author Administrator
     * Date 2014年11月5日
     * @version
     */
    public List<Map<String, Object>> putFiledMulti(List<Map<String, Object>> _fieldList, List<Map<String, Object>> dataMapList) {
        List<Map<String, Object>> resultData = new ArrayList<Map<String,Object>>();
        for(Map<String, Object> _dataMap : dataMapList) {
            Map<String,Object> _tempMap = singleDataName2Value(_fieldList, _dataMap);
            resultData.add(_tempMap);
        }
        return resultData;
    }
    
    /**
     * 单数据map 别名转换
     * @param _fieldList
     * @param dataMap
     * @return
     * @author Administrator
     * Date 2014年11月5日
     * @version
     */
    private Map<String,Object> singleDataName2Value(List<Map<String, Object>> _fieldList, Map<String, Object> dataMap) {
        Map<String,Object> _tempMap = new HashMap<String, Object>();
        for(Map<String, Object> _field : _fieldList) {
            if(xmlFieldVerifyOk(_field) && dataMap.containsKey(_field.get("fieldName"))) {
                _tempMap.put(_field.get("name").toString(), dataMap.get(_field.get("fieldName")));
            }
        }
        return _tempMap;
    }
    
    /**
     * 验证xml中field必须属性是否存在
     * @param field
     * @return
     * @author Administrator
     * Date 2014年11月5日
     * @version
     */
    public Boolean xmlFieldVerifyOk(Map<String,Object> field) {
        if(field != null && field.containsKey("fieldName") && field.get("fieldName") != null && field.containsKey("name") && field.containsKey("name") ) {
            return true;
        }else {
            return false;
        }
    }
    
    /**
     * 获取基础url路径(单例)
     * @param conf
     * @return
     * @author Administrator
     * Date 2014年12月16日
     * @version
     */
    public Map<String, String> getBasicUrls(ConfPrepareData conf) {
        if(urlsMap.size() > 0) {
            return urlsMap;
        }

        SiteSettingEntity siteSettingEntity =baseCommon.getSitePlatformSet(conf);
        String baseUrl = siteSettingEntity.getSiteDomainName();
        //静态文件服务
        SiteSettingEntity imageServer = baseCommon.getSiteSetting(CommonConstants.IMAGE_PLATFORM);
        String imgBaseUrl = imageServer.getSiteDomainName();
        
        SiteSettingEntity fileServer = baseCommon.getSiteSetting(CommonConstants.FILE_PLATFORM);
        String fileBaseUrl = fileServer.getSiteDomainName();
        
        urlsMap.put("base_url",baseUrl);
        urlsMap.put("images_url", imgBaseUrl);
        urlsMap.put("files_url", fileBaseUrl);
        return urlsMap;
    }
}
