/**
 * 
 */
package com.kohler.dao.utils;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;


/**
 * 
 * Class Function Description
 *
 * @author sana
 * @Date 2014年11月17日
 */
public class PropertyUtil {


   public static String getPropertyByKey(String key) {
      Properties props = new Properties();
      String value = "";
       try {
    	 //配置文件的路径要注意,下面的getResurceAsStream是webinf\classes下面的文件，如果要变更，这边需要修改
         props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("app.properties"));
         value = props.getProperty(key);
         if(value == null || ("").equals(value.trim())) {
            throw new RuntimeException("Please configure the "+key+" in the file config.properties ");
         }         

      } catch (IOException e) {
         e.printStackTrace();
      }
      return value;
   }
  
   
   public static String getAllProperties() {
      Properties props = new Properties();
      String value = "";
       try {
         props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
        Enumeration<Object> keys = props.keys();
        while(keys.hasMoreElements()) {
           String key = (String)keys.nextElement();
           String val = props.getProperty(key);
           value += " key=="+key+"  value== "+val+"  \n";
        }
         if(value == null || ("").equals(value.trim())) {
            throw new RuntimeException("Cann't find the config.properties! ");
         }         

      } catch (IOException e) {
         e.printStackTrace();
      }
      return value;
   }
}
