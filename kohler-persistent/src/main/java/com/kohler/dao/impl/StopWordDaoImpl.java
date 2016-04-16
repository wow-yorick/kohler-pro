package com.kohler.dao.impl;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.kohler.constants.StopWordSQL;
import com.kohler.dao.StopWordDao;
import com.kohler.dao.utils.Pager;
import com.kohler.entity.SearchStopWordEntity;
@Repository
public class StopWordDaoImpl extends BaseDaoImpl<SearchStopWordEntity> implements StopWordDao{

    /**
     * {@inheritDoc}
     */
    @Override
    public Pager<SearchStopWordEntity> getStopWordList(Pager<SearchStopWordEntity> pager,
            SearchStopWordEntity stopWord) {
      //TODO Auto-generated method stub
      List<Object> params = new ArrayList<Object>();
      StringBuffer listSql = new StringBuffer(StopWordSQL.SELECT_STOP_WORD_LIST);
      StringBuffer countSql = new StringBuffer(StopWordSQL.GET_STOP_WORD_COUNT);
      if(stopWord.getSettingValue() != null && !stopWord.getSettingValue().equals("")){
          listSql.append(" and  SETTING_VALUE like concat(concat('%',?),'%')");
          countSql.append(" and  SETTING_VALUE like concat(concat('%',?),'%')");
          params.add(stopWord.getSettingValue());
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
      List<SearchStopWordEntity> list = selectByCondition(listSql.toString(), params);
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
    public int createStopWord(SearchStopWordEntity stopWord) {
        // TODO Auto-generated method stub
        return insert(stopWord);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int deleteStopWord(SearchStopWordEntity stopWord) {
        // TODO Auto-generated method stub
        return delete(stopWord);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SearchStopWordEntity> selectOne(List<Object> params) {
        // TODO Auto-generated method stub
        StringBuffer listSql = new StringBuffer(StopWordSQL.SELECT_ONE_STOP_WORD);
        List<SearchStopWordEntity> list = selectByCondition(listSql.toString(), params);
        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int updateStopWord(SearchStopWordEntity stopWord) {
        // TODO Auto-generated method stub
        return update(stopWord);
    }
    public List<SearchStopWordEntity> selectForName(List<Object> params) {
        // TODO Auto-generated method stub
        StringBuffer listSql = new StringBuffer(StopWordSQL.SELECT_ONE_STOP_WORD_FOR_NAME);
        List<SearchStopWordEntity> list = selectByCondition(listSql.toString(), params);
        return list;
    }
}