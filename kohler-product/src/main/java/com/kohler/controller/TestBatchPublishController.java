/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kohler.bean.ConfPrepareData;
import com.kohler.constants.CommonConstants;
import com.kohler.service.publish.BatchPublishForAutoSinglePlatform;
import com.kohler.service.publish.classify.CategoryListBatch;
import com.kohler.service.publish.classify.NewArrivalDetailBatch;
import com.kohler.service.publish.classify.PageListBatch;
import com.kohler.service.publish.classify.SuiteDetailBatch;

/**
 * 用于处理Catalog,及公共的相关的请求
 *
 * @author XHY
 * @Date 2014年10月9日
 */
@Controller
@RequestMapping(value = "/publish/unlimited")
public class TestBatchPublishController extends BasicController {
    
    @Autowired
    BatchPublishForAutoSinglePlatform batchPublishForAuto;
    
    @Autowired
    private PageListBatch pageListBatch;
    
    @Autowired
    private CategoryListBatch categoryListBatch;
    
    @Autowired
    private NewArrivalDetailBatch newArrivalDetailBatch;
    
    @Autowired
    private SuiteDetailBatch suiteDetailBatch;
    
    /**
     * @param model
     * @param pager
     * @param categoryId
     * @param request
     * @return
     * @author XHY Date 2014年10月9日
     * @version
     */
    @RequestMapping(value = "/test")
    public String getBatchBtn() {
        return "testpublish/batch";
    }
    
    @RequestMapping(value = "/result")
    public String batch(Model model) {
        ConfPrepareData confen = new ConfPrepareData();
        confen.setLang(CommonConstants.LOCALE_EN);
        confen.setWebsitePlatform(CommonConstants.PC_PLATFORM);
        confen.setIsPreview(false);
        Map<String, Object> retMapEn = batchPublishForAuto.start(confen);
        model.addAttribute("url_en", retMapEn.get("publishUrl"));
        model.addAttribute("success_en", retMapEn.get("success"));
        model.addAttribute("msg_en", retMapEn.get("msg"));
        model.addAttribute("log_en", retMapEn.get("log"));
        
        ConfPrepareData conf = new ConfPrepareData();
        conf.setLang(CommonConstants.LOCALE_CN);
        conf.setWebsitePlatform(CommonConstants.PC_PLATFORM);
        conf.setIsPreview(false);
        Map<String, Object> retMap = batchPublishForAuto.start(conf);
        model.addAttribute("url", retMap.get("publishUrl"));
        model.addAttribute("success", retMap.get("success"));
        model.addAttribute("msg", retMap.get("msg"));
        model.addAttribute("log", retMap.get("log"));
        return "testpublish/result";
    }
    
    /**
     * page pc cn
     * @param model
     * @return
     * @author Administrator
     * Date 2014年12月29日
     * @version
     */
    @RequestMapping(value = "/result_page_pc_cn")
    public String batchPage_pc_cn(Model model) {
        ConfPrepareData conf = new ConfPrepareData();
        conf.setLang(CommonConstants.LOCALE_CN);
        conf.setWebsitePlatform(CommonConstants.PC_PLATFORM);
        conf.setIsPreview(false);
        //conf.setTestPlatform("TEST-1");
        Map<String, Object> retMap = pageListBatch.batchPublish(new Integer(0), conf);
        model.addAttribute("type", "page pc cn");
        model.addAttribute("url", retMap.get("publishUrl"));
        model.addAttribute("success", retMap.get("success"));
        model.addAttribute("msg", retMap.get("msg"));
        model.addAttribute("log", retMap.get("log"));
        return "testpublish/resultall";
    }
    
    @RequestMapping(value = "/result_page_pc_en")
    public String batchPage_pc_en(Model model) {
        ConfPrepareData conf = new ConfPrepareData();
        conf.setLang(CommonConstants.LOCALE_EN);
        conf.setWebsitePlatform(CommonConstants.PC_PLATFORM);
        conf.setIsPreview(false);
        //conf.setTestPlatform("TEST-1");
        Map<String, Object> retMap = pageListBatch.batchPublish(new Integer(0), conf);
        model.addAttribute("type", "page pc en");
        model.addAttribute("url", retMap.get("publishUrl"));
        model.addAttribute("success", retMap.get("success"));
        model.addAttribute("msg", retMap.get("msg"));
        model.addAttribute("log", retMap.get("log"));
        return "testpublish/resultall";
    }
    
    @RequestMapping(value = "/result_category_pc_cn")
    public String batchCategory_pc_cn(Model model) {
        ConfPrepareData conf = new ConfPrepareData();
        conf.setLang(CommonConstants.LOCALE_CN);
        conf.setWebsitePlatform(CommonConstants.PC_PLATFORM);
        conf.setIsPreview(false);
        //conf.setTestPlatform("TEST-1");
        Map<String, Object> retMap = categoryListBatch.batchPublish(new Integer(61), conf);
        
        model.addAttribute("type", "category pc cn 61");
        model.addAttribute("url", retMap.get("publishUrl"));
        model.addAttribute("success", retMap.get("success"));
        model.addAttribute("msg", retMap.get("msg"));
        model.addAttribute("log", retMap.get("log"));
        return "testpublish/resultall";
    }
    
    @RequestMapping(value = "/result_category_pc_en")
    public String batchCategory_pc_en(Model model) {
        ConfPrepareData conf = new ConfPrepareData();
        conf.setLang(CommonConstants.LOCALE_EN);
        conf.setWebsitePlatform(CommonConstants.PC_PLATFORM);
        conf.setIsPreview(false);
        //conf.setTestPlatform("TEST-1");
        Map<String, Object> retMap = categoryListBatch.batchPublish(new Integer(61), conf);
        
        model.addAttribute("type", "category pc en 61");
        model.addAttribute("url", retMap.get("publishUrl"));
        model.addAttribute("success", retMap.get("success"));
        model.addAttribute("msg", retMap.get("msg"));
        model.addAttribute("log", retMap.get("log"));
        return "testpublish/resultall";
    }
    
    @RequestMapping(value = "/result_newarrival_pc_cn")
    public String batchNewArrival_pc_cn(Model model) {
        ConfPrepareData conf = new ConfPrepareData();
        conf.setLang(CommonConstants.LOCALE_CN);
        conf.setWebsitePlatform(CommonConstants.PC_PLATFORM);
        //conf.setTestPlatform("TEST-1");
        Map<String, Object> retMap = newArrivalDetailBatch.batchPublish(new Integer(0), conf);
        
        model.addAttribute("type", "new arrival pc cn ");
        model.addAttribute("url", retMap.get("publishUrl"));
        model.addAttribute("success", retMap.get("success"));
        model.addAttribute("msg", retMap.get("msg"));
        model.addAttribute("log", retMap.get("log"));
        return "testpublish/resultall";
    }
    
    @RequestMapping(value = "/result_newarrival_pc_en")
    public String batchNewArrival_pc_en(Model model) {
        ConfPrepareData conf = new ConfPrepareData();
        conf.setLang(CommonConstants.LOCALE_EN);
        conf.setWebsitePlatform(CommonConstants.PC_PLATFORM);
        //conf.setTestPlatform("TEST-1");
        Map<String, Object> retMap = newArrivalDetailBatch.batchPublish(new Integer(0), conf);
        
        model.addAttribute("type", "new arrival pc en ");
        model.addAttribute("url", retMap.get("publishUrl"));
        model.addAttribute("success", retMap.get("success"));
        model.addAttribute("msg", retMap.get("msg"));
        model.addAttribute("log", retMap.get("log"));
        return "testpublish/resultall";
    }
    
    @RequestMapping(value = "/result_suitedetail_pc_cn")
    public String batchSuiteDetail_pc_cn(Model model) {
        ConfPrepareData conf = new ConfPrepareData();
        conf.setLang(CommonConstants.LOCALE_CN);
        conf.setWebsitePlatform(CommonConstants.PC_PLATFORM);
        //conf.setTestPlatform("TEST-1");
        Map<String, Object> retMap = suiteDetailBatch.batchPublish(new Integer(0), conf);
        
        model.addAttribute("type", "suite detail pc en ");
        model.addAttribute("url", retMap.get("publishUrl"));
        model.addAttribute("success", retMap.get("success"));
        model.addAttribute("msg", retMap.get("msg"));
        model.addAttribute("log", retMap.get("log"));
        return "testpublish/resultall";
    }
    
    @RequestMapping(value = "/result_suitedetail_pc_en")
    public String batchSuiteDetail_pc_en(Model model) {
        ConfPrepareData conf = new ConfPrepareData();
        conf.setLang(CommonConstants.LOCALE_EN);
        conf.setWebsitePlatform(CommonConstants.PC_PLATFORM);
        //conf.setTestPlatform("TEST-1");
        Map<String, Object> retMap = suiteDetailBatch.batchPublish(new Integer(0), conf);
        
        model.addAttribute("type", "suite detail pc en ");
        model.addAttribute("url", retMap.get("publishUrl"));
        model.addAttribute("success", retMap.get("success"));
        model.addAttribute("msg", retMap.get("msg"));
        model.addAttribute("log", retMap.get("log"));
        return "testpublish/resultall";
    }
    

}
