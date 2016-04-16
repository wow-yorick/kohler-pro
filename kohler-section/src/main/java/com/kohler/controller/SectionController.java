/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kohler.bean.EditDataType;
import com.kohler.bean.Field;
import com.kohler.bean.ListDataType;
import com.kohler.bean.PublishConf;
import com.kohler.constants.CommonConstants;
import com.kohler.dao.utils.Pager;
import com.kohler.dao.utils.UserSession;
import com.kohler.entity.MasterDataCodeConstant;
import com.kohler.entity.MasterdataEntity;
import com.kohler.entity.PublishFolderEntity;
import com.kohler.entity.SectionEntity;
import com.kohler.entity.TemplateEntity;
import com.kohler.entity.extend.PagePojo;
import com.kohler.framework.util.ParseXml;
import com.kohler.pojo.DataTypePojo;
import com.kohler.service.PageService;
import com.kohler.service.PublishFolderService;
import com.kohler.service.PublishHtmlForInterfaceService;
import com.kohler.service.SectionService;
import com.kohler.util.JSonUtil;
import com.kohler.util.PropertiesUtils;

/**
 * 用于处理Section/Pages的相关的请求
 * 
 * @author shefeng
 * @Date 2014年9月25日
 */
@Controller
@RequestMapping(value = "/section")
public class SectionController extends BasicController {

    // SectionService Interface
    @Autowired
    private SectionService                 sectionService;

    // PublishFolderService Interface
    @Autowired
    private PublishFolderService           publishFolderService;

    // PageService Interface
    @Autowired
    private PageService                    pageService;

    // PublishHtmlForInterfaceService Interface
    @Autowired
    private PublishHtmlForInterfaceService publishHtmlForInterfaceService;

    // UserSession 类
    @Autowired
    private UserSession                    userSession;

    /**
     * section 列表
     * 
     * @param model
     * @param pager
     * @param sectionId
     * @param request
     * @return
     * @author shefeng Date 2014年9月30日
     * @version
     */
    @RequestMapping(value = "/sectionList")
    public String sectionList(Model model, Pager<Map<String, Object>> pager, Integer sectionId,
            HttpServletRequest request) {

        String uri = request.getRequestURI() + "?";

        if (sectionId == null) {
            sectionId = 0;
        }
        uri += "sectionId=" + sectionId + "&";

        pager = sectionService.getSectionPageById(pager, sectionId);
        List<Map<String, Object>> sectionTree = sectionService.getSectionWithMap();

        pager.setUrl(uri);
        model.addAttribute("sectionId", sectionId);
        model.addAttribute("pager", pager);
        model.addAttribute("sectionTree", JSonUtil.toJSonString(sectionTree));
        return "sectionpages/sectionList";
    }

    /**
     * 打开section创建页面 点击new section调用
     * 
     * @param model
     * @param section
     * @return
     * @author shefeng Date 2014年9月30日
     * @version
     */
    @RequestMapping(value = "/create")
    public String newSectionPage(Model model, SectionEntity section) {
        section.setIsEnable(false);

        section = sectionService.addSection(section);
        List<PublishFolderEntity> publishFolders = publishFolderService.getPublishFolders();

        model.addAttribute("publishFolders", publishFolders);
        model.addAttribute("section", section);
        return "sectionpages/sectionNew";
    }

    /**
     * Section保存
     * 
     * @param section
     * @return
     * @author shefeng Date 2014年9月30日
     * @version
     */
    @RequestMapping(value = "/createSave")
    @ResponseBody
    public Map<String, Object> addSection(SectionEntity section) {
        logger.info("In addSection Section =" + section);
        Map<String, Object> result = new HashMap<String, Object>();
        String msg = "Section Create Failed!";

        section.setIsEnable(true);
        int success = sectionService.editSection(section);
        if (success == 1) {
            msg = "Section Create Success!";
        }

        result.put("msg", msg);
        return result;
    }

    /**
     * 打开section编辑页面
     * 
     * @param model
     * @param isEdit
     * @param sectionId
     * @return
     * @author shefeng Date 2014年9月30日
     * @version
     */
    @RequestMapping(value = "/edit")
    public String editSectionPage(Model model, String isEdit, Integer sectionId, String parentName) {
        SectionEntity section = sectionService.getSectionById(sectionId);
        SectionEntity parentSection = sectionService.getSectionById(section.getParentId());
        List<PublishFolderEntity> publishFolders = publishFolderService.getPublishFolders();
        if (parentSection == null) {
            parentSection = new SectionEntity();
            parentSection.setSectionId(0);
            parentSection.setSectionName(parentName);
        }
        model.addAttribute("publishFolders", publishFolders);
        model.addAttribute("isEdit", isEdit);
        model.addAttribute("parentSection", parentSection);
        model.addAttribute("section", section);
        return "sectionpages/sectionEdit";
    }

    /**
     * 更新section
     * 
     * @param section
     * @return
     * @author shefeng Date 2014年9月30日
     * @version
     */
    @RequestMapping(value = "/editSave")
    @ResponseBody
    public Map<String, Object> editSave(SectionEntity section) {
        Map<String, Object> result = new HashMap<String, Object>();
        String msg = "Section更新失败!";

        int success = sectionService.editSection(section);
        if (success == 1) {
            msg = "Section更新成功!";
        }

        result.put("msg", msg);
        return result;
    }

    /**
     * 删除section
     * 
     * @param data
     * @return
     * @author shefeng Date 2014年9月30日
     * @version
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Map<String, Object> deleteSection(String data) {

        if (logger.isInfoEnabled()) {
            logger.info("SectionController.deleteSectionOrPage begin. Data = " + data);
        }

        Map<String, Object> result = new HashMap<String, Object>();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        String msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_DELETE_FAILED);

        JSONArray sectionPagesArray = JSONArray.fromObject(data);
        for (int i = 0; i < sectionPagesArray.size(); i++) {
            list.add(JSonUtil.toObject(sectionPagesArray.get(i).toString(), HashMap.class));
        }

        sectionService.deleteSection(list);
        msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_DELETE_SUCCESS);
        result.put("msg", msg);

        if (logger.isInfoEnabled()) {
            logger.info("SectionController.deleteSectionOrPage end.");
        }

        return result;
    }

    /**
     * 删除page
     * 
     * @param data
     * @return
     * @author shefeng Date 2014年9月30日
     * @version
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/unlimited/deletePage")
    @ResponseBody
    public Map<String, Object> deletePage(String data) {

        if (logger.isInfoEnabled()) {
            logger.info("SectionController.deleteSectionOrPage begin. Data = " + data);
        }

        Map<String, Object> result = new HashMap<String, Object>();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        String msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_DELETE_SUCCESS);

        JSONArray sectionPagesArray = JSONArray.fromObject(data);
        for (int i = 0; i < sectionPagesArray.size(); i++) {
            list.add(JSonUtil.toObject(sectionPagesArray.get(i).toString(), HashMap.class));
        }

        sectionService.deleteSection(list);

        result.put("msg", msg);

        if (logger.isInfoEnabled()) {
            logger.info("SectionController.deleteSectionOrPage end.");
        }

        return result;
    }

    /**
     * 检查sectipn name唯一性
     * 
     * @param section
     * @return
     * @author shefeng Date 2014年9月30日
     * @version
     */
    @RequestMapping(value = "/unlimited/checkSectionName")
    @ResponseBody
    public Map<String, Object> checkSectionName(String sectionName, String orgSectionName) {
        Map<String, Object> result = new HashMap<String, Object>();

        int isUnique = sectionService.checkSectionName(sectionName, orgSectionName);

        result.put("flag", isUnique);
        return result;
    }

    /**
     * 打开page页面 点击new page调用
     * 
     * @param model
     * @param section
     * @return
     * @author shefeng Date 2014年9月30日
     * @version
     */
    @RequestMapping(value = "/createPage")
    public String newPagePage(Model model, SectionEntity section, String typeId, String sectionId) {

        if (logger.isInfoEnabled()) {
            logger.info("SectionController.newPagePage begin.");
        }

        List<TemplateEntity> templateList = pageService.getTemplateList();

        model.addAttribute("templateList", templateList);
        model.addAttribute("typeId", typeId);
        model.addAttribute("sectionId", sectionId);
        model.addAttribute("section", section);
        model.addAttribute("loginUser", userSession.getSysUser());
        if (logger.isInfoEnabled()) {
            logger.info("SectionController.newPagePage end.");
        }
        return "sectionpages/pageNew";
    }

    /**
     * 编辑page页面
     * 
     * @param model
     * @param section
     * @return
     * @author shefeng Date 2014年9月30日
     * @version
     */
    @RequestMapping(value = "/editPage")
    public String editPagePage(Model model, String pageId, String sectionName, String isEdit) {

        if (logger.isInfoEnabled()) {
            logger.info("SectionController.editPagePage begin.");
        }

        PagePojo pagePojo = pageService.getPageById(Integer.parseInt(pageId));

        // ContentMetadataEntity c =
        // pageService.getContentMetadataById(pagePojo.getDatatypeId());

        List<TemplateEntity> templateList = pageService.getTemplateList();
        if (isEdit == null || "".equals(isEdit)) {
            isEdit = "1";
        }
        model.addAttribute("templateList", templateList);
        model.addAttribute("isEdit", isEdit);
        // model.addAttribute("contentId",c.getContentMetadataId());
        model.addAttribute("datatypeId", pagePojo.getDatatypeId());
        model.addAttribute("pagePojo", pagePojo);
        model.addAttribute("sectionId", pagePojo.getSectionId());
        model.addAttribute("sectionName", sectionName);
        if (logger.isInfoEnabled()) {
            logger.info("SectionController.editPagePage end.");
        }

        return "sectionpages/pageEdit";
    }

    /**
     * 保存page数据
     * 
     * @param section
     * @return
     * @author shefeng Date 2014年9月30日
     * @version
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/createPageSave")
    @ResponseBody
    public Map<String, Object> addPage(HttpServletRequest request, PagePojo pagePojo,
            String sectionId, String datatypeId, String selStr) throws UnsupportedEncodingException {

        if (logger.isInfoEnabled()) {
            logger.info("SectionController.addPage begin.");
        }

        Map<String, Object> result = new HashMap<String, Object>();
        String msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_CREATE_FAILED);

        if (sectionId == null || "".equals(sectionId)) {
            sectionId = "1";
        }

        // 获取自定义字段的值

        DataTypePojo dataTypePojo = pageService.geteDataTypeById(Integer.parseInt(datatypeId));

        EditDataType dataTypeEdit = ParseXml.parseEditXml(dataTypePojo.getEditDefinationXML());

        Map<String, Field> _map = new HashMap<String, Field>();

        for (Field field : dataTypeEdit.getFields()) {
            String refId = field.getDataTypeRef();

            if (refId != null && !"".equals(refId)) {
                String requstDatatypeId = request.getParameter("datatype" + refId);
                if (requstDatatypeId != null && !"".equals(requstDatatypeId)) {
                    field.setValue(requstDatatypeId);
                    _map.put(field.getName(), field);
                }
            }
        }

        // Integer contentMetadataId =
        // pageService.insertContentFieldValues(Integer.parseInt(datatypeId),
        // _map);

        pagePojo.setSectionId(Integer.parseInt(sectionId));
        pagePojo.setDatatypeId(Integer.parseInt(datatypeId));
        pageService.insertPage(pagePojo, selStr);
        msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_CREATE_SUCCESS);

        result.put("msg", msg);

        if (logger.isInfoEnabled()) {
            logger.info("SectionController.addPage end.");
        }

        return result;
    }

    /**
     * 更新page数据
     * 
     * @param section
     * @return
     * @author shefeng Date 2014年9月30日
     * @version
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/editPageSave")
    @ResponseBody
    public Map<String, Object> editPage(HttpServletRequest request, PagePojo pagePojo,
            String sectionId, String datatypeId, String selStr) throws UnsupportedEncodingException {

        if (logger.isInfoEnabled()) {
            logger.info("SectionController.editPage begin.");
        }

        Map<String, Object> result = new HashMap<String, Object>();
        String msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_UPDATE_FAILED);

        if (sectionId == null || "".equals(sectionId)) {
            sectionId = "1";
        }

        pagePojo.setSectionId(Integer.parseInt(sectionId));

        // ContentMetadataEntity entity =
        // pageService.getContentMetadataById(Integer.parseInt(datatypeId));
        // pageService.deleteContentById(Integer.parseInt(datatypeId));
        // 获取自定义字段的值

        // DataTypePojo dataTypePojo =
        // pageService.geteDataTypeById(Integer.parseInt(datatypeId));

        // EditDataType dataTypeEdit =
        // ParseXml.parseEditXml(dataTypePojo.getEditDefinationXML());

        // Map<String, Field> _map = new HashMap<String, Field>();

        // for (Field field : dataTypeEdit.getFields()) {
        // String refId = field.getDataTypeRef();
        //
        // if (refId != null && !"".equals(refId)) {
        // String requstDatatypeId = request.getParameter("datatype" + refId);
        // if (requstDatatypeId != null && !"".equals(requstDatatypeId)) {
        // field.setValue(requstDatatypeId);
        // _map.put(field.getName(), field);
        // }
        // }
        // }

        // Integer contentMetadataId =
        // pageService.insertContentFieldValues(entity.getDatatypeId(), _map);

        pagePojo.setDatatypeId(Integer.parseInt(datatypeId));

        pageService.editPage(pagePojo, selStr);
        msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_UPDATE_SUCCESS);

        result.put("msg", msg);

        if (logger.isInfoEnabled()) {
            logger.info("SectionController.editPage end.");
        }

        return result;
    }

    /**
     * 打开选择page type页面
     * 
     * @param model model
     * @return
     * @author kangmin_Qu Date 2014-10-17
     * @version
     */
    @RequestMapping(value = "/selectPageType")
    public String selectPageType(Model model, String sectionId, String sectionName) {

        if (logger.isInfoEnabled()) {
            logger.info("SectionController.selectPageType begin.");
        }

        // Find DataType List
        List<DataTypePojo> dataTypeList = pageService.getDataType();

        model.addAttribute("dataTypeList", dataTypeList);
        model.addAttribute("sectionId", sectionId);
        model.addAttribute("sectionName", sectionName);

        if (logger.isInfoEnabled()) {
            logger.info("SectionController.addPage end.");
        }
        return "sectionpages/selectPageType";
    }

    /**
     * search Datatype
     * 
     * @param section
     * @return
     * @author shefeng Date 2014年9月30日
     * @version
     */
    @RequestMapping(value = "/unlimited/searchDatatype")
    @ResponseBody
    public List<Map<String, Object>> searchDatatype(String selStr, String dataTypeId) {

        if (logger.isInfoEnabled()) {
            logger.info("SectionController.searchDatatype begin.");
        }

        // Select Map Data
        Map<String, String> selMap = new HashMap<String, String>();

        

        DataTypePojo dataTypePojo = pageService.geteDataTypeById(Integer.parseInt(dataTypeId));

        ListDataType dataTypeList = ParseXml.parseListXml(dataTypePojo.getListDefinationXML());

        // 搜索条件
        if (selStr != null && selStr.trim().length() > 0) {
            String[] s = selStr.split("%");
            for (String v : s) {
                String[] h = v.split("&");
                if (h != null && h.length > 1) {
                    selMap.put(h[0], h[1]);
                }
            }

        }
        Map<String, List<Map<String, Object>>> map = pageService.getContentMetadataList(
                Integer.parseInt(dataTypeId), selMap);

        List<Map<String, Object>> _resultMap = new ArrayList<Map<String, Object>>();

        Set<Entry<String, List<Map<String, Object>>>> set = map.entrySet();
        for (Entry<String, List<Map<String, Object>>> entry : set) {
            String key = entry.getKey();
            List<Map<String, Object>> _list = entry.getValue();

            Map<String, Object> m = new LinkedHashMap<String, Object>();
            m.put("keyId", key);

            for (String displayFild : dataTypeList.getDisplayFields()) {

                int _i = 0;

                for (Map<String, Object> _map : _list) {

                    String fieldName = _map.get("FIELD_NAME").toString();

                    if (fieldName.equals(displayFild)) {
                        m.put(displayFild, _map.get("FIELD_VALUE"));
                        _i = 1;
                        break;
                    }
                }
                if (_i == 0) {
                    m.put(displayFild, "");
                }
            }
            _resultMap.add(m);
        }

        if (logger.isInfoEnabled()) {
            logger.info("SectionController.searchDatatype end.");
        }

        return _resultMap;
    }
    
    /**
     * search Datatype
     * 
     * @param section
     * @return
     * @author shefeng Date 2014年9月30日
     * @version
     */
    @RequestMapping(value = "/unlimited/searchContent")
    @ResponseBody
    public List<Map<String, Object>> searchContent(String contentMetadataIds, String dataTypeId) {

        if (logger.isInfoEnabled()) {
            logger.info("SectionController.searchContent begin.");
        }

        Map<String, List<Map<String, Object>>> map = pageService.getContentByIdsList(contentMetadataIds);

        List<Map<String, Object>> _resultMap = new ArrayList<Map<String, Object>>();

        DataTypePojo dataTypePojo = pageService.geteDataTypeById(Integer.parseInt(dataTypeId));

        ListDataType dataTypeList = ParseXml.parseListXml(dataTypePojo.getListDefinationXML());
        
        Set<Entry<String, List<Map<String, Object>>>> set = map.entrySet();
        for (Entry<String, List<Map<String, Object>>> entry : set) {
            String key = entry.getKey();
            List<Map<String, Object>> _list = entry.getValue();

            Map<String, Object> m = new LinkedHashMap<String, Object>();
            m.put("keyId", key);

            for (String displayFild : dataTypeList.getDisplayFields()) {

                int _i = 0;

                for (Map<String, Object> _map : _list) {

                    String fieldName = _map.get("FIELD_NAME").toString();

                    if (fieldName.equals(displayFild)) {
                        m.put(displayFild, _map.get("FIELD_VALUE"));
                        _i = 1;
                        break;
                    }
                }
                if (_i == 0) {
                    m.put(displayFild, "");
                }
            }
            _resultMap.add(m);
        }

        if (logger.isInfoEnabled()) {
            logger.info("SectionController.searchContent end.");
        }

        return _resultMap;
    }

    /**
     * 跳转到Pick DataType 页面
     * 
     * @param model model
     * @param dataTypeId DataTypeId
     * @return
     * @author kangmin_Qu Date 2014-10-17
     * @version
     */
    @RequestMapping(value = "/unlimited/dataTypePicker")
    public String dataTypePickerPage(Model model, HttpServletRequest request, String dataTypeId,
            String elementId, String elementName, String isMulti, String maxSelect) {

        if (logger.isInfoEnabled()) {
            logger.info("SectionController.dataTypePicker begin.");
        }

        List<Map<String, Object>> _resultMap = new ArrayList<Map<String, Object>>();

        DataTypePojo dataTypePojo = pageService.geteDataTypeById(Integer.parseInt(dataTypeId));

        ListDataType dataTypeList = ParseXml.parseListXml(dataTypePojo.getListDefinationXML());

        Map<String, Object> m = new LinkedHashMap<String, Object>();
        m.put("keyId", "");

        for (String displayFild : dataTypeList.getDisplayFields()) {
            m.put(displayFild, "");
        }
        _resultMap.add(m);
        MasterdataEntity masterEntity = new MasterdataEntity();
        masterEntity.setLang(1);
        masterEntity.setIsEnable(true);
        if("93".equals(dataTypeId)){
            masterEntity.setMasterdataMetadataId(MasterDataCodeConstant.CONTENT_DISPLAY_ATTR_NAME_HERO_AREA);
        }
        if("94".equals(dataTypeId)){
            masterEntity.setMasterdataMetadataId(MasterDataCodeConstant.CONTENT_DISPLAY_ATTR_NAME_BANNER_UNIT);
        }
        if("95".equals(dataTypeId)){
            masterEntity.setMasterdataMetadataId(MasterDataCodeConstant.CONTENT_DISPLAY_ATTR_NAME_HOT_SPOT);
        }
        if("96".equals(dataTypeId)){
            masterEntity.setMasterdataMetadataId(MasterDataCodeConstant.CONTENT_DISPLAY_ATTR_NAME_STORE_LOCATOR);
        }
        if("97".equals(dataTypeId)){
            masterEntity.setMasterdataMetadataId(MasterDataCodeConstant.CONTENT_DISPLAY_ATTR_NAME_FAMOUS_PROJECT);
        }
        if("98".equals(dataTypeId)){
            masterEntity.setMasterdataMetadataId(MasterDataCodeConstant.CONTENT_DISPLAY_ATTR_NAME_NEWS);
        }
        if("99".equals(dataTypeId)){
            masterEntity.setMasterdataMetadataId(MasterDataCodeConstant.CONTENT_DISPLAY_ATTR_NAME_ARTICLE);
        }
        masterEntity = pageService.getMasterdataByMetadataId(masterEntity);
        String showcontent = "";
        if(masterEntity!=null){
            showcontent = masterEntity.getMasterdataName();
        }
        model.addAttribute("showcontent", showcontent);
        model.addAttribute("m", _resultMap);
        model.addAttribute("dataTypeId", dataTypeId);
        model.addAttribute("elementId", elementId);
        model.addAttribute("elementName", elementName);
        model.addAttribute("isMulti", isMulti);
        model.addAttribute("maxSelect", maxSelect);
        if (maxSelect != null && "1".equals(maxSelect)) {
            model.addAttribute("isMulti", "1");
        }

        if (logger.isInfoEnabled()) {
            logger.info("SectionController.dataTypePicker end.");
        }
        return "common/datatypePicker";
    }

    /**
     * 预览中转页面
     * 
     * @param model
     * @param pageId
     * @param type
     * @return
     * @author sana Date 2014年12月4日
     * @version
     */
    @RequestMapping(value = "/unlimited/previewTransfer")
    public String preview(Model model, Integer pageId, String type) {
        if (logger.isInfoEnabled()) {
            logger.info("SectionController.preview begin");
        }

        model.addAttribute("pageId", pageId);
        model.addAttribute("type", type);
        if (logger.isInfoEnabled()) {
            logger.info("SectionController.preview end");
        }
        return "sectionpages/pagePreviewBlank";
    }

    /**
     * 预览page
     * 
     * @param model
     * @param isEdit
     * @param categoryMetadataId
     * @return
     * @author Dragon Date 2014年11月25日
     * @version
     */
    @SuppressWarnings("finally")
    @RequestMapping(value = "/previewPage", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> previewPage(Integer pageId, String type) {
        if (logger.isInfoEnabled()) {
            logger.info("SectionController.previewPage begin");
        }
        Map<String, Object> result = new HashMap<String, Object>();

        PublishConf publishConf = new PublishConf();
        publishConf.setDataId(pageId);
        publishConf.setLang(CommonConstants.LOCALE_CN);
        int templateType = 0;
        if ("page".equals(type)) {
            templateType = CommonConstants.TEMPLATE_TYPE_PAGE;
        }
        publishConf.setTemplateType(templateType);
        publishConf.setWebsitePlatform(CommonConstants.PC_PLATFORM);
        publishConf.setIsPreview(true);

        if (logger.isInfoEnabled()) {
            logger.info("CatalogController.previewCatalog end");
        }
        try {
            result = publishHtmlForInterfaceService.run(publishConf);
        } catch (Exception e) {
            result.put("success", false);
            result.put("msg", e.getMessage());
            e.printStackTrace();
        } finally {
            return result;
        }

    }
}
