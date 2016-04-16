package com.kohler.tag;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.kohler.bean.Field;
import com.kohler.bean.ListDataType;
import com.kohler.bean.Numeration;
import com.kohler.constants.CommonConstants;

public class DatatypeSearchTag extends TagSupport {
	
	private static final long serialVersionUID = 1L;

	private String datatypeId;
	
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

    

    public void init() {
    }
    
    public int doStartTag() {
        init();
		
		JspWriter out = pageContext.getOut();// 用pageContext获取out，他还能获取session等，基本上jsp的内置对象都能获取
		
        //DataTypePojo dataTypePojo = pageService.geteDataTypeById(Integer.parseInt(datatypeId));
        
        ListDataType dataTypeList = (ListDataType) CommonConstants.DATATYPE_DEFINATION_XML_HASHMAP.get(datatypeId).get(0);

		try {
			for (Field field : dataTypeList.getFields()) {
				String editType = field.getEditorType();
				if ("select".equals(editType)) {
					String outString = createSelect(field);
					out.print(outString);
				}
				if ("text".equals(editType)) {
					String outString = createText(field);
					out.print(outString);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;// 标签执行完后，继续执行后面的
	}

	public String createText(Field field) {
		StringBuffer sb = new StringBuffer();
		String name = field.getName();
		sb.append("<div class='col-md-2 tn_c'><label>"+name+"</label></div>");
		sb.append("<div class='col-md-3'><input type='text' value='' name='"+name+"' id='actionSearch' /></div>");
		return sb.toString();
	}

	public String createSelect(Field field) {
		StringBuffer sb = new StringBuffer();
		String name = field.getName();
		
	    sb.append("<div class='col-md-2 tn_c'><label>"+name+"</label></div>");
		sb.append("<div class='col-md-3'><select name=" + name + " id='actionSearch' >");
		for (Numeration num : field.getNumeration()) {
			sb.append("<option name='" + num.getOption() + "'>" + num.getValue()
					+ "</option>");
		}
		sb.append("</select></div>");
		return sb.toString();
	}

	public int doEndTag() {
		return SKIP_BODY;// 标签执行完后，不继续执行后面的
	}

}
