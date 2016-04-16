/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.util;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 模板名称解析
 *
 * @author Administrator
 * @Date 2014年11月18日
 */

public class TemplateNameParse {
    
    /**
     * 匹配一些变量
     * @param str
     * @author Administrator
     * Date 2014年10月30日
     * @version
     */
    public static  String patternCompile(String str,Map<String,Object> replaceMap) {

        if(str == null || "".equals(str)) {
            return "";
        }
        Pattern p = Pattern.compile("@+(\\w*)");
        Matcher m = p.matcher(str);
        if(m.groupCount() >0) {
            while (m.find()) {
                Object paramVal = replaceMap.get(m.group(1));
                if(replaceMap.containsKey(m.group(1)) && paramVal != null) {
                    str = str.replace(m.group(0),paramVal.toString());                
                }
            }
            StringBuilder retStr = new StringBuilder();
            String[] _tmpStr = str.split("&");
            for(String _tmp : _tmpStr) {
                retStr.append(_tmp);
            }
            return retStr.toString();
        } else {
            return str;
        }
        
    }
    
}
