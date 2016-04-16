package com.kohler.tags;

import java.util.List;

import com.kohler.dao.utils.UserSession;
import com.kohler.entity.PermissionEntity;
import com.kohler.service.PermissionService;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 关于菜单权限的标签类
 * @author zhangtingting
 *
 */
public class PermissionTagOld extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4763169422128223980L;
	
	private String type;
	private String resourceId;
	private String style;
	private String param;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}
	  
	public int doStartTag() throws JspException{
		ApplicationContext ac1 = WebApplicationContextUtils.getRequiredWebApplicationContext(pageContext.getServletContext());
		
		UserSession userSession=ac1.getBean(UserSession.class);
		PermissionService tmPermissionService=ac1.getBean(PermissionService.class);
		
		JspWriter out = pageContext.getOut();
		
		StringBuffer writeContent=new StringBuffer();
		try {
			//获取用户的所有按钮权限
			List<PermissionEntity> buttonPermission= userSession.getButtonPermission();
			if(null!=resourceId && !"".equals(resourceId)){
				if(contain(buttonPermission,resourceId)){//登录用户是否有该按钮的权限
					PermissionEntity tmPer=tmPermissionService.getPermissionBycode(resourceId);
					String actionUrl="";
					if(null!=tmPer && !"".equals(tmPer)){
						actionUrl=tmPer.getActionUrl();
					}
					String inputStyle="border:1;background-color:gray;";
					if(null!=style && !"".equals(style)){
						inputStyle=style;
					}
					if("add".equals(type)){//添加按钮
						writeContent.append("<input type=\"button\" id=\"add\" name=\"add\" value=\"add\" style=\""+inputStyle+"\" onclick=\"addObject('"+actionUrl+"')\" />");
					}
					if("delete".equals(type)){//删除按钮
						writeContent.append("<input type=\"button\" id=\"delete\" name=\"delete\" value=\"delete\" style=\""+inputStyle+"\" onclick=\"deleteObject('"+param+"','"+actionUrl+"')\" />");
					}
					if("update".equals(type)){//修改按钮
						writeContent.append("<input type=\"button\" id=\"update\" name=\"update\" value=\"update\" style=\""+inputStyle+"\" onclick=\"updateObject('"+param+"','"+actionUrl+"')\" />");
					}
					if("preview".equals(type)){//预览按钮
						writeContent.append("<input type=\"button\" id=\"preview\" name=\"preview\" value=\"preview\" style=\""+inputStyle+"\" onclick=\"previewObject('"+param+"','"+actionUrl+"')\" />");
					}
				}
			}
			out.print(writeContent.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;// 标签执行完后，继续执行后面的
	}
	
	/**
	 * 根据用户所拥有的权限显示菜单
	 * @param list 权限集合
	 * @param source 菜单编号
	 * @return
	 */
	public static boolean contain(List<PermissionEntity> list,String source){
		if(null != list && list.size()>0){
			for(PermissionEntity tp:list){
				if(tp.getPermissionCode().equals(source)){
					return true;
				}
			}
		}
		return false;
	}
}
