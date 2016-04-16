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

import com.kohler.constants.CommonConstants;
import com.kohler.dao.utils.Pager;
import com.kohler.entity.CollectionStyleEntity;
import com.kohler.entity.CollectionStyleMetadataEntity;
import com.kohler.entity.LocaleEntity;
import com.kohler.entity.NewArrivalBannerUnitEntity;
import com.kohler.entity.NewArrivalEntity;
import com.kohler.entity.NewArrivalHeroAreaEntity;
import com.kohler.entity.NewArrivalMetadataEntity;
import com.kohler.entity.TemplateEntity;
import com.kohler.entity.extend.CollectionStyleMetadataPojo;
import com.kohler.entity.extend.NewArrivalMetadataPojo;
import com.kohler.service.CollectionStyleService;
import com.kohler.service.LocaleService;
import com.kohler.util.PropertiesUtils;
/**
 * 
 * 处理系列产品的类
 *
 * @author fujiajun
 * @Date 2014年10月28日
 */

@Controller
@RequestMapping("/collectionStyle")
public class CollectionStyleController extends BasicController{
    
    @Autowired
    private CollectionStyleService  CollectionStyleService;
    @Autowired
    public LocaleService localeService;
    /**
     * 列表
     * @param model
     * @param pager
     * @param request
     * @return
     * @author fujiajun
     * Date 2014年10月28日
     * @version
     */
    @RequestMapping(value = "/collectionStyleList")
    public String collectionStyleList(Model model, Pager<Map<String, Object>> pager,
            HttpServletRequest request,String searchname) {
        if (logger.isInfoEnabled()) {
            logger.info("CollectionStyleController.collectionStyleList begin");
        }
        
        String uri = request.getRequestURI() + "?";
        System.out.println("Name"+searchname);
        pager = CollectionStyleService.getCollectionStyleListPage(pager,(searchname != null) ? searchname : "");
        pager.setUrl(uri);
        model.addAttribute("pager", pager);
        if(searchname != null){
            model.addAttribute("searchname", searchname);
            uri +="searchname="+searchname;
        }
        
        if (logger.isInfoEnabled()) {
            logger.info("CollectionStyleController.collectionStyleList end");
        }
        return "productcatalogs/collectionsList";
    }
    /**
     * 创建显示页
     * @param model
     * @return
     * @author fujiajun
     * Date 2014年10月23日
     * @version
     */
    @RequestMapping(value = "/create")
    public String create(Model model) {
        
        if (logger.isInfoEnabled()) {
            logger.info("CollectionStyleController.create begin");
        }
        List<LocaleEntity> languages = localeService.getLanguages();
        model.addAttribute("languages", languages);
        if (logger.isInfoEnabled()) {
            logger.info("CollectionStyleController.create end");
        }
        return "productcatalogs/collectionsNew";
    }
    
    /**
     * 创建新品保存
     * @param CollectionStyleMetadataPojo
     * @param products
     * @return
     * @author Administrator
     * Date 2014年10月23日
     * @version
     */
    @RequestMapping(value = "/createSave")
    @ResponseBody
    public Map<String, Object> createSave(CollectionStyleMetadataPojo CollectionStyleMetadataPojo,
            @RequestParam(value="products")String products) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (logger.isInfoEnabled()) {
            logger.info("CollectionStyleController.createSave begin");
        }
        CollectionStyleMetadataEntity CollectionStyleMetadataEntity = new CollectionStyleMetadataEntity();
        BeanUtils.copyProperties(CollectionStyleMetadataPojo,CollectionStyleMetadataEntity);
        int i=CollectionStyleService.addCollectionStyle(CollectionStyleMetadataEntity, products);
        result.clear();
        String msg=(i > 0) ?  PropertiesUtils.findPropertiesKey(CommonConstants.INFO_CONTENT_CREATE_SUCCESS) : PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_CREATE_FAILED);
        result.put("msg", msg);
        result.put("success", true);
        if (logger.isInfoEnabled()) {
            logger.info("CollectionStyleController.createSave end");
        }
        return result;
  }
    
    /**
     * CollectionStyle显示
     * @param model
     * @param collectionsStyleMetadataId
     * @return
     * @author Administrator
     * Date 2014年10月23日
     * @version
     */
    @RequestMapping(value = "/view")
    public String view(Model model,Integer collectionsStyleMetadataId) {
        if (logger.isInfoEnabled()) {
            logger.info("CollectionStyleController.view begin");
        }
        CollectionStyleMetadataEntity  CollectionStyleMetadata=CollectionStyleService.getCollectionStyleMetadataByMetadataId(collectionsStyleMetadataId);
        List<CollectionStyleEntity> CollectionStyle=CollectionStyleService.getCollectionStyleByMetadataId(collectionsStyleMetadataId);
        List<LocaleEntity> languages = localeService.getLanguages();
        model.addAttribute("languages", languages);
        model.addAttribute("CollectionStyleMetadata", CollectionStyleMetadata);
        model.addAttribute("actionType", "show");    
        model.addAttribute("CollectionStyle", CollectionStyle);
        if (logger.isInfoEnabled()) {
            logger.info("CollectionStyleController.view end");
        }
        return "productcatalogs/collectionsEdit";
    }
    /**
     * CollectionStyle编辑显示
     * @param model
     * @param collectionsStyleMetadataId
     * @return
     * @author Administrator
     * Date 2014年10月23日
     * @version
     */
    @RequestMapping(value = "/edit")
    public String edit(Model model,Integer collectionsStyleMetadataId) {
        if (logger.isInfoEnabled()) {
            logger.info("CollectionStyleController.edit begin");
        }
        CollectionStyleMetadataEntity  CollectionStyleMetadata=CollectionStyleService.getCollectionStyleMetadataByMetadataId(collectionsStyleMetadataId);
        List<CollectionStyleEntity> CollectionStyle=CollectionStyleService.getCollectionStyleByMetadataId(collectionsStyleMetadataId);
        List<LocaleEntity> languages = localeService.getLanguages();
        model.addAttribute("languages", languages);
        model.addAttribute("CollectionStyleMetadata", CollectionStyleMetadata);
        model.addAttribute("actionType", "add");    
        model.addAttribute("CollectionStyle", CollectionStyle);
        
        if (logger.isInfoEnabled()) {
            logger.info("CollectionStyleController.edit end");
        }
        return "productcatalogs/collectionsEdit";
    }
    
    /**
     * 修改保存
     * @param CollectionStyleMetadataPojo
     * @param products
     * @return
     * @author Administrator
     * Date 2014年10月23日
     * @version
     */
    @RequestMapping(value = "/editSave")
    @ResponseBody
    public Map<String, Object> EditSave(CollectionStyleMetadataPojo CollectionStyleMetadataPojo,
            @RequestParam(value="products")String products) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (logger.isInfoEnabled()) {
            logger.info("CollectionStyleController.createSave begin");
        }
        CollectionStyleMetadataEntity CollectionStyleMetadataEntity = new CollectionStyleMetadataEntity();
        BeanUtils.copyProperties(CollectionStyleMetadataPojo,CollectionStyleMetadataEntity);
        int i=CollectionStyleService.editCollectionStyle(CollectionStyleMetadataEntity, products);
        result.clear();
        String msg=(i > 0) ?  PropertiesUtils.findPropertiesKey(CommonConstants.INFO_CONTENT_CREATE_SUCCESS) : PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_CREATE_FAILED);
        result.put("msg", msg);
        result.put("success", true);
        if (logger.isInfoEnabled()) {
            logger.info("CollectionStyleController.createSave end");
        }
        return result;
    }
    
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Map<String, Object> delete(Integer[] collectionsStyleMetadataId) {
        
        if (logger.isInfoEnabled()) {
            logger.info("CollectionStyleController.delete begin");
        }
        Map<String, Object> result = new HashMap<String, Object>();
        CollectionStyleService.deleteCollectionStyle(collectionsStyleMetadataId);
        String msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_DELETE_SUCCESS);
        result.put("msg", msg);
        result.put("success", true);
        if (logger.isInfoEnabled()) {
            logger.info("CollectionStyleController.delete end");
        }
        return result;
    } 
    
}
