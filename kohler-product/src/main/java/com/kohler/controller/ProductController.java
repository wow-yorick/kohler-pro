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

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kohler.bean.PublishConf;
import com.kohler.constants.CommonConstants;
import com.kohler.entity.CategoryEntity;
import com.kohler.entity.CollectionEntity;
import com.kohler.entity.LocaleEntity;
import com.kohler.entity.ProductEntity;
import com.kohler.entity.ProductMetadataEntity;
import com.kohler.entity.TemplateEntity;
import com.kohler.entity.extend.ProductMetadataPojo;
import com.kohler.service.CatalogService;
import com.kohler.service.CategoryComAttrMetadataService;
import com.kohler.service.CollectionService;
import com.kohler.service.LocaleService;
import com.kohler.service.ProductMetadataService;
import com.kohler.service.ProductService;
import com.kohler.service.ProductTemplateService;
import com.kohler.util.DateUtil;
import com.kohler.util.JSonUtil;
import com.kohler.util.PropertiesUtils;

/**
 * Class Function Description
 * ProductController
 * @author zhangtingting
 * @Date 2014年9月25日
 */
@Controller
@RequestMapping("/product")
public class ProductController extends BasicController{
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private ProductMetadataService productMetadataService;
    
    @Autowired
    private CollectionService collectionService;
    
    @Autowired
    private ProductTemplateService templateService;
    
    @Autowired
    private CatalogService categoryService;
    
    @Autowired
    private CategoryComAttrMetadataService cateComAttrMetadataService;
    
    @Autowired
    public LocaleService localeService;
    
    
    
    /**
     * Add Product Page
     * @param model model
     * @param request
     * @param categoryMetadataId categoryMetadataId
     * @return
     * @author ztt
     * Date 2014年10月22日
     * @version
     */
    @RequestMapping("/createProduct")
    public String newProductPage(Model model,HttpServletRequest request,
            @RequestParam(value="categoryMetadataId")Integer categoryMetadataId){
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductController.newProductPage begin.");
        }
        
        ProductMetadataEntity productMetadataEntity=productMetadataService.addProductMetadata();
        
        CategoryEntity categoryEntity=categoryService.getCategoryByCategoryMetadataId(categoryMetadataId);
        List<CollectionEntity> collections=collectionService.getCollectionsByDefault();
        List<TemplateEntity> templates=templateService.getAllTemplateList();
        List<LocaleEntity> languages = localeService.getLanguages();

        
        model.addAttribute("categoryEntity", categoryEntity);
        model.addAttribute("languages", languages);
        model.addAttribute("categoryMetadataId", categoryMetadataId);
        model.addAttribute("productMetadataEntity", productMetadataEntity);
        model.addAttribute("collections", collections);
        model.addAttribute("templates", templates);
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductController.newProductPage end.");
        }  
        
        return "productcatalogs/productNew";
    }
    
    
    
    /**
     * Save Product 
     * @param request
     * @param productMetadataPojo ProductMetadataPojo
     * @param products Product Json
     * @return
     * @author ztt
     * Date 2014年10月22日
     * @version
     * @throws UnsupportedEncodingException 
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/createProductSave")
    @ResponseBody
    public Map<String, Object> saveProduct(HttpServletRequest request,ProductMetadataPojo productMetadataPojo,
            @RequestParam(value="products")String products,
            @RequestParam(value="comattrs")String comattrs) throws UnsupportedEncodingException{
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductController.saveProduct begin.");
        }
        Map<String, Object> map=new HashMap<String, Object>();
        String msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_CREATE_FAILED);
        JSONArray comattrArray = JSONArray.fromObject(comattrs);
        
        List<Map<String, Object>> attrlist = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < comattrArray.size(); i++) {
            attrlist.add(JSonUtil.toObject(comattrArray.get(i).toString(), HashMap.class));
        }
        
        List<ProductEntity> productList=new ArrayList<ProductEntity>();
        
        JSONArray productArray=JSONArray.fromObject(products);
        
        if(productArray.size()>0){
            for(int i=0;i<productArray.size();i++){
                productList.add(JSonUtil.toObject(productArray.getString(i).toString(), ProductEntity.class));
           }
        }
        ProductMetadataEntity productMetadataEntity=new ProductMetadataEntity();
        BeanUtils.copyProperties(productMetadataPojo,productMetadataEntity);
        if(null != productMetadataPojo.getPublishTime() && !"".equals(productMetadataPojo.getPublishTime())){
            productMetadataEntity.setPublishDate(DateUtil.strFormatDate2(productMetadataPojo.getPublishTime()));
        }
        
        productMetadataService.saveProductMetadata(productMetadataEntity, productList, attrlist);
        
        msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_CREATE_SUCCESS);
        map.put("msg", msg);
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductController.saveProduct end.");
        }  
        
        return map;
    }
    
    
    
    /**
     * Edit Product Page
     * @param model model
     * @param request
     * @param categoryMetadataId categoryMetadataId
     * @param productMetadataId productMetadataId
     * @return
     * @author ztt
     * Date 2014年10月22日
     * @version
     */
    @RequestMapping("/editProduct")
    public String editProductPage(Model model,HttpServletRequest request,
            @RequestParam(value="categoryMetadataId")Integer categoryMetadataId,
            @RequestParam(value="productMetadataId")Integer productMetadataId,
            Integer isEdit){
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductController.editProductPage begin.");
        }
        
        CategoryEntity categoryEntity=categoryService.getCategoryByCategoryMetadataId(categoryMetadataId);
        //Get LocaleEntity
        List<LocaleEntity> languages = localeService.getLanguages();
        //Get ProductMetadata Data
        ProductMetadataEntity productMetadataEntity=productMetadataService.getProductMetadataById(productMetadataId);
        ProductMetadataPojo pmPojo=new ProductMetadataPojo();
        BeanUtils.copyProperties(productMetadataEntity,pmPojo);
        if(null != productMetadataEntity.getPublishDate() && !"".equals(productMetadataEntity.getPublishDate())){
            pmPojo.setPublishTime(DateUtil.dateFormatStr3(productMetadataEntity.getPublishDate()));
        }
        //Get Product
        ProductEntity productEntity=new ProductEntity();
        productEntity.setIsEnable(true);
        productEntity.setProductMetadataId(productMetadataId);  
        List<ProductEntity> productlist=productService.getProductListByEntity(productEntity);
        if(null != productlist && productlist.size()>0){
            ProductEntity product=productlist.get(0);
            model.addAttribute("isCombineProduct", product.getIsCombineProduct());
            model.addAttribute("combineProductMetadataId",product.getCombineProductMetadataId());
            model.addAttribute("isDiscontinue",product.getIsDiscontinue());
            
            ProductEntity p = new ProductEntity();
            p.setLang(1);
            p.setProductMetadataId(product.getCombineProductMetadataId());
            List<ProductEntity> l = productService.getProductListByEntity(p);
            if(l!=null&&l.size()>0){
                model.addAttribute("combineProductName",l.get(0).getProductName());
            }
        }
        List<CollectionEntity> collections=collectionService.getCollectionsByDefault();
        List<TemplateEntity> templates=templateService.getAllTemplateList();
        
        model.addAttribute("categoryMetadataId", categoryMetadataId);
        model.addAttribute("categoryEntity", categoryEntity);
        model.addAttribute("languages", languages);
        model.addAttribute("pmPojo", pmPojo);
        model.addAttribute("collections", collections);
        model.addAttribute("templates", templates);
        model.addAttribute("productlist", productlist);
        model.addAttribute("isEdit", isEdit);
        if (logger.isInfoEnabled()) {
            logger.info("ProductController.editProductPage end.");
        }  
        
        return "productcatalogs/productEdit";
    }
    
    
    
    /**
     * Update Product
     * @param request
     * @param productMetadataPojo ProductMetadataPojo
     * @param products Product Json
     * @return
     * @author Admin
     * Date 2014年10月22日
     * @version
     * @throws UnsupportedEncodingException 
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/editProductSave")
    @ResponseBody
    public Map<String, Object> updateProduct(HttpServletRequest request,ProductMetadataPojo productMetadataPojo,
            @RequestParam(value="products")String products,
            @RequestParam(value="comattrs")String comattrs) throws UnsupportedEncodingException{
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductController.updateProduct begin.");
        }
        JSONArray comattrArray = JSONArray.fromObject(comattrs);
        List<Map<String, Object>> attrlist = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < comattrArray.size(); i++) {
            attrlist.add(JSonUtil.toObject(comattrArray.get(i).toString(), HashMap.class));
        }
        Map<String, Object> map=new HashMap<String, Object>();
        String msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_UPDATE_FAILED);
        
        List<ProductEntity> productList=new ArrayList<ProductEntity>();
        
        JSONArray productArray=JSONArray.fromObject(products);
        
        if(productArray.size()>0){
            for(int i=0;i<productArray.size();i++){
                productList.add(JSonUtil.toObject(productArray.getString(i).toString(), ProductEntity.class));
           }
        }
        ProductMetadataEntity productMetadataEntity=new ProductMetadataEntity();
        BeanUtils.copyProperties(productMetadataPojo,productMetadataEntity);
        
        if(null != productMetadataPojo.getPublishTime() && !"".equals(productMetadataPojo.getPublishTime())){
            productMetadataEntity.setPublishDate(DateUtil.strFormatDate2(productMetadataPojo.getPublishTime()));
        }
        
        productMetadataService.updateProductMetadata(productMetadataEntity, productList, attrlist);
        
        msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_UPDATE_SUCCESS);
        map.put("msg", msg);
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductController.updateProduct end.");
        }  
        
        return map;
    }
    
    /**
     * Update Product
     * @param request
     * @param productMetadataPojo ProductMetadataPojo
     * @param products Product Json
     * @return
     * @author Admin
     * Date 2014年10月22日
     * @version
     * @throws UnsupportedEncodingException 
     */
    @RequestMapping("/unlimited/deleteProduct")
    @ResponseBody
    public Map<String, Object> deleteProduct(HttpServletRequest request,String productMetadataIds) throws UnsupportedEncodingException{
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductController.updateProduct begin.");
        }
        Map<String, Object> map=new HashMap<String, Object>();
        String msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_DELETE_FAILED);
        
        int i = productMetadataService.deleteProduct(","+productMetadataIds+",");
        if(i==1){
            msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_DELETE_SUCCESS);
        }
        map.put("msg", msg);
        
        if (logger.isInfoEnabled()) {
            logger.info("ProductController.deleteProduct end.");
        }  
        
        return map;
    }
    
    /**
     * 检测product name唯一性
     * @param model model
     * @return
     * @author WHH Date 2014-11-04
     * @version
     */
    @RequestMapping(value = "/unlimited/checkProductName")
    @ResponseBody
    public Map<String, Object> checkProductName(ProductEntity product) {

        if (logger.isInfoEnabled()) {
            logger.info("ProductController.checkProductName begin.");
        }
        int size = 0;
        Map<String, Object> result = new HashMap<String, Object>();
        //检测是否存在
        List<ProductEntity> list = productService.checkProductName(product);
        if(list!=null){
            size = list.size();
        }
        result.put("size", size);
        if (logger.isInfoEnabled()) {
            logger.info("ProductController.checkProductName end.");
        }
        return result;
    }
   
   
}
