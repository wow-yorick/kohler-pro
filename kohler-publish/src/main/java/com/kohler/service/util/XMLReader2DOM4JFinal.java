package com.kohler.service.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 
 * Dom4j 解析xml
 *
 * @author Administrator
 * @Date 2014年10月29日
 */
public class XMLReader2DOM4JFinal {
    
    private final static Logger logger = Logger.getLogger(XMLReader2DOM4JFinal.class);
    
    @SuppressWarnings("unchecked")
    public static Map<String,Object> readFileXml(File xmlFile) {
        Map<String,Object> retMap = new HashMap<String, Object>();
        SAXReader reader = new SAXReader();
        Map<String,Object> elementPrivateAttr = new HashMap<String, Object>(); 
        try {
            Document doc = reader.read(xmlFile);
            //获取根节点
            Element rootElt = doc.getRootElement();
            //获取根节点的属性
            Iterator<Attribute> rootAttrEles =  rootElt.attributeIterator();
            Map<String,String> rootAttrElt = parseXmlAttr(rootAttrEles);
            HashMap<String, Object> attrSpace = new HashMap<String, Object>();
            attrSpace.put("_attribute", rootAttrElt);
            attrSpace.put("_elemtName", rootElt.getName());
            elementPrivateAttr.put("_privateInfo", attrSpace);
            //解析子元素
            Map<String,Object> childRet = parseDataResource(rootElt, elementPrivateAttr);
            retMap.put("root", childRet);
        } catch (DocumentException e) {
            logger.error(e);
        }
        
        //logger.info("XML map:"+ JSonUtil.toJSonString(retMap));
        
        return retMap;
        
    }
    
    /**
     * 解析数据源-----根节点下一级元素
     * @param rootElt
     * @author Administrator
     * Date 2014年10月29日
     * @version
     */
    @SuppressWarnings("unchecked")
    private static Map<String,Object> parseDataResource(Element actElt, Map<String,Object> container) {
        
        //获取根节点下的子节点数据资源-单数据
        Iterator<Element> iterData = actElt.elementIterator("data");
        while (iterData.hasNext()) {
            parseXmlElement(iterData,container);
        }
        
        //获取根节点下的子节点数据资源-多数据
        Iterator<Element> iterMultidata = actElt.elementIterator("multidata");
        while (iterMultidata.hasNext()) {
            parseXmlElement(iterMultidata,container);
        }
        
        //函数资源
        Iterator<Element> iterFuncData = actElt.elementIterator("funcdata");
        while (iterFuncData.hasNext()) {
            parseXmlElement(iterFuncData,container);
        }
        
        //映射资源
        Iterator<Element> iterMapData = actElt.elementIterator("map");
        while (iterMapData.hasNext()) {
            parseXmlElement(iterMapData,container);
        }
        
        return container;
        
    }
    
    
    
    
    /**
     * 解析xmlElement
     * @param elemt
     * @return
     * @author Administrator
     * Date 2014年10月29日
     * @version
     */
    @SuppressWarnings("unchecked")
    private static void parseXmlElement(Iterator<Element> iterData, Map<String,Object> container) {
        Element elemt = iterData.next();
        Map<String,Object> retElt = new HashMap<String, Object>();//返回--一个元素的熟悉及字段
        Map<String,Object> elementPrivateAttr = new HashMap<String, Object>(); 
        
        //获取数据资源的属性
        Iterator<Attribute> attrEles =  elemt.attributeIterator();
        Map<String,String> attrMap = parseXmlAttr(attrEles);
        //获取字段信息
        List<Map<String,String>> field = parseFieldElt(elemt);
        retElt.put("_field", field);            

        retElt.put("_attribute", attrMap);
        retElt.put("_elemtName", elemt.getName());//元素名称
        elementPrivateAttr.put("_privateInfo", retElt);
        
        container.put(elemt.attributeValue("name"), elementPrivateAttr);

        parseDataResource(elemt, elementPrivateAttr);//递归处理子集元素
    }
    
    
    /**
     * 获取xml中元素的属性,放入Map中
     * @param attrEles
     * @return
     * @author Administrator
     * Date 2014年10月29日
     * @version
     */
    private static Map<String,String> parseXmlAttr(Iterator<Attribute> attrEles) {
        Map<String,String> retAttr = new HashMap<String, String>();
        while (attrEles.hasNext()) {
            Attribute itemEle = (Attribute) attrEles.next();
            retAttr.put(itemEle.getName(), itemEle.getValue());
        }
        
        return retAttr;
    }
    
    
    /**
     * 解析field元素信息
     * @param fieldEltIter
     * @return
     * @author Administrator
     * Date 2014年10月29日
     * @version
     */
    @SuppressWarnings("unchecked")
    private static List<Map<String,String>> parseFieldElt(Element elmtPar) {
        List<Map<String,String>> retAttrList = new ArrayList<Map<String,String>>();
        Iterator<Element> fieldEltIter = elmtPar.elementIterator("field");
        while (fieldEltIter.hasNext()) {
            Element fieldElt = fieldEltIter.next();
            //获取属性
            Iterator<Attribute> attrEles =  fieldElt.attributeIterator();
            Map<String,String> attrMap = parseXmlAttr(attrEles);
            retAttrList.add(attrMap);
        }
        return retAttrList;
    }

}