/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * Common Utils
 *
 * @author kangmin_Qu
 * @Date 2014-10-20
 */
public class CommonUtils {

    /*
     * LOG4J Print Object
     */
    protected final static Logger logger = Logger.getLogger(CommonUtils.class);
    
    /**
     * Couver Object To String
     * @param obj
     * @return
     * @author kangmin_Qu
     * Date 2014-10-20
     * @version
     */
    public static String coverObjectToString(Object obj){
        return obj != null ? obj.toString() : ""; 
    }

}
