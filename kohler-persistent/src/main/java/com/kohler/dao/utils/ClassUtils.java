/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.dao.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * 类辅助工具
 *
 * @author shefeng
 * @Date 2014年9月25日
 */
public class ClassUtils {

    
    @SuppressWarnings("rawtypes")
	private static final Map<Class, BeanInfo> classCache = Collections.synchronizedMap(new WeakHashMap<Class, BeanInfo>());

    /**
     * 获取类本身的BeanInfo
     * 
     * @param clazz
     * @return
     */
    @SuppressWarnings("rawtypes")
	public static BeanInfo getSelfBeanInfo(Class<?> clazz) {
        try {
            BeanInfo beanInfo;
            if (classCache.get(clazz) == null) {
//            	beanInfo = Introspector.getBeanInfo(clazz, clazz.getSuperclass());
            	beanInfo = Introspector.getBeanInfo(clazz, Object.class);
                classCache.put(clazz, beanInfo);
                // Immediately remove class from Introspector cache, to allow for proper
                // garbage collection on class loader shutdown - we cache it here anyway,
                // in a GC-friendly manner. In contrast to CachedIntrospectionResults,
                // Introspector does not use WeakReferences as values of its WeakHashMap!
                Class classToFlush = clazz;
                do {
                    Introspector.flushFromCaches(classToFlush);
                    classToFlush = classToFlush.getSuperclass();
                } while (classToFlush != null);
            } else {
//                beanInfo = classCache.get(clazz);
            	beanInfo = Introspector.getBeanInfo(clazz, Object.class);
            }
            return beanInfo;
        } catch (IntrospectionException e) {
        }
		return null;
    }

    
    
    /**
     * 初始化实例
     * 
     * @param clazz
     * @return
     * @throws Exception 
     */
    public static Object newInstance(Class<?> clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
        }
		return clazz;
    }
}