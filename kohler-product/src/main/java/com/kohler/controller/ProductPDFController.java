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
import com.kohler.entity.ProductPDFEntity;
import com.kohler.entity.ProductPDFMetadataEntity;
import com.kohler.service.LocaleService;
import com.kohler.service.ProductPDFMetadataService;
import com.kohler.service.ProductPDFService;
import com.kohler.util.JSonUtil;
import com.kohler.util.PropertiesUtils;

/**
 * Class Function Description
 * ProductPDFController
 * @author zhangtingting
 * @Date 2014年10月17日
 */
@Controller
@RequestMapping("/productPDF/unlimited")
public class ProductPDFController extends BasicController{
    
    @Autowired
    private ProductPDFMetadataService productPDFMetadataService;
    
    @Autowired
    private ProductPDFService productPDFService;
    
    @Autowired
    public LocaleService localeService;
    
    
    
    /**
     * ProductPDF list Page
     * @param model model
     * @param productMetadataId productMetadataId
     * @return
     * @author ztt
     * Date 2014年10月22日
     * @version
     */
    @RequestMapping("/productPDFList")
    public String productPDFList(Model model,
            @RequestParam(value="productMetadataId")Integer productMetadataId){
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductPDFController.productPDFList begin.");
        }
        
        List<Map<String, Object>> pdfMaplist=new ArrayList<Map<String,Object>>();
        
        List<ProductPDFMetadataEntity> pdfMetadataList=productPDFMetadataService.getPDFMetadatalistById(productMetadataId);
        if(null != pdfMetadataList && pdfMetadataList.size()>0){
            for(ProductPDFMetadataEntity pdfMeta:pdfMetadataList){
                ProductPDFEntity pdfEntity=productPDFService.getProductPDFById(pdfMeta.getProductPDFMetadataId());
                if(null != pdfEntity && !"".equals(pdfEntity)){
                    Map<String,Object> map=new HashMap<String, Object>();
                    
                    map.put("productPDFMetadataId", pdfMeta.getProductPDFMetadataId());
                    map.put("pdfType", pdfEntity.getPdfType());
                    map.put("fileUrl", pdfEntity.getFileUrl());
                    
                    pdfMaplist.add(map);
                }
            }
        }
        model.addAttribute("pdfMaplist", pdfMaplist);
        model.addAttribute("productMetadataId", productMetadataId);
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductPDFController.productPDFList end.");
        } 
        
        return "productcatalogs/productPDFList";
    }
    
    
    
    /**
     * Add ProductPDF Page 
     * @param model model
     * @param request
     * @param productMetadataId productMetadataId
     * @return
     * @author ztt
     * Date 2014年10月22日
     * @version
     */
    @RequestMapping("/newPDFPage")
    public String newPDFPage(Model model,HttpServletRequest request,
            @RequestParam(value="productMetadataId")Integer productMetadataId){
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductPDFController.newPDFPage begin.");
        }
        
   //   ProductPDFMetadataEntity pdfMetadataEntity=productPDFMetadataService.addProductPDFMetadata();
        
      //Get LocaleEntity
        List<LocaleEntity> languages = localeService.getLanguages();
        
        model.addAttribute("languages", languages);
        model.addAttribute("productMetadataId", productMetadataId);
    //    model.addAttribute("pdfMetadataEntity", pdfMetadataEntity);
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductPDFController.newPDFPage end.");
        } 
        
        return "productcatalogs/productPDFNew";
    }
    
    
    
    /**
     * Save ProductPDF 
     * @param request
     * @param productPDFMetadataEntity ProductPDFMetadataEntity
     * @param productPDFs ProductPDF Json
     * @return
     * @author ztt
     * Date 2014年10月22日
     * @version
     */
    @RequestMapping("/saveProductPDF")
    @ResponseBody
    public Map<String, Object> saveProductPDF(HttpServletRequest request,ProductPDFMetadataEntity productPDFMetadataEntity,
            @RequestParam(value="productPDFs")String productPDFs){
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductPDFController.saveProductPDF begin.");
        }
        
        Map<String, Object> map=new HashMap<String, Object>();
        String msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_CREATE_FAILED);
        
        List<ProductPDFEntity> productPDFList=new ArrayList<ProductPDFEntity>();
        
        JSONArray productPDFArray=JSONArray.fromObject(productPDFs);
        
        if(productPDFArray.size()>0){
            for(int i=0;i<productPDFArray.size();i++){
                productPDFList.add(JSonUtil.toObject(productPDFArray.getString(i).toString(), ProductPDFEntity.class));
           }
        }
        productPDFMetadataService.saveProductPDFMetadata(productPDFMetadataEntity, productPDFList);

        msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_CREATE_SUCCESS);
        map.put("msg", msg);
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductPDFController.saveProductPDF end.");
        } 
        
        return map;
    }
    
    
    
    /**
     * Edit ProductPDF Page
     * @param model model
     * @param request
     * @param productPDFMetadataId productPDFMetadataId
     * @param productMetadataId productMetadataId
     * @return
     * @author ztt
     * Date 2014年10月22日
     * @version
     */
    @RequestMapping("/editPDFPage")
    public String editPDFPage(Model model,HttpServletRequest request,
            @RequestParam(value="productPDFMetadataId")Integer productPDFMetadataId ,
            @RequestParam(value="productMetadataId")Integer productMetadataId){
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductPDFController.editPDFPage begin.");
        }
        
        //Get ProductPDFMetadataEntity
        ProductPDFMetadataEntity pdfMetadataEntity=productPDFMetadataService.getPDFMetadataById(productPDFMetadataId);
        //Get ProductPDFEntity
        ProductPDFEntity pdfEntity=new ProductPDFEntity();
        pdfEntity.setProductPDFMetadataId(productPDFMetadataId);
        pdfEntity.setIsEnable(true);
        List<ProductPDFEntity> productPDFlist=productPDFService.getProductPDFListByEntity(pdfEntity);
        Map<String,String> fileNameMap = new HashMap<String,String>();
        for (ProductPDFEntity productPDFEntity : productPDFlist) {
            if(productPDFEntity.getFileId()!=null){
                FileAssetEntity fa = productPDFService.getFileAssetById(productPDFEntity.getFileId());
                fileNameMap.put(String.valueOf(productPDFEntity.getLang()), fa.getFileAssetName());
            }else{
                fileNameMap.put(String.valueOf(productPDFEntity.getLang()), "");
            }
        }
        
        //Get LocaleEntity
        List<LocaleEntity> languages = localeService.getLanguages();
        model.addAttribute("fileNameMap", fileNameMap);
        model.addAttribute("productPDFlist", productPDFlist);
        model.addAttribute("languages", languages);
        model.addAttribute("pdfMetadataEntity", pdfMetadataEntity);
        model.addAttribute("productMetadataId", productMetadataId);
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductPDFController.editPDFPage end.");
        } 
        
        return "productcatalogs/productPDFEdit";
    }
    
    
    
    /**
     * Update ProductPDF
     * @param request
     * @param productPDFMetadataEntity ProductPDFMetadataEntity
     * @param productPDFs ProductPDF Json
     * @return
     * @author ztt
     * Date 2014年10月22日
     * @version
     */
    @RequestMapping("/updateProductPDF")
    @ResponseBody
    public Map<String, Object> updateProductPDF(HttpServletRequest request,ProductPDFMetadataEntity productPDFMetadataEntity,
            @RequestParam(value="productPDFs")String productPDFs){
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductPDFController.updateProductPDF begin.");
        }
        
        Map<String, Object> map=new HashMap<String, Object>();
        String msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_UPDATE_FAILED);
        
        List<ProductPDFEntity> productPDFList=new ArrayList<ProductPDFEntity>();
        
        JSONArray productPDFArray=JSONArray.fromObject(productPDFs);
        
        if(productPDFArray.size()>0){
            for(int i=0;i<productPDFArray.size();i++){
                productPDFList.add(JSonUtil.toObject(productPDFArray.getString(i).toString(), ProductPDFEntity.class));
           }
        }
        productPDFMetadataService.updateProductPDFMetadata(productPDFMetadataEntity, productPDFList);

        msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_UPDATE_SUCCESS);
        map.put("msg", msg);
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductPDFController.updateProductPDF end.");
        } 
        
        return map;
    }
    
    
    
    /**
     * delete ProductPDF
     * @param request
     * @param productPDFMetadataId productPDFMetadataId
     * @return
     * @author ztt
     * Date 2014年10月22日
     * @version
     */
    @RequestMapping("/deleteProductPDF")
    @ResponseBody
    public Map<String, Object> deleteProductPDF(HttpServletRequest request,
            @RequestParam(value="productPDFMetadataId")Integer productPDFMetadataId){
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductPDFController.deleteProductPDF begin.");
        }
        
        Map<String, Object> map=new HashMap<String, Object>();
        String msg = "";
        
        productPDFMetadataService.deleteProductPDFMetadata(productPDFMetadataId);

        msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_DELETE_SUCCESS);
        map.put("msg", msg);
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductPDFController.deleteProductPDF end.");
        } 
        
        return map;
    }
}
