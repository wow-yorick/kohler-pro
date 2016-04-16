/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.dao;
import java.util.List;

import com.kohler.dao.utils.Pager;
import com.kohler.entity.SearchStopWordEntity;


/**
 * Page Dao 
 *
 * @author ys
 * @Date 2014-11-13
 */
public interface StopWordDao extends BaseDao<SearchStopWordEntity>{
    //列表查询
	public Pager<SearchStopWordEntity> getStopWordList(Pager<SearchStopWordEntity> pager,
	        SearchStopWordEntity stopWord);
	//新增
	public int createStopWord(SearchStopWordEntity stopWord);
	//删除
    public int deleteStopWord(SearchStopWordEntity stopWord);
    //修改页面数据显示
    public List<SearchStopWordEntity> selectOne(List<Object> list);
    //修改保存
    public int updateStopWord(SearchStopWordEntity stopWord);
    //唯一约束
    public List<SearchStopWordEntity> selectForName(List<Object> params);
}
