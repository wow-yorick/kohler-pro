/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.service;
import java.util.List;

import com.kohler.dao.utils.Pager;
import com.kohler.entity.SearchKeywordRedirectionEntity;


/**
 * Phrase service
 * 
 * @author YS
 * @Date 11/14/2014
 */
public interface KeywordService{
    //列表查询
    public Pager<SearchKeywordRedirectionEntity> getKeywordList(Pager<SearchKeywordRedirectionEntity> pager,
            SearchKeywordRedirectionEntity keyword);
    //新增
    public int createKeyword(SearchKeywordRedirectionEntity keyword);
    //删除
    public int deleteKeyword(SearchKeywordRedirectionEntity keyword);
    //修改页面数据显示
    public List<SearchKeywordRedirectionEntity> selectOne(List<Object> list);
    //修改保存
    public int updateKeyword(SearchKeywordRedirectionEntity keyword);
}
