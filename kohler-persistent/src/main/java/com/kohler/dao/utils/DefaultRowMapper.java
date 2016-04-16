/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.dao.utils;

import java.beans.BeanInfo;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


/**
 * 默认通用类型映射转换
 *
 * @author shefeng
 * @Date 2014年9月25日
 */
public class DefaultRowMapper implements RowMapper<Object> {

    private Class<?> clazz;//转换的目标对象

    private NameHandler nameHandler;//名称处理器

    public DefaultRowMapper(Class<?> clazz, NameHandler nameHandler) {
        this.clazz = clazz;
        this.nameHandler = nameHandler;
    }

    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
    	Object entity = null;
		try {
			entity = ClassUtils.newInstance(this.clazz);
			BeanInfo beanInfo = ClassUtils.getSelfBeanInfo(this.clazz);
			PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor pd : pds) {
	            String column = nameHandler.getColumnName(pd.getName());
	            Method writeMethod = pd.getWriteMethod();
	            if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
	                writeMethod.setAccessible(true);
	            }
	            writeMethod.invoke(entity, resultSet.getObject(column));
	        }
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException();
		}
        return entity;
    }
}