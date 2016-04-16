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

import com.kohler.dao.SynonymDao;
import com.kohler.dao.utils.Pager;
import com.kohler.entity.SearchSynonymEntity;
import com.kohler.service.SynonymService;

/**
 * Synonym service implement
 * 
 * @author ys
 * @Date 10/13/2014
 */
@Service
public class SynonymServiceImpl implements SynonymService {
    @Autowired
    private SynonymDao synonymDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public Pager<SearchSynonymEntity> getSynonymList(Pager<SearchSynonymEntity> pager,
            SearchSynonymEntity synonym) {
        // TODO Auto-generated method stub
        return synonymDao.getSynonymList(pager, synonym);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int createSynonym(SearchSynonymEntity synonym) {
        // TODO Auto-generated method stub
        List<Object> list = new ArrayList<Object>();
        list.add(synonym.getSettingValue());
        list.add(0);
        List<SearchSynonymEntity> selectForName = synonymDao.selectForName(list);
        if(selectForName.size() > 0)
            return 0;
        else
            return synonymDao.createSynonym(synonym);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int deleteSynonym(SearchSynonymEntity synonym) {
        // TODO Auto-generated method stub
        return synonymDao.deleteSynonym(synonym);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SearchSynonymEntity> selectOne(List<Object> list) {
        // TODO Auto-generated method stub
        return synonymDao.selectOne(list);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int updateSynonym(SearchSynonymEntity synonym) {
        // TODO Auto-generated method stub
        List<Object> list = new ArrayList<Object>();
        list.add(synonym.getSettingValue());
        list.add(synonym.getSearchSynonymId());
        List<SearchSynonymEntity> selectForName = synonymDao.selectForName(list);
        if(selectForName.size() > 0)
            return 0;
        else
            return synonymDao.updateSynonym(synonym);
    }
}
