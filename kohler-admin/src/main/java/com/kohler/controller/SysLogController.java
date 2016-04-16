/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.controller;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kohler.constants.CommonConstants;
import com.kohler.dao.utils.Pager;
import com.kohler.service.SysLogService;
import com.kohler.util.PropertiesUtils;
/**
 * 
 * 用于处理LOG日志相关的类
 * Class Function Description
 *
 * @author fujiajun
 * @Date 2014年10月21日
 * 
 */
@Controller
@RequestMapping("/log")
public class SysLogController extends BasicController {
    @Autowired
    private SysLogService syslogservice;
    //查询列表
    @RequestMapping("/logList")
    public String LogListpage(Model model,HttpServletRequest request,Pager<Map<String,Object>> pager,String search,String operation,String sdate,String edate){
        if (logger.isInfoEnabled()) {
            logger.info("SysLogController.LogListpage begin");
        }  
        Map<String,Object> map =new HashMap<String,Object>();
        String uri = request.getRequestURI() + "?";
        if(operation != null && !"".equals(operation)){ 
            map.put("operation", operation);
            uri += "operation="+operation+"&";
        }
        //用于判断是否为查询还是默认列表
        if(search == null){
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();  
            calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
            sdate= format.format(calendar.getTime());
            map.put("sdate", sdate);
            //calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
            edate= format.format(new Date());
            map.put("edate", edate);
           
            uri += "sdate="+sdate+"&edate="+edate+"&";
        }else{
            if(sdate != null && !"".equals(sdate)){ 
                map.put("sdate", sdate);
                uri += "sdate="+sdate+"&";
            }
            if(edate != null && !"".equals(edate)){ 
                map.put("edate", edate);
                uri += "edate="+edate+"&";
            }
        }
        //uri += "sdate="+sdate+"&edate="+edate+"&operation="+operation+"&search=search&";
        pager = syslogservice.GetbyRoleList(pager, map);
        pager.setUrl(uri);
        model.addAttribute("pager", pager);
        model.addAttribute("operation", operation);
        model.addAttribute("sdate", sdate);
        model.addAttribute("edate", edate);
        if (logger.isInfoEnabled()) {
            logger.info("SysLogController.getCategoryList end");
        }  
        return "admin/syslogList";
    }
    //删除 指定日志
    @RequestMapping("/delete")
    @ResponseBody
    public Map<String,Object> delectSyslog(Model model,String syslogid){
        if (logger.isInfoEnabled()) {
            logger.info("SysLogController.delectSyslog begin");
        }
        String msg = "";
        Map<String, Object> result = new HashMap<String, Object>();
        if(syslogid != null &&  !"".equals(syslogid)){
            result.put("ids",syslogid);
            result.put("type",1);
            syslogservice.deleteSyslogs(result);
            result.clear();
            msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_DELETE_SUCCESS);
        }else{
            msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_DELETE_FAILED);
        }
        result.put("msg", msg);
        if (logger.isInfoEnabled()) {
            logger.info("SysLogController.delectSyslog end");
        }
        return result;
    }
    //清空指定日志
    @RequestMapping("/clear")
    @ResponseBody
    public Map<String,Object> clearSyslog(Model model,String sdate,String edate,String operation){
        if (logger.isInfoEnabled()) {
            logger.info("SysLogController.clearSyslog begin");
        }
        String msg = "";
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("edate",edate);
        result.put("type",2);
        result.put("sdate",sdate);
        result.put("operation",operation);
        syslogservice.deleteSyslogs(result);
        result.clear();
        msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_DELETE_SUCCESS);
        result.put("msg", msg);
        if (logger.isInfoEnabled()) {
            logger.info("SysLogController.clearSyslog end");
        }
        return result;
    }
}
