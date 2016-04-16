/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kohler.bean.PublishConf;
import com.kohler.constants.CommonConstants;
import com.kohler.dao.utils.Pager;
import com.kohler.entity.SuiteEntity;
import com.kohler.entity.SuiteMetadataEntity;
import com.kohler.entity.SuiteProductEntity;
import com.kohler.entity.extend.SuiteHotSpotPojo;
import com.kohler.entity.extend.SuiteMetadataPojo;
import com.kohler.entity.extend.SuiteProductPojo;
import com.kohler.exception.DataException;
import com.kohler.service.PublishHtmlForInterfaceService;
import com.kohler.service.SuiteProductService;
import com.kohler.service.SuiteService;
import com.kohler.util.PropertiesUtils;

/**
 * 
 * 处理套间模块相关前端请求
 *
 * @author Administrator
 * @Date 2014年10月23日
 */
@Controller
@RequestMapping(value = "/suite")
public class SuiteController extends BasicController {

    @Autowired
    private SuiteService suiteService;
    
    @Autowired
    private SuiteProductService suiteProductService;
    
    @Autowired
    private PublishHtmlForInterfaceService publishHtmlForInterfaceService;

    /**
     * 套间列表
     * @param model
     * @param pager
     * @param request
     * @return
     * @author Administrator
     * Date 2014年10月23日
     * @version
     */
    @RequestMapping(value = "/suiteList")
    public String suiteList(Model model, Pager<Map<String, Object>> pager,
            HttpServletRequest request) {
        
        if (logger.isInfoEnabled()) {
            logger.info("SuiteController.suiteList begin");
        }
        
        String uri = request.getRequestURI() + "?";
        String suiteName = request.getParameter("suiteName");
        pager = suiteService.getSuitePage(pager,suiteName);
        pager.setUrl(uri);
        model.addAttribute("pager", pager);
        model.addAttribute("suiteName",suiteName);
        
        if (logger.isInfoEnabled()) {
            logger.info("SuiteController.suiteList end");
        }
        
        return "productcatalogs/suiteList";
    }

    /**
     * 创建套间显示页
     * @param model
     * @return
     * @author Administrator
     * Date 2014年10月23日
     * @version
     */
    @RequestMapping(value = "/create")
    public String create(Model model) {
        
        if (logger.isInfoEnabled()) {
            logger.info("SuiteController.create begin");
        }
        
        Map<String,Object> masterData = suiteService.getGlobleMasterData();
        model.addAttribute("hotSpot", CommonConstants.CONTENT_HOT_SPOTS);
        model.addAttribute("masterData", masterData);
        model.addAttribute("internalFile",CommonConstants.FILE_SOURCE_INTERNAL);
        model.addAttribute("externalFile",CommonConstants.FILE_SOURCE_EXTERNAL);
        
        if (logger.isInfoEnabled()) {
            logger.info("SuiteController.create end");
        }
        
        return "productcatalogs/suiteNew";
    }



    /**
     * 创建套间保存
     * @param jsonEmity
     * @param suiteMetadataPojo
     * @param jsonSuiteProduct
     * @return
     * @author Administrator
     * Date 2014年10月23日
     * @version
     */
    @RequestMapping(value = "/createSave")
    @ResponseBody
    public Map<String, Object> createSave(@RequestParam(value="suite")String jsonEmity,SuiteMetadataPojo suiteMetadataPojo,
            @RequestParam(value="suiteProduct")String jsonSuiteProduct, SuiteHotSpotPojo suHotSpotPojo ) {
        Map<String, Object> result = new HashMap<String, Object>();
        
        if (logger.isInfoEnabled()) {
            logger.info("SuiteController.createSave begin");
        }
        
        SuiteMetadataEntity suiteMetadataEntity = new SuiteMetadataEntity();
        BeanUtils.copyProperties(suiteMetadataPojo,suiteMetadataEntity);
        
        suiteService.addSuite(jsonEmity, suiteMetadataEntity,jsonSuiteProduct, suHotSpotPojo);
        String msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_CREATE_SUCCESS);
        result.put("msg", msg);
        result.put("success", true);

        
        if (logger.isInfoEnabled()) {
            logger.info("SuiteController.createSave end");
        }
        
        return result;
        
    }


    /**
     * 套间编辑显示
     * @param model
     * @param suiteMetadataId
     * @return
     * @author Administrator
     * Date 2014年10月23日
     * @version
     */
    @RequestMapping(value = "/edit")
    public String edit(Model model,Integer suiteMetadataId) {
        
        if (logger.isInfoEnabled()) {
            logger.info("SuiteController.edit begin");
        }
        
        List<SuiteEntity> suite = suiteService.getSuiteByMetadataId(suiteMetadataId);
        SuiteMetadataEntity suiteMetadata = suiteService.getSuiteMetadataByMetadataId(suiteMetadataId);
        Map<String,Object> masterData = suiteService.getGlobleMasterData();
        List<Map<String,Object>> suiteProduct = suiteProductService.getSuiteProductList(suiteMetadataId);
        List<Map<String,Object>> suiteHotSpot = suiteService.getSuiteHotSpot(suiteMetadataId);
        Map<String,Object> hotSpot = new HashMap<String, Object>();
        if(suiteHotSpot != null && suiteHotSpot.size() > 0) {
            hotSpot = suiteHotSpot.get(0);
        }
        
        model.addAttribute("hotSpot", CommonConstants.CONTENT_HOT_SPOTS);
        
        model.addAttribute("suiteHotSpotObj", hotSpot);//suite hot spot
        model.addAttribute("masterData", masterData);
        model.addAttribute("actionType", "add");
        model.addAttribute("suite", suite);
        model.addAttribute("suiteMetadata", suiteMetadata);
        model.addAttribute("suiteProduct", suiteProduct);
        model.addAttribute("internalFile",CommonConstants.FILE_SOURCE_INTERNAL);
        model.addAttribute("externalFile",CommonConstants.FILE_SOURCE_EXTERNAL);
        
        if (logger.isInfoEnabled()) {
            logger.info("SuiteController.edit end");
        }
        
        return "productcatalogs/suiteEdit";
    }   


    /**
     * 套间编辑保存
     * @param jsonEmity
     * @param suiteMetadataPojo
     * @param jsonSuiteProduct
     * @return
     * @author Administrator
     * Date 2014年10月23日
     * @version
     */
    @RequestMapping(value = "/editSave")
    @ResponseBody
    public Map<String, Object> editSave(@RequestParam(value="jsonSuite")String jsonEmity
            ,SuiteMetadataPojo suiteMetadataPojo
            ,@RequestParam(value="jsonSuiteProduct")String jsonSuiteProduct, SuiteHotSpotPojo suHotSpotPojo) {
        
        if (logger.isInfoEnabled()) {
            logger.info("SuiteController.editSave begin");
        }
        
        Map<String, Object> result = new HashMap<String, Object>();

        SuiteMetadataEntity suiteMetadataEntity = new SuiteMetadataEntity();
        BeanUtils.copyProperties(suiteMetadataPojo,suiteMetadataEntity);
        
        suiteService.editSuite( jsonEmity,  suiteMetadataEntity, jsonSuiteProduct,suHotSpotPojo);
        String msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_UPDATE_SUCCESS);
        result.put("success", true);
        result.put("msg", msg);  
     
        
        if (logger.isInfoEnabled()) {
            logger.info("SuiteController.editSave end");
        }
        
        return result;
    }
    
    
    /**
     * 套间显示
     * @param model
     * @param isEdit
     * @param suiteMetadataId
     * @return
     * @author Administrator
     * Date 2014年10月23日
     * @version
     */
    @RequestMapping(value = "/view")
    public String view(Model model, String isEdit, Integer suiteMetadataId) {
        
        if (logger.isInfoEnabled()) {
            logger.info("SuiteController.view begin");
        }
        
        List<SuiteEntity> suite = suiteService.getSuiteByMetadataId(suiteMetadataId);
        SuiteMetadataEntity suiteMetadata = suiteService.getSuiteMetadataByMetadataId(suiteMetadataId);
        List<Map<String,Object>> suiteProduct = suiteProductService.getSuiteProductList(suiteMetadataId);
        Map<String,Object> masterData = suiteService.getGlobleMasterData();
        List<Map<String,Object>> suiteHotSpot = suiteService.getSuiteHotSpot(suiteMetadataId);
        Map<String,Object> hotSpot = new HashMap<String, Object>();
        if(suiteHotSpot != null && suiteHotSpot.size() > 0) {
            hotSpot = suiteHotSpot.get(0);
        }
        
        model.addAttribute("hotSpot", CommonConstants.CONTENT_HOT_SPOTS);
        
        model.addAttribute("suiteHotSpotObj", hotSpot);//suite hot spot
        
        model.addAttribute("masterData", masterData);
        model.addAttribute("actionType", "show");
        model.addAttribute("suite", suite);
        model.addAttribute("suiteMetadata", suiteMetadata);
        model.addAttribute("suiteProduct", suiteProduct);
        model.addAttribute("internalFile",CommonConstants.FILE_SOURCE_INTERNAL);
        model.addAttribute("externalFile",CommonConstants.FILE_SOURCE_EXTERNAL);
        
        if (logger.isInfoEnabled()) {
            logger.info("SuiteController.view end");
        }
        
        return "productcatalogs/suiteEdit";
    }


    /**
     * 删除套间
     * @param suiteMetadataId
     * @return
     * @author Administrator
     * Date 2014年10月23日
     * @version
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Map<String, Object> delete(String suiteMetadataId) {
        
        if (logger.isInfoEnabled()) {
            logger.info("SuiteController.delete begin");
        }
        
        Map<String, Object> result = new HashMap<String, Object>();
        
        String msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_DELETE_SUCCESS);
        String[] suiteMetadataIdArr = suiteMetadataId.split(",");
        suiteService.deleteSuite(suiteMetadataIdArr);
        result.put("msg", msg);
        result.put("success", true);

        
        if (logger.isInfoEnabled()) {
            logger.info("SuiteController.delete end");
        }
        
        return result;
       
    }
    

    /**
     * 显示创建套间产品页
     * @return
     * @author Administrator
     * Date 2014年10月23日
     * @version
     */
    @RequestMapping(value = "/unlimited/create")
    public String createSuiteProductPage() {
        
        if (logger.isInfoEnabled()) {
            logger.info("SuiteController.newSuiteProductPage begin");
        }

        if (logger.isInfoEnabled()) {
            logger.info("SuiteController.newSuiteProductPage end");
        }
        
        return "productcatalogs/suiteProductNew";
    }
    
    /**
     * 创建套间产品保存
     * @param suiteProductPojo
     * @return
     * @author Administrator
     * Date 2014年10月23日
     * @version
     */
    @RequestMapping(value = "/unlimited/createSave")
    @ResponseBody
    public Map<String, Object> addSuiteProduct(SuiteProductPojo suiteProductPojo) {
        
        if (logger.isInfoEnabled()) {
            logger.info("SuiteController.addSuiteProduct begin");
        }
        
        Map<String, Object> result = new HashMap<String, Object>();
        SuiteProductEntity suiteProductEntity = new SuiteProductEntity();
        BeanUtils.copyProperties(suiteProductPojo,suiteProductEntity);
        
        suiteProductService.addSuiteProduct(suiteProductEntity);
        String msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_CREATE_SUCCESS);
        result.put("msg", msg);
        result.put("success", true);

        if (logger.isInfoEnabled()) {
            logger.info("SuiteController.addSuiteProduct end");
        }
        
        return result;
        
    }
    

    /**
     * 套间产品编辑显示
     * @param model
     * @param suiteProductPojo
     * @return
     * @author Administrator
     * Date 2014年10月23日
     * @version
     */
    @RequestMapping(value = "/unlimited/edite")
    public String editSuiteProductPage(Model model,SuiteProductPojo suiteProductPojo) {
        if (logger.isInfoEnabled()) {
            logger.info("SuiteController.editSuiteProductPage begin");
        }
        
        if(null != suiteProductPojo && null != suiteProductPojo.getSuiteProductId()) {
             suiteProductPojo = suiteProductService.getSuiteProductDetail(suiteProductPojo.getSuiteProductId());
        }

        model.addAttribute("suiteProduct", suiteProductPojo);
        model.addAttribute("actionType", "edit");
        
        if (logger.isInfoEnabled()) {
            logger.info("SuiteController.editSuiteProductPage end");
        }
        
        return "productcatalogs/suiteProductEdit";
    }
    

    /**
     * 套间产品编辑保存
     * @param suiteProductPojo
     * @return
     * @author Administrator
     * Date 2014年10月23日
     * @version
     */
    @RequestMapping(value = "/unlimited/editSave")
    @ResponseBody
    public Map<String, Object> editSuiteProduct(SuiteProductPojo suiteProductPojo) {
        
        if (logger.isInfoEnabled()) {
            logger.info("SuiteController.editSuiteProduct begin");
        }
        
        Map<String, Object> result = new HashMap<String, Object>();
        SuiteProductEntity suiteProductEntity = new SuiteProductEntity();
        BeanUtils.copyProperties(suiteProductPojo,suiteProductEntity);

        suiteProductService.editSuiteProduct(suiteProductEntity);
        String msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_UPDATE_SUCCESS);
        result.put("success", true);
        result.put("msg", msg);
        
        if (logger.isInfoEnabled()) {
            logger.info("SuiteController.editSuiteProduct end");
        }
        
        return result;
    }
    
    /**
     * 删除套间产品
     * @param suiteProductId
     * @return
     * @author Administrator
     * Date 2014年10月23日
     * @version
     */
    @RequestMapping(value = "/unlimited/delete")
    @ResponseBody
    public Map<String, Object> deleteSuiteProduct(Integer suiteProductId) {
        
        if (logger.isInfoEnabled()) {
            logger.info("SuiteController.deleteSuiteProduct begin");
        }
        
        Map<String, Object> result = new HashMap<String, Object>();
        
        String msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_DELETE_SUCCESS);
        suiteProductService.deleteSuiteProduct(suiteProductId);
        result.put("msg", msg);
        result.put("success", true);

        
        if (logger.isInfoEnabled()) {
            logger.info("SuiteController.deleteSuiteProduct end");
        }
        
        return result;
       
    }
    
    /**
     * 查询套间名称是否唯一
     * @param suiteEntity
     * @return
     * @author Administrator
     * Date 2014年11月6日
     * @version
     */
    @RequestMapping(value = "/unlimited/uniquenessVerification")
    @ResponseBody
    public Boolean uniquenessVerification(SuiteEntity suiteEntity) {
        
        if (logger.isInfoEnabled()) {
            logger.info("SuiteController.uniquenessVerification begin");
        }
        boolean result = false;
        
        List<SuiteEntity> suiteEntityList = suiteService.selectSuiteBySuiteEntity(suiteEntity);
        if(suiteEntityList == null || suiteEntityList.size() == 0) {
            result = true;
        }

        
        if (logger.isInfoEnabled()) {
            logger.info("SuiteController.uniquenessVerification end");
        }
        
        return result;
       
    }
    
    
    @RequestMapping(value = "/unlimited/previewTransfer")
    public String previewTransfer(Model model, Integer suiteMetadataId, String type) {
        if (logger.isInfoEnabled()) {
            logger.info("SuiteController.preview begin");
        }

        model.addAttribute("suiteMetadataId", suiteMetadataId);
        model.addAttribute("type", type);
        if (logger.isInfoEnabled()) {
            logger.info("SuiteController.preview end");
        }
        return "productcatalogs/suitePreviewBlank";
    }
    
    /**
     * 
     * @param model
     * @param suiteMetadataId
     * @param type
     * @return
     * @author Administrator
     * Date 2014年12月15日
     * @version
     */
    @RequestMapping(value = "/unlimited/preview")
    @ResponseBody
    public Map<String, Object> preview(Model model, Integer suiteMetadataId, String type) {
        if (logger.isInfoEnabled()) {
            logger.info("SuiteController.preview begin");
        }

        Map<String, Object> result = new HashMap<String, Object>();
        PublishConf publishConf = new PublishConf();
        publishConf.setDataId(suiteMetadataId);
        publishConf.setLang(CommonConstants.LOCALE_CN);
        publishConf.setTemplateType(CommonConstants.TEMPLATE_TYPE_SUITE);
        publishConf.setWebsitePlatform(CommonConstants.PC_PLATFORM);
        publishConf.setIsPreview(true);
        try {
            result = publishHtmlForInterfaceService.run(publishConf);
        } catch (Exception e) {
            result.put("success", false);
            e.printStackTrace();
        }
        
        if (logger.isInfoEnabled()) {
            logger.info("SuiteController.preview end");
        }
        return result;
    }
    
}
