/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.util;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * Properties Util
 * 
 * @author kangmin_Qu
 * @Date 2014-10-17
 */
public class PropertiesUtils {

    /*
     * app.properties path
     */
    private final static String APP_PATH = "/message.properties";

    /**
     * 获取属性文件的数据 根据key获取值
     * @param fileName 文件名　(注意：加载的是src下的文件,如果在某个包下．请把包名加上)
     * @param key
     * @return
     */
    public static String findPropertiesKey(String key) {

        try {
            Properties prop = getProperties();
            return prop.getProperty(key);
        } catch (Exception e) {
            return "";
        }

    }

    /**
     * 返回　Properties
     * @param fileName 文件名　(注意：加载的是src下的文件,如果在某个包下．请把包名加上)
     * @param
     * @return
     */
    public static Properties getProperties() {
        Properties prop = new Properties();
        try {
            InputStream in = PropertiesUtils.class.getResourceAsStream(APP_PATH);
            prop.load(in);
        } catch (Exception e) {
            return null;
        }
        return prop;
    }

    /**
     * 写入properties信息
     * 
     * @param key 名称
     * @param value 值
     */
    public static void modifyProperties(String key, String value) {
        try {
            // 从输入流中读取属性列表（键和元素对）
            Properties prop = getProperties();
            prop.setProperty(key, value);
            String path = PropertiesUtils.class.getResource(APP_PATH).getPath();
            FileOutputStream outputFile = new FileOutputStream(path);
            prop.store(outputFile, "modify");
            outputFile.close();
            outputFile.flush();
        } catch (Exception e) {
        }
    }
}
