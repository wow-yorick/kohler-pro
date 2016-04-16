package com.kohler.dao.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kohler.constants.NewProductSQL;
import com.kohler.dao.NewProductBannerUnitDao;
import com.kohler.entity.NewArrivalBannerUnitEntity;
@Repository
public class NewProductBannerUnitDaoImpl extends BaseDaoImpl<NewArrivalBannerUnitEntity> implements NewProductBannerUnitDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<Map<String, Object>> BannerUnit(Integer NewArrivalMetadataId, String fieldname) {
        // TODO Auto-generated method stub
        return jdbcTemplate.queryForList(NewProductSQL.SELECT_Bannerunit_LIST,new Object[]{NewArrivalMetadataId,fieldname,1});
    }
}
