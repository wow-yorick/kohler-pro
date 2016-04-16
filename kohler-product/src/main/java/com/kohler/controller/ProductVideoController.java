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
import com.kohler.entity.ProductVideoEntity;
import com.kohler.entity.ProductVideoMetadataEntity;
import com.kohler.service.LocaleService;
import com.kohler.service.ProductPDFService;
import com.kohler.service.ProductVideoMetadataService;
import com.kohler.service.ProductVideoService;
import com.kohler.util.JSonUtil;
import com.kohler.util.PropertiesUtils;

/**
 * Class Function Description
 * ProductVideoController
 * @author zhangtingting
 * @Date 2014年10月17日
 */
@Controller
@RequestMapping("/productVideo/unlimited")
public class ProductVideoController extends BasicController{
    
    @Autowired
    private ProductVideoMetadataService productVideoMetadataService;
    
    @Autowired
    private ProductVideoService productVideoService;
    
    @Autowired
    public LocaleService localeService;
    
    @Autowired
    public ProductPDFService productPDFService;
    
    /**
     * ProductVideo List Page 
     * @param model model
     * @param productMetadataId productMetadataId
     * @return
     * @author ztt
     * Date 2014年10月22日
     * @version
     */
    @RequestMapping("/productVideoList")
    public String productVideoList(Model model,
            @RequestParam(value="productMetadataId")Integer productMetadataId){
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductVideoController.productVideoList begin.");
        }
        
        List<Map<String, Object>> videoMaplist=new ArrayList<Map<String,Object>>();
        
        List<ProductVideoMetadataEntity> videoMetadataList=productVideoMetadataService.getVideoMetadatalistById(productMetadataId);
        if(null != videoMetadataList && videoMetadataList.size()>0){
            for(ProductVideoMetadataEntity videoMeta:videoMetadataList){
                ProductVideoEntity videoEntity=productVideoService.getProductVideoById(videoMeta.getProductVideoMetadataId());
                if(null != videoEntity && !"".equals(videoEntity)){
                    Map<String,Object> map=new HashMap<String, Object>();
                    
                    map.put("productVideoMetadataId", videoMeta.getProductVideoMetadataId());
                    map.put("productVideoName", videoEntity.getProductVideoName());
                    map.put("imageUrl", videoEntity.getFileUrl());
                    
                    videoMaplist.add(map);
                }
            }
        }
        model.addAttribute("videoMaplist", videoMaplist);
        model.addAttribute("productMetadataId", productMetadataId);
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductVideoController.productVideoList end.");
        } 
        
        return "productcatalogs/productVideoList";
    }
    
    
    
    /**
     * Add ProductVideo Page 
     * @param model model
     * @param request
     * @param productMetadataId productMetadataId
     * @return
     * @author ztt
     * Date 2014年10月22日
     * @version
     */
    @RequestMapping("/newVideoPage")
    public String newVideoPage(Model model,HttpServletRequest request,
            @RequestParam(value="productMetadataId")Integer productMetadataId){
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductVideoController.newVideoPage begin.");
        }
        
   //     ProductVideoMetadataEntity videoMetadataEntity=productVideoMetadataService.addProductVideoMetadata();
        
        //Get LocaleEntity
        List<LocaleEntity> languages = localeService.getLanguages();
        
        model.addAttribute("productMetadataId", productMetadataId);
        model.addAttribute("languages", languages);
   //     model.addAttribute("videoMetadataEntity", videoMetadataEntity);
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductVideoController.newVideoPage end.");
        } 
        
        return "productcatalogs/productVideoNew";
    }
    
    
    
    /**
     * Save ProductVideo 
     * @param request
     * @param productVideoMetadataEntity ProductVideoMetadataEntity
     * @param productVideos ProductVideo Json
     * @return
     * @author ztt
     * Date 2014年10月22日
     * @version
     */
    @RequestMapping("/saveProductVideo")
    @ResponseBody
    public Map<String, Object> saveProductVideo(HttpServletRequest request,ProductVideoMetadataEntity productVideoMetadataEntity,
            @RequestParam(value="productVideos")String productVideos){
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductVideoController.saveProductVideo begin.");
        }
        
        Map<String, Object> map=new HashMap<String, Object>();
        String msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_CREATE_FAILED);
        
        List<ProductVideoEntity> productVideoList=new ArrayList<ProductVideoEntity>();
        
        JSONArray productVideoArray=JSONArray.fromObject(productVideos);
        
        if(productVideoArray.size()>0){
            for(int i=0;i<productVideoArray.size();i++){
                productVideoList.add(JSonUtil.toObject(productVideoArray.getString(i).toString(), ProductVideoEntity.class));
           }
        }
        productVideoMetadataService.saveProductVideoMetadata(productVideoMetadataEntity, productVideoList);

        msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_CREATE_SUCCESS);
        map.put("msg", msg);
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductVideoController.saveProductVideo end.");
        } 
        
        return map;
    }
    
    
    
    /**
     * Edit ProductVideo Page 
     * @param model model
     * @param request
     * @param productVideoMetadataId productVideoMetadataId
     * @param productMetadataId productMetadataId
     * @return
     * @author ztt
     * Date 2014年10月22日
     * @version
     */
    @RequestMapping("/editVideoPage")
    public String editVideoPage(Model model,HttpServletRequest request,
            @RequestParam(value="productVideoMetadataId")Integer productVideoMetadataId ,
            @RequestParam(value="productMetadataId")Integer productMetadataId){
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductVideoController.editVideoPage begin.");
        }
        
        //Get ProductVideoMetadataEntity
        ProductVideoMetadataEntity videoMetadataEntity=productVideoMetadataService.getVideoMetadataById(productVideoMetadataId);
        //Get ProductVideoEntity
        ProductVideoEntity videoEntity=new ProductVideoEntity();
        videoEntity.setProductVideoMetadataId(productVideoMetadataId);
        videoEntity.setIsEnable(true);
        List<ProductVideoEntity> productVideolist=productVideoService.getProductVideoListByEntity(videoEntity);  
        
        Map<String,String> fileNameMap = new HashMap<String,String>();
        for (ProductVideoEntity productVideoEntity : productVideolist) {
            if(productVideoEntity.getFileId()!=null){
                FileAssetEntity fa = productPDFService.getFileAssetById(productVideoEntity.getFileId());
                fileNameMap.put(String.valueOf(productVideoEntity.getLang()), fa.getFileAssetName());
            }else{
                fileNameMap.put(String.valueOf(productVideoEntity.getLang()), "");
            }
        }
        //Get LocaleEntity
        List<LocaleEntity> languages = localeService.getLanguages();
        model.addAttribute("fileNameMap", fileNameMap);
        model.addAttribute("productVideolist", productVideolist);
        model.addAttribute("languages", languages);
        model.addAttribute("videoMetadataEntity", videoMetadataEntity);
        model.addAttribute("productMetadataId", productMetadataId);
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductVideoController.editVideoPage end.");
        } 
        
        return "productcatalogs/productVideoEdit";
    }
    
    
    
    /**
     * Update ProductVideo 
     * @param request
     * @param productVideoMetadataEntity ProductVideoMetadataEntity
     * @param productVideos ProductVideo Json
     * @return
     * @author ztt
     * Date 2014年10月22日
     * @version
     */
    @RequestMapping("/updateProductVideo")
    @ResponseBody
    public Map<String, Object> updateProductVideo(HttpServletRequest request,ProductVideoMetadataEntity productVideoMetadataEntity,
            @RequestParam(value="productVideos")String productVideos){
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductVideoController.updateProductVideo begin.");
        }
        
        Map<String, Object> map=new HashMap<String, Object>();
        String msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_UPDATE_FAILED);
        
        List<ProductVideoEntity> productVideoList=new ArrayList<ProductVideoEntity>();
        
        JSONArray productVideoArray=JSONArray.fromObject(productVideos);
        
        if(productVideoArray.size()>0){
            for(int i=0;i<productVideoArray.size();i++){
                productVideoList.add(JSonUtil.toObject(productVideoArray.getString(i).toString(), ProductVideoEntity.class));
           }
        }
        productVideoMetadataService.updateProductVideoMetadata(productVideoMetadataEntity, productVideoList);

        msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_UPDATE_SUCCESS);
        map.put("msg", msg);
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductVideoController.updateProductVideo end.");
        } 
        
        return map;
    }
    
    
    
    /**
     * delete ProductVideo
     * @param request
     * @param productVideoMetadataId productVideoMetadataId
     * @return
     * @author ztt
     * Date 2014年10月22日
     * @version
     */
    @RequestMapping("/deleteProductVideo")
    @ResponseBody
    public Map<String, Object> deleteProductVideo(HttpServletRequest request,
            @RequestParam(value="productVideoMetadataId")Integer productVideoMetadataId){
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductVideoController.deleteProductVideo begin.");
        }
        
        Map<String, Object> map=new HashMap<String, Object>();
        String msg = "";
        
        productVideoMetadataService.deleteProductVideoMetadata(productVideoMetadataId);

        msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_DELETE_SUCCESS);
        map.put("msg", msg);
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductVideoController.deleteProductVideo end.");
        } 
        
        return map;
    }

}
