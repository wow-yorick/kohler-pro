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
import org.springframework.web.bind.annotation.ResponseBody;

import com.kohler.constants.CommonConstants;
import com.kohler.dao.utils.Pager;
import com.kohler.entity.MasterDataCodeConstant;
import com.kohler.entity.NewArrivalMetadataEntity;
import com.kohler.service.ContactusService;
import com.kohler.util.PropertiesUtils;

/**
 * 
 * 处理Contactus的类
 *
 * @author fujiajun
 * @Date 2014年10月28日
 */
@Controller
@RequestMapping("/contactUs")
public class ContactusController extends BasicController {
    @Autowired
    private ContactusService ContactusService;
    
    @RequestMapping(value = "/contactUsList")
    public String ContactusList(Model model, Pager<Map<String, Object>> pager,
            HttpServletRequest request,Integer Status) {
        if (logger.isInfoEnabled()) {
            logger.info("ContactusController.ContactusList begin");
        }
        String uri = request.getRequestURI() + "?";
        pager =ContactusService.getContactusListPage(pager, Status);
        System.out.println("pager"+pager.getDatas().size());
        pager.setUrl(uri);
        model.addAttribute("pager", pager); 
        List<Map<String,Object>> listdata=ContactusService.getselectlist(MasterDataCodeConstant.TYPE_CONTACT_STATUS_TYPE);
        model.addAttribute("listdata", listdata);
        if(Status != null){
            model.addAttribute("Status", Status);
            uri +="Status="+Status;
        }
        if (logger.isInfoEnabled()) {
            logger.info("ContactusController.ContactusList end");
        }  
        return "account/contactUsList";
    }
    
    @RequestMapping(value = "/cancelSave")
    @ResponseBody
    public  Map<String,Object> cancelSave(Model model,Integer contactusid)
    {
        Map<String, Object> result = new HashMap<String, Object>();
        if (logger.isInfoEnabled()) {
            logger.info("ContactusController.cancelSave begin");
        }
        int i=ContactusService.ReplySave(contactusid, "");
        String msg=(i > 0) ?  PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_UPDATE_SUCCESS) : PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_UPDATE_FAILED);
        result.put("msg", msg);
        result.put("success", true);
        if (logger.isInfoEnabled()) {
            logger.info("ContactusController.cancelSave end");
        }
        return result;
        
    }
    
    @RequestMapping(value = "/replySave")
    @ResponseBody
    public Map<String,Object> replySave(Model model,Integer contactusid,String replyContent)
    {
        Map<String, Object> result = new HashMap<String, Object>();
        if (logger.isInfoEnabled()) {
            logger.info("ContactusController.replySave begin");
        }
        int i=ContactusService.ReplySave(contactusid, replyContent);
        String msg=(i > 0) ?  PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_UPDATE_SUCCESS) : PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_UPDATE_FAILED);
        result.put("msg", msg);
        result.put("success", true);
        if (logger.isInfoEnabled()) {
            logger.info("ContactusController.replySave end");
        }
        return result; 
    }
    /**
     * contactUs reply
     * @param model
     * @param contactusid
     * @return
     * @author Administrator
     * Date 2014年10月23日
     * @version
     */
    @RequestMapping(value = "/reply")
    public String reply(Model model,Integer contactusid,Integer type)
    {   
        if (logger.isInfoEnabled()) {
            logger.info("ContactusController.reply begin");
        }   
        List<Map<String,Object>> list=ContactusService.getview(contactusid);
        model.addAttribute("ContactusView", list.get(0));
        model.addAttribute("type", type);
        ContactusService.Reply(contactusid);
        if (logger.isInfoEnabled()) {
            logger.info("ContactusController.reply end");
        }
        return "account/contactUsLayer";
    }
    
   
    
    
}
