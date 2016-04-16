/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.framework.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.commons.digester.Digester;
import org.apache.log4j.Logger;

import com.kohler.bean.Check;
import com.kohler.bean.EditDataType;
import com.kohler.bean.Field;
import com.kohler.bean.Lang;
import com.kohler.bean.ListDataType;
import com.kohler.bean.Numeration;

/**
 * Xml 解析器
 *
 * @author kangmin.qu
 * @Date 2014年10月16日
 */
public class ParseXml {
    
    /**
     * LOG4J Print Object
     */
    private final static Logger logger = Logger.getLogger(ParseXml.class);
    
    /**
     * 解析列表界面XML
     * @param xml 列表界面XML
     * @return
     * @author kangmin_Qu
     * Date 2014-11-4
     * @version
     */
    public static ListDataType parseListXml(String xml) {

        if (xml == null || "".equals(xml)) {
            return null;
        }

        Digester digester = new Digester();
        digester.setValidating(false);

        digester.addObjectCreate("datatype", ListDataType.class);
        digester.addSetProperties("datatype", "name", "name");

        digester.addObjectCreate("datatype/selectFields/field", Field.class.getName());
        digester.addSetProperties("datatype/selectFields/field", "editorType", "editorType");
        digester.addSetProperties("datatype/selectFields/field", "name", "name");

        digester.addObjectCreate("datatype/selectFields/field/numeration/value",Numeration.class.getName());

        digester.addSetProperties("datatype/selectFields/field/numeration/value", "option","option");
        digester.addBeanPropertySetter("datatype/selectFields/field/numeration/value", "value");

        digester.addSetNext("datatype/selectFields/field/numeration/value", "addNumeration");

        digester.addSetNext("datatype/selectFields/field", "addField");

        digester.addCallMethod("datatype/displayFields/field", "addDisplayField", 1);
        digester.addCallParam("datatype/displayFields/field", 0);

        try {
            InputStream inputStream = new ByteArrayInputStream(xml.getBytes());
            ListDataType dataTypeList = (ListDataType) digester.parse(inputStream);
            return dataTypeList;
        } catch (Exception e) {
            logger.error("Parse List Xml Error ",e);
        }
        return null;

    }
    
    
    
    
    /**
     * 解析编辑页面XML
     * @param xml 编辑页面XML
     * @return
     * @author kangmin_Qu
     * Date 2014-11-4
     * @version
     */
    public static EditDataType parseEditXml(String xml) {

        if (xml == null || "".equals(xml)) {
            return null;
        }

        //Start Parse Xml
        Digester digester = new Digester();
        digester.setValidating(false);

        digester.addObjectCreate("datatype", EditDataType.class);
        digester.addSetProperties("datatype", "name", "name");

        digester.addObjectCreate("datatype/field", Field.class.getName());
        digester.addSetProperties("datatype/field", "editorType", "editorType");
        digester.addSetProperties("datatype/field", "editoTypeAttributes", "editoTypeAttributes");
        digester.addSetProperties("datatype/field", "name", "name");
        digester.addSetProperties("datatype/field", "id", "id");
        digester.addSetProperties("datatype/field", "affect", "affect");

        digester.addSetProperties("datatype/field/dataTypeRef", "dataTypeRef", "dataTypeRef");
        digester.addBeanPropertySetter("datatype/field/dataTypeRef", "dataTypeRef");
        
        digester.addObjectCreate("datatype/field/lang/value",Lang.class.getName());
        digester.addSetProperties("datatype/field/lang/value", "display","display");
        digester.addBeanPropertySetter("datatype/field/lang/value", "value");
        digester.addSetNext("datatype/field/lang/value", "addLang");

        digester.addSetProperties("datatype/field/masterDataCode", "masterDataCode", "masterDataCode");
        digester.addBeanPropertySetter("datatype/field/masterDataCode", "masterDataCode");
        

        digester.addObjectCreate("datatype/field/numeration/value",Numeration.class.getName());

        digester.addSetProperties("datatype/field/numeration/value", "option","option");
        digester.addBeanPropertySetter("datatype/field/numeration/value", "value");

        digester.addSetNext("datatype/field/numeration/value", "addNumeration");
        
        digester.addObjectCreate("datatype/field/check",Check.class.getName());

        digester.addSetProperties("datatype/field/check", "required","required");
        digester.addSetProperties("datatype/field/check", "maxLength","maxLength");
        digester.addSetProperties("datatype/field/check", "pattern","pattern");

        digester.addSetNext("datatype/field/check", "addCheck");

        digester.addSetNext("datatype/field", "addField");

        try {
            InputStream inputStream = new ByteArrayInputStream(xml.getBytes());
            EditDataType dataType = (EditDataType) digester.parse(inputStream);

            return dataType;

        } catch (Exception e) {
            logger.error("Parse Edit Xml Error ",e);e.printStackTrace();
        }
        return null;
    }

}
