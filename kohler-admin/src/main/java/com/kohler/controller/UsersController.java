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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kohler.constants.CommonConstants;
import com.kohler.dao.utils.Pager;
import com.kohler.entity.RoleEntity;
import com.kohler.entity.SysUserEntity;
import com.kohler.entity.UserRoleEntity;
import com.kohler.entity.extend.SysUserPojo;
import com.kohler.service.UsersService;
import com.kohler.util.PropertiesUtils;

/**
 * user admin
 *
 * @author Administrator
 * @Date 2014年11月18日
 */
@Controller
@RequestMapping(value = "/user")
public class UsersController extends BasicController {
    
    @Autowired
    private UsersService usersService;
    
    /**
     * 用户列表
     * @param model
     * @param pager
     * @param request
     * @return
     * @author Administrator
     * Date 2014年11月18日
     * @version
     */
    @RequestMapping(value = "/userList")
    public String userList(Model model, Pager<Map<String, Object>> pager,
            HttpServletRequest request) {
        
        if (logger.isInfoEnabled()) {
            logger.info("UsersController.userList begin");
        }
        
        String uri = request.getRequestURI() + "?";
        
        String userName = request.getParameter("userName");
        String realName = request.getParameter("realName");
        SysUserEntity sysUserEntity = new SysUserEntity();
        sysUserEntity.setUserName(userName);
        sysUserEntity.setRealName(realName);
        
        pager = usersService.getUsersPage(pager, sysUserEntity);
        pager.setUrl(uri);
        model.addAttribute("pager", pager);
        model.addAttribute("userName",userName);
        model.addAttribute("realName", realName);
        
        if (logger.isInfoEnabled()) {
            logger.info("UsersController.userList end");
        }
        
        return "admin/userList";
    }
    
    /**
     * 创建用户
     * @param model
     * @return
     * @author Administrator
     * Date 2014年11月18日
     * @version
     */
    @RequestMapping(value = "/create")
    public String create(Model model) {
        
        if (logger.isInfoEnabled()) {
            logger.info("UsersController.create begin");
        }
        //init roles
        List<RoleEntity> allRoles = usersService.getRoles();
        
        model.addAttribute("allRoles", allRoles);
        
        if (logger.isInfoEnabled()) {
            logger.info("UsersController.create end");
        }
        
        return "admin/userNew";
    }
    
    /**
     * 
     * @param sysUserPojo
     * @param userRole
     * @return
     * @author XHY
     * Date 2014年12月9日
     * @version
     */
    @RequestMapping(value = "/createSave")
    @ResponseBody
    public Map<String, Object> createSave(SysUserPojo sysUserPojo, @RequestParam(value="userRole")String userRole) {
        Map<String, Object> result = new HashMap<String, Object>();
        
        if (logger.isInfoEnabled()) {
            logger.info("UsersController.createSave begin");
        }
        
        SysUserEntity sysUserEntity = new SysUserEntity();
        BeanUtils.copyProperties(sysUserPojo,sysUserEntity);
        
        usersService.addUser(sysUserEntity,userRole);
        
        String msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_CREATE_SUCCESS);
        result.put("msg", msg);
        result.put("success", true);

        
        if (logger.isInfoEnabled()) {
            logger.info("UsersController.createSave end");
        }
        
        return result;
        
    }
    
    /**
     * 
     * @param model
     * @param sysUserId
     * @param type
     * @return
     * @author XHY
     * Date 2014年12月9日
     * @version
     */
    @RequestMapping(value = "/edit")
    public String edit(Model model,Integer sysUserId,Integer type) {
        
        if (logger.isInfoEnabled()) {
            logger.info("UsersController.edit begin");
        }
        SysUserEntity syUser = usersService.getUserDetail(sysUserId);
        List<UserRoleEntity> userRoleList = usersService.getUserRoleList(sysUserId);
//        
//        model.addAttribute("userRoleList", userRoleList);
        //init roles
        List<RoleEntity> allRoles = usersService.getRoles();
        
        model.addAttribute("allRoles", allRoles);
        if(userRoleList!=null&&userRoleList.size()>0){
              model.addAttribute("userRole", userRoleList.get(0));
        }
        model.addAttribute("sysUser", syUser);
        model.addAttribute("type", type);
        
        if (logger.isInfoEnabled()) {
            logger.info("UsersController.edit end");
        }
        
        return "admin/userEdit";
    }   
    
    /**
     * 
     * @param sysUserPojo
     * @param userRole
     * @return
     * @author XHY
     * Date 2014年12月9日
     * @version
     */
    @RequestMapping(value = "/editSave")
    @ResponseBody
    public Map<String, Object> editSave(SysUserPojo sysUserPojo, @RequestParam(value="userRole")String userRole) {
        
        if (logger.isInfoEnabled()) {
            logger.info("UsersController.editSave begin");
        }
        
        Map<String, Object> result = new HashMap<String, Object>();

        SysUserEntity sysUserEntity = new SysUserEntity();
        BeanUtils.copyProperties(sysUserPojo, sysUserEntity);
        
        usersService.editUser(sysUserEntity,userRole);
        
        String msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_UPDATE_SUCCESS);
        result.put("success", true);
        result.put("msg", msg);  
     
        
        if (logger.isInfoEnabled()) {
            logger.info("UsersController.editSave end");
        }
        
        return result;
    }
    


    /**
     * 
     * @param sysUserIds
     * @return
     * @author XHY
     * Date 2014年11月25日
     * @version
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Map<String, Object> delete(Integer[] sysUserIds) {
        
        if (logger.isInfoEnabled()) {
            logger.info("SuiteController.delete begin");
        }
        
        Map<String, Object> result = new HashMap<String, Object>();
        
        String msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_DELETE_FAILED);
        
        boolean isSuccess = usersService.deleteUserByIds(sysUserIds);
        
        if(isSuccess){
        	msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_DELETE_SUCCESS);
        }
        
        result.put("msg", msg);
        result.put("success", true);

        
        if (logger.isInfoEnabled()) {
            logger.info("SuiteController.delete end");
        }
        
        return result;
       
    }
    
    /**
	 * @param catalogName
	 * @param lang
	 * @param categoryMetadataId
	 * @return
	 * @author XHY Date 2014年10月22日
	 * @version
	 */
	@RequestMapping(value = "/unlimited/checkUserName")
	@ResponseBody
	public Map<String, Object> checkUserName(String userName,Integer userId) {
		Map<String, Object> result = new HashMap<String, Object>();

		int isUnique = -1;
		if (userId == null) {
			isUnique = usersService.checkUserName(userName);
		} else {
			isUnique = usersService.checkUserNameOutSelf(userName, userId);
		}

		result.put("flag", isUnique);
		return result;
	}
    
    
}
