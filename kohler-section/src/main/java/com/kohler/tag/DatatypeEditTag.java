package com.kohler.tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.kohler.bean.Check;
import com.kohler.bean.EditDataType;
import com.kohler.bean.Field;
import com.kohler.bean.Lang;
import com.kohler.bean.ListDataType;
import com.kohler.bean.Numeration;
import com.kohler.constants.CommonConstants;
import com.kohler.entity.AreaEntity;
import com.kohler.entity.AreaMetadataEntity;
import com.kohler.entity.MasterdataEntity;
import com.kohler.service.PageService;

/**
 * DatatypeEditTag Tag
 *
 * @author whh
 * @Date 2014-11-14
 */
public class DatatypeEditTag extends TagSupport {

    private static final long serialVersionUID = 1L;

    private String            datatypeId;           // datetypeId from content

    private String            lang;                 // lang from content

    private String            contentId;            // contentId from content

    private String            isEdit;               // isEdit

    /**
     * @return the contentId
     */
    public String getContentId() {
        return contentId;
    }

    /**
     * 
     * @param contentId the contentId to set
     * @version
     */
    public void setContentId(String contentId) {
        this.contentId = contentId;
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

    /**
     * @return the lang
     */
    public String getLang() {
        return lang;
    }

    /**
     * @param lang the lang to set
     */
    public void setLang(String lang) {
        this.lang = lang;
    }

    /**
     * @return the isEdit
     */
    public String getIsEdit() {
        return isEdit;
    }

    /**
     * @param isEdit the isEdit to set
     */
    public void setIsEdit(String isEdit) {
        this.isEdit = isEdit;
    }

    private PageService pageService;

    /**
     * 
     * Tag init
     * @author whh Date 2014年11月14日
     * @version
     */
    public void init() {
        try {
            ServletContext sc = pageContext.getServletContext();
            pageService = (PageService) WebApplicationContextUtils
                    .getRequiredWebApplicationContext(sc).getBean("pageService");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * {@inheritDoc}
     */
    public int doStartTag() {
        init();

        JspWriter out = pageContext.getOut();// 用pageContext获取out，他还能获取session等，基本上jsp的内置对象都能获取

        //DataTypePojo dataTypePojo = pageService.geteDataTypeById(Integer.parseInt(datatypeId));

        //EditDataType dataTypeList = ParseXml.parseEditXml(dataTypePojo.getEditDefinationXML());
        EditDataType dataTypeList = (EditDataType) CommonConstants.DATATYPE_DEFINATION_XML_HASHMAP.get(String.valueOf(datatypeId)).get(1);
        EditDataType newDataTypeList = new EditDataType();
        if (dataTypeList != null && dataTypeList.getFields() != null) {
            newDataTypeList.setName(dataTypeList.getName());
            for (Field field : dataTypeList.getFields()) {
                if (field.getLangs() != null && field.getLangs().size() > 0) {
                    for (Lang langfield : field.getLangs()) {
                        // 根据语言来添加
                        if (langfield.getValue() != null && langfield.getValue().equals(lang)) {
                            newDataTypeList.addField(field);
                        }
                    }
                }
            }

        }
        dataTypeList = newDataTypeList;
        if("0".equals(lang)){
            lang = "1";
        }
        try {
            for (Field field : dataTypeList.getFields()) {
                String editType = field.getEditorType();
                // 根据edittype进行输出类型选择
                if ("select".equals(editType)) {
                    String outString = createSelect(field);
                    out.print(outString);
                }
                if ("selectarea".equals(editType)) {
                    String outString = createSelectArea(field);
                    out.print(outString);
                }
                if ("text".equals(editType)) {
                    String outString = createText(field);
                    out.print(outString);
                }
                if ("dateinput".equals(editType)) {
                    String outString = createDateInput(field);
                    out.print(outString);
                }
                if ("textarea".equals(editType)) {
                    String outString = createTextarea(field);
                    out.print(outString);
                }
                if ("picker".equals(editType)) {
                    String outString = createComplicated(field);
                    out.print(outString);
                }
                if ("radio".equals(editType)) {
                    String outString = createRadio(field);
                    out.print(outString);
                }
                if ("checkbox".equals(editType)) {
                    String outString = createCheckBox(field);
                    out.print(outString);
                }
                if ("richtext".equals(editType)) {
                    String outString = createRichText(field);
                    out.print(outString);
                }
                if ("contentpicker".equals(editType)) {
                    String outString = createContentPicker(field);
                    out.print(outString);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return EVAL_PAGE;// 标签执行完后，继续执行后面的
    }

    /**
     * 创建input输入框
     * @param field 需要创建的filed
     * @return
     * @author whh Date 2014年11月14日
     * @version
     */
    public String createText(Field field) {
        StringBuffer sb = new StringBuffer();
        String name = field.getName(); // field name
        String fieldValue = getFieldValue(name); // field value
        StringBuffer checksb = new StringBuffer(); // input check

        String display = "";
        if (field.getLangs() != null) {
            for (Lang l : field.getLangs()) {
                if ((l.getValue() != null && l.getValue().equals(lang))||"0".equals(l.getValue())) {
                    display = l.getDisplay();
                }
            }
        }
        String req = "";
        if (field.getChecks() != null && field.getChecks().size() > 0) {
            for (Check check : field.getChecks()) {
                if ("1".equals(lang)) {
                    if (check.getRequired() != null) {
                        checksb.append(" required='" + check.getRequired() + "' ");
                        req = "required";
                    }
                }
                if (check.getMaxLength() != null) {
                    checksb.append(" maxlength='" + check.getMaxLength() + "' ");
                }
                if (check.getIsUnique() != null) {
                    if (lang == null) {
                        checksb.append(" isFieldValueUnique='-1' ");
                    } else {
                        checksb.append(" isFieldValueUnique='" + lang + "' ");
                    }
                }
                if (check.getPattern() != null) {
                    checksb.append(" pattern='" + check.getPattern() + "' ");
                }

            }
        }

        sb.append("<div class='row'><div class='col-md-3 tn_l " + req + "' >" + display + "</div>");
        sb.append("<div class='col-md-9'><input type='text'  name='" + name + "'  "
                + checksb.toString() + " value='" + fieldValue + "'/></div></div>");
        return sb.toString();
    }
    
    /**
     * 创建日期选择框
     * @param field 需要创建的filed
     * @return
     * @author whh Date 2014年11月14日
     * @version
     */
    public String createDateInput(Field field) {
        StringBuffer sb = new StringBuffer();
        String name = field.getName(); // field name
        String fieldValue = getFieldValue(name); // field value
        StringBuffer checksb = new StringBuffer(); // input check

        String display = "";
        if (field.getLangs() != null) {
            for (Lang l : field.getLangs()) {
                if ((l.getValue() != null && l.getValue().equals(lang))||"0".equals(l.getValue())) {
                    display = l.getDisplay();
                }
            }
        }
        String req = "";
        if (field.getChecks() != null && field.getChecks().size() > 0) {
            for (Check check : field.getChecks()) {
                if ("1".equals(lang)) {
                    if (check.getRequired() != null) {
                        checksb.append(" required='" + check.getRequired() + "' ");
                        req = "required";
                    }
                }
            }
        }

        sb.append("<div class='row'><div class='col-md-3 tn_l " + req + "' >" + display + "</div>");
        sb.append("<div class='col-md-4 laydate'><input class='laydate-icon' type='text'  name='" + name + "'  "
                + checksb.toString() + " readonly='readonly'  onClick='laydate()' value='" + fieldValue + "'/></div></div>");
        return sb.toString();
    }

    /**
     * 创建下拉选择框
     * @param field
     * @return
     * @author sana Date 2014年11月14日
     * @version
     */
    public String createSelect(Field field) {
        StringBuffer sb = new StringBuffer();
        String name = field.getName(); // field name
        String display = "";
        if (field.getLangs() != null) {
            for (Lang l : field.getLangs()) {
                if ((l.getValue() != null && l.getValue().equals(lang))||"0".equals(l.getValue())) {
                    display = l.getDisplay();
                }
            }
        }

        String fieldValue = getFieldValue(name); // field value
        StringBuffer checksb = new StringBuffer(); // field check
        String req = "";
        if (field.getChecks() != null && field.getChecks().size() > 0) {
            for (Check check : field.getChecks()) {
                if ("1".equals(lang)) {
                    if (check.getRequired() != null) {
                        checksb.append(" required='" + check.getRequired() + "' ");
                        req = "required";
                    }
                }
                if (check.getMaxLength() != null) {
                    checksb.append(" maxlength='" + check.getMaxLength() + "' ");
                }
                if (check.getIsUnique() != null) {

                    checksb.append(" isFieldValueUnique='" + lang + "' ");

                }
                if (check.getPattern() != null) {
                    checksb.append(" pattern='" + check.getPattern() + "' ");
                }

            }
        }

        sb.append("<div class='row'><div class='col-md-3 tn_l " + req + "'>" + display + "</div>");
        sb.append("<div class='col-md-6'><select name='" + name + "' " + checksb.toString() + " >");

        if (field.getMasterDataCode() != null) {
            if (!"TYPE_BANNER_UNIT".equals(field.getMasterDataCode())
                    && !"TYPE_FILE_SOURCE".equals(field.getMasterDataCode())) {
                sb.append("<option value=''>---请选择---</option>");
            }
            List<MasterdataEntity> sellist = pageService.getMasterdataByName(
                    field.getMasterDataCode(), lang);
            for (MasterdataEntity masterdataEntity : sellist) {
                if (fieldValue.equals(masterdataEntity.getMasterdataMetadataId().toString())) {
                    sb.append("<option selected='selected' value='"
                            + masterdataEntity.getMasterdataMetadataId() + "'>"
                            + masterdataEntity.getMasterdataName() + "</option>");
                } else {
                    sb.append("<option value='" + masterdataEntity.getMasterdataMetadataId() + "'>"
                            + masterdataEntity.getMasterdataName() + "</option>");
                }
            }

        } else {
            if (field.getNumeration() != null && field.getNumeration().size() > 0) {
                for (Numeration num : field.getNumeration()) {
                    if (fieldValue.equals(num.getOption())) {
                        sb.append("<option selected='selected' value='" + num.getOption() + "'>"
                                + num.getValue() + "</option>");
                    } else {
                        sb.append("<option value='" + num.getOption() + "'>" + num.getValue()
                                + "</option>");
                    }

                }
            }
        }
        sb.append("</select></div></div>");
        if (field.getAffect() != null && "1".equals(lang)) {
            sb.append("<script>");
            sb.append("$(function() {");
            String[] sels = field.getAffect().split(";");
            List<String> selectlist = new ArrayList<String>();
            for (String sel : sels) {
                String[] kv = sel.split(":");
                if (kv != null && kv.length > 1) {
                    selectlist.add(kv[0]);
                }
            }

            if (field.getMasterDataCode() != null) {

                sb.append("$.each($('select[name=\"" + name + "\"]'),function(){");

                for (String selectvalue : selectlist) {
                    sb.append("if($(this).val()==" + selectvalue + "){");
                    for (String sel : sels) {
                        String[] kv = sel.split(":");

                        if (kv != null && kv.length > 1) {
                            if (selectvalue.equals(kv[0])) {
                                String[] inputnames = kv[1].split(",");
                                for (String inputname : inputnames) {
                                    sb.append("$(this).parents('.box').find('[name=\"" + inputname
                                            + "\"]').parents('.row').show();");
                                }
                            } else {
                                String[] inputnames = kv[1].split(",");
                                for (String inputname : inputnames) {
                                    sb.append("$(this).parents('.box').find('[name=\"" + inputname
                                            + "\"]').parents('.row').hide();");
                                }
                            }
                        }
                    }
                    sb.append("}");
                }
                sb.append("});");

                sb.append("$('select[name=\"" + name + "\"]').change(function(){");

                for (String selectvalue : selectlist) {
                    sb.append("if($(this).val()==" + selectvalue + "){");
                    for (String sel : sels) {
                        String[] kv = sel.split(":");

                        if (kv != null && kv.length > 1) {
                            if (selectvalue.equals(kv[0])) {
                                String[] inputnames = kv[1].split(",");
                                for (String inputname : inputnames) {
                                    sb.append("$(this).parents('.box').find('[name=\"" + inputname
                                            + "\"]').parents('.row').show();");
                                }
                            } else {
                                String[] inputnames = kv[1].split(",");
                                for (String inputname : inputnames) {
                                    sb.append("$(this).parents('.box').find('[name=\"" + inputname
                                            + "\"]').parents('.row').hide();");
                                }
                            }
                        }
                    }
                    sb.append("}");
                }

                sb.append("});");
            }

            sb.append("});</script>");
        }

        return sb.toString();
    }

    /**
     * 创建地址选择
     * @param field
     * @return
     * @author sana Date 2014年11月14日
     * @version
     */
    public String createSelectArea(Field field) {
        StringBuffer sb = new StringBuffer();
        String name = field.getName(); // field name
        String display = "";
        if (field.getLangs() != null) {
            for (Lang l : field.getLangs()) {
                if ((l.getValue() != null && l.getValue().equals(lang))||"0".equals(l.getValue())) {
                    display = l.getDisplay();
                }
            }
        }

        String fieldValue = getFieldValue(name); // field value
        StringBuffer checksb = new StringBuffer(); // field check
        String req = "";
        if (field.getChecks() != null && field.getChecks().size() > 0) {
            for (Check check : field.getChecks()) {
                if (check.getRequired() != null) {
                    checksb.append(" required='" + check.getRequired() + "' ");
                    req = "required";
                }

            }
        }

        sb.append("<div class='row'><div class='col-md-3 tn_l " + req + "'>" + display + "</div>");
        sb.append("<div class='col-md-3'><select name='" + name + "' " + checksb.toString() + " >");
        sb.append("<option value=''></option>");
        if (field.getMasterDataCode() != null&&!"".equals(field.getMasterDataCode())) {
            String parentId = field.getMasterDataCode();
            List<AreaEntity> arealist = pageService.getAreaListByParentId(parentId);
            for (AreaEntity areaEntity : arealist) {
                if (fieldValue.equals(areaEntity.getAreaMetadataId().toString())) {
                    sb.append("<option selected='selected' value='"
                            + areaEntity.getAreaMetadataId() + "'>" + areaEntity.getAreaName()
                            + "</option>");
                } else {
                    sb.append("<option value='" + areaEntity.getAreaMetadataId() + "'>"
                            + areaEntity.getAreaName() + "</option>");
                }
            }
        }else{
            if(fieldValue!=null&&!"".equals(fieldValue)){
                String areaMetadataId = fieldValue;
                AreaMetadataEntity area = pageService.getAreaMetadataByMetadataId(areaMetadataId);
                List<AreaEntity> arealist = pageService.getAreaListByParentId(area.getParentId().toString());
                for (AreaEntity areaEntity : arealist) {
                    if (fieldValue.equals(areaEntity.getAreaMetadataId().toString())) {
                        sb.append("<option selected='selected' value='"
                                + areaEntity.getAreaMetadataId() + "'>" + areaEntity.getAreaName()
                                + "</option>");
                    } else {
                        sb.append("<option value='" + areaEntity.getAreaMetadataId() + "'>"
                                + areaEntity.getAreaName() + "</option>");
                    }
                }
            }
        }
        sb.append("</select></div></div>");
        if (field.getAffect() != null) {
            sb.append("<script>");
            sb.append("$(function() {");
            String affect = field.getAffect();
            if (affect.indexOf(",") > -1) {
                String[] afn = affect.split(",");
                int count = 0;
                for (String str : afn) {
                    if (count == 0) {
//                        sb.append("$('select[name=\"" + str + "\"]').change(function(){");
//                        sb.append("$.post('unlimited/getAreaList.action','parentId='+$(this).val(),function(data){");
//                        sb.append("var tr='<option value=\"\"></option>';");
//                        sb.append("$.each(data.arealist,function(name,value){");
//                        sb.append("tr+= '<option value=\"'+value.areaMetadataId+'\">'+value.areaName+'</option>';");
//                        sb.append("});");
//                        sb.append("$(this).parents('.box').find('select[name=\"" + name + "\"]').html(tr);");
//                        sb.append("},'json');");
//                        sb.append("});");
                        
                        sb.append("$('select[name=\"" + str + "\"]').change(function(){");
                        sb.append("var tr='<option value=\"\"></option>';");
                        sb.append("$.ajax({url: 'unlimited/getAreaList.action',data:'parentId='+$(this).val(),async: false,type:'post',success : function(data){");
                        sb.append("$.each(data.arealist,function(name,value){");
                        sb.append("tr+= '<option value=\"'+value.areaMetadataId+'\">'+value.areaName+'</option>';");
                        sb.append("});");
                        sb.append("}});");
                        sb.append("$(this).parents('.box').find('select[name=\"" + name + "\"]').html(tr);");
                        sb.append("});");
                        count = 1;
                    } else {
                        sb.append("$('select[name=\"" + str + "\"]').change(function(){");
                        sb.append("var tr='<option value=\"\"></option>';");
                        sb.append("$(this).parents('.box').find('select[name=\"" + name + "\"]').html(tr);");
                        sb.append("});");
                    }
                }
            } else {

                sb.append("$('select[name=\"" + field.getAffect() + "\"]').change(function(){");
                sb.append("var tr='<option value=\"\"></option>';");
                sb.append("$.ajax({url: 'unlimited/getAreaList.action',data:'parentId='+$(this).val(),async: false,type:'post',success : function(data){");
                sb.append("$.each(data.arealist,function(name,value){");
                sb.append("tr+= '<option value=\"'+value.areaMetadataId+'\">'+value.areaName+'</option>';");
                sb.append("});");
                sb.append("}});");
                sb.append("$(this).parents('.box').find('select[name=\"" + name + "\"]').html(tr);");
                sb.append("});");

            }

            sb.append("});</script>");
        }

        return sb.toString();
    }

    /**
     * 创建多行输入框
     * @param field
     * @return
     * @author whh Date 2014年11月14日
     * @version
     */
    public String createTextarea(Field field) {
        StringBuffer sb = new StringBuffer();
        String name = field.getName(); // field name
        String display = "";
        if (field.getLangs() != null) {
            for (Lang l : field.getLangs()) {
                if ((l.getValue() != null && l.getValue().equals(lang))||"0".equals(l.getValue())) {
                    display = l.getDisplay();
                }
            }
        }
        String fieldValue = getFieldValue(name); // field value
        StringBuffer checksb = new StringBuffer(); // field check
        String req = "";
        if (field.getChecks() != null && field.getChecks().size() > 0) {
            for (Check check : field.getChecks()) {
                if ("1".equals(lang)) {
                    if (check.getRequired() != null) {
                        checksb.append(" required='" + check.getRequired() + "' ");
                        req = "required";
                    }
                }
                if (check.getMaxLength() != null) {
                    checksb.append(" maxlength='" + check.getMaxLength() + "' ");
                }
                if (check.getIsUnique() != null) {

                    checksb.append(" isFieldValueUnique='" + lang + "' ");

                }
                if (check.getPattern() != null) {
                    checksb.append(" pattern='" + check.getPattern() + "' ");
                }

            }
        }

        sb.append("<div class='row'><div class='col-md-3 tn_l " + req + "'>" + display + "</div>");
        sb.append("<div class='col-md-9'><textarea  rows='4' class='textarea' name='" + name + "' "
                + checksb.toString() + ">" + fieldValue + "</textarea></div></div>");

        return sb.toString();
    }

    /**
     * 创建复杂类型的字段
     * @param field 需要创建的filed
     * @return
     * @author whh Date 2014-11-14
     * @version
     */
    public String createComplicated(Field field) {
        StringBuffer sb = new StringBuffer();
        String name = field.getName(); // field name
        String display = "";
        if (field.getLangs() != null) {
            for (Lang l : field.getLangs()) {
                if ((l.getValue() != null && l.getValue().equals(lang))||"0".equals(l.getValue())) {
                    display = l.getDisplay();
                }
            }
        }
        String fieldValue = getFieldValue(name); // field value
        StringBuffer checksb = new StringBuffer(); // field check
        String req = "";
        if (field.getChecks() != null && field.getChecks().size() > 0) {
            for (Check check : field.getChecks()) {
                if ("1".equals(lang)) {
                    if (check.getRequired() != null) {
                        checksb.append(" required='" + check.getRequired() + "' ");
                        req = "required";
                    }
                }
                if (check.getMaxLength() != null) {
                    checksb.append(" maxlength='" + check.getMaxLength() + "' ");
                }
                if (check.getIsUnique() != null) {
                    checksb.append(" isFieldValueUnique='" + lang + "' ");
                }
                if (check.getPattern() != null) {
                    checksb.append(" pattern='" + check.getPattern() + "' ");
                }

            }
        }

        sb.append("<div class='row choose_con'><div class='col-md-3 " + req + "' >" + display
                + "</div>");
        sb.append("<div class='col-md-7 border'><input type='hidden' id='hiddencontent"
                + name.replace(" ", "") + lang + "' name='" + name + "' value='" + fieldValue
                + "'><input id='showcontent" + name.replace(" ", "") + lang
                + "' readonly='readonly' value='" + getFileAssetName(fieldValue) + "' "
                + checksb.toString()
                + "/></div><div class='col-md-2 border'><a href='#'  class='choose tn_c' id='"
                + name + lang + "choose'>Choose</a></div></div>");

        sb.append("<script>");
        sb.append("$('a[id=\"" + name + lang + "choose\"]').on('click', function(){");

        // if(field.getAffect()!=null){
        // sb.append("if($(this).parents('.box').find('select[name=\""+field.getAffect()+"\"]').val()==''){");
        // sb.append("alert('Type required');");
        // sb.append("return;");
        // sb.append("}");
        // //
        // sb.append("var masterdataid = $(this).parents('.box').find('select[name=\""+field.getAffect()+"\"]').val();");
        // sb.append("openDataType('10090001','hiddencontent"+name.replace(" ",
        // "")+lang+"','1','showcontent"+name.replace(" ", "")+lang+"');");
        // }else{
        if (field.getDataTypeRef() != null) {
            sb.append("openDataType('" + field.getDataTypeRef() + "','hiddencontent"
                    + name.replace(" ", "") + lang + "','1','showcontent" + name.replace(" ", "")
                    + lang + "');");
        }
        // }

        sb.append("});");
        sb.append("</script>");

        return sb.toString();
    }

    /**
     * 创建radio选择框
     * @param field
     * @return
     * @author whh Date 2014年11月14日
     * @version
     */
    public String createRadio(Field field) {
        StringBuffer sb = new StringBuffer();
        String name = field.getName(); // field name
        String display = "";
        if (field.getLangs() != null) {
            for (Lang l : field.getLangs()) {
                if ((l.getValue() != null && l.getValue().equals(lang))||"0".equals(l.getValue())) {
                    display = l.getDisplay();
                }
            }
        }
        String fieldValue = getFieldValue(name); // field value
        StringBuffer checksb = new StringBuffer(); // field check
        String req = "";
        if (field.getChecks() != null && field.getChecks().size() > 0) {
            for (Check check : field.getChecks()) {
                if ("1".equals(lang)) {
                    if (check.getRequired() != null) {
                        checksb.append(" required='" + check.getRequired() + "' ");
                        req = "required";
                    }
                }
                if (check.getMaxLength() != null) {
                    checksb.append(" maxlength='" + check.getMaxLength() + "' ");
                }
                if (check.getIsUnique() != null) {
                    checksb.append(" isFieldValueUnique='" + lang + "' ");
                }
                if (check.getPattern() != null) {
                    checksb.append(" pattern='" + check.getPattern() + "' ");
                }

            }
        }

        sb.append("<div class='row'><div class='col-md-3 tn_l " + req + "'>" + display + "</div>");
        sb.append("<div class='col-md-9'>");
        if (field.getMasterDataCode() != null) {
            List<MasterdataEntity> sellist = pageService.getMasterdataByName(
                    field.getMasterDataCode(), lang);
            for (MasterdataEntity masterdataEntity : sellist) {
                if (fieldValue.equals(masterdataEntity.getMasterdataMetadataId().toString())) {
                    sb.append("<input checked='checked' style='width:30px;height:15px;'  "
                            + checksb.toString() + "  type='radio' name='" + name + "' value='"
                            + masterdataEntity.getMasterdataMetadataId() + "'/>"
                            + masterdataEntity.getMasterdataName());

                } else {
                    sb.append("<input style='width:30px;height:15px;'  " + checksb.toString()
                            + "  type='radio' name='" + name + "' value='"
                            + masterdataEntity.getMasterdataMetadataId() + "'/>"
                            + masterdataEntity.getMasterdataName());

                }
            }
        } else {
            if (field.getNumeration() != null && field.getNumeration().size() > 0) {
                for (Numeration num : field.getNumeration()) {
                    if (fieldValue.equals(num.getOption())) {
                        sb.append("<input checked='checked' style='width:30px;height:15px'  "
                                + checksb.toString() + "  type='radio' name='" + name + "' value='"
                                + num.getOption() + "'/>" + num.getValue());
                    } else {
                        sb.append("<input style='width:30px;height:15px'  " + checksb.toString()
                                + "  type='radio' name='" + name + "' value='" + num.getOption()
                                + "'/>" + num.getValue());
                    }
                }
            }
        }
        sb.append("</div></div>");
        // field与其他field的联动
        if (field.getAffect() != null) {
            sb.append("<script>");
            sb.append("$('input[name=\"" + name + "\"]').change(function(){");
            sb.append("$(this).parents('.box').find('input[name=\"" + field.getAffect()
                    + "\"]').val('');");
            sb.append("});</script>");
        }

        return sb.toString();
    }

    /**
     * 创建checkbox选择框
     * @param field
     * @return
     * @author sana Date 2014年11月14日
     * @version
     */
    public String createCheckBox(Field field) {
        StringBuffer sb = new StringBuffer();
        String name = field.getName(); // field name
        String fieldValue = getFieldValue(name); // field value
        String display = "";
        if (field.getLangs() != null) {
            for (Lang l : field.getLangs()) {
                if ((l.getValue() != null && l.getValue().equals(lang))||"0".equals(l.getValue())) {
                    display = l.getDisplay();
                }
            }
        }
        StringBuffer checksb = new StringBuffer(); // field check
        String req = "";
        if (field.getChecks() != null && field.getChecks().size() > 0) {
            for (Check check : field.getChecks()) {
                if ("1".equals(lang)) {
                    if (check.getRequired() != null) {
                        checksb.append(" required='" + check.getRequired() + "' ");
                        req = "required";
                    }
                }
                if (check.getMaxLength() != null) {
                    checksb.append(" maxlength='" + check.getMaxLength() + "' ");
                }
                if (check.getIsUnique() != null) {
                    checksb.append(" isFieldValueUnique='" + lang + "' ");
                }
                if (check.getPattern() != null) {
                    checksb.append(" pattern='" + check.getPattern() + "' ");
                }

            }
        }

        // 默认随便选
        int maxselect = -1;
        if (field.getEditoTypeAttributes() != null && !"".equals(field.getEditoTypeAttributes())) {
            if (field.getEditoTypeAttributes().indexOf("=") > -1) {
                String[] selectnum = field.getEditoTypeAttributes().split("=");
                if ("maxSelect".equals(selectnum[0])) {

                    // 可以选择selectnum[1]个
                    if (isNumeric(selectnum[1])) {
                        maxselect = Integer.parseInt(selectnum[1]);
                    }

                }
            }

        }

        sb.append("<div class='row'><div class='col-md-3 tn_l " + req + "'>" + display + "</div>");
        sb.append("<div class='col-md-9'>");
        if (field.getMasterDataCode() != null) {
            List<MasterdataEntity> sellist = pageService.getMasterdataByName(
                    field.getMasterDataCode(), lang);
            for (MasterdataEntity masterdataEntity : sellist) {
                if (fieldValue.indexOf(",") > -1) {
                    if (fieldValue.indexOf(","
                            + masterdataEntity.getMasterdataMetadataId().toString() + ",") > -1) {
                        sb.append("<input checked='checked' style='width:30px;height:15px;'  "
                                + checksb.toString() + "  type='checkbox' name='" + name
                                + "' value='" + masterdataEntity.getMasterdataMetadataId() + "'/>"
                                + masterdataEntity.getMasterdataName());
                    } else {
                        sb.append("<input  style='width:30px;height:15px;'  " + checksb.toString()
                                + "  type='checkbox' name='" + name + "' value='"
                                + masterdataEntity.getMasterdataMetadataId() + "'/>"
                                + masterdataEntity.getMasterdataName());
                    }

                } else {
                    if (fieldValue.equals(masterdataEntity.getMasterdataMetadataId().toString())) {
                        sb.append("<input checked='checked' style='width:30px;height:15px;'  "
                                + checksb.toString() + "  type='checkbox' name='" + name
                                + "' value='" + masterdataEntity.getMasterdataMetadataId() + "'/>"
                                + masterdataEntity.getMasterdataName());
                    } else {
                        sb.append("<input style='width:30px;height:15px;'  " + checksb.toString()
                                + "  type='checkbox' name='" + name + "' value='"
                                + masterdataEntity.getMasterdataMetadataId() + "'/>"
                                + masterdataEntity.getMasterdataName());
                    }
                }
            }
        } else {
            if (field.getNumeration() != null && field.getNumeration().size() > 0) {
                for (Numeration num : field.getNumeration()) {
                    if (fieldValue.indexOf(",") > -1) {
                        if (fieldValue.indexOf("," + num.getOption() + ",") > -1) {
                            sb.append("<input checked='checked' style='width:30px;height:15px;'  "
                                    + checksb.toString() + "  type='checkbox' name='" + name
                                    + "' value='" + num.getOption() + "'/>" + num.getValue());
                        } else {
                            sb.append("<input  style='width:30px;height:15px;'  "
                                    + checksb.toString() + "  type='checkbox' name='" + name
                                    + "' value='" + num.getOption() + "'/>" + num.getValue());
                        }

                    } else {
                        if (fieldValue.equals(num.getOption())) {
                            sb.append("<input checked='checked' style='width:30px;height:15px;'  "
                                    + checksb.toString() + "  type='checkbox' name='" + name
                                    + "' value='" + num.getOption() + "'/>" + num.getValue());
                        } else {
                            sb.append("<input style='width:30px;height:15px;'  "
                                    + checksb.toString() + "  type='checkbox' name='" + name
                                    + "' value='" + num.getOption() + "'/>" + num.getValue());
                        }
                    }
                }
            }
        }
        sb.append("</div></div>");
        if (maxselect != -1) {
            sb.append("<script>");
            sb.append("$('input[type=checkbox][name=\"" + name + "\"]').on('change', function(){");
            sb.append("if($(this).parents('.box').find('input[type=checkbox][name=\"" + name
                    + "\"]:checked').length > " + maxselect + " ) {");
            sb.append("$(this).removeAttr('checked');");
            sb.append("alert('最多能选" + maxselect + "个');");
            sb.append("}");
            sb.append("});");
            sb.append("</script>");
        }
        return sb.toString();
    }

    /**
     * 创建富文本编辑器
     * @param field 需要创建的filed
     * @return
     * @author whh Date 2014-11-14
     * @version
     */
    public String createRichText(Field field) {

        String name = field.getName(); // field name

        StringBuffer sb = new StringBuffer();
        String display = "";
        if (field.getLangs() != null) {
            for (Lang l : field.getLangs()) {
                if ((l.getValue() != null && l.getValue().equals(lang))||"0".equals(l.getValue())) {
                    display = l.getDisplay();
                }
            }
        }
        String fieldValue = getFieldValue(name); // field value
        StringBuffer checksb = new StringBuffer(); // field check
        String req = "";
        String maxlength = "";
        if (field.getChecks() != null && field.getChecks().size() > 0) {
            for (Check check : field.getChecks()) {
                if (lang == null || "1".equals(lang)) {
                    if (check.getRequired() != null) {
                        checksb.append(" required='" + check.getRequired() + "' ");
                        req = "required";
                    }
                }
                if (check.getMaxLength() != null) {
                    checksb.append(" maxlength='" + check.getMaxLength() + "' ");
                    maxlength = check.getMaxLength();
                }
                if (check.getIsUnique() != null) {
                    checksb.append(" isFieldValueUnique='" + lang + "' ");
                }
                if (check.getPattern() != null) {
                    checksb.append(" pattern='" + check.getPattern() + "' ");
                }

            }
        }

        sb.append("<div class='row choose_con'><div class='col-md-3 " + req + "'> ");
        sb.append(display);
        sb.append("</div><div class='col-md-9 border'>");
        sb.append("<textarea rows='4' class='richtext'  id='list_content" + name.replace(" ", "")
                + lang + "' name='" + name + "' " + checksb.toString() + " >" + fieldValue
                + "</textarea>");
        sb.append("</div></div>");
        sb.append("<script>");
        if (isEdit != null && "0".equals(isEdit)) {
            if ("".equals(maxlength)) {
                sb.append(" var editor_" + name.replace(" ", "") + lang
                        + " = UeditorForContentOnlyRead.init('list_content" + name.replace(" ", "")
                        + lang + "'); ");
            } else {
                sb.append(" var editor_" + name.replace(" ", "") + lang
                        + " = UeditorForContentOnlyRead.init('list_content" + name.replace(" ", "")
                        + lang + "'," + maxlength + "); ");
            }
        } else {
            if ("".equals(maxlength)) {
                sb.append(" var editor_" + name.replace(" ", "") + lang
                        + " = UeditorForContent.init('list_content" + name.replace(" ", "") + lang
                        + "'); ");
            } else {
                sb.append(" var editor_" + name.replace(" ", "") + lang
                        + " = UeditorForContent.init('list_content" + name.replace(" ", "") + lang
                        + "'," + maxlength + "); ");
            }
        }

        sb.append("</script>");

        return sb.toString();
    }

    /**
     * 根据字段名称获取字段值
     * @param fieldName 字段名称
     * @return
     * @author kangmin_Qu Date 2014-11-3
     * @version
     */
    public String getFieldValue(String fieldName) {
        if (lang != null && !"".equals(lang)) {
            if (contentId != null && !"".equals(contentId)) {
                String value = pageService.getLangFieldValue(Integer.parseInt(contentId),
                        Integer.parseInt(lang), fieldName);
                if (value != null && !"".equals(value)) {
                    return value;
                }
            }
        } else {
            if (contentId != null && !"".equals(contentId)) {
                String value = pageService.getLangFieldValue(Integer.parseInt(contentId), -1,
                        fieldName);
                if (value != null && !"".equals(value)) {
                    return value;
                }
            }
        }
        return "";
    }

    public String getContent(String conId) {
        if (conId != null && !"".equals(conId)) {
            //DataTypePojo dataTypePojo = pageService.geteDataTypeById(Integer.parseInt(datatypeId));
            //ListDataType dataTypeList = ParseXml.parseListXml(dataTypePojo.getListDefinationXML());
            ListDataType dataTypeList = (ListDataType) CommonConstants.DATATYPE_DEFINATION_XML_HASHMAP.get(datatypeId).get(0);
            if (dataTypeList != null && dataTypeList.getDisplayFields() != null
                    && dataTypeList.getDisplayFields().size() > 0) {
                String fieldname = dataTypeList.getDisplayFields().get(0);
                return pageService.getLangFieldValue(Integer.parseInt(conId), 1, fieldname);
            }
        }

        return "";
    }

    public String getFileAssetName(String fileAssetIds) {

        if (contentId != null && !"".equals(contentId)) {
            List<Map<String, Object>> fileasset = pageService.getFileAssetName("," + fileAssetIds
                    + ",");
            if (fileasset != null && fileasset.size() > 0) {
                StringBuffer sb = new StringBuffer();
                for (Map<String, Object> map : fileasset) {
                    sb.append(map.get("FILE_ASSET_NAME"));
                    sb.append(",");
                }
                String str = sb.toString();
                if (str != null && !"".equals(str)) {
                    return sb.substring(0, str.length() - 1);
                }
            }
        }

        // if(fileAssetId!=null&&!"".equals(fileAssetId)){
        //
        // }

        return "";
    }

    /**
     * 创建content选择器
     * @param field 需要创建的filed
     * @return
     * @author kangmin_Qu Date 2014-11-3
     * @version
     */
    public String createContentPicker(Field field) {

        StringBuffer sb = new StringBuffer();

        String name = field.getName(); // file name
        String id = field.getDataTypeRef(); // DataType master Id
        String fieldValue = getFieldValue(name); // field value
        String display = "";
        if (field.getLangs() != null) {
            for (Lang l : field.getLangs()) {
                if ((l.getValue() != null && l.getValue().equals(lang))||"0".equals(l.getValue())) {
                    display = l.getDisplay();
                }
            }
        }
        StringBuffer checksb = new StringBuffer(); // field check
        String req = "";
        if (field.getChecks() != null && field.getChecks().size() > 0) {
            for (Check check : field.getChecks()) {
                if ("1".equals(lang)) {
                    if (check.getRequired() != null) {
                        checksb.append(" required='" + check.getRequired() + "' ");
                        req = "required";
                    }
                }
                if (check.getMaxLength() != null) {
                    checksb.append(" maxlength='" + check.getMaxLength() + "' ");
                }
                if (check.getIsUnique() != null) {
                    checksb.append(" isFieldValueUnique='" + lang + "' ");
                }
                if (check.getPattern() != null) {
                    checksb.append(" pattern='" + check.getPattern() + "' ");
                }

            }
        }
        // 默认选一个
        int maxselect = 1;
        if (field.getEditoTypeAttributes() != null && !"".equals(field.getEditoTypeAttributes())) {
            if (field.getEditoTypeAttributes().indexOf("=") > -1) {
                String[] selectnum = field.getEditoTypeAttributes().split("=");
                if ("maxSelect".equals(selectnum[0])) {
                    // 可以选择无数个
                    if ("*".equals(selectnum[1])) {
                        maxselect = -1;
                    } else {
                        // 可以选择selectnum[1]个
                        if (isNumeric(selectnum[1])) {
                            maxselect = Integer.parseInt(selectnum[1]);
                        }
                    }
                }
            }

        }

        sb.append("<div class='row choose_con'><div class='col-md-3 " + req + "'> ");
        sb.append(display);
        sb.append("</div><div class='col-md-7 border'><input type='hidden' name='" + name
                + "' id='hidden" + name + "' value='" + fieldValue
                + "' /><input readonly='readonly' " + checksb.toString() + "  id='show" + name
                + "' value='" + getContent(fieldValue) + "'/></div>");
        sb.append("<div class='col-md-2'><a href='#' onclick=\"openDataTypePicker('" + id
                + "','hidden" + name + "','show" + name + "','" + maxselect
                + "');\" class='choose tn_c'>Choose</a></div></div>");
        return sb.toString();
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    public int doEndTag() {
        return SKIP_BODY;// 标签执行完后，不继续执行后面的
    }

}
