package com.kohler.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.kohler.dao.SearchConfigurationDao;
import com.kohler.entity.MasterDataCodeConstant;
import com.kohler.entity.MasterdataEntity;
@Repository
public class SearchConfigurationDaoImpl extends BaseDaoImpl<MasterdataEntity> implements SearchConfigurationDao{

    /**
     * {@inheritDoc}
     */
    @Override
    public int updateDidYouMean(MasterdataEntity entity) {
        // TODO Auto-generated method stub
        List<Object> params = new ArrayList<Object>();
        params.add(MasterDataCodeConstant.SEARCH_SETTINGS_DID_YOU_MEAN_THRESHOLD);
        StringBuffer listSql = new StringBuffer("select * from MASTERDATA where MASTERDATA_METADATA_ID = ?");
        List<MasterdataEntity> list = selectByCondition(listSql.toString(), params);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setMasterdataName(entity.getMasterdataName());
            update(list.get(i));
        }
        return 1;
    }

}