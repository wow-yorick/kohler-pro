package com.kohler.tags;

import java.util.List;

import com.kohler.dao.utils.UserSession;
import com.kohler.entity.PermissionEntity;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 关于菜单权限的标签类
 * @author zhangtingting
 *
 */
public class PermissionTag extends BodyTagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4763169422128223980L;
	
	private String resourceId;
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	private String body=null;//用于存放成功条件后的内容
	public String getBody() {
		if(body==null){
			return bodyContent.getString().trim();
		}else{
			return body;
		}
	}
	public void setBody() {
		if(body==null){
			body=bodyContent.getString().trim();
		}
	}
	private boolean subtagSucceeded;//判断标签是否提交成功
	public void succeeded(){//判断成功
		subtagSucceeded=true;
	}
	private void init(){
		subtagSucceeded=false;
		body=null;
	}
	public int doStartTag() throws JspException{
		ApplicationContext ac1 = WebApplicationContextUtils.getRequiredWebApplicationContext(pageContext.getServletContext());
		
		UserSession userSession=ac1.getBean(UserSession.class);
		
		try {
			//获取用户的所有按钮权限
			List<PermissionEntity> buttonPermission= userSession.getButtonPermission();
			if(null!=resourceId && !"".equals(resourceId)){
				if(contain(buttonPermission,resourceId)){//登录用户是否有该按钮的权限
					this.succeeded();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return EVAL_BODY_BUFFERED;//  系统开始运行这个标签时自动调用
	}
	
	public int doEndTag() throws JspException{
		JspWriter out = pageContext.getOut();
		
		try {
			if(subtagSucceeded){
				out.print(getBody());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		init();
		return super.doEndTag();
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
