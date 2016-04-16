/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.service;
import java.util.List;

import com.kohler.dao.utils.Pager;
import com.kohler.entity.SearchStopWordEntity;


/**
 * Phrase service
 * 
 * @author ys
 * @Date 11/13/2014
 */
public interface StopWordService{
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
}
