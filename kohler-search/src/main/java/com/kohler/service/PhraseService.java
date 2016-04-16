/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.service;
import java.util.List;
import com.kohler.dao.utils.Pager;
import com.kohler.entity.SearchPhraseEntity;


/**
 * Phrase service
 * 
 * @author ys
 * @Date 11/12/2014
 */
public interface PhraseService{
  //列表查询
    public Pager<SearchPhraseEntity> getPharseList(Pager<SearchPhraseEntity> pager,
            SearchPhraseEntity phrase);
    //新增
    public int createPharse(SearchPhraseEntity phrase);
    //删除
    public int deletePharse(SearchPhraseEntity phrase);
    //修改页面数据显示
    public List<SearchPhraseEntity> selectOne(List<Object> list);
    //修改保存
    public int updatePharse(SearchPhraseEntity phrase);
}
