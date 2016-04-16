/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.service;
import java.util.List;
import com.kohler.dao.utils.Pager;
import com.kohler.entity.SearchSynonymEntity;


/**
 * Phrase service
 * 
 * @author ys
 * @Date 11/13/2014
 */
public interface SynonymService{
  //列表查询
    public Pager<SearchSynonymEntity> getSynonymList(Pager<SearchSynonymEntity> pager,
            SearchSynonymEntity synonym);
    //新增
    public int createSynonym(SearchSynonymEntity synonym);
    //删除
    public int deleteSynonym(SearchSynonymEntity synonym);
    //修改页面数据显示
    public List<SearchSynonymEntity> selectOne(List<Object> list);
    //修改保存
    public int updateSynonym(SearchSynonymEntity synonym);
}
