/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.dao;
import java.util.List;

import com.kohler.dao.utils.Pager;
import com.kohler.entity.SearchPhraseEntity;


/**
 * Page Dao 
 *
 * @author ys
 * @Date 2014-11-12
 */
public interface PhraseDao extends BaseDao<SearchPhraseEntity>{
    //列表查询
	public Pager<SearchPhraseEntity> getPharseList(Pager<SearchPhraseEntity> pager,
	        SearchPhraseEntity spelling);
	//新增
	public int createPharse(SearchPhraseEntity spelling);
	//删除
    public int deletePharse(SearchPhraseEntity spelling);
    //修改页面数据显示
    public List<SearchPhraseEntity> selectOne(List<Object> list);
    //修改保存
    public int updatePharse(SearchPhraseEntity spelling);
    //唯一约束
    public List<SearchPhraseEntity> selectForName(List<Object> params);
    
}
