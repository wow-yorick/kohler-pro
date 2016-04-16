package com.kohler.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kohler.constants.CommonConstants;
import com.kohler.dao.utils.UserSession;
import com.kohler.entity.PermissionEntity;
import com.kohler.entity.RolePermissionEntity;
import com.kohler.entity.SysUserEntity;
import com.kohler.entity.UserRoleEntity;
import com.kohler.service.PermissionService;
import com.kohler.service.RolePermissionService;
import com.kohler.service.SysUserService;
import com.kohler.service.UserRoleService;
import com.kohler.util.MD5Util;
import com.kohler.util.PropertiesUtils;

@Controller
@RequestMapping("/base")
public class LoginController extends BasicController {
	
	@Autowired
	private UserSession userSession;
	
	@Autowired
	private SysUserService userService;
	
	@Autowired
	private PermissionService userPermissionService;
	
	@Autowired
	private UserRoleService userRoleRelService;
	
	@Autowired
	private RolePermissionService rolePermissionRelService;
	
	@RequestMapping(value = "/login")
	public String loginAction(Map<String, Object> map,HttpServletRequest request) {
		return "base/login";
	}
	/**
	 * 用户登录
	 * @param response
	 * @param request
	 * @param userName
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/dologin")
	@ResponseBody
	public Map<String,Object> dologin(HttpServletResponse response,HttpServletRequest request,
			@RequestParam(value = "userName") String userName,
			@RequestParam(value = "password") String password) {
	    Map<String, Object> map=new HashMap<String, Object>();
	    if (logger.isInfoEnabled()) {
            logger.info("LoginController.dologin begin");
        }
	    map.put("status", "1");
		// 加密
		// 用户名密码不为空
		if (null != userName && !"".equals(userName) && null != password && !"".equals(password)) {
			// 获得用户信息
			SysUserEntity loginUser=userService.getUserByNamePwd(userName);
			// 如果用户存在
			if (null != loginUser) {
			    if(loginUser.getPassword().equals(MD5Util.md5Hex(password))){
			        StringBuffer sb=new StringBuffer();
	                int userId = loginUser.getSysUserId();
	                if(userId != 0){
	                    //根据用户id,获取用户的角色id
	                    List<UserRoleEntity> listUserRoleRel=userRoleRelService.getListUserRole(userId);
	                    if(null!=listUserRoleRel && listUserRoleRel.size()>0){
	                        for(UserRoleEntity rel:listUserRoleRel){
	                            if(null!=rel){
	                                //根据角色id,获取所有的权限id
	                                List<RolePermissionEntity> listRolePermissionRel=rolePermissionRelService.getListRolePermissionRel(rel.getRoleId());
	                                if(null!=listRolePermissionRel && listRolePermissionRel.size()>0){
	                                    for(RolePermissionEntity prel:listRolePermissionRel){
	                                        if(null!=prel){ 
	                                            sb.append(prel.getPermissionId()+",");
	                                        }
	                                    }
	                                }
	                            }
	                        }
	                    }
	                }
	                // 获得当前用户获取用户所有的权限
	                List<PermissionEntity> listUserPermission=new ArrayList<PermissionEntity>();
	                if(null!=sb && sb.length()>0){
	                    String[] permissionIds=sb.toString().split(",");
	                    Integer [] ids=new Integer[permissionIds.length];
	                    for(int i=0;i<permissionIds.length;i++){
	                        ids[i]=Integer.parseInt(permissionIds[i]);
	                    }
	                    listUserPermission=userPermissionService.getListTmPermission(ids);
	                }
	                // 设置用户SESSION
	                userSession.setSysUser(loginUser);
	                // 设置用户角色对应的权限
	                userSession.setTmPermissionList(listUserPermission);
	                userSession.setMenuPermission();
	                //登录之后跳转的页面（默认页面）
	                String msg =PropertiesUtils.findPropertiesKey(CommonConstants.INFO_USER_LOGIN_SUCCESS);
	                map.put("status", "0");
	                map.put("msg", msg);
			    }else{
			        String msg =PropertiesUtils.findPropertiesKey(CommonConstants.INFO_USER_lOGIN_PASSWORD);
			        map.put("msg", msg);
			    }
			}else{
			    String msg =PropertiesUtils.findPropertiesKey(CommonConstants.INFO_USER_lOGIN_USERNAME);
			    map.put("msg", msg);
			}
		}
		if (logger.isInfoEnabled()) {
            logger.info("LoginController.dologin end");
        }
		return map;
	}
	
	/**
	 * 用户注销登录
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/logout")
    @ResponseBody
    public Map<String, Object>  loginOut(HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		HttpSession session1 = request.getSession();
		  
		session1.invalidate();
		String msg="logout success";
		map.put("msg", msg);
        return map;
	}
}
