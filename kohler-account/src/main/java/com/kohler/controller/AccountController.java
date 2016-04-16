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
import com.kohler.entity.AccountEntity;
import com.kohler.entity.MasterDataCodeConstant;

import com.kohler.entity.extend.AccountPojo;
import com.kohler.service.AccountService;
import com.kohler.util.MD5Util;
import com.kohler.util.PropertiesUtils;
/**
 * 
 * 处理系列的类
 *
 * @author fujiajun
 * @Date 2014年10月28日
 */
@Controller
@RequestMapping("/account")
public class AccountController  extends BasicController {
    @Autowired
    private AccountService  AccountService;
    
    @RequestMapping(value = "/accountList")
    public String accountList(Model model, Pager<Map<String, Object>> pager,
            HttpServletRequest request,String Name,String Mobile,Integer Type) {
        if (logger.isInfoEnabled()) {
            logger.info("AccountController.accountList begin");
        }
        String uri = request.getRequestURI() + "?";
        pager =AccountService.getAccountListPage(pager, Name, Mobile, Type);
        pager.setUrl(uri);
        model.addAttribute("pager", pager); 
        List<Map<String,Object>> listdata=AccountService.getselectlist(MasterDataCodeConstant.TYPE_ACCOUNT_TYPE);
        model.addAttribute("listdata", listdata);
        if(Name != null){
            model.addAttribute("Name", Name);
            uri +="Name="+Name;
        }
        if(Mobile != null){
            model.addAttribute("Mobile", Mobile);
            uri +="Mobile="+Mobile;
        }
        if(Type != null){
            model.addAttribute("Type", Type);
            uri +="Type="+Type;
        }
        if (logger.isInfoEnabled()) {
            logger.info("AccountController.accountList end");
        }  
        return "account/accountList";
    }
    /**
     * 会员详细
     * @param model
     * @param isEdit
     * @param accountId
     * @return
     * @author Administrator
     * Date 2014年10月23日
     * @version
     */
    @RequestMapping(value = "/view")
    public String view(Model model,Integer accountId,Integer type) {
        String accounttype;
        if (logger.isInfoEnabled()) {
            logger.info("AccountController.view begin");
        }
        List<Map<String,Object>> list=AccountService.getAccountById(accountId);
        accounttype=list.get(0).get("accounttype").toString();
        model.addAttribute("restpassword", type);
        model.addAttribute("AccountView", list.get(0));
        if (logger.isInfoEnabled()) {
            logger.info("AccountController.view end");
        }
        return (type !=null && type == 1)? "account/accontConsumption" :  this.goreturn_(accounttype);
    }
    
    /**
     * 会员freeze
     * @param model
     * @param isEdit
     * @param accountId
     * @return
     * @author fujiajun
     * Date 2014年11月18日
     * @version
     */
    @RequestMapping(value = "/freeze")
    public String freeze(Model model,Integer accountId)
    {   
       
        if (logger.isInfoEnabled()) {
            logger.info("AccountController.freeze begin");
        }
        List<Map<String,Object>> list=AccountService.getAccountById(accountId);
        //accounttype=list.get(0).get("accounttype").toString();
        model.addAttribute("type", 2);
        model.addAttribute("title","freeze");
        model.addAttribute("AccountView", list.get(0));
        if (logger.isInfoEnabled()) {
            logger.info("AccountController.freeze end");
        }
        return "account/accontConsumption";
    }
    
    
    /**
     * 会员unfreeze
     * @param model
     * @param isEdit
     * @param accountId
     * @return
     * @author Administrator
     * Date 2014年10月23日
     * @version
     */
    @RequestMapping(value = "/unfreeze")
    public String unfreeze(Model model,Integer accountId)
    {   
        if (logger.isInfoEnabled()) {
            logger.info("AccountController.edit begin");
        }
        
        List<Map<String,Object>> list=AccountService.getAccountById(accountId);
        //accounttype=list.get(0).get("accounttype").toString();
        model.addAttribute("type", 2);
        model.addAttribute("title","unfreeze");
        model.addAttribute("AccountView", list.get(0));
        if (logger.isInfoEnabled()) {
            logger.info("AccountController.edit end");
        }
        return "account/accontConsumption";
    }
    
    private String goreturn_(String accounttype){
        String return_="";
        if(accounttype.equals("消费者")){
            return_="account/accontConsumption";
        }else if(accounttype.equals("开发商")){    
            return_="account/accontDevelopersLayer";
        }else if(accounttype.equals("家装设计师")){
            return_="account/accontHomeLayer";
        }else if(accounttype.equals("工装设计师")){
            return_="account/accontWorkLayer";
        }else if(accounttype.equals("雅悦荟会员")){
            return_="account/accontMembersLayer";
        }else{
            return_="account/accountList";
        }
        return return_;
    }
    /**
     * 修改会员信息
     
     * @param AccountPojo
     * @return
     * @author fujiajun
     * Date 2014年10月23日
     * @version
     */
    @RequestMapping(value = "/freezeSave")
    @ResponseBody
    public Map<String, Object> freezeSave(AccountPojo AccountPojo){
        if (logger.isInfoEnabled()) {
            logger.info("AccountController.freezeSave begin");
        }
        Map<String, Object> result = new HashMap<String, Object>();
        AccountEntity AccountEntity = new AccountEntity();
        BeanUtils.copyProperties(AccountPojo,AccountEntity);
        int i=AccountService.updateAccount(AccountEntity,1);
        String msg=(i > 0) ?  PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_UPDATE_SUCCESS) : PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_CREATE_FAILED);
        result.put("msg", msg);
        result.put("success", true);
        if (logger.isInfoEnabled()) {
            logger.info("AccountController.freezeSave end");
        }
        return result;
    }
    
    /**
     * 修改会员信息
     * @param jsonEmity
     * @param AccountPojo
     * @return
     * @author Administrator
     * Date 2014年10月23日
     * @version
     */
    @RequestMapping(value = "/unfreezeSave")
    @ResponseBody
    public Map<String, Object> unfreezeSave(AccountPojo AccountPojo){
        Map<String, Object> result = new HashMap<String, Object>();
        if (logger.isInfoEnabled()){
            logger.info("AccountController.unfreezeSave begin");
        }
        AccountEntity AccountEntity = new AccountEntity();
        BeanUtils.copyProperties(AccountPojo,AccountEntity);
        int i=AccountService.updateAccount(AccountEntity,2);
        String msg=(i > 0) ?  PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_UPDATE_SUCCESS) : PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_CREATE_FAILED);
        result.put("msg", msg);
        result.put("success", true);
        if (logger.isInfoEnabled()){
            logger.info("AccountController.unfreezeSave end");
        }
        return result;
    }
    /**
     * 修改会员密码
     * @param newpassword
     * @param AccountPojo
     * @return
     * @author Administrator
     * Date 2014年10月23日
     * @version
     */
    @RequestMapping(value = "/resetPasswordSave")
    @ResponseBody
    public Map<String, Object> resetPasswordSave(AccountPojo AccountPojo,String password){
        Map<String, Object> result = new HashMap<String, Object>();
        if (logger.isInfoEnabled()){
            logger.info("AccountController.resetPasswordSave begin");
        }
        AccountEntity AccountEntity = new AccountEntity();
        BeanUtils.copyProperties(AccountPojo,AccountEntity);
        AccountEntity.setpassword(MD5Util.md5Hex(password)); 
        int i=AccountService.restpassword(AccountEntity);
        
        String msg=(i > 0) ?  PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_UPDATE_SUCCESS) : PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_CREATE_FAILED);
        result.put("msg", msg);
        result.put("success", true);
        if (logger.isInfoEnabled()){
            logger.info("AccountController.resetPasswordSave end");
        }
        return result;
    }
    
}
