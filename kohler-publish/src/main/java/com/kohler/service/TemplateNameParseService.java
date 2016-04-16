/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service;

import com.kohler.bean.ConfPrepareData;

/**
 * 解析模板名称的配置
 *
 * @author Administrator
 * @Date 2014年11月18日
 */
public interface TemplateNameParseService {
    /**
     * 根据名称规则配置获取实际名称
     * @param generalName
     * @return
     * @author Administrator
     * Date 2014年11月18日
     * @version
     */
    public String getGoalName(String generalName, ConfPrepareData conf);
}
