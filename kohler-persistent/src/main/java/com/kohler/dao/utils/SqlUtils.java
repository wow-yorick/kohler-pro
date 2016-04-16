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
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * sql构建类
 *
 * @author shefeng
 * @Date 2014年9月25日
 */
public class SqlUtils {

    private static final Logger LOG = LoggerFactory.getLogger(SqlUtils.class);

    /**
     * 构建insert语句
     *
     * @param entity 实体映射对象
     * @param nameHandler
     * @param nameHandler 名称转换处理器
     * @return
     */
    public static SqlContext buildInsertSql(Object entity, NameHandler nameHandler) {
        Class<?> clazz = entity.getClass();
        String tableName = nameHandler.getTableName(clazz.getSimpleName());
        String primaryName = nameHandler.getPrimaryName(clazz.getSimpleName());
        StringBuilder sql = new StringBuilder("insert into ");
        List<Object> params = new ArrayList<Object>();
        sql.append(tableName);

        //获取属性信息
        BeanInfo beanInfo = ClassUtils.getSelfBeanInfo(clazz);
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        sql.append("(");
        StringBuilder args = new StringBuilder();
        args.append("(");
        for (PropertyDescriptor pd : pds) {
            Object value = getReadMethodValue(pd.getReadMethod(), entity);
            if (value == null) {
                continue;
            }
            sql.append(nameHandler.getColumnName(pd.getName()));
            args.append("?");
            params.add(value);
            sql.append(",");
            args.append(",");
        }
        sql.deleteCharAt(sql.length() - 1);
        args.deleteCharAt(args.length() - 1);
        args.append(")");
        sql.append(")");
        sql.append(" values ");
        sql.append(args);
        return new SqlContext(sql, primaryName, params);
    }

    
    
    /**
     * 构建更新sql
     * 
     * @param entity
     * @param nameHandler
     * @param nameHandler
     * @return
     */
    public static SqlContext buildUpdateSql(Object entity, NameHandler nameHandler) {
        Class<?> clazz = entity.getClass();
        StringBuilder sql = new StringBuilder();
        List<Object> params = new ArrayList<Object>();
        String tableName = nameHandler.getTableName(clazz.getSimpleName());
        String primaryName = nameHandler.getPrimaryName(clazz.getSimpleName());
        //获取属性信息
        BeanInfo beanInfo = ClassUtils.getSelfBeanInfo(clazz);
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();

        sql.append("update ");
        sql.append(tableName);
        sql.append(" set ");
        Object primaryValue = null;
        for (PropertyDescriptor pd : pds) {
            String methodName=pd.getReadMethod().getName();
            Object value = getReadMethodValue(pd.getReadMethod(), entity);
            /*if (value == null) {
                continue;
            }*/
            if ("getCreateTime".equals(methodName) || "getCreateUser".equals(methodName)) {
                continue;
            }
            String columnName = nameHandler.getColumnName(pd.getName());
            if (primaryName.equalsIgnoreCase(columnName)) {
                primaryValue = value;
            }
            sql.append(columnName);
            sql.append(" = ");
            sql.append("?");
            params.add(value);
            sql.append(",");
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(" where ");
        sql.append(primaryName);
        sql.append(" = ?");
        params.add(primaryValue);
        return new SqlContext(sql, primaryName, params);
    }

    
    
    /**
     * 构建查询条件
     * 
     * @param entity
     *  @param nameHandler
     * @return
     */
    public static SqlContext buildQueryCondition(Object entity, NameHandler nameHandler) {
        //获取属性信息
        BeanInfo beanInfo = ClassUtils.getSelfBeanInfo(entity.getClass());
        //        PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(entityClass);
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        StringBuilder condition = new StringBuilder();
        List<Object> params = new ArrayList<Object>();
        int count = 0;
        for (PropertyDescriptor pd : pds) {
            Object value = getReadMethodValue(pd.getReadMethod(), entity);
            if (value == null) {
                continue;
            }
            if (count > 0) {
                condition.append(" and ");
            }
            condition.append(nameHandler.getColumnName(pd.getName()));
           /* if(pd.getPropertyType() == String.class){
         //   	condition.append(" like concat(concat('%',?),'%')");
            	condition.append(" = ?");
            }else{
            	condition.append(" = ?");
            }*/
            condition.append(" = ?");
            params.add(value);
            count++;
        }
        return new SqlContext(condition, null, params);
    }

    /**
     * 检索字符串含 * 号时， 都统一替换成 mysql 用的 %
     * @param value 需要替换的检索用字符串
     * @return 替换后的字符串
     * @author xj
     * Date 2014年10月24日
     * @version
     */
    public static String replaceSearchStr(String value) {
        if (StringUtils.isEmpty(value)) {
            return value;
        } else {
            return StringUtils.replace(value, "*", "%");
        }
    }
    
    
    /**
     * 获取属性值
     *
     * @param readMethod
     * @param entity
     * @return
     */
    private static Object getReadMethodValue(Method readMethod, Object entity) {
        if (readMethod == null) {
            return null;
        }
        try {
            if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                readMethod.setAccessible(true);
            }
            return readMethod.invoke(entity);
        } catch (Exception e) {
            LOG.error("获取属性值失败", e);
        }
		return entity;
    }
}