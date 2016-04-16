/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.controller;

import java.io.UnsupportedEncodingException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kohler.constants.CommonConstants;
import com.kohler.entity.CategorySKUAttrEntity;
import com.kohler.entity.CategorySKUAttrValuesEntity;
import com.kohler.entity.LocaleEntity;
import com.kohler.entity.SKUAttrEntity;
import com.kohler.entity.SKUEntity;
import com.kohler.entity.SKUMetadataEntity;
import com.kohler.service.CategorySKUAttrService;
import com.kohler.service.CategorySKUAttrValuesService;
import com.kohler.service.LocaleService;
import com.kohler.service.SKUAttrService;
import com.kohler.service.SKUMetadataService;
import com.kohler.service.SKUService;
import com.kohler.util.JSonUtil;
import com.kohler.util.PropertiesUtils;

/**
 * Class Function Description
 * SKUController
 * @author zhangtingting
 * @Date 2014年10月17日
 */
@Controller
@RequestMapping("/sku/unlimited")
public class SKUController extends BasicController{
    
    @Autowired
    private SKUMetadataService skuMetadataService;
    
    @Autowired
    private SKUAttrService skuAttrService;
    
    @Autowired
    private SKUService skuService;
    
    @Autowired
    private CategorySKUAttrService categorySKUAttrService;
    
    @Autowired
    private CategorySKUAttrValuesService categorySKUAttrValuesService;
    
    @Autowired
    public LocaleService localeService;
    
    /**
     * SKU List Page
     * @param model model
     * @param productMetadataId productMetadataId
     * @param categoryMetadataId categoryMetadataId
     * @return
     * @author ztt
     * Date 2014年10月22日
     * @version
     */
    @RequestMapping("/SKUList")
    public String SKUList(Model model,
            @RequestParam(value="productMetadataId")Integer productMetadataId,
            @RequestParam(value="categoryMetadataId")Integer categoryMetadataId){
        
        if (logger.isInfoEnabled()) {
            logger.info("SKUController.SKUList begin.");
        }
        
        List<Map<String, Object>> skuMaplist=new ArrayList<Map<String,Object>>();
        
        List<SKUMetadataEntity> skuMetadataList=skuMetadataService.getSKUMetadataEntitylistById(productMetadataId);
        if(null != skuMetadataList && skuMetadataList.size()>0){
            for(SKUMetadataEntity skuMeta:skuMetadataList){
                List<CategorySKUAttrValuesEntity> skuAttrValuesList = categorySKUAttrValuesService.getSKUAttrValuesListBySKUMetadataId(skuMeta.getSkuMetadataId());
                Map<String,Object> map=new HashMap<String, Object>();
                
                map.put("skuMetadataId", skuMeta.getSkuMetadataId());
                map.put("skuCode", skuMeta.getSkuCode());
                map.put("isDefault", skuMeta.getIsDefault());
                map.put("skuAttrValuesList", skuAttrValuesList);
                
                skuMaplist.add(map);
            }
        }
        model.addAttribute("skuMaplist", skuMaplist);
        model.addAttribute("productMetadataId", productMetadataId);
        model.addAttribute("categoryMetadataId", categoryMetadataId);
        
        if (logger.isInfoEnabled()) {
            logger.info("SKUController.SKUList end.");
        } 
        
        return "productcatalogs/skuList";
    }
    
    
    
    /**
     * Add SKU Page 
     * @param model model
     * @param request
     * @param categoryMetadataId categoryMetadataId
     * @param productMetadataId  productMetadataId
     * @return
     * @author ztt
     * Date 2014年10月22日
     * @version
     */
    @RequestMapping("/newSKUPage")
    public String newSKUPage(Model model,HttpServletRequest request,
            @RequestParam(value="categoryMetadataId")Integer categoryMetadataId,
            @RequestParam(value="productMetadataId")Integer productMetadataId){
        
        if (logger.isInfoEnabled()) {
            logger.info("SKUController.newSKUPage begin.");
        }
        
   //   SKUMetadataEntity skuMetadataEntity=skuMetadataService.addSKUMetadata();
      //Get LocaleEntity
        List<LocaleEntity> languages = localeService.getLanguages();
        
        List<Map<String, Object>> maplist=new ArrayList<Map<String,Object>>();
        List<CategorySKUAttrEntity> skuAttrList=categorySKUAttrService.getCategorySKUAttrListByCategoryMetadataId(categoryMetadataId);
        if(null != skuAttrList && skuAttrList.size() > 0){
            for (CategorySKUAttrEntity csae:skuAttrList) {
                List<CategorySKUAttrValuesEntity> skuAttrValuesList = categorySKUAttrValuesService.getSKUAttrValuesListBySKUAttrMetadataId(csae.getCategorySKUAttrMetadataId());
                Map<String,Object> map=new HashMap<String, Object>();
                
                map.put("categorySKUAttrMetadataId", csae.getCategorySKUAttrMetadataId());
                map.put("categorySKUName", csae.getCategorySkuAttrName());
                map.put("skuAttrValuesList", skuAttrValuesList);
                
                maplist.add(map);
            }
        }
        model.addAttribute("languages", languages);
    //  model.addAttribute("skuMetadataEntity", skuMetadataEntity);
        model.addAttribute("maplist",maplist);
        model.addAttribute("productMetadataId", productMetadataId);
        model.addAttribute("categoryMetadataId", categoryMetadataId);
        
        if (logger.isInfoEnabled()) {
            logger.info("SKUController.newSKUPage end.");
        } 
        
        return "productcatalogs/skuNew";
    }
    
    
    
    /**
     * Save SKU
     * @param request
     * @param skuMetadataEntity SKUMetadataEntity
     * @param skus SKU Json
     * @param skuAttrs SKUAttr Json
     * @return
     * @author ztt
     * Date 2014年10月22日
     * @version
     * @throws UnsupportedEncodingException 
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/saveSKU")
    @ResponseBody
    public Map<String, Object> saveSKU(HttpServletRequest request,SKUMetadataEntity skuMetadataEntity,
            @RequestParam(value="skus")String skus,
            @RequestParam(value="skuAttrs")String skuAttrs,
            @RequestParam(value="accessoryvalues")String accessoryvalues) throws UnsupportedEncodingException{
        
        if (logger.isInfoEnabled()) {
            logger.info("SKUController.saveSKU begin.");
        }
        
        Map<String, Object> map=new HashMap<String, Object>();
        String msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_CREATE_FAILED);
        
        //SKU
        List<SKUEntity> skuList=new ArrayList<SKUEntity>();
        JSONArray skuArray=JSONArray.fromObject(skus);
        if(skuArray.size()>0){
            for(int i=0;i<skuArray.size();i++){
                skuList.add(JSonUtil.toObject(skuArray.getString(i).toString(), SKUEntity.class));
           }
        }
        //SKUAttr
        List<SKUAttrEntity> skuAttrList=new ArrayList<SKUAttrEntity>();
        JSONArray skuAttrArray=JSONArray.fromObject(skuAttrs);
        if(skuAttrArray.size()>0){
            for(int i=0;i<skuAttrArray.size();i++){
                skuAttrList.add(JSonUtil.toObject(skuAttrArray.getString(i).toString(), SKUAttrEntity.class));
           }
        }
        
        JSONArray accessoryArray = JSONArray.fromObject(accessoryvalues);
        //SKUaccessory
        List<Map<String, Object>> accessorylist = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < accessoryArray.size(); i++) {
            accessorylist.add(JSonUtil.toObject(accessoryArray.get(i).toString(), HashMap.class));
        }
        
        skuMetadataService.saveSKUMetadata(skuMetadataEntity, skuList,skuAttrList,accessorylist);

        msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_CREATE_SUCCESS);
        map.put("msg", msg);
        
        if (logger.isInfoEnabled()) {
            logger.info("SKUController.saveSKU end.");
        } 
        
        return map;
    }
    
    
    
    /**
     * Edit SKU Page
     * @param model model
     * @param request
     * @param skuMetadataId skuMetadataId
     * @param categoryMetadataId categoryMetadataId
     * @param productMetadataId productMetadataId
     * @return
     * @author ztt
     * Date 2014年10月22日
     * @version
     */
    @RequestMapping("/editSKUPage")
    public String editSKUPag(Model model,HttpServletRequest request,
            @RequestParam(value="skuMetadataId")Integer skuMetadataId,
            @RequestParam(value="categoryMetadataId")Integer categoryMetadataId,
            @RequestParam(value="productMetadataId")Integer productMetadataId){
        
        if (logger.isInfoEnabled()) {
            logger.info("SKUController.editSKUPage begin.");
        }
        
        //Get SKUMetadataEntity
        SKUMetadataEntity skuMetadataEntity=skuMetadataService.getSkuMetadataBySKUMetadataid(skuMetadataId);
        //Get SKUEntity
        SKUEntity sku=new SKUEntity();
        sku.setSkuMetadataId(skuMetadataId);
        sku.setIsEnable(true);
        List<SKUEntity> skulist=skuService.getSKUListByEntity(sku);
        List<Map<String,String>> imagelist = new ArrayList<Map<String,String>>();
        
        for (SKUEntity e : skulist) {
            Map<String,String> fileNameMap = new HashMap<String,String>();
            fileNameMap.put("lang", e.getLang().toString());
            Map<String,Object> m = skuMetadataService.getFileNamesBySkuId(e.getSkuId());
            
            if(m.get("LIST_IMAGE_ID")!=null){
                fileNameMap.put("listImageId", m.get("LIST_IMAGE_ID").toString());
            }else{
                fileNameMap.put("listImageId", "");
            }
            if(m.get("DETAIL_IMAGE1_ID")!=null){
                fileNameMap.put("detailImage1Id", m.get("DETAIL_IMAGE1_ID").toString());
            }else{
                fileNameMap.put("detailImage1Id", "");
            }
            if(m.get("DETAIL_IMAGE2_ID")!=null){
                fileNameMap.put("detailImage2Id", m.get("DETAIL_IMAGE2_ID").toString());
            }else{
                fileNameMap.put("detailImage2Id", "");
            }
            if(m.get("DETAIL_IMAGE3_ID")!=null){
                fileNameMap.put("detailImage3Id", m.get("DETAIL_IMAGE3_ID").toString());
            }else{
                fileNameMap.put("detailImage3Id", "");
            }
            if(m.get("DETAIL_IMAGE4_ID")!=null){
                fileNameMap.put("detailImage4Id", m.get("DETAIL_IMAGE4_ID").toString());
            }else{
                fileNameMap.put("detailImage4Id", "");
            }
            if(m.get("DETAIL_IMAGE5_ID")!=null){
                fileNameMap.put("detailImage5Id", m.get("DETAIL_IMAGE5_ID").toString());
            }else{
                fileNameMap.put("detailImage5Id", "");
            }
            
            imagelist.add(fileNameMap);
        }
        
        //Get List<SKUAttrEntity>
        List<SKUAttrEntity> skuAttrlist=skuAttrService.getSKUAttrListBySKUMetadataId(skuMetadataId);
        //Get LocaleEntity
        List<LocaleEntity> languages = localeService.getLanguages();
        
        List<Map<String, Object>> maplist=new ArrayList<Map<String,Object>>();
        List<CategorySKUAttrEntity> skuAttrList=categorySKUAttrService.getCategorySKUAttrListByCategoryMetadataId(categoryMetadataId);
        if(null != skuAttrList && skuAttrList.size() > 0){
            for (CategorySKUAttrEntity csae:skuAttrList) {
                List<CategorySKUAttrValuesEntity> skuAttrValuesList = categorySKUAttrValuesService.getSKUAttrValuesListBySKUAttrMetadataId(csae.getCategorySKUAttrMetadataId());
                Map<String,Object> map=new HashMap<String, Object>();
                
                map.put("categorySKUAttrMetadataId", csae.getCategorySKUAttrMetadataId());
                map.put("categorySKUName", csae.getCategorySkuAttrName());
                map.put("skuAttrValuesList", skuAttrValuesList);
                
                maplist.add(map);
            }
        }
        model.addAttribute("imagelist", imagelist);
        model.addAttribute("skulist", skulist);
        model.addAttribute("skuAttrlist", skuAttrlist);
        model.addAttribute("languages", languages);
        model.addAttribute("skuMetadataEntity", skuMetadataEntity);
        model.addAttribute("maplist",maplist);
        model.addAttribute("productMetadataId", productMetadataId);
        model.addAttribute("categoryMetadataId", categoryMetadataId);
        if (logger.isInfoEnabled()) {
            logger.info("SKUController.editSKUPage end.");
        } 
        
        return "productcatalogs/skuEdit";
    }
    
    
    
    /**
     * Update SKu
     * @param request
     * @param skuMetadataEntity SkuMetadataEntity
     * @param skus SKU Json
     * @param skuAttrs SKUAttr Json
     * @return
     * @author ztt
     * Date 2014年10月22日
     * @version
     * @throws UnsupportedEncodingException 
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/updateSKU")
    @ResponseBody
    public Map<String, Object> updateSKU(HttpServletRequest request,SKUMetadataEntity skuMetadataEntity,
            @RequestParam(value="skus")String skus,
            @RequestParam(value="skuAttrs")String skuAttrs,
            @RequestParam(value="accessoryvalues")String accessoryvalues) throws UnsupportedEncodingException{
        
        if (logger.isInfoEnabled()) {
            logger.info("SKUController.updateSKU begin.");
        }
        
        Map<String, Object> map=new HashMap<String, Object>();
        String msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_UPDATE_FAILED);
        
        //SKU
        List<SKUEntity> skuList=new ArrayList<SKUEntity>();
        JSONArray skuArray=JSONArray.fromObject(skus);
        if(skuArray.size()>0){
            for(int i=0;i<skuArray.size();i++){
                skuList.add(JSonUtil.toObject(skuArray.getString(i).toString(), SKUEntity.class));
           }
        }
        //SKUAttr
        List<SKUAttrEntity> skuAttrList=new ArrayList<SKUAttrEntity>();
        JSONArray skuAttrArray=JSONArray.fromObject(skuAttrs);
        if(skuAttrArray.size()>0){
            for(int i=0;i<skuAttrArray.size();i++){
                skuAttrList.add(JSonUtil.toObject(skuAttrArray.getString(i).toString(), SKUAttrEntity.class));
           }
        }
        
        JSONArray accessoryArray = JSONArray.fromObject(accessoryvalues);
        //SKUaccessory
        List<Map<String, Object>> accessorylist = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < accessoryArray.size(); i++) {
            accessorylist.add(JSonUtil.toObject(accessoryArray.get(i).toString(), HashMap.class));
        }
        
        skuMetadataService.updateSKUMetadata(skuMetadataEntity, skuList, skuAttrList, accessorylist);

        msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_UPDATE_SUCCESS);
        map.put("msg", msg);
        
        if (logger.isInfoEnabled()) {
            logger.info("SKUController.updateSKU end.");
        } 
        
        return map;
    }
    
    
    /**
     * delete SKU
     * @param request
     * @param skuMetadataId skuMetadataId
     * @return
     * @author ztt
     * Date 2014年10月22日
     * @version
     */
    @RequestMapping("/deleteSKU")
    @ResponseBody
    public Map<String, Object> deleteSKU(HttpServletRequest request,
            @RequestParam(value="skuMetadataId")Integer skuMetadataId){
        
        if (logger.isInfoEnabled()) {
            logger.info("SKUController.deleteSKU begin.");
        }
        
        Map<String, Object> map=new HashMap<String, Object>();
        String msg = "";
        
        skuMetadataService.deleteSKUMetadata(skuMetadataId);

        msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_DELETE_SUCCESS);
        map.put("msg", msg);
        
        if (logger.isInfoEnabled()) {
            logger.info("SKUController.deleteSKU end.");
        } 
        
        return map;
    }
    
    /**
     * 检测唯一性
     * @param model model
     * @return
     * @author WHH Date 2014-11-04
     * @version
     */
    @RequestMapping(value = "/checkSKUCode")
    @ResponseBody
    public Map<String, Object> checkSKUCode(String skuCode, Integer skuMetadataId) {

        if (logger.isInfoEnabled()) {
            logger.info("SKUController.checkSKUCode begin.");
        }
        int size = 0;
        Map<String, Object> result = new HashMap<String, Object>();
        //检测是否存在
        List<SKUMetadataEntity> list = skuMetadataService.checkSKUCode(skuCode, skuMetadataId);
        System.out.println("2222222222222======="+list.size());
        if(list!=null){
            size = list.size();
        }
        result.put("size", size);
        if (logger.isInfoEnabled()) {
            logger.info("SKUController.checkSKUCode end.");
        }
        return result;
    }
}
