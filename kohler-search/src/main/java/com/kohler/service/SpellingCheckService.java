/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.service;
import java.util.List;

import com.kohler.dao.utils.Pager;
import com.kohler.entity.SearchSpellingCheckEntity;


/**
 * spellingCheck service
 * 
 * @author ys
 * @Date 11/12/2014
 */
public interface SpellingCheckService{
    //列表查询
    public Pager<SearchSpellingCheckEntity> getSpellingCheckList(Pager<SearchSpellingCheckEntity> pager,
            SearchSpellingCheckEntity spelling);
    //新增
    public int createSpeiing(SearchSpellingCheckEntity spelling);
    //删除
    public int deleteSpeiing(SearchSpellingCheckEntity spelling);
    //修改页面数据显示
    public List<SearchSpellingCheckEntity> selectOne(List<Object> params);
    //修改保存
    public int updateSpeiing(SearchSpellingCheckEntity spelling);
}
