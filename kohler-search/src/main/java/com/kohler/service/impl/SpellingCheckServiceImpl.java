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

import com.kohler.dao.SpellingCheckDao;
import com.kohler.dao.utils.Pager;
import com.kohler.entity.SearchSpellingCheckEntity;
import com.kohler.service.SpellingCheckService;

/**
 * SpellingCheck service implement
 * 
 * @author ys
 * @Date 10/9/2014
 */
@Service
public class SpellingCheckServiceImpl implements SpellingCheckService {
    @Autowired
    private SpellingCheckDao spellingDao;

    /**
     * {@inheritDoc}页面显示
     */
    @Override
    public Pager<SearchSpellingCheckEntity> getSpellingCheckList(
            Pager<SearchSpellingCheckEntity> pager, SearchSpellingCheckEntity spelling) {
        // TODO Auto-generated method stub
        return spellingDao.getSpellingCheckList(pager, spelling);
    }

    /**
     * {@inheritDoc}新增
     */
    @Override
    public int createSpeiing(SearchSpellingCheckEntity spelling) {
        // TODO Auto-generated method stub
        List<Object> list = new ArrayList<Object>();
        list.add(spelling.getSettingValue());
        list.add(0);
        List<SearchSpellingCheckEntity> selectForName = spellingDao.selectForName(list);
        if(selectForName.size() > 0)
            return 0;
        else
            return spellingDao.createSpeiing(spelling);
    }

    /**
     * {@inheritDoc}删除
     */
    @Override
    public int deleteSpeiing(SearchSpellingCheckEntity spelling) {
        // TODO Auto-generated method stub
        return spellingDao.delete(spelling);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SearchSpellingCheckEntity> selectOne(List<Object> params) {
        // TODO Auto-generated method stub
        return spellingDao.selectOne(params);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int updateSpeiing(SearchSpellingCheckEntity spelling) {
        // TODO Auto-generated method stub
        List<Object> list = new ArrayList<Object>();
        list.add(spelling.getSettingValue());
        list.add(spelling.getSearchSpellingCheckId());
        List<SearchSpellingCheckEntity> selectForName = spellingDao.selectForName(list);
        if(selectForName.size() > 0)
            return 0;
        else
            return spellingDao.updateSpeiing(spelling);
    }

}
