package com.kohler.dao.impl;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.kohler.constants.SynonymSQL;
import com.kohler.dao.SynonymDao;
import com.kohler.dao.utils.Pager;
import com.kohler.entity.SearchSynonymEntity;
@Repository
public class SynonymDaoImpl extends BaseDaoImpl<SearchSynonymEntity> implements SynonymDao{

    /**
     * {@inheritDoc}
     */
    @Override
    public Pager<SearchSynonymEntity> getSynonymList(Pager<SearchSynonymEntity> pager,
            SearchSynonymEntity synonym) {
        // TODO Auto-generated method stub
        List<Object> params = new ArrayList<Object>();
        StringBuffer listSql = new StringBuffer(SynonymSQL.SELECT_SYNONYM_LIST);
        StringBuffer countSql = new StringBuffer(SynonymSQL.GET_SYNONYM_COUNT);
        if(synonym.getSettingValue() != null && !synonym.getSettingValue().equals("")){
            listSql.append(" and  SETTING_VALUE like concat(concat('%',?),'%')");
            countSql.append(" and  SETTING_VALUE like concat(concat('%',?),'%')");
            params.add(synonym.getSettingValue());
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
        List<SearchSynonymEntity> list = selectByCondition(listSql.toString(), params);
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
    public int createSynonym(SearchSynonymEntity synonym) {
        // TODO Auto-generated method stub
        return insert(synonym);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int deleteSynonym(SearchSynonymEntity synonym) {
        // TODO Auto-generated method stub
        return delete(synonym);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SearchSynonymEntity> selectOne(List<Object> params) {
        // TODO Auto-generated method stub
        StringBuffer listSql = new StringBuffer(SynonymSQL.SELECT_ONE_SYNONYM);
        List<SearchSynonymEntity> list = selectByCondition(listSql.toString(), params);
        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int updateSynonym(SearchSynonymEntity synonym) {
        // TODO Auto-generated method stub
        return update(synonym);
    }
    public List<SearchSynonymEntity> selectForName(List<Object> params) {
        // TODO Auto-generated method stub
        StringBuffer listSql = new StringBuffer(SynonymSQL.SELECT_ONE_SYNONYM_FOR_NAME);
        List<SearchSynonymEntity> list = selectByCondition(listSql.toString(), params);
        return list;
    }
}