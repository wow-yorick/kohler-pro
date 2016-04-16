/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.entity;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * properties util entity
 * 
 * @author zqq
 * @Date 10/9/2014
 */
public class PropertiesUtilEntity {

    private static Properties prop;

    private static Logger     logger = Logger.getLogger(PropertiesUtilEntity.class);
    static {
        InputStream is = PropertiesUtilEntity.class.getClassLoader().getResourceAsStream(
                "facet.properties");
        prop = new Properties();
        try {
            prop.load(is);
        } catch (Exception e) {
            logger.error("Can not read properties file!" + e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    logger.error("Close inputstream error!" + e);
                }
                is = null;
            }
        }
    }

    /**
     * Get property value by key
     * @Date : 2011-4-6
     * @param key property key
     * @return property value
     */
    public static String getProperties(String key) {
        String value = prop.getProperty(key);
        return value;
    }

}
