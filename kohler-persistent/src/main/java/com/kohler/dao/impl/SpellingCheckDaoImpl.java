package com.kohler.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.kohler.constants.SpellingCheckSQL;
import com.kohler.dao.SpellingCheckDao;
import com.kohler.dao.utils.Pager;
import com.kohler.entity.SearchSpellingCheckEntity;
@Repository
public class SpellingCheckDaoImpl extends BaseDaoImpl<SearchSpellingCheckEntity> implements SpellingCheckDao{

    /**
     * {@inheritDoc}
     */
    @Override
    public Pager<SearchSpellingCheckEntity> getSpellingCheckList(Pager<SearchSpellingCheckEntity> pager,
            SearchSpellingCheckEntity spelling) {
        List<Object> params = new ArrayList<Object>();
        StringBuffer listSql = new StringBuffer(SpellingCheckSQL.SELECT_SPELLING_CHECK_LIST);
        StringBuffer countSql = new StringBuffer(SpellingCheckSQL.GET_SPELLING_CHECK_COUNT);
        if(spelling.getSettingValue() != null && !spelling.getSettingValue().equals("")){
            listSql.append(" and  SETTING_VALUE like concat(concat('%',?),'%')");
            countSql.append(" and  SETTING_VALUE like concat(concat('%',?),'%')");
            params.add(spelling.getSettingValue());
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
        List<SearchSpellingCheckEntity> list = selectByCondition(listSql.toString(), params);
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
    public int createSpeiing(SearchSpellingCheckEntity spelling) {
        // TODO Auto-generated method stub
        return insert(spelling);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int deleteSpeiing(SearchSpellingCheckEntity spelling) {
        // TODO Auto-generated method stub
        return delete(spelling);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SearchSpellingCheckEntity> selectOne(List<Object> params) {
        // TODO Auto-generated method stub
        StringBuffer listSql = new StringBuffer(SpellingCheckSQL.SELECT_ONE_SPELLING_CHECK);
        List<SearchSpellingCheckEntity> list = selectByCondition(listSql.toString(), params);
        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int updateSpeiing(SearchSpellingCheckEntity spelling) {
        // TODO Auto-generated method stub
        return update(spelling);
    }
    public List<SearchSpellingCheckEntity> selectForName(List<Object> params) {
        // TODO Auto-generated method stub
        StringBuffer listSql = new StringBuffer(SpellingCheckSQL.SELECT_ONE_SPELLING_CHECKF_FOR_NAME);
        List<SearchSpellingCheckEntity> list = selectByCondition(listSql.toString(), params);
        return list;
    }
}