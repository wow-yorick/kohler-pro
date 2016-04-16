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
import com.kohler.entity.ProductCADEntity;
import com.kohler.entity.ProductCADMetadataEntity;
import com.kohler.service.LocaleService;
import com.kohler.service.ProductCADMetadataService;
import com.kohler.service.ProductCADService;
import com.kohler.service.ProductPDFService;
import com.kohler.util.JSonUtil;
import com.kohler.util.PropertiesUtils;

/**
 * Class Function Description
 * ProductCADController
 * @author zhangtingting
 * @Date 2014年10月17日
 */
@Controller
@RequestMapping("/productCAD/unlimited")
public class ProductCADController extends BasicController{
    
    @Autowired
    private ProductCADMetadataService productCADMetadataService;
    
    @Autowired
    private ProductCADService productCADService;
    
    @Autowired
    public LocaleService localeService;
    
    @Autowired
    public ProductPDFService productPDFService;
    
    /**
     * ProductCAD list Page
     * @param model model
     * @param productMetadataId productMetadataId
     * @return 
     * @author ztt
     * Date 2014年10月22日
     * @version
     */
    @RequestMapping("/productCADList")
    public String productCADList(Model model,
            @RequestParam(value="productMetadataId")Integer productMetadataId){
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductCADController.productCADList begin.");
        }
        
        List<Map<String, Object>> cadMaplist=new ArrayList<Map<String,Object>>();
        
        List<ProductCADMetadataEntity> cadMetadataList=productCADMetadataService.getCADMetadatalistById(productMetadataId);
        if(null != cadMetadataList && cadMetadataList.size()>0){
            for(ProductCADMetadataEntity cadMeta:cadMetadataList){
                ProductCADEntity cadEntity=productCADService.getProductCADById(cadMeta.getProductCADMetadataId());
                if(null != cadEntity && !"".equals(cadEntity)){
                    Map<String,Object> map=new HashMap<String, Object>();
                    
                    map.put("productCADMetadataId", cadMeta.getProductCADMetadataId());
                    map.put("productCADName", cadEntity.getProductCADName());
                    map.put("suffix", cadMeta.getSuffix());
                    map.put("imageUrl", cadEntity.getFileUrl());
                    
                    cadMaplist.add(map);
                }
            }
        }
        model.addAttribute("cadMaplist", cadMaplist);
        model.addAttribute("productMetadataId", productMetadataId);
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductCADController.productCADList end.");
        }  
        
        return "productcatalogs/productCADList";
    }
    
    
    
    /**
     * Add ProductCAD Page
     * @param model model
     * @param request
     * @param productMetadataId productMetadataId
     * @return
     * @author ztt
     * Date 2014年10月22日
     * @version
     */
    @RequestMapping("/newCADPage")
    public String newCADPage(Model model,HttpServletRequest request,
            @RequestParam(value="productMetadataId")Integer productMetadataId){
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductCADController.newCADPage begin.");
        }
        
   //     ProductCADMetadataEntity productCADMetadataEntity=productCADMetadataService.addProductCADMetadata();
        //Get LocaleEntity
        List<LocaleEntity> languages = localeService.getLanguages();
        
        model.addAttribute("productMetadataId", productMetadataId);
  //      model.addAttribute("productCADMetadataEntity", productCADMetadataEntity);
        model.addAttribute("languages", languages);
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductCADController.newCADPage end.");
        }  
        
        return "productcatalogs/productCADNew";
    }
    
    
    
    /**
     * Save ProductCAD
     * @param request
     * @param productCADMetadataEntity ProductCADMetadataEntity
     * @param productCADs ProductCAD Json
     * @return
     * @author ztt
     * Date 2014年10月22日
     * @version
     */
    @RequestMapping("/saveProductCAD")
    @ResponseBody
    public Map<String, Object> saveProductCAD(HttpServletRequest request,ProductCADMetadataEntity productCADMetadataEntity,
            @RequestParam(value="productCADs")String productCADs){
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductCADController.saveProductCAD begin.");
        }
        
        Map<String, Object> map=new HashMap<String, Object>();
        String msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_CREATE_FAILED);
        
        List<ProductCADEntity> productCADList=new ArrayList<ProductCADEntity>();
        
        JSONArray productCADArray=JSONArray.fromObject(productCADs);
        
        if(productCADArray.size()>0){
            for(int i=0;i<productCADArray.size();i++){
                productCADList.add(JSonUtil.toObject(productCADArray.getString(i).toString(), ProductCADEntity.class));
           }
        }
        productCADMetadataService.saveProductCADMetadata(productCADMetadataEntity, productCADList);

        msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_CREATE_SUCCESS);
        map.put("msg", msg);
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductCADController.saveProductCAD end.");
        }  
        
        return map;
    }
    
    
    
    /**
     * Edit ProductCAD Page
     * @param model model
     * @param request
     * @param productCADMetadataId productCADMetadataId
     * @param productMetadataId productMetadataId
     * @return
     * @author ztt
     * Date 2014年10月22日
     * @version
     */
    @RequestMapping("/editCADPage")
    public String editCADPage(Model model,HttpServletRequest request,
            @RequestParam(value="productCADMetadataId")Integer productCADMetadataId ,
            @RequestParam(value="productMetadataId")Integer productMetadataId){
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductCADController.editCADPage begin.");
        }
        
        //Get ProductCADMetadataEntity
        ProductCADMetadataEntity productCADMetadataEntity=productCADMetadataService.getCADMetadataById(productCADMetadataId);
        //Get ProductCADEntity
        ProductCADEntity cadEntity=new ProductCADEntity();
        cadEntity.setProductCADMetadataId(productCADMetadataId);
        cadEntity.setIsEnable(true);
        List<ProductCADEntity> productCADlist=productCADService.getProductCADListByEntity(cadEntity);
        
        Map<String,String> fileNameMap = new HashMap<String,String>();
        for (ProductCADEntity productCADEntity : productCADlist) {
            if(productCADEntity.getFileId()!=null){
                FileAssetEntity fa = productPDFService.getFileAssetById(productCADEntity.getFileId());
                fileNameMap.put(String.valueOf(productCADEntity.getLang()), fa.getFileAssetName());
            }else{
                fileNameMap.put(String.valueOf(productCADEntity.getLang()), "");
            }
        }
        
        //Get LocaleEntity
        List<LocaleEntity> languages = localeService.getLanguages();
        model.addAttribute("fileNameMap", fileNameMap);
        model.addAttribute("productCADlist", productCADlist);
        model.addAttribute("languages", languages);
        model.addAttribute("productCADMetadataEntity", productCADMetadataEntity);
        model.addAttribute("productMetadataId", productMetadataId);
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductCADController.editCADPage end.");
        }  
        
        return "productcatalogs/productCADEdit";
    }
    
    
    
    /**
     * Update ProductCAD
     * @param request
     * @param productCADMetadataEntity ProductCADMetadataEntity
     * @param productCADs ProductCAD Json
     * @return
     * @author ztt
     * Date 2014年10月22日
     * @version
     */
    @RequestMapping("/updateProductCAD")
    @ResponseBody
    public Map<String, Object> updateProductCAD(HttpServletRequest request,ProductCADMetadataEntity productCADMetadataEntity,
            @RequestParam(value="productCADs")String productCADs){
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductCADController.updateProductCAD begin.");
        }
        
        Map<String, Object> map=new HashMap<String, Object>();
        String msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_UPDATE_FAILED);
        
        List<ProductCADEntity> productCADList=new ArrayList<ProductCADEntity>();
        
        JSONArray productCADArray=JSONArray.fromObject(productCADs);
        
        if(productCADArray.size()>0){
            for(int i=0;i<productCADArray.size();i++){
                productCADList.add(JSonUtil.toObject(productCADArray.getString(i).toString(), ProductCADEntity.class));
           }
        }
        productCADMetadataService.updateProductCADMetadata(productCADMetadataEntity, productCADList);

        msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_UPDATE_SUCCESS);
        map.put("msg", msg);
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductCADController.updateProductCAD end.");
        }  
        
        return map;
    }
    
    
    
    /**
     * delete ProductCAD
     * @param request
     * @param productCADMetadataId productCADMetadataId
     * @return
     * @author ztt
     * Date 2014年10月22日
     * @version
     */
    @RequestMapping("/deleteProductCAD")
    @ResponseBody
    public Map<String, Object> deleteProductCAD(HttpServletRequest request,
            @RequestParam(value="productCADMetadataId")Integer productCADMetadataId){
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductCADController.deleteProductCAD begin.");
        }
        
        Map<String, Object> map=new HashMap<String, Object>();
        String msg = "";
        
        productCADMetadataService.deleteProductCADMetadata(productCADMetadataId);

        msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_DELETE_SUCCESS);
        map.put("msg", msg);
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductCADController.deleteProductCAD end.");
        } 
        
        return map;
    }
}
