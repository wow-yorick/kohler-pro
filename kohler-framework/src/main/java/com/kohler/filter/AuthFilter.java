/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;




import com.kohler.util.SessionUtil;
import com.kohler.util.SysContent;

/**
 * Page Interface
 *
 * @author kangmin.qu
 * @Date 2014年9月27日
 */
@Component("authFilter")
public class AuthFilter implements Filter {

	static Logger logger = Logger.getLogger(AuthFilter.class.getName());
	
	
	@Autowired
	public SessionUtil sessionUtil;
	
    private static final String STR_SLASH = "/";
    private String redirectURL = "/base/login.action";

    
    /**
     * 过滤器实现
     */
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        SysContent.setRequest(request);
        SysContent.setResponse(response);

        
        String redirectPath = request.getContextPath()+ redirectURL;//未登录用户重定向路径
        String servletPath = request.getServletPath();   // 当前路径
        String currentURI = request.getRequestURI(); // 取得根目录所对应的绝对路径:
        
        String targerURI = currentURI.substring(currentURI.indexOf(STR_SLASH, 1));
        
        logger.info("Request uri = "+targerURI);
        
        /*HttpSession session = sessionUtil.getSessionData(request);
        
        if(session.getAttribute(CommonConstants.USER_LOGIN_SESSION) == null){
        	response.sendRedirect(request.getContextPath() + redirectURL);
        }*/
        
        /*
		 * 获取公共路径集合
		 */
		String[] publicStringsList = new String[]{"base/login.action","base/dologin.action","/unlimited","/common"};

		if(request.getSession() != null){
		    
		    /*
	         * 判断是否为公共文件
	         */
	        if (isContains(currentURI, publicStringsList)) {
	            filterChain.doFilter(request, response);
	            return;
	        }
	        /*
	         * 如果用户未登录，则跳转至配置重定向路径
	         */
	        if (request.getSession().getAttribute("sysUser") != null) {
	            /*
	             * 登录用户权限判断
	             */
	            //获取登录用户权限路径集合
	            String permissionActionUrls = (String) request.getSession().getAttribute("permissionActionUrls");
	            if (null != permissionActionUrls && !"".equals(permissionActionUrls)) {
	                String[] permissionStringList = permissionActionUrls.replace("#", "").trim().split(";");
	                //判断权限
	                if (isContains(servletPath, permissionStringList)) {
	                    filterChain.doFilter(request, response);
	                    return;
	                }else{
	                    response.sendRedirect(redirectPath);
	                }
	            }else{
	                response.sendRedirect(redirectPath);
	            }
	        }else{
	            response.sendRedirect(redirectPath);
	        }
		}
    }

    /**
     * 初始化过滤器
     */
    public void init(FilterConfig arg0) throws ServletException {

    }

    /**
     * 销毁过滤器
     */
	public void destroy() {
	}

	/**
	 * 判断用户是否包含该权限
	 * 
	 * @param container
	 * @param regx
	 * @return
	 */
	public static boolean isContains(String container, String[] regx) {
		boolean result = false;
		for (int i = 0; i < regx.length; i++) {
			regx[i] = regx[i].trim();
			if(null != regx[i] && !"".equals(regx[i])){
			    if (container.indexOf(regx[i]) != -1) {
	                result = true;
	            } 
			}
		}
		return result;
	}
}
