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

import com.kohler.dao.PhraseDao;
import com.kohler.dao.utils.Pager;
import com.kohler.entity.SearchPhraseEntity;
import com.kohler.service.PhraseService;

/**
 * Phrase service implement
 * 
 * @author ys
 * @Date 10/12/2014
 */
@Service
public class PhraseServiceImpl implements PhraseService {
    @Autowired
    private PhraseDao phraseDao;
    /**
     * {@inheritDoc}
     */
    @Override
    public Pager<SearchPhraseEntity> getPharseList(Pager<SearchPhraseEntity> pager,
            SearchPhraseEntity phrase) {
        // TODO Auto-generated method stub
        return phraseDao.getPharseList(pager, phrase);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int createPharse(SearchPhraseEntity phrase) {
        // TODO Auto-generated method stub
        List<Object> list = new ArrayList<Object>();
        list.add(phrase.getSettingValue());
        list.add(0);
        List<SearchPhraseEntity> selectForName = phraseDao.selectForName(list);
        if(selectForName.size() > 0)
            return 0;
        else
            return phraseDao.createPharse(phrase);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int deletePharse(SearchPhraseEntity phrase) {
        // TODO Auto-generated method stub
        return phraseDao.deletePharse(phrase);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SearchPhraseEntity> selectOne(List<Object> list) {
        // TODO Auto-generated method stub
        return phraseDao.selectOne(list);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int updatePharse(SearchPhraseEntity phrase) {
        // TODO Auto-generated method stub
        List<Object> list = new ArrayList<Object>();
        list.add(phrase.getSettingValue());
        list.add(phrase.getSearchPhraseId());
        List<SearchPhraseEntity> selectForName = phraseDao.selectForName(list);
        if(selectForName.size() > 0)
            return 0;
        else
            return phraseDao.updatePharse(phrase);
    }

}
