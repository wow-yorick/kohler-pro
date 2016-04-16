/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kohler.dao.KeywordDao;
import com.kohler.dao.utils.Pager;
import com.kohler.entity.SearchKeywordRedirectionEntity;
import com.kohler.service.KeywordService;

/**
 * Phrase service implement
 * 
 * @author ys
 * @Date 10/13/2014
 */
@Service
public class KeywordServiceImpl implements KeywordService {
    @Autowired
    private KeywordDao keyDao;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Pager<SearchKeywordRedirectionEntity> getKeywordList(
            Pager<SearchKeywordRedirectionEntity> pager, SearchKeywordRedirectionEntity keyword) {
        // TODO Auto-generated method stub
        return keyDao.getKeywordList(pager, keyword);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int createKeyword(SearchKeywordRedirectionEntity keyword) {
        // TODO Auto-generated method stub
        return keyDao.createKeyword(keyword);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int deleteKeyword(SearchKeywordRedirectionEntity keyword) {
        // TODO Auto-generated method stub
        return keyDao.deleteKeyword(keyword);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SearchKeywordRedirectionEntity> selectOne(List<Object> list) {
        // TODO Auto-generated method stub
        return keyDao.selectOne(list);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int updateKeyword(SearchKeywordRedirectionEntity keyword) {
        // TODO Auto-generated method stub
        return keyDao.updateKeyword(keyword);
    }
}
