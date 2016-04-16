/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kohler.dao.utils.UserSession;
import com.kohler.entity.PermissionEntity;
import com.kohler.service.PermissionService;

/**
 * 公共请求
 *
 * @author shefeng
 * @Date 2014年9月25日
 */
@Controller
@RequestMapping(value = "/common")
public class CommonController {
	
	@Autowired
	private UserSession userSession;

	@Autowired
	public PermissionService tmPermissionService;
	
	
	@RequestMapping("/index")
    public String index() {
        return "common/index";
    }

	/**
     * @param model
     * @return
     * @author shefeng Date 2014年9月30日
     * @version
     */
	@RequestMapping(value = "/getPermissions")
	public String userList(Model model) {
		List<PermissionEntity> tmPermissions = tmPermissionService.getPermissions();
		model.addAttribute("tmPermissions",tmPermissions);
		return "log/logList";
	}
	
	
	
	/**
	 * 菜单栏
     * @param model
     * @param map
     * @return
     * @author shefeng Date 2014年9月30日
     * @version
     */
	@RequestMapping("/header")
	public String header(Map<String, Object> map,Model model) {
		List<PermissionEntity> tmPermissions = tmPermissionService.getPermissions();
		
		List<PermissionEntity> listTopMenu=new ArrayList<PermissionEntity>();//一级菜单
		List<PermissionEntity> listSubMenu=new ArrayList<PermissionEntity>();//二级菜单
		
		if(null!=tmPermissions && tmPermissions.size()>0){
			for(PermissionEntity per:tmPermissions){
				if(per.getPermissionType()==1){
					listTopMenu.add(per);
				}else if(per.getPermissionType()==2){
					listSubMenu.add(per);
				}
			}
		}
		/*//登录用户的权限
		userSession.setMenuPermission();*/
		map.put("topPermission", userSession.getTopPermission());//一级菜单权限
		map.put("subPermission", userSession.getSubPermission());//二级菜单权限
		map.put("loginUser", userSession.getSysUser());
		
		model.addAttribute("topMenu",listTopMenu);
		model.addAttribute("subMenu", listSubMenu);
		
		return "common/header";
	}
	
	/**
     * 底部信息
     * @param model
     * @return
     * @author shefeng Date 2014年9月30日
     * @version
     */
	@RequestMapping("/footer")
	public String footer(Model model) {
		return "common/footer";
	}

}
