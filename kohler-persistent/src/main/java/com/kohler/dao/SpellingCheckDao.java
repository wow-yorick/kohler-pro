/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.dao;
import java.util.List;

import com.kohler.dao.utils.Pager;
import com.kohler.entity.SearchSpellingCheckEntity;


/**
 * Page Dao 
 *
 * @author ys
 * @Date 2014-11-10
 */
public interface SpellingCheckDao extends BaseDao<SearchSpellingCheckEntity>{
    //列表查询
	public Pager<SearchSpellingCheckEntity> getSpellingCheckList(Pager<SearchSpellingCheckEntity> pager,
	        SearchSpellingCheckEntity spelling);
	//新增
	public int createSpeiing(SearchSpellingCheckEntity spelling);
	//删除
    public int deleteSpeiing(SearchSpellingCheckEntity spelling);
    //修改页面数据显示
    public List<SearchSpellingCheckEntity> selectOne(List<Object> list);
    //修改保存
    public int updateSpeiing(SearchSpellingCheckEntity spelling);
    //唯一约束
    public List<SearchSpellingCheckEntity> selectForName(List<Object> params);
}
