package com.kohler.dao.impl;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.kohler.constants.PhraseSQL;
import com.kohler.dao.PhraseDao;
import com.kohler.dao.utils.Pager;
import com.kohler.entity.SearchPhraseEntity;
@Repository
public class PhraseDaoImpl extends BaseDaoImpl<SearchPhraseEntity> implements PhraseDao{

    /**
     * {@inheritDoc}
     */
    @Override
    public Pager<SearchPhraseEntity> getPharseList(Pager<SearchPhraseEntity> pager,
            SearchPhraseEntity phrase) {
        // TODO Auto-generated method stub
        List<Object> params = new ArrayList<Object>();
        StringBuffer listSql = new StringBuffer(PhraseSQL.SELECT_PHRASE_LIST);
        StringBuffer countSql = new StringBuffer(PhraseSQL.GET_PHRASE_COUNT);
        if(phrase.getSettingValue() != null && !phrase.getSettingValue().equals("")){
            listSql.append(" and  SETTING_VALUE like concat(concat('%',?),'%')");
            countSql.append(" and  SETTING_VALUE like concat(concat('%',?),'%')");
            params.add(phrase.getSettingValue());
        }
        listSql.append(" LIMIT ?,?");
        int templateCount = selectCountByCondition(countSql.toString(), params);
        int pageCount = 0;
        if (templateCount % pager.getPageSize() == 0) {
            pageCount = templateCount / pager.getPageSize();
        } else {
            pageCount = templateCount / pager.getPageSize() + 1;
        }
        int index = (pager.getCurrentPage() - 1) * pager.getPageSize();
        params.add(index);
        params.add(pager.getPageSize());
        List<SearchPhraseEntity> list = selectByCondition(listSql.toString(), params);
        pager.setStartRow(index);
        pager.setDatas(list);
        pager.setTotalRecord(templateCount);
        pager.setTotalPage(pageCount);
        return pager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int createPharse(SearchPhraseEntity spelling) {
        // TODO Auto-generated method stub
        return insert(spelling);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int deletePharse(SearchPhraseEntity spelling) {
        // TODO Auto-generated method stub
        return delete(spelling);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SearchPhraseEntity> selectOne(List<Object> params) {
        // TODO Auto-generated method stub
        StringBuffer listSql = new StringBuffer(PhraseSQL.SELECT_ONE_PHRASE);
        List<SearchPhraseEntity> list = selectByCondition(listSql.toString(), params);
        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int updatePharse(SearchPhraseEntity spelling) {
        // TODO Auto-generated method stub
        return update(spelling);
    }

    public List<SearchPhraseEntity> selectForName(List<Object> params) {
        // TODO Auto-generated method stub
        StringBuffer listSql = new StringBuffer(PhraseSQL.SELECT_ONE_PHRASE_FRO_NAME);
        List<SearchPhraseEntity> list = selectByCondition(listSql.toString(), params);
        return list;
    }
    
}