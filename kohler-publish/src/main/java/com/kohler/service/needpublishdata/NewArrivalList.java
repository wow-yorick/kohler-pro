/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.service.needpublishdata;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kohler.bean.ConfPrepareData;
import com.kohler.dao.NewArrivalMetadataDao;
import com.kohler.entity.NewArrivalMetadataEntity;
import com.kohler.service.NeedPublishDataService;

/**
 * 获取新品列表
 *
 * @author Administrator
 * @Date 2014年11月17日
 */
@Component
public class NewArrivalList implements NeedPublishDataService {

    @Autowired
    private NewArrivalMetadataDao newArrivalMetadataDao;

    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Integer> getPrimaryKeyList(Integer keyId, ConfPrepareData conf) {
        
        List<Integer> retList = new ArrayList<Integer>();
        
        NewArrivalMetadataEntity neArrivalMetadataEntity = new NewArrivalMetadataEntity();
        neArrivalMetadataEntity.setIsEnable(true);
        
        List<NewArrivalMetadataEntity> newArrivalMetadataEntity = newArrivalMetadataDao.selectByCondition(neArrivalMetadataEntity);
        for(NewArrivalMetadataEntity newArrivalMetadata : newArrivalMetadataEntity) {
            retList.add(newArrivalMetadata.getNewArrivalMetadataId()); 
        }
        
        return retList;
    }

}
