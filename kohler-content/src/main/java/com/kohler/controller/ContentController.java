/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */

package com.kohler.controller;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kohler.bean.EditDataType;
import com.kohler.bean.ListDataType;
import com.kohler.constants.CommonConstants;
import com.kohler.dao.utils.Pager;
import com.kohler.entity.AreaEntity;
import com.kohler.entity.ContentFieldValues;
import com.kohler.entity.ContentMetadataEntity;
import com.kohler.entity.DatatypeEntity;
import com.kohler.entity.LocaleEntity;
import com.kohler.entity.MasterdataEntity;
import com.kohler.service.ContentService;
import com.kohler.service.LocaleService;
import com.kohler.util.JSonUtil;
import com.kohler.util.PropertiesUtils;

/**
 * 用于处理Content的相关的请求
 * 
 * @author WHH
 * @Date 2014年10月28日
 */
@Controller
@RequestMapping(value = "/content")
public class ContentController extends BasicController {

    @Autowired
    private ContentService  contentService;
    
    @Autowired
    public LocaleService localeService;

    /**
     * @param model
     * @param pager
     * @param datatypeId
     * @param request
     * @return
     * @author WHH Date 2014年10月28日
     * @version
     * @throws UnsupportedEncodingException 
     * @throws ParseException 
     */
    @RequestMapping(value = "/contentList")
    public String contentList(Model model, Pager<Map<String, Object>> pager, Integer datatypeId,String selStr, 
            HttpServletRequest request) throws UnsupportedEncodingException, ParseException {
        if (logger.isInfoEnabled()) {
            logger.info("ContentController.contentList start.");
        }
        List<Map<String,String>> mlist = new ArrayList<Map<String,String>>();
        if(selStr!=null&&!"".equals(selStr)){
            String[] ss = selStr.split("&");
            for (String string : ss) {
                String[] skv = string.split("=");
                if(skv!=null&&skv.length>1){
                    Map<String,String> m = new HashMap<String,String>();
                    m.put("fieldname", skv[0]);
                    m.put("fieldvalue", URLDecoder.decode(skv[1],"utf-8"));
                    mlist.add(m);
                }
            }
        }
        model.addAttribute("mlist", mlist);
        
        String uri = request.getRequestURI()+"?";
        String queryString = request.getQueryString();
        //判断是否有查询条件
        if(queryString != null){
            //判断是否有页数
            if(queryString.indexOf("currentPage=") > 0){
                queryString = queryString.substring(0,queryString.indexOf("&currentPage="));
            }
            uri = request.getRequestURI() + "?"+queryString+"&";
            if(queryString.indexOf("currentPage=") == 0){
                uri = request.getRequestURI() + "?";
            }
            
        }
        List<Map<String, Object>> datatypeTree = contentService.getContentWithMap();
        if (datatypeId == null) {
            if(datatypeTree!=null&&datatypeTree.size()>0&&datatypeTree.get(0).get("id")!=null){
                datatypeId = Integer.parseInt(datatypeTree.get(0).get("id").toString());
            }else{
                datatypeId = 1;
            }
        }
        //获取当前选择的datatype
        DatatypeEntity d = contentService.getDatatypeById(datatypeId);
        if(d==null){
            d = new DatatypeEntity();
        }
        if(d.getDatatypeName() == null){
            d.setDatatypeName("Content");
        }
        List<String> fieldlist = new ArrayList<String>();
        ListDataType dataTypeList = null;
        if(datatypeId!=1){
            dataTypeList = (ListDataType) CommonConstants.DATATYPE_DEFINATION_XML_HASHMAP.get(String.valueOf(datatypeId)).get(0);
            if(dataTypeList != null){
                fieldlist = dataTypeList.getDisplayFields();
            }
        }
        //得到列表页
        pager = contentService.getContentPageById(pager, datatypeId,fieldlist,selStr);
        //List<Map<String, Object>> datatypeTree = contentService.getContentWithMap();
        pager.setUrl(uri);
        model.addAttribute("datatypeId", datatypeId);
        model.addAttribute("fieldlist", fieldlist);
        model.addAttribute("d", d);
        model.addAttribute("pager", pager);
        model.addAttribute("datatypeTree", JSonUtil.toJSonString(datatypeTree));
        
        if (logger.isInfoEnabled()) {
            logger.info("ContentController.contentList end.");
        }
        return "content/contentList";
    }

    /**
     * @param model
     * @param datatypeId
     * @return
     * @author WHH Date 2014年10月29日
     * @version
     */
    @RequestMapping(value = "/create")
    public String create(Model model, Integer datatypeId) {
        DatatypeEntity d = contentService.getDatatypeById(datatypeId);
        EditDataType dataTypeList = null;
        dataTypeList = (EditDataType) CommonConstants.DATATYPE_DEFINATION_XML_HASHMAP.get(String.valueOf(datatypeId)).get(1);
        
        if(d.getDatatypeName()==null){
            d.setDatatypeName("Content");
        }
        
        ContentMetadataEntity maxcontent = contentService.getMaxContentMetadata();
        int newid = 1;
        if(maxcontent!=null){
            newid = maxcontent.getContentMetadataId()+1;
        }
        //获取语言种类
        List<LocaleEntity> languages = localeService.getLanguages();
        model.addAttribute("languages", languages);
        model.addAttribute("d", d);
        model.addAttribute("datatypeId", datatypeId);
        model.addAttribute("dataTypeList", dataTypeList);
        model.addAttribute("newid", newid);
        return "content/contentNew";
    }
    
    /**
     * Add Page Object
     * @param ContentMetadataEntity
     * @return
     * @author WHH Date 2014年10月29日
     * @version
     * @throws UnsupportedEncodingException 
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/createSave")
    @ResponseBody
    public Map<String, Object> createSave(ContentMetadataEntity contentMetadata, String selStr, String data) throws UnsupportedEncodingException {
        if (logger.isInfoEnabled()) {
            logger.info("ContentController.createSave begin. ");
        }
        JSONArray contentLangArray = JSONArray.fromObject(data);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < contentLangArray.size(); i++) {
            list.add(JSonUtil.toObject(contentLangArray.get(i).toString(), HashMap.class));
        }
        
        
        Map<String, Object> result = new HashMap<String, Object>();
        String msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_CONTENT_CREATE_FAILED);
        contentMetadata.setIsEnable(true);
        int isSuccess = contentService.saveContent(contentMetadata,selStr,list);
        //判断是否操作成功
        if (isSuccess == 1) {
            msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_CONTENT_CREATE_SUCCESS);
        }

        result.put("msg", msg);
        if (logger.isInfoEnabled()) {
            logger.info("ContentController.createSave end. ");
        }
        return result;
    }
    
    /**
     * @param model
     * @param isEdit
     * @param contentMetadataId
     * @return
     * @author WHH Date 2014年10月29日
     * @version
     */
    @RequestMapping(value = "/edit")
    public String edit(Model model, ContentMetadataEntity contentMetadata, String isEdit) {
        contentMetadata = contentService.getContentMetadataById(contentMetadata.getContentMetadataId());
        List<ContentFieldValues> contentFieldValuesList = contentService.getContentFieldValuesByMetadataId(contentMetadata.getContentMetadataId());
        
        DatatypeEntity d = contentService.getDatatypeById(contentMetadata.getDatatypeId());
        EditDataType dataTypeList = null;
        dataTypeList = (EditDataType) CommonConstants.DATATYPE_DEFINATION_XML_HASHMAP.get(contentMetadata.getDatatypeId().toString()).get(1);
        if(d.getDatatypeName() == null){
            d.setDatatypeName("Content");
        }
        //获取语言种类
        List<LocaleEntity> languages = localeService.getLanguages();
        model.addAttribute("languages", languages);
        model.addAttribute("d", d);
        model.addAttribute("datatypeId", contentMetadata.getDatatypeId());
        model.addAttribute("dataTypeList", dataTypeList);
        model.addAttribute("contentMetadata", contentMetadata);
        model.addAttribute("contentFieldValuesList", contentFieldValuesList);
        model.addAttribute("isEdit", isEdit);
        return "content/contentEdit";
    }
    
    /**
     * Add Page Object
     * @param ContentMetadataEntity
     * @return
     * @author WHH Date 2014年10月29日
     * @version
     * @throws UnsupportedEncodingException 
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/editSave")
    @ResponseBody
    public Map<String, Object> editSave(ContentMetadataEntity contentMetadata, String selStr, String data) throws UnsupportedEncodingException {
        if (logger.isInfoEnabled()) {
            logger.info("ContentController.createSave begin. ");
        }
        JSONArray contentLangArray = JSONArray.fromObject(data);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < contentLangArray.size(); i++) {
            list.add(JSonUtil.toObject(contentLangArray.get(i).toString(), HashMap.class));
        }
        
        Map<String, Object> result = new HashMap<String, Object>();
        String msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_CONTENT_EDIT_FAILED);
        contentMetadata.setIsEnable(true);
        int isSuccess = contentService.saveContent(contentMetadata,selStr,list);
        //判断是否更新成功
        if (isSuccess == 1) {
            msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_CONTENT_EDIT_SUCCESS);
        }

        result.put("msg", msg);
        if (logger.isInfoEnabled()) {
            logger.info("ContentController.createSave end. ");
        }
        return result;
    }
    
    /**
     * @param data
     * @return
     * @author WHH Date 2014年10月30日
     * @version
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Map<String, Object> delete(String data) {

        if (logger.isInfoEnabled()) {
            logger.info("ContentController.delete begin. ");
        }

        Map<String, Object> result = new HashMap<String, Object>();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        String msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_DELETE_SUCCESS);

        JSONArray sectionPagesArray = JSONArray.fromObject(data);
        for (int i = 0; i < sectionPagesArray.size(); i++) {
            list.add(JSonUtil.toObject(sectionPagesArray.get(i).toString(), HashMap.class));
        }

        contentService.deleteContent(list);

        result.put("msg", msg);

        if (logger.isInfoEnabled()) {
            logger.info("ContentController.delete end.");
        }

        return result;
    }
    
    /**
     * 获取select
     * @param masterdataTypeName
     * @param lang
     * @return
     * @author WHH Date 2014-10-31
     * @version
     */
    @RequestMapping(value = "/unlimited/getMasterDateByName")
    @ResponseBody
    public Map<String, Object>  dataTypePickerPage(String masterdataTypeName, String lang) {

        if (logger.isInfoEnabled()) {
            logger.info("ContentController.getMasterDateByName begin.");
        }
        Map<String, Object> result = new HashMap<String, Object>();
        List<MasterdataEntity> list = contentService.getMasterdataByName(masterdataTypeName,lang);
        result.put("result", list);
        if (logger.isInfoEnabled()) {
            logger.info("ContentController.getMasterDateByName end.");
        }
        return result;
    }
    
    /**
     * 检测唯一性
     * @param model model
     * @return
     * @author WHH Date 2014-11-04
     * @version
     */
    @RequestMapping(value = "/unlimited/getAreaList")
    @ResponseBody
    public Map<String, Object> getAreaList(String parentId) {

        if (logger.isInfoEnabled()) {
            logger.info("ContentController.getAreaList begin.");
        }
        Map<String, Object> result = new HashMap<String, Object>();
        //检测是否存在
        List<AreaEntity> arealist = contentService.getAreaListByParentId(parentId);
        result.put("arealist", arealist);
        if (logger.isInfoEnabled()) {
            logger.info("ContentController.getAreaList end.");
        }
        return result;
    }
 
    /**
     * 检测唯一性
     * @param model model
     * @return
     * @author WHH Date 2014-11-04
     * @version
     */
    @RequestMapping(value = "/unlimited/checkContentValue")
    @ResponseBody
    public Map<String, Object> checkContentValue(String fieldname, String fieldvalue, String lang, String metadataId, String datatypeId) {

        if (logger.isInfoEnabled()) {
            logger.info("ContentController.checkContentValue begin.");
        }
        int size = 0;
        Map<String, Object> result = new HashMap<String, Object>();
        //检测是否存在
        List<ContentFieldValues> list = contentService.checkContentValue(fieldname,fieldvalue,lang,metadataId,datatypeId);
        if(list!=null){
            size = list.size();
        }
        result.put("size", size);
        if (logger.isInfoEnabled()) {
            logger.info("ContentController.checkContentValue end.");
        }
        return result;
    }
}
