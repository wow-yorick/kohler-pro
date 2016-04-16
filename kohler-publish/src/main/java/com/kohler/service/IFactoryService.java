/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service;


/**
 * 对象工厂接口
 *
 * @author Administrator
 * @Date 2014年12月2日
 */
public interface IFactoryService {
    /**
     * 更加具体对象名获取对象
     * @param objName
     * @return
     * @author Administrator
     * Date 2014年12月2日
     * @version
     */
    public Object createObject(String objName);
}
