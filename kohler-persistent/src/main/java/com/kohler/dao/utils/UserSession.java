package com.kohler.dao.utils;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.kohler.entity.SysUserEntity;
import com.kohler.entity.PermissionEntity;

@Repository
public class UserSession {
	/**
	 * 设置用户session
	 * @param user
	 */
	public void setSysUser(SysUserEntity user){
		HttpServletRequest request = ( (ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		request.getSession().setAttribute("sysUser", user);
	}
	/**
	 * 获得用户信息
	 * @return
	 */
	public SysUserEntity getSysUser(){
		HttpServletRequest request = ( (ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		if(null != request.getSession().getAttribute("sysUser")){
			return (SysUserEntity)request.getSession().getAttribute("sysUser");
		}
		return null;
	}
	/**
	 * 设置用户权限列表
	 * @param tmPermissionList
	 */
	public void setTmPermissionList(List<PermissionEntity> userPermissionList){
		HttpServletRequest request = ( (ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		request.getSession().setAttribute("tmPermissionList", userPermissionList);
	}
	/**
	 * 删除用户权限列表
	 */
	public void removeTmPermissionList(){
		HttpServletRequest request = ( (ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		if(null != request.getSession().getAttribute("tmPermissionList")){
			request.getSession().removeAttribute("tmPermissionList");
		}
	}
	/**
	 * 设置权限
	 */
	@SuppressWarnings("unchecked")
	public void setMenuPermission(){
		HttpServletRequest request = ( (ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		List<PermissionEntity> listUserPermission = (List<PermissionEntity>) request.getSession().getAttribute("tmPermissionList");
		
		List<PermissionEntity> listTopPermission=new ArrayList<PermissionEntity>();//一级菜单权限
		List<PermissionEntity> listSubPermission=new ArrayList<PermissionEntity>();//二级菜单权限
		List<PermissionEntity> listButtonPermission=new ArrayList<PermissionEntity>();//按钮权限
		String permissionActionUrls = "";   //用户权限actionUrl列表
				
		if(null!=listUserPermission && listUserPermission.size()>0){
			for(PermissionEntity per:listUserPermission){
				if(per.getPermissionType()==1){
					listTopPermission.add(per);
				}else if(per.getPermissionType()==2){
					listSubPermission.add(per);
				}else{
					listButtonPermission.add(per);
				}
				if(null != per.getActionUrl() && !"".equals(per.getActionUrl())){
					permissionActionUrls+=per.getActionUrl()+";";
				}
			}
		}
		
		request.getSession().setAttribute("topPermission", listTopPermission);
		request.getSession().setAttribute("subPermission", listSubPermission);
		request.getSession().setAttribute("buttonPermission", listButtonPermission);
		
		request.getSession().setAttribute("permissionActionUrls", permissionActionUrls);
	}
	/**
	 * 获取一级菜单权限
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PermissionEntity> getTopPermission(){
		HttpServletRequest request = ( (ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		List<PermissionEntity> menuList = (List<PermissionEntity>) request.getSession().getAttribute("topPermission");
		return menuList;
	}
	/**
	 * 获取二级菜单权限
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PermissionEntity> getSubPermission(){
		HttpServletRequest request = ( (ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		List<PermissionEntity> menuList = (List<PermissionEntity>) request.getSession().getAttribute("subPermission");
		return menuList;
	}
	/**
	 * 获取按钮权限
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PermissionEntity> getButtonPermission(){
		HttpServletRequest request = ( (ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		List<PermissionEntity> menuList = (List<PermissionEntity>) request.getSession().getAttribute("buttonPermission");
		return menuList;
	}
	/**
	 * 获得用户权限actionUrl列表
	 * @return
	 */
	public String getPermissionActionUrls(){
		HttpServletRequest request = ( (ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		if(null != request.getSession().getAttribute("permissionActionUrls")){
			return (String) request.getSession().getAttribute("permissionActionUrls");
		}
		return null;
	}
}
