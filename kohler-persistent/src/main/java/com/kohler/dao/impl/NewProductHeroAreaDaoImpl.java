package com.kohler.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kohler.constants.NewProductSQL;
import com.kohler.dao.NewProductHeroAreaDao;
import com.kohler.entity.NewArrivalHeroAreaEntity;
@Repository
public class NewProductHeroAreaDaoImpl  extends BaseDaoImpl<NewArrivalHeroAreaEntity> implements NewProductHeroAreaDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<Map<String, Object>> NewProductHeroArea(Integer NewArrivalMetadataId,String fieldname) {
        // TODO Auto-generated method stub
        return jdbcTemplate.queryForList(NewProductSQL.SELECT_HeroAreA_LIST,new Object[]{NewArrivalMetadataId,fieldname,1});
    }
}
