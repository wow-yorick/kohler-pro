/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.kohler.constants.CommonConstants;
import com.kohler.util.PropertiesUtils;

/**
 * Framework BasicController
 *
 * @author kangmin_Qu
 * @Date 2014-10-17
 */
@Controller
public class BasicController {

    /**
     * LOG4J Print Object
     */
    protected final static Logger logger = Logger.getLogger(BasicController.class);
    
    @ExceptionHandler(value={SQLException.class, RuntimeException.class, Exception.class})
    public ModelAndView handleException(Exception ex, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("msg", PropertiesUtils.findPropertiesKey(
//                CommonConstants.ERROR_COMMON_ACTION_001));
        
        //开发时把error msg 打出来
        modelAndView.addObject("msg", PropertiesUtils.findPropertiesKey(
                CommonConstants.ERROR_COMMON_ACTION_001) + " errormsg:" + ex.getMessage());
        logger.error("Exception is occoured when do action。", ex);
        return modelAndView;
    }
}
