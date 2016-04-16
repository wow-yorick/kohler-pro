/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service;

import java.util.Map;

import com.kohler.bean.PublishConf;

/**
 * 发布功能外部调用接口
 *
 * @author Administrator
 * @Date 2014年11月21日
 */
public interface PublishHtmlForInterfaceService {
    /**
     * 外部发布按钮
     * @param publishConf
     * @param isPreview
     * @return
     * @author Administrator
     * Date 2014年11月21日
     * @version
     */
    public Map<String,Object> run(PublishConf publishConf) throws Exception;
}
