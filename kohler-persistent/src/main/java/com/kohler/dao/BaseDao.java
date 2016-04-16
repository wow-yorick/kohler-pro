/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * BaseDao Interface
 *
 * @author shefeng
 * @Date 2014年9月25日
 */
public interface BaseDao<T> {

    /**
     * 插入一条记录
     * @param entity
     * @return
     * @author shefeng Date 2014年9月27日
     * @version
     */
	public Integer insert(T entity);

	
	
	/**
	 * 修改一条记录
     * @param entity
     * @return
     * @author shefeng Date 2014年9月27日
     * @version
     */
	public Integer update(T entity);
	
	
	
	/**
	 * 删除一条记录(逻辑删除)
     * @param entity
     * @return
     * @author shefeng Date 2014年9月27日
     * @version
     */
	public Integer delete(T entity);

	
	
	/**
	 * 根据 id获取记录
     * @param id
     * @return
     * @author shefeng Date 2014年9月27日
     * @version
     */
	public T selectById(Serializable id);

	
	
	/**
	 * 获取所有记录
     * @return
     * @author shefeng Date 2014年9月27日
     * @version
     */
	public List<T> selectAll();
	


	/**
	 * 根据条件获取记录
     * @param entity
     * @return
     * @author shefeng Date 2014年9月27日
     * @version
     */
	public List<T> selectByCondition(T entity);
	
	
	
	/**
	 * 自定义查询
     * @param sql
     * @param params
     * @return
     * @author shefeng Date 2014年9月27日
     * @version
     */
	public List<T> selectByCondition(String sql,List<Object> params);
	
	
	
	/**
	 * 自定义查询的总记录数
     * @param sql
     * @param params
     * @return
     * @author shefeng Date 2014年9月27日
     * @version
     */
	public Integer selectCountByCondition(String sql,List<Object> params);
	
	
	
	/**
	 * 自定义查询以List<Map<String,Object>>形式返回
     * @param entity
     * @return
     * @author shefeng Date 2014年9月27日
     * @version
     */
	public List<Map<String, Object>> selectByConditionWithMap(String sql,List<Object> params);

}
