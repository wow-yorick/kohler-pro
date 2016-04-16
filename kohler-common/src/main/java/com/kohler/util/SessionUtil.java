package com.kohler.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;


/**
 * Session工具类
 * @author kangmin_Qu
 *
 */
@Component("sessionUtil")
public class SessionUtil {
	
	/**
	 * 从Request中获取Session对象
	 * @param request
	 * @return
	 */
    public HttpSession getSessionData(HttpServletRequest request) {
    	return request.getSession();
    }
    
	/**
	 * 从Redis中获取Session对象
	 * @param request
	 * @return
	 */
    public static HttpSession getSessionData() {
    	return null;
    }
}
