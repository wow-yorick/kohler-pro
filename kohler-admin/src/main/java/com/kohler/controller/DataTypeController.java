/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.controller;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.SAXException;

import com.kohler.constants.CommonConstants;
import com.kohler.dao.utils.Pager;
import com.kohler.entity.DatatypeEntity;
import com.kohler.entity.MasterdataEntity;
import com.kohler.service.DataTypeService;
import com.kohler.util.PropertiesUtils;




/**
 * 用于处理Datatype/Pages的相关的请求
 *
 * @author liuzhiyong
 * @Date 2014年9月25日
 */
@Controller
@RequestMapping(value = "/dataType")
public class DataTypeController extends BasicController {

	
	
    @Autowired
    private DataTypeService datatypeService;

    
    @Value("#{settings['file.datatype.xsd.dir']}")
    private String datatypeXsdDir;
    
    @Value("#{settings['file.datatype.xmlList.dir']}")
    private String datatypeXmlListDir;
    
    @Value("#{settings['file.datatype.xmlEdit.dir']}")
    private String datatypeXmlEditDir;
    
    

    /**
     * 获取列表页面的列表数据
     * @param Model model
     * @param Pager<DatatypeEntity> pager 用于存储获得的列表数据
     * @param HttpServletRequest request
     * @return
     * @author liuzhiyong Date 2014年10月15日
     * @version
     */
    @RequestMapping(value = "/dataTypeList")
    public String listSelectDataType(Model model,Pager<DatatypeEntity> pager, HttpServletRequest request) {
    	if (logger.isInfoEnabled()) {
            logger.info("DataTypeController.listSelectDataType begin");
        }
        String uri = request.getRequestURI() + "?";
        String dataTypeName = request.getParameter("dataTypeName");
        //判断dataTypeName是否为空
        String datatypeNameBase = dataTypeName;
        if (datatypeNameBase == null) {
        	datatypeNameBase = "";
        }
        if (datatypeNameBase.contains("*"))
        {
        	datatypeNameBase = datatypeNameBase.replace('*', '%');
        }
        datatypeNameBase = "%"+ datatypeNameBase +"%";
        uri += "dataTypeName=" + datatypeNameBase + "&";
        pager = datatypeService.listDataType(pager, datatypeNameBase);

        pager.setUrl(uri);
        model.addAttribute("dataTypeName", dataTypeName);
        model.addAttribute("pager", pager);
        if (logger.isInfoEnabled()) {
            logger.info("DataTypeController.listSelectDataType end");
        }
        return "admin/dataTypeList";
    }

    
    
    /**
     * 进入datatype新建页面，获取datatypeType下拉框的数据
     * @param Model model
     * @return
     * @author liuzhiyong Date 2014年10月14日
     * @version
     */
    @RequestMapping(value = "/create")
    public String newDataTypePage(Model model) {
    	if (logger.isInfoEnabled()) {
            logger.info("DataTypeController.newDataTypePage begin");
        }
    	Pager<MasterdataEntity> masterdataEntity = datatypeService.getMasterData();
    	model.addAttribute("masterdataEntity", masterdataEntity);
    	if (logger.isInfoEnabled()) {
            logger.info("DataTypeController.newDataTypePage end");
        }
        return "admin/dataTypeCreate";
    }
    
    /**
     * 将新建页面输入的数据插入到数据库中
     * @param model
     * @param dataTypeName	页面上的datatype名称
     * @param datatypeMenuName 页面上的datatype菜单名称
     * @param datatypeType 页面上的datatype类型
     * @param datatypeListUISchemaXML 页面上的ListUISchemaXML的数据
     * @param datatypeEditUISchemaXML 页面上的EditUISchemaXML的数据
     * @return
     * @author liuzhiyong Date 2014年10月14日
     * @version
     */
    @RequestMapping(value = "/createSave")
    @ResponseBody
    public Map<String,Object> addSaveDataType(Model model,String dataTypeName,String datatypeMenuName,String datatypeType,String datatypeListUISchemaXML,String datatypeEditUISchemaXML)
    {
    	if (logger.isInfoEnabled()) {
            logger.info("DataTypeController.addSaveDataType begin");
        }
    	DatatypeEntity dataType = new DatatypeEntity();
    	int typeId = Integer.parseInt(datatypeType);
    	dataType.setDatatypeName(dataTypeName);
    	dataType.setDatatypeType(typeId);
    	dataType.setMenuName(datatypeMenuName);
    	dataType.setListDefinationXML(datatypeListUISchemaXML);
    	dataType.setEditDefinationXML(datatypeEditUISchemaXML);
    	Map<String, Object> result = new HashMap<String, Object>();
    	dataType.setIsEnable(true);
    	datatypeService.addDataType(dataType);
    	String msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_CREATE_SUCCESS);
        result.put("msg", msg);
        result.put("success", true);
        if (logger.isInfoEnabled()) {
            logger.info("DataTypeController.addSaveDataType end");
        }
    	return result; 
    }
    
    /**
     * 进入修改页面
     * @param model
     * @param dataTypeIds 选择查看或修改的数据的datatype数据Id
     * @param type 用于判断是进入修改页面还是明细页面
     * @return
     * @author liuzhiyong Date 2014年9月30日
     * @version
     */
    @RequestMapping(value = "/edit")
    public String editDataTypePage(Model model,Integer dataTypeIds, Integer type) {
    	if (logger.isInfoEnabled()) {
            logger.info("DataTypeController.editDataTypePage begin");
        }
    	DatatypeEntity dataType = datatypeService.getDataTypeById(dataTypeIds);
    	Pager<MasterdataEntity> masterdataEntity = datatypeService.getMasterData();
        model.addAttribute("type", type);
        model.addAttribute("masterdataEntity", masterdataEntity);
        model.addAttribute("dataType", dataType);
        if (logger.isInfoEnabled()) {
            logger.info("DataTypeController.editDataTypePage end");
        }
        return "admin/dataTypeEdit";
    }
    
    /**
     * 进入详细页面
     * @param model
     * @param dataTypeIds 选择查看或修改的数据的datatype数据Id
     * @param type 用于判断是进入修改页面还是明细页面
     * @return
     * @author liuzhiyong Date 2014年9月30日
     * @version
     */
    @RequestMapping(value = "/view")
    public String viewDataTypePage(Model model,Integer dataTypeIds, Integer type) {
    	if (logger.isInfoEnabled()) {
            logger.info("DataTypeController.editDataTypePage begin");
        }
    	DatatypeEntity dataType = datatypeService.getDataTypeById(dataTypeIds);
    	Pager<MasterdataEntity> masterdataEntity = datatypeService.getMasterData();
        model.addAttribute("type", type);
        model.addAttribute("masterdataEntity", masterdataEntity);
        model.addAttribute("dataType", dataType);
        if (logger.isInfoEnabled()) {
            logger.info("DataTypeController.editDataTypePage end");
        }
        return "admin/dataTypeEdit";
    }
    

    
    
    /**
     * 将修改页面输入的数据对应修改数据库中的数据
     * @param model
     * @param dataTypeName	页面上的datatype名称
     * @param dataTypeId	页面上的datatypeId
     * @param datatypeMenuName 页面上的datatype菜单名称
     * @param datatypeType 页面上的datatype类型
     * @param datatypeListUISchemaXML 页面上的ListUISchemaXML的数据
     * @param datatypeEditUISchemaXML 页面上的EditUISchemaXML的数据
     * @return
     * @author liuzhiyong Date 2014年9月30日
     * @version
     */
    @RequestMapping(value = "/editSave")
    @ResponseBody
    public Map<String,Object> updateSaveDataType(Model model,String dataTypeName,String dataTypeId,String datatypeMenuName,String datatypeType,String datatypeListUISchemaXML,String datatypeEditUISchemaXML)
    {
    	if (logger.isInfoEnabled()) {
            logger.info("DataTypeController.updateSaveDataType begin");
        }
    	DatatypeEntity dataType = new DatatypeEntity();
    	int Id = Integer.parseInt(dataTypeId);
    	int typeId = Integer.parseInt(datatypeType);
    	dataType.setDatatypeId(Id);
    	dataType.setDatatypeName(dataTypeName);
    	dataType.setDatatypeType(typeId);
    	dataType.setMenuName(datatypeMenuName);
    	dataType.setListDefinationXML(datatypeListUISchemaXML);
    	dataType.setEditDefinationXML(datatypeEditUISchemaXML);
    	Map<String, Object> result = new HashMap<String, Object>();
    	
    	dataType.setIsEnable(true);
    	datatypeService.editDataType(dataType);
    	String msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_UPDATE_SUCCESS);
        result.put("msg", msg);
        result.put("success", true);
    	if (logger.isInfoEnabled()) {
            logger.info("DataTypeController.updateSaveDataType end");
        }
    	return result; 
    }
    
    
    /**
     * 删除所选择的数据
     * @param datatypeId 选中的所有id的连接而成的字符串
     * @param model
     * @return
     * @author liuzhiyong Date 2014年9月30日
     * @version
     */
    //@SuppressWarnings("unchecked")
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Map<String, Object> deleteDataTypePage(Model model,String datatypeId) {
    	if (logger.isInfoEnabled()) {
            logger.info("DataTypeController.deleteDataTypePage begin");
        }
        Map<String, Object> result = new HashMap<String, Object>();
        //判断所选列表数据的id是否为空
        if(datatypeId != null &&  !"".equals(datatypeId)){
            datatypeService.deletedataType(datatypeId);
            String msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_DELETE_SUCCESS);
            result.put("msg", msg);
            result.put("success", true);
        }
        if (logger.isInfoEnabled()) {
            logger.info("DataTypeController.deleteDataTypePage end");
        }
        return result;
    }
    
    /**
     * 对XML内容进行效确
     * @param Model model
     * @param String datatypeListUISchemaXML 页面上的ListUISchemaXML的数据
     * @param String datatypeEditUISchemaXML 页面上的EditUISchemaXML的数据
     * @return
     * @author liuzhiyong Date 2014年9月30日
     * @version
     */
    @RequestMapping(value = "/unlimited/changeDatatype")
    @ResponseBody
    public Map<String, Object> verificationXML(Model model,String datatypeListUISchemaXML,String datatypeEditUISchemaXML) {
    	Map<String, Object> result = new HashMap<String, Object>();	
    	if (logger.isInfoEnabled()) {
            logger.info("DataTypeController.change begin");
        }
    	InputStream inputStreamList = string2InputStream(datatypeListUISchemaXML);
    	InputStream inputStreamEdit = string2InputStream(datatypeEditUISchemaXML);
    	// 建立schema工厂
    	//	SchemaFactory schemaFactory = SchemaFactory
    	//			.newInstance(datatypeXmlDir);
    		// 建立验证文档文件对象，利用此文件对象所封装的文件进行schema验证
    	//	File schemaFile = new File(datatypeXsdDir);

    		// 利用schema工厂，接收验证文档文件对象生成Schema对象
    	//	Schema schema = schemaFactory.newSchema(schemaFile);
    		// 通过Schema产生针对于此Schema的验证器，利用students.xsd进行验证
    	//	Validator validator = schema.newValidator();
    		// 得到验证的数据源，就是students.xml
    		//Source source = new StreamSource("C:/Users/Administrator/Desktop/h2.xml");
    			
    	//	File a = getFileFromBytes(datatypeListUISchemaXML,datatypeXmlListDir);
    	//	File b = getFileFromBytes(datatypeEditUISchemaXML,datatypeXmlEditDir);
    	//	Source source = new StreamSource(a);
    	//	Source source1 = new StreamSource(b);
    	

    		// 开始验证，成功输出success!!!，失败输出fail
    	//	try{
    	//		validator.validate(source);
    	//		validator.validate(source1);
    	//		System.out.println("success!!!");
    	//	}catch(Exception ex){
    	//	System.out.println("fail");
    	//}
    	String xsdPath = datatypeXsdDir;
		boolean listResult = validateXML(xsdPath,inputStreamList);
		boolean editResult = validateXML(xsdPath,inputStreamEdit);
		String msglistUISchemaXML = PropertiesUtils.findPropertiesKey(CommonConstants.MSG_LIST_UI_SCHEMA_XML);
		String msgEditUISchemaXML = PropertiesUtils.findPropertiesKey(CommonConstants.MSG_EDIT_UI_SCHEMA_XML);
		if(listResult == false)
		{
			result.put("msg", msglistUISchemaXML);
			return result;
		}
		if(editResult == false)
		{
			result.put("msg", msgEditUISchemaXML);
			return result;
		}
		result.put("msg", "");
    	if (logger.isInfoEnabled()) {
            logger.info("DataTypeController.change end");
        }
		return result;
	}
    
    /**
     * 将内容由String格式转换为File类型
     * @param String name 需要验证的内容
     * @param String path 被转换的文件路径
     * @return
     * @author liuzhiyong Date 2014年10月23日
     * @version
     */
    public static File getFileFromBytes(String name,String path) {
    	if (logger.isInfoEnabled()) {
            logger.info("DataTypeController.getFileFromBytes begin");
        }
     byte[] b=name.getBytes();
        BufferedOutputStream stream = null;
        FileOutputStream fstream = null;
        File file = null;
        try {
            file = new File(path);
            fstream = new FileOutputStream(file);
            stream = new BufferedOutputStream(fstream);
            stream.write(b);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e1) {
                }
            }
            if (fstream != null) {
                try {
                    fstream.close();
                } catch (IOException e1) {
                }
            }
        }
        if (logger.isInfoEnabled()) {
            logger.info("DataTypeController.getFileFromBytes end");
        }
        return file;
    }

    /**
     * 对内容进行XML验证的方法
     * @param String xsdPath 用于验证的文档路径
     * @param InputStream xmlData 有内容转换为InputStream类型数据
     * @return
     * @author liuzhiyong Date 2014年10月23日
     * @version
     */
	private boolean validateXML(String xsdPath, InputStream xmlData) {
		// 建立schema工厂
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

		// 建立验证文档文件对象，利用此文件对象所封装的文件进行schema验证
		File schemaFile = new File(xsdPath);

		// 利用schema工厂，接收验证文档文件对象生成Schema对象
		Schema schema = null;
		try {
			schema = schemaFactory.newSchema(schemaFile);
		} catch (SAXException e) {
			//e.printStackTrace();
		}

		// 通过Schema产生针对于此Schema的验证器，利用schenaFile进行验证
		Validator validator = schema.newValidator();

		// 得到验证的数据源
		Source source = new StreamSource(xmlData);
		// 开始验证，成功输出success!!!，失败输出fail
		// 参数还可以用文件的String转为的inputstreamnew
		// //ByteArrayInputStream(text.getBytes("UTF-8"));
		try {
			validator.validate(source);
		} catch (Exception ex) {
			//System.out.println(ex.getMessage());
			logger.debug(ex.getMessage());
			//ex.printStackTrace();
			return false;
		}

		return true;
	}
	
	/**
     * 将String类型转换为InputStream类型的方法
     * @param String str 需要被转换的字符数据
     * @return
     * @author liuzhiyong Date 2014年10月23日
     * @version
     */
	private static InputStream string2InputStream(String str) {
		ByteArrayInputStream stream = null;
		try {
			stream = new ByteArrayInputStream(str.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			return null;
		}
		return stream;
	}
   
	
	/**
     * 新增数据时Name的唯一性判断
     * @param String datatypeName 用于验证唯一性的datatypeName
     * @return
     * @author liuzhiyong Date 2014年9月30日
     * @version
     */
    //@SuppressWarnings("unchecked")
    @RequestMapping(value = "/unlimited/uniquenessVerification")
    @ResponseBody
    public boolean uniquenessVerification(String datatypeName) {
    	if (logger.isInfoEnabled()) {
            logger.info("DataTypeController.uniquenessVerification begin");
        }
        int row = datatypeService.existdatatypeName(datatypeName);
        if (logger.isInfoEnabled()) {
            logger.info("DataTypeController.uniquenessVerification end");
        }
        if (row > 0)
        {
        	return  false; 
        }
        else
        {
        	return  true; 
        }
    }
    
    /**
     * 修改数据时Name的唯一性判断
     * @param String datatypeName 用于验证唯一性的datatypeName
     * @param String datatypeId   被选择数据的datatypeId
     * @return
     * @author liuzhiyong Date 2014年9月30日
     * @version
     */
    //@SuppressWarnings("unchecked")
    @RequestMapping(value = "/unlimited/uniquenessEditVerification")
    @ResponseBody
    public boolean uniquenessEditVerification(String datatypeName,String datatypeId) {
    	if (logger.isInfoEnabled()) {
            logger.info("DataTypeController.uniquenessEditVerification begin");
        }
        int row = datatypeService.existEditdatatypeName(datatypeName,datatypeId);
        if (logger.isInfoEnabled()) {
            logger.info("DataTypeController.uniquenessEditVerification end");
        }
        if (row > 0)
        {
        	return  false; 
        }
        else
        {
        	return  true; 
        }
    }
    
}

