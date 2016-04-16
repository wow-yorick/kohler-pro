/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.tag;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.kohler.bean.Check;
import com.kohler.bean.EditDataType;
import com.kohler.bean.Field;
import com.kohler.constants.CommonConstants;
import com.kohler.entity.MasterDataCodeConstant;
import com.kohler.service.PageService;


/**
 * SectionPageTag Tag
 *
 * @author kangmin_Qu
 * @Date 2014-10-17
 */
public class SectionPageTag extends TagSupport {

    private static final long serialVersionUID = 1L;

    private String            datatypeId; // DataTypeId from page

    private String pageId; //content id from page
    
    
    /**
     * LOG4J Print Object
     */
    private final static Logger logger = Logger.getLogger(SectionPageTag.class);
    
    /**
     * @return the pageId
     */
    public String getPageId() {
        return pageId;
    }

    /**
     * @param pageId the pageId to set
     */
    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    /**
     * @return the datatypeId
     */
    public String getDatatypeId() {
        return datatypeId;
    }

    /**
     * @param datatypeId the datatypeId to set
     */
    public void setDatatypeId(String datatypeId) {
        this.datatypeId = datatypeId;
    }

    private PageService pageService;

    
    
    
    /**
     * Tag init
     * @author kangmin_Qu
     * Date 2014-11-3
     * @version
     */
    public void init() {
        try {
            ServletContext sc = pageContext.getServletContext();
            pageService = (PageService) WebApplicationContextUtils
                    .getRequiredWebApplicationContext(sc).getBean("pageService");
        } catch (Exception e) {
            logger.error("SectionPage Tag init error",e);
        }
    }
    
    
    
    /**
     * 
     * {@inheritDoc}
     */
    public int doStartTag() {
        init();
        
        if(datatypeId == null || "".equals(datatypeId)||CommonConstants.DATATYPE_DEFINATION_XML_HASHMAP.get(String.valueOf(datatypeId))==null){
            return EVAL_PAGE;
        }

        //DataTypePojo dataTypePojo = pageService.geteDataTypeById(Integer.parseInt(datatypeId));
        EditDataType dataTypeEdit = (EditDataType) CommonConstants.DATATYPE_DEFINATION_XML_HASHMAP.get(String.valueOf(datatypeId)).get(1);
        
        JspWriter out = pageContext.getOut();// 用pageContext获取out，他还能获取session等，基本上jsp的内置对象都能获取
        
        StringBuffer sb = new StringBuffer();
        if(dataTypeEdit!=null&&dataTypeEdit.getFields()!=null&&dataTypeEdit.getFields().size()>0){
            for (Field field : dataTypeEdit.getFields()) {
                
                try {
                    String editType = field.getEditorType();

                    if ("DataTypeListPicker".equals(editType)) {
                        String outString = createComplicated(field);
                        sb.append(outString);
                    }
                    
                    if ("RichText".equals(editType)) {
                        String outString = createRichText(field);
                        sb.append(outString);
                    }
                    if ("Textarea".equals(editType)) {
                        String outString = createTextarea(field);
                        sb.append(outString);
                    }
                    
                } catch (Exception e) {
                    logger.error("SectionPage Tag doStartTag error",e);
                }

            }
        }
        
        
        try {
            out.print(sb.toString());
        } catch (IOException e) {
            logger.error("SectionPage Tag out.print error",e);
        }
        
        return EVAL_PAGE;// 标签执行完后，继续执行后面的
    }
    
    
    
    
    /**
     * 创建富文本编辑器
     * @param field 需要创建的filed
     * @return
     * @author kangmin_Qu
     * Date 2014-11-3
     * @version
     */
    public String createRichText(Field field){
        
        String name = field.getName(); //file name
        
        StringBuffer sb = new StringBuffer();
        
        String fieldValue = getFieldValue(name);
        
        sb.append("<div class='row choose_con'><div class='col-md-2'> ");
        sb.append(name);
        sb.append("</div><div class='col-md-10 border'>");
        sb.append("<textarea rows='4'  id='list_content"+name+"' name='"+name+"' >"+fieldValue+"</textarea>");
        sb.append("</div></div>");
        sb.append("<script>");
        sb.append(" UeditorFileLoad.init('list_content"+name+"'); ");
        sb.append("</script>");
        
        return sb.toString();
    }
    
    /**
     * 创建多行输入框
     * @param field 需要创建的filed
     * @return
     * @author kangmin_Qu
     * Date 2014-11-3
     * @version
     */
    public String createTextarea(Field field){
        
        String name = field.getName(); //file name
        
        StringBuffer sb = new StringBuffer();
        
        String fieldValue = getFieldValue(name);
        
        sb.append("<div class='row choose_con'><div class='col-md-2'> ");
        sb.append(name);
        sb.append("</div><div class='col-md-10 border'>");
        sb.append("<textarea rows='4'  name='"+name+"' >"+fieldValue+"</textarea>");
        sb.append("</div></div>");
        
        return sb.toString();
    }
    
    /**
     * 创建复杂类型的字段
     * @param field 需要创建的filed
     * @return
     * @author kangmin_Qu
     * Date 2014-11-3
     * @version
     */
    public String createComplicated(Field field) {
        
        StringBuffer sb = new StringBuffer();
        
        String name = field.getName(); //file name
        String id = field.getDataTypeRef(); // DataType master Id
        String fieldValue = getFieldValue(name); // field value
        String req = "";
        if (field.getChecks() != null && field.getChecks().size() > 0) {
            for (Check check : field.getChecks()) {
                if (check.getRequired() != null) {
                    req = "required";
                }
            }
        }
        
        sb.append("<div class='row choose_con "+req+"'><div class='col-md-2'> ");
        sb.append(name);
        sb.append("</div><div class='col-md-10 border'><input type='hidden' name='"+name+"' id='hidden"+name+"' value='"+fieldValue+"'/><input type='text' "+req+" name='show"+name+"'  id='show"+name+"' value='"+getContentFieldValue(fieldValue,id)+"'/><a href='#' onclick=\"openDataType('"+id+"','hidden"+name+"','show"+name+"');\" class='choose tn_c'>Choose</a></div></div>");
        //sb.append("<input type='hidden' name='datatype"+id+"' value='"+fieldValue+"' />");

        return sb.toString();
    }


    /**
     * 根据字段名称获取字段值
     * @param fieldName 字段名称
     * @return
     * @author kangmin_Qu
     * Date 2014-11-3
     * @version
     */
    public String getFieldValue(String fieldName) {
        if (pageId != null && !"".equals(pageId)) {
            String value = pageService.getPageFieldValue(Integer.parseInt(pageId), fieldName);
            if(value != null && !"".equals(value)){
                return value;
            }
        }
        return "";
    }
    
    /**
     * 根据字段名称获取字段值
     * @param fieldName 字段名称
     * @return
     * @author kangmin_Qu
     * Date 2014-11-3
     * @version
     */
    public String getContentFieldValue(String pageIds,String id) {
        int mastercode = MasterDataCodeConstant.CONTENT_DISPLAY_ATTR_NAME_HERO_AREA;
        if(CommonConstants.CONTENT_HERO_AREAS.equals(id)){
            mastercode = MasterDataCodeConstant.CONTENT_DISPLAY_ATTR_NAME_HERO_AREA;
        }
        if(CommonConstants.CONTENT_BANNER_UNITS.equals(id)){
            mastercode = MasterDataCodeConstant.CONTENT_DISPLAY_ATTR_NAME_BANNER_UNIT;
        }
        if(CommonConstants.CONTENT_HOT_SPOTS.equals(id)){
            mastercode = MasterDataCodeConstant.CONTENT_DISPLAY_ATTR_NAME_HOT_SPOT;
        }
        if (pageIds != null && !"".equals(pageIds)) {
            List<Map<String, Object>> contentvaluelist = pageService.getContentFieldValues(","+pageIds+",",mastercode);
            if(contentvaluelist != null && contentvaluelist.size()>0){
                StringBuffer sb = new StringBuffer();
                for (Map<String, Object> map : contentvaluelist) {
                    sb.append(map.get("FIELD_VALUE"));
                    sb.append(",");
                }
                String str = sb.toString();
                return str.substring(0, str.length() - 1);
            }
        }
        return "";
    }
    
    

    
    
    
    /**
     * 
     * {@inheritDoc}
     */
    public int doEndTag() {
        return SKIP_BODY;// 标签执行完后，不继续执行后面的
    }
}
