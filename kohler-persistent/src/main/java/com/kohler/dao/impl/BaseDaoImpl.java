/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.kohler.dao.BaseDao;
import com.kohler.dao.utils.DefaultNameHandler;
import com.kohler.dao.utils.DefaultRowMapper;
import com.kohler.dao.utils.NameHandler;
import com.kohler.dao.utils.SqlContext;
import com.kohler.dao.utils.SqlUtils;
import com.kohler.entity.SysUserEntity;
import com.kohler.util.SysContent;

/**
 * BaseDao Interface Impl
 * 
 * @author shefeng
 * @Date 2014年9月25日
 */
public class BaseDaoImpl<T> implements BaseDao<T> {
    protected final static Logger logger = Logger.getLogger(BaseDaoImpl.class);

    @Autowired
    protected JdbcTemplate        jdbcTemplate;

    private Class<T>              entityClass;                                  // 具体操作的实体类对象

    private NameHandler           nameHandler;                                  // 名称加工处理器

    @SuppressWarnings("unchecked")
    public BaseDaoImpl() {
        Type superclass = getClass().getGenericSuperclass();
        ParameterizedType type = (ParameterizedType) superclass;
        entityClass = (Class<T>) type.getActualTypeArguments()[0];
    }

    private NameHandler getActualNameHandler() {
        if (nameHandler == null) {
            synchronized (this) {
                if (nameHandler == null) {
                    nameHandler = this.getNameHandler();
                }
            }
        }
        return nameHandler;
    }

    protected NameHandler getNameHandler() {
        return new DefaultNameHandler();
    }

    @Override
    public synchronized Integer insert(T entity) {

        setTime(entity, "setCreateTime");
        setOperator(entity, "setCreateUser");
        setTime(entity, "setModifyTime");
        setOperator(entity, "setModifyUser");

        final SqlContext sqlContext = SqlUtils.buildInsertSql(entity, this.getActualNameHandler());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sqlContext.getSql().toString(),
                        new String[] { sqlContext.getPrimaryKey() });
                int index = 0;
                for (Object param : sqlContext.getParams()) {
                    index++;
                    ps.setObject(index, param);
                }
                return ps;
            }
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public Integer update(T entity) {

        setTime(entity, "setModifyTime");
        setOperator(entity, "setModifyUser");

        SqlContext sqlContext = SqlUtils.buildUpdateSql(entity, this.getActualNameHandler());
        return jdbcTemplate
                .update(sqlContext.getSql().toString(), sqlContext.getParams().toArray());
    }

    @SuppressWarnings("unchecked")
    @Override
    public T selectById(Serializable id) {
        String tableName = this.getNameHandler().getTableName(entityClass.getSimpleName());
        String primaryName = this.getNameHandler().getPrimaryName(entityClass.getSimpleName());
        String sql = "SELECT * FROM " + tableName + " WHERE IS_ENABLE=1 and " + primaryName
                + " = ?";
        List<Object> temp = jdbcTemplate.query(sql,
                new DefaultRowMapper(entityClass, this.getActualNameHandler()), id);

        try {
            jdbcTemplate.getDataSource().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (temp != null && temp.size() > 0) {
            return (T) temp.get(0);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> selectAll() {
        String sql = "SELECT * FROM "
                + this.getActualNameHandler().getTableName(entityClass.getSimpleName());

        List<T> t = (List<T>) jdbcTemplate.query(sql,
                new DefaultRowMapper(entityClass, this.getActualNameHandler()));
        try {
            jdbcTemplate.getDataSource().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return t;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> selectByCondition(T entity) {
        final SqlContext sqlContext = SqlUtils.buildQueryCondition(entity,
                this.getActualNameHandler());
        String tableName = this.getNameHandler().getTableName(entityClass.getSimpleName());
        StringBuilder sql = new StringBuilder("SELECT * FROM ");
        sql.append(tableName);
        if (sqlContext.getSql().length() > 0) {
            sql.append(" where ");
            sql.append(sqlContext.getSql());
        }

        List<T> t = (List<T>) jdbcTemplate.query(sql.toString(), sqlContext.getParams().toArray(),
                new DefaultRowMapper(entityClass, this.getActualNameHandler()));

        try {
            jdbcTemplate.getDataSource().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return t;
    }

    public int queryCount(T entity) {
        String tableName = this.getActualNameHandler().getTableName(entityClass.getSimpleName());
        StringBuilder countSql = new StringBuilder("select count(*) from ");
        countSql.append(tableName);
        SqlContext sqlContext = SqlUtils.buildQueryCondition(entity, this.getActualNameHandler());
        if (sqlContext.getSql().length() > 0) {
            countSql.append(" where ");
            countSql.append(sqlContext.getSql());
        }
        return jdbcTemplate.queryForInt(countSql.toString(), sqlContext.getParams().toArray());
    }

    private void setOperator(Object obj, String methodName) {
        Class<?> clazz = obj.getClass();
        try {
            // Method createMethod = clazz.getDeclaredMethod(methodName,
            // String.class);
            Method createMethod = clazz.getSuperclass().getDeclaredMethod(methodName, String.class);
            HttpSession session = SysContent.getSession();
            SysUserEntity user = null;
            if (session != null) {
                user = (SysUserEntity) session.getAttribute("sysUser");
                if(user == null){
                    createMethod.invoke(obj, "");
                }else{
                    createMethod.invoke(obj, user.getUserName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setTime(Object obj, String methodName) {
        Class<?> clazz = obj.getClass();
        try {
            // Method createMethod = clazz.getDeclaredMethod(methodName,
            // Date.class);
            Method createMethod = clazz.getSuperclass().getDeclaredMethod(methodName, Date.class);
            createMethod.invoke(obj, new Date());
        } catch (Exception e) {
            logger.error("SetTime Error",e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> selectByCondition(final String sql, final List<Object> params) {

        List<T> t = (List<T>) jdbcTemplate.query(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

                PreparedStatement ps = con.prepareStatement(sql);
                if (params != null && params.size() > 0) {
                    for (int i = 0; i < params.size(); i++) {
                        ps.setObject(i+1, params.get(i));
                    }
                }
                return ps;
            }
        }, new DefaultRowMapper(entityClass, this.getActualNameHandler()));
//        
//        try {
//            jdbcTemplate.getDataSource().getConnection().close();
//        } catch (Exception e) {
//            logger.error("SelectByCondition Error",e);
//        }
        return t;
    }

    @Override
    public Integer selectCountByCondition(String sql, List<Object> params) {
        return jdbcTemplate.queryForInt(sql, params.toArray());
    }

    @Override
    public List<Map<String, Object>> selectByConditionWithMap(final String sql,
            final List<Object> params) {

//        List<Map<String, Object>> t2 = jdbcTemplate.queryForList(sql,params.toArray());
        List<Map<String, Object>> t = (List<Map<String, Object>>) jdbcTemplate.query(
                new PreparedStatementCreator() {

                    @Override
                    public PreparedStatement createPreparedStatement(Connection con)
                            throws SQLException {

                        PreparedStatement ps = con.prepareStatement(sql);
                        if (params != null && params.size() > 0) {
                            for (int i = 0; i < params.size(); i++) {
                                ps.setObject(i+1, params.get(i));
                            }
                        }
                        return ps;
                    }
                }, new ResultSetExtractor<List<Map<String, Object>>>(){

                    @Override
                    public List<Map<String, Object>> extractData(ResultSet rs) throws SQLException {
                        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                        ResultSetMetaData rsmd = rs.getMetaData();  
                        while(rs.next()){
                            Map<String, Object> map = new HashMap<String,Object>();
                            int columnCount = rsmd.getColumnCount();  
                            for(int i=0;i<columnCount;i++){  
                                String columnName = rsmd.getColumnLabel(i+1);
                                map.put(columnName, rs.getObject(i+1));  
                            }  
                            list.add(map);
                        }
                        return list;
                    }
                }
        );

        return t;
    }

    @Override
    public Integer delete(T entity) {
        setTime(entity, "setModifyTime");
        setOperator(entity, "setModifyUser");
        setIsEnable(entity);

        SqlContext sqlContext = SqlUtils.buildUpdateSql(entity, this.getActualNameHandler());
        return jdbcTemplate
                .update(sqlContext.getSql().toString(), sqlContext.getParams().toArray());
    }

    private void setIsEnable(Object obj) {
        Class<?> clazz = obj.getClass();
        try {
            Method createMethod = clazz.getSuperclass().getDeclaredMethod("setIsEnable",
                    Boolean.class);
            createMethod.invoke(obj, Boolean.FALSE);
        } catch (Exception e) {
        }
    }
}
