/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohler.dao.StopWordDao;
import com.kohler.dao.utils.Pager;
import com.kohler.entity.SearchStopWordEntity;
import com.kohler.service.StopWordService;

/**
 * Phrase service implement
 * 
 * @author ys
 * @Date 10/13/2014
 */
@Service
public class StopWordServiceImpl implements StopWordService {
    @Autowired
    private StopWordDao stopDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public Pager<SearchStopWordEntity> getStopWordList(Pager<SearchStopWordEntity> pager,
            SearchStopWordEntity stopWord) {
        // TODO Auto-generated method stub
        return stopDao.getStopWordList(pager, stopWord);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int createStopWord(SearchStopWordEntity stopWord) {
        // TODO Auto-generated method stub
        List<Object> list = new ArrayList<Object>();
        list.add(stopWord.getSettingValue());
        list.add(0);
        List<SearchStopWordEntity> selectForName = stopDao.selectForName(list);
        if(selectForName.size() > 0)
            return 0;
        else
            return stopDao.createStopWord(stopWord);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int deleteStopWord(SearchStopWordEntity stopWord) {
        // TODO Auto-generated method stub
        return stopDao.deleteStopWord(stopWord);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SearchStopWordEntity> selectOne(List<Object> list) {
        // TODO Auto-generated method stub
        return stopDao.selectOne(list);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int updateStopWord(SearchStopWordEntity stopWord) {
        // TODO Auto-generated method stub
        List<Object> list = new ArrayList<Object>();
        list.add(stopWord.getSettingValue());
        list.add(stopWord.getSearchStopWordId());
        List<SearchStopWordEntity> selectForName = stopDao.selectForName(list);
        if(selectForName.size() > 0)
            return 0;
        else
            return stopDao.updateStopWord(stopWord);
    }
}
