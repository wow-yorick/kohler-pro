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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kohler.constants.CommonConstants;
import com.kohler.dao.utils.Pager;
import com.kohler.entity.RoleEntity;
import com.kohler.entity.RoleRightEntity;
import com.kohler.service.RoleService;
import com.kohler.util.JSonUtil;
import com.kohler.util.PropertiesUtils;

/**
 * 
 * 用于处理权限相关的类
 * Class Function Description
 *
 * @author fujiajun
 * @Date 2014年10月21日
 * 
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BasicController {
    @Autowired
    public RoleService roleservice;
    /**
     * @param request
     * @param model
     * @param pager
     * @param rolename  
     * @return String 
     * @author fujiajun Date 2014年10月14日
     * @version
     */
    @RequestMapping("/roleList")
    public String RoleListpage(Model model,HttpServletRequest request,Pager<RoleEntity> pager, String rolename){
        if (logger.isInfoEnabled()) {
            logger.info("RoleController.roleList begin");
        }   
        Map<String,Object> map =new HashMap<String,Object>();
           String uri = request.getRequestURI() + "?";
           if(rolename != null && !"".equals(rolename)){
               uri +="rolename="+rolename+"&";
               map.put("rolename", rolename);
           }
          pager = roleservice.getRoleList(pager, map);
          pager.setUrl(uri);
          model.addAttribute("pager", pager);
          model.addAttribute("rolename", rolename);
          if (logger.isInfoEnabled()) {
              logger.info("RoleController.roleList end");
          }  
          return "admin/roleList";
    }
    /**
     * @param request
     * @param model
     * @param pager
     * @param roleids 数据Id串
     * @param type  
     * @return String 
     * @author fujiajun Date 2014年10月14日
     * @version
     */
    @RequestMapping("/edit")
    public String edit(Model model,HttpServletRequest request,String roleids,Integer type){
        if (logger.isInfoEnabled()) {
            logger.info("RoleController.edit begin");
        }
        List<Map<String, Object>> list= null;
        list=roleservice.getUserTree();
        model.addAttribute("jsondata", JSonUtil.toJSonString(list));
        list=roleservice.getRoleWithMap(roleids);
        model.addAttribute("roledata",list.get(0));
        model.addAttribute("type",type);
        if (logger.isInfoEnabled()) {
            logger.info("RoleController.edit end");
        }  
        return "admin/roleEdit";
    }
    /**
     * @param request
     * @param model
     * @param pager
     * @param rolesid PERMISSION PARENT_ID 数据Id串
     * @param roleid    Role编号
     * @return String 
     * @author fujiajun Date 2014年10月14日
     * @version
     */
    @RequestMapping("/editSave")
    @ResponseBody
    public  Map<String, Object>  editSave(Model model,String rolename,String rolesid,Integer roleid){
        if (logger.isInfoEnabled()) {
            logger.info("RoleController.editSave begin");
        }
        String msg = "";
        Map<String, Object> result = new HashMap<String, Object>();
        if(roleid != null && rolename != null &&  !"".equals(rolename) && rolesid != null &&  !"".equals(rolesid)){
            RoleEntity re=new RoleEntity();
            re.setRoleId(roleid);
            re.setRoleName(rolename);
            int go=roleservice.editRole(re);
            if(go == 1){
               msg=roleid.toString();
               roleservice.deleteRole(msg,2);
               String s[]=rolesid.split(",");
               this.insertrr(s, re.getRoleId());
                msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_UPDATE_SUCCESS);
            }else{
                msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_UPDATE_FAILED);
            }
        }else{
            msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_UPDATE_FAILED);
        }
        result.put("msg", msg);
        if (logger.isInfoEnabled()) {
            logger.info("RoleController.editSave end");
        }  
        return result;
    }
    
    
    /**
     * @param request
     * @param model
     * @param pager
    
     * @return String 
     * @author fujiajun Date 2014年10月14日
     * @version
     */
    @RequestMapping("/create")
    public String create(Model model,HttpServletRequest request){
        if (logger.isInfoEnabled()) {
            logger.info("RoleController.create begin");
        }
        List<Map<String, Object>> list= roleservice.getUserTree();
        model.addAttribute("jsondata", JSonUtil.toJSonString(list));
        if (logger.isInfoEnabled()) {
            logger.info("RoleController.create end");
        }  
        return "admin/roleNew";
    }
    
    /**
     * @param request
     * @param model
     * @param rolesid //Role表 Id 字符串
    
     * @return String 
     * @author fujiajun Date 2014年10月14日
     * @version
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Map<String,Object> delete(Model model,String rolesid){
        if (logger.isInfoEnabled()) {
            logger.info("RoleController.delete begin");
        }
        String msg = "";
        Map<String, Object> result = new HashMap<String, Object>();
        if(rolesid != null &&  !"".equals(rolesid)){
            int yes=roleservice.deleteRole(rolesid,1);
            msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_DELETE_SUCCESS);
        }else{
            msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_DELETE_FAILED);
        }
        result.put("msg", msg);
        if (logger.isInfoEnabled()) {
            logger.info("RoleController.delete end");
        }  
        return result;
    }
    private void insertrr(final String[] s,final int rid){
        RoleRightEntity rre=new RoleRightEntity();
        for (String x : s) {
            if(x != null ){
                int ids=Integer.parseInt(x);
                rre.setRoleId(rid);
                rre.setRightId(ids);
                rre=roleservice.addRoleRight(rre);
            }
        }
    }
    /**
     * @param request
     * @param model
     * @param rolesid  PERMISSION PARENT_ID 数据Id串
    
     * @return String 
     * @author fujiajun Date 2014年10月14日
     * @version
     */
    @RequestMapping("/createSave")
    @ResponseBody
    public  Map<String, Object> createSave(Model model,String rolename,String rolesid){
        if (logger.isInfoEnabled()) {
            logger.info("RoleController.createSave begin");
        }
         String msg = "";
         Map<String, Object> result = new HashMap<String, Object>();
         if(rolename != null &&  !"".equals(rolename) && rolesid != null &&  !"".equals(rolesid)){
             RoleEntity re=new RoleEntity();
             re.setRoleName(rolename);
             re=roleservice.addRole(re);
             if(re.getRoleId() != null)
             {  
                 String s[]=rolesid.split(",");
//                 RoleRightEntity rre=new RoleRightEntity();
//                 for (String x : s) {
//                     if(x != null ){
//                         int ids=Integer.parseInt(x);
//                         rre.setRoleId(re.getRoleId());
//                         rre.setRightId(ids);
//                         rre=roleservice.addRoleRight(rre);
//                     }
//                 }
                 this.insertrr(s, re.getRoleId());
                 msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_CREATE_SUCCESS);
                 
             }else{
                 msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_CREATE_FAILED);
             }
         }else{
             msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_CREATE_FAILED);
         }
         result.put("msg", msg);
         if (logger.isInfoEnabled()) {
             logger.info("RoleController.createSave end");
         }  
         return result;
    }
    
}