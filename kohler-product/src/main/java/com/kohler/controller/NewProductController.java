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
import com.kohler.entity.LocaleEntity;
import com.kohler.entity.NewArrivalBannerUnitEntity;
import com.kohler.entity.NewArrivalEntity;
import com.kohler.entity.NewArrivalHeroAreaEntity;
import com.kohler.entity.NewArrivalMetadataEntity;
import com.kohler.entity.TemplateEntity;
import com.kohler.entity.extend.NewArrivalMetadataPojo;
import com.kohler.service.LocaleService;
import com.kohler.service.NewProductService;
import com.kohler.service.ProductTemplateService;
import com.kohler.util.PropertiesUtils;
import com.kohler.service.PublishHtmlForInterfaceService;
/**
 * 
 * 处理新建产品的类
 *
 * @author fujiajun
 * @Date 2014年10月28日
 */

@Controller
@RequestMapping("/newProduct")
public class NewProductController extends BasicController{
    @Autowired
    private NewProductService NewProductService;
    
    @Autowired
    public LocaleService localeService;
    
    @Autowired
    private ProductTemplateService templateService;
    
    @Autowired
    private PublishHtmlForInterfaceService publishHtmlForInterfaceService;
    /**
     * 新品列表
     * @param model
     * @param pager
     * @param request
     * @return
     * @author fujiajun
     * Date 2014年10月28日
     * @version
     */
    @RequestMapping(value = "/newProductList")
    public String newarrivalsList(Model model, Pager<Map<String, Object>> pager,
            HttpServletRequest request,String searchname) {
        if (logger.isInfoEnabled()) {
            logger.info("NewArrivalsController.newarrivalsList begin");
        }
        
        String uri = request.getRequestURI() + "?";
        pager = NewProductService.getNewarrivalsListPage(pager,searchname);
        pager.setUrl(uri);
        model.addAttribute("pager", pager);
        if(searchname != null){
            model.addAttribute("searchname", searchname);
            uri +="searchname="+searchname;
        }
        
        if (logger.isInfoEnabled()) {
            logger.info("NewArrivalsController.newarrivalsList end");
        }
        return "productcatalogs/newproductList";
    }
    /**
     * 创建新品显示页
     * @param model
     * @return
     * @author fujiajun
     * Date 2014年10月23日
     * @version
     */
    @RequestMapping(value = "/create")
    public String create(Model model) {
        
        if (logger.isInfoEnabled()) {
            logger.info("newProduct.create begin");
        }
        Map<String,Object> masterData =NewProductService.getselectList();
        List<LocaleEntity> languages = localeService.getLanguages();
        //List<TemplateEntity> templates=templateService.getAllTemplateList();
        model.addAttribute("masterData", masterData);
        model.addAttribute("languages", languages);
        //model.addAttribute("templates", templates);
        if (logger.isInfoEnabled()) {
            logger.info("newProduct.create end");
        }
        return "productcatalogs/newproductNew";
    }
    
    /**
     * 创建新品保存
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
    public Map<String, Object> createSave(NewArrivalMetadataPojo NewArrivalMetadataPojo,
            @RequestParam(value="products")String products,String bannerUnitMetadataId,String heroAreaMetadataId) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (logger.isInfoEnabled()) {
            logger.info("NewProductController.createSave begin");
        }
        NewArrivalMetadataEntity NewArrivalMetadataEntity = new NewArrivalMetadataEntity();
        BeanUtils.copyProperties(NewArrivalMetadataPojo,NewArrivalMetadataEntity);
        int i=NewProductService.addnewproduct(NewArrivalMetadataEntity, products,bannerUnitMetadataId,heroAreaMetadataId);
        result.clear();
        String msg=(i > 0) ?  PropertiesUtils.findPropertiesKey(CommonConstants.INFO_CONTENT_CREATE_SUCCESS) : PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_CREATE_FAILED);
        result.put("msg", msg);
        result.put("success", true);
        if (logger.isInfoEnabled()) {
            logger.info("NewProductController.createSave end");
        }
        return result;
  }
   
    /**
     * 新品编辑显示
     * @param model
     * @param suiteMetadataId
     * @return
     * @author Administrator
     * Date 2014年10月23日
     * @version
     */
    @RequestMapping(value = "/edit")
    public String edit(Model model,Integer NewArrivalMetadataId) {
        
        if (logger.isInfoEnabled()) {
            logger.info("NewProductController.edit begin");
        }
        NewArrivalMetadataEntity  NewArrivalMetadata=NewProductService.getNewArrivalMetadataByMetadataId(NewArrivalMetadataId);
        List<NewArrivalEntity> NewArrival=NewProductService.getNewArrivalByMetadataId(NewArrivalMetadataId);
        List<Map<String,Object>> NewArrivalHeroArea=NewProductService.getNewArrivalHeroAreAByMetadataId(NewArrivalMetadataId);
        List<Map<String,Object>> NewArrivalBanner=NewProductService.getNewArrivalBannerUnitByMetadataId(NewArrivalMetadataId);
        Map<String,Object> masterData =NewProductService.getselectList();
        List<LocaleEntity> languages = localeService.getLanguages();
        List<TemplateEntity> templates=templateService.getAllTemplateList();
        model.addAttribute("masterData", masterData);
        model.addAttribute("languages", languages);
        model.addAttribute("templates", templates);
        model.addAttribute("NewArrivalMetadata", NewArrivalMetadata);
        model.addAttribute("actionType", "add");    
        model.addAttribute("NewArrival", NewArrival);
        model.addAttribute("NewArrivalHeroArea", NewArrivalHeroArea);
        model.addAttribute("NewArrivalBanner", NewArrivalBanner);
        if (logger.isInfoEnabled()) {
            logger.info("NewProductController.edit end");
        }
        return "productcatalogs/newproductEdit";
    }   
    /**
     * 新品详细
     * @param model
     * @param isEdit
     * @param NewArrivalMetadataId
     * @return
     * @author Administrator
     * Date 2014年10月23日
     * @version
     */
    @RequestMapping(value = "/view")
    public String view(Model model,Integer NewArrivalMetadataId) {
        
        if (logger.isInfoEnabled()) {
            logger.info("NewProductController.view begin");
        }
        NewArrivalMetadataEntity  NewArrivalMetadata=NewProductService.getNewArrivalMetadataByMetadataId(NewArrivalMetadataId);
        List<NewArrivalEntity> NewArrival=NewProductService.getNewArrivalByMetadataId(NewArrivalMetadataId);
        List<Map<String,Object>> NewArrivalHeroArea=NewProductService.getNewArrivalHeroAreAByMetadataId(NewArrivalMetadataId);
        List<Map<String,Object>> NewArrivalBanner=NewProductService.getNewArrivalBannerUnitByMetadataId(NewArrivalMetadataId);
        Map<String,Object> masterData =NewProductService.getselectList();
        List<LocaleEntity> languages = localeService.getLanguages();
        List<TemplateEntity> templates=templateService.getAllTemplateList();
        model.addAttribute("masterData", masterData);
        model.addAttribute("languages", languages);
        model.addAttribute("templates", templates);
        model.addAttribute("NewArrivalMetadata", NewArrivalMetadata);
        model.addAttribute("actionType", "show");
        model.addAttribute("NewArrival", NewArrival);
        model.addAttribute("NewArrivalHeroArea", NewArrivalHeroArea);
        model.addAttribute("NewArrivalBanner", NewArrivalBanner);
        if (logger.isInfoEnabled()) {
            logger.info("NewProductController.view end");
        }
        return "productcatalogs/newproductEdit";
    }
    /**
     * 修改新品保存
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
    public Map<String, Object> EditSave(NewArrivalMetadataPojo NewArrivalMetadataPojo,
            @RequestParam(value="products")String products,Integer[] bannerUnitMetadataId,Integer[] heroAreaMetadataId) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (logger.isInfoEnabled()) {
            logger.info("NewProductController.createSave begin");
        }
        NewArrivalMetadataEntity NewArrivalMetadataEntity = new NewArrivalMetadataEntity();
        BeanUtils.copyProperties(NewArrivalMetadataPojo,NewArrivalMetadataEntity);
        
        int i=NewProductService.editnewproduct(NewArrivalMetadataEntity, products, bannerUnitMetadataId, heroAreaMetadataId);
        result.clear();
        String msg=(i > 0) ?  PropertiesUtils.findPropertiesKey(CommonConstants.INFO_CONTENT_CREATE_SUCCESS) : PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_CREATE_FAILED);
        result.put("msg", msg);
        result.put("success", true);
        if (logger.isInfoEnabled()) {
            logger.info("NewProductController.createSave end");
        }
        return result;
  }
    
    /**
     * 删除新品
     * @param suiteMetadataId
     * @return
     * @author fujiajun
     * Date 2014年10月23日
     * @version
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Map<String, Object> delete(Integer[] NewArrivalArr) {
        
        if (logger.isInfoEnabled()) {
            logger.info("NewProductController.delete begin");
        }
        Map<String, Object> result = new HashMap<String, Object>();
        NewProductService.deleteNewArrival(NewArrivalArr);
        String msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_DELETE_SUCCESS);
        result.put("msg", msg);
        result.put("success", true);
        if (logger.isInfoEnabled()) {
            logger.info("NewProductController.delete end");
        }
        return result;
    }
    
    
    /**
     * @param model
     * @param isEdit
     * @param categoryMetadataId
     * @return
     * @author XHY Date 2014年10月9日
     * @version
     */
    @RequestMapping(value = "/unlimited/previewTransfer")
    public String preview(Model model, Integer newProductMetadataId) {
        if (logger.isInfoEnabled()) {
            logger.info("NewProductController.preview begin");
        }
        model.addAttribute("newProductMetadataId", newProductMetadataId);
        if (logger.isInfoEnabled()) {
            logger.info("NewProductController.preview end");
        }
        return "productcatalogs/newproductPreviewBlank";
    }
    /**
     * preview新品
     * @param suiteMetadataId
     * @return
     * @author fujiajun
     * Date 2014年10月23日
     * @version
     */
    @SuppressWarnings("finally")
    @RequestMapping(value = "/preview")
    @ResponseBody                             
    public Map<String, Object> preview(Integer newProductMetadataId){
        if (logger.isInfoEnabled()) {
            logger.info("NewProductController.preview begin");
        }
        Map<String, Object> result = new HashMap<String, Object>();
        
        PublishConf publishConf = new PublishConf();
        publishConf.setDataId(newProductMetadataId);
        publishConf.setLang(CommonConstants.LOCALE_CN);
        publishConf.setTemplateType(CommonConstants.TEMPLATE_TYPE_NEW_PRODUCT);
        publishConf.setWebsitePlatform(CommonConstants.PC_PLATFORM);
        publishConf.setIsPreview(true);
        try {
            result = publishHtmlForInterfaceService.run(publishConf);
        } catch (Exception e) {
            if (logger.isInfoEnabled()) {
                logger.info("NewProductController.preview end");
            }
            result.put("success", false);
            e.printStackTrace();
        } finally {
            return result;
        }
    }
    
}
