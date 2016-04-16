package com.kohler.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kohler.constants.CommonConstants;
import com.kohler.dao.utils.UserSession;
import com.kohler.entity.SysUserEntity;
import com.kohler.entity.extend.SysUserPojo;
import com.kohler.service.impl.HomeServiceImpl;
import com.kohler.util.MD5Util;
import com.kohler.util.PropertiesUtils;

/**
 * Change Password
 * 
 * @author wuyun
 * @Date 2014年11月6日
 */
@Controller
@RequestMapping(value = "/home")
public class HomeController extends BasicController {
    @Autowired
    private HomeServiceImpl homeServiceImpl;

    @Autowired
    private UserSession userSession;

    /**
     * 
     * @param model
     * @param home
     * @return
     */
    @RequestMapping(value = "/unlimited/changePassword")
    public String getSysUserById(Model model, SysUserEntity user, HttpServletRequest request) {
        SysUserEntity userSessionInfo = userSession.getSysUser();
        if (userSessionInfo != null) {
            user = homeServiceImpl.getSysUserById(userSessionInfo.getSysUserId());
        }
        model.addAttribute("user", user);
        return "home/changePassword";
    }

    @RequestMapping(value = "/unlimited/editSysUser")
    @ResponseBody
    public Map<String, Object> editSysUser(SysUserPojo sysUserPojo, HttpServletRequest request,
            String oldPwd) {
        if (logger.isInfoEnabled()) {
            logger.info("HomeController.edit begin");
        }
        Map<String, Object> result = new HashMap<String, Object>();
        String msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_UPDATE_FAILED);
        SysUserEntity sysEntity = new SysUserEntity();
        BeanUtils.copyProperties(sysUserPojo, sysEntity);
        // 判断原密码是否正确
        SysUserEntity sysUser = homeServiceImpl.getSysUserById(sysUserPojo.getSysUserId());
        if (sysUser != null) {
            if (!sysUser.getPassword().equals(MD5Util.md5Hex(oldPwd))) {
                msg = "The original password input error!";
            }else if (sysUser.getPassword().equals(MD5Util.md5Hex(sysEntity.getPassword()))) {
                msg = "With the recent new password cannot be used the same password!";
            }else {
                sysEntity.setPassword(MD5Util.md5Hex(sysEntity.getPassword()));
                int count = homeServiceImpl.editSysUser(sysEntity);
                if (count >= 0) {
                    msg = PropertiesUtils
                            .findPropertiesKey(CommonConstants.INFO_COMMON_UPDATE_SUCCESS);
                }
            }
        }
        result.put("msg", msg);
        if (logger.isInfoEnabled()) {
            logger.info("HomeController.editSysUser end");
        }
        return result;
    }

}
