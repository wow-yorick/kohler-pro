/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.controller;

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
import com.kohler.entity.FileAssetEntity;
import com.kohler.entity.LocaleEntity;
import com.kohler.entity.ProductPartEntity;
import com.kohler.entity.ProductPartMetadataEntity;
import com.kohler.service.LocaleService;
import com.kohler.service.ProductPDFService;
import com.kohler.service.ProductPartMetadataService;
import com.kohler.service.ProductPartService;
import com.kohler.util.JSonUtil;
import com.kohler.util.PropertiesUtils;

/**
 * Class Function Description
 * ProductPartController
 * @author zhangtingting
 * @Date 2014年10月17日
 */
@Controller
@RequestMapping("/productPart/unlimited")
public class ProductPartController extends BasicController{
    
    @Autowired
    private ProductPartMetadataService productPartMetadataService;
    
    @Autowired
    private ProductPartService productPartService;
    
    @Autowired
    public LocaleService localeService;
    
    @Autowired
    public ProductPDFService productPDFService;
    
    /**
     * ProductPart list Page
     * @param model model
     * @param productMetadataId productMetadataId
     * @return
     * @author ztt
     * Date 2014年10月22日
     * @version
     */
    @RequestMapping("/productPartList")
    public String productPartList(Model model,
            @RequestParam(value="productMetadataId")Integer productMetadataId){
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductPartController.productPartList begin.");
        }
        
        List<Map<String, Object>> partMaplist=new ArrayList<Map<String,Object>>();
        
        List<ProductPartMetadataEntity> partMetadataList=productPartMetadataService.getPartMetadatalistById(productMetadataId);
        if(null != partMetadataList && partMetadataList.size()>0){
            for(ProductPartMetadataEntity partMeta:partMetadataList){
                ProductPartEntity partEntity=productPartService.getProductPartById(partMeta.getProductPartMetadataId());
                if(null != partEntity && !"".equals(partEntity)){
                    Map<String,Object> map=new HashMap<String, Object>();
                    
                    map.put("productPartMetadataId", partMeta.getProductPartMetadataId());
                    map.put("productPartName", partEntity.getProductPartName());
                    map.put("imageUrl", partEntity.getImageUrl());
                    
                    partMaplist.add(map);
                }
            }
        }
        model.addAttribute("partMaplist", partMaplist);
        model.addAttribute("productMetadataId", productMetadataId);
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductPartController.editCADPage end.");
        } 
        
        return "productcatalogs/productPartList";
    }
    
    
    
    /**
     * Add ProductPart Page
     * @param model model
     * @param request
     * @param productMetadataId productMetadataId
     * @return
     * @author ztt
     * Date 2014年10月22日
     * @version
     */
    @RequestMapping("/newPartPage")
    public String newPartPage(Model model,HttpServletRequest request,
            @RequestParam(value="productMetadataId")Integer productMetadataId){
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductPartController.newPartPage begin.");
        }
        
   //     ProductPartMetadataEntity productPartMetadataEntity=productPartMetadataService.addProductPartMetadata();
        //Get LocaleEntity
        List<LocaleEntity> languages = localeService.getLanguages();
        
        model.addAttribute("productMetadataId", productMetadataId);
    //    model.addAttribute("productPartMetadataEntity", productPartMetadataEntity);
        model.addAttribute("languages", languages);
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductPartController.newPartPage end.");
        } 
        
        return "productcatalogs/productPartNew";
    }
    
    
    
    /**
     * Save ProductPart
     * @param request
     * @param productPartMetadataEntity ProductPartMetadataEntity
     * @param productParts ProductPart Json
     * @return
     * @author ztt
     * Date 2014年10月22日
     * @version
     */
    @RequestMapping("/saveProductPart")
    @ResponseBody
    public Map<String, Object> saveProductPart(HttpServletRequest request,ProductPartMetadataEntity productPartMetadataEntity,
            @RequestParam(value="productParts")String productParts){
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductPartController.saveProductPart begin.");
        }
        
        Map<String, Object> map=new HashMap<String, Object>();
        String msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_CREATE_FAILED);
        
        List<ProductPartEntity> productPartList=new ArrayList<ProductPartEntity>();
        
        JSONArray productPartArray=JSONArray.fromObject(productParts);
        
        if(productPartArray.size()>0){
            for(int i=0;i<productPartArray.size();i++){
                productPartList.add(JSonUtil.toObject(productPartArray.getString(i).toString(), ProductPartEntity.class));
           }
        }
        
        productPartMetadataService.saveProductPartMetadata(productPartMetadataEntity, productPartList);

        msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_CREATE_SUCCESS);
        map.put("msg", msg);
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductPartController.saveProductPart end.");
        } 
        
        return map;
    }
    
    
    
    /**
     * Edit ProductPart Page
     * @param model model
     * @param request
     * @param productPartMetadataId productPartMetadataId
     * @param productMetadataId productMetadataId
     * @return
     * @author ztt
     * Date 2014年10月22日
     * @version
     */
    @RequestMapping("/editPartPage")
    public String editPartPage(Model model,HttpServletRequest request,
            @RequestParam(value="productPartMetadataId")Integer productPartMetadataId,
            @RequestParam(value="productMetadataId")Integer productMetadataId){
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductPartController.editPartPage begin.");
        }
        
        //Get ProductPartMetadataEntity
        ProductPartMetadataEntity productPartMetadataEntity=productPartMetadataService.getPartMetadataById(productPartMetadataId);
        //Get ProductPartEntity
        ProductPartEntity partEntity=new ProductPartEntity();
        partEntity.setProductPartMetadataId(productPartMetadataId);
        partEntity.setIsEnable(true);
        List<ProductPartEntity> productPartlist=productPartService.getProductPartListByEntity(partEntity);
        Map<String,String> fileNameMap = new HashMap<String,String>();
        for (ProductPartEntity productPartEntity : productPartlist) {
            if(productPartEntity.getImageId()!=null){
                FileAssetEntity fa = productPDFService.getFileAssetById(productPartEntity.getImageId());
                fileNameMap.put(String.valueOf(productPartEntity.getLang()), fa.getFileAssetName());
            }else{
                fileNameMap.put(String.valueOf(productPartEntity.getLang()), "");
            }
        }
        //Get LocaleEntity
        List<LocaleEntity> languages = localeService.getLanguages();
        model.addAttribute("fileNameMap", fileNameMap);
        model.addAttribute("productPartlist", productPartlist);
        model.addAttribute("languages", languages);
        model.addAttribute("productPartMetadataEntity", productPartMetadataEntity);
        model.addAttribute("productMetadataId", productMetadataId);
        if (logger.isInfoEnabled()) {
            logger.info("ProductPartController.editPartPage end.");
        } 
        
        return "productcatalogs/productPartEdit";
    }
    
    
    
    /**
     * Update ProductPart
     * @param request
     * @param productPartMetadataEntity ProductPartMetadataEntity
     * @param productParts ProductPart Json
     * @return
     * @author ztt
     * Date 2014年10月22日
     * @version
     */
    @RequestMapping("/updateProductPart")
    @ResponseBody
    public Map<String, Object> updateProductPart(HttpServletRequest request,ProductPartMetadataEntity productPartMetadataEntity,
            @RequestParam(value="productParts")String productParts){
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductPartController.updateProductPart begin.");
        }
        
        Map<String, Object> map=new HashMap<String, Object>();
        String msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_UPDATE_FAILED);
        
        List<ProductPartEntity> productPartList=new ArrayList<ProductPartEntity>();
        
        JSONArray productPartArray=JSONArray.fromObject(productParts);
        
        if(productPartArray.size()>0){
            for(int i=0;i<productPartArray.size();i++){
                productPartList.add(JSonUtil.toObject(productPartArray.getString(i).toString(), ProductPartEntity.class));
           }
        }
        
        productPartMetadataService.updateProductPartMetadata(productPartMetadataEntity, productPartList);

        msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_UPDATE_SUCCESS);
        map.put("msg", msg);
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductPartController.updateProductPart end.");
        } 
        
        return map;
    }
    
    /**
     * delete ProductPart
     * @param request
     * @param productPartMetadataId productPartMetadataId
     * @return
     * @author ztt
     * Date 2014年10月22日
     * @version
     */
    @RequestMapping("/deleteProductPart")
    @ResponseBody
    public Map<String, Object> deleteProductPart(HttpServletRequest request,
            @RequestParam(value="productPartMetadataId")Integer productPartMetadataId){
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductPartController.deleteProductPart begin.");
        }
        
        Map<String, Object> map=new HashMap<String, Object>();
        String msg = "";
        
        productPartMetadataService.deleteProductPartMetadata(productPartMetadataId);

        msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_DELETE_SUCCESS);
        map.put("msg", msg);
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductPartController.deleteProductPart end.");
        } 
        
        return map;
    }
}
