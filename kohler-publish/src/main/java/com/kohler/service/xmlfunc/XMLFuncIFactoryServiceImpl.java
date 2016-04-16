/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.xmlfunc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kohler.bean.ConfPrepareData;
import com.kohler.constants.CommonConstants;
import com.kohler.exception.DataException;
import com.kohler.service.BreadCrumbService;
import com.kohler.service.DataPrepareService;
import com.kohler.service.SameCommonAttrProductService;
import com.kohler.service.XMLFuncIFactoryService;
import com.kohler.service.base.XmlElementDefineMath;
import com.kohler.service.breadcrumb.BreadcrumbFactory;

/**
 * xml 函数工厂
 *
 * @author Administrator
 * @Date 2014年12月9日
 */
@Component
public class XMLFuncIFactoryServiceImpl implements XMLFuncIFactoryService {
    
    //private final static Logger logger = Logger.getLogger(XMLFuncIFactoryServiceImpl.class);
    
    @Autowired
    private XmlElementDefineMath xmlElementDefineMath;
    
    @Autowired
    private BreadcrumbFactory breadcrumbFactory;
    
    @Autowired
    private SameCommonAttrProductService commonAttrProductService;
    
    @Autowired
    private DataPrepareService dataPrepareService;
    

    /**
     * {@inheritDoc}
     * @throws DataException 
     */
    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> funcFactory(Map<String, Object> resultMap,
            Map<String, Object> dataALGMap , ConfPrepareData conf) throws DataException {
        
        Map<String,Object> _privateInfo =xmlElementDefineMath._getPrivateAttrInfo(dataALGMap);
        Map<String,Object> _attribute =  (Map<String, Object>) _privateInfo.get("_attribute");
        String _elemtName =  (String) _privateInfo.get("_elemtName");
        
        if(_elemtName.equals("funcdata") && _attribute.get("from") != null && _attribute.get("from").equals("getBreadcrumb")) {//面包屑
            
            BreadCrumbService breadCrumb = (BreadCrumbService) breadcrumbFactory.createObject(XmlElementDefineMath.xmlParamMap.get("xmlName").toString());
            List<Map<String,Object>> breadcrumb = breadCrumb.getBreadCrumb(conf);
            resultMap.put(_attribute.get("name").toString(), breadcrumb);
            
        } else if(_elemtName.equals("funcdata") && _attribute.get("from") != null && _attribute.get("from").equals("pageGeneralData")) {//页面准备数据
            
            Map<String,Object> generalData = funcPageGeneralData(dataALGMap, conf);
            resultMap.putAll(generalData);
            
        } else if(_elemtName.equals("funcdata") && _attribute.get("from") != null && _attribute.get("from").equals("getCommonAttrProduct")) {//同类产品
            
            String parameter = (String) _attribute.get("parameter");
            List<Map<String,Object>> productData = commonAttrProductService.getProductWith(parameter, conf);
            resultMap.put(_attribute.get("name").toString(),productData);
            
        }
        return resultMap;
    }
    
    /**
     * XML定义调用函数信息 page准备数据
     * @param dataMap
     * @return
     * @author Administrator
     * Date 2014年11月5日
     * @version
     */
    @SuppressWarnings("unchecked")
    private Map<String,Object> funcPageGeneralData(Map<String,Object> dataMap, ConfPrepareData conf) {
        Map<String,Object> retMap = new HashMap<String, Object>();//结果集
        Map<String, Object> preData = dataPrepareService.getGeneralData(conf);//页面数据准备原始数据

        for (Map.Entry<String, Object> dataEntry : dataMap.entrySet()) {
            String mapKey = dataEntry.getKey();
            if(mapKey == "_privateInfo") {
                continue;
            }
            
            Map<String,Object> dataVal = (Map<String, Object>) dataEntry.getValue();
            
            Map<String,Object> _dataPriInfo = xmlElementDefineMath._getPrivateAttrInfo(dataVal);
            Map<String,Object> _dataAttribute = (Map<String, Object>) _dataPriInfo.get("_attribute");//属性
            List<Map<String, Object>> _dataFieldList = (List<Map<String, Object>>) _dataPriInfo.get("_field");//字段
            
            if(xmlElementDefineMath.xmlFieldVerifyOk(_dataAttribute)  && preData.containsKey(_dataAttribute.get("fieldName"))) {
                List<Map<String,Object>> preListData = (List<Map<String, Object>>) preData.get(_dataAttribute.get("fieldName"));
                if(preListData != null) {
                    List<Map<String,Object>> _temp = xmlElementDefineMath.putFiledMulti(_dataFieldList, preListData);
                    retMap.put(_dataAttribute.get("name").toString(), _temp);
                } else {
                    retMap.put(_dataAttribute.get("name").toString(), "");
                }
            }
            
            //for static html publish
            if(xmlElementDefineMath.xmlFieldVerifyOk(_dataAttribute)  && preData.containsKey(CommonConstants.STATIC_HTML_CONTENT_FIELD)) {
                retMap.put("static_content", preData.get(CommonConstants.STATIC_HTML_CONTENT_FIELD));
            }
            
        }
        
        return retMap;
    }

}
