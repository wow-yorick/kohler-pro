package com.kohler.dao.impl;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.kohler.constants.KeywordSQL;
import com.kohler.dao.KeywordDao;
import com.kohler.dao.utils.Pager;
import com.kohler.entity.SearchKeywordRedirectionEntity;
@Repository
public class KeywordDaoImpl extends BaseDaoImpl<SearchKeywordRedirectionEntity> implements KeywordDao{

    /**
     * {@inheritDoc}
     */
    @Override
    public Pager<SearchKeywordRedirectionEntity> getKeywordList(
            Pager<SearchKeywordRedirectionEntity> pager, SearchKeywordRedirectionEntity keyword) {
        // TODO Auto-generated method stub
        List<Object> params = new ArrayList<Object>();
        StringBuffer listSql = new StringBuffer(KeywordSQL.SELECT_KEYWORD_LIST);
        StringBuffer countSql = new StringBuffer(KeywordSQL.GET_KEYWORD_COUNT);
        if(keyword.getKeyword() != null && !keyword.getKeyword().equals("")){
            listSql.append(" and  KEYWORD like concat(concat('%',?),'%')");
            countSql.append(" and  KEYWORD like concat(concat('%',?),'%')");
            params.add(keyword.getKeyword());
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
        List<SearchKeywordRedirectionEntity> list = selectByCondition(listSql.toString(), params);
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
    public int createKeyword(SearchKeywordRedirectionEntity keyword) {
        // TODO Auto-generated method stub
        return insert(keyword);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int deleteKeyword(SearchKeywordRedirectionEntity keyword) {
        // TODO Auto-generated method stub
        return delete(keyword);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SearchKeywordRedirectionEntity> selectOne(List<Object> params) {
        // TODO Auto-generated method stub
        StringBuffer listSql = new StringBuffer(KeywordSQL.SELECT_ONE_KEYWORD);
        List<SearchKeywordRedirectionEntity> list = selectByCondition(listSql.toString(), params);
        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int updateKeyword(SearchKeywordRedirectionEntity keyword) {
        // TODO Auto-generated method stub
        return update(keyword);
    }

//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public List<SearchStopWordEntity> selectOne(List<Object> params) {
//        // TODO Auto-generated method stub
//        StringBuffer listSql = new StringBuffer(StopWordSQL.SELECT_ONE_STOP_WORD);
//        List<SearchStopWordEntity> list = selectByCondition(listSql.toString(), params);
//        return list;
//    }
}